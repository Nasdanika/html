package org.nasdanika.html.ecore.gen;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;

public class EAttributeActionSupplier extends EStructuralFeatureActionSupplier<EAttribute> {

	public EAttributeActionSupplier(
			EAttribute value, 
			Context context, 
			java.util.function.Function<EPackage,String> ePackagePathComputer,
			Predicate<EModelElement> elementPredicate,
			BiFunction<ENamedElement, String, String> labelProvider) {
		super(value, context, ePackagePathComputer, elementPredicate, labelProvider);
	}	

}
