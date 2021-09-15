package org.nasdanika.html.ecore;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.emf.PlantUmlTextGenerator;
import org.nasdanika.emf.PlantUmlTextGenerator.RelationshipDirection;
import org.nasdanika.emf.persistence.EObjectLoader;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.RowContainer.Row.Cell;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.bootstrap.Text.Alignment;
import org.nasdanika.html.bootstrap.Text.Weight;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.SectionStyle;

public class EClassActionSupplier extends EClassifierActionSupplier<EClass> {

	public EClassActionSupplier(EClass value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	public org.nasdanika.html.model.app.Action execute(ProgressMonitor progressMonitor) throws Exception {
		Action action = super.execute(progressMonitor);
		
		action.setSectionStyle(SectionStyle.HEADER);
		
		// Diagram
		addContent(action, generateDiagram(false, null, 1, RelationshipDirection.both, true, true, progressMonitor));

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
				genericType(superType, listItem.getContent(), progressMonitor);
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
				list.content(TagName.li.create(link(subType)));
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
				list.content(TagName.li.create(link(referrer)));
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
				list.content(TagName.li.create(link(use)));
			}
			addContent(action, gstf.toString());
		}
		
		// Load specification
		if (!eObject.isAbstract() && "true".equals(EmfUtil.getNasdanikaAnnotationDetail(eObject, EObjectLoader.IS_LOADABLE, "true"))) {
			HTMLFactory htmlFactory = context.get(HTMLFactory.class);
			Fragment gstf = htmlFactory.fragment(TagName.a.create(TagName.h3.create("Load specification")).attribute("name", "load-specification"));
			
			String loadDoc = EmfUtil.getNasdanikaAnnotationDetail(eObject, EObjectLoader.LOAD_DOC);
			if (!Util.isBlank(loadDoc)) {
				gstf.content(interpolatedMarkdown(loadDoc));
			}
			
			BootstrapFactory bootstrapFactory = BootstrapFactory.INSTANCE;
			Table table = bootstrapFactory.table().bordered().striped();
			table.header().headerRow("Key", "Type", "Homogenous", "Strict containment", "Exclusive with", "Description").background(Color.SECONDARY);			
			
			Predicate<EStructuralFeature> predicate = sf -> sf.isChangeable() && "true".equals(EmfUtil.getNasdanikaAnnotationDetail(sf, EObjectLoader.IS_LOADABLE, "true"));
			Comparator<EStructuralFeature> comparator = (a,b) -> a.getName().compareTo(b.getName());
			for (EStructuralFeature sf: eObject.getEAllStructuralFeatures().stream().filter(predicate).sorted(comparator).collect(Collectors.toList())) {
				Row featureRow = table.body().row();
				Cell keyCell = featureRow.cell(EmfUtil.getNasdanikaAnnotationDetail(sf, EObjectLoader.LOAD_KEY, Util.camelToKebab(sf.getName())));
				keyCell.text().monospace();
				if (EObjectLoader.isDefaultFeature(eObject, sf)) {
					keyCell.text().weight(Weight.BOLD);
				}

				genericType(sf.getEGenericType(), featureRow.cell().toHTMLElement().getContent(), progressMonitor);				
				
				featureRow.cell(EmfUtil.getNasdanikaAnnotationDetail(sf, EObjectLoader.IS_HOMOGENOUS, "")).text().alignment(Alignment.CENTER);
				featureRow.cell(EmfUtil.getNasdanikaAnnotationDetail(sf, EObjectLoader.IS_STRICT_CONTAINMENT, "")).text().alignment(Alignment.CENTER);
				
				String exclusiveWithStr = EmfUtil.getNasdanikaAnnotationDetail(sf, EObjectLoader.EXCLUSIVE_WITH);
				String[] exclusiveWith = exclusiveWithStr == null ? new String[0] : exclusiveWithStr.split("\\s");
				if (exclusiveWith.length == 0) {
					featureRow.cell("");
				} else {
					Tag ul = TagName.ul.create();
					for (String exw: exclusiveWith) {
						ul.content(TagName.li.create(exw));
					}
					
					featureRow.cell(ul).text().monospace();
				}
				
				String featureDoc = EObjectAdaptable.getResourceContext(sf).getString("documentation", EcoreUtil.getDocumentation(sf));
				if (Util.isBlank(featureDoc)) {
					featureDoc = EmfUtil.getDocumentation(sf);
				}
				
				String featureLoadDoc = EmfUtil.getNasdanikaAnnotationDetail(sf, EObjectLoader.LOAD_DOC, featureDoc);
				featureRow.cell(Util.isBlank(featureLoadDoc) ? "" : MarkdownHelper.INSTANCE.markdownToHtml(featureLoadDoc));
			};
			gstf.content(table);
			
			addContent(action, gstf.toString());
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
				attributes.add(adaptChild(sf).execute(progressMonitor));
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
				references.add(adaptChild(sf).execute(progressMonitor));
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
				operations.add(adaptChild(eOp).execute(progressMonitor));			
			}
		}
		
		if (eObject.isInterface()) {
			action.setIcon(ICONS_BASE + "EInterface.gif");			
		}		
		
		return action;
	}
	
	protected String generateDiagram(
			boolean leftToRightDirection, 
			String width, 
			int depth, 
			PlantUmlTextGenerator.RelationshipDirection relationshipDirection,
			boolean appendAttributes,
			boolean appendOperations,
			ProgressMonitor monitor) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		PlantUmlTextGenerator gen = new PlantUmlTextGenerator(sb, this::path, this::getEModelElementFirstDocSentence) {
			
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
		
		if (leftToRightDirection) {
			sb.append("left to right direction").append(System.lineSeparator());
		}
		
		if (width != null) {
			sb.append("scale ").append(width).append(" width").append(System.lineSeparator());
		}
						
		gen.appendWithRelationships(Collections.singleton(eObject), relationshipDirection, depth);
		
		return context.get(DiagramGenerator.class).generateUmlDiagram(sb.toString());
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
