package org.nasdanika.html.flow;

import java.util.Collection;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Action;

public class EClassifierActionSupplier<T extends EClassifier> extends ENamedElementActionSupplier<T> {

	public EClassifierActionSupplier(T value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	public Action execute(ProgressMonitor progressMonitor) throws Exception {
		Action action = super.execute(progressMonitor);
		action.setLocation(eObject.getName() + ".html");
		action.setId(eObject.eClass().getName() + "-" + encodeEPackage(eObject.getEPackage()) + "-" + eObject.getName());
		action.setText(eObject.getName() + typeParameters(eObject));
		return action;
	}
	
	/**
	 * Finds all type uses in the resourceset. 
	 * @return
	 */
	protected Collection<EClass> getUses() {
		return getUses(eObject);
	}	
	
}
