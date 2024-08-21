package org.nasdanika.html.model.app.gen.cli;

import org.nasdanika.cli.CommandGroup;
import org.nasdanika.cli.ModuleVersionProvider;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Document;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.util.ActionSupplier;

import picocli.CommandLine.Command;

@Command(
		description = "Generates actions from a drawio document",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "actions")
@ParentCommands(Document.Supplier.class)
public class DrawioActionGeneratorCommand extends CommandGroup implements ActionSupplier {

	@Override
	public Action getEObject(ProgressMonitor progressMonitor) {
		System.out.println(this + "Yeah");
		return null;
	}		

}
