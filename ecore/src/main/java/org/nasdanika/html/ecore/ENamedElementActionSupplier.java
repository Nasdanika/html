package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.model.app.Action;

public class ENamedElementActionSupplier<T extends ENamedElement> extends EModelElementActionSupplier<T> {
	
	public ENamedElementActionSupplier(T value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	public Action execute(EClass contextEClass, ProgressMonitor progressMonitor) throws Exception {
		Action action = super.execute(contextEClass, progressMonitor);
		Context resourceContext = EObjectAdaptable.getResourceContext(eObject);
		String text = resourceContext.getString("label");
		action.setText(text == null ? org.nasdanika.common.Util.nameToLabel(eObject.getName()) : text);
		return action;
	}
	
	@Override
	public String name() {
		return super.name() + " " + eObject.getName();
	}
	

}
