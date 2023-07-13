package org.nasdanika.html.model.html.gen;

import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.exec.util.ExecObjectLoaderExecutionParticipant;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.persistence.ObjectLoaderResourceFactory;

/**
 * {@link YamlLoadingSupplier} for Engineering {@link EPackage}s.
 * Registers exec- loader. 
 * @author Pavel
 *
 */
public abstract class HtmlGenObjectLoaderExecutionParticipant extends ExecObjectLoaderExecutionParticipant {

	public HtmlGenObjectLoaderExecutionParticipant(Context context, boolean parallel) {
		super(context, parallel);
	}
	
	@Override
	protected ObjectLoaderResourceFactory createObjectLoaderResorceFactory(ResourceSet resourceSet, ProgressMonitor progressMonitor) {
		resourceSet.getAdapterFactories().add(new HtmlAdapterFactory());
		return super.createObjectLoaderResorceFactory(resourceSet, progressMonitor);
	}

	@Override
	protected Collection<EPackage> getEPackages() {
		Collection<EPackage> ret = super.getEPackages(); 
		ret.add(HtmlPackage.eINSTANCE);
		return ret;
	}
	
}
