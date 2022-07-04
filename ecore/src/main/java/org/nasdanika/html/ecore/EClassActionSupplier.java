package org.nasdanika.html.ecore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.emf.DiagramTextGenerator;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.emf.EmfUtil.EModelElementDocumentation;
import org.nasdanika.emf.MermaidTextGenerator;
import org.nasdanika.emf.PlantUmlTextGenerator;
import org.nasdanika.emf.DiagramTextGenerator.RelationshipDirection;
import org.nasdanika.emf.persistence.EObjectLoader;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.SectionStyle;
import org.nasdanika.ncore.util.NcoreUtil;

public class EClassActionSupplier extends EClassifierActionSupplier<EClass> {
	
	private BooleanSupplier isGenerateLoadSpecification;
	private Supplier<String> diagramDialectSupplier;

	public EClassActionSupplier(
			EClass value, 
			Context context, 
			java.util.function.Function<EPackage,String> ePackagePathComputer,
			java.util.function.Function<String, String> javadocResolver,
			BooleanSupplier isGenerateLoadSpecification,
			Supplier<String> diagramDialectSupplier) {
		super(value, context, ePackagePathComputer, javadocResolver);
		this.isGenerateLoadSpecification = isGenerateLoadSpecification;
		this.diagramDialectSupplier = diagramDialectSupplier;
	}
	
