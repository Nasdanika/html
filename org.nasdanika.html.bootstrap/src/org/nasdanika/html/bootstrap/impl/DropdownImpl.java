package org.nasdanika.html.bootstrap.impl;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.html.Dropdown;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.UIElement;

class DropdownImpl extends UIElementImpl<DropdownImpl> implements Dropdown<DropdownImpl> {
	private static Object DIVIDER = new Object(); // Marker object.
	
	private UIElement<?> toggle;
	private List<Object> items = new ArrayList<>();

	DropdownImpl(HTMLFactory factory, String tagName, UIElement<?> toggle) {
		super(factory, tagName);
		this.toggle = toggle;
		this.toggle.addClass("dropdown-toggle").attribute("data-toggle", "dropdown");
		addClass("dropdown");
	}
	
	private class Header implements AutoCloseable {
		Object header;
		
		Header(Object header) {
			this.header = header;
		}
		
		@Override
		public void close() throws Exception {
			UIElementImpl.close(header);				
		}
	}

	@Override
	public DropdownImpl item(Object... item) {
		items.add(factory.fragment(item));
		return this;
	}

	@Override
	public DropdownImpl divider() {
		items.add(DIVIDER);
		return this;
	}
	
	@Override
	protected List<Object> getContent() {
		List<Object> ret = new ArrayList<>();
		ret.add(toggle);
		Tag ul = factory.tag("ul").addClass("dropdown-menu");
        for (Object item: items) {
        	if (item==DIVIDER) {
        		ul.content(factory.tag("li", "").addClass("divider"));
        	} else if (item instanceof Header) {
        		ul.content(factory.tag("li", ((Header) item).header).addClass("dropdown-header"));
        	} else {
        		ul.content(factory.tag("li", item));
        	}
        }
        ret.add(ul);
		return ret;
	}

	@Override
	public DropdownImpl header(Object header) {
		items.add(new Header(header));
		return this;
	}
	
	@Override
	public void close() throws Exception {
		close(toggle);
		for (Object item: items) {
			close(item);
		}
	}

	@Override
	public boolean isDropdownEmpty() {
		return items.isEmpty();
	}
	
}