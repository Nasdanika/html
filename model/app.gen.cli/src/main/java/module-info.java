import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.html.model.app.gen.cli.AppCommandFactory;

module org.nasdanika.html.model.app.gen.cli {

	requires org.nasdanika.html.model.app.gen;		
	requires org.nasdanika.cli;
	requires spring.core;
	
	opens org.nasdanika.html.model.app.gen.cli to info.picocli;
	
	provides CapabilityFactory with AppCommandFactory;

}
