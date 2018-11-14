package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.html.bootstrap.BootstrapFactory;

/**
 * Renders Bootstrap styled HTML using EClass metadata.
 * @author Pavel Vlasov
 *
 */
public class EClassBootstrapRenderer<RC extends RenderingContext> extends EClassHTMLRenderer<RC> {

	protected BootstrapFactory bootstrapFactory;
	
	public EClassBootstrapRenderer(EClass eClass, BootstrapFactory bootstrapFactory, RC renderingContext) {
		super(eClass, bootstrapFactory.getHTMLFactory(), renderingContext);
		this.bootstrapFactory = bootstrapFactory;
	}
	
	public EClassBootstrapRenderer(EClass eClass, RC renderingContext) {
		this(eClass, BootstrapFactory.INSTANCE, renderingContext);
	}

}
