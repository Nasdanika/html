import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.html.model.bootstrap.util.BootstrapEPackageResourceSetCapabilityFactory;

module org.nasdanika.html.model.bootstrap {
		
	requires transitive org.nasdanika.html.bootstrap;
	requires transitive org.nasdanika.html.model.html;
	
	exports org.nasdanika.html.model.bootstrap;
	exports org.nasdanika.html.model.bootstrap.impl;
	exports org.nasdanika.html.model.bootstrap.util;
	
	provides CapabilityFactory with BootstrapEPackageResourceSetCapabilityFactory;
	
}
