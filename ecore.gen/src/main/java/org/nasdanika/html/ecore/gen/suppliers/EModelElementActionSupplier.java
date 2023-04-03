package org.nasdanika.html.ecore.gen.suppliers;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Hex;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertyComputer;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.emf.EmfUtil.EModelElementDocumentation;
import org.nasdanika.emf.persistence.MarkerFactory;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Text;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.util.ActionSupplier;
import org.nasdanika.ncore.util.NcoreUtil; 

public class EModelElementActionSupplier<T extends EModelElement> implements EcoreActionSupplier {
		
	protected BiFunction<ENamedElement, String, String> labelProvider;	
	
	protected Comparator<ENamedElement> eNamedElementComparator = (a,b) -> labelProvider.apply(a, a.getName()).compareTo(labelProvider.apply(b, b.getName()));
	
	public static final String ICONS_BASE = "https://www.nasdanika.org/resources/images/ecore/";
		
	/**
	 * Descriptions shorter than this value are put on the top of the tabs, longer
	 * ones end up in their own tab. 
	 */
	protected int descriptionTabLengthThreshold = 2500;

	protected Context context;

	protected java.util.function.Function<EPackage,String> ePackagePathComputer;
	protected Predicate<EModelElement> elementPredicate;
	
	protected T eObject;

	/**
	 * Adapts child eObject to {@link ActionSupplier} and adds to the list of children to be configured.
	 * @param child
	 * @return
	 */
	protected EcoreActionSupplier adaptChild(EObject child) {
		return EObjectAdaptable.adaptTo(child, EcoreActionSupplier.class);
	}	
		
	public EModelElementActionSupplier(
			T value, 
			Context context, 
			java.util.function.Function<EPackage,String> ePackagePathComputer,
			Predicate<EModelElement> elementPredicate,
			BiFunction<ENamedElement, String, String> labelProvider) {
		this.eObject = value;
		this.context = context.fork();
		PropertyComputer eClassifierPropertyComputer = new PropertyComputer() {
			
			@SuppressWarnings("unchecked")
			@Override
			public <P> P compute(Context context, String key, String path, Class<P> type) {
				Resource contextResource = value.eResource();
				EObject contextElement = value;
				while (contextElement != null && !(contextElement instanceof EPackage)) {
					contextElement = contextElement.eContainer();
				}
				
				EClassifier targetClassifier = null;
				int atIdx = path.indexOf('@');
				if (atIdx == -1) {
					if (contextElement == null) {
						return null;
					}
					
					targetClassifier = ((EPackage) contextElement).getEClassifier(path);
				} else {
					if (contextResource == null) {
						return null;
					}
					ResourceSet resourceSet = contextResource.getResourceSet();
					if (resourceSet == null) {
						return null;
					}
					TreeIterator<Notifier> cit = resourceSet.getAllContents();
					String targetNsUri = path.substring(atIdx + 1);
					while (cit.hasNext()) {
						Notifier next = cit.next();
						if (next instanceof EPackage) {
							if (((EPackage) next).getNsURI().equals(targetNsUri)) {
								targetClassifier = ((EPackage) next).getEClassifier(path.substring(0, atIdx));
								break;
							}
						}
					}
				}
				
				if (targetClassifier == null) {
					return null;
				}

				return (P) path(targetClassifier, value instanceof EClassifier ? (EClassifier) value : null);
			}
			
		};
		((MutableContext) this.context).put("classifier", eClassifierPropertyComputer);
		this.ePackagePathComputer = ePackagePathComputer;
		this.elementPredicate = elementPredicate;
		this.labelProvider = labelProvider;
	}

