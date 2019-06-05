package org.nasdanika.html.ecore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.bootstrap.Table.TableBody;
import org.nasdanika.html.bootstrap.Table.TableHeader;
import org.nasdanika.html.bootstrap.Text.Alignment;
import org.nasdanika.html.ecore.PlantUmlTextGenerator.RelationshipDirection;
import org.nasdanika.html.emf.EObjectAdaptable;
import org.nasdanika.html.emf.ViewAction;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

public class EClassViewAction extends EClassifierViewAction<EClass> {

	public EClassViewAction(EClass value) {
		super(value);
	}
	
	@Override
	public Object generate(ViewGenerator viewGenerator) {
		BootstrapFactory bootstrapFactory = viewGenerator.getBootstrapFactory();
		Container contentContainer = bootstrapFactory.fluidContainer();
		contentContainer.text().alignment(Alignment.LEFT);
		if (target.isAbstract()) {
			contentContainer.row().col(target.isInterface() ? "Interface" : "Abstract").padding().bottom(3);
		}
		String description = getDescription();
		if (!Util.isBlank(description) && description.length() < descriptionTabLengthThreshold) {
			contentContainer.row().col(description);
		}
		
		Navs tabs = bootstrapFactory.tabs();
		contentContainer.row().col(tabs);
		
		if (!Util.isBlank(description) && description.length() >= descriptionTabLengthThreshold) {
			tabs.item("Description", description);
		}		

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			String diagramCMap = generateDiagram(false, null, 1, RelationshipDirection.both, true, true, baos);
			baos.close();
			String imagePath = viewGenerator.getResourceConsumer().apply(getId()+".png", baos.toByteArray());
			if (imagePath != null) {
				HTMLFactory htmlFactory = viewGenerator.getHTMLFactory();
				Tag diagramImage = htmlFactory.tag(TagName.img).attribute("src", imagePath).attribute("usemap", "#plantuml_map");
				tabs.item("Diagram", htmlFactory.fragment(diagramImage, diagramCMap));				
			}
		} catch (IOException e) {
			tabs.item("Diagram", bootstrapFactory.alert(Color.DANGER, "Error generating class diagram: "+e));
			e.printStackTrace();
		}
		
		List<Action> children = getChildren();
		if (!children.isEmpty()) {
			Table table = bootstrapFactory.table().bordered();
			TableHeader header = table.header();
			header.headerRow("Name", "Type", "Cardinality", "Summary");
			TableBody body = table.body();
			children.forEach(child -> {
				ETypedElement typedElement = child.adaptTo(ETypedElement.class);
				EClassifier type = typedElement.getEType();
				ViewAction typeViewAction = EObjectAdaptable.adaptTo(type, ViewAction.class);
				body.row(viewGenerator.link(child), typeViewAction == null ?  type.getName() : viewGenerator.link(typeViewAction), cardinality(typedElement), child.getTooltip());			
			});
			tabs.item("Contents", table);
		}
		
		EList<EClass> st = target.getESuperTypes();
		if (!st.isEmpty()) {
			Table superTypesTable = bootstrapFactory.table().bordered();
			superTypesTable.header().headerRow("Name", "Summary");
			TableBody superTypesTableBody = superTypesTable.body();
			st.forEach(superType -> {
				Action viewAction = EObjectAdaptable.adaptTo(superType, ViewAction.class);
				if (viewAction != null) {
					superTypesTableBody.row(viewGenerator.link(viewAction), viewAction.getTooltip());
				}
			});
			tabs.item("Supertypes", superTypesTable);							
		}
		
		List<EClass> sbt = getSubTypes(target).stream().sorted((a,b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());
		if (!sbt.isEmpty()) {
			Table subTypesTable = bootstrapFactory.table().bordered();
			subTypesTable.header().headerRow("Name", "Summary");
			TableBody subTypesTableBody = subTypesTable.body();
			sbt.forEach(subType -> {
				Action viewAction = EObjectAdaptable.adaptTo(subType, ViewAction.class);
				if (viewAction != null) {
					subTypesTableBody.row(viewGenerator.link(viewAction), viewAction.getTooltip());
				}
			});
			tabs.item("Subtypes", subTypesTable);				
			
		}
		
		return contentContainer;
	}
	
	@Override
	protected List<EStructuralFeature> getChildFeatures() {
		List<EStructuralFeature> ret = new ArrayList<>();
		ret.add(EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES);
		ret.add(EcorePackage.Literals.ECLASS__EOPERATIONS);
		return ret;
	}
	

	/**
	 * Generates PNG diagram.
	 * @return
	 * @throws IOException 
	 */
	protected String generateDiagram(
			boolean leftToRightDirection, 
			String width, 
			int depth, 
			PlantUmlTextGenerator.RelationshipDirection relationshipDirection,
			boolean appendAttributes,
			boolean appendOperations,
			OutputStream out) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		PlantUmlTextGenerator gen = new PlantUmlTextGenerator(sb, eClassifierLinkResolver, eModelElementFirstDocSentenceProvider) {
			
			@Override
			protected Collection<EClass> getSubTypes(EClass eClass) {
				return EClassViewAction.this.getSubTypes(eClass);
			}
			
			@Override
			protected Collection<EClass> getReferrers(EClass eClass) {
				return EClassViewAction.this.getReferrers(eClass);
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
		gen.appendStartUml();
		
		if (leftToRightDirection) {
			sb.append("left to right direction").append(System.lineSeparator());
		}
		
		if (width != null) {
			sb.append("scale ").append(width).append(" width").append(System.lineSeparator());
		}
						
		gen.appendWithRelationships(Collections.singleton(target), relationshipDirection, depth);
		
		gen.appendEndUml();
		SourceStringReader reader = new SourceStringReader(sb.toString());
		
		FileFormatOption fileFormatOption = new FileFormatOption(FileFormat.PNG);
		reader.outputImage(out, 0, fileFormatOption);
		
		return reader.getCMapData(0, fileFormatOption);
	}
		
	/**
	 * Override to return a list of sub-types of given EClass. 
	 * This implementation returns all sub-types found in the EClass' current package. 
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
			acit = resourceSet == null ? eResource.getAllContents() : eResource.getAllContents();
		}
		Set<EClass> ret = new HashSet<>();
		acit.forEachRemaining(obj -> {
			if (obj instanceof EClass && ((EClass) obj).getESuperTypes().contains(eClass)) {
				ret.add((EClass) obj);
			}
		});
		return ret;
	}
	
	@Override
	public String getIcon() {
		return target.isInterface() ? iconsBase+"EInterface.gif" : super.getIcon();			
	}

}
