package org.nasdanika.html.model.bootstrap.util;

import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.html.util.HtmlYamlLoadingExecutionParticipant;

/**
 * {@link YamlLoadingSupplier} for Bootstrap.
 * Registers exec- loader. 
 * @author Pavel
 *
 */
public abstract class BootstrapYamlLoadingExecutionParticipant extends HtmlYamlLoadingExecutionParticipant {

	public BootstrapYamlLoadingExecutionParticipant(Context context) {
		super(context);
	}

	@Override
	protected Collection<EPackage> getEPackages() {
		Collection<EPackage> ret = super.getEPackages(); 
		ret.add(BootstrapPackage.eINSTANCE);
		return ret;
	}
	
}