	@Override
	public Action execute(EClass contextClass, ProgressMonitor progressMonitor) {		
		// TODO - refactor to 
//		EObject actionPrototype = NcoreUtil.getNasdanikaAnnotationDetail(eObject, "action-prototype");
//		if (actionPrototype instanceof Action) {
//			return EcoreUtil.copy((Action) actionPrototype);
//		}
//		if (actionPrototype != null) {
//			ActionProvider actionProvider = Objects.requireNonNull((ActionProvider) EcoreUtil.getRegisteredAdapter(actionPrototype, ActionProvider.class), "Cannot adapt " + actionPrototype + " to " + ActionProvider.class);
//			return actionProvider.execute(registry, progressMonitor);
//		}
//		return AppFactory.eINSTANCE.createAction();
		
		
		Action ret = AppFactory.eINSTANCE.createAction();
		ret.setIcon(NcoreUtil.getNasdanikaAnnotationDetail(eObject, "icon", ICONS_BASE+eObject.eClass().getName()+".gif"));
		
		header(ret, progressMonitor);
		
		EModelElementDocumentation documentation = EmfUtil.getDocumentation(eObject); //EObjectAdaptable.getResourceContext(eObject).getString("documentation", EcoreUtil.getDocumentation(eObject));
//		if (Util.isBlank(markdown)) {
//			markdown = EmfUtil.getDocumentation(eObject);
//		}
		
		MarkdownHelper markdownHelper = new MarkdownHelper() {
			
			@Override
			protected URI getResourceBase() {
				return documentation.getLocation();
			}
			
			@Override
			protected DiagramGenerator getDiagramGenerator() {
				return context == null ? super.getDiagramGenerator() : context.get(DiagramGenerator.class, super.getDiagramGenerator()); 
			}
			
		};
		
		if (documentation != null) {
			ret.getContent().add(interpolatedMarkdown(context.interpolateToString(documentation.getDocumentation()), documentation.getLocation(), progressMonitor));			
			String tooltip = NcoreUtil.getNasdanikaAnnotationDetail(eObject, "description", markdownHelper.firstPlainTextSentence(documentation.getDocumentation()));
			ret.setTooltip(tooltip);
		}
		
		return ret;
	}
	
	/**
	 * Content before documentation.
	 * @param action
	 * @param progressMonitor
	 */
	protected void header(Action action, ProgressMonitor progressMonitor) {}

	@Override
	public double size() {
		return 1;
	}

	@Override
	public String name() {
		return eObject.eClass().getName();
	}
	
	/**
	 * @param markdown Markdown text
	 * @return Spec for interpolating markdown and then converting to HTML. 
	 */
	protected Markdown interpolatedMarkdown(String markdown, URI location, ProgressMonitor progressMonitor) {
		if (Util.isBlank(markdown)) {
			return null;
		}
		Markdown ret = ContentFactory.eINSTANCE.createMarkdown();
		Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();
		Text text = ContentFactory.eINSTANCE.createText();
		text.setContent(markdown);
		interpolator.setSource(text);
		ret.setSource(interpolator);
		ret.setStyle(true);
		
		// Creating a marker with EObject resource location for resource resolution in Markdown
		if (location != null) {
			org.nasdanika.ncore.Marker marker = context.get(MarkerFactory.class, MarkerFactory.INSTANCE).createMarker(location.toString(), progressMonitor);
			ret.getMarkers().add(marker); 
		}
		
		return ret;
	}
	
	protected String getEModelElementFirstDocSentence(EModelElement modelElement) {
		EModelElementDocumentation documentation = EmfUtil.getDocumentation(modelElement);
//		String markdown = EObjectAdaptable.getResourceContext(modelElement).getString("documentation", EcoreUtil.getDocumentation(modelElement));
//		if (Util.isBlank(markdown)) {
//			markdown = EmfUtil.getDocumentation(modelElement);
//		}
		if (documentation == null) {
			return null;
		}
		
		MarkdownHelper markdownHelper = new MarkdownHelper() {
			
			@Override
			protected URI getResourceBase() {
				return documentation.getLocation();
			}
			
			@Override
			protected DiagramGenerator getDiagramGenerator() {
				return context == null ? super.getDiagramGenerator() : context.get(DiagramGenerator.class, super.getDiagramGenerator()); 
			}
			
		};
		
		String ret = /* context.computingContext().get(MarkdownHelper.class, markdownHelper) */ markdownHelper.firstPlainTextSentence(documentation.getDocumentation());
		return String.join(" ", ret.split("\\R")); // Replacing new lines, shall they be in the first sentence, with spaces.		
	}

	/**
	 * In situations where classes referencing this class are known this method can be overridden. 
	 * @return
	 */	
	protected Collection<EClass> getReferrers(EClass eClass) {
		return getReferrers(eClass, true);
	}
	
