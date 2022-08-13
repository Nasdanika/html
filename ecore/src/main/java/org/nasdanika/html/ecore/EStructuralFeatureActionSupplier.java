package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.model.app.Action;

public class EStructuralFeatureActionSupplier<T extends EStructuralFeature> extends ETypedElementActionSupplier<T> implements EcoreActionSupplier {

	public EStructuralFeatureActionSupplier(T value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
		
	@Override
	public Action execute(EClass contextEClass, ProgressMonitor progressMonitor) {
		Action action = super.execute(contextEClass, progressMonitor);
	
		EClass eContainingClass = eObject.getEContainingClass();
		action.setId(encodeEPackage(
				eContainingClass.getEPackage())
				+ "-"
				+ eContainingClass.getName()
				+ "-"
				+ eObject.eClass().getName()
				+ "-" 				
				+ eObject.getName());

		action.setName(eObject.eClass().getName() + "-" + eObject.getName());
		return action;
	}

	@Override
	protected Table propertiesTable(EClass contextEClass, ProgressMonitor monitor) {		
		Table table = super.propertiesTable(contextEClass, monitor);
		addRow(table, "Changeable").add(eObject.isChangeable());
		addRow(table, "Derived").add(eObject.isDerived());
		if (contextEClass != null) {
			addRow(table, "Declaring class").add(link(eObject.getEContainingClass(), contextEClass));
		}
		
		return table;
	}
	
	
}
