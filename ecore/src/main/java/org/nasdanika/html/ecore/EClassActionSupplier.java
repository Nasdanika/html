package org.nasdanika.html.ecore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.emf.DiagramTextGenerator;
import org.nasdanika.emf.DiagramTextGenerator.RelationshipDirection;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.emf.EmfUtil.EModelElementDocumentation;
import org.nasdanika.emf.MermaidTextGenerator;
import org.nasdanika.emf.PlantUmlTextGenerator;
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
import org.nasdanika.html.model.app.gen.DynamicTableBuilder;
import org.nasdanika.ncore.util.NcoreUtil;

public class EClassActionSupplier extends EClassifierActionSupplier<EClass> {
	
	private BooleanSupplier isGenerateLoadSpecification;
	private Supplier<String> diagramDialectSupplier;

	public EClassActionSupplier(
			EClass value, 
			Context context, 
			java.util.function.Function<EPackage,String> ePackagePathComputer,
			java.util.function.Function<String, String> javadocResolver,
			java.util.function.Function<String, Object> ePackageResolver,
			Predicate<EModelElement> elementPredicate,
			BiFunction<ENamedElement, String, String> labelProvider,
			BooleanSupplier isGenerateLoadSpecification,
			Supplier<String> diagramDialectSupplier) {
		super(value, context, ePackagePathComputer, javadocResolver, ePackageResolver, elementPredicate, labelProvider);
		this.elementPredicate = elementPredicate;
		this.isGenerateLoadSpecification = isGenerateLoadSpecification;
		this.diagramDialectSupplier = diagramDialectSupplier;
	}
	
	@Override
	public org.nasdanika.html.model.app.Action execute(EClass contextEClass, ProgressMonitor progressMonitor) {
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
		List<EGenericType> eGenericSuperTypes =  eObject.getEGenericSuperTypes().stream().filter(gst -> elementPredicate.test(gst.getEClassifier())).toList();
		if (!eGenericSuperTypes.isEmpty()) {
			HTMLFactory htmlFactory = context.get(HTMLFactory.class);
			Fragment gstf = htmlFactory.fragment(TagName.a.create(TagName.h3.create("Supertypes")).attribute("name", "supertypes"));

			Tag list = TagName.ul.create();
			gstf.content(list);
			
			for (EGenericType superType: eGenericSuperTypes) {
				Tag listItem = TagName.li.create();
				list.content(listItem);
				genericType(superType, eObject, listItem.getContent()::add, progressMonitor);
			}
			addContent(action, gstf.toString());
		}
		
		// Subtypes
		Collection<EClass> eSubTypes = getSubTypes(eObject).stream().filter(elementPredicate).sorted(eNamedElementComparator).toList();
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
		Collection<EClass> referrers = getReferrers().stream().filter(elementPredicate).sorted(eNamedElementComparator).toList();
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
		Collection<EClass> uses = getUses().stream().filter(elementPredicate).sorted(eNamedElementComparator).toList();
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
		
		List<EAttribute> allAttributes = eObject.getEAllAttributes().stream().filter(elementPredicate).sorted(eNamedElementComparator).toList();
		List<EReference> allReferences = eObject.getEAllReferences().stream().filter(elementPredicate).sorted(eNamedElementComparator).toList();
		List<EOperation> allOperations = eObject.getEAllOperations().stream().filter(elementPredicate).sorted(eNamedElementComparator).toList();		
		List<EGenericType> allGenericSupertypes = eObject.getEAllGenericSuperTypes().stream().filter(gst -> elementPredicate.test(gst.getEClassifier())).toList();
		
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
			generateLoadSpecification(action, eNamedElementComparator, progressMonitor);
		}
		
		List<EAttribute> sortedAttributes = eObject.getEAttributes().stream().filter(elementPredicate).sorted(eNamedElementComparator).toList();
		
		EList<Action> sections = action.getSections();
		if (!sortedAttributes.isEmpty()) {
			Action attributeSummaryCategory = AppFactory.eINSTANCE.createAction();
			attributeSummaryCategory.setText("Attribute summary");
			attributeSummaryCategory.setName("attribute-summary");
			attributeSummaryCategory.setSectionStyle(SectionStyle.HEADER);
			sections.add(attributeSummaryCategory);
			attributeSummaryCategory.getContent().add(buildDynamicAttributesTable(sortedAttributes, progressMonitor));			
		}
		
		List<EReference> sortedReferences = eObject.getEReferences().stream().filter(elementPredicate).sorted(eNamedElementComparator).toList();
		if (!sortedReferences.isEmpty()) {
			Action referenceSummaryCategory = AppFactory.eINSTANCE.createAction();
			referenceSummaryCategory.setText("Reference summary");
			referenceSummaryCategory.setName("reference-summary");
			referenceSummaryCategory.setSectionStyle(SectionStyle.HEADER);
			sections.add(referenceSummaryCategory);
			referenceSummaryCategory.getContent().add(buildDynamicReferencesTable(sortedReferences, progressMonitor));			
		}
		
		if (!sortedAttributes.isEmpty()) {
			Action attributeDetailsCategory = AppFactory.eINSTANCE.createAction();
			attributeDetailsCategory.setText("Attribute details");
			attributeDetailsCategory.setName("attribute-details");
			attributeDetailsCategory.setSectionStyle(SectionStyle.HEADER);
			sections.add(attributeDetailsCategory);
			EList<Action> attributes = attributeDetailsCategory.getSections();
			
			for (EStructuralFeature sf: sortedAttributes) {
				attributes.add(adaptChild(sf).execute(null, progressMonitor));
			}
		}
		
		if (!sortedReferences.isEmpty()) {
			Action referenceDetailsCategory = AppFactory.eINSTANCE.createAction();
			referenceDetailsCategory.setText("Reference details");
			referenceDetailsCategory.setName("reference-details");
			referenceDetailsCategory.setSectionStyle(SectionStyle.HEADER);
			sections.add(referenceDetailsCategory);
			EList<Action> references = referenceDetailsCategory.getSections();			
			for (EStructuralFeature sf: sortedReferences) {
				references.add(adaptChild(sf).execute(null, progressMonitor));
			}
		}
		
		List<EOperation> sortedOperations = eObject.getEOperations().stream().filter(elementPredicate).sorted(eNamedElementComparator).toList();
		if (!sortedOperations.isEmpty()) {
			Action operationsCategory = AppFactory.eINSTANCE.createAction();
			operationsCategory.setText("Operations");
			operationsCategory.setName("operations");
			operationsCategory.setSectionStyle(SectionStyle.HEADER);
			sections.add(operationsCategory);
			EList<Action> operations = operationsCategory.getSections();			
			for (EOperation eOp: sortedOperations) {
				operations.add(adaptChild(eOp).execute(null, progressMonitor));			
			}
		}
		
		if (eObject.isInterface()) {
			action.setIcon(ICONS_BASE + "EInterface.gif");			
		}		
		
