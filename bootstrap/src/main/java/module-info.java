import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.impl.DefaultBootstrapFactory;

module org.nasdanika.html.bootstrap {
	
	requires transitive org.nasdanika.html;
	
	exports org.nasdanika.html.bootstrap;
	exports org.nasdanika.html.bootstrap.impl;
	
	provides BootstrapFactory with DefaultBootstrapFactory;
	
}