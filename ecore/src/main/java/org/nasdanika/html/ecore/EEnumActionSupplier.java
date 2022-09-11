package org.nasdanika.html.ecore;

import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.SectionStyle;

public class EEnumActionSupplier extends EClassifierActionSupplier<EEnum> {

	public EEnumActionSupplier(
			EEnum value, 
			Context context, 
			java.util.function.Function<EPackage,String> ePackagePathComputer,
			java.util.function.Function<String, String> javadocResolver,
			java.util.function.Function<String, Object> ePackageResolver) {
		super(value, context, ePackagePathComputer, javadocResolver, ePackageResolver);
	}
	
	@Override
	public org.nasdanika.html.model.app.Action execute(EClass contextEClass, ProgressMonitor progressMonitor) {
		Action action = super.execute(contextEClass, progressMonitor);
		action.setSectionStyle(SectionStyle.HEADER);

		EList<Action> sections = action.getSections();
		sections.add(createLiteralsAction(contextEClass, progressMonitor));
		Action usesAction = createUsesAction(contextEClass, progressMonitor);
		if (usesAction != null) {
			sections.add(usesAction);
		}

		return action;
	}
	
	protected Action createLiteralsAction(EClass contextEClass, ProgressMonitor progressMonitor) {
		Action literalsAction = AppFactory.eINSTANCE.createAction();
		literalsAction.setText("Literals");
		literalsAction.setSectionStyle(SectionStyle.TABLE);
		EList<Action> literals = literalsAction.getSections();
		for (EEnumLiteral literal: eObject.getELiterals()) {
			literals.add(adaptChild(literal).execute(contextEClass, progressMonitor));
		}
		return literalsAction;
	}
	
	protected Action createUsesAction(EClass contextEClass, ProgressMonitor progressMonitor) {
		Collection<EClass> uses = getUses().stream().sorted((a,b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());
		if (uses.isEmpty()) {
			return null;
		}
		Action usesAction = AppFactory.eINSTANCE.createAction();
		usesAction.setText("Uses");
		
		// Uses
		Tag list = TagName.ul.create();
		for (EClass use: uses) {
			list.content(TagName.li.create(link(use, contextEClass)));
		}
		addContent(usesAction, list.toString());
		
		return usesAction;
	}

}
