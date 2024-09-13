package org.nasdanika.html.model.app.gen.cli;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.cli.SubCommandCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class SiteGeneratorCommandFactory extends SubCommandCapabilityFactory<SiteGeneratorCommand> {

	@Override
	protected Class<SiteGeneratorCommand> getCommandType() {
		return SiteGeneratorCommand.class;
	}
	
	@Override
	protected CompletionStage<SiteGeneratorCommand> doCreateCommand(
			List<CommandLine> parentPath,
			Loader loader,
			ProgressMonitor progressMonitor) {
		return CompletableFuture.completedStage(new SiteGeneratorCommand());
	}

}
