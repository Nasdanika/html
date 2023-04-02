package org.nasdanika.html.ecore.gen;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Action;

public class EEnumLiteralActionSupplier extends ENamedElementActionSupplier<EEnumLiteral> {

	public EEnumLiteralActionSupplier(
			EEnumLiteral value, 
			Context context, 
			java.util.function.Function<EPackage,String> ePackagePathComputer,
			Predicate<EModelElement> elementPredicate,
			BiFunction<ENamedElement, String, String> labelProvider) {
		super(value, context, ePackagePathComputer, elementPredicate, labelProvider);
	}
	
	@Override
	public Action execute(EClass contextEClass, ProgressMonitor progressMonitor) {
		Action action = super.execute(contextEClass, progressMonitor);
		action.setId(eObject.eClass().getName() + "-" + encodeEPackage(eObject.getEEnum().getEPackage()) + "-" + eObject.getEEnum().getName() + "-" + eObject.getName());
		action.setName(eObject.getName());		
		return action;
	}
		
}
