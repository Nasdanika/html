package org.nasdanika.html.ecore;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

public class EStructuralFeatureViewAction<T extends EStructuralFeature> extends ETypedElementViewAction<T> {

	public EStructuralFeatureViewAction(T value) {
		super(value);
	}
	
	@Override
	protected List<EStructuralFeature> getChildFeatures() {
		return Collections.emptyList();
	}
	
}
