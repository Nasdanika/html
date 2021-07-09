package org.nasdanika.html;

public enum OrderedListType {
	NUMBER("1", "Number"),
	UPPERCASE_LETTERS("A", "Uppercase letters"),
	LOWERCASE_LETTERS("a", "Lowercase letters"),
	UPPERCASE_ROMAN_NUMBERS("I", "Uppercase roman numbers"),
	LOWERCASE_ROMAN_NUMBERS("i", "Lowercase roman numbers"),
	/**
	 * Pseudo-type indicating rotation of types for nested lists. An example of rotated numbering: "1.B.c.IV.v". 
	 */
	ROTATE(null, "Rotate");
	
	OrderedListType(String code, String label) {
		this.code = code;
		this.label = label;
	}
	
	public final String code;
	public final String label;
		
	public static OrderedListType fromLabel(String label) {
		for (OrderedListType breakpoint: values()) {
			if (breakpoint.label.equals(label)) {
				return breakpoint;
			}
		}
		throw new IllegalArgumentException("No type value for label "+label);
	}


}
