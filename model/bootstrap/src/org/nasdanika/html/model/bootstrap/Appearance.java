/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.nasdanika.ncore.AbstractEntry;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Appearance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This class is used for configuring common aspects of HTML and Bootstrap elements such as background, spacing, text, etc.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Appearance#getBackground <em>Background</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Appearance#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Appearance#getBorder <em>Border</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Appearance#getMargin <em>Margin</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Appearance#getPadding <em>Padding</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Appearance#getText <em>Text</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Appearance#getFloat <em>Float</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getAppearance()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='border_overlap background attributes'"
 * @generated
 */
public interface Appearance extends EObject {
	/**
	 * Returns the value of the '<em><b>Background</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Bootstrap color for background.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Background</em>' attribute.
	 * @see #setBackground(String)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getAppearance_Background()
	 * @model
	 * @generated
	 */
	String getBackground();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Appearance#getBackground <em>Background</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Background</em>' attribute.
	 * @see #getBackground()
	 * @generated
	 */
	void setBackground(String value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.AbstractEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * HTML element (tag) attributes.
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
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getAppearance_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<AbstractEntry> getAttributes();

	/**
	 * Returns the value of the '<em><b>Border</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.bootstrap.Border}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Border configuration.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Border</em>' containment reference list.
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getAppearance_Border()
	 * @model containment="true" upper="4"
	 * @generated
	 */
	EList<Border> getBorder();

	/**
	 * Returns the value of the '<em><b>Margin</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.bootstrap.Spacing}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Margin configuration.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Margin</em>' containment reference list.
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getAppearance_Margin()
	 * @model containment="true"
	 * @generated
	 */
	EList<Spacing> getMargin();

	/**
	 * Returns the value of the '<em><b>Padding</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.bootstrap.Spacing}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Padding configuration.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Padding</em>' containment reference list.
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getAppearance_Padding()
	 * @model containment="true"
	 * @generated
	 */
	EList<Spacing> getPadding();

	/**
	 * Returns the value of the '<em><b>Text</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Text style
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Text</em>' containment reference.
	 * @see #setText(Text)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getAppearance_Text()
	 * @model containment="true"
	 * @generated
	 */
	Text getText();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Appearance#getText <em>Text</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' containment reference.
	 * @see #getText()
	 * @generated
	 */
	void setText(Text value);

	/**
	 * Returns the value of the '<em><b>Float</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.bootstrap.Float}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Float configuration.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Float</em>' containment reference list.
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getAppearance_Float()
	 * @model containment="true"
	 * @generated
	 */
	EList<org.nasdanika.html.model.bootstrap.Float> getFloat();

} // Appearance
