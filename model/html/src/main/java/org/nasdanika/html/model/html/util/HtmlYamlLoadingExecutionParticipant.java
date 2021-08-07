package org.nasdanika.html.model.html.util;

import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.exec.util.ExecYamlLoadingExecutionParticipant;
import org.nasdanika.html.model.html.HtmlPackage;

/**
 * {@link YamlLoadingSupplier} for Engineering {@link EPackage}s.
 * Registers exec- loader. 
 * @author Pavel
 *
 */
public abstract class HtmlYamlLoadingExecutionParticipant extends ExecYamlLoadingExecutionParticipant {

	public HtmlYamlLoadingExecutionParticipant(Context context) {
		super(context);
	}

	@Override
	protected Collection<EPackage> getEPackages() {
		Collection<EPackage> ret = super.getEPackages(); 
		ret.add(HtmlPackage.eINSTANCE);
		return ret;
	}
	
}
