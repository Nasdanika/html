package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.impl.PathNavigationActionActivator;

public class EStructuralFeatureViewActionImpl<T extends EObject, F extends EStructuralFeature, V extends ViewAction<T>> extends ViewActionImpl<T> implements EStructuralFeatureViewAction<T,F> {
	
	private F feature;
	private V semanticElementViewAction;

	public EStructuralFeatureViewActionImpl(T semanticElement, F feature) {
		super(semanticElement);
		this.feature = feature;
		this.setText(EmfUtil.getNasdanikaAnnotationDetail(feature, EmfUtil.LABEL_KEY, Util.nameToLabel(feature.getName())));
		this.setIcon(EmfUtil.getNasdanikaAnnotationDetail(feature, EmfUtil.ICON_KEY, EmfUtil.getNasdanikaAnnotationDetail(feature.getEType(), EmfUtil.ICON_KEY)));
	}
	
	public EStructuralFeatureViewActionImpl(V semanticElementViewAction, F feature) {
		this(semanticElementViewAction.getSemanticElement(), feature);
		this.semanticElementViewAction = semanticElementViewAction;
		
		ActionActivator semanticElementViewActionActivator = semanticElementViewAction.getActivator();
		if (semanticElementViewActionActivator instanceof NavigationActionActivator) {
			Marked marked = EObjectAdaptable.adaptTo(getSemanticElement(), Marked.class);
			setActivator(
					new PathNavigationActionActivator(
							this, 
							((NavigationActionActivator) semanticElementViewActionActivator).getUrl(null), 
							feature.getName() + "/index.html", 
							marked == null ? null : marked.getMarker()));
		}
		setParent(semanticElementViewAction);
	}	
	
	@Override
	public F getEStructuralFeature() {
		return feature;
	}
	
	public V getSemanticElementViewAction() {
		return semanticElementViewAction;
	}
	
	@Override
	public String getTooltip() {
		String descr = getDescription();
		if (!Util.isBlank(descr)) {
			return Util.firstPlainTextSentence(descr, 50, 250);
		}
		
		return null;
	}
	
	@Override
	public String getText() {
		return semanticElementViewAction == null ? super.getText() : semanticElementViewAction.featureLabelText(getEStructuralFeature());
	}
	
	@Override
	public String getIcon() {
		return semanticElementViewAction == null ? super.getIcon() : semanticElementViewAction.featureIcon(getEStructuralFeature());
	}
	
	@Override
	public String getDescription() {
		return semanticElementViewAction == null ? super.getDescription() : semanticElementViewAction.featureDescription(getEStructuralFeature());
	}

}