	/**
	 * In situations where classes referencing this class are known this method can be overridden. 
	 * @return
	 */	
	private Collection<EClass> getReferrers(EClass eClass, boolean includeAssociations) {
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
			acit = resourceSet == null ? eResource.getAllContents() : resourceSet.getAllContents();
		}
		Set<EClass> ret = new HashSet<>();
		acit.forEachRemaining(obj -> {
			if (obj instanceof EReference && ((EReference) obj).getEReferenceType() == eClass) {
				EClass referrer = ((EReference) obj).getEContainingClass();
				if (includeAssociations) {
					for (EClass superReferrer: getReferrers(referrer, false)) {
						for (EReference superReference: superReferrer.getEReferences()) {
							if (superReference.getEReferenceType() == referrer) {
								EClass associationTarget = NcoreUtil.getAssociationTarget(superReference);
								if (associationTarget == eClass) {
									ret.add(superReferrer);
								}
							}
						}
					}
				}
				ret.add(referrer);
			}
		});
		return ret;
	}
	
	/**
	 * Finds all type uses in the resourceset. 
	 * @return
	 */
	protected Collection<EClass> getUses(EClassifier eClassifier) {
		TreeIterator<?> acit;
		Resource eResource = eClassifier.eResource();
		if (eResource == null) {
			EPackage ePackage = eClassifier.getEPackage();
			if (ePackage == null) {
				return Collections.emptySet();
			}
			acit = ePackage.eAllContents();
		} else {
			ResourceSet resourceSet = eResource.getResourceSet();
			acit = resourceSet == null ? eResource.getAllContents() : resourceSet.getAllContents();
		}
		Set<EClass> ret = new HashSet<>();
		acit.forEachRemaining(obj -> {
			if (obj instanceof EClass && (org.nasdanika.emf.EmfUtil.collectTypeDependencies((EClass) obj).contains(eClassifier))) {
				ret.add((EClass) obj);
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

	protected String computeLabel(EGenericType genericType, ProgressMonitor monitor) {
		EObject container = genericType.eContainer();
		EClassifier rawType = genericType.getERawType();
		String rawTypeText = labelProvider.apply(rawType, rawType.getName()); // rawTypeViewActionSupplierFactory == null ? rawType.getName() : rawTypeViewActionSupplierFactory.create(context).execute(monitor).getText();
		if (container == null || !container.eIsSet(genericType.eContainingFeature())) {
			return rawTypeText;
		}
		
		StringBuilder label = new StringBuilder();
		if (genericType.getEClassifier() != null) {
			label.append(rawTypeText);

			if (!genericType.getETypeArguments().isEmpty()) {
				label.append("&lt;");
				for (Iterator<EGenericType> i = genericType.getETypeArguments().iterator(); i.hasNext();) {
					EGenericType typeArgument = i.next();
					label.append(computeLabel(typeArgument, monitor));
					if (i.hasNext()) {
						label.append(", ");
					}
				}
				label.append("&gt;");
			}
		} else {
			ETypeParameter typeParameter = genericType.getETypeParameter();
			String name = typeParameter != null ? labelProvider.apply(typeParameter, typeParameter.getName()) : "?";
			label.append(name);

			if (genericType.getELowerBound() != null) {
				label.append(" super ");
				label.append(computeLabel(genericType.getELowerBound(),  monitor));
			} else if (genericType.getEUpperBound() != null) {
				label.append(" extends ");
				label.append(computeLabel(genericType.getEUpperBound(), monitor));
			}
		}
		return label.toString();
	}
	
	// --- Generics ---
	
	/**
	 * @param eClassifier
	 * @return Type parameters string.
	 */
	protected String typeParameters(EClassifier eClassifier) {
		if (eClassifier.getETypeParameters().isEmpty()) {
			return "";
		}
		StringBuilder typeParameters = new StringBuilder();
		for (ETypeParameter typeParameter: eClassifier.getETypeParameters()) {
			if (typeParameters.length() > 0) {
				typeParameters.append(",");
			}
			typeParameters.append(genericName(typeParameter));
		}		
		
		return "&lt;" + typeParameters +"&gt;";
	}	
	
	protected String genericName(ETypeParameter typeParameter) {
		StringBuilder ret = new StringBuilder(labelProvider.apply(typeParameter, typeParameter.getName()));
		for (EGenericType bound : typeParameter.getEBounds()) {
			if (bound.getEUpperBound() != null) {
				ret.append(" extends ").append(genericName(bound.getEUpperBound()));
			}
			if (bound.getELowerBound() != null) {
				ret.append(" super ").append(genericName(bound.getELowerBound()));
			}
		}
		
		return ret.toString();
	}
	
	protected String genericName(EGenericType eGenericType) {
		StringBuilder ret = new StringBuilder();
		ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
		if (eTypeParameter != null) {			
			ret.append(labelProvider.apply(eTypeParameter, eTypeParameter.getName()));
		} else {
			EClassifier eClassifier = eGenericType.getEClassifier();
			if (eClassifier != null) {
				ret.append(labelProvider.apply(eClassifier, eClassifier.getName()));			
			}
		}
		ret.append(genericTypeArguments(eGenericType));
		return ret.toString();
	}

	protected String genericTypeArguments(EGenericType eGenericType) {
		StringBuilder ret = new StringBuilder();
		Iterator<EGenericType> it = eGenericType.getETypeArguments().iterator();
		if (it.hasNext()) {
			ret.append("<");
			while (it.hasNext()) {
				ret.append(genericName(it.next()));
				if (it.hasNext()) {
					ret.append(",");
				}
			}
			ret.append(">");
		}
		return ret.toString();
	}

	/**
	 * Generates generic type text with links to classifiers.
	 * @param eGenericType
	 * @param accumulator 
	 */
	protected void genericType(EGenericType eGenericType, EClassifier contextClassifier, Consumer<String> accumulator, ProgressMonitor monitor) {
		if (eGenericType == null) {
			accumulator.accept("void");
		} else {
			ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
			if (eTypeParameter != null) {
				accumulator.accept(labelProvider.apply(eTypeParameter, eTypeParameter.getName()));
			} else if (eGenericType.getEClassifier() != null) {
				accumulator.accept(link(eGenericType.getEClassifier(), contextClassifier));
				genericTypeArguments(eGenericType, contextClassifier, accumulator, monitor);
			} else {
				accumulator.accept("?");
				if (eGenericType.getELowerBound() != null) {
					accumulator.accept(" super ");
					genericType(eGenericType.getELowerBound(), contextClassifier, accumulator, monitor);
				} else if (eGenericType.getEUpperBound() != null) {
					accumulator.accept(" extends ");
					genericType(eGenericType.getEUpperBound(), contextClassifier, accumulator, monitor);
				}
			}
		}
	}
	
	/**
	 * @param eClassifier
	 * @return Relative path to the argument {@link EClassifier} or null if the classifier is not part of the documentation resource set.
	 */
	protected String path(EClassifier eClassifier, EClassifier contextClassifier) {
		if (!elementPredicate.test(eClassifier)) {
			return null;
		}
		// TODO - resolution of external eClassifiers for federated/hierarchical documentation - from the adapter factory.
		Resource targetResource = eClassifier.eResource();
		if (targetResource == null) {
			return null;
		}
		ResourceSet targetResourceSet = targetResource.getResourceSet();
		if (targetResourceSet != eObject.eResource().getResourceSet()) {
			return null;
		}		

		String targetEPackagePath = encodeEPackage(eClassifier.getEPackage());
		if (Util.isBlank(targetEPackagePath)) {
			return null;
		}
		
		String targetPath = targetEPackagePath + "/" + eClassifier.getName() + ".html";
		String thisPath = null;
		if (contextClassifier == null) {
			if (eObject instanceof EClassifier) {
				contextClassifier = (EClassifier) eObject;
			} else if (eObject.eContainer() instanceof EClassifier) {
				contextClassifier = (EClassifier) eObject.eContainer();
			} else if (eObject.eContainer() instanceof EOperation) {
				contextClassifier = (EClassifier) eObject.eContainer().eContainer();
			} 
		}
			
		if (contextClassifier != null) {	
			thisPath = encodeEPackage(contextClassifier.getEPackage()) + "/" + contextClassifier.getName() + ".html";
		} else if (eObject instanceof EPackage) {
			thisPath = encodeEPackage(((EPackage) eObject)) + "/package-summary.html";					
		}
		
		if (thisPath == null) {
			return null;
		}
		
		URI base = URI.createURI(context.getString(Context.BASE_URI_PROPERTY, "tmp://base/doc/"));
		URI target = URI.createURI(targetPath).resolve(base);
		URI source = URI.createURI(thisPath).resolve(base);
		URI relativeTarget = target.deresolve(source, true, true, true);
		return relativeTarget.toString();		
	}
	
	/**
	 * @return Link to {@link EClassifier} if it is part of the doc or plain text if it is not.
	 */
	protected String link(EClassifier eClassifier, EClassifier contextClassifier) {
		String path = path(eClassifier, contextClassifier);
		String label = labelProvider.apply(eClassifier, eClassifier.getName());
		return Util.isBlank(path) ? label : "<a href=\"" + path + "\">" + label + "</a>";
	}
	
	/**
	 * @return Link to {@link EClassifier} if it is part of the doc or plain text if it is not.
	 */
	protected String link(EStructuralFeature feature, EClassifier contextClassifier) {
		String path = path(feature.getEContainingClass(), contextClassifier);
		String fragment = "#" + feature.eClass().getName() + "-" + feature.getName();
		path = Util.isBlank(path) ? fragment : path + fragment;
		return  "<a href=\"" + path + "\">" + labelProvider.apply(feature, feature.getName()) + "</a>";
	}

	protected void genericTypeArguments(EGenericType eGenericType, EClassifier contextClassifier, Consumer<String> accumulator, ProgressMonitor monitor) {
		Iterator<EGenericType> it = eGenericType.getETypeArguments().iterator();
		if (it.hasNext()) {
			accumulator.accept("&lt;");
			while (it.hasNext()) {
				genericType(it.next(), contextClassifier, accumulator, monitor);
				if (it.hasNext()) {
					accumulator.accept(",");
				}
			}
			accumulator.accept("&gt;");
		}
	}
	
	/**
	 * Encodes ePackage path.
	 * @param ePackage
	 * @return
	 */
	public String encodeEPackage(EPackage ePackage) {
		return ePackagePathComputer == null ? Hex.encodeHexString(ePackage.getNsURI().getBytes(StandardCharsets.UTF_8)) : ePackagePathComputer.apply(ePackage);
//
//		
//		String ret = null;
//				
//		for (EPackage p = ePackage; p != null; p = p.getESuperPackage()) {
//			String segment = ePackagePathComputer == null ? Hex.encodeHexString(p.getNsURI().getBytes(StandardCharsets.UTF_8)) : ePackagePathComputer.apply(p);
//			if (ret == null) {
//				ret = segment;
//			} else {
//				ret = segment + "/" + ret;
//			}
//		}
//		
//		return ret;
	}
	
	/**
	 * Adds textual content.
	 * @param content
	 */
	protected static void addContent(Action action, String content) {
		Text text = ContentFactory.eINSTANCE.createText();
		text.setContent(content);
		action.getContent().add(text);
	}
	
	/**
	 * Filters the collection retaining only model elements which shall be documented.
	 * @param <M>
	 * @param elements
	 * @return
	 */
	protected <M extends EModelElement> List<M> retainDocumentable(Collection<M> elements) {
		return elements.stream().filter(elementPredicate).collect(Collectors.toList());
	}

	protected static Class<?> getInstanceClass(EClassifier eClassifier, java.util.function.Function<String, Object> ePackageResolver) {
		Class<?> instanceClass = eClassifier.getInstanceClass();
		if (instanceClass == null) {
			EPackage registeredPackage = getRegisteredPackage(eClassifier, ePackageResolver);
			if (registeredPackage != null) {
				EClassifier registeredClassifier = registeredPackage.getEClassifier(eClassifier.getName());
				if (registeredClassifier != null) {
					instanceClass = registeredClassifier.getInstanceClass();
				}
			}
		}
		return instanceClass;
	}
	
	private static EPackage getRegisteredPackage(EClassifier eObject, java.util.function.Function<String, Object> ePackageResolver) {
		String nsURI = eObject.getEPackage().getNsURI();
		Object value = ePackageResolver.apply(nsURI);
		if (value instanceof EPackage) {
			return (EPackage) value;
		}
		if (value instanceof EPackage.Descriptor) {
			return Objects.requireNonNull(((EPackage.Descriptor) value).getEPackage(), "EPackage is null for " + nsURI);  
		}
		
		if (value instanceof EPackage) {
			return (EPackage) value;
		}
		return null;
	}	
	
	
}
