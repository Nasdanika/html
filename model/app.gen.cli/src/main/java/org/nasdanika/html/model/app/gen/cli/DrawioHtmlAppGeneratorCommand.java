package org.nasdanika.html.model.app.gen.cli;

import java.util.Collection;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.cli.ModuleVersionProvider;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Document;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.drawio.DrawioActionGenerator;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParentCommand;

@Command(
		description = "Generates html application model from a drawio document",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "html-app")
@ParentCommands(Document.Supplier.class)
public class DrawioHtmlAppGeneratorCommand extends AbstractHtmlAppGeneratorCommand {
	
	@ParentCommand
	private Document.Supplier documentSupplier;

	protected Consumer<Diagnostic> createDiagnosticConsumer() {
		return d -> d.dump(System.out, 0);
	}

	protected DrawioActionGenerator createDrawioActionGenerator() {
		return new DrawioActionGenerator() {
			
			@Override
			protected URI getBaseURI() {
				URI baseURI = super.getBaseURI();
				if (Util.isBlank(base)) {
					return baseURI;
				}
				
				URI bURI = URI.createURI(base);
				if (bURI.isRelative()) {
					bURI = bURI.resolve(baseURI);
				}
				return bURI;
			}
			
		};
	}
	
	@Option(
		names = {"-b", "--base-uri"},
		description = "Base URI. E.g. 'pages/'")
	private String base;

	@Override
	protected Collection<Label> getLabels(ProgressMonitor progressMonitor) {
		Document document = documentSupplier.getDocument(progressMonitor); 
		DrawioActionGenerator actionGenerator = createDrawioActionGenerator();
		Supplier<Collection<Label>> labelSupplier = actionGenerator.createLabelsSupplier(document, progressMonitor);
		Consumer<Diagnostic> diagnosticConsumer = createDiagnosticConsumer();
		return labelSupplier.call(progressMonitor, diagnosticConsumer);
	}		

}