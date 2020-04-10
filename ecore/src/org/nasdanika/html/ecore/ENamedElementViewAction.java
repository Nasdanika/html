package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.ENamedElement;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.emf.ENamedElementLabel;

public class ENamedElementViewAction<T extends ENamedElement> extends EModelElementViewAction<T> {
	
	private Label label;
		
	public ENamedElementViewAction(T value) {
		super(value);
		
		/**
		 * Element name as-is.
		 */
		label = new ENamedElementLabel<T>(value) {
			
			@Override
			protected String nameToLabel() {
				return modelElement.getName();
			}
			
		};
	}
	
	@Override
	public String getText() {
		return label.getText();
	}
	
	@Override
	public String getTooltip() {
		return label.getTooltip();
	}
	
	@Override
	public String getDescription() {
		return label.getDescription();
	}
	
	/**
	 * Encodes package NS URI as HEX.
	 */
	@Override
	public Object getId() {
		return getParent().getId()+"/"+target.getName();
	}
	
	@Override
	public ActionActivator getActivator() {
		return new NavigationActionActivator() {
			
			@Override
			public String getUrl(String base) {
				return "#content/"+getId()+".html";
			}
			
		};
	}

}
