package org.nasdanika.html.model.html.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

public class HtmlModelUtil {
	
	public static boolean isBlank(String str) {
		return str == null || str.trim().isEmpty(); 
	}
	
	/**
	 * Iterates over registered ecore packages. Collects and instantiates concrete subclasses of specified type. 
	 * @return
	 */
	public static List<EObject> collect(EClass type) {
		List<EObject> ret = new ArrayList<>();
		IExtensionRegistry registry = RegistryFactory.getRegistry();
		if (registry != null) {
			IConfigurationElement[] configElems = registry.getConfigurationElementsFor("org.eclipse.emf.ecore.generated_package");
			for (IConfigurationElement elem : configElems) {
				String uri = elem.getAttribute("uri");
				if (!isBlank(uri)) {
					EPackage epkg = EPackage.Registry.INSTANCE.getEPackage(uri);
					if (epkg != null) {
						for (EClassifier eClassifier: epkg.getEClassifiers()) {
							if (eClassifier instanceof EClass 
									&& !((EClass) eClassifier).isAbstract() 
									&& type.isSuperTypeOf((EClass) eClassifier)) {
								
								EFactory eFactory = epkg.getEFactoryInstance();
								ret.add(eFactory.create((EClass) eClassifier));						
							}
						}
					}
				}
			}
		}
		return ret;
	}	

}
