package org.nasdanika.html.model.bootstrap.gen;

import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.persistence.YamlResourceFactory;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.html.util.HtmlYamlLoadingExecutionParticipant;

/**
 * {@link YamlLoadingSupplier} for Engineering {@link EPackage}s.
 * Registers exec- loader. 
 * @author Pavel
 *
 */
public abstract class BootstrapGenYamlLoadingExecutionParticipant extends HtmlYamlLoadingExecutionParticipant {

	public BootstrapGenYamlLoadingExecutionParticipant(Context context) {
		super(context);
	}
	
	@Override
	protected YamlResourceFactory createYamlResorceFactory(ResourceSet resourceSet, ProgressMonitor progressMonitor) {
		resourceSet.getAdapterFactories().add(new BootstrapAdapterFactory());
		return super.createYamlResorceFactory(resourceSet, progressMonitor);
	}

	@Override
	protected Collection<EPackage> getEPackages() {
		Collection<EPackage> ret = super.getEPackages(); 
		ret.add(BootstrapPackage.eINSTANCE);
		return ret;
	}
	
}
