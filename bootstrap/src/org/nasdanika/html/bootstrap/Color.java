package org.nasdanika.html.bootstrap;

/**
 * Bootstrap colors. Not all colors are applicable in all situations. E.g. TRANSPARENT is applicable for backgrounds.
 * @author Pavel Vlasov
 *
 */
public enum Color {
	
	/**
	 * No color - for borders means that there should be no border. 
	 * For other uses is the same as DEFAULT
	 */
	NONE(null, ""),
	/**
	 * Default color - no specific.
	 */
	DEFAULT(null, ""),
	PRIMARY("primary", "Primary"),
	SECONDARY("secondary", "Secondary"),
	SUCCESS("success", "Success"),
	DANGER("danger", "Danger"),
	WARNING("warning", "Warning"),
	INFO("info", "Info"),
	LIGHT("light", "Light"),
	DARK("dark", "Dark"),
	BODY("body", "Body"),
	MUTED("muted", "Muted"),
	WHITE("white", "White"),
	BLACK50("black-50", "Black 50%"),
	WHITE50("white-50", "White 50%"),
	TRANSPARENT("transparent", "Transparent");
	
	Color(String code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public final String code;
	public final String label;

}
