module org.nasdanika.html.model.app.graph {
		
	requires transitive org.nasdanika.html.model.app.gen;
	requires transitive org.nasdanika.graph;
	requires org.apache.commons.text;
	requires org.eclipse.emf.ecore.xmi;
	
	exports org.nasdanika.html.model.app.graph;
	exports org.nasdanika.html.model.app.graph.emf;
	
}
