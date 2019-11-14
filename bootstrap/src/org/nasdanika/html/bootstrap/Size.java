package org.nasdanika.html.bootstrap;

/**
 * Size enumeration for widths and spacings. Spacings valid values are S1..S5.
 * @author Pavel
 *
 */
public enum Size {
	
	NONE(""),
	S0("0"),
	S1("1"),
	S2("2"),
	S3("3"),
	S4("4"),
	S5("5"),
	S6("6"),
	S7("7"),
	S8("8"),
	S9("9"),
	S10("10"),
	S11("11"),
	S12("12"),
	AUTO("auto");
	
	Size(String code) {
		this.code = code;
	}		
	
	public final String code;
	
	public static Size fromCode(String code) {
		for (Size size: values()) {
			if (size.code.equals(code)) {
				return size;
			}
		}
		throw new IllegalArgumentException("No size value for code "+code);
	}

}
