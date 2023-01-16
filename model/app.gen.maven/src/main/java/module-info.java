module org.nasdanika.html.model.app.gen.maven {
	requires maven.plugin.api;
	requires maven.plugin.annotations;
	requires transitive org.nasdanika.html.model.app.gen;
	requires maven.project; 
	
	exports org.nasdanika.html.model.app.gen.maven;
	
}
