package org.nasdanika.html.model.app.gen.cli;

import java.io.File;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.nasdanika.cli.CommandGroup;
import org.nasdanika.cli.ModuleVersionProvider;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.drawio.Document;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.gen.ActionSiteGenerator;
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
		
		
		
		Action rootAction = AppFactory.eINSTANCE.createAction();
		rootAction.setText("Nasdanika");		
		rootAction.getChildren().addAll(labels);
		
		String siteMapDomain = "https://nasdanika.org/demos/" + diagramName;		
		ActionSiteGenerator actionSiteGenerator = new ActionSiteGenerator();		
		
		Map<String, Collection<String>> errors = actionSiteGenerator.generate(
				rootAction, // URI.appendFragment("/"), 
				Theme.Cerulean.pageTemplateCdnURI, 
				siteMapDomain, 
				new File("target/drawio-action-site/" + diagramName), 
				new File("target/doc-site-work-dir/" + diagramName), 
				false);
				
		int errorCount = 0;
		for (Entry<String, Collection<String>> ee: errors.entrySet()) {
			System.err.println(ee.getKey());
			for (String error: ee.getValue()) {
				System.err.println("\t" + error);
				++errorCount;
			}
		}
		
		System.out.println(errorCount);
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
