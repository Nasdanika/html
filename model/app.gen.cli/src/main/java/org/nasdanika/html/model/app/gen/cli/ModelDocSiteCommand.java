package org.nasdanika.html.model.app.gen.cli;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.exec.util.ModelMixIn;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.emf.ActionGenerator;
import org.nasdanika.models.ecore.graph.processors.EcoreActionGenerator;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
		description = "Generates model documentation site",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "site")
public class ModelDocSiteCommand extends AbstractSiteCommand {
	
	@Mixin
	private ModelMixIn modelMixIn;
		
	@Parameters(
		index =  "0",	
		description = {  
			"Model URI, resolved relative",
			"to the current directory",
			"or looked up in registered rule sets",
			"if -R option is provided"
		})
	private String model;
			
	@Parameters(index =  "1", description = "Output directory")
	private String output;
	
	@Override
	protected String getOutput() {
		return output;
	}
	
	private URI modelURI;
	
	@Override
	protected URI getModelURI(URI contextURI) {
		return modelURI;
	}
		
	@Option(names = "--root-action-icon", description = "Root action icon")
	private String rootActionIcon;
	
	@Option(names = "--root-action-text", description = "Root action text")
	private String rootActionText;
	
	@Option(names = "--root-action-location", description = "Root action location")
	private String rootActionLocation;

	@Override
	protected int generate(Context context, ProgressMonitor progressMonitor) throws IOException, DiagnosticException {
		try (ProgressMonitor gpm = progressMonitor.scale(4)) {
			EObject eObj = modelMixIn.getEObject(model, progressMonitor.split("Loading model", 1));
			
			Consumer<Diagnostic> diagnosticConsumer = d -> d.dump(progressMonitor.split("Diagnostic", 1));		
			try (ProgressMonitor actionGeneratorProgressMonitor = progressMonitor.split("Generating action model", 1)) {
				Action rootAction = AppFactory.eINSTANCE.createAction();
				rootAction.setIcon(rootActionIcon);
				rootAction.setText(rootActionText);
				rootAction.setLocation(rootActionLocation);
				
				EcoreActionGenerator actionGenerator = EcoreActionGenerator.loadEcoreActionGenerator(
						eObj, 
						context, 
						null, 
						null, 
						null, 
						diagnosticConsumer, 
						actionGeneratorProgressMonitor);
				
				Map<EObject, Collection<Label>> labelMap = actionGenerator.generateActionModel(diagnosticConsumer, progressMonitor);
				labelMap
					.values()
					.stream()
					.flatMap(Collection::stream)
					.forEach(principal -> {
						rootAction.getChildren().add(principal);
						((Action) principal).setLocation("${base-uri}index.html");
					});
				
				URI actionModelResourceURI = URI.createFileURI(File.createTempFile("resource-set-action-model-", ".xml").getAbsolutePath());		
				ActionGenerator.saveLabels(Collections.singleton(rootAction), actionModelResourceURI);
				modelURI = actionModelResourceURI.appendFragment("/");
			}			
			return super.generate(context, gpm.split("Generating site", 1));
		}
	}

}
