/**
 */
package org.nasdanika.html.model.app;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Color</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.nasdanika.html.model.app.AppPackage#getColor()
 * @model
 * @generated
 */
public enum Color implements Enumerator {
	/**
	 * The '<em><b>Primary</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PRIMARY_VALUE
	 * @generated
	 * @ordered
	 */
	PRIMARY(0, "Primary", "Primary"),

	/**
	 * The '<em><b>Secondary</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SECONDARY_VALUE
	 * @generated
	 * @ordered
	 */
	SECONDARY(1, "Secondary", "Secondary"),

	/**
	 * The '<em><b>Success</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUCCESS_VALUE
	 * @generated
	 * @ordered
	 */
	SUCCESS(2, "Success", "Success"),

	/**
	 * The '<em><b>Info</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INFO_VALUE
	 * @generated
	 * @ordered
	 */
	INFO(3, "Info", "Info"),

	/**
	 * The '<em><b>Warning</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WARNING_VALUE
	 * @generated
	 * @ordered
	 */
	WARNING(4, "Warning", "Warning"),

	/**
	 * The '<em><b>Danger</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DANGER_VALUE
	 * @generated
	 * @ordered
	 */
	DANGER(5, "Danger", "Danger"),

	/**
	 * The '<em><b>Light</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LIGHT_VALUE
	 * @generated
	 * @ordered
	 */
	LIGHT(6, "Light", "Light"),

	/**
	 * The '<em><b>Dark</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DARK_VALUE
	 * @generated
	 * @ordered
	 */
	DARK(7, "Dark", "Dark"),

	/**
	 * The '<em><b>Body</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BODY_VALUE
	 * @generated
	 * @ordered
	 */
	BODY(8, "Body", "Body"),

	/**
	 * The '<em><b>Muted</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MUTED_VALUE
	 * @generated
	 * @ordered
	 */
	MUTED(9, "Muted", "Muted"),

	/**
	 * The '<em><b>White</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WHITE_VALUE
	 * @generated
	 * @ordered
	 */
	WHITE(10, "White", "White"),

	/**
	 * The '<em><b>Black50</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BLACK50_VALUE
	 * @generated
	 * @ordered
	 */
	BLACK50(11, "Black50", "Black50"),

	/**
	 * The '<em><b>White50</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WHITE50_VALUE
	 * @generated
	 * @ordered
	 */
	WHITE50(12, "White50", "White50");

	/**
	 * The '<em><b>Primary</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PRIMARY
	 * @model name="Primary"
	 * @generated
	 * @ordered
	 */
	public static final int PRIMARY_VALUE = 0;

	/**
	 * The '<em><b>Secondary</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SECONDARY
	 * @model name="Secondary"
	 * @generated
	 * @ordered
	 */
	public static final int SECONDARY_VALUE = 1;

	/**
	 * The '<em><b>Success</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUCCESS
	 * @model name="Success"
	 * @generated
	 * @ordered
	 */
	public static final int SUCCESS_VALUE = 2;

	/**
	 * The '<em><b>Info</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #INFO
	 * @model name="Info"
	 * @generated
	 * @ordered
	 */
	public static final int INFO_VALUE = 3;

	/**
	 * The '<em><b>Warning</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WARNING
	 * @model name="Warning"
	 * @generated
	 * @ordered
	 */
	public static final int WARNING_VALUE = 4;

	/**
	 * The '<em><b>Danger</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DANGER
	 * @model name="Danger"
	 * @generated
	 * @ordered
	 */
	public static final int DANGER_VALUE = 5;

	/**
	 * The '<em><b>Light</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LIGHT
	 * @model name="Light"
	 * @generated
	 * @ordered
	 */
	public static final int LIGHT_VALUE = 6;

	/**
	 * The '<em><b>Dark</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #DARK
	 * @model name="Dark"
	 * @generated
	 * @ordered
	 */
	public static final int DARK_VALUE = 7;

	/**
	 * The '<em><b>Body</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BODY
	 * @model name="Body"
	 * @generated
	 * @ordered
	 */
	public static final int BODY_VALUE = 8;

	/**
	 * The '<em><b>Muted</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MUTED
	 * @model name="Muted"
	 * @generated
	 * @ordered
	 */
	public static final int MUTED_VALUE = 9;

	/**
	 * The '<em><b>White</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WHITE
	 * @model name="White"
	 * @generated
	 * @ordered
	 */
	public static final int WHITE_VALUE = 10;

	/**
	 * The '<em><b>Black50</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #BLACK50
	 * @model name="Black50"
	 * @generated
	 * @ordered
	 */
	public static final int BLACK50_VALUE = 11;

	/**
	 * The '<em><b>White50</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #WHITE50
	 * @model name="White50"
	 * @generated
	 * @ordered
	 */
	public static final int WHITE50_VALUE = 12;

	/**
	 * An array of all the '<em><b>Color</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Color[] VALUES_ARRAY =
		new Color[] {
			PRIMARY,
			SECONDARY,
			SUCCESS,
			INFO,
			WARNING,
			DANGER,
			LIGHT,
			DARK,
			BODY,
			MUTED,
			WHITE,
			BLACK50,
			WHITE50,
		};

	/**
	 * A public read-only list of all the '<em><b>Color</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Color> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Color</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Color get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Color result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Color</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Color getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Color result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Color</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Color get(int value) {
		switch (value) {
			case PRIMARY_VALUE: return PRIMARY;
			case SECONDARY_VALUE: return SECONDARY;
			case SUCCESS_VALUE: return SUCCESS;
			case INFO_VALUE: return INFO;
			case WARNING_VALUE: return WARNING;
			case DANGER_VALUE: return DANGER;
			case LIGHT_VALUE: return LIGHT;
			case DARK_VALUE: return DARK;
			case BODY_VALUE: return BODY;
			case MUTED_VALUE: return MUTED;
			case WHITE_VALUE: return WHITE;
			case BLACK50_VALUE: return BLACK50;
			case WHITE50_VALUE: return WHITE50;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Color(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //Color
