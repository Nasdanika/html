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
import org.nasdanika.cli.ModuleVersionProvider;
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
public class HtmlAppGeneratorCommand extends AbstractHtmlAppGeneratorCommand {
	
	@ParentCommand
	EObjectSupplier<EObject> eObjectSupplier;
	
	@Mixin
	ContextMixIn contextMixin;
	
	@Override
	protected Collection<Label> getLabels(ProgressMonitor progressMonitor) {
//		EObject source,
//		Context context, 
//		java.util.function.BiFunction<URI, ProgressMonitor, Label> prototypeProvider,			
//		Predicate<Object> factoryPredicate,
//		Predicate<EPackage> ePackagePredicate,
//		Consumer<Diagnostic> diagnosticConsumer,
//		ProgressMonitor progressMonitor) {
		
		try {
			Context context = contextMixin.createContext(progressMonitor);
			EObject source = eObjectSupplier.getEObject(progressMonitor); 
			Consumer<Diagnostic> diagnosticConsumer = createDiagnosticConsumer();
			HtmlAppGenerator generator = HtmlAppGenerator.load(
					source, 
					context, 
					createPrototypeProvider(progressMonitor), 
					createFactoryPredicate(progressMonitor),
					createEPackagePredicate(progressMonitor),
					diagnosticConsumer, 
					progressMonitor);
			
			Map<EObject, Collection<Label>> labelMap = generator.generateHtmlAppModel(diagnosticConsumer, progressMonitor);
			return flatMap(labelMap);
		} catch (IOException e) {
			throw new NasdanikaException(e);
		}		
	}		
	
	protected Collection<Label> flatMap(Map<EObject, Collection<Label>> labelMap) {
		return labelMap
				.values()
				.stream()
				.flatMap(Collection::stream)
				.toList();
		
	}

	protected Consumer<Diagnostic> createDiagnosticConsumer() {
		return d -> d.dump(System.out, 0);
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
