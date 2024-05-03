package org.nasdanika.html.model.app.gen.cli;

import java.util.List;

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
	protected ActionHelpMixIn doCreateMixIn(List<CommandLine> commandPath, ProgressMonitor progressMonitor) {
		if (commandPath != null && commandPath.size() > 1) {
			Object userObj = commandPath.get(commandPath.size() - 1).getCommandSpec().userObject();
			if (userObj instanceof HelpCommand) {
				return new ActionHelpMixIn(((HelpCommand) userObj).getRoot());
			}
		}
		return null;
	}

	@Override
	protected Class<ActionHelpMixIn> getMixInType() {
		return ActionHelpMixIn.class;
	}

}
