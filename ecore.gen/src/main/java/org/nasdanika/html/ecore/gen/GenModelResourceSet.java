package org.nasdanika.html.ecore.gen;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
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
