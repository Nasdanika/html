package org.nasdanika.html.impl;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.html.Button;
import org.nasdanika.html.FieldContainer;
import org.nasdanika.html.FieldSet;
import org.nasdanika.html.FormFragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Producer;

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
	public Button button(Object... content) {
		Button ret = new ButtonImpl(factory, content);
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
	public String produce(int indent) {
		StringBuilder sb = new StringBuilder();
		for (Object o: content) {
			sb.append(stringify(o, indent));
		}
		return sb.toString();
	}
		
	protected String stringify(Object content, int indent) {
		return HTMLElementImpl.stringify(content, indent);
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
	
}
