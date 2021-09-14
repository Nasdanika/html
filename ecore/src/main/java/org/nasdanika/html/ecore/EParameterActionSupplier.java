package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Action;

public class EParameterActionSupplier extends ETypedElementActionSupplier<EParameter> {

	public EParameterActionSupplier(EParameter value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	public Action execute(ProgressMonitor progressMonitor) throws Exception {
		Action action = super.execute(progressMonitor);
		action.setName(EOperationActionSupplier.eOperationSignature(eObject.getEOperation(), this::encodeEPackage) + "--" + eObject.getName());
		return action;
	}

}
