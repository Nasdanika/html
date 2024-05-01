package org.nasdanika.html.model.app.gen.cli;

import org.eclipse.emf.common.util.URI;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
		description = "Generates HTML site",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "site")
public class SiteCommand extends AbstractSiteCommand {
		
	@Parameters(
		index =  "0",	
		description = {  
			"Model URI, resolved relative",
			"to the current directory"
		})
	private String model;
	
	@Override
	protected URI getModelURI(URI contextURI) {
		 return URI.createURI(model).resolve(contextURI);	
	}
	
	@Parameters(index =  "1", description = "Output directory")
	private String output;
	
	@Override
	protected String getOutput() {
		return output;
	}

}
