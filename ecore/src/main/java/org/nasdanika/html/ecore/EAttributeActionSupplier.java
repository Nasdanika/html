package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;

public class EAttributeActionSupplier extends EStructuralFeatureActionSupplier<EAttribute> {

	public EAttributeActionSupplier(EAttribute value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}	

}
