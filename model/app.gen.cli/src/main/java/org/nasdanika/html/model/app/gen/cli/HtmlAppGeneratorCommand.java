package org.nasdanika.html.model.app.gen.cli;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.cli.ContextMixIn;
import org.nasdanika.cli.Description;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.EObjectSupplier;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.emf.HtmlAppGenerator;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.ParentCommand;

@Command(
		description = "Generates html application model from a drawio document",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "html-app")
@ParentCommands(EObjectSupplier.class)
@Description(icon = "https://img.icons8.com/external-flatart-icons-outline-flatarticons/20/external-html-programming-and-coding-flatart-icons-outline-flatarticons-5.png")
public class HtmlAppGeneratorCommand extends AbstractHtmlAppGeneratorCommand {
	
	@ParentCommand
	EObjectSupplier<EObject> eObjectSupplier;
	
	@Mixin
	ContextMixIn contextMixin;
	
	@Override
	protected Collection<Label> getLabels(ProgressMonitor progressMonitor) {		
		try {
			Context context = contextMixin.createContext(progressMonitor);
			Collection<EObject> sources = eObjectSupplier.getEObjects(progressMonitor); 
			Consumer<Diagnostic> diagnosticConsumer = createDiagnosticConsumer(progressMonitor);
			HtmlAppGenerator generator = createHtmlAppGenerator(sources, context, progressMonitor, diagnosticConsumer);			
			Map<EObject, Collection<Label>> labelMap = generator.generateHtmlAppModel(diagnosticConsumer, progressMonitor);
			return flatMap(labelMap);
		} catch (IOException e) {
			throw new NasdanikaException(e);
		}		
	}

	protected HtmlAppGenerator createHtmlAppGenerator(
			Collection<EObject> sources,
			Context context, 
			ProgressMonitor progressMonitor, 
			Consumer<Diagnostic> diagnosticConsumer) {
		return HtmlAppGenerator.load(
				sources, 
				context, 
				createPrototypeProvider(progressMonitor), 
				createFactoryPredicate(progressMonitor),
				createEPackagePredicate(progressMonitor),
				diagnosticConsumer, 
				progressMonitor);
	}		
	
	protected Collection<Label> flatMap(Map<EObject, Collection<Label>> labelMap) {
		return labelMap
				.values()
				.stream()
				.flatMap(Collection::stream)
				.toList();
		
	}

	protected Consumer<Diagnostic> createDiagnosticConsumer(ProgressMonitor progressMonitor) {
		return d -> progressMonitor.worked(d.getStatus(), 1, "Diagnostic: " + d.getMessage(), d);
	}	
	
	protected java.util.function.BiFunction<URI, ProgressMonitor, Label> createPrototypeProvider(ProgressMonitor progressMonitor) {
		return null;
	}
	
	protected Predicate<Object> createFactoryPredicate(ProgressMonitor progressMonitor) {
		return null;
	}
	
	protected Predicate<EPackage> createEPackagePredicate(ProgressMonitor progressMonitor) {
		return null;
	}		

}
