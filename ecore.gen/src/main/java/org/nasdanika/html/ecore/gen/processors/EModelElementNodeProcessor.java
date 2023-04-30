package org.nasdanika.html.ecore.gen.processors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.Util;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.emf.EObjectNodeProcessor;

public class EModelElementNodeProcessor<T extends EModelElement> extends EObjectNodeProcessor<T> {

	public EModelElementNodeProcessor(
			NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config, 
			Context context,
			java.util.function.Function<URI, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
	}	
	
	@Override
	protected void configureLabel(EObject eObject, Label label) {
		if (Util.isBlank(label.getIcon()) && eObject instanceof EModelElement) {
			label.setIcon("https://cdn.jsdelivr.net/gh/Nasdanika/html@master/ecore.gen/icons/" + ((EModelElement) eObject).eClass().getName() + ".gif");
		}
		super.configureLabel(eObject, label);
	}

}
