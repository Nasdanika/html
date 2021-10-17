package org.nasdanika.html.model.app.gen;

import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.persistence.YamlResourceFactory;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.bootstrap.util.BootstrapYamlLoadingExecutionParticipant;

/**
 * {@link YamlLoadingSupplier} for Engineering {@link EPackage}s.
 * Registers exec- loader. 
 * @author Pavel
 *
 */
public abstract class AppGenYamlLoadingExecutionParticipant extends BootstrapYamlLoadingExecutionParticipant {

	public AppGenYamlLoadingExecutionParticipant(Context context) {
		super(context);
	}
	
	@Override
	protected YamlResourceFactory createYamlResorceFactory(ResourceSet resourceSet, ProgressMonitor progressMonitor) {
		resourceSet.getAdapterFactories().add(new AppAdapterFactory());
		return super.createYamlResorceFactory(resourceSet, progressMonitor);
	}

	@Override
	protected Collection<EPackage> getEPackages() {
		Collection<EPackage> ret = super.getEPackages(); 
		ret.add(AppPackage.eINSTANCE);
		return ret;
	}
	
}
