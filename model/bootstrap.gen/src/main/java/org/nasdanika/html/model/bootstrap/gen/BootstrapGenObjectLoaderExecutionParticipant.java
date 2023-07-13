package org.nasdanika.html.model.bootstrap.gen;

import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.html.util.HtmlObjectLoaderExecutionParticipant;
import org.nasdanika.persistence.ObjectLoaderResourceFactory;

/**
 * {@link YamlLoadingSupplier} for Engineering {@link EPackage}s.
 * Registers exec- loader. 
 * @author Pavel
 *
 */
public abstract class BootstrapGenObjectLoaderExecutionParticipant extends HtmlObjectLoaderExecutionParticipant {

	public BootstrapGenObjectLoaderExecutionParticipant(Context context, boolean parallel) {
		super(context, parallel);
	}
	
	@Override
	protected ObjectLoaderResourceFactory createObjectLoaderResorceFactory(ResourceSet resourceSet, ProgressMonitor progressMonitor) {
		resourceSet.getAdapterFactories().add(new BootstrapAdapterFactory());
		return super.createObjectLoaderResorceFactory(resourceSet, progressMonitor);
	}

	@Override
	protected Collection<EPackage> getEPackages() {
		Collection<EPackage> ret = super.getEPackages(); 
		ret.add(BootstrapPackage.eINSTANCE);
		return ret;
	}
	
}
