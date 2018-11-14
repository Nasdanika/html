package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.knockout.KnockoutFactory;

/**
 * Renderer which uses KnockoutJS for binding.
 * @author Pavel Vlasov
 *
 */
public class KnockoutEObjectRenderer<T extends EObject, RC extends RenderingContext> extends EObjectRenderer<T,RC> {

	protected KnockoutFactory knockoutFactory;

	public KnockoutEObjectRenderer(T eObject, BootstrapFactory  bootstrapFactory, KnockoutFactory knockoutFactory, RC renderingContext) {
		super(eObject, bootstrapFactory, renderingContext);
		this.knockoutFactory = knockoutFactory;
	}
	
	public KnockoutEObjectRenderer(T eObject, RC renderingContext) {
		super(eObject, renderingContext);
		this.knockoutFactory = KnockoutFactory.INSTANCE;
	}
	
	// TODO - configurable which aspects to bind, e.g. only the edit form.
	
	// TODO - render view model script too, possibly as a module. 

}
