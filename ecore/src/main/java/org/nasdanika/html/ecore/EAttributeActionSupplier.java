package org.nasdanika.html.ecore;

import java.util.function.Predicate;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;

public class EAttributeActionSupplier extends EStructuralFeatureActionSupplier<EAttribute> {

	public EAttributeActionSupplier(
			EAttribute value, 
			Context context, 
			java.util.function.Function<EPackage,String> ePackagePathComputer,
			Predicate<EModelElement> elementPredicate) {
		super(value, context, ePackagePathComputer, elementPredicate);
	}	

}