		return action;
	}

	private void generateAllGenericSupertypes(List<EGenericType> allGenericSupertypes, Action allGroup, ProgressMonitor progressMonitor) {		
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
					genericType(superType, eObject, listItem.getContent()::add, progressMonitor);
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
			ProgressMonitor monitor) {
		
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
	
	
	private void generateAllOperations(List<EOperation> allOperations, Action allGroup, ProgressMonitor progressMonitor) {		
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

	private void generateAllReferences(List<EReference> allReferences,	Action allGroup, ProgressMonitor progressMonitor) {		
		if (!allReferences.isEmpty()) {
			Action allReferencesAction = AppFactory.eINSTANCE.createAction();
			allReferencesAction.setText("References");
			allReferencesAction.setLocation(eObject.getName() + "-all-references.html");
			allReferencesAction.setSectionStyle(SectionStyle.HEADER);
			allGroup.getChildren().add(allReferencesAction);
			
			allReferencesAction.getContent().add(buildDynamicReferencesTable(allReferences, progressMonitor));
		}
	}

	private void generateAllAttributes(List<EAttribute> allAttributes, Action allGroup,	ProgressMonitor progressMonitor) {		
		if (!allAttributes.isEmpty()) {
			Action allAttributesAction = AppFactory.eINSTANCE.createAction();
			allAttributesAction.setText("Attributes");
			allAttributesAction.setLocation(eObject.getName() + "-all-attributes.html");
			allAttributesAction.setSectionStyle(SectionStyle.HEADER);
			allGroup.getChildren().add(allAttributesAction);
			
			allAttributesAction.getContent().add(buildDynamicAttributesTable(allAttributes,	progressMonitor));
		}
	}

	protected org.nasdanika.html.model.html.Tag buildDynamicAttributesTable(List<EAttribute> attributes, ProgressMonitor progressMonitor) {
		DynamicTableBuilder<EAttribute> attributesTableBuilder = new DynamicTableBuilder<>();
		attributesTableBuilder
			.addStringColumnBuilder("name", true, true, "Name", attr -> link(attr, eObject))
			.addStringColumnBuilder("type", true, true, "Type", attr -> {
				EGenericType genericType = attr.getEGenericType(); 
				if (genericType == null) {
					return null;
				}
				StringBuilder sb = new StringBuilder();
				genericType(genericType, eObject, sb::append, progressMonitor);
				return sb.toString();
			})
			.addStringColumnBuilder("cardinality", true, true, "Cardinality", EModelElementActionSupplier::cardinality)
			.addBooleanColumnBuilder("changeable", true, true, "Changeable", EStructuralFeature::isChangeable)
			.addBooleanColumnBuilder("derived", true, true, "Derived", EStructuralFeature::isDerived)
			.addStringColumnBuilder("declaring-class", true, true, "Declaring Class", attr -> link(attr.getEContainingClass(), eObject))
			.addStringColumnBuilder("description", true, false, "Description", this::getEModelElementFirstDocSentence);
			// Other things not visible?
		
		org.nasdanika.html.model.html.Tag attributesTable = attributesTableBuilder.build(attributes, eObject.getEPackage().getNsURI().hashCode() + "-" + eObject.getName() + "-attributes", "attributes-table", progressMonitor);
		return attributesTable;
	}
	
	protected org.nasdanika.html.model.html.Tag buildDynamicReferencesTable(List<EReference> references, ProgressMonitor progressMonitor) {
		DynamicTableBuilder<EReference> referencesTableBuilder = new DynamicTableBuilder<>();
		referencesTableBuilder
			.addStringColumnBuilder("name", true, true, "Name", ref -> link(ref, eObject))
			.addStringColumnBuilder("type", true, true, "Type", ref -> {
				EGenericType genericType = ref.getEGenericType(); 
				if (genericType == null) {
					return null;
				}
				StringBuilder sb = new StringBuilder();
				genericType(genericType, eObject, sb::append, progressMonitor);
				return sb.toString();
			})
			.addStringColumnBuilder("cardinality", true, true, "Cardinality", EModelElementActionSupplier::cardinality)
			.addBooleanColumnBuilder("changeable", true, true, "Changeable", EStructuralFeature::isChangeable)
			.addBooleanColumnBuilder("derived", true, true, "Derived", EStructuralFeature::isDerived)
			.addStringColumnBuilder("declaring-class", true, true, "Declaring Class", ref -> link(ref.getEContainingClass(), eObject))
			.addStringColumnBuilder("opposite", true, true, "Opposite", ref -> {
				EReference opposite = NcoreUtil.getOpposite(ref);
				return opposite == null ? null : link(opposite, eObject);				
			})
			.addStringColumnBuilder("description", true, false, "Description", this::getEModelElementFirstDocSentence);
			// Other things not visible?
		
		org.nasdanika.html.model.html.Tag referencesTable = referencesTableBuilder.build(references, eObject.getEPackage().getNsURI().hashCode() + "-" + eObject.getName() + "-references", "references-table", progressMonitor);
		return referencesTable;
	}
	
	private void generateLoadSpecification(
			Action action, 
			Comparator<ENamedElement> namedElementComparator,
			ProgressMonitor progressMonitor) {
		
		// Load specification
		if (!eObject.isAbstract() && "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(eObject, EObjectLoader.IS_LOADABLE, "true"))) {
			Action loadSpecificationAction = AppFactory.eINSTANCE.createAction();
			loadSpecificationAction.setText("Load specification");
			loadSpecificationAction.setLocation(eObject.getName() + "-load-specification.html");			
			action.getNavigation().add(loadSpecificationAction);
			
			EModelElementDocumentation loadDoc = EmfUtil.getLoadDocumentation(eObject);
			if (loadDoc != null) {
				loadSpecificationAction.getContent().add(interpolatedMarkdown(loadDoc.documentation(), loadDoc.location(), progressMonitor));
			}
			
			Predicate<EStructuralFeature> predicate = sf -> sf.isChangeable() && "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(sf, EObjectLoader.IS_LOADABLE, "true"));
			List<EStructuralFeature> sortedFeatures = eObject.getEAllStructuralFeatures().stream().filter(predicate.and(elementPredicate)).sorted(namedElementComparator).toList();
			
			Function<EStructuralFeature, String> keyExtractor = sf -> NcoreUtil.getNasdanikaAnnotationDetail(sf, EObjectLoader.LOAD_KEY, NcoreUtil.getFeatureKey(eObject, sf));
			Predicate<EStructuralFeature> homogeneousPredicate = sf -> "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(sf, EObjectLoader.IS_HOMOGENEOUS)) || NcoreUtil.getNasdanikaAnnotationDetail(sf, EObjectLoader.REFERENCE_TYPE) != null;
			Predicate<EStructuralFeature> strictContainmentPredicate = homogeneousPredicate.and(sf -> "true".equals(NcoreUtil.getNasdanikaAnnotationDetail(sf, EObjectLoader.IS_STRICT_CONTAINMENT)));
			Function<EStructuralFeature, Object[]> exclusiveWithExtractor = sf -> EObjectLoader.getExclusiveWith(eObject, sf, EObjectLoader.LOAD_KEY_PROVIDER);
			
			DynamicTableBuilder<EStructuralFeature> loadSpecificationTableBuilder = new DynamicTableBuilder<>();
			loadSpecificationTableBuilder
				.addStringColumnBuilder("key", true, true, "Key", sf -> {
					String key = keyExtractor.apply(sf);
					return TagName.a.create(key).attribute("href", "#key-section-" + key).attribute("style", "font-weight:bold", EObjectLoader.isDefaultFeature(eObject, sf)).toString();
				})
				.addStringColumnBuilder("type", true, true, "Type", attr -> {
					EGenericType genericType = attr.getEGenericType(); 
					if (genericType == null) {
						return null;
					}
					StringBuilder sb = new StringBuilder();
					genericType(genericType, eObject, sb::append, progressMonitor);
					return sb.toString();
				})
				.addStringColumnBuilder("cardinality", true, false, "Cardinality", EModelElementActionSupplier::cardinality)
				.addBooleanColumnBuilder("homogeneous", true, false, "Homogeneous", homogeneousPredicate)
				.addBooleanColumnBuilder("strict-containment", true, false, "Strict Containment", strictContainmentPredicate)
				.addStringColumnBuilder("exclusive-with", true, false, "Exclusive With", sf -> {
					Object[] exclusiveWith = exclusiveWithExtractor.apply(sf);
					if (exclusiveWith.length == 0) {
						return null;
					}
					Tag ul = TagName.ul.create();
					for (Object exw: exclusiveWith) {
						ul.content(TagName.li.create(exw));
					}
					return ul.toString();				
				})
				.addStringColumnBuilder("description", true, false, "Description", this::getEStructuralFeatureFirstLoadDocSentence);
				// Other things not visible?
			
			org.nasdanika.html.model.html.Tag loadSpecificationTable = loadSpecificationTableBuilder.build(sortedFeatures, eObject.getEPackage().getNsURI().hashCode() + "-" + eObject.getName() + "-load-specification", "load-specification-table", progressMonitor);						
			
			for (EStructuralFeature sf: sortedFeatures) {
				Action featureAction = AppFactory.eINSTANCE.createAction();
				String key = keyExtractor.apply(sf);
				featureAction.setText(key);
				String sectionAnchor = "key-section-" + key;
				
				featureAction.setName(sectionAnchor);			
				loadSpecificationAction.getSections().add(featureAction);

				// Properties table
				Table table = context.get(BootstrapFactory.class).table();
				table.toHTMLElement().style().width("auto");
				
				genericType(sf.getEGenericType(), eObject, ETypedElementActionSupplier.addRow(table, "Type")::add, progressMonitor);
				
				boolean isDefaultFeature = EObjectLoader.isDefaultFeature(eObject, sf);
				if (isDefaultFeature) {
					ETypedElementActionSupplier.addRow(table, "Default").add("true");				
				}
				
				boolean isHomogeneous = homogeneousPredicate.test(sf);
				if (isHomogeneous) {
					ETypedElementActionSupplier.addRow(table, "Homogeneous").add("true");									
				}
				
				boolean isStrictContainment = strictContainmentPredicate.test(sf);			
				if (isStrictContainment) {
					ETypedElementActionSupplier.addRow(table, "Strict containment").add("true");									
				}
				
				Object[] exclusiveWith = exclusiveWithExtractor.apply(sf);
				if (exclusiveWith.length != 0) {
					Tag ul = TagName.ul.create();
					for (Object exw: exclusiveWith) {
						ul.content(TagName.li.create(exw));
					}
					ETypedElementActionSupplier.addRow(table, "Exclusive with").add(ul);				
				}

				addContent(featureAction, table.toString());
				
				EModelElementDocumentation featureLoadDoc = getFeatureLoadDoc(sf);
				if (featureLoadDoc != null) {
					featureAction.getContent().add(interpolatedMarkdown(context.interpolateToString(featureLoadDoc.documentation()), featureLoadDoc.location(), progressMonitor));
				}
			}	
			
			loadSpecificationAction.getContent().add(loadSpecificationTable);
		}
	}
	
	protected EModelElementDocumentation getFeatureLoadDoc(EStructuralFeature sf) {
		EModelElementDocumentation featureLoadDoc = EmfUtil.getLoadDocumentation(sf);
		return featureLoadDoc == null ? EmfUtil.getDocumentation(sf) : featureLoadDoc;
	}	
	
	protected String getEStructuralFeatureFirstLoadDocSentence(EStructuralFeature sf) {
		EModelElementDocumentation documentation = getFeatureLoadDoc(sf);
		if (documentation == null) {
			return null;
		}
		
		MarkdownHelper markdownHelper = new MarkdownHelper() {
			
			@Override
			protected URI getResourceBase() {
				return documentation.location();
			}
			
			@Override
			protected DiagramGenerator getDiagramGenerator() {
				return context == null ? super.getDiagramGenerator() : context.get(DiagramGenerator.class, super.getDiagramGenerator()); 
			}
			
		};
		
		String ret = markdownHelper.firstPlainTextSentence(documentation.documentation());
		return String.join(" ", ret.split("\\R")); // Replacing new lines, shall they be in the first sentence, with spaces.		
	}
		
	protected DiagramTextGenerator getDiagramTextGenerator(StringBuilder sb, boolean appendAttributes, boolean appendOperations) {
		String dialect = diagramDialectSupplier.get();
		if (Util.isBlank(dialect)) {
			return null;
		}
		switch (dialect) {
		case DiagramGenerator.UML_DIALECT:
			return new PlantUmlTextGenerator(sb, elementPredicate, ec -> path(ec, eObject), this::getEModelElementFirstDocSentence, labelProvider) {
				
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
				
				@Override
				protected String qualifiedName(EClassifier eClassifier) {
					Class<?> ic = getInstanceClass(eClassifier, ePackageResolver);
					return ic == null ? super.qualifiedName(eClassifier) : ic.getName();
				}
				
			};
		case DiagramGenerator.MERMAID_DIALECT:
			return new MermaidTextGenerator(sb, elementPredicate, ec -> path(ec, eObject), this::getEModelElementFirstDocSentence) {
				
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
			ProgressMonitor monitor) {

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
