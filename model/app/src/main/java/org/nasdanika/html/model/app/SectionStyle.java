/**
 */
package org.nasdanika.html.model.app;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Section Style</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.nasdanika.html.model.app.AppPackage#getSectionStyle()
 * @model
 * @generated
 */
public enum SectionStyle implements Enumerator {
	/**
	 * The '<em><b>Auto</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AUTO_VALUE
	 * @generated
	 * @ordered
	 */
	AUTO(0, "Auto", "Auto"),

	/**
	 * The '<em><b>Action Group</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ACTION_GROUP_VALUE
	 * @generated
	 * @ordered
	 */
	ACTION_GROUP(1, "ActionGroup", "ActionGroup"),

	/**
	 * The '<em><b>Card</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CARD_VALUE
	 * @generated
	 * @ordered
	 */
	CARD(2, "Card", "Card"),

	/**
	 * The '<em><b>Card Pill</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CARD_PILL_VALUE
	 * @generated
	 * @ordered
	 */
	CARD_PILL(3, "CardPill", "CardPill"),

	/**
	 * The '<em><b>Card Tab</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CARD_TAB_VALUE
	 * @generated
	 * @ordered
	 */
	CARD_TAB(4, "CardTab", "CardTab"),

	/**
	 * The '<em><b>Header</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HEADER_VALUE
	 * @generated
	 * @ordered
	 */
	HEADER(5, "Header", "Header"),

	/**
	 * The '<em><b>Pill</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PILL_VALUE
	 * @generated
	 * @ordered
	 */
	PILL(6, "Pill", "Pill"),

	/**
	 * The '<em><b>Tab</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TAB_VALUE
	 * @generated
	 * @ordered
	 */
	TAB(7, "Tab", "Tab"),

	/**
	 * The '<em><b>Table</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TABLE_VALUE
	 * @generated
	 * @ordered
	 */
	TABLE(8, "Table", "Table");

	/**
	 * The '<em><b>Auto</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AUTO
	 * @model name="Auto"
	 * @generated
	 * @ordered
	 */
	public static final int AUTO_VALUE = 0;

	/**
	 * The '<em><b>Action Group</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ACTION_GROUP
	 * @model name="ActionGroup"
	 * @generated
	 * @ordered
	 */
	public static final int ACTION_GROUP_VALUE = 1;

	/**
	 * The '<em><b>Card</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CARD
	 * @model name="Card"
	 * @generated
	 * @ordered
	 */
	public static final int CARD_VALUE = 2;

	/**
	 * The '<em><b>Card Pill</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CARD_PILL
	 * @model name="CardPill"
	 * @generated
	 * @ordered
	 */
	public static final int CARD_PILL_VALUE = 3;

	/**
	 * The '<em><b>Card Tab</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CARD_TAB
	 * @model name="CardTab"
	 * @generated
	 * @ordered
	 */
	public static final int CARD_TAB_VALUE = 4;

	/**
	 * The '<em><b>Header</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HEADER
	 * @model name="Header"
	 * @generated
	 * @ordered
	 */
	public static final int HEADER_VALUE = 5;

	/**
	 * The '<em><b>Pill</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PILL
	 * @model name="Pill"
	 * @generated
	 * @ordered
	 */
	public static final int PILL_VALUE = 6;

	/**
	 * The '<em><b>Tab</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TAB
	 * @model name="Tab"
	 * @generated
	 * @ordered
	 */
	public static final int TAB_VALUE = 7;

	/**
	 * The '<em><b>Table</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TABLE
	 * @model name="Table"
	 * @generated
	 * @ordered
	 */
	public static final int TABLE_VALUE = 8;

	/**
	 * An array of all the '<em><b>Section Style</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SectionStyle[] VALUES_ARRAY =
		new SectionStyle[] {
			AUTO,
			ACTION_GROUP,
			CARD,
			CARD_PILL,
			CARD_TAB,
			HEADER,
			PILL,
			TAB,
			TABLE,
		};

	/**
	 * A public read-only list of all the '<em><b>Section Style</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<SectionStyle> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Section Style</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SectionStyle get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SectionStyle result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Section Style</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SectionStyle getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SectionStyle result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Section Style</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SectionStyle get(int value) {
		switch (value) {
			case AUTO_VALUE: return AUTO;
			case ACTION_GROUP_VALUE: return ACTION_GROUP;
			case CARD_VALUE: return CARD;
			case CARD_PILL_VALUE: return CARD_PILL;
			case CARD_TAB_VALUE: return CARD_TAB;
			case HEADER_VALUE: return HEADER;
			case PILL_VALUE: return PILL;
			case TAB_VALUE: return TAB;
			case TABLE_VALUE: return TABLE;
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
	private SectionStyle(int value, String name, String literal) {
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
	
} //SectionStyle
