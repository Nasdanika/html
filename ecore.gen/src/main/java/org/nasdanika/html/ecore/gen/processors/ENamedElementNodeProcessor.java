package org.nasdanika.html.ecore.gen.processors;

import org.eclipse.emf.ecore.ENamedElement;
import org.nasdanika.common.Context;

public class ENamedElementNodeProcessor<T extends ENamedElement> extends EModelElementNodeProcessor<T> {

	public ENamedElementNodeProcessor(Context context) {
		super(context);
	}	

}
