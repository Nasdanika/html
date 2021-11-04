package org.nasdanika.html.ecore;

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
	protected Table propertiesTable(ProgressMonitor monitor) throws Exception {
		Table propertiesTable = super.propertiesTable(monitor);
		EReference opposite = eObject.getEOpposite();
		if (opposite == null) {
			String oName = NcoreUtil.getNasdanikaAnnotationDetail(eObject, "opposite");
			EClass refType = eObject.getEReferenceType();
			for (EReference ref: refType.getEAllReferences()) {
				if (ref.getName().equals(oName)) {
					opposite = ref;
					break;
				}
				String ooName = NcoreUtil.getNasdanikaAnnotationDetail(refType, "opposite");
				if (eObject.getName().equals(ooName)) {
					opposite = ref;
					break;
				}
			}
		}
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
