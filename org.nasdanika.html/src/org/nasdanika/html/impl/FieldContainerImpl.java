package org.nasdanika.html.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.nasdanika.html.Button;
import org.nasdanika.html.FieldContainer;
import org.nasdanika.html.FieldSet;
import org.nasdanika.html.FormFragment;
import org.nasdanika.html.FormGroup;
import org.nasdanika.html.FormInputGroup;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.InputGroup;
import org.nasdanika.html.KnockoutBindingsSource;
import org.nasdanika.html.KnockoutBindingsSource.Binding;
import org.nasdanika.html.Producer;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;
import org.nasdanika.html.UIElement;

class FieldContainerImpl<T extends FieldContainer<T>> implements FieldContainer<T>, Producer {
	
	private FormImpl form;
	private T master;
	private HTMLFactory factory;
	
	@SuppressWarnings("unchecked")
	FieldContainerImpl(HTMLFactory factory, T master, FormImpl form) {
		this.factory = factory;
		this.form = form;
		this.master = master==null ? (T) this : master;
	}
	
	private List<Object> content = new ArrayList<>();

	@Override
	public T content(Object... content) {
		for (Object c: content) {
			this.content.add(c);
		}
		return master;
	}
	
	@Override
	public FormGroup<?> formGroup(Object label, Object controlId, Object control, Object helpText) {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		FormGroup<?> ret = new FormGroupImpl(factory, form, label, controlId, control, helpText);
		content.add(ret);
		return ret;
	}

	@Override
	public FormGroup<?> formGroup(Object label, UIElement<?> control, Object helpText) {
		return formGroup(label, UIElementImpl.autoId(factory, control), control, helpText) ;
	}
	
	private interface KnockoutBindingsSourceProducer extends KnockoutBindingsSource, Producer {

	}	

	@Override
	public T checkbox(final Object label, final Object checkboxControl, final boolean inline) {
		content.add(new KnockoutBindingsSourceProducer() {
			
			@Override
			public Object produce(int indent) {
				Tag tag;
				if (inline) {
					tag = factory.tag(TagName.label, checkboxControl, label).addClass("checkbox-inline");
				} else {
					tag = factory.div(factory.tag(TagName.label, checkboxControl, label)).addClass("checkbox");
				}
				if (!form.horizontal) {
					return tag.produce(indent);				
				}
				
				UIElement<?> controlDiv = form.factory.div(tag);
				controlDiv.addClass("col-"+form.deviceSize.code+"-"+(12-form.labelWidth));
				controlDiv.addClass("col-"+form.deviceSize.code+"-offset-"+form.labelWidth);
				return controlDiv.produce(indent);
			}
						
			/**
			 * Fall-back to mitigate misses during refactoring.
			 */
			@Override
			public String toString() {
				return stringify(produce(0), 0);
			}
			

			@Override
			public Collection<Binding> getAllBindings() {
				if (checkboxControl instanceof UIElement<?>) {
					return ((UIElement<?>) checkboxControl).knockout().getAllBindings();
				}
				if (checkboxControl instanceof KnockoutBindingsSource) {
					return ((KnockoutBindingsSource) checkboxControl).getAllBindings();
				}
				return Collections.emptyList();
			}
			
			
		});
				
		return master;
	}

