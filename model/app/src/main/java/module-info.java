module org.nasdanika.html.model.app {
		
	requires transitive org.nasdanika.html.model.bootstrap;
	requires transitive org.nasdanika.drawio;
	requires org.jsoup;
	
	exports org.nasdanika.html.model.app;
	exports org.nasdanika.html.model.app.impl;
	exports org.nasdanika.html.model.app.util;
	
}
