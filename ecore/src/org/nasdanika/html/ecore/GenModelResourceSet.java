package org.nasdanika.html.ecore;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class GenModelResourceSet extends ResourceSetImpl {
	
	public GenModelResourceSet() {
		getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
		getPackageRegistry().put(GenModelPackage.eNS_URI, GenModelPackage.eINSTANCE);
	}
	
	/**
	 * Finds GenModel in the registry and loads to this resource set.
	 * @param namespaceURI Model namespace URI.
	 * @return GenModel for a given namespace URI or null if a registration is not found.
	 */
	public Resource loadGenModel(String namespaceURI) {
		IExtensionRegistry registry = RegistryFactory.getRegistry();
		if (registry != null) {
			IConfigurationElement[] configElems = registry.getConfigurationElementsFor("org.eclipse.emf.ecore.generated_package");
			for (IConfigurationElement elem : configElems) {
				String uri = elem.getAttribute("uri");
				if (uri.equals(namespaceURI)) {					
					String modelLocation = elem.getContributor().getName()+"/"+elem.getAttribute("genModel");
					URI modelUri = URI.createPlatformPluginURI(modelLocation, false);
					return getResource(modelUri, true);
				}
			}
		}
		return null;		
	}

	/**
	 * @return Top level EPackage's.
	 */
	public List<EPackage> getEPackages() {
		List<EPackage> ret = new ArrayList<>();
		for (Resource res: new ArrayList<>(getResources())) {
			for (Object contents: res.getContents()) {
				if (contents instanceof GenModel) {
					for (GenPackage genPackage: ((GenModel) contents).getGenPackages()) {
						ret.add(genPackage.getEcorePackage());
					}
				}
			}
		}		
		return ret;
	}
	
}
