package org.nasdanika.html.ecore;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.binary.Hex;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Context;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Text;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;

public class EModelElementActionSupplier<T extends EModelElement> extends EObjectActionSupplier<T> {
	
	public static final String ICONS_BASE = "https://www.nasdanika.org/resources/images/ecore/";
		
	/**
	 * Descriptions shorter than this value are put on the top of the tabs, longer
	 * ones end up in their own tab. 
	 */
	protected int descriptionTabLengthThreshold = 2500;

	protected Context context;

	protected java.util.function.Function<EPackage,String> ePackagePathComputer; 
		
	public EModelElementActionSupplier(T value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value);		
		this.context = context;
		this.ePackagePathComputer = ePackagePathComputer;
	}
	

	@Override
	public Action execute(ProgressMonitor progressMonitor) throws Exception {		
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
		ret.setIcon(ICONS_BASE+eObject.eClass().getName()+".gif");
		
		header(ret, progressMonitor);
		
		String markdown = EObjectAdaptable.getResourceContext(eObject).getString("documentation", EcoreUtil.getDocumentation(eObject));
		if (Util.isBlank(markdown)) {
			markdown = EmfUtil.getDocumentation(eObject);
		}
		
		if (!Util.isBlank(markdown)) {
			ret.getContent().add(interpolatedMarkdown(markdown));
			ret.setTooltip(context.computingContext().get(MarkdownHelper.class, MarkdownHelper.INSTANCE).firstPlainTextSentence(markdown));
		}
		
		return ret;
	}
	
	/**
	 * Content before documentation.
	 * @param action
	 * @param progressMonitor
	 * @throws Exception
	 */
	protected void header(Action action, ProgressMonitor progressMonitor) throws Exception {}

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
	protected static Markdown interpolatedMarkdown(String markdown) {
		Markdown ret = ContentFactory.eINSTANCE.createMarkdown();
		Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();
		Text text = ContentFactory.eINSTANCE.createText();
		text.setContent(markdown);
		interpolator.setSource(text);
		ret.setSource(interpolator);
		ret.setStyle(true);
		return ret;
	}
	
	protected String getEModelElementFirstDocSentence(EModelElement modelElement) {
		String markdown = EObjectAdaptable.getResourceContext(modelElement).getString("documentation", EcoreUtil.getDocumentation(modelElement));
		if (Util.isBlank(markdown)) {
			markdown = EmfUtil.getDocumentation(modelElement);
		}
		if (Util.isBlank(markdown)) {
			return null;
		}
		String ret = context.computingContext().get(MarkdownHelper.class, MarkdownHelper.INSTANCE).firstPlainTextSentence(markdown);
		return String.join(" ", ret.split("\\R")); // Replacing new lines, shall they be in the first sentence, with spaces.		
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
			acit = resourceSet == null ? eResource.getAllContents() : resourceSet.getAllContents();
		}
		Set<EClass> ret = new HashSet<>();
		acit.forEachRemaining(obj -> {
			if (obj instanceof EReference && ((EReference) obj).getEReferenceType() == eClass) {
				ret.add(((EReference) obj).getEContainingClass());
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

	protected String computeLabel(EGenericType genericType, ProgressMonitor monitor) throws Exception {
		EObject container = genericType.eContainer();
		EClassifier rawType = genericType.getERawType();
		String rawTypeText = rawType.getName(); // rawTypeViewActionSupplierFactory == null ? rawType.getName() : rawTypeViewActionSupplierFactory.create(context).execute(monitor).getText();
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
			String name = typeParameter != null ? typeParameter.getName() : "?";
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
		StringBuilder ret = new StringBuilder(typeParameter.getName());
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
		if (eGenericType.getETypeParameter() != null) {
			ret.append(eGenericType.getETypeParameter().getName());
		} else if (eGenericType.getEClassifier() != null) {
			ret.append(eGenericType.getEClassifier().getName());			
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
	 * @throws Exception 
	 */
	protected void genericType(EGenericType eGenericType, List<Object> accumulator, ProgressMonitor monitor) throws Exception {
		if (eGenericType == null) {
			accumulator.add("void");
		} else if (eGenericType.getETypeParameter() != null) {
			accumulator.add(eGenericType.getETypeParameter().getName());
		} else if (eGenericType.getEClassifier() != null) {
			accumulator.add(link(eGenericType.getEClassifier()));
			genericTypeArguments(eGenericType, accumulator, monitor);
		} else {
			accumulator.add('?');
			if (eGenericType.getELowerBound() != null) {
				accumulator.add(" super ");
				genericType(eGenericType.getELowerBound(), accumulator, monitor);
			} else if (eGenericType.getEUpperBound() != null) {
				accumulator.add(" extends ");
				genericType(eGenericType.getEUpperBound(), accumulator, monitor);
			}
		}
	}
	
	/**
	 * @param eClassifier
	 * @return Relatieve path to the argument {@link EClassifier} or null if the classifier is not part of the documentation resource set.
	 */
	protected String path(EClassifier eClassifier) {
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
		EClassifier contextClassifier = null;
		if (eObject instanceof EClassifier) {
			contextClassifier = (EClassifier) eObject;
		} else if (eObject.eContainer() instanceof EClassifier) {
			contextClassifier = (EClassifier) eObject.eContainer();
		} else if (eObject.eContainer() instanceof EOperation) {
			contextClassifier = (EClassifier) eObject.eContainer().eContainer();
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
	protected String link(EClassifier eClassifier) {
		String path = path(eClassifier);
		return Util.isBlank(path) ? eClassifier.getName() : "<a href=\"" + path + "\">" + eClassifier.getName() + "</a>";
	}

	protected void genericTypeArguments(EGenericType eGenericType, List<Object> accumulator, ProgressMonitor monitor) throws Exception {
		Iterator<EGenericType> it = eGenericType.getETypeArguments().iterator();
		if (it.hasNext()) {
			accumulator.add("&lt;");
			while (it.hasNext()) {
				genericType(it.next(), accumulator, monitor);
				if (it.hasNext()) {
					accumulator.add(",");
				}
			}
			accumulator.add("&gt;");
		}
	}
	
	/**
	 * Encodes ePackage path.
	 * @param ePackage
	 * @return
	 */
	public String encodeEPackage(EPackage ePackage) {
		String ret = null;
				
		for (EPackage p = ePackage; p != null; p = p.getESuperPackage()) {
			String segment = ePackagePathComputer == null ? Hex.encodeHexString(p.getNsURI().getBytes(StandardCharsets.UTF_8)) : ePackagePathComputer.apply(p);
			if (ret == null) {
				ret = segment;
			} else {
				ret = segment + "/" + ret;
			}
		}
		
		return ret;
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
	
}
