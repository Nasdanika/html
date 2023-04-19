package org.nasdanika.html.ecore.gen.processors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;

public class EPackageNodeProcessor extends ENamedElementNodeProcessor<EPackage> {

	public EPackageNodeProcessor(NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config, Context context) {
		super(config, context);
	}	
	
}
