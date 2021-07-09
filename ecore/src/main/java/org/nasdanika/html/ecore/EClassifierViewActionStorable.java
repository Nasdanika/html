package org.nasdanika.html.ecore;

import java.net.URL;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;

public class EClassifierViewActionStorable<T extends EClassifier> extends ENamedElementViewActionStorable<T> {

	public EClassifierViewActionStorable(T value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	public Map<String, Map<String, Object>> store(URL base, ProgressMonitor progressMonitor) throws Exception {
		Map<String, Map<String, Object>> data = super.store(base, progressMonitor);
		put(data, "href", eObject.getName() + ".html");
		put(data, "id", eObject.eClass().getName() + "-" + encodeEPackage(eObject.getEPackage()) + "-" + eObject.getName());
		put(data, "text", eObject.getName() + typeParameters(eObject));
		
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
		
		return data;
	}
	
	/**
	 * Finds all type uses in the resourceset. 
	 * @return
	 */
	protected Collection<EClass> getUses() {
		return getUses(eObject);
	}	
	
}
