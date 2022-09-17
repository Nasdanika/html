package org.nasdanika.html.ecore;

import java.util.Collection;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
import org.nasdanika.html.model.app.Action;

public class EClassifierActionSupplier<T extends EClassifier> extends ENamedElementActionSupplier<T> {

	private Function<String, String> javadocResolver;
	protected Class<?> instanceClass;
	private Function<String, Object> ePackageResolver;

	public EClassifierActionSupplier(
			T value, 
			Context context, 
			java.util.function.Function<EPackage,String> ePackagePathComputer,
			java.util.function.Function<String, String> javadocResolver,
			java.util.function.Function<String, Object> ePackageResolver,
			Predicate<EModelElement> elementPredicate,
			BiFunction<ENamedElement, String, String> labelProvider) {
		super(value, context, ePackagePathComputer, elementPredicate, labelProvider);
		this.javadocResolver = javadocResolver;
		this.ePackageResolver = ePackageResolver;
		instanceClass = value.getInstanceClass();
		if (instanceClass == null) {
			EPackage registeredPackage = getRegisteredPackage(value);
			if (registeredPackage != null) {
				EClassifier registeredClassifier = registeredPackage.getEClassifier(value.getName());
				if (registeredClassifier != null) {
					instanceClass = registeredClassifier.getInstanceClass();
				}
			}
		}
	}
	
	@Override
	protected void header(Action action, ProgressMonitor progressMonitor) {
		if (instanceClass != null) {
			String instanceClassName = instanceClass.getName();
			if (javadocResolver != null) {
				instanceClassName = javadocResolver.apply(instanceClassName);
			}
			
			Text text = ContentFactory.eINSTANCE.createText();
			text.setContent("<div class='text-monospace'>" + instanceClassName + "</div>");
			action.getContent().add(text);
		}		
	}
	
	@Override
	public Action execute(EClass contextEClass, ProgressMonitor progressMonitor) {
		Action action = super.execute(contextEClass, progressMonitor);
		action.setLocation(eObject.getName() + ".html");
		action.setId(eObject.eClass().getName() + "-" + encodeEPackage(eObject.getEPackage()) + "-" + eObject.getName());
		return action;
	}
	
	@Override
	protected String getDefaultLabel(ProgressMonitor progressMonitor) {
		return eObject.getName() + typeParameters(eObject);
	}
	
	/**
	 * Finds all type uses in the resourceset. 
	 * @return
	 */
	protected Collection<EClass> getUses() {
		return getUses(eObject);
	}

	private EPackage getRegisteredPackage(EClassifier eObject) {
		String nsURI = eObject.getEPackage().getNsURI();
		Object value = ePackageResolver.apply(nsURI);
		if (value instanceof EPackage) {
			return (EPackage) value;
		}
		if (value instanceof EPackage.Descriptor) {
			return Objects.requireNonNull(((EPackage.Descriptor) value).getEPackage(), "EPackage is null for " + nsURI);  
		}
		
		if (value instanceof EPackage) {
			return (EPackage) value;
		}
		return null;
	}	
	
}
