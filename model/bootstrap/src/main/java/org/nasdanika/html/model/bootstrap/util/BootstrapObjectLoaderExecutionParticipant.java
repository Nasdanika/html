package org.nasdanika.html.model.bootstrap.util;

import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.html.util.HtmlObjectLoaderExecutionParticipant;

/**
 * Registers {@link BootstrapPackage}
 * @author Pavel
 *
 */
public abstract class BootstrapObjectLoaderExecutionParticipant extends HtmlObjectLoaderExecutionParticipant {

	public BootstrapObjectLoaderExecutionParticipant(Context context) {
		super(context);
	}

	@Override
	protected Collection<EPackage> getEPackages() {
		Collection<EPackage> ret = super.getEPackages(); 
		ret.add(BootstrapPackage.eINSTANCE);
		return ret;
	}
	
}
