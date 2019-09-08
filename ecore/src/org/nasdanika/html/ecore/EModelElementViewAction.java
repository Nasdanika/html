package org.nasdanika.html.ecore;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.emf.EObjectAdaptable;
import org.nasdanika.html.emf.EObjectViewAction;
import org.nasdanika.html.emf.ViewAction;

public class EModelElementViewAction<T extends EModelElement> extends EObjectViewAction<T> {
	
	public static final String iconsBase = "https://www.nasdanika.org/resources/images/ecore/";
	
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
		
	public EModelElementViewAction(T value) {
		super(value);
		
	}
	
	@Override
	public String getIcon() {
		return iconsBase+target.eClass().getName()+".gif";
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
	
	// --- Handling generic types in action text --- 
	// TODO - a way to provide links in addition to plain text
	
	protected String getTypeParameterLabel(ETypeParameter typeParameter) {
		StringBuilder label = new StringBuilder();

		String name = typeParameter.getName();
		EObject container = typeParameter.eContainer();
		if (container instanceof EClassifier) {
			EClassifier classifier = (EClassifier) container;
			label.append("<a name=\"");
			label.append(classifier.getName());
			label.append("@@");
			label.append(name);
			label.append("\">");
			label.append(name);
			label.append("</a>");
		} else {
			label.append(name);
		}

		if (!typeParameter.getEBounds().isEmpty()) {
			label.append(" extends ");
			for (Iterator<EGenericType> j = typeParameter.getEBounds().iterator(); j.hasNext();) {
				EGenericType bound = j.next();
				label.append(computeLabel(bound));
				if (j.hasNext()) {
					label.append(" &amp; ");
				}
			}
		}
		return label.toString();
	}

	protected String computeLabel(EGenericType genericType) {
		EObject container = genericType.eContainer();
		EClassifier rawType = genericType.getERawType();
		ViewAction rawTypeViewAction = EObjectAdaptable.adaptTo(rawType, ViewAction.class);
		if (container == null || !container.eIsSet(genericType.eContainingFeature())) {
			return rawTypeViewAction == null ? "" : rawTypeViewAction.getText();
		} else {
			StringBuilder label = new StringBuilder();
			if (genericType.getEClassifier() != null) {
				label.append(rawTypeViewAction.getText());

				if (!genericType.getETypeArguments().isEmpty()) {
					label.append("&lt;");
					for (Iterator<EGenericType> i = genericType.getETypeArguments().iterator(); i.hasNext();) {
						EGenericType typeArgument = i.next();
						label.append(computeLabel(typeArgument));
						if (i.hasNext()) {
							label.append(", ");
						}
					}
					label.append("&gt;");
				}
			} else {
				ETypeParameter typeParameter = genericType.getETypeParameter();
				String name = typeParameter != null ? typeParameter.getName() : "?";
				label.append(name);

				if (genericType.getELowerBound() != null) {
					label.append(" super ");
					label.append(computeLabel(genericType.getELowerBound()));
				} else if (genericType.getEUpperBound() != null) {
					label.append(" extends ");
					label.append(computeLabel(genericType.getEUpperBound()));
				}
			}
			return label.toString();
		}
	}

}