	@Override
	public org.nasdanika.html.model.app.Action execute(EClass contextEClass, ProgressMonitor progressMonitor) throws Exception {
		Action action = super.execute(contextEClass, progressMonitor);
		
		action.setSectionStyle(SectionStyle.HEADER);
		
		// Diagram
		String diagramMode = NcoreUtil.getNasdanikaAnnotationDetail(eObject, "diagram", "navigation");
		String diagram = generateDiagram(1, RelationshipDirection.both, true, true, progressMonitor);
		if (!Util.isBlank(diagram)) {
			switch (diagramMode) {
			case "content":
				addContent(action, diagram);
				break;
			case "none":
				break;
			case "navigation": {
				Action diagramAction = AppFactory.eINSTANCE.createAction();
				action.getNavigation().add(diagramAction);
				diagramAction.setText("Diagram");
				diagramAction.setIcon("fas fa-project-diagram");
				diagramAction.setLocation(eObject.getName() + "-diagram.html");
				addContent(diagramAction, diagram);
				break;
			}
			case "anonymous": {
				Action diagramAction = AppFactory.eINSTANCE.createAction();
				action.getAnonymous().add(diagramAction);
				diagramAction.setText("Diagram");
				diagramAction.setIcon("fas fa-project-diagram");
				diagramAction.setLocation(eObject.getName() + "-diagram.html");
				addContent(diagramAction, diagram);
				break;			
			}
			default:
				throw new IllegalArgumentException("Unsupported diagram annotation value '" + diagramMode +"' on EClass " + eObject); 			
			}
		}

		// Generic supertypes
		EList<EGenericType> eGenericSuperTypes = eObject.getEGenericSuperTypes();
		if (!eGenericSuperTypes.isEmpty()) {
			HTMLFactory htmlFactory = context.get(HTMLFactory.class);
			Fragment gstf = htmlFactory.fragment(TagName.a.create(TagName.h3.create("Supertypes")).attribute("name", "supertypes"));

			Tag list = TagName.ul.create();
			gstf.content(list);
			
			for (EGenericType superType: eGenericSuperTypes) {
				Tag listItem = TagName.li.create();
				list.content(listItem);
				genericType(superType, eObject, listItem.getContent(), progressMonitor);
			}
			addContent(action, gstf.toString());
		}
		
		// Subtypes
		Collection<EClass> eSubTypes = getSubTypes(eObject).stream().sorted((a,b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());
		if (!eSubTypes.isEmpty()) {
			HTMLFactory htmlFactory = context.get(HTMLFactory.class);
			Fragment gstf = htmlFactory.fragment(TagName.a.create(TagName.h3.create("Subtypes")).attribute("name", "subtypes"));

			Tag list = TagName.ul.create();
			gstf.content(list);
			
			for (EClass subType: eSubTypes) {
				list.content(TagName.li.create(link(subType, eObject)));
			}
			addContent(action, gstf.toString());
		}
		
		// Referrers
		Collection<EClass> referrers = getReferrers().stream().sorted((a,b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());
		if (!referrers.isEmpty()) {
			HTMLFactory htmlFactory = context.get(HTMLFactory.class);
			Fragment gstf = htmlFactory.fragment(TagName.a.create(TagName.h3.create("Referrers")).attribute("name", "referrers"));

			Tag list = TagName.ul.create();
			gstf.content(list);
			
			for (EClass referrer: referrers) {
				list.content(TagName.li.create(link(referrer, eObject)));
			}
			addContent(action, gstf.toString());
		}
		
		// Uses
		Collection<EClass> uses = getUses().stream().sorted((a,b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());
		if (!uses.isEmpty()) {
			HTMLFactory htmlFactory = context.get(HTMLFactory.class);
			Fragment gstf = htmlFactory.fragment(TagName.a.create(TagName.h3.create("Uses")).attribute("name", "uses"));

			Tag list = TagName.ul.create();
			gstf.content(list);
			
			for (EClass use: uses) {
				list.content(TagName.li.create(link(use, eObject)));
			}
			addContent(action, gstf.toString());
		}
		
		Comparator<ENamedElement> namedElementComparator = (a,b) -> a.getName().compareTo(b.getName());
		
		List<EAttribute> allAttributes = eObject.getEAllAttributes().stream().sorted((a,b) ->  a.getName().compareTo(b.getName())).collect(Collectors.toList());
		List<EReference> allReferences = eObject.getEAllReferences().stream().sorted((a,b) ->  a.getName().compareTo(b.getName())).collect(Collectors.toList());
		List<EOperation> allOperations = eObject.getEAllOperations().stream().sorted((a,b) ->  a.getName().compareTo(b.getName())).collect(Collectors.toList());		
		EList<EGenericType> allGenericSupertypes = eObject.getEAllGenericSuperTypes();
		
		if (allAttributes.size() + allReferences.size() + allOperations.size() + allGenericSupertypes.size()  != 0) { 	
			Action allGroup = AppFactory.eINSTANCE.createAction();
			allGroup.setText("All");
			allGroup.setUuid(action.getUuid() + "-all");
			action.getNavigation().add(allGroup);
			
			generateAllAttributes(allAttributes, allGroup, progressMonitor);
			generateAllReferences(allReferences, allGroup, progressMonitor);
			generateAllOperations(allOperations, allGroup, progressMonitor);			
			generateAllGenericSupertypes(allGenericSupertypes, allGroup, progressMonitor);			
		}
	
		// No load specification for EMap entries.
		if (isGenerateLoadSpecification.getAsBoolean() && Map.Entry.class != instanceClass) { 
			generateLoadSpecification(action, namedElementComparator, progressMonitor);
		}
		
//		TODO - Table (list) of contents
//		Map<String, Object> locConfig = new LinkedHashMap<>();
//		locConfig.put("tooltip", true);
//		locConfig.put("header", "Members");		
//		locConfig.put("role", Action.Role.SECTION);		
//		addContent(data, Collections.singletonMap("component-list-of-contents", locConfig));
		
		EList<Action> sections = action.getSections();
		if (!eObject.getEAttributes().isEmpty()) {
			Action attributesCategory = AppFactory.eINSTANCE.createAction();
			attributesCategory.setText("Attributes");
			attributesCategory.setName("attributes");
			attributesCategory.setSectionStyle(SectionStyle.HEADER);
			sections.add(attributesCategory);
			EList<Action> attributes = attributesCategory.getSections();
			for (EStructuralFeature sf: eObject.getEAttributes().stream().sorted((a,b) ->  a.getName().compareTo(b.getName())).collect(Collectors.toList())) {
				attributes.add(adaptChild(sf).execute(null, progressMonitor));
			}
		}
		
		if (!eObject.getEReferences().isEmpty()) {
			Action referencesCategory = AppFactory.eINSTANCE.createAction();
			referencesCategory.setText("References");
			referencesCategory.setName("references");
			referencesCategory.setSectionStyle(SectionStyle.HEADER);
			sections.add(referencesCategory);
			EList<Action> references = referencesCategory.getSections();			
			for (EStructuralFeature sf: eObject.getEReferences().stream().sorted((a,b) ->  a.getName().compareTo(b.getName())).collect(Collectors.toList())) {
				references.add(adaptChild(sf).execute(null, progressMonitor));
			}
		}
		
		if (!eObject.getEOperations().isEmpty()) {
			Action operationsCategory = AppFactory.eINSTANCE.createAction();
			operationsCategory.setText("Operations");
			operationsCategory.setName("operations");
			operationsCategory.setSectionStyle(SectionStyle.HEADER);
			sections.add(operationsCategory);
			EList<Action> operations = operationsCategory.getSections();			
			for (EOperation eOp: eObject.getEOperations().stream().sorted((a,b) ->  a.getName().compareTo(b.getName())).collect(Collectors.toList())) {
				operations.add(adaptChild(eOp).execute(null, progressMonitor));			
			}
		}
		
		if (eObject.isInterface()) {
			action.setIcon(ICONS_BASE + "EInterface.gif");			
		}		
		
		return action;
	}

	private void generateAllGenericSupertypes(List<EGenericType> allGenericSupertypes, Action allGroup, ProgressMonitor progressMonitor) throws Exception {		
		if (!allGenericSupertypes.isEmpty()) {
			String inheritanceDiagram = generateInheritanceDiagram(0, RelationshipDirection.both, true, true, progressMonitor);
			if (!Util.isBlank(inheritanceDiagram)) {
				Action allSupertypesAction = AppFactory.eINSTANCE.createAction();
				allSupertypesAction.setText("Supertypes");
				allSupertypesAction.setLocation(eObject.getName() + "-all-supertypes.html");
				allSupertypesAction.setSectionStyle(SectionStyle.HEADER);
				allGroup.getChildren().add(allSupertypesAction);
				
				Tag list = TagName.ul.create();
				
				for (EGenericType superType: allGenericSupertypes) {
					Tag listItem = TagName.li.create();
					list.content(listItem);
					genericType(superType, eObject, listItem.getContent(), progressMonitor);
				}
				addContent(allSupertypesAction, list.toString());			
				addContent(allSupertypesAction, inheritanceDiagram);
			}			
		}
	}
	
	protected String generateInheritanceDiagram(
			int depth, 
			RelationshipDirection relationshipDirection,
			boolean appendAttributes,
			boolean appendOperations,
			ProgressMonitor monitor) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		DiagramTextGenerator gen = getDiagramTextGenerator(sb, appendAttributes, appendOperations);
		if (gen == null) {
			return null;
		}
		List<EClass> diagramElements = new ArrayList<>();
		diagramElements.add(eObject);
		diagramElements.addAll(eObject.getEAllSuperTypes());
		gen.appendWithRelationships(diagramElements, relationshipDirection, depth);
		
		return context.get(DiagramGenerator.class).generateUmlDiagram(sb.toString());
	}
	
	
	private void generateAllOperations(List<EOperation> allOperations, Action allGroup, ProgressMonitor progressMonitor) throws Exception {		
		if (!allOperations.isEmpty()) {
			Action allOperationsAction = AppFactory.eINSTANCE.createAction();
			allOperationsAction.setText("Operations");
			allOperationsAction.setLocation(eObject.getName() + "-all-operations.html");
			allOperationsAction.setSectionStyle(SectionStyle.HEADER);
			allGroup.getChildren().add(allOperationsAction);
			
			EList<Action> operations = allOperationsAction.getSections();			
			for (EOperation eOp: allOperations) {
				operations.add(adaptChild(eOp).execute(eObject, progressMonitor));			
			}
		}
	}

	private void generateAllReferences(List<EReference> allReferences,	Action allGroup, ProgressMonitor progressMonitor) throws Exception {		
		if (!allReferences.isEmpty()) {
			Action allReferencesAction = AppFactory.eINSTANCE.createAction();
			allReferencesAction.setText("References");
			allReferencesAction.setLocation(eObject.getName() + "-all-references.html");
			allReferencesAction.setSectionStyle(SectionStyle.HEADER);
			allGroup.getChildren().add(allReferencesAction);
			
			EList<Action> references = allReferencesAction.getSections();			
			for (EStructuralFeature sf: allReferences) {
				references.add(adaptChild(sf).execute(eObject, progressMonitor));
			}
		}
	}

	private void generateAllAttributes(List<EAttribute> allAttributes, Action allGroup,	ProgressMonitor progressMonitor) throws Exception {		
		if (!allAttributes.isEmpty()) {
			Action allAttributesAction = AppFactory.eINSTANCE.createAction();
			allAttributesAction.setText("Attributes");
			allAttributesAction.setLocation(eObject.getName() + "-all-attributes.html");
			allAttributesAction.setSectionStyle(SectionStyle.HEADER);
			allGroup.getChildren().add(allAttributesAction);
			
			EList<Action> attributes = allAttributesAction.getSections();
			for (EStructuralFeature sf: allAttributes) {
				attributes.add(adaptChild(sf).execute(eObject, progressMonitor));
			}
		}
	}

	private void generateLoadSpecification(
			Action action, 
			Comparator<ENamedElement> namedElementComparator,
			ProgressMonitor progressMonitor) throws Exception {
		
		// Load specification
		if (!eObject.isAbstract() && "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(eObject, EObjectLoader.IS_LOADABLE, "true"))) {
			Action loadSpecificationAction = AppFactory.eINSTANCE.createAction();
			loadSpecificationAction.setText("Load specification");
			loadSpecificationAction.setLocation(eObject.getName() + "-load-specification.html");			
			action.getNavigation().add(loadSpecificationAction);
			
			EModelElementDocumentation loadDoc = EmfUtil.getLoadDocumentation(eObject);
			if (loadDoc != null) {
				loadSpecificationAction.getContent().add(interpolatedMarkdown(loadDoc.getDocumentation(), loadDoc.getLocation(), progressMonitor));
			}
			
			Tag toc = TagName.ul.create();
			
			Predicate<EStructuralFeature> predicate = sf -> sf.isChangeable() && "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(sf, EObjectLoader.IS_LOADABLE, "true"));
			for (EStructuralFeature sf: eObject.getEAllStructuralFeatures().stream().filter(predicate).sorted(namedElementComparator).collect(Collectors.toList())) {
				Action featureAction = AppFactory.eINSTANCE.createAction();
				String key = NcoreUtil.getNasdanikaAnnotationDetail(sf, EObjectLoader.LOAD_KEY, NcoreUtil.getFeatureKey(eObject, sf));
				featureAction.setText(key);
				String sectionAnchor = "key-section-" + key;
				
				featureAction.setName(sectionAnchor);			
				loadSpecificationAction.getSections().add(featureAction);

				// Properties table
				Table table = context.get(BootstrapFactory.class).table();
				table.toHTMLElement().style().width("auto");
				
				genericType(sf.getEGenericType(), eObject, ETypedElementActionSupplier.addRow(table, "Type"), progressMonitor);
				
				boolean isDefaultFeature = EObjectLoader.isDefaultFeature(eObject, sf);
				if (isDefaultFeature) {
					ETypedElementActionSupplier.addRow(table, "Default").add("true");				
				}
				toc.accept(TagName.li.create(TagName.a.create(key).attribute("href", "#" + sectionAnchor).attribute("style", "font-weight:bold", isDefaultFeature)));
				
				boolean isHomogenous = "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(sf, EObjectLoader.IS_HOMOGENOUS)) || NcoreUtil.getNasdanikaAnnotationDetail(sf, EObjectLoader.REFERENCE_TYPE) != null;
				if (isHomogenous) {
					ETypedElementActionSupplier.addRow(table, "Homogenous").add("true");									
				}
				
				boolean isStrictContainment = isHomogenous && "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(sf, EObjectLoader.IS_STRICT_CONTAINMENT));			
				if (isStrictContainment) {
					ETypedElementActionSupplier.addRow(table, "Strict containment").add("true");									
				}
				
				Object[] exclusiveWith = EObjectLoader.getExclusiveWith(eObject, sf, EObjectLoader.LOAD_KEY_PROVIDER);
				if (exclusiveWith.length != 0) {
					Tag ul = TagName.ul.create();
					for (Object exw: exclusiveWith) {
						ul.content(TagName.li.create(exw));
					}
					ETypedElementActionSupplier.addRow(table, "Exclusive with").add(ul);				
				}

				addContent(featureAction, table.toString());
				
				EModelElementDocumentation featureLoadDoc = EmfUtil.getLoadDocumentation(sf);
				if (featureLoadDoc == null) {
//					featureLoadDoc = EObjectAdaptable.getResourceContext(sf).getString("documentation", EcoreUtil.getDocumentation(sf));
//					if (Util.isBlank(featureLoadDoc)) {
						featureLoadDoc = EmfUtil.getDocumentation(sf);
//					}					
				}
				if (featureLoadDoc != null) {
					featureAction.getContent().add(interpolatedMarkdown(context.interpolateToString(featureLoadDoc.getDocumentation()), featureLoadDoc.getLocation(), progressMonitor));
				}
			}	
			
			addContent(loadSpecificationAction, toc.toString());
		}
	}
	
	protected DiagramTextGenerator getDiagramTextGenerator(StringBuilder sb, boolean appendAttributes, boolean appendOperations) {
		String dialect = diagramDialectSupplier.get();
		if (Util.isBlank(dialect)) {
			return null;
		}
		switch (dialect) {
		case DiagramGenerator.UML_DIALECT:
			return new PlantUmlTextGenerator(sb, ec -> path(ec, eObject), this::getEModelElementFirstDocSentence) {
				
				@Override
				protected Collection<EClass> getSubTypes(EClass eClass) {
					return EClassActionSupplier.this.getSubTypes(eClass);
				}
				
				@Override
				protected Collection<EClass> getReferrers(EClass eClass) {
					return EClassActionSupplier.this.getReferrers(eClass);
				}
				
				@Override
				protected Collection<EClass> getUses(EClassifier eClassifier) {
					return EClassActionSupplier.this.getUses(eClassifier);
				}
				
				@Override
				protected boolean isAppendAttributes(EClass eClass) {
					return appendAttributes;
				}
				
				@Override
				protected boolean isAppendOperations(EClass eClass) {
					return appendOperations;
				}
				
			};
		case DiagramGenerator.MERMAID_DIALECT:
			return new MermaidTextGenerator(sb, ec -> path(ec, eObject), this::getEModelElementFirstDocSentence) {
				
				@Override
				protected Collection<EClass> getSubTypes(EClass eClass) {
					return EClassActionSupplier.this.getSubTypes(eClass);
				}
				
				@Override
				protected Collection<EClass> getReferrers(EClass eClass) {
					return EClassActionSupplier.this.getReferrers(eClass);
				}
				
				@Override
				protected Collection<EClass> getUses(EClassifier eClassifier) {
					return EClassActionSupplier.this.getUses(eClassifier);
				}
				
				@Override
				protected boolean isAppendAttributes(EClass eClass) {
					return appendAttributes;
				}
				
				@Override
				protected boolean isAppendOperations(EClass eClass) {
					return appendOperations;
				}
				
			};
		default:
			throw new UnsupportedOperationException("Unsupported dialect: " + dialect);
		}					
	}
	
	protected String generateDiagram(
			int depth, 
			DiagramTextGenerator.RelationshipDirection relationshipDirection,
			boolean appendAttributes,
			boolean appendOperations,
			ProgressMonitor monitor) throws Exception {

		DiagramGenerator diagramGenerator = context.get(DiagramGenerator.class);		
		
		StringBuilder sb = new StringBuilder();

		DiagramTextGenerator gen = getDiagramTextGenerator(sb, appendAttributes, appendOperations);
		if (gen == null) {
			return null;
		}
		gen.appendWithRelationships(Collections.singleton(eObject), relationshipDirection, depth);
		
		return diagramGenerator.generateUmlDiagram(sb.toString());
	}
		
	/**
	 * Override to return a list of sub-types of given EClass. 
	 * This implementation returns all sub-types found in the resource set. 
	 * @param eClass
	 * @return
	 */
	protected Collection<EClass> getSubTypes(EClass eClass) {
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
			if (obj instanceof EClass && ((EClass) obj).getESuperTypes().contains(eClass)) {
				ret.add((EClass) obj);
			}
		});
		return ret;
	}
			
	/**
	 * @return Referrers to this class
	 */	
	protected Collection<EClass> getReferrers() {
		return getReferrers(eObject);
	}
	
}
