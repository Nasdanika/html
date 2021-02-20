package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;

public class EEnumLiteralViewActionStorable extends ENamedElementViewActionStorable<EEnumLiteral> {

	public EEnumLiteralViewActionStorable(EEnumLiteral value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
//	@Override
//	protected Action create(ProgressMonitor progressMonitor) throws Exception {
//		Action action = super.create(progressMonitor);
//		action.setRole(ActionRole.SECTION.label);
//		
//		action.setId(eObject.eClass().getName() + "-" + encodeEPackage(eObject.getEEnum().getEPackage()) + "-" + eObject.getEEnum().getName() + "-" + eObject.getName());
//		action.setActivator(eObject.getEEnum().getName()+".html#"+eObject.getName());
//		
//		return action;
//	}
	
}
