package org.nasdanika.html.impl;

import java.util.List;

interface RouterApplicationConfig {

	String getTitle();

	List<String> getScripts();
	
	List<String> getStylesheets();

	String getHead();

	String getInitialRoute();

	String getBody();

}
