module org.nasdanika.html.ecore {
	
	requires transitive org.nasdanika.html.model.app.gen;
	requires transitive org.nasdanika.emf;
	requires org.apache.commons.codec;
	requires org.eclipse.emf.codegen.ecore;
	requires org.eclipse.emf.ecore.xmi;
	requires org.nasdanika.html.model.html;
	
	exports org.nasdanika.html.ecore;
	
}