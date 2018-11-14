package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.html.bootstrap.BootstrapFactory;

/**
 * Adds context help to the generated HTML - question mark items next to classes and features
 * elements which show tooltips, open modals with help content and link to the documentation system.
 * @author Pavel Vlasov
 *
 */
public class EClassBootstrapContextHelpRenderer<RC extends RenderingContext> extends EClassBootstrapRenderer<RC> {
	
	public EClassBootstrapContextHelpRenderer(EClass eClass, BootstrapFactory bootstrapFactory, RC renderingContext) {
		super(eClass, bootstrapFactory, renderingContext);
	}
	
	public EClassBootstrapContextHelpRenderer(EClass eClass, RC renderingContext) {
		super(eClass, renderingContext);
	}
	
	// TODO - ability to turn context help on and off
	
	// consumer to add modal declarations

}
