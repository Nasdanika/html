package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.knockout.KnockoutFactory;

/**
 * Renderer which uses KnockoutJS for binding.
 * @author Pavel Vlasov
 *
 */
public class KnockoutEObjectRenderer<T extends EObject> extends EObjectRenderer<T> {

	protected KnockoutFactory knockoutFactory;

	public KnockoutEObjectRenderer(T eObject, BootstrapFactory  bootstrapFactory, KnockoutFactory knockoutFactory) {
		super(eObject, bootstrapFactory);
		this.knockoutFactory = knockoutFactory;
	}
	
	public KnockoutEObjectRenderer(T eObject) {
		super(eObject);
		this.knockoutFactory = KnockoutFactory.INSTANCE;
	}
	
	// TODO - configurable which aspects to bind, e.g. only the edit form.
	
	// TODO - render view model script too, possibly as a module. 

}
