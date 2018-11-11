package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.html.bootstrap.BootstrapFactory;

/**
 * Renders Bootstrap styled HTML using EClass metadata.
 * @author Pavel Vlasov
 *
 */
public class EClassBootstrapRenderer extends EClassHTMLRenderer {

	protected BootstrapFactory bootstrapFactory;
	
	public EClassBootstrapRenderer(EClass eClass, BootstrapFactory bootstrapFactory) {
		super(eClass, bootstrapFactory.getHTMLFactory());
		this.bootstrapFactory = bootstrapFactory;
	}
	
	public EClassBootstrapRenderer(EClass eClass) {
		this(eClass, BootstrapFactory.INSTANCE);
	}

}
