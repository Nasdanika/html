package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.impl.PathNavigationActionActivator;

public class ETypedElementViewActionImpl<T extends EObject, E extends ETypedElement, V extends ViewAction<T>> extends ViewActionImpl<T> implements ETypedElementViewAction<T,E> {
	
	private E typedElement;
	private V semanticElementViewAction;

	public ETypedElementViewActionImpl(T semanticElement, E typedElement) {
		super(semanticElement);
		this.typedElement = typedElement;
		this.setText(EmfUtil.getNasdanikaAnnotationDetail(typedElement, EmfUtil.LABEL_KEY, Util.nameToLabel(typedElement.getName())));
		this.setIcon(EmfUtil.getNasdanikaAnnotationDetail(typedElement, EmfUtil.ICON_KEY, EmfUtil.getNasdanikaAnnotationDetail(typedElement.getEType(), EmfUtil.ICON_KEY)));
	}
	
	public ETypedElementViewActionImpl(V semanticElementViewAction, E typedElement) {
		this(semanticElementViewAction.getSemanticElement(), typedElement);
		this.semanticElementViewAction = semanticElementViewAction;
		
		ActionActivator semanticElementViewActionActivator = semanticElementViewAction.getActivator();
		if (semanticElementViewActionActivator instanceof NavigationActionActivator) {
			Marked marked = EObjectAdaptable.adaptTo(getSemanticElement(), Marked.class);
			setActivator(
					new PathNavigationActionActivator(
							this, 
							((NavigationActionActivator) semanticElementViewActionActivator).getUrl(null), 
							SimpleEObjectViewAction.sectionPath(semanticElementViewAction) + Util.camelToKebab(typedElement.eClass().getName()) + "/" + typedElement.getName() + "/index.html", 
							marked == null ? null : marked.getMarker()));
		}
		setParent(semanticElementViewAction);
	}	
	
	@Override
	public E getETypedElement() {
		return typedElement;
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
		return semanticElementViewAction == null ? super.getText() : semanticElementViewAction.memberLabelText(getETypedElement());
	}
	
	@Override
	public String getIcon() {
		return semanticElementViewAction == null ? super.getIcon() : semanticElementViewAction.memberIcon(getETypedElement());
	}
	
	@Override
	public String getDescription() {
		return semanticElementViewAction == null ? super.getDescription() : semanticElementViewAction.memberDescription(getETypedElement());
	}

}
