package org.nasdanika.html;

public interface Require extends AutoCloseable {

	Require module(Object... module);
	
}
