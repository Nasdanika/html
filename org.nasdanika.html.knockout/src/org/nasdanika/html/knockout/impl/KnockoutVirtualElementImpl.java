package org.nasdanika.html.knockout.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.impl.HTMLElementImpl;
import org.nasdanika.html.knockout.KnockoutBindingsSource;
import org.nasdanika.html.knockout.KnockoutFactory;
import org.nasdanika.html.knockout.KnockoutVirtualElement;

class KnockoutVirtualElementImpl extends KnockoutControlFlowImpl<KnockoutVirtualElement> implements KnockoutVirtualElement {
		
	private List<Object> content = new ArrayList<>();
	private KnockoutFactory factory;
	private String binding;
	private Object expression;
	private Object initialValue;

	KnockoutVirtualElementImpl(KnockoutFactory factory, Object... content) {
		this.factory = factory;
		for (Object c: content) {
			this.content.add(c);
		}
	}
	
	@Override
	public boolean isEmpty() {
		return content.isEmpty();
	}
		
	public List<Object> getContent() {
		return content;
	}

	@Override
	public KnockoutVirtualElement bind(String binding, Object expression, Object initialValue) {
		this.binding = binding;
		this.expression = expression;
		this.initialValue = initialValue;
		return this;
	}

	@Override
	public KnockoutVirtualElement content(Object... content) {
		for (Object c: content) {
			this.content.add(c);
		}
		return this;
	}
		
	@Override
	public String produce(int indent) {		
		List<Object> theContent = getContent();
		
		StringBuilder sb = HTMLElementImpl.indent(indent).append("<!-- ko ")
				.append(binding)
				.append(": ")
				.append(HTMLElementImpl.stringify(expression))
				.append(" -->")
				.append(System.lineSeparator());
		
		for (Object c: theContent) {
			sb.append(HTMLElementImpl.stringify(c, indent+1));
		}
		return HTMLElementImpl.indent(sb, indent).append("<!-- /ko -->").toString();
	}
	
	/**
	 * Fallback for situations with missed refactorings of append().
	 */
	@Override
	public String toString() {
		return produce(0);
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
	
	static boolean isJavaIdentifier(String s) {
	    if (s.length() == 0 || !Character.isJavaIdentifierStart(s.charAt(0))) {
	        return false;
	    }
	    for (int i=1; i<s.length(); i++) {
	        if (s.charAt(i) != '.' && !Character.isJavaIdentifierPart(s.charAt(i))) {
	            return false;
	        }
	    }
	    return true;
	}
	
	static boolean isJavaIdentifierPath(String s) {
		String[] pea = s.split("\\.");
		for (String pe: pea) {
			if (!isJavaIdentifier(pe)) {
				return false;
			}
		}
		return true;
	}
		
	@Override
	public Collection<Binding> getAllBindings() {
		Map<String, Binding> collector = new LinkedHashMap<>();
		if (KnockoutBindingInfo.isObservable(binding)) {
			String fieldName = String.valueOf(expression).trim();
			if (isJavaIdentifierPath(fieldName)) {
				collector.put(fieldName, new KnockoutBindingImpl(fieldName, KnockoutBindingInfo.isArray(binding), initialValue));
			}
		}
		if (!KnockoutBindingInfo.isNewScope(binding)) {
			for (Object c: content) {
				if (c instanceof HTMLElement) {
					for (Binding b: factory.from(((HTMLElement<?>) c)).getAllBindings()) {
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

}
