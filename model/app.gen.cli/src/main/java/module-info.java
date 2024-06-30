import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.html.model.app.gen.cli.ActionHelpMixInFactory;
import org.nasdanika.html.model.app.gen.cli.AppCommandFactory;
import org.nasdanika.html.model.app.gen.cli.HelpSiteCommandFactory;

module org.nasdanika.html.model.app.gen.cli {
	
	exports org.nasdanika.html.model.app.gen.cli;

	requires transitive org.nasdanika.html.model.app.gen;		
	requires transitive org.nasdanika.cli;
	requires spring.core;
	requires org.eclipse.emf.ecore.xmi;
	requires org.nasdanika.html.model.app.graph;
	
	opens org.nasdanika.html.model.app.gen.cli to info.picocli;
	
	provides CapabilityFactory with 
		AppCommandFactory, 
		HelpSiteCommandFactory,
		ActionHelpMixInFactory;

}
