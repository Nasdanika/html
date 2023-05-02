package org.nasdanika.html.ecore.gen.processors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Context;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;

public class EStructuralFeatureNodeProcessor<T extends EStructuralFeature> extends ETypedElementNodeProcessor<T> {

	public EStructuralFeatureNodeProcessor(
			NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config,
			Context context,
			java.util.function.Function<URI, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
	}	

}
