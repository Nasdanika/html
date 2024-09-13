package org.nasdanika.html.model.app.gen.cli;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.cli.RootCommand;
import org.nasdanika.cli.SubCommandCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class AppCommandFactory extends SubCommandCapabilityFactory<AppCommand> {

	@Override
	protected CompletionStage<AppCommand> createCommand(
			List<CommandLine> parentPath, 
			Loader loader,
			ProgressMonitor progressMonitor) {
		if (parentPath != null && parentPath.size() == 1 && parentPath.get(0).getCommandSpec().userObject() instanceof RootCommand) {
			return CompletableFuture.completedStage(new AppCommand());			
		}
		return null;
	}

	@Override
	protected Class<AppCommand> getCommandType() {
		return AppCommand.class;
	}

}
