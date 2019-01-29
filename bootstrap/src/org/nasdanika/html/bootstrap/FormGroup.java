package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

public interface FormGroup extends BootstrapElement<Tag,FormGroup> {
	
	FormGroup large();
	
	FormGroup large(boolean large);
	
	FormGroup small();
	
	FormGroup small(boolean small);
	
	FormGroup plainText();
	
	FormGroup plainText(boolean plainText);
	
	/**
	 * Applicable only for checkboxes and radios
	 * @return
	 */
	FormGroup inline();
	
	FormGroup inline(boolean inline);
	
	FormGroup valid(Object... feedback);
	
	FormGroup invalid(Object... feedback);

}
