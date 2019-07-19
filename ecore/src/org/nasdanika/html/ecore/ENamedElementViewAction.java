package org.nasdanika.html.ecore;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.emf.ENamedElementLabel;
import org.nasdanika.html.emf.EObjectAdaptable;
import org.nasdanika.html.emf.EObjectViewAction;
import org.nasdanika.html.emf.ViewAction;

public class ENamedElementViewAction<T extends ENamedElement> extends EObjectViewAction<T> {
	
	public static final String iconsBase = "https://www.nasdanika.org/resources/images/ecore/";
	private Label label;
	
	/**
	 * Descriptions shorter than this value are put on the top of the tabs, longer
	 * ones end up in their own tab. 
	 */
	protected int descriptionTabLengthThreshold = 2500; 
		
	protected Function<EClassifier, String> eClassifierLinkResolver = eClassifier -> {
		ViewAction viewAction = EObjectAdaptable.adaptTo(eClassifier, ViewAction.class);
		if (viewAction == null) {
			return null;
		}
		
		ActionActivator activator = viewAction.getActivator();
		return activator instanceof NavigationActionActivator  ? ((NavigationActionActivator) activator).getUrl() : null;
	};
	
	protected Function<EModelElement, String> eModelElementFirstDocSentenceProvider = eModelElement -> EObjectAdaptable.adaptTo(eModelElement, ViewAction.class).getTooltip();
		
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
	
	@Override
	public String getIcon() {
		return iconsBase+target.eClass().getName()+".gif";
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
			public String getUrl() {
				return "#content/"+getId()+".html";
			}
			
		};
	}

	/**
	 * Sorting by text.
	 */
	@Override
	public List<Action> getChildren() {
		return super.getChildren().stream().sorted((a1,a2) -> a1.getText().compareTo(a2.getText())).collect(Collectors.toList());
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
	
	protected static String cardinality(ETypedElement typedElement) {
		int lowerBound = typedElement.getLowerBound();
		int upperBound = typedElement.getUpperBound();
		String cardinality;
		if (lowerBound == upperBound) {
			cardinality = String.valueOf(lowerBound);
		} else {
			cardinality = lowerBound + ".." + (upperBound == -1 ? "*" : String.valueOf(upperBound));
		}
		if (typedElement instanceof EReference && ((EReference) typedElement).isContainment()) {
			cardinality = "<B>"+cardinality+"</B>";
		}
		return cardinality;
	}

}
