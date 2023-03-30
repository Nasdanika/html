package org.nasdanika.html.ecore;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.ncore.util.NcoreUtil;

public class ENamedElementActionSupplier<T extends ENamedElement> extends EModelElementActionSupplier<T> {

	public ENamedElementActionSupplier(
			T value, 
			Context context, 
			java.util.function.Function<EPackage,String> ePackagePathComputer,
			Predicate<EModelElement> elementPredicate,
			BiFunction<ENamedElement, String, String> labelProvider) {
		super(value, context, ePackagePathComputer, elementPredicate, labelProvider);
	}
	
	@Override
	public Action execute(EClass contextEClass, ProgressMonitor progressMonitor) {
		Action action = super.execute(contextEClass, progressMonitor);
		action.setText(labelProvider.apply(eObject, getDefaultLabel(progressMonitor)));
		return action;
	}
	
	protected String getDefaultLabel(ProgressMonitor progressMonitor) {
		return NcoreUtil.getNasdanikaAnnotationDetail(eObject, "label", eObject.getName());
	}
	
	@Override
	public String name() {
		return super.name() + " " + eObject.getName();
	}
	

}
