package org.nasdanika.html;

public enum InputType { 
	button,
	checkbox,
	color,
	date, 
	datetime_local, 
	email,
	file,
	hidden,
	image,
	month, 
	number, 
	password,
	radio,
	range, 
	reset,
	search,
	submit,
	tel,
	text,
	time, 
	url,
	week;

	public String code() {
		return name().replace('_', '-');
	}
	
	/**
	 * Creates input with {@link HTMLFactory}.INSTANCE.
	 * @return
	 */
	public Input create() {
		return HTMLFactory.INSTANCE.input(this);
	}
}