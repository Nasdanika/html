package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.ENamedElement;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.emf.EObjectViewAction;

public class ENamedElementViewAction<T extends ENamedElement> extends EObjectViewAction<T> {
	
	public static final String iconsBase = "https://www.nasdanika.org/resources/images/ecore/";

	public ENamedElementViewAction(T value) {
		super(value);
	}
	
	@Override
	public String getText() {
		return ((ENamedElement) getValue()).getName(); // Escape?
	}
	
	@Override
	public String getIcon() {
		return iconsBase+((ENamedElement) getValue()).eClass().getName()+".gif";
	}
	
	/**
	 * Encodes package NS URI as HEX.
	 */
	@Override
	public Object getId() {
		return getParent().getId()+"/"+((ENamedElement) getValue()).getName();
	}
	
	@Override
	public ActionActivator getActivator() {
		return new NavigationActionActivator() {
			
			@Override
			public String getUrl() {
				return "#router/content/"+getId()+".html";
			}
			
		};
	}

}
