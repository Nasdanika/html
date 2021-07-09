package org.nasdanika.html.knockout;

import java.util.Collection;

public interface KnockoutBindingsSource {
	
	/**
	 * Binding interface for generation of observables.
	 * @author Pavel Vlasov
	 *
	 */
	interface Binding {
		
		String getName();
		
		Object getInitialValue();
		
		boolean isArray();
		
	}
	
	/**
	 * @return Returns all bindings in this element and sub-elements.
	 */
	Collection<Binding> getAllBindings();
	

}
