package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Action;

public class EEnumLiteralActionSupplier extends ENamedElementActionSupplier<EEnumLiteral> {

	public EEnumLiteralActionSupplier(EEnumLiteral value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	public Action execute(ProgressMonitor progressMonitor) throws Exception {
		Action action = super.execute(progressMonitor);
		action.setId(eObject.eClass().getName() + "-" + encodeEPackage(eObject.getEEnum().getEPackage()) + "-" + eObject.getEEnum().getName() + "-" + eObject.getName());
		action.setLocation("#"+eObject.getName());		
		return action;
	}
		
}
