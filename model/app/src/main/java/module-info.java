import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.html.model.app.util.AppEPackageResourceSetCapabilityFactory;

module org.nasdanika.html.model.app {
		
	requires transitive org.nasdanika.html.model.bootstrap;
	
	exports org.nasdanika.html.model.app;
	exports org.nasdanika.html.model.app.impl;
	exports org.nasdanika.html.model.app.util;	
	
	provides CapabilityFactory with AppEPackageResourceSetCapabilityFactory;
	
}
	
