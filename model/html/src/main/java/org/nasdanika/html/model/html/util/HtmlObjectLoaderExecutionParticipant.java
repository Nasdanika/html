package org.nasdanika.html.model.html.util;

import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.exec.util.ExecObjectLoaderExecutionParticipant;
import org.nasdanika.html.model.html.HtmlPackage;

/**
 * Registers {@link HtmlPackage} 
 * @author Pavel
 *
 */
public abstract class HtmlObjectLoaderExecutionParticipant extends ExecObjectLoaderExecutionParticipant {

	public HtmlObjectLoaderExecutionParticipant(Context context) {
		super(context);
	}

	@Override
	protected Collection<EPackage> getEPackages() {
		Collection<EPackage> ret = super.getEPackages(); 
		ret.add(HtmlPackage.eINSTANCE);
		return ret;
	}
	
}
