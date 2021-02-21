package org.nasdanika.html.ecore;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.PlantUmlTextGenerator;
import org.nasdanika.emf.PlantUmlTextGenerator.RelationshipDirection;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.SectionStyle;

public class EClassViewActionStorable extends EClassifierViewActionStorable<EClass> {

	public EClassViewActionStorable(EClass value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	public Map<String, Map<String, Object>> store(URL base, ProgressMonitor progressMonitor) throws Exception {
		Map<String, Map<String, Object>> data = super.store(base, progressMonitor);
		put(data, "section-style", SectionStyle.DEFAULT.name().toLowerCase());
		
		// Diagram
		addContent(data, generateDiagram(false, null, 1, RelationshipDirection.both, true, true, progressMonitor));

		// Generic supertypes
		EList<EGenericType> eGenericSuperTypes = eObject.getEGenericSuperTypes();
		if (!eGenericSuperTypes.isEmpty()) {
			HTMLFactory htmlFactory = context.get(HTMLFactory.class);
			Fragment gstf = htmlFactory.fragment(TagName.h3.create("Supertypes"));

			Tag list = TagName.ul.create();
			gstf.content(list);
			
			for (EGenericType superType: eGenericSuperTypes) {
				Tag listItem = TagName.li.create();
				list.content(listItem);
				genericType(superType, listItem.getContent(), progressMonitor);
			}
			addContent(data, gstf.toString());
		}
		
		// Subtypes
		Collection<EClass> eSubTypes = getSubTypes(eObject).stream().sorted((a,b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());
		if (!eSubTypes.isEmpty()) {
			HTMLFactory htmlFactory = context.get(HTMLFactory.class);
			Fragment gstf = htmlFactory.fragment(TagName.h3.create("Subtypes"));

			Tag list = TagName.ul.create();
			gstf.content(list);
			
			for (EClass subType: eSubTypes) {
				list.content(TagName.li.create(link(subType)));
			}
			addContent(data, gstf.toString());
		}
		
		// Referrers
		Collection<EClass> referrers = getReferrers().stream().sorted((a,b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());
		if (!referrers.isEmpty()) {
			HTMLFactory htmlFactory = context.get(HTMLFactory.class);
			Fragment gstf = htmlFactory.fragment(TagName.h3.create("Referrers"));

			Tag list = TagName.ul.create();
			gstf.content(list);
			
			for (EClass referrer: referrers) {
				list.content(TagName.li.create(link(referrer)));
			}
			addContent(data, gstf.toString());
		}
		
		// Uses
		Collection<EClass> uses = getUses().stream().sorted((a,b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());
		if (!uses.isEmpty()) {
			HTMLFactory htmlFactory = context.get(HTMLFactory.class);
			Fragment gstf = htmlFactory.fragment(TagName.h3.create("Uses"));

			Tag list = TagName.ul.create();
			gstf.content(list);
			
			for (EClass use: uses) {
				list.content(TagName.li.create(link(use)));
			}
			addContent(data, gstf.toString());
		}

		Map<String, Object> locConfig = new LinkedHashMap<>();
		locConfig.put("tooltip", true);
		locConfig.put("header", "Members");		
		locConfig.put("role", Action.Role.SECTION);		
		addContent(data, Collections.singletonMap("component-list-of-contents", locConfig));
		
		List<Object> children = new ArrayList<>();
		
		if (!eObject.getEAttributes().isEmpty()) {
			Map<String,Object> attrsCategory = new LinkedHashMap<>();
			children.add(Collections.singletonMap("app-category", attrsCategory));
			attrsCategory.put("text", "Attributes");
			Collection<Object> attrList = new ArrayList<>();
			attrsCategory.put("actions", attrList);
			for (EStructuralFeature sf: eObject.getEAttributes().stream().sorted((a,b) ->  a.getName().compareTo(b.getName())).collect(Collectors.toList())) {
				attrList.add(adaptChild(sf).store(base, progressMonitor));
			}
		}
		
		if (!eObject.getEReferences().isEmpty()) {
			Map<String,Object> refsCategory = new LinkedHashMap<>();
			children.add(Collections.singletonMap("app-category", refsCategory));
			refsCategory.put("text", "References");
			Collection<Object> refList = new ArrayList<>();
			refsCategory.put("actions", refList);
			
			for (EStructuralFeature sf: eObject.getEReferences().stream().sorted((a,b) ->  a.getName().compareTo(b.getName())).collect(Collectors.toList())) {
				refList.add(adaptChild(sf).store(base, progressMonitor));
			}
		}
		
		if (!eObject.getEOperations().isEmpty()) {
			Map<String,Object> opsCategory = new LinkedHashMap<>();
			children.add(Collections.singletonMap("app-category", opsCategory));
			opsCategory.put("text", "Operations");
			Collection<Object> opList = new ArrayList<>();
			opsCategory.put("actions", opList);
			
			for (EOperation eOp: eObject.getEOperations().stream().sorted((a,b) ->  a.getName().compareTo(b.getName())).collect(Collectors.toList())) {
				opList.add(adaptChild(eOp).store(base, progressMonitor));			
			}
		}
		
		if (!children.isEmpty()) {
			put(data, "children", children);
		}
		
		if (eObject.isInterface()) {
			put(data, "icon", ICONS_BASE + "EInterface.gif");			
		}		
		
		return data;
	}
	
	protected String generateDiagram(
			boolean leftToRightDirection, 
			String width, 
			int depth, 
			PlantUmlTextGenerator.RelationshipDirection relationshipDirection,
			boolean appendAttributes,
			boolean appendOperations,
			ProgressMonitor monitor) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		PlantUmlTextGenerator gen = new PlantUmlTextGenerator(sb, this::path, this::getEModelElementFirstDocSentence) {
			
			@Override
			protected Collection<EClass> getSubTypes(EClass eClass) {
				return EClassViewActionStorable.this.getSubTypes(eClass);
			}
			
			@Override
			protected Collection<EClass> getReferrers(EClass eClass) {
				return EClassViewActionStorable.this.getReferrers(eClass);
			}
			
			@Override
			protected Collection<EClass> getUses(EClassifier eClassifier) {
				return EClassViewActionStorable.this.getUses(eClassifier);
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
