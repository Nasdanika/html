package org.nasdanika.html.knockout.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.knockout.Knockout;
import org.nasdanika.html.knockout.KnockoutBindingsSource;
import org.nasdanika.html.knockout.KnockoutFactory;

class KnockoutImpl<T extends HTMLElement<?>> extends KnockoutControlFlowImpl<Knockout<T>> implements Knockout<T> {
	
	private static final String KNOCKOUT_DATA_BIND_ENTRIES = "knockout-data-bind-entries";
	private static final String KNOCKOUT_INITIAL_VALUE_ENTRIES = "knockout-initial-value-entries";

	private T htmlElement;
	
	private Map<String, Object> koDataBindEntries;
	
	private Map<String, Object> koInitialValueEntries;
	private KnockoutFactory factory;

	@SuppressWarnings("unchecked")
	KnockoutImpl(KnockoutFactory factory, T htmlElement) {
		this.factory = factory;
		this.htmlElement = htmlElement;
		koDataBindEntries = (Map<String, Object>) htmlElement.getData(KNOCKOUT_DATA_BIND_ENTRIES);
		if (koDataBindEntries == null) {
			koDataBindEntries = new LinkedHashMap<>();
			htmlElement.setData(KNOCKOUT_DATA_BIND_ENTRIES, koDataBindEntries);
		}
		
		koInitialValueEntries = (Map<String, Object>) htmlElement.getData(KNOCKOUT_INITIAL_VALUE_ENTRIES);
		if (koInitialValueEntries == null) {
			koInitialValueEntries = new LinkedHashMap<>();
			htmlElement.setData(KNOCKOUT_INITIAL_VALUE_ENTRIES, koInitialValueEntries);
		}	
	}
	
	@Override
	public T toHTMLElement() {
		return htmlElement;
	}

	@Override
	public Knockout<T> visible(Object expression, Object initialValue) {
		return bind(KnockoutBindingInfo.VISIBLE.name, expression, initialValue);
	}
	
	@Override
	public Knockout<T> visible(Object expression) {
		return visible(expression, null);
	}

	@Override
	public Knockout<T> text(Object expression, Object initialValue) {
		return bind(KnockoutBindingInfo.TEXT.name, expression, initialValue);
	}

	@Override
	public Knockout<T> text(Object expression) {
		return text(expression, null);
	}

	@Override
	public Knockout<T> html(Object expression, Object initialValue) {
		return bind(KnockoutBindingInfo.HTML.name, expression, initialValue);
	}
	
	@Override
	public Knockout<T> html(Object expression) {
		return html(expression, null);
	}

	@Override
	public Knockout<T> css(Object expression) {
		return bind(KnockoutBindingInfo.CSS.name, expression, null);
	}

	@Override
	public Knockout<T> style(Object expression) {
		return bind(KnockoutBindingInfo.STYLE.name, expression, null);
	}

	@Override
	public Knockout<T> attr(Object expression) {
		return bind(KnockoutBindingInfo.ATTR.name, expression, null);
	}

	@Override
	public Knockout<T> click(Object expression) {
		return bind(KnockoutBindingInfo.CLICK.name, expression, null);
	}

	@Override
	public Knockout<T> event(Object expression) {
		return bind(KnockoutBindingInfo.EVENT.name, expression, null);
	}

	@Override
	public Knockout<T> submit(Object expression) {
		return bind(KnockoutBindingInfo.SUBMIT.name, expression, null);
	}

	@Override
	public Knockout<T> enable(Object expression, Object initialValue) {
		return bind(KnockoutBindingInfo.ENABLE.name, expression, initialValue);
	}
	
	@Override
	public Knockout<T> enable(Object expression) {
		return enable(expression, null);
	}

	@Override
	public Knockout<T> disable(Object expression, Object initialValue) {
		return bind(KnockoutBindingInfo.DISABLE.name, expression, initialValue);
	}
	
	@Override
	public Knockout<T> disable(Object expression) {
		return disable(expression, null);
	}

	@Override
	public Knockout<T> value(Object expression, Object initialValue) {
		return bind(KnockoutBindingInfo.VALUE.name, expression, initialValue);
	}
	
	@Override
	public Knockout<T> value(Object expression) {
		return value(expression, null);
	}

	@Override
	public Knockout<T> textInput(Object expression, Object initialValue) {
		return bind(KnockoutBindingInfo.TEXT_INPUT.name, expression, initialValue);
	}
	
	@Override
	public Knockout<T> textInput(Object expression) {
		return textInput(expression, null);
	}

	@Override
	public Knockout<T> hasFocus(Object expression, Object initialValue) {
		return bind(KnockoutBindingInfo.HAS_FOCUS.name, expression, initialValue);
	}
	
	@Override
	public Knockout<T> hasFocus(Object expression) {
		return hasFocus(expression, null);
	}

	@Override
	public Knockout<T> checked(Object expression, Object initialValue) {
		return bind(KnockoutBindingInfo.CHECKED.name, expression, initialValue);
	}
	
	@Override
	public Knockout<T> checked(Object expression) {
		return checked(expression, null);
	}

	@Override
	public Knockout<T> options(Object expression, Object initialValue) {
		return bind(KnockoutBindingInfo.OPTIONS.name, expression, initialValue);
	}
	
	@Override
	public Knockout<T> options(Object expression) {
		return options(expression, null);
	}

	@Override
	public Knockout<T> selectedOptions(Object expression, Object initialValue) {
		return bind(KnockoutBindingInfo.SELECTED_OPTIONS.name, expression, initialValue);
	}
	
	@Override
	public Knockout<T> selectedOptions(Object expression) {
		return selectedOptions(expression, null);
	}

	@Override
	public Knockout<T> uniqueName(Object expression) {
		return bind(KnockoutBindingInfo.UNIQUE_NAME.name, expression, null);
	}

	@Override
	public Knockout<T> template(Object expression) {
		return bind(KnockoutBindingInfo.TEMPLATE.name, expression, null);
	}

	@Override
	public Knockout<T> bind(String binding, Object expression, Object initialValue) {
		if (expression==null) {
			koDataBindEntries.remove(binding);
			koInitialValueEntries.remove(binding);
		} else {
			koDataBindEntries.put(binding, expression);
			if (initialValue!=null) {
				koInitialValueEntries.put(binding, initialValue);
			}
		}
		return this;
	}
	
	@Override
	public Collection<Binding> getAllBindings() {
		Map<String, Binding> collector = new LinkedHashMap<>();
		boolean isNewScope = false;
		for (String binding: koDataBindEntries.keySet()) {
			if (KnockoutBindingInfo.isObservable(binding)) {
				String fieldName = String.valueOf(koDataBindEntries.get(binding)).trim();
				if (KnockoutVirtualElementImpl.isJavaIdentifierPath(fieldName)) {
					collector.put(fieldName, new KnockoutBindingImpl(fieldName, KnockoutBindingInfo.isArray(binding), koInitialValueEntries.get(binding)));
				}
			}
			if (KnockoutBindingInfo.isNewScope(binding)) {
				isNewScope = true;
			}
		}
		
		if (!isNewScope) {
			for (Object c: htmlElement.getContent()) {
				if (c instanceof HTMLElement) {
					for (Binding b: factory.from((HTMLElement<?>) c).getAllBindings()) {
						Binding eb = collector.get(b.getName());
						if (eb==null) {
							collector.put(b.getName(), b);
						} else if (eb.getInitialValue()==null && b.getInitialValue()!=null) {
							((KnockoutBindingImpl) eb).setInitialValue(b.getInitialValue());
						}
					}
				} else if (c instanceof KnockoutBindingsSource) {
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
}
