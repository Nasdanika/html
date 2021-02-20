package org.nasdanika.html.ecore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.PlantUmlTextGenerator;
import org.nasdanika.emf.PlantUmlTextGenerator.RelationshipDirection;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

public class EPackageViewActionStorable extends ENamedElementViewActionStorable<EPackage> {

	public EPackageViewActionStorable(EPackage value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
//	
//	@Override
//	protected Action create(ProgressMonitor progressMonitor) throws Exception {
//		Action action = super.create(progressMonitor);
//		String nsUriEncoded = encodeEPackage(eObject);
//		action.setId(eObject.eClass().getName() + "-" + nsUriEncoded);
//		action.setActivator(nsUriEncoded + "/package-summary.html");
//		
//		for (EPackage subPackage: eObject.getESubpackages().stream().sorted((a,b) ->  a.getName().compareTo(b.getName())).collect(Collectors.toList())) {
//			action.getElements().add(adaptChild(subPackage).getAction(progressMonitor));
//		}
//		
//		for (EClassifier eClassifier: eObject.getEClassifiers().stream().sorted((a,b) ->  a.getName().compareTo(b.getName())).collect(Collectors.toList())) {
//			action.getElements().add(adaptChild(eClassifier).getAction(progressMonitor));			
//		}
//		
//		return action;
//	}
//	
//	@Override
//	public void configure(ProgressMonitor monitor) throws Exception {
//		super.configure(monitor);
//		
//		// Diagram		
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		String diagramCMap = generateDiagram(false, null, 0, RelationshipDirection.both, true, true, baos);
//		baos.close();
//		ContentTag imageTag = HtmlFactory.eINSTANCE.createContentTag();
//		imageTag.setName("img");
//		
//		Property srcAttribute = NcoreFactory.eINSTANCE.createProperty();
//		srcAttribute.setName("src");
//		srcAttribute.setValue("data:image/png;base64, " + Base64.getEncoder().encodeToString(baos.toByteArray()));
//		imageTag.getAttributes().add(srcAttribute);
//		
//		Property useMapAttribute = NcoreFactory.eINSTANCE.createProperty();
//		useMapAttribute.setName("usemap");
//		useMapAttribute.setValue("#plantuml_map");
//		imageTag.getAttributes().add(useMapAttribute);
//		
//		action.getContent().add(imageTag);
//		
//		Value cMapValue = NcoreFactory.eINSTANCE.createValue();
//		cMapValue.setValue(diagramCMap);
//		action.getContent().add(cMapValue);
//
//		ListOfContents loc = ComponentsFactory.eINSTANCE.createListOfContents();
//		loc.setDepth(1);
//		loc.setTooltips(true);
//		loc.setHeader("EClassifiers");
//		action.getContent().add(loc);		
//	}
	
	/**
	 * Generates PNG diagram.
	 * @return Image map for the diagram
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
		
		PlantUmlTextGenerator gen = new PlantUmlTextGenerator(sb, eClassifierLinkResolver, this::getEModelElementFirstDocSentence) {
			
			@Override
			protected Collection<EClass> getSubTypes(EClass eClass) {
				return EPackageViewActionStorable.this.getSubTypes(eClass);
			}
			
			@Override
			protected Collection<EClass> getReferrers(EClass eClass) {
				return EPackageViewActionStorable.this.getReferrers(eClass);
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
			protected Collection<EClass> getUses(EClassifier eClassifier) {
				return Collections.emptySet(); // No usage information on package diagrams - too much.
			}
								
		};
		gen.appendStartUml();
		
		if (leftToRightDirection) {
			sb.append("left to right direction").append(System.lineSeparator());
		}
		
		if (width != null) {
			sb.append("scale ").append(width).append(" width").append(System.lineSeparator());
		}
										
		gen.appendWithRelationships(eObject.getEClassifiers(), relationshipDirection, depth);		
		
		gen.appendEndUml();

		SourceStringReader reader = new SourceStringReader(sb.toString());
		
		FileFormatOption fileFormatOption = new FileFormatOption(FileFormat.PNG);
		reader.outputImage(out, 0, fileFormatOption);
		
		return reader.getCMapData(0, fileFormatOption);
	}

	/**
	 * Override to return a list of sub-types of given EClass. 
	 * This implementation returns all sub-types found in the current package. 
	 * @param eClass
	 * @return
	 */
	protected Collection<EClass> getSubTypes(EClass eClass) {
		Collection<EClass> ret = new ArrayList<>();
		for (EClassifier ec: eObject.getEClassifiers()) {
			if (eClass != ec && ec instanceof EClass && eClass.isSuperTypeOf((EClass) ec)) {
				ret.add((EClass) ec);
			}
		}
		return ret;		
	}	
		
	protected Function<EClassifier, String> eClassifierLinkResolver = target -> {
		String localName = target.getName() + ".html";
		if (target.getEPackage().getNsURI().equals(eObject.getNsURI())) {
			return localName;
		}
		
		StringBuilder pathUp = new StringBuilder();
		for (EPackage p = eObject; p != null; p = p.getESuperPackage()) {
			pathUp.append("../");
		}
		
		return pathUp + encodeEPackage(target.getEPackage()) + "/" + localName;
	};
	
}
