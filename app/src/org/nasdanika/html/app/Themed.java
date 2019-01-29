package org.nasdanika.html.app;

import org.nasdanika.html.bootstrap.Theme;

/**
 * Mix-in interface for providing Bootstrap/Bootswatch {@link Theme}. 
 * This interface may be implemented by actions and action backing property sources.
 * @author Pavel Vlasov
 *
 */
public interface Themed {
	
	Theme getTheme();

}
