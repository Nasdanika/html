package org.nasdanika.html.app;

import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Producer;

/**
 * An interface for building HTML applications which have
 * header, navigation bar, left panel, content panel, and footer.
 * @author Pavel Vlasov
 *
 */
public interface Application extends Producer, AutoCloseable {
	
	/**
	 * Adds header content. 
	 * @param content
	 * @return
	 */
	Application header(Object... content);
	
	/**
	 * Adds top navigation content. 
	 * @param content
	 * @return
	 */
	Application navigationBar(Object... content);
	
	/**
	 * Adds navigation panel content. 
	 * @param content
	 * @return
	 */
	Application navigationPanel(Object... content);
	
	/**
	 * Adds content panel content. 
	 * @param content
	 * @return
	 */
	Application content(Object... content);	
	
	/**
	 * Adds footer content.
	 * @param content
	 * @return
	 */
	Application footer(Object... content);
		
	/**
	 * @return HTML page backing this application.
	 */
	HTMLPage getHTMLPage();
}
