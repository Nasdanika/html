package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;

public class EStructuralFeatureViewActionStorable<T extends EStructuralFeature> extends ETypedElementViewActionStorable<T> {

	public EStructuralFeatureViewActionStorable(T value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
//	@Override
//	protected Action create(ProgressMonitor progressMonitor) throws Exception {
//		Action action = super.create(progressMonitor);
//		action.setRole(ActionRole.SECTION.label);
//		EClass eContainingClass = eObject.getEContainingClass();
//		action.setId(
//				encodeEPackage(eContainingClass.getEPackage())
//				+ "-"
//				+ eContainingClass.getName()
//				+ "-"
//				+ eObject.eClass().getName()
//				+ "-" 				
//				+ eObject.getName());
//		
//		action.setActivator(eContainingClass.getName() + ".html#" + eObject.eClass().getName() + "-" + eObject.getName());
//		
//		return action;
//	}
	
}
