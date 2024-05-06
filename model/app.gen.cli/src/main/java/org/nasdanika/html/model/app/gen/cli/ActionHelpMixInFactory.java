package org.nasdanika.html.model.app.gen.cli;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.cli.HelpCommand;
import org.nasdanika.cli.MixInCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class ActionHelpMixInFactory extends MixInCapabilityFactory<ActionHelpMixIn> {

	@Override
	protected String getName() {
		return "action-output";
	}

	@Override
	protected CompletionStage<ActionHelpMixIn> doCreateMixIn(
			List<CommandLine> commandPath, 
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		if (commandPath != null && commandPath.size() > 1) {
			Object userObj = commandPath.get(commandPath.size() - 1).getCommandSpec().userObject();
			if (userObj instanceof HelpCommand) {
				return CompletableFuture.completedStage(new ActionHelpMixIn(((HelpCommand) userObj).getRoot()));
			}
		}
		return null;
	}

	@Override
	protected Class<ActionHelpMixIn> getMixInType() {
		return ActionHelpMixIn.class;
	}

}
