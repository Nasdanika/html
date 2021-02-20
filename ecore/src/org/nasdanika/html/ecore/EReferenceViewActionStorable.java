package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.EObjectAdaptable;

public class EReferenceViewActionStorable extends EStructuralFeatureViewActionStorable<EReference> {

	public EReferenceViewActionStorable(EReference value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
//	@Override
//	protected Table propertiesTable(ProgressMonitor monitor) throws Exception {
//		Table propertiesTable = super.propertiesTable(monitor);
//		EReference opposite = eObject.getEOpposite();
//		if (opposite != null) {
//			ViewActionSupplier oppositeViewActionSupplier = EObjectAdaptable.adaptTo(opposite, ViewActionSupplier.class);
//			if (oppositeViewActionSupplier == null) {
//				addRow(propertiesTable, "Opposite").add(wrap(opposite.getName()));
//			} else {
//				ActionLink link = ComponentsFactory.eINSTANCE.createActionLink();
//				link.setTarget(oppositeViewActionSupplier.getAction(monitor));
//				addRow(propertiesTable, "Opposite").add(link);				
//			}
//		}
//		return propertiesTable;
//	}

}
