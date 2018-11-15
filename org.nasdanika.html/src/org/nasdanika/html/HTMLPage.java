package org.nasdanika.html;

/**
 * An interface for constructing HTML pages.
 * @author Pavel Vlasov
 *
 */
public interface HTMLPage extends Producer {
	
	/**
	 * Adds content to html head element of the page.
	 * @param content
	 */
	HTMLPage head(Object... content);
	
	/**
	 * Adds external script to the header.
	 * @param url
	 */
	HTMLPage script(Object url);
	
	/**
	 * Adds stylesheet to the header.
	 * @param url
	 */
	HTMLPage stylesheet(Object url);
		
	/**
	 * Adds page title.
	 * @param title
	 */
	HTMLPage title(Object title);

	/**
	 * Adds content to HTML body element of the page.
	 * @param content
	 * @return
	 */
	HTMLPage body(Object... content);
	
}
