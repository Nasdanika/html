module org.nasdanika.html.model.app.gen {
		
	requires transitive org.nasdanika.html.model.app;
	requires transitive org.nasdanika.html.model.bootstrap.gen;
	requires transitive org.nasdanika.html.jstree;
	requires transitive org.nasdanika.drawio;
	requires org.apache.commons.codec;
	requires org.apache.commons.text;
	requires transitive org.jsoup;
	requires org.eclipse.emf.ecore.xmi;
	
	exports org.nasdanika.html.model.app.gen;	
}
