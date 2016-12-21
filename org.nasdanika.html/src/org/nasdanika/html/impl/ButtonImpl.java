package org.nasdanika.html.impl;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.html.Bootstrap;
import org.nasdanika.html.Button;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;

class ButtonImpl extends UIElementImpl<Button> implements Button {
	
	private boolean isInputGroupButton;
	private Object forEachExpresion;

	ButtonImpl(HTMLFactory factory, boolean isInputGroupButton, Object... content) {
		super(factory, TagName.button);
		addClass("btn");
		for (Object c: content) {
			if (c!=null) {
				this.content.add(c);
			}
		}
		this.isInputGroupButton = isInputGroupButton;
	}
	
	private class DropdownItem implements AutoCloseable {
		
		Object content;

		DropdownItem(Object content) {
			this.content = content;
		}

		@Override
		public void close() throws Exception {
			UIElementImpl.close(content);
		}
		
	}
	
	private class Divider extends DropdownItem {
		
		Divider() {
			super(null);
		}
		
	}

	private class Header extends DropdownItem {
		
		public Header(Object header) {
			super(header);
		}
		
	}

	private class Item extends DropdownItem {
		
		public Item(Object content) {
			super(content);
		}
		
	}
	
	private List<DropdownItem> items = new ArrayList<>();
	
	@Override
	public Button item(Object... item) {
		for (Object o: item) {
			items.add(new Item(o));
		}
		return this;
	}

	@Override
	public Button divider() {
		items.add(new Divider());
		return this;
	}

	@Override
	public Button header(Object header) {
		items.add(new Header(header));
		return this;
	}
		
	private Bootstrap.Style style = Bootstrap.Style.DEFAULT;
	private Bootstrap.Size size = Bootstrap.Size.DEFAULT;
	private boolean split;
	private boolean dropup;
	private boolean active;
	private boolean block;	
	

	@Override
	public Button style(Bootstrap.Style style) {
		if (this.style!=null) {
			removeClass("btn-"+this.style.name().toLowerCase());
		}
		this.style = style;
		if (style!=null) {
			addClass("btn-"+style.name().toLowerCase());
		}
		return this;
	}

	@Override
	public Button size(Bootstrap.Size size) {
		if (this.size!=null && this.size!=Bootstrap.Size.DEFAULT) {
			removeClass("btn-"+this.size.code);
		}
		this.size = size;
		if (size!=null && size!=Bootstrap.Size.DEFAULT) {
			addClass("btn-"+this.size.code);
		}		
		return this;
	}

	@Override
	public Button block(boolean block) {
		if (block) {
			addClass("btn-block");
		} else {
			removeClass("btn-block");
		}
		this.block = block;
		return this;
	}

	@Override
	public Button block() {		
		return block(true);
	}

	@Override
	public Button active(boolean active) {
		if (active) {
			addClass("active");
		} else {
			removeClass("active");
		}
		this.active = active;
		return this;
	}

	@Override
	public Button active() {
		return active(true);
	}

	@Override
	public Button disabled(boolean disabled) {
		attribute("disabled", disabled ? "disabled" : null);
		return this;
	}

	@Override
	public Button disabled() {
		return disabled(true);
	}

	@Override
	public Button split(boolean split) {
		this.split = split;
		return this;
	}

	@Override
	public Button split() {		
		return split(true);
	}
	
	@Override
	public Button dropup() {
		return dropup(true);
	}
	
	@Override
	public Button dropup(boolean dropup) {
		this.dropup = dropup;
		return this;
	}
	
	@Override
	public Button type(Type type) {
		attribute("type", type.name().toLowerCase());
		return this;
	}
	
	private boolean splitAugmented;
	
	@Override
	public String produce(int indent) {
		// Simple button - no items
		if (items.isEmpty()) {
			if (isInputGroupButton) {
				return stringify(factory.span(super.produce(indent)).addClass("input-group-btn"), indent);
			}
			return super.produce(indent);
		}		
		
		Tag wrapperDiv = factory.div();
						
		if (isInputGroupButton) {
			wrapperDiv.addClass("input-group-btn");
		} else {
			wrapperDiv.addClass("btn-group");
		}
		if (dropup) {
			wrapperDiv.addClass("dropup");
		}
		if (!split && !splitAugmented) {
			splitAugmented = true;
			addClass("dropdown-toggle");
			attribute("data-toggle", "dropdown");
			content(factory.span("").addClass("caret"));
		}
		
		wrapperDiv.content(super.produce(indent+1));
		
		if (split) {
			Tag dropDownButton = factory.tag(TagName.button, 
					factory.span("").addClass("caret"),
					factory.span("Toggle Dropdown").addClass("sr-only"))
					.attribute("type", "button")
					.attribute("data-toggle", "dropdown")
					.addClass("btn")
					.addClass("dropdown-toggle");
			buttonClasses(dropDownButton);
			wrapperDiv.content(dropDownButton);
		}
		
		Tag list = factory.tag(TagName.ul).addClass("dropdown-menu").attribute("role", "menu");
		wrapperDiv.content(list);
		
		if (forEachExpresion!=null && forEachExpresion.toString().trim().length()>0) {
			list.knockout().foreach(forEachExpresion);
		}
		
		for (DropdownItem item: items) {
			if (item instanceof Item) {
				list.content(factory.tag(TagName.li, item.content));
			} else if (item instanceof Divider) {
				list.content(factory.tag(TagName.li).addClass("divider"));
			} else {
				list.content(factory.tag(TagName.li, item.content).addClass("dropdown-header"));
			}
		}
		
		return stringify(wrapperDiv, indent);
	}
	
	private void buttonClasses(Tag button) {
		button.addClass("btn-"+style.name().toLowerCase());
		if (size!=Bootstrap.Size.DEFAULT) {
			button.addClass("btn-"+size.code);
		}
		if (block) {
			button.addClass("btn-block");
		}
		
		if (active) {
			button.addClass("active");
		}
	}

	
	@Override
	public void close() throws Exception {
		super.close();
		close(items);
		close(forEachExpresion);
	}
	
	@Override
	public Button content(Object... content) {
		return super.content(content);
	}

	@Override
	public boolean isDropdownEmpty() {
		return items.isEmpty();
	}

	@Override
	public Button koItemForEach(Object forEachExpression) {
		this.forEachExpresion = forEachExpression;
		return this;
	}
}
