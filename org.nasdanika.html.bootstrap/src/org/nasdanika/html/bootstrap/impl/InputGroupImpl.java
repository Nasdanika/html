package org.nasdanika.html.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.nasdanika.html.Bootstrap;
import org.nasdanika.html.Bootstrap.Glyphicon;
import org.nasdanika.html.Button;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLFactory.Placement;
import org.nasdanika.html.InputBase;
import org.nasdanika.html.InputGroup;
import org.nasdanika.html.Tag.TagName;

class InputGroupImpl extends UIElementImpl<InputGroupImpl> implements InputGroup<InputGroupImpl> {

	private Object leftAddOn;
	private Object rightAddOn;
	private Object control;
	private StringBuilder initScript = new StringBuilder();

	InputGroupImpl(HTMLFactory factory, Object control) {
		super(factory, TagName.div);
		this.control = control;
		if (control instanceof InputBase) {
			((InputBase<?>) control).addClass("form-control");
		}		
		addClass("input-group");
	}
	
	@Override
	public InputGroupImpl size(Bootstrap.Size size) {
		addClass("input-group-"+size.code);
		return this;
	}

	@Override
	public InputGroupImpl leftAddOn(Object... addOn) {
		if (leftAddOn!=null) {
			throw new IllegalStateException("Left add-on has already been set");
		}
		this.leftAddOn = new FragmentImpl(factory, addOn);
		return this;
	}

	@Override
	public Button leftButton(Object... content) {
		if (leftAddOn!=null) {
			throw new IllegalStateException("Left add-on has already been set");
		}
		this.leftAddOn = new ButtonImpl(factory, true, content);
		return (Button) leftAddOn;
	}

	@Override
	public InputGroupImpl rightAddOn(Object... addOn) {
		if (rightAddOn!=null) {
			throw new IllegalStateException("Right add-on has already been set");
		}
		this.rightAddOn = new FragmentImpl(factory, addOn);
		return this;
	}

	@Override
	public Button rightButton(Object... content) {
		if (rightAddOn!=null) {
			throw new IllegalStateException("Right add-on has already been set");
		}
		this.rightAddOn = new ButtonImpl(factory, true, content);
		return (Button) rightAddOn;
	}
	
	@Override
	protected List<Object> getContent() {
		List<Object> ret = new ArrayList<>();
		if (leftAddOn instanceof Button) {
			ret.add(leftAddOn);
		} else if (leftAddOn!=null) {
			ret.add(factory.span(leftAddOn).addClass("input-group-addon"));
		}
		
		ret.add(control);

		if (rightAddOn instanceof Button) {
			ret.add(rightAddOn);
		} else if (rightAddOn!=null) {
			ret.add(factory.span(rightAddOn).addClass("input-group-addon"));
		} 	
		return ret;		
	}
	
	@Override
	public String produce(int indent) {
		if (initScript.length()==0) {
			return super.produce(indent);
		}
		return super.produce(indent) + stringify(factory.tag(TagName.script, initScript), indent);
	}

	@Override
	public void close() throws Exception {
		super.close();
		close(control);
		close(leftAddOn);
		close(rightAddOn);	
	}

	@Override
	public Button leftPopoverHelpButton(Placement placement, String title, String body, Map<String, Object> options) {
		Button ret = leftButton(factory.glyphicon(Glyphicon.question_sign)).id(factory.nextId());
		factory.popover(ret, placement, title, body);
		String opts = options==null ? "" : new JSONObject(options).toString();		
		initScript.append("$(\"#"+ret.getId()+"\").popover("+opts+");");
		return ret;
	}

	@Override
	public Button rightPopoverHelpButton(Placement placement, String title, String body, Map<String, Object> options) {
		Button ret = rightButton(factory.glyphicon(Glyphicon.question_sign)).id(factory.nextId());
		factory.popover(ret, placement, title, body);
		String opts = options==null ? "" : new JSONObject(options).toString();		
		initScript.append("$(\"#"+ret.getId()+"\").popover("+opts+");");
		return ret;
	}
}
