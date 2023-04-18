package org.nasdanika.html.ecore.gen.processors;

import org.eclipse.emf.ecore.EModelElement;
import org.nasdanika.common.Context;
import org.nasdanika.html.model.app.graph.emf.EObjectNodeProcessor;

public class EModelElementNodeProcessor<T extends EModelElement> extends EObjectNodeProcessor<T> {

	public EModelElementNodeProcessor(Context context) {
		super(context);
	}	

}
