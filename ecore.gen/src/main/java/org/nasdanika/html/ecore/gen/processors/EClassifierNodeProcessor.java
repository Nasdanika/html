package org.nasdanika.html.ecore.gen.processors;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;
import org.nasdanika.html.model.app.graph.Registry;

public class EClassifierNodeProcessor<T extends EClassifier> extends ENamedElementNodeProcessor<T> {

	public EClassifierNodeProcessor(
			NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config,
			Context context,
			java.util.function.Function<ProgressMonitor, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
	}	
		
	// === Uses ===
	
	// TODO - type references in structural features, operation and parameter types, generic types?
	
	
	@Override
	protected void buildIncomingReference(
			EReference eReference,
			List<Entry<EReferenceConnection, WidgetFactory>> referenceIncomingEndpoints, Collection<Label> labels,
			Map<EReferenceConnection, Collection<Label>> incomingLabels, ProgressMonitor progressMonitor) {
		
		System.out.println("*** " + eReference.getEContainingClass().getName() + "." + eReference.getName());
		
		// TODO Auto-generated method stub
		super.buildIncomingReference(eReference, referenceIncomingEndpoints, labels, incomingLabels, progressMonitor);
	}	

}
