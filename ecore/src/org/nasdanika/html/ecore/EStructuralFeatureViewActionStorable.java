package org.nasdanika.html.ecore;

import java.net.URL;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.bootstrap.Table;

public class EStructuralFeatureViewActionStorable<T extends EStructuralFeature> extends ETypedElementViewActionStorable<T> {

	public EStructuralFeatureViewActionStorable(T value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	public Map<String, Map<String, Object>> store(URL base, ProgressMonitor progressMonitor) throws Exception {
		Map<String, Map<String, Object>> data = super.store(base, progressMonitor);
		put(data, "role", Action.Role.SECTION);
		EClass eContainingClass = eObject.getEContainingClass();
		put(data, "id", encodeEPackage(
				eContainingClass.getEPackage())
				+ "-"
				+ eContainingClass.getName()
				+ "-"
				+ eObject.eClass().getName()
				+ "-" 				
				+ eObject.getName());

		put(data, "href", eContainingClass.getName() + ".html#" + eObject.eClass().getName() + "-" + eObject.getName());
		return data;
	}

	@Override
	protected Table propertiesTable(ProgressMonitor monitor) throws Exception {		
		Table table = super.propertiesTable(monitor);
		addRow(table, "Changeable").add(eObject.isChangeable());
		addRow(table, "Derived").add(eObject.isDerived());
		
		return table;
	}
	
	
}
