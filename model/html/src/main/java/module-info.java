import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.html.model.html.util.HtmlEPackageResourceSetCapabilityFactory;

module org.nasdanika.html.model.html {
		
	requires transitive org.nasdanika.html;
	requires transitive org.nasdanika.exec;
	
	exports org.nasdanika.html.model.html;
	exports org.nasdanika.html.model.html.impl;
	exports org.nasdanika.html.model.html.util;
	
	provides CapabilityFactory with HtmlEPackageResourceSetCapabilityFactory;
	
}
