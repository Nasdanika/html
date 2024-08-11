module org.nasdanika.html.model.app.graph {
		
	requires transitive org.nasdanika.html.model.app.gen;
	requires transitive org.nasdanika.graph;
	requires org.apache.commons.text;
	requires org.eclipse.emf.ecore.xmi;
	requires org.nasdanika.drawio;
	
	exports org.nasdanika.html.model.app.graph;
	exports org.nasdanika.html.model.app.graph.drawio;
	exports org.nasdanika.html.model.app.graph.emf;
	
	opens org.nasdanika.html.model.app.graph.drawio to org.nasdanika.common;
	opens org.nasdanika.html.model.app.graph.emf to org.nasdanika.common;
	
}
