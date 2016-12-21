package org.nasdanika.html;

public interface Function extends AutoCloseable {
	
	Function parameter(Object... param);

	Function code(Object... code);
	
	Function bind(Object... bind);

}
