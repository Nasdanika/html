package org.nasdanika.html.ecore;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.SectionStyle;

public class EOperationViewActionStorable extends ETypedElementViewActionStorable<EOperation> {

	public EOperationViewActionStorable(EOperation value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	public Map<String, Map<String, Object>> store(URL base, ProgressMonitor progressMonitor) throws Exception {
		Map<String, Map<String, Object>> data = super.store(base, progressMonitor);
		put(data, "role", Action.Role.SECTION);
		put(data, "section-style", SectionStyle.DEFAULT.name().toLowerCase());
		
		EClass eContainingClass = eObject.getEContainingClass();
		
		StringBuilder signatureBuilder = new StringBuilder(eObject.eClass().getName())
				.append("-")
				.append(eObject.getName());

		
		List<Object> children = new ArrayList<>();		
		
		if (!eObject.getEParameters().isEmpty()) {
			Map<String,Object> parametersCategory = new LinkedHashMap<>();
			children.add(Collections.singletonMap(APP_CATEGORY_KEY, parametersCategory));
			parametersCategory.put("text", "Parameters");
			Collection<Object> parametersList = new ArrayList<>();
			parametersCategory.put("actions", parametersList);
			
			// Creating a digest of parameter types to make the id shorter.
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			for (EParameter ep: eObject.getEParameters()) {
				EClassifier type = ep.getEType();
				String typeStr = type.eClass().getName() + "-" + encodeEPackage(type.getEPackage()) + "-" + type.getName() + ",";
				md.update(typeStr.getBytes(StandardCharsets.UTF_8));
				
				parametersList.add(adaptChild(ep).store(base, progressMonitor));				
			}
			signatureBuilder.append("-").append(Hex.encodeHexString(md.digest()));
			
			children.add(parametersCategory);			
		}
		
		if (!children.isEmpty()) {
			put(data, "children", children);
		}
		
		StringBuilder idBuilder = new StringBuilder(encodeEPackage(eContainingClass.getEPackage()))
				.append("-")
				.append(eContainingClass.getName())
				.append("-")
				.append(signatureBuilder);
		
		put(data, "id", idBuilder.toString());
		put(data, "href", eContainingClass.getName() + ".html#" + signatureBuilder);
		
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
			addContent(data, gstf.toString());
		}				
		
		return data;
	}
		
}
