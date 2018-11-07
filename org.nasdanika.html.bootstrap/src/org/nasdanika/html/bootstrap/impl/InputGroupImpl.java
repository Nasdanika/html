package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.InputBase;
import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.InputGroup;

class InputGroupImpl extends BootstrapElementImpl<Tag> implements InputGroup  {

	private Tag root;	
	private Tag prepend;
	private Fragment inputs;
	private Tag append;
	
	public InputGroupImpl(BootstrapFactory factory) {
		super(factory);
		HTMLFactory htmlFactory = getFactory().getHTMLFactory();
		prepend = htmlFactory.nonEmptyDiv().addClass("input-group-prepend");
		inputs = htmlFactory.fragment();
		append = htmlFactory.nonEmptyDiv().addClass("input-group-append");
		root = htmlFactory.div(prepend, inputs, append).addClass("input-group");
	}

	@Override
	public Tag toHTMLElement() {
		return root;
	}

	@Override
	public void close() throws Exception {
		root.close();
	}

	@Override
	public Object produce(int indent) {
		return root.produce(indent);
	}
	
	protected Object wrapContent(Object content) {
		return content instanceof CharSequence ? getFactory().getHTMLFactory().span(content).addClass("input-group-text") : content;
	}

	@Override
	public InputGroup prepend(Object content) {
		prepend.content(wrapContent(content));
		return this;
	}

	@Override
	public InputGroup input(InputBase<?> input) {
		input.addClass("form-control");
		inputs.content(input);
		return this;
	}

	@Override
	public InputGroup append(Object content) {
		append.content(wrapContent(content));
		return this;
	}

	@Override
	public InputGroup large() {
		return large(true);
	}

	@Override
	public InputGroup large(boolean large) {
		root.addClassConditional(large, "input-group-lg");
		return this;
	}

	@Override
	public InputGroup small() {
		return small(true);
	}

	@Override
	public InputGroup small(boolean small) {
		root.addClassConditional(small, "input-group-sm");
		return this;
	}

}
