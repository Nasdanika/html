package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.html.bootstrap.BootstrapFactory;

/**
 * Adds context help to the generated HTML - question mark items next to classes and features
 * elements which show tooltips, open modals with help content and link to the documentation system.
 * @author Pavel Vlasov
 *
 */
public class EClassBootstrapContextHelpRenderer extends EClassBootstrapRenderer {
	
	public EClassBootstrapContextHelpRenderer(EClass eClass, BootstrapFactory bootstrapFactory) {
		super(eClass, bootstrapFactory);
	}
	
	public EClassBootstrapContextHelpRenderer(EClass eClass) {
		super(eClass);
	}
	
	// TODO - ability to turn context help on and off

}
