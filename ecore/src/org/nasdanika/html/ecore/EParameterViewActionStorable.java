package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;

public class EParameterViewActionStorable extends ETypedElementViewActionStorable<EParameter> {

	public EParameterViewActionStorable(EParameter value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
//	@Override
//	protected Action create(ProgressMonitor progressMonitor) throws Exception {
//		Action ret = super.create(progressMonitor);
//		ret.setRole(ActionRole.SECTION.label);
//		
//		ret.setActivatorType(ActivatorType.NONE);
//		return ret;
//	}

}
