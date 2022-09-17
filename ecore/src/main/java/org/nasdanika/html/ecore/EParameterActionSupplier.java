package org.nasdanika.html.ecore;

import java.security.NoSuchAlgorithmException;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.nasdanika.common.Context;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Action;

public class EParameterActionSupplier extends ETypedElementActionSupplier<EParameter> {

	public EParameterActionSupplier(
			EParameter value, 
			Context context, 
			java.util.function.Function<EPackage,String> ePackagePathComputer,
			Predicate<EModelElement> elementPredicate,
			BiFunction<ENamedElement, String, String> labelProvider) {
		super(value, context, ePackagePathComputer, elementPredicate, labelProvider);
	}
	
	@Override
	public Action execute(EClass contextEClass, ProgressMonitor progressMonitor) {
		Action action = super.execute(contextEClass, progressMonitor);
		try {
			action.setName(EOperationActionSupplier.eOperationSignature(eObject.getEOperation(), this::encodeEPackage) + "--" + eObject.getName());
		} catch (NoSuchAlgorithmException e) {
			throw new NasdanikaException(e);
		}
		return action;
	}

}