	@Override
	public T radio(final Object label, final Object radioControl, final boolean inline) {
		content.add(new KnockoutBindingsSourceProducer() {
			
			@Override
			public Object produce(int indent) {
				Tag tag;
				if (inline) {
					tag = factory.tag(TagName.label, radioControl, label).addClass("radio-inline");
				} else {
					tag = factory.div(factory.tag(TagName.label, radioControl, label)).addClass("radio");
				}
				if (!form.horizontal) {
					return tag.produce(indent);
				}
				
				UIElement<?> controlDiv = form.factory.div(tag);
				controlDiv.addClass("col-"+form.deviceSize.code+"-"+(12-form.labelWidth));
				controlDiv.addClass("col-"+form.deviceSize.code+"-offset-"+form.labelWidth);
				return controlDiv.produce(indent);
			}
						
			/**
			 * Fall-back to mitigate misses during refactoring.
			 */
			@Override
			public String toString() {
				return stringify(produce(0), 0);
			}

			@Override
			public Collection<Binding> getAllBindings() {
				if (radioControl instanceof UIElement<?>) {
					return ((UIElement<?>) radioControl).knockout().getAllBindings();
				}
				if (radioControl instanceof KnockoutBindingsSource) {
					return ((KnockoutBindingsSource) radioControl).getAllBindings();
				}
				return Collections.emptyList();
			}
			
		});
		
		return master;
	}
	
	@Override
	public Button button(Object... content) {
		Button ret = new ButtonImpl(factory, false, content);
		this.content.add(ret);
		return ret;
	}

	@Override
	public FieldSet fieldset() {
		FieldSet ret = new FieldSetImpl(factory, form);
		content.add(ret);
		return ret;
	}

	@Override
	public FormFragment formFragment() {		
		class FormFragmentImpl extends FieldContainerImpl<FormFragment> implements FormFragment {

			FormFragmentImpl(FormFragment master, FormImpl form) {
				super(factory, master, form);
			}
			
		}
		return new FormFragmentImpl(null, form);
	}

	@Override
	public InputGroup<?> inputGroup(Object control) {
		InputGroup<?> ret = form.factory.inputGroup(control);
		content.add(ret);
		return ret;
	}
	
	@Override
	public FormInputGroup formInputGroup(Object label, Object controlId, Object control, Object helpText) {
		FormInputGroupImpl ret = new FormInputGroupImpl(factory, form, label, controlId, control, helpText);
		content.add(ret);
		return ret;
	}	

	@Override
	public FormInputGroup formInputGroup(Object label, UIElement<?> control, Object helpText) {
		return formInputGroup(label, UIElementImpl.autoId(factory, control), control, helpText);
	}
	
	@Override
	public String produce(int indent) {
		StringBuilder sb = new StringBuilder();
		for (Object o: content) {
			sb.append(stringify(o, indent));
		}
		return sb.toString();
	}
	
	

	@Override
	public void close() throws Exception {
		for (Object o: content) {
			if (o instanceof AutoCloseable) {
				((AutoCloseable) o).close();
			}
		}
	}
	
	protected String stringify(Object content, int indent) {
		return UIElementImpl.stringify(content, indent, factory);
	}
	
	/**
	 * Fall-back to mitigate misses during refactoring.
	 */
	@Override
	public String toString() {
		return produce(0);
	}

	@Override
	public boolean isEmpty() {
		return content.isEmpty();
	}
	
	Collection<Binding> getKnockoutBindings() {
		Map<String, Binding> collector = new LinkedHashMap<>();
		for (Object c: content) {
			if (c instanceof UIElement) {
				for (Binding b: ((UIElement<?>) c).knockout().getAllBindings()) {
					Binding eb = collector.get(b.getName());
					if (eb==null) {
						collector.put(b.getName(), b);
					} else if (eb.getInitialValue()==null && b.getInitialValue()!=null) {
						((KnockoutBindingImpl) eb).setInitialValue(b.getInitialValue());
					}
				}
			}
			
			if (c instanceof KnockoutBindingsSource) {
				for (Binding b: ((KnockoutBindingsSource) c).getAllBindings()) {
					Binding eb = collector.get(b.getName());
					if (eb==null) {
						collector.put(b.getName(), b);
					} else if (eb.getInitialValue()==null && b.getInitialValue()!=null) {
						((KnockoutBindingImpl) eb).setInitialValue(b.getInitialValue());
					}
				}				
			}
		}
		return Collections.unmodifiableCollection(collector.values());
	}	
	
}
