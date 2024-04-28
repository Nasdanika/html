package org.nasdanika.html.model.app.gen.cli;

import java.util.List;

import org.nasdanika.cli.RootCommand;
import org.nasdanika.cli.SubCommandCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class AppCommandFactory extends SubCommandCapabilityFactory {


	@Override
	protected Object createCommand(List<CommandLine> parentPath, ProgressMonitor progressMonitor) {
		if (parentPath != null && parentPath.size() == 1 && parentPath.get(0).getCommandSpec().userObject() instanceof RootCommand) {
			return new AppCommand();			
		}
		return null;
	}

}
