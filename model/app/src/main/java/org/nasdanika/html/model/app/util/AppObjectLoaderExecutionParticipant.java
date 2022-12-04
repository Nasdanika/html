package org.nasdanika.html.model.app.util;

import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.bootstrap.util.BootstrapObjectLoaderExecutionParticipant;

/**
 * Registers {@link AppPackage}
 * @author Pavel
 *
 */
public abstract class AppObjectLoaderExecutionParticipant extends BootstrapObjectLoaderExecutionParticipant {

	public AppObjectLoaderExecutionParticipant(Context context) {
		super(context);
	}

	@Override
	protected Collection<EPackage> getEPackages() {
		Collection<EPackage> ret = super.getEPackages(); 
		ret.add(AppPackage.eINSTANCE);
		return ret;
	}
	
}
