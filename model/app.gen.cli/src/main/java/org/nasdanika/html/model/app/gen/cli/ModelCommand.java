package org.nasdanika.html.model.app.gen.cli;

import org.nasdanika.cli.CommandGroup;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.cli.RootCommand;

import picocli.CommandLine.Command;

@Command(
		description = "Model commands",
		name = "model",
		versionProvider = ModuleVersionProvider.class,
		subcommands = {
			ActionModelCommand.class,
			ModelDocSiteCommand.class
		},		
		mixinStandardHelpOptions = true)
public class ModelCommand extends CommandGroup {
	

}
