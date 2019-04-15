/**
 */
package org.nasdanika.html.model.bootstrap;

import org.nasdanika.html.model.html.HTMLElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Wrap</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Wraps an HTML element into a Bootstrap element for applying Bootstrap styles.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Wrap#getHtmlElement <em>Html Element</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getWrap()
 * @model
 * @generated
 */
public interface Wrap extends ContentBootstrapElement {
	/**
	 * Returns the value of the '<em><b>Html Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Html Element</em>' containment reference.
	 * @see #setHtmlElement(HTMLElement)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getWrap_HtmlElement()
	 * @model containment="true"
	 * @generated
	 */
	HTMLElement getHtmlElement();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Wrap#getHtmlElement <em>Html Element</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Html Element</em>' containment reference.
	 * @see #getHtmlElement()
	 * @generated
	 */
	void setHtmlElement(HTMLElement value);

} // Wrap
