package org.nasdanika.html;

/**
 * An interface for constructing HTML pages.
 * @author Pavel Vlasov
 *
 */
public interface HTMLPage extends Producer {
	
	/**
	 * Adds lang attribute to html tag.
	 * @param lang
	 * @return
	 */
	HTMLPage lang(Object lang);
	
	/**
	 * Adds content to html head element of the page.
	 * @param content
	 */
	HTMLPage head(Object... content);
	
	/**
	 * Adds external script to the header.
	 * @param url
	 */
	Tag script(Object url);
	
	/**
	 * Adds stylesheet to the header.
	 * @param url
	 */
	Tag stylesheet(Object url);
		
	/**
	 * Adds page title.
	 * @param title
	 */
	Tag title(Object title);

	/**
	 * Adds content to HTML body element of the page.
	 * @param content
	 * @return
	 */
	HTMLPage body(Object... content);
	
	HTMLFactory getFactory();
	
}
