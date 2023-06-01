module org.nasdanika.html.ecore.processors {
	
	exports org.nasdanika.html.ecore.processors;
	opens org.nasdanika.html.ecore.processors;
	
	requires transitive org.nasdanika.html.model.app;
	requires transitive org.nasdanika.html.ecore.gen;
		
}