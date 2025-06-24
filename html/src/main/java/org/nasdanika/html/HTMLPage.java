package org.nasdanika.html;

/**
 * An interface for constructing HTML pages.
 * @author Pavel Vlasov
 *
 */
public interface HTMLPage extends Producer<String> {
	
	/**
	 * Adds lang attribute to html tag.
	 * @param lang
	 * @return
	 */
	HTMLPage lang(Object lang);
	
	/**
	 * Adds content before the opening html tag.
	 * @param content
	 */
	HTMLPage prolog(Object... content);
	
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
	
	/**
	 * Adds content after the closing html tag.
	 * @param content
	 */
	HTMLPage epilog(Object... content);
	
	HTMLFactory getFactory();
	
}
