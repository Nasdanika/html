package org.nasdanika.html.alpinejs.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.alpinejs.AlpineJs;
import org.nasdanika.html.alpinejs.AlpineJsFactory;

class AlpineJsImpl<T extends HTMLElement<?>> implements AlpineJs<T> {

	private static final String X_TRANSITION_ATTRIBUTE = "x-transition";

	private T htmlElement;
	
	private AlpineJsFactory factory;

	AlpineJsImpl(AlpineJsFactory factory, T htmlElement) {
		this.factory = factory;
		this.htmlElement = htmlElement;
	}
	
	@Override
	public T toHTMLElement() {
		return htmlElement;
	}

	@Override
	public AlpineJs<T> data(Object data) {
		htmlElement.attribute("x-data", data);
		return this;
	}

	@Override
	public JSONObject data() {
		JSONObject data = new JSONObject();
		data(data);
		return data;
	}

	@Override
	public AlpineJs<T> text(Object expression) {
		htmlElement.attribute("x-text", expression);
		return this;
	}

	@Override
	public AlpineJs<T> html(Object expression) {
		htmlElement.attribute("x-html", expression);
		return this;
	}

	@Override
	public AlpineJs<T> init(Object expression) {
		htmlElement.attribute("x-init", expression);
		return this;
	}

	@Override
	public AlpineJs<T> show(Object expression) {
		htmlElement.attribute("x-show", expression);
		return this;
	}

	@Override
	public AlpineJs<T> bind(String attribute, Object expression) {
		htmlElement.attribute("x-bind:" + attribute, expression);
		return this;
	}

	@Override
	public AlpineJs<T> on(String event, Object expression) {
		htmlElement.attribute("x-on:" + event, expression);
		return this;
	}

	@Override
	public AlpineJs<T> model(Object dataField) {
		htmlElement.attribute("x-model", dataField);
		return this;
	}

	@Override
	public AlpineJs<T> modelable(Object dataField) {
		htmlElement.attribute("x-modelable", dataField);
		return this;
	}

	@Override
	public AlpineJs<T> _for(Object expression) {
		htmlElement.attribute("x-for", expression);
		return this;
	}

	@Override
	public AlpineJs<T> effect(Object expression) {
		htmlElement.attribute("x-effect", expression);
		return this;
	}

	@Override
	public AlpineJs<T> ignore() {
		htmlElement.attribute("x-ignore", true);
		return this;
	}

	@Override
	public AlpineJs<T> ref(Object name) {
		htmlElement.attribute("x-ref", name);
		return this;
	}

	@Override
	public AlpineJs<T> cloak() {
		htmlElement.attribute("x-cloak", true);
		return this;
	}

	@Override
	public AlpineJs<T> teleport(Object selector) {
		htmlElement.attribute("x-teleport", selector);
		return this;
	}

	@Override
	public AlpineJs<T> _if(Object expression) {
		htmlElement.attribute("x-if", expression);
		return this;
	}

	@Override
	public AlpineJs<T> id(Object ids) {
		htmlElement.attribute("x-text", ids);
		return this;
	}

	@Override
	public JSONArray id() {
		JSONArray ids = new JSONArray();
		id(ids);
		return ids;
	}

	@Override
	public AlpineJs<T> transition() {
		htmlElement.attribute(X_TRANSITION_ATTRIBUTE, true);
		return this;
	}

	@Override
	public AlpineJs<T> transition(String modifier) {
		htmlElement.attribute(X_TRANSITION_ATTRIBUTE + modifier, true);
		return this;
	}

	@Override
	public AlpineJs<T> transition(String modifier, Object expression) {
		htmlElement.attribute(X_TRANSITION_ATTRIBUTE + modifier, expression);
		return this;
	}
	
}
