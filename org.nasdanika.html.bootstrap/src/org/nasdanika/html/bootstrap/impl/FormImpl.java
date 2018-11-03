package org.nasdanika.html.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.nasdanika.html.Bootstrap;
import org.nasdanika.html.Button;
import org.nasdanika.html.FieldSet;
import org.nasdanika.html.Form;
import org.nasdanika.html.FormFragment;
import org.nasdanika.html.FormGroup;
import org.nasdanika.html.FormInputGroup;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.InputGroup;
import org.nasdanika.html.Knockout;
import org.nasdanika.html.KnockoutFilter;
import org.nasdanika.html.Tag.TagName;
import org.nasdanika.html.UIElement;

class FormImpl extends UIElementImpl<Form> implements Form {
	
	boolean horizontal;
	boolean inline;
	boolean hideInlineLabels;
	Bootstrap.DeviceSize deviceSize;
	int labelWidth;
	private FieldContainerImpl<Form> container;

	FormImpl(HTMLFactory factory, boolean nav, boolean navRight) {
		super(factory, TagName.form);
		if (nav) {
			addClass("navbar-form");
			if (navRight) {
				addClass("navbar-right");
			} else {
				addClass("navbar-left");
			}
		} 
		
		attribute("role", "form");
		
		container = new FieldContainerImpl<Form>(factory, this, this);
		this.content.add(container);
	}
	
	@Override
	public Form horizontal(Bootstrap.DeviceSize deviceSize, int labelWidth) {
		horizontal = true;
		this.deviceSize = deviceSize;
		this.labelWidth = labelWidth;
		addClass("form-horizontal");
		return this;
	}

	@Override
	public Form inline(boolean inline, boolean hideLabels) {
		this.inline = inline;
		this.hideInlineLabels = hideLabels;
		addClass("form-inline");
		return this;
	}

	@Override
	public Form inline() {
		return inline(true, true);
	}

	@Override
	public Form content(Object... content) {
		return container.content(content);
	}

	@Override
	public FormGroup<?> formGroup(Object label, Object controlId, Object control, Object helpText) {
		return container.formGroup(label, controlId, control, helpText);
	}
	
	@Override
	public FormGroup<?> formGroup(Object label, UIElement<?> control, Object helpText) {
		return formGroup(label, UIElementImpl.autoId(factory, control), control, helpText) ;
	}

	@Override
	public Form checkbox(Object label, Object checkboxControl, boolean inline) {
		return container.checkbox(label, checkboxControl, inline);
	}

	@Override
	public Form radio(Object label, Object radioControl, boolean inline) {
		return container.radio(label, radioControl, inline);
	}

	@Override
	public Button button(Object... content) {
		return container.button(content);
	}

	@Override
	public InputGroup<?> inputGroup(Object control) {
		return container.inputGroup(control);
	}

	@Override
	public FieldSet fieldset() {
		return container.fieldset();
	}
	
	@Override
	public FormFragment formFragment() {
		return container.formFragment();
	}

	@Override
	public FormInputGroup formInputGroup(Object label, Object controlId, Object control, Object helpText) {
		return container.formInputGroup(label, controlId, control, helpText);
	}
	
	@Override
	public Knockout<Form> knockout() {		
		return new KnockoutFilter<Form>(super.knockout()) {
			
			@Override
			public Collection<Binding> getAllBindings() {
				Map<String, Binding> collector = new LinkedHashMap<>();
				for (Binding b: super.getAllBindings()) {
					Binding eb = collector.get(b.getName());
					if (eb==null) {
						collector.put(b.getName(), b);
					} else if (eb.getInitialValue()==null && b.getInitialValue()!=null) {
						((KnockoutBindingImpl) eb).setInitialValue(b.getInitialValue());
					}
				}
				for (Binding b: container.getKnockoutBindings()) {
					Binding eb = collector.get(b.getName());
					if (eb==null) {
						collector.put(b.getName(), b);
					} else if (eb.getInitialValue()==null && b.getInitialValue()!=null) {
						((KnockoutBindingImpl) eb).setInitialValue(b.getInitialValue());
					}
				}
				
				return Collections.unmodifiableCollection(collector.values());
			}
			
			@Override
			public String generateObservables(String... excludes) {
				StringBuilder ret = new StringBuilder("// Generated observables").append(System.lineSeparator());
				ObservablesGenerator og = new ObservablesGenerator(excludes);
				for (Binding binding: getAllBindings()) {
					og.addBinding(binding);
				}	
				og.generateObservables(ret);
				return ret.append("// End of generated observables").append(System.lineSeparator()).toString();		
			}
						
		};
	}

	@Override
	public FormInputGroup formInputGroup(Object label, UIElement<?> control, Object helpText) {
		return formInputGroup(label, UIElementImpl.autoId(factory, control), control, helpText);
	}
	
	@Override
	public void close() throws Exception {
		super.close();
		close(container);		
	}

	@Override
	public Form action(Object action) {
		return attribute("action", action);
	}

	@Override
	public Form autocomplete() {
		return autocomplete(true);
	}

	@Override
	public Form autocomplete(boolean autocomplete) {
		return attribute("autocomplete", autocomplete ? "on" : "off");
	}

	@Override
	public Form enctype(EncType enctype) {
		return attribute("enctype", enctype.literal);
	}

	@Override
	public Form acceptCharset(String charset) {
		return attribute("accept-charset", charset);
	}

	@Override
	public Form method(Method method) {
		return attribute("method", method.name());
	}

	@Override
	public Form name(String name) {
		return attribute("name", name);
	}

	@Override
	public Form novalidate() {
		return novalidate(true);
	}

	@Override
	public Form novalidate(boolean novalidate) {
		return attribute("novalidate", novalidate ? "on" : null);
	}

	@Override
	public Form target(String target) {
		return attribute("target", target);
	}
	
	@Override
	public Form ngSubmit(Object handler) {
		return attribute("ng-submit", handler);
	}

}
