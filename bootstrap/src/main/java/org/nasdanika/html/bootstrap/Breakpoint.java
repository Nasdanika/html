package org.nasdanika.html.bootstrap;

import org.nasdanika.common.Util;

public enum Breakpoint {
	
	DEFAULT("", ""),
	SMALL("sm", "Small"),
	MEDIUM("md", "Medium"),
	LARGE("lg", "Large"),
	EXTRA_LARGE("xl", "Extra large");
	
	Breakpoint(String code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public final String code;
	public final String label;
	
	/**
	 * @return width string
	 */
	public String size(String prefix, Size size) {
		StringBuilder ret = new StringBuilder(prefix);
		if (code.length() > 0) {
			if (ret.length() > 0) {
				ret.append("-");
			}
			ret.append(code);
		}
		if (size.code.length() > 0) {
			if (ret.length() > 0) {
				ret.append("-");
			}
			ret.append(size.code);
		}
		return ret.toString();
	};
		
	public static Breakpoint fromCode(String code) {
		if (Util.isBlank(code)) {
			return DEFAULT;
		}
		for (Breakpoint breakpoint: values()) {
			if (breakpoint.code.equals(code)) {
				return breakpoint;
			}
		}
		throw new IllegalArgumentException("No breakpoint value for code "+code);
	}
		
	public static Breakpoint fromLabel(String label) {
		if (label == null || label.trim().length() == 0) {
			return DEFAULT;
		}
		
		for (Breakpoint breakpoint: values()) {
			if (breakpoint.label.equals(label)) {
				return breakpoint;
			}
		}
		throw new IllegalArgumentException("No breakpoint value for label "+label);
	}

}
