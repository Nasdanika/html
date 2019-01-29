package org.nasdanika.html.bootstrap;

import org.nasdanika.html.HTMLPage;

/**
 * Enumeration for out-of-the-box and Bootswatch themes.
 * @author Pavel Vlasov
 *
 */
public enum Theme {
	/** Default Bootstrap theme **/
	Default("https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"),
	/** Bootswatch theme **/
	Cerulean,
	/** Bootswatch theme **/
	Cosmo,
	/** Bootswatch theme **/
	Cyborg,
	/** Bootswatch theme **/
	Darkly,
	/** Bootswatch theme **/
	Flatly,
	/** Bootswatch theme **/
	Journal,
	/** Bootswatch theme **/
	Litera,
	/** Bootswatch theme **/
	Lumen,
	/** Bootswatch theme **/
	Lux,
	/** Bootswatch theme **/
	Materia,
	/** Bootswatch theme **/
	Minty,	
	/** Bootswatch theme **/
	Pulse,
	/** Bootswatch theme **/
	Sandstone,
	/** Bootswatch theme **/
	Simplex,
	/** Bootswatch theme **/
	Sketchy,
	/** Bootswatch theme **/
	Slate,
	/** Bootswatch theme **/
	Solar,
	/** Bootswatch theme **/
	Spacelab,
	/** Bootswatch theme **/
	Superhero,
	/** Bootswatch theme **/
	United,
	/** Bootswatch theme **/
	Yeti;
	
	/**
	 * id of bootstrap stylesheet link head tag.
	 */
	public static final String STYLESHEET_ID = "nsd-bootstrap-theme-stylesheet";
	
	/**
	 * CDN location of theme stylesheet.
	 */
	public final String stylesheetCdnURL;
	
	/**
	 * Adds CDN stylesheet declaration to the page.
	 * @param page
	 * @return
	 */
	public <P extends HTMLPage> P cdn(P page) {
		page.stylesheet(stylesheetCdnURL).id(STYLESHEET_ID);
		return page;
	}
	
	private Theme() {
		stylesheetCdnURL = "https://stackpath.bootstrapcdn.com/bootswatch/4.1.3/"+name().toLowerCase()+"/bootstrap.min.css";
	}
	
	private Theme(String stylesheetCdnURL) {
		this.stylesheetCdnURL = stylesheetCdnURL;
	}
}
