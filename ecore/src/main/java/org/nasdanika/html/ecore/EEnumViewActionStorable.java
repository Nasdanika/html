package org.nasdanika.html.ecore;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.SectionStyle;

public class EEnumViewActionStorable extends EClassifierViewActionStorable<EEnum> {

	public EEnumViewActionStorable(EEnum value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	public Map<String, Map<String, Object>> store(URL base, ProgressMonitor progressMonitor) throws Exception {
		Map<String, Map<String, Object>> data = super.store(base, progressMonitor);
		put(data, "section-style", SectionStyle.DEFAULT.name().toLowerCase());
		
		List<Object> children = new ArrayList<>();
		children.add(createLiteralsAction(base, progressMonitor));
		Map<String, Map<String, Object>> usesAction = createUsesAction(base, progressMonitor);
		if (usesAction != null) {
			children.add(usesAction);
		}
		put(data, "children", children);

		return data;
	}
	
	protected Map<String, Map<String, Object>> createLiteralsAction(URL base, ProgressMonitor progressMonitor) throws Exception {
		Map<String, Object> literalsData = new LinkedHashMap<>();
		literalsData.put("section-style", SectionStyle.TABLE.name().toLowerCase());
		literalsData.put("text", "Literals");
		literalsData.put("role", Action.Role.SECTION);
		
		List<Object> children = new ArrayList<>();
		for (EEnumLiteral literal: eObject.getELiterals()) {
			children.add(adaptChild(literal).store(base, progressMonitor));
		}
		literalsData.put("children", children);
		
		return Collections.singletonMap("app-action", literalsData);
	}
	
	protected Map<String, Map<String, Object>> createUsesAction(URL base, ProgressMonitor progressMonitor) throws Exception {
		Collection<EClass> uses = getUses().stream().sorted((a,b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());
		if (uses.isEmpty()) {
			return null;
		}
		
		Map<String, Object> usesData = new LinkedHashMap<>();
		usesData.put("text", "Uses");
		usesData.put("role", Action.Role.SECTION);
		
		// Uses
		HTMLFactory htmlFactory = context.get(HTMLFactory.class);
		Fragment gstf = htmlFactory.fragment(TagName.h3.create("Uses"));

		Tag list = TagName.ul.create();
		gstf.content(list);
		
		for (EClass use: uses) {
			list.content(TagName.li.create(link(use)));
		}
		usesData.put(CONTENT_KEY, gstf.toString());
		
		return Collections.singletonMap("app-action", usesData);
	}

}
