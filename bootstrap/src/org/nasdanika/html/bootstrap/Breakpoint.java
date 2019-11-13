package org.nasdanika.html.bootstrap;

public enum Breakpoint {
	
	DEFAULT(""),
	SMALL("sm"),
	MEDIUM("md"),
	LARGE("lg"),
	EXTRA_LARGE("xl");
	
	Breakpoint(String code) {
		this.code = code;
	}
	
	public final String code;
	
	/**
	 * @return width string
	 */
	public String size(String prefix, Size size) {
		StringBuilder ret = new StringBuilder(prefix);
		if (code.length() > 0) {
			ret.append("-").append(code);
		}
		if (size.code.length() > 0) {
			ret.append("-").append(size.code);
		}
		return ret.toString();
	};

}
