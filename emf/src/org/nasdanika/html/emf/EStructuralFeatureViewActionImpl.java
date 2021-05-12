package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EmfUtil;

public class EStructuralFeatureViewActionImpl<T extends EObject, F extends EStructuralFeature> extends ViewActionImpl<T> implements EStructuralFeatureViewAction<T,F> {
	
	private F feature;

	public EStructuralFeatureViewActionImpl(T semanticElement, F feature) {
		super(semanticElement);
		this.feature = feature;
		this.setText(EmfUtil.getNasdanikaAnnotationDetail(feature, EmfUtil.LABEL_KEY, Util.nameToLabel(feature.getName())));
		this.setIcon(EmfUtil.getNasdanikaAnnotationDetail(feature, EmfUtil.ICON_KEY, EmfUtil.getNasdanikaAnnotationDetail(feature.getEType(), EmfUtil.ICON_KEY)));
	}
	
	@Override
	public F getEStructuralFeature() {
		return feature;
	}

}
