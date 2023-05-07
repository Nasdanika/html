package org.nasdanika.html.ecore.gen.processors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;

public class EStructuralFeatureNodeProcessor<T extends EStructuralFeature> extends ETypedElementNodeProcessor<T> {

	public EStructuralFeatureNodeProcessor(
			NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config,
			Context context,
			java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
	}	
	
	/**
	 * Creating a link only if the action has content 
	 */
	@Override
	public Label createLink(String path, ProgressMonitor progressMonitor) {
		Label action = createAction(progressMonitor);
		if (action instanceof Action && !((Action) action).getContent().isEmpty()) {
			return super.createLink(path, progressMonitor);
		}
		return createLabel(progressMonitor);
	}

}
