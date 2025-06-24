package org.nasdanika.html;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

import reactor.core.publisher.Mono;

public class HTMLElementFilter<T extends HTMLElement<T>> implements HTMLElement<T> {

	private T target;

	public HTMLElementFilter(T target) {
		this.target = target;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T on(Event event, Object handler) {
		target.on(event, handler);
		return (T) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T on(String event, Object handler) {
		target.on(event, handler);
		return (T) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T on(Event event, Reader handler) throws IOException {
		target.on(event, handler);
		return (T) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T on(String event, Reader handler) throws IOException {
		target.on(event, handler);
		return (T) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T on(Event event, InputStream handler) throws IOException {
		target.on(event, handler);
		return (T) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T on(String event, InputStream handler) throws IOException {
		target.on(event, handler);
		return (T) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T id(Object id) {
		target.id(id);
		return (T) this;
	}

	@Override
	public Object getId() {
		return target.getId();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T attribute(String name, Object value) {
		target.attribute(name, value);
		return (T) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T attribute(String name, Object value, boolean condition) {
		target.attribute(name, value, condition);
		return (T) this;
	}

	@Override
	public String getAttribute(String name) {
		return target.getAttribute(name);
	}

	@Override
	@SuppressWarnings("unchecked")
	public T style(String name, Object value) {
		target.style(name, value);
		return (T) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T addClass(Object... clazz) {
		target.addClass(clazz);
		return (T) this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T addClassConditional(boolean condition, Object... clazz) {
		target.addClass(clazz);
		return (T) this;		
	}
	
	@Override
	public String toString() {
		return target.toString();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T comment(String comment) {
		target.comment(comment);
		return (T) this;		
	}

	@Override
	public Object produce(int indent) {
		return target.produce(indent);
	}
	
	@Override
	public Mono<Object> produceAsync(int indent) {
		return target.produceAsync(indent);
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
	public Object getData(Object key) {
		return target.getData(key);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T setData(Object data) {
		target.setData(data);
		return (T) this;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public T setData(Object key, Object data) {
		target.setData(key, data);
		return (T) this;
	}
	
	@Override
	public List<Object> getContent() {
		return target.getContent();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T removeClass(Object... clazz) {
		target.removeClass(clazz);
		return (T) this;
	}
	
}
