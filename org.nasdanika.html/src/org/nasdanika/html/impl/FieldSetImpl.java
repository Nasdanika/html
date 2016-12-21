package org.nasdanika.html.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.nasdanika.html.Button;
import org.nasdanika.html.FieldSet;
import org.nasdanika.html.FormFragment;
import org.nasdanika.html.FormGroup;
import org.nasdanika.html.FormInputGroup;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.InputGroup;
import org.nasdanika.html.Knockout;
import org.nasdanika.html.KnockoutFilter;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;
import org.nasdanika.html.UIElement;
import org.nasdanika.html.KnockoutBindingsSource.Binding;

class FieldSetImpl extends UIElementImpl<FieldSet> implements FieldSet {
	
	private FieldContainerImpl<FieldSet> container;

	FieldSetImpl(HTMLFactory factory, FormImpl form) {
		super(factory, "fieldset");
		container = new FieldContainerImpl<FieldSet>(factory, this, form);	
		this.content.add(container);
	}

	@Override
	public FieldSet content(Object... content) {
		return container.content(content);
	}
	
	@Override
	public Tag legend(Object... content) {
		Tag legend = factory.tag(TagName.legend, content);
		content(legend);
		return legend;
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
	public FieldSet checkbox(Object label, Object checkboxControl, boolean inline) {
		return container.checkbox(label, checkboxControl, inline);
	}

	@Override
	public FieldSet radio(Object label,	Object radioControl, boolean inline) {
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
	public FieldSet disabled(boolean disabled) {
		attribute("disabled", disabled ? "disabled" : null);	
		return this;
	}

	@Override
	public FieldSet disabled() {
		return disabled(true);
	}

	@Override
	public FormInputGroup formInputGroup(Object label, Object controlId, Object control, Object helpText) {
		return container.formInputGroup(label, controlId, control, helpText);
	}

	@Override
	public FormInputGroup formInputGroup(Object label, UIElement<?> control, Object helpText) {
		return formInputGroup(label, UIElementImpl.autoId(factory, control), control, helpText);
	}

	@Override
	public void close() throws Exception {
		super.close();
		container.close();		
	}
	
	@Override
	public Knockout<FieldSet> knockout() {		
		return new KnockoutFilter<FieldSet>(super.knockout()) {
			
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
	

}
