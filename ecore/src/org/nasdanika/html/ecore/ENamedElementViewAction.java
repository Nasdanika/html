package org.nasdanika.html.ecore;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.emf.ENamedElementLabel;
import org.nasdanika.html.emf.EObjectViewAction;

public class ENamedElementViewAction<T extends ENamedElement> extends EObjectViewAction<T> {
	
	public static final String iconsBase = "https://www.nasdanika.org/resources/images/ecore/";
	private Label label;

	public ENamedElementViewAction(T value) {
		super(value);
		label = new ENamedElementLabel<>(value);
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
				return "#content/"+getId()+".html";
			}
			
		};
	}
		
	/**
	 * In situations where classes referencing this class are known this method can be overridden. 
	 * @return
	 */	
	protected Collection<EClass> getReferrers(EClass eClass) {
		TreeIterator<?> acit;
		Resource eResource = eClass.eResource();
		if (eResource == null) {
			EPackage ePackage = eClass.getEPackage();
			if (ePackage == null) {
				return Collections.emptySet();
			}
			acit = ePackage.eAllContents();
		} else {
			ResourceSet resourceSet = eResource.getResourceSet();
			acit = resourceSet == null ? eResource.getAllContents() : eResource.getAllContents();
		}
		Set<EClass> ret = new HashSet<>();
		acit.forEachRemaining(obj -> {
			if (obj instanceof EReference && ((EReference) obj).getEReferenceType() == eClass) {
				ret.add(((EReference) obj).getEContainingClass());
			}
		});
		return ret;
	}
	

}
