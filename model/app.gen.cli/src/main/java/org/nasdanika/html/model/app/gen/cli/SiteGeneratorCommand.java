package org.nasdanika.html.model.app.gen.cli;

import java.io.File;

import org.nasdanika.cli.CommandBase;
import org.nasdanika.cli.ModuleVersionProvider;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.cli.ProgressMonitorMixIn;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.util.ActionSupplier;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;

@Command(
		description = "Generates HTML site",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "site")
@ParentCommands(ActionSupplier.class)
public class SiteGeneratorCommand extends CommandBase {
	
	@Parameters(
			index =  "0",	
			arity = "1",
			description = "Output directory")
		private File output;
	

	@ParentCommand
	ActionSupplier actionSupplier;
	
	@Mixin
	ProgressMonitorMixIn progressMonitorMixIn;	

	@Override
	public Integer call() throws Exception {
		ProgressMonitor progressMonitor = progressMonitorMixIn.createProgressMonitor(1);
		System.out.println(actionSupplier);
		// TODO Auto-generated method stub
		return 0;
	}	

}
