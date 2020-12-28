package org.nasdanika.html.bootstrap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.nasdanika.common.Util;

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
	BODY("body", "Body", Applicability.TEXT),
	MUTED("muted", "Muted", Applicability.TEXT),
	WHITE("white", "White"),
	BLACK50("black-50", "Black 50%", Applicability.TEXT),
	WHITE50("white-50", "White 50%", Applicability.TEXT),
	TRANSPARENT("transparent", "Transparent", Applicability.BACKGROUND);
	
	/**
	 * Defines where the color can be applied.
	 * @author Pavel
	 *
	 */
	public static enum Applicability {
		BACKGROUND,
		BORDER,
		TEXT;
		
		/**
		 * @return Applicable colors;
		 */
		public List<Color> colors() {
			return Arrays.stream(Color.values()).filter(c -> c.isApplicable(this)).collect(Collectors.toList());
		}
		
		/**
		 * @return Labels of applicable colors with suppressed duplicates - only one empty string for both NONE and DEFAULT.
		 */
		public List<Object> colorLabels() {
			List<Object> ret = new ArrayList<>();
			for (Color c: colors()) {
				if (!ret.contains(c.label)) {
					ret.add(c.label);
				}
			}		
			return ret;
		}
		
	}
	
	Color(String code, String label, Applicability... applicability) {
		this.code = code;
		this.label = label;
		this.applicabilities = applicability;
	}
	
	public final String code;
	public final String label;
	private final Applicability[] applicabilities; 
		
	public static Color fromLabel(String label) {
		for (Color color: values()) {
			if (color.label.equals(label)) {
				return color;
			}
		}
		throw new IllegalArgumentException("No color value for label "+label);
	}
	
	public static Color fromCode(String code) {
		if (Util.isBlank(code)) {
			return NONE;
		}
		for (Color color: values()) {
			if (color.code != null && color.code.equals(code)) {
				return color;
			}
		}
		throw new IllegalArgumentException("No color value for code "+code);
	}
	
	public boolean isApplicable(Applicability applicability) {
		if (applicabilities.length == 0) {
			return true;
		}
		for (Applicability a: applicabilities) {
			if (a == applicability) {
				return true;
			}
		}
		return false;
	}

}
