package org.nasdanika.html.ecore;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.ncore.util.NcoreUtil;

public class EReferenceActionSupplier extends EStructuralFeatureActionSupplier<EReference> {

	public EReferenceActionSupplier(EReference value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	protected Table propertiesTable(EClass contextEClass, ProgressMonitor monitor) {
		Table propertiesTable = super.propertiesTable(contextEClass, monitor);
		EReference opposite = NcoreUtil.getOpposite(eObject);
		if (opposite != null) {
			String oPath = path(opposite.getEContainingClass(), contextEClass);
			if (Util.isBlank(oPath)) {
				addRow(propertiesTable, "Opposite").add(opposite.getName());
			} else {
				addRow(propertiesTable, "Opposite").add("<a href=\"" + oPath + "#EReference-" + opposite.getName() +"\">" + opposite.getName() + "</a>");				
			}
		}	
		EList<EAttribute> eKeys = eObject.getEKeys();
		if (!eKeys.isEmpty()) {
			if (eKeys.size() == 1) {
				addRow(propertiesTable, "Key").add(eKeys.get(0).getName());			
			} else {
				StringBuilder vb = new StringBuilder("<ol>");
				eKeys.forEach(eKey -> vb.append("<li>").append(eKey.getName()).append("</li>"));
				vb.append("</ol>");
				addRow(propertiesTable, "Key").add(vb.toString());			
			}
		}
		return propertiesTable;
	}

}
