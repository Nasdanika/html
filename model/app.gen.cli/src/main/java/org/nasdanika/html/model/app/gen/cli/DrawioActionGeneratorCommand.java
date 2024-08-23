package org.nasdanika.html.model.app.gen.cli;

import java.util.Collection;
import java.util.function.Consumer;

import org.nasdanika.cli.CommandGroup;
import org.nasdanika.cli.ModuleVersionProvider;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.drawio.Document;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.drawio.DrawioActionGenerator;
import org.nasdanika.html.model.app.util.ActionSupplier;

import picocli.CommandLine.Command;
import picocli.CommandLine.ParentCommand;

@Command(
		description = "Generates actions from a drawio document",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "actions")
@ParentCommands(Document.Supplier.class)
public class DrawioActionGeneratorCommand extends CommandGroup implements ActionSupplier {
	
	@ParentCommand
	private Document.Supplier documentSupplier;

	@Override
	public Action getEObject(ProgressMonitor progressMonitor) {
		Document document = documentSupplier.getDocument(progressMonitor); 
		DrawioActionGenerator actionGenerator = createDrawioActionGenerator();
		Supplier<Collection<Label>> labelSupplier = actionGenerator.createLabelsSupplier(document, progressMonitor);
		Consumer<Diagnostic> diagnosticConsumer = createDiagnosticConsumer();
		Collection<Label> labels = labelSupplier.call(progressMonitor, diagnosticConsumer);
		return reduce(labels);
	}
	
	protected Action reduce(Collection<Label> labels) {
		// TODO - root action, principal, ...
		if (labels.size() == 1) {
			return (Action) labels.iterator().next();
		}
		// TODO - more than one
		throw new UnsupportedOperationException();
	}

	protected Consumer<Diagnostic> createDiagnosticConsumer() {
		return d -> d.dump(System.out, 0);
	}

	protected DrawioActionGenerator createDrawioActionGenerator() {
		return new DrawioActionGenerator();
	}		

}
