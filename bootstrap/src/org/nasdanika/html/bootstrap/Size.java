package org.nasdanika.html.bootstrap;

/**
 * Size enumeration for widths and spacings. Spacings valid values are S1..S5.
 * @author Pavel
 *
 */
public enum Size {
	
	NONE("", true),
	S0("0", true),
	S1("1", true),
	S2("2", true),
	S3("3", true),
	S4("4", true),
	S5("5", true),
	S6("6", false),
	S7("7", false),
	S8("8", false),
	S9("9", false),
	S10("10", false),
	S11("11", false),
	S12("12", false),
	AUTO("auto", true);
	
	Size(String code, boolean spacing) {
		this.code = code;
		this.spacing = spacing;
	}		
	
	/**
	 * Size code.
	 */
	public final String code;
	
	/**
	 * If true, this size is applicable for {@link Spacing}.
	 */
	private final boolean spacing;
	
	/**
	 * @return true if this size is applicable for {@link Spacing} - 0..5, auto.
	 */
	public boolean isSpacing() {
		return spacing;
	}
	
	public static Size fromCode(String code) {
		for (Size size: values()) {
			if (size.code.equals(code)) {
				return size;
			}
		}
		throw new IllegalArgumentException("No size value for code "+code);
	}

}
