/**
 */
package org.nasdanika.html.model.html;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.exec.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for other HTML and bootstrap elements. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.HtmlElement#getAttributes <em>Attributes</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getHtmlElement()
 * @model abstract="true"
 * @generated
 */
public interface HtmlElement extends ModelElement {

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.eclipse.emf.ecore.EObject},
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
	 * @return the value of the '<em>Attributes</em>' map.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getHtmlElement_Attributes()
	 * @model mapType="org.nasdanika.exec.Property&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EObject&gt;"
	 * @generated
	 */
	EMap<String, EObject> getAttributes();
} // HtmlElement
