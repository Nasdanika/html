package org.nasdanika.html.model.app.gen.cli;

import org.nasdanika.cli.CommandGroup;

import picocli.CommandLine.Command;

@Command(
		description = "HTML Application model commands",
		name = "app",
		subcommands = {
			SiteCommand.class
			// TODO - serve command to serve a site over HTTP instead of generating. Netty, caching, ...
		})
public class AppCommand extends CommandGroup {
	

}
