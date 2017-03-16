package org.nasdanika.html.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.html.Bootstrap;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Pills;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;

class PillsImpl extends UIElementImpl<Pills> implements Pills {

	private String pillsId;

	PillsImpl(HTMLFactory factory) {
		super(factory, TagName.div);
		pillsId = factory.nextId()+"_pills";
	}
	
	private abstract class Pill implements AutoCloseable {
		int idx;
		boolean active;
		Object name;
		
		@Override
		public void close() throws Exception {
			UIElementImpl.close(name);			
		}

		Pill(Object name, int idx, boolean active) {
			super();
			this.name = name;
			this.idx = idx;
			this.active = active;
		}	
		
		Tag li() {
			Tag ret = factory.tag("li");
			if (active) {
				ret.addClass("active");
			}
			ret.content(link());			
			return ret;
		}
		
		Tag div() {
			Tag ret = factory.div(pillContent()).addClass("tab-pane").id(pillsId+"_"+idx);
			if (active) {
				ret.addClass("active");
			}
			return ret;
		}
		
		protected abstract Tag link();
		
		protected abstract Object pillContent();
		
	}
	
	private class ContentPill extends Pill {
		
		ContentPill(Object name, int idx, Object content, boolean active) {
			super(name, idx, active);
			this.content = content;
		}

		Object content;
		
		@Override
		public void close() throws Exception {
			super.close();
			UIElementImpl.close(content);
		}

		@Override
		protected Tag link() {
			Tag link = factory.link("#"+pillsId+"_"+idx, name).attribute("data-toggle", "pill");
			return link;
		}
		
		@Override
		protected Object pillContent() {
			return content;
		}
	}
	
	private class AjaxPill extends Pill {
		
		AjaxPill(Object name, int idx, Object location, boolean active) {
			super(name, idx, active);
			this.location = location;
		}

		Object location;
		
		@Override
		public void close() throws Exception {
			super.close();
			UIElementImpl.close(location);
		}

		@Override
		protected Tag link() {
			Tag link = factory.link(location.toString(), name).attribute("data-target", "#"+pillsId+"_"+idx).attribute("data-toggle", "pillajax");
			return link;
		}
		
		@Override
		protected Object[] pillContent() {
			return new Object[] {""};
		}
		
		@Override
		Tag div() {
			Tag ret = super.div();
			if (idx==0) {
				ret.remoteContent(location.toString()); // Loading active tab
			}
			return ret;
		}
	}
	
	private List<Pill> pills = new ArrayList<>();

	@Override
	public void close() throws Exception {
		super.close();
		close(pills);
	}

	@Override
	public Pills item(Object name, Object content) {
		item(name, content, pills.isEmpty());
		return this;
	}

	@Override
	public Pills ajaxItem(Object name, Object location) {
		ajaxItem(name, location, pills.isEmpty());
		return this;
	}
	
	@Override
	public Pills item(Object name, Object content, boolean active) {
		pills.add(new ContentPill(name, pills.size(), content, active));
		return this;
	}	
	
	@Override
	public Pills ajaxItem(Object name, Object location, boolean active) {
		pills.add(new AjaxPill(name, pills.size(), location, active));
		return this;
	}	
	
	private PillAjaxDataToggleScriptRenderer pillAjaxDataToggleScriptRenderer = new PillAjaxDataToggleScriptRenderer();
	private boolean stacked;
	private boolean justified;
	
	@Override
	public Pills stacked() {
		return stacked(true);
	}
	
	@Override
	public Pills stacked(boolean stacked) {
		this.stacked = stacked;
		return this;
	}
	
	@Override
	public Pills justified() {
		return justified(true);
	}
	
	@Override
	public Pills justified(boolean justified) {
		this.justified = justified;
		return this;
	}
			
	private Map<Bootstrap.DeviceSize, Integer> pillsWidth = new HashMap<>();
	
	@Override
	public Pills pillsWidth(Bootstrap.DeviceSize deviceSize, int width) {
		pillsWidth.put(deviceSize, width);
		return this;
	}
	
	@Override
	protected List<Object> getContent() {
		List<Object> ret = new ArrayList<>();
		
		Tag navUL = factory.tag("ul").addClass("nav").addClass("nav-pills");
		if (stacked) {
			navUL.addClass("nav-stacked");
		}
		
		if (justified) {
			navUL.addClass("nav-justified");
		}
		
		boolean hasAjaxPills = false;
		for (Pill pill: pills) {
			navUL.content(pill.li());
			hasAjaxPills = hasAjaxPills || pill instanceof AjaxPill;
		}
		
		Tag contentDiv = factory.div().addClass("tab-content");
		for (Pill pill: pills) {
			contentDiv.content(pill.div());
		}
		
		if (pillsWidth.isEmpty()) {
			ret.add(navUL);
		} else {
			Tag ulContainer = factory.div(navUL);
			for (Entry<Bootstrap.DeviceSize, Integer> pwe: pillsWidth.entrySet()) {
				ulContainer.bootstrap().grid().col(pwe.getKey(), pwe.getValue());
				contentDiv.bootstrap().grid().col(pwe.getKey(), 12 - pwe.getValue());
			}
			ret.add(ulContainer);
		}
		ret.add(contentDiv);

		if (hasAjaxPills) {
			ret.add(pillAjaxDataToggleScriptRenderer.generate(null));
		}		
		
		return ret;
	};

	@Override
	public boolean isEmpty() {
		return pills.isEmpty();
	}

}

