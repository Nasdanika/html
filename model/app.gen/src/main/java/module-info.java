import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.html.model.app.gen.AppAdapterCapabilityFactory;

module org.nasdanika.html.model.app.gen {
		
	requires transitive org.nasdanika.html.model.bootstrap.gen;
	requires transitive org.nasdanika.html.jstree;
	requires transitive org.nasdanika.html.emf;
	requires transitive sitemapgen4j;
	requires org.apache.commons.codec;
	requires org.apache.commons.text;
	requires transitive org.jsoup;
	requires org.eclipse.emf.ecore.xmi;
	requires org.eclipse.emf.ecore;
	
	exports org.nasdanika.html.model.app.gen;
	opens org.nasdanika.html.model.app.gen;
	
	provides CapabilityFactory with AppAdapterCapabilityFactory;
}
