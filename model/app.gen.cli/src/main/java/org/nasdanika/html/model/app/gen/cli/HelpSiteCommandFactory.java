package org.nasdanika.html.model.app.gen.cli;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.cli.HelpCommand;
import org.nasdanika.cli.SubCommandCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

/**
 * Sub-command for the help command to generate help site.
 */
public class HelpSiteCommandFactory extends SubCommandCapabilityFactory<HelpSiteCommand> {

	@Override
	protected CompletionStage<HelpSiteCommand> createCommand(
			List<CommandLine> parentPath, 
			Loader loader,
			ProgressMonitor progressMonitor) {
		if (parentPath != null && parentPath.size() > 1) {
			Object userObj = parentPath.get(parentPath.size() - 1).getCommandSpec().userObject();
			if (userObj instanceof HelpCommand) {
				return CompletableFuture.completedStage(new HelpSiteCommand(((HelpCommand) userObj).getRoot()));
			}
		}
		return null;
	}

	@Override
	protected Class<HelpSiteCommand> getCommandType() {
		return HelpSiteCommand.class;
	}

}
