package org.nasdanika.html.flow;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Action;

public class PackageActionSupplier extends PackageElementActionSupplier<org.nasdanika.flow.Package> {
	
	public PackageActionSupplier(org.nasdanika.flow.Package value, Context context) {
		super(value, context);
	}
	
	@Override
	public Action execute(ProgressMonitor progressMonitor) throws Exception {
		Action action = super.execute(progressMonitor);
		// TODO
		
//		
//		EList<EObject> children = action.getChildren();
//		for (EPackage subPackage: eObject.getESubpackages().stream().sorted((a,b) ->  a.getName().compareTo(b.getName())).collect(Collectors.toList())) {
//			children.add(adaptChild(subPackage).execute(progressMonitor));
//		}
//	
//		for (EClassifier eClassifier: eObject.getEClassifiers().stream().sorted((a,b) ->  a.getName().compareTo(b.getName())).collect(Collectors.toList())) {
//			children.add(adaptChild(eClassifier).execute(progressMonitor));			
//		}
		
		return action;
	}

}
