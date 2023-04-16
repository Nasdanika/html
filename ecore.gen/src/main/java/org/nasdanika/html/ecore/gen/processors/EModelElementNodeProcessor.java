package org.nasdanika.html.ecore.gen.processors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.nasdanika.common.Context;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Processor;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.emf.EObjectNodeProcessor;

public class EModelElementNodeProcessor<T extends EModelElement> extends EObjectNodeProcessor<T> {

	public EModelElementNodeProcessor(
			NodeProcessorConfig<Processor, LabelFactory, LabelFactory, Registry<URI>> config,
			Context context) {
		super(config, context);
	}	

}
