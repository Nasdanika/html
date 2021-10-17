package org.nasdanika.html.model.html.gen;

import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.persistence.YamlResourceFactory;
import org.nasdanika.exec.util.ExecYamlLoadingExecutionParticipant;
import org.nasdanika.html.model.html.HtmlPackage;

/**
 * {@link YamlLoadingSupplier} for Engineering {@link EPackage}s.
 * Registers exec- loader. 
 * @author Pavel
 *
 */
public abstract class HtmlGenYamlLoadingExecutionParticipant extends ExecYamlLoadingExecutionParticipant {

	public HtmlGenYamlLoadingExecutionParticipant(Context context) {
		super(context);
	}
	
	@Override
	protected YamlResourceFactory createYamlResorceFactory(ResourceSet resourceSet, ProgressMonitor progressMonitor) {
		resourceSet.getAdapterFactories().add(new HtmlAdapterFactory());
		return super.createYamlResorceFactory(resourceSet, progressMonitor);
	}

	@Override
	protected Collection<EPackage> getEPackages() {
		Collection<EPackage> ret = super.getEPackages(); 
		ret.add(HtmlPackage.eINSTANCE);
		return ret;
	}
	
}
