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
	week,
	/**
	 * Pseudo input type, not to be used in input tag, but to indicate that the control shall be a select. 
	 */
	select() {
		
		@Override
		public InputBase<?> create(HTMLFactory htmlFactory) {
			return htmlFactory.select();
		}
	},
	/**
	 * Pseudo input type, not to be used in input tag, but to indicate that the control shall be a text area. 
	 */
	text_area() {
		
		@Override
		public InputBase<?> create(HTMLFactory htmlFactory) {
			return htmlFactory.textArea();
		}
		
	};

	public String code() {
		return name().replace('_', '-');
	}
	
	/**
	 * Creates input with {@link HTMLFactory}.INSTANCE.
	 * @return
	 */
	public InputBase<?> create(HTMLFactory htmlFactory) {
		return htmlFactory.input(this);
	}
	
	/**
	 * Creates input with {@link HTMLFactory}.INSTANCE.
	 * @return
	 */
	public InputBase<?> create() {
		return create(HTMLFactory.INSTANCE);
	}
}