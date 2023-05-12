package org.nasdanika.html.ecore.gen.processors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EGenericType;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.graph.WidgetFactory;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.emf.EObjectNodeProcessor;

public class EGenericTypeNodeProcessor extends EObjectNodeProcessor<EGenericType> {

	public EGenericTypeNodeProcessor(
			NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config,
			Context context,
			java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
	}	

}
