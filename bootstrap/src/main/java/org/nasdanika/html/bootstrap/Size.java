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
		if (code == null) {
			return NONE;
		}
		for (Size size: values()) {
			if (size.code.equals(code)) {
				return size;
			}
		}
		throw new IllegalArgumentException("No size value for code "+code);
	}
	
	/**
	 * For numeric sizes returns size complementing to 12
	 * @param size
	 * @return
	 */
	public Size complementary() {
		switch (this) {
		case S0:
			return S12;
		case S1:
			return S11;
		case S2:
			return S10;
		case S3:
			return S9;
		case S4:
			return S8;
		case S5:
			return S7;
		case S6:
			return S6;
		case S7:
			return S5;
		case S8:
			return S4;
		case S9:
			return S3;
		case S10:
			return S2;
		case S11:
			return S1;
		case S12:
			return S0;
		case AUTO:
		case NONE:
		default:
			return AUTO;
		}
	}

}
