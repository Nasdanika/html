package org.nasdanika.html;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class HTMLElementFilter<T extends HTMLElement<T>> implements HTMLElement<T> {

	public void close() throws Exception {
		target.close();
	}

	public T on(Event event, Object handler) {
		return target.on(event, handler);
	}

	public T on(String event, Object handler) {
		return target.on(event, handler);
	}

	public T on(Event event, Reader handler) throws IOException {
		return target.on(event, handler);
	}

	public T on(String event, Reader handler) throws IOException {
		return target.on(event, handler);
	}

	public T on(Event event, InputStream handler) throws IOException {
		return target.on(event, handler);
	}

	public T on(String event, InputStream handler) throws IOException {
		return target.on(event, handler);
	}

	public T id(Object id) {
		return target.id(id);
	}

	public Object getId() {
		return target.getId();
	}

	public T attribute(String name, Object value) {
		return target.attribute(name, value);
	}

	public T attribute(String name, Object value, boolean condition) {
		return target.attribute(name, value, condition);
	}

	public String getAttribute(String name) {
		return target.getAttribute(name);
	}

	public T style(String name, Object value) {
		return target.style(name, value);
	}

	public T addClass(Object... clazz) {
		return target.addClass(clazz);
	}

	public T addClassConditional(boolean condition, Object... clazz) {
		return target.addClass(clazz);
	}

	private T target;

	public HTMLElementFilter(T target) {
		this.target = target;
	}
	
	@Override
	public String toString() {
		return target.toString();
	}

	@Override
	public T comment(String comment) {
		return target.comment(comment);
	}

	@Override
	public Object produce(int indent) {
		return target.produce(indent);
	}

	@Override
	public Style<T> style() {
		return target.style();
	}

	@Override
	public HTMLFactory getFactory() {
		return target.getFactory();
	}
	
	@Override
	public String jQuery(String expr) {
		return target.jQuery(expr);
	}
	
	@Override
	public Object getData() {
		return target.getData();
	}
	
	@Override
	public Object getData(String key) {
		return target.getData(key);
	}
	
	@Override
	public T setData(Object data) {
		return target.setData(data);
	}
	
	@Override
	public T setData(String key, Object data) {
		return target.setData(key, data);
	}
	
}
