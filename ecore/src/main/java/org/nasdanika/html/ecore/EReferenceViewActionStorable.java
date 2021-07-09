package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.html.bootstrap.Table;

public class EReferenceViewActionStorable extends EStructuralFeatureViewActionStorable<EReference> {

	public EReferenceViewActionStorable(EReference value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	protected Table propertiesTable(ProgressMonitor monitor) throws Exception {
		Table propertiesTable = super.propertiesTable(monitor);
		EReference opposite = eObject.getEOpposite();
		if (opposite != null) {
			String oPath = path(opposite.getEContainingClass());
			if (Util.isBlank(oPath)) {
				addRow(propertiesTable, "Opposite").add(opposite.getName());
			} else {
				addRow(propertiesTable, "Opposite").add("<a href=\"" + oPath + "#EReference-" + opposite.getName() +"\">" + opposite.getName() + "</a>");				
			}
		}		
		return propertiesTable;
	}

}
