/**
 */
package org.nasdanika.html.model.html;

import org.nasdanika.exec.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stylesheet Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Reference to an external stylesheet.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.StylesheetReference#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getStylesheetReference()
 * @model
 * @generated
 */
public interface StylesheetReference extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Stylesheet URL.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' attribute.
	 * @see #setTarget(String)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getStylesheetReference_Target()
	 * @model required="true"
	 *        annotation="urn:org.nasdanika default-feature='true'"
	 * @generated
	 */
	String getTarget();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.StylesheetReference#getTarget <em>Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' attribute.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(String value);

} // StylesheetReference
