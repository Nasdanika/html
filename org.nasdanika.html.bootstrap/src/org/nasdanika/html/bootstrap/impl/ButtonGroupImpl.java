package org.nasdanika.html.bootstrap.impl;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.html.Bootstrap;
import org.nasdanika.html.Button;
import org.nasdanika.html.ButtonGroup;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag.TagName;

class ButtonGroupImpl extends UIElementImpl<ButtonGroup> implements ButtonGroup {
	
	private org.nasdanika.html.Bootstrap.Size size;
	private boolean justified;

	ButtonGroupImpl(HTMLFactory factory, Button... buttons) {
		super(factory, TagName.div);
		addClass("btn-group");
		attribute("role", "group");
		add(buttons);
	}

	@Override
	public Button button(Object... buttonContent) {
		Button ret = factory.button(buttonContent);
		this.content.add(ret);
		return ret;
	}

	@Override
	public ButtonGroup add(Button... buttons) {
		for (Button button: buttons) {
			if (button!=null) {
				this.content.add(button);
			}
		}
		return this;
	}

	@Override
	public boolean isEmpty() {
		return content.isEmpty();
	}

	@Override
	public ButtonGroup size(org.nasdanika.html.Bootstrap.Size size) {
		if (this.size!=null && !Bootstrap.Size.DEFAULT.equals(this.size)) {
			removeClass("btn-group-"+this.size.code);
		}
		this.size = size;
		if (size!=null && !Bootstrap.Size.DEFAULT.equals(size)) {
			addClass("btn-group-"+size.code);
		}
		return this;
	}

	@Override
	public ButtonGroup vertical() {
		return vertical(true);
	}

	@Override
	public ButtonGroup vertical(boolean vertical) {
		if (vertical) {
			removeClass("btn-group");
			addClass("btn-group-vertical");
		} else {
			removeClass("btn-group-vertical");
			addClass("btn-group");
		}
		return this;
	}

	@Override
	public ButtonGroup justified() {
		return justified(true);
	}

	@Override
	public ButtonGroup justified(boolean justified) {
		this.justified = justified;
		if (justified) {
			addClass("btn-group-justified");
		} else {
			removeClass("btn-group-justified");			
		}
		return this;
	}

	@Override
	protected List<Object> getContent() {
		if (justified) {
			List<Object> ret = new ArrayList<>();
			for (Object c: super.getContent()) {
				if (c instanceof Button && ((Button) c).isDropdownEmpty()) {
					ret.add(factory.div(c).addClass("btn-group"));
				} else {
					ret.add(c);
				}
			}
			return ret;
		}
		return super.getContent();
	}

}
