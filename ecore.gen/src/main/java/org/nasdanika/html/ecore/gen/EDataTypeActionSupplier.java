package org.nasdanika.html.ecore.gen;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.model.app.Action;

public class EDataTypeActionSupplier extends EClassifierActionSupplier<EDataType> {

	public EDataTypeActionSupplier(
			EDataType value, 
			Context context, 
			java.util.function.Function<EPackage,String> ePackagePathComputer,
			java.util.function.Function<String, String> javadocResolver,
			java.util.function.Function<String, Object> ePackageResolver,
			Predicate<EModelElement> elementPredicate,
			BiFunction<ENamedElement, String, String> labelProvider) {
		super(value, context, ePackagePathComputer, javadocResolver, ePackageResolver, elementPredicate, labelProvider);
	}
	
	@Override
	public Action execute(EClass contextEClass, ProgressMonitor progressMonitor) {
		Action action = super.execute(contextEClass, progressMonitor);
		
		// Uses
		Collection<EClass> uses = getUses().stream().sorted((a,b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());
		if (!uses.isEmpty()) {
			HTMLFactory htmlFactory = context.get(HTMLFactory.class);
			Fragment gstf = htmlFactory.fragment(TagName.h3.create("Uses"));

			Tag list = TagName.ul.create();
			gstf.content(list);
			
			for (EClass use: uses) {
				list.content(TagName.li.create(link(use, eObject)));
			}
			addContent(action, gstf.toString());
		}
		
		return action;
	}
	
}
