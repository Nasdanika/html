package org.nasdanika.html.bootstrap.impl;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.html.Dropdown;
import org.nasdanika.html.Form;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Navbar;
import org.nasdanika.html.Producer;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;

class NavbarImpl extends UIElementImpl<Navbar> implements Navbar {
	
	private Object brand;
	private Object brandRef;

	NavbarImpl(HTMLFactory factory, Object brand, Object brandRef) {
		super(factory, (String) null);
		this.brand = brand;
		this.brandRef = brandRef;
	}
	
	private List<Object> leftItems = new ArrayList<>();
	private List<Object> rightItems = new ArrayList<>();
	
	private class ItemEntry implements Producer {
		Object item;
		boolean active;
		
		ItemEntry(Object item, boolean active) {
			super();
			this.item = item;
			this.active = active;
		}		
		
		@Override
		public Object produce(int indent) {
			Tag li = factory.tag(TagName.li, item);
			if (active) {
				li.addClass("active");
			}
			return li.produce(indent);
		}
		
		/**
		 * Fall-back to mitigate misses during refactoring.
		 */
		@Override
		public String toString() {
			return stringify(produce(0), 0);
		}
	}

	@Override
	public Navbar item(Object item, boolean active, boolean right) {
		(right ? rightItems : leftItems).add(new ItemEntry(item, active));
		return this;
	}
		
	@Override
	public Dropdown<?> dropdown(Object name, boolean right) {
		Dropdown<?> ret = new DropdownImpl(factory, "li", factory.link("#", name, " ", factory.tag("b", "").addClass("caret")));
		(right ? rightItems : leftItems).add(ret);
		return ret;
	}
	
	private NavbarRenderer navbarRenderer = new NavbarRenderer();
	private FormImpl form;
	
	@Override
	public String produce(final int indent) {
		
		final String collapseTargetId = factory.nextId()+"_collapse";

		return renderComment(indent)+navbarRenderer.generate(new NavbarConfig() {
			
			@Override
			public Object getBrand() {
				return brand;
			}
			
			@Override
			public Object getBrandRef() {
				return brandRef==null ? "#" : brandRef;
			}
			
			@Override
			public String getLeftItems() {
				if (leftItems.isEmpty()) {
					return null;					
				}
				StringBuilder ret = new StringBuilder();
				for (Object item: leftItems) {
					ret.append(stringify(item, indent));
				}
				return ret.toString();
			}
			
			@Override
			public String getRightItems() {
				if (rightItems.isEmpty()) {
					return null;					
				}
				StringBuilder ret = new StringBuilder();
				for (Object item: rightItems) {
					ret.append(stringify(item, indent));
				}
				return ret.toString();
			}
			
			@Override
			public String getCollapseTargetId() {
				return collapseTargetId;
			}
			
			@Override
			public String getAttributes() {
				return NavbarImpl.this.attributes("class", "role");
			}

			@Override
			public String getForm() {
				return form==null ? "" : form.toString();
			}
		})+genLoadRemoteContentScript();
	}

	@Override
	public void close() throws Exception {
		super.close();
		for (Object item: leftItems) {
			close(item);
		}
		for (Object item: rightItems) {
			close(item);
		}
		close(form);
		close(brand);
		close(brandRef);
	}

	@Override
	public Form form(boolean right) {
		form = new FormImpl(factory, true, right);
		return form;
	}

}
