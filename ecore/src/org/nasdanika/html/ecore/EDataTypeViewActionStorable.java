package org.nasdanika.html.ecore;

import java.util.Collection;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.EObjectAdaptable;

public class EDataTypeViewActionStorable extends EClassifierViewActionStorable<EDataType> {

	public EDataTypeViewActionStorable(EDataType value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
//	@Override
//	protected Action create(ProgressMonitor progressMonitor) throws Exception {
//		Action ret = super.create(progressMonitor);
//		
//		// Uses
//		Collection<EClass> uses = getUses().stream().sorted((a,b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());
//		if (!uses.isEmpty()) {
//			ListOfActions usesList = ComponentsFactory.eINSTANCE.createListOfActions();
//			ret.getContent().add(usesList);
//			usesList.setDepth(1);
//			usesList.setTooltips(true);
//			usesList.setHeader("Uses");
//			for (EClass use: uses) {
//				ViewActionSupplier uvas = EObjectAdaptable.adaptTo(use, ViewActionSupplier.class);
//				if (uvas != null) {
//					usesList.getActions().add(uvas.getAction(progressMonitor));
//				}
//			}
//		}
//		
//		return ret;
//	}
	
}
