package org.nasdanika.html.ecore;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Hex;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.PlantUmlTextGenerator;
import org.nasdanika.emf.PlantUmlTextGenerator.RelationshipDirection;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.ncore.util.NcoreUtil;

public class EPackageActionSupplier extends ENamedElementActionSupplier<EPackage> {

	public EPackageActionSupplier(EPackage value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
//		dump(value, 0);
	}
	
//	private static void dump(EPackage ePackage, int offset) {
//		String prefix = "";
//		for (int i = 0; i < offset; ++i) {
//			prefix += "\t";
//		}
//		System.out.println(prefix + ePackage.getName());
//		for (EPackage sp: ePackage.getESubpackages()) {
//			dump(sp, offset + 1);
//		}
//		for (EClassifier ec: ePackage.getEClassifiers()) {
//			System.out.println(prefix + "\t" + ec.getName());
//			if (ec instanceof EClass) {
//				for (EStructuralFeature sf: ((EClass) ec).getEStructuralFeatures()) {
//					System.out.println(prefix + "\t\t" + sf.getName());
//				}
//				for (EOperation op: ((EClass) ec).getEOperations()) {
//					System.out.println(prefix + "\t\t" + op.getName() + "()");
//				}
//			}
//		}
//	}
	
	@Override
	public Action execute(EClass contextEClass, ProgressMonitor progressMonitor) throws Exception {
		Action action = super.execute(contextEClass, progressMonitor);
		String ePackageFolder = ePackagePathComputer == null ? Hex.encodeHexString(eObject.getNsURI().getBytes(StandardCharsets.UTF_8)) : ePackagePathComputer.apply(eObject);
		action.setLocation(ePackageFolder + "/package-summary.html");
		action.setId(eObject.eClass().getName() + "-" + encodeEPackage(eObject));
		
		String diagramMode = NcoreUtil.getNasdanikaAnnotationDetail(eObject, "diagram", "navigation");
		switch (diagramMode) {
		case "content":
			addContent(action, generateDiagram(false,  null, 0, RelationshipDirection.both, true, true));
			break;
		case "none":
			break;
		case "navigation": {
			Action diagramAction = AppFactory.eINSTANCE.createAction();
			action.getNavigation().add(diagramAction);
			diagramAction.setText("Diagram");
			diagramAction.setIcon("fas fa-project-diagram");
			diagramAction.setLocation("package-summary-diagram.html");
			addContent(diagramAction, generateDiagram(false,  null, 0, RelationshipDirection.both, true, true));
			break;
		}
		case "anonymous": {
			Action diagramAction = AppFactory.eINSTANCE.createAction();
			action.getAnonymous().add(diagramAction);
			diagramAction.setText("Diagram");
			diagramAction.setIcon("fas fa-project-diagram");
			diagramAction.setLocation(ePackageFolder + "/package-summary-diagram.html");
			addContent(diagramAction, generateDiagram(false,  null, 0, RelationshipDirection.both, true, true));
			break;			
		}
		default:
			throw new IllegalArgumentException("Unsupported diagram annotation value '" + diagramMode +"' on EPackage " + eObject); 			
		}
		
		// TODO - Table (list) of contents
//		addContent(data, Collections.singletonMap("component-list-of-contents", Collections.singletonMap("tooltip", true))); 
		
		EList<EObject> children = action.getChildren();
		for (EPackage subPackage: eObject.getESubpackages().stream().sorted((a,b) ->  a.getName().compareTo(b.getName())).collect(Collectors.toList())) {
			children.add(adaptChild(subPackage).execute(contextEClass, progressMonitor));
		}
	
		for (EClassifier eClassifier: eObject.getEClassifiers().stream().sorted((a,b) ->  a.getName().compareTo(b.getName())).collect(Collectors.toList())) {
			children.add(adaptChild(eClassifier).execute(contextEClass, progressMonitor));			
		}
		
		return action;
	}
	
	/**
	 * Generates PNG diagram.
	 * @return Inline PNG and the image map.
	 * @throws IOException 
	 */
	protected String generateDiagram(
			boolean leftToRightDirection, 
			String width, 
			int depth, 
			PlantUmlTextGenerator.RelationshipDirection relationshipDirection,
			boolean appendAttributes,
			boolean appendOperations) throws Exception {
		
		StringBuilder sb = new StringBuilder();
		
		PlantUmlTextGenerator gen = new PlantUmlTextGenerator(sb, eClassifierLinkResolver, this::getEModelElementFirstDocSentence) {
			
			@Override
			protected Collection<EClass> getSubTypes(EClass eClass) {
				return EPackageActionSupplier.this.getSubTypes(eClass);
			}
			
			@Override
			protected Collection<EClass> getReferrers(EClass eClass) {
				return EPackageActionSupplier.this.getReferrers(eClass);
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
		
		if (leftToRightDirection) {
			sb.append("left to right direction").append(System.lineSeparator());
		}
		
		if (width != null) {
			sb.append("scale ").append(width).append(" width").append(System.lineSeparator());
		}
										
		gen.appendWithRelationships(eObject.getEClassifiers(), relationshipDirection, depth);		
		return context.get(DiagramGenerator.class).generateUmlDiagram(sb.toString());
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
