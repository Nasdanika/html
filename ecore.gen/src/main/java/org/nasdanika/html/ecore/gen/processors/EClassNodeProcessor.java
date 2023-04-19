package org.nasdanika.html.ecore.gen.processors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.nasdanika.common.Context;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;

public class EClassNodeProcessor extends EClassifierNodeProcessor<EClass> {

	public EClassNodeProcessor(NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config, Context context) {
		super(config, context);
	}	

}
