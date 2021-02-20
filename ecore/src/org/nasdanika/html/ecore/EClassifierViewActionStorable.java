package org.nasdanika.html.ecore;

import java.util.Collection;
import java.util.Objects;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

public class EClassifierViewActionStorable<T extends EClassifier> extends ENamedElementViewActionStorable<T> {

	public EClassifierViewActionStorable(T value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
//	
//	@Override
//	protected Action create(ProgressMonitor progressMonitor) throws Exception {
//		Action action = super.create(progressMonitor);
//		
//		// Instance class
//		Class<?> instanceClass = eObject.getInstanceClass();
//		if (instanceClass == null) {
//			EPackage registeredPackage = getRegisteredPackage();
//			if (registeredPackage != null) {
//				EClassifier registeredClassifier = registeredPackage.getEClassifier(eObject.getName());
//				if (registeredClassifier != null) {
//					instanceClass = registeredClassifier.getInstanceClass();
//				}
//			}
//		}
//		
//		if (instanceClass != null) {
//			String instanceClassName = instanceClass.getName();
//			Object jdcbm = context.get(EcoreDocumentationGeneratorCommand.JAVADOC_CONTEXT_BUILDER_MOUNT);
//			if (jdcbm != null) {
//				instanceClassName = "${" + jdcbm + instanceClassName + "}";
//			}
//			String mc = action.getMarkdownContent();
//			if (Util.isBlank(mc)) {
//				action.setMarkdownContent("Instance class: ``" + instanceClassName + "``");
//			} else {
//				action.setMarkdownContent(
//						"Instance class: ``" + 
//						instanceClassName + 
//						"``" +
//						System.lineSeparator() +
//						System.lineSeparator() +
//						mc);				
//			}
//		}
//						
//		action.setId(eObject.eClass().getName() + "-" + encodeEPackage(eObject.getEPackage()) + "-" + eObject.getName());
//		action.setActivator(eObject.getName()+".html");
//		
//		return action;
//	}
//
//	private EPackage getRegisteredPackage() {
//		String nsURI = eObject.getEPackage().getNsURI();
//		Object value = EPackage.Registry.INSTANCE.get(nsURI);
//		if (value instanceof EPackage) {
//			return (EPackage) value;
//		}
//		if (value instanceof EPackage.Descriptor) {
//			return Objects.requireNonNull(((EPackage.Descriptor) value).getEPackage(), "EPackage is null for " + nsURI);  
//		}
//		if (value == null) {
//			throw new NullPointerException("No registry entry for " + nsURI);
//		}
//		throw new NasdanikaException("Unexpected registry value for " + nsURI + ": " + value);
//	}
//	
//	@Override
//	public void configure(ProgressMonitor monitor) throws Exception {
//		super.configure(monitor);		
//		action.setText(eObject.getName() + typeParameters(eObject));
//	}
	
	/**
	 * Finds all type uses in the resourceset. 
	 * @return
	 */
	protected Collection<EClass> getUses() {
		return getUses(eObject);
	}	
	
}
