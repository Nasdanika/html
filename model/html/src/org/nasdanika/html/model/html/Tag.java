/**
 */
package org.nasdanika.html.model.html;

import org.eclipse.emf.common.util.EList;

import org.nasdanika.ncore.AbstractEntry;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tag</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * HTML Tag
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.Tag#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Tag#getAttributes <em>Attributes</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getTag()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='attributes'"
 * @generated
 */
public interface Tag extends HtmlElement {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Tag name.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getTag_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.Tag#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.AbstractEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Tag attributes.
	 * 
	 * ## Interpolation
	 * 
	 * Attribute values are interpolated, i.e. tokens in the form of ``${token name[|default value]}`` are replaced with the contextual values or default values, if any. Examples:
	 * 
	 * * ``${my-style}`` - Token without a default value.
	 * * ``${font-weight|bold}`` - Token with a default value.
	 * 
	 * ## Regular attributes
	 * 
	 * For all top-level entries except ``class``, ``style``, and ``data`` attribute value is produced by converting the value to string for scalars and to JSON string for lists and maps. 
	 * For attributes which do not start with ``data-`` a warning is issued if the value is not a scalar, i.e. a list or a map.
	 * 
	 * ## Class
	 * 
	 * For class attribute its value is formed by concantenating elements using space as a separator. If elements are hierarchical then class name is formed by concatenation with a dash (``-``) as a separator.
	 * 
	 * ## Data
	 * 
	 * If value of ``data`` attbibute is a map then keys of that map get concatenated with ``data`` using dash (``-``) as a separator, them same applies to nested maps. Non-map values become attribute values - scalars are converted to string, lists are converted to JSON string.
	 * 
	 * ## Style
	 * 
	 * Style can be defined as a string, list or map. If style is defined as a list, all list values are concatenated with a space as a separator - it is a convent way for long unstructured definitions.
	 * 
	 * If style value is a map then the value and its contained map values are processed in the following fashion:
	 * 
	 * * Keys are concatenated with dash as a separator.
	 * * List values are contcatenated wtih space as a separator.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getTag_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractEntry> getAttributes();

} // Tag
