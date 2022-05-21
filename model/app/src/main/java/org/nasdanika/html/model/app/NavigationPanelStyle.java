/**
 */
package org.nasdanika.html.model.app;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Navigation Panel Style</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.nasdanika.html.model.app.AppPackage#getNavigationPanelStyle()
 * @model
 * @generated
 */
public enum NavigationPanelStyle implements Enumerator {
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
	 * The '<em><b>Cards</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CARDS_VALUE
	 * @generated
	 * @ordered
	 */
	CARDS(1, "Cards", "Cards"),

	/**
	 * The '<em><b>Collapsible Cards</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COLLAPSIBLE_CARDS_VALUE
	 * @generated
	 * @ordered
	 */
	COLLAPSIBLE_CARDS(2, "CollapsibleCards", "CollapsibleCards"), /**
	 * The '<em><b>Tree</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TREE_VALUE
	 * @generated
	 * @ordered
	 */
	TREE(3, "Tree", "Tree"), /**
	 * The '<em><b>Searchable Tree</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEARCHABLE_TREE_VALUE
	 * @generated
	 * @ordered
	 */
	SEARCHABLE_TREE(4, "SearchableTree", "SearchableTree");

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
	 * The '<em><b>Cards</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CARDS
	 * @model name="Cards"
	 * @generated
	 * @ordered
	 */
	public static final int CARDS_VALUE = 1;

	/**
	 * The '<em><b>Collapsible Cards</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COLLAPSIBLE_CARDS
	 * @model name="CollapsibleCards"
	 * @generated
	 * @ordered
	 */
	public static final int COLLAPSIBLE_CARDS_VALUE = 2;

	/**
	 * The '<em><b>Tree</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TREE
	 * @model name="Tree"
	 * @generated
	 * @ordered
	 */
	public static final int TREE_VALUE = 3;

	/**
	 * The '<em><b>Searchable Tree</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEARCHABLE_TREE
	 * @model name="SearchableTree"
	 * @generated
	 * @ordered
	 */
	public static final int SEARCHABLE_TREE_VALUE = 4;

	/**
	 * An array of all the '<em><b>Navigation Panel Style</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final NavigationPanelStyle[] VALUES_ARRAY =
		new NavigationPanelStyle[] {
			AUTO,
			CARDS,
			COLLAPSIBLE_CARDS,
			TREE,
			SEARCHABLE_TREE,
		};

	/**
	 * A public read-only list of all the '<em><b>Navigation Panel Style</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<NavigationPanelStyle> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Navigation Panel Style</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static NavigationPanelStyle get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NavigationPanelStyle result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Navigation Panel Style</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static NavigationPanelStyle getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NavigationPanelStyle result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Navigation Panel Style</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static NavigationPanelStyle get(int value) {
		switch (value) {
			case AUTO_VALUE: return AUTO;
			case CARDS_VALUE: return CARDS;
			case COLLAPSIBLE_CARDS_VALUE: return COLLAPSIBLE_CARDS;
			case TREE_VALUE: return TREE;
			case SEARCHABLE_TREE_VALUE: return SEARCHABLE_TREE;
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
	private NavigationPanelStyle(int value, String name, String literal) {
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
	
} //NavigationPanelStyle
