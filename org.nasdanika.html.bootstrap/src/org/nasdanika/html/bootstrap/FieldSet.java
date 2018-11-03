package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

/**
 * Bootstrap fieldset
 * @author Pavel Vlasov
 *
 */
public interface FieldSet extends BootstrapElement<org.nasdanika.html.FieldSet>, FieldContainer<FieldSet> {
	
	FieldSet disabled(boolean disabled);
	
	FieldSet disabled();
	
	Tag legend(Object... content);
	
}