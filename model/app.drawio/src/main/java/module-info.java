module org.nasdanika.html.model.app.drawio {
		
	requires transitive org.nasdanika.html.model.app;
	requires transitive org.nasdanika.drawio;
	requires org.jsoup;
	requires org.nasdanika.exec;
	requires org.eclipse.emf.common;
	requires spring.expression;
	
	exports org.nasdanika.html.model.app.drawio;	
}
