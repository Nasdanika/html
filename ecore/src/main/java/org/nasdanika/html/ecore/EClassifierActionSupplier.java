package org.nasdanika.html.ecore;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
import org.nasdanika.html.model.app.Action;

public class EClassifierActionSupplier<T extends EClassifier> extends ENamedElementActionSupplier<T> {

	private Function<String, String> javadocResolver;

	public EClassifierActionSupplier(
			T value, 
			Context context, 
			java.util.function.Function<EPackage,String> ePackagePathComputer,
			java.util.function.Function<String, String> javadocResolver) {
		super(value, context, ePackagePathComputer);
		this.javadocResolver = javadocResolver;
	}
	
	@Override
	protected void header(Action action, ProgressMonitor progressMonitor) throws Exception {
		Class<?> instanceClass = eObject.getInstanceClass();
		if (instanceClass == null) {
			EPackage registeredPackage = getRegisteredPackage();
			if (registeredPackage != null) {
				EClassifier registeredClassifier = registeredPackage.getEClassifier(eObject.getName());
				if (registeredClassifier != null) {
					instanceClass = registeredClassifier.getInstanceClass();
				}
			}
		}
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
	public Action execute(EClass contextEClass, ProgressMonitor progressMonitor) throws Exception {
		Action action = super.execute(contextEClass, progressMonitor);
		action.setLocation(eObject.getName() + ".html");
		action.setId(eObject.eClass().getName() + "-" + encodeEPackage(eObject.getEPackage()) + "-" + eObject.getName());
		action.setText(eObject.getName() + typeParameters(eObject));				 
		return action;
	}
	
	/**
	 * Finds all type uses in the resourceset. 
	 * @return
	 */
	protected Collection<EClass> getUses() {
		return getUses(eObject);
	}

	private EPackage getRegisteredPackage() {
		String nsURI = eObject.getEPackage().getNsURI();
		Object value = EPackage.Registry.INSTANCE.get(nsURI);
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
