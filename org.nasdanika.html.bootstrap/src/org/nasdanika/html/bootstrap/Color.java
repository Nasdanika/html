package org.nasdanika.html.bootstrap;

/**
 * Bootstrap colors. Not all colors are applicable in all situations. E.g. TRANSPARENT is applicable for backgrounds.
 * @author Pavel Vlasov
 *
 */
public enum Color {
	
	PRIMARY("primary"),
	SECONDARY("secondary"),
	SUCCESS("success"),
	DANGER("danger"),
	WARNING("warning"),
	INFO("info"),
	LIGHT("light"),
	DARK("dark"),
	BODY("body"),
	MUTED("muted"),
	WHITE("white"),
	BLACK50("black-50"),
	WHITE50("white-50"),
	TRANSPARENT("transparent");	
	
	Color(String code) {
		this.code = code;
	}
	
	public final String code;

}