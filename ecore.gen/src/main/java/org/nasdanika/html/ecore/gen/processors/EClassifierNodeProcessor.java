package org.nasdanika.html.ecore.gen.processors;

import org.eclipse.emf.ecore.EClassifier;
import org.nasdanika.common.Context;

public class EClassifierNodeProcessor<T extends EClassifier> extends ENamedElementNodeProcessor<T> {

	public EClassifierNodeProcessor(Context context) {
		super(context);
	}	

}
