package org.nasdanika.html.flow;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.function.Function;

import org.apache.commons.codec.binary.Hex;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.SectionStyle;

public class EOperationActionSupplier extends ETypedElementActionSupplier<EOperation> {

	public EOperationActionSupplier(EOperation value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	public Action execute(ProgressMonitor progressMonitor) throws Exception {
		Action action = super.execute(progressMonitor);
		action.setSectionStyle(SectionStyle.HEADER);
		
		EClass eContainingClass = eObject.getEContainingClass();
		
		EList<Action> sections = action.getSections();		
		
		if (!eObject.getEParameters().isEmpty()) {
			Action parametersCategory = AppFactory.eINSTANCE.createAction();
			parametersCategory.setText("Parameters");
			sections.add(parametersCategory);
			EList<Action> parameters = parametersCategory.getSections();
			
			// Creating a digest of parameter types to make the id shorter.
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			for (EParameter ep: eObject.getEParameters()) {
				EClassifier type = ep.getEType();
				String typeStr = type.eClass().getName() + "-" + encodeEPackage(type.getEPackage()) + "-" + type.getName() + ",";
				md.update(typeStr.getBytes(StandardCharsets.UTF_8));
				
				parameters.add(adaptChild(ep).execute(progressMonitor));				
			}
		}
		
		String signature = eOperationSignature(eObject, this::encodeEPackage);
		
		StringBuilder idBuilder = new StringBuilder(encodeEPackage(eContainingClass.getEPackage()))
				.append("-")
				.append(eContainingClass.getName())
				.append("-")
				.append(signature);
		
		action.setId(idBuilder.toString());
		action.setName(signature);
		
		// Exceptions
		EList<EGenericType> eGenericExceptions = eObject.getEGenericExceptions();
		if (!eGenericExceptions.isEmpty()) {
			HTMLFactory htmlFactory = context.get(HTMLFactory.class);
			Fragment gstf = htmlFactory.fragment(TagName.h3.create("Exceptions"));

			Tag list = TagName.ul.create();
			gstf.content(list);
			
			for (EGenericType genericException: eGenericExceptions) {
				Tag listItem = TagName.li.create();
				list.content(listItem);
				genericType(genericException, listItem.getContent(), progressMonitor);
			}
			addContent(action, gstf.toString());
		}				
		
		return action;
	}
	
	public static String eOperationSignature(EOperation eOperation, Function<EPackage, String> ePackageEncoder) throws NoSuchAlgorithmException {		
		StringBuilder signatureBuilder = new StringBuilder(eOperation.eClass().getName())
				.append("-")
				.append(eOperation.getName());
		
		if (!eOperation.getEParameters().isEmpty()) {
			// Creating a digest of parameter types to make the id shorter.
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			for (EParameter ep: eOperation.getEParameters()) {
				EClassifier type = ep.getEType();
				String typeStr = type.eClass().getName() + "-" + ePackageEncoder.apply(type.getEPackage()) + "-" + type.getName() + ",";
				md.update(typeStr.getBytes(StandardCharsets.UTF_8));
			}
			signatureBuilder.append("-").append(Hex.encodeHexString(md.digest()));			
		}

		return signatureBuilder.toString();
	}
		
}
