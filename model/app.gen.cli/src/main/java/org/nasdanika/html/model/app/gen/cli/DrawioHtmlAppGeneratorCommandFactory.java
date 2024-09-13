package org.nasdanika.html.model.app.gen.cli;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.cli.SubCommandCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class DrawioHtmlAppGeneratorCommandFactory extends SubCommandCapabilityFactory<DrawioHtmlAppGeneratorCommand> {

	@Override
	protected Class<DrawioHtmlAppGeneratorCommand> getCommandType() {
		return DrawioHtmlAppGeneratorCommand.class;
	}
	
	@Override
	protected CompletionStage<DrawioHtmlAppGeneratorCommand> doCreateCommand(
			List<CommandLine> parentPath,
			Loader loader,
			ProgressMonitor progressMonitor) {
		return CompletableFuture.completedStage(new DrawioHtmlAppGeneratorCommand());
	}

}
