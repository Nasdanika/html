/**
 */
package org.nasdanika.html.model.html;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Case</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Switch case.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.Case#getValue <em>Value</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Case#getContent <em>Content</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getCase()
 * @model
 * @extends CDOObject
 * @generated
 */
public interface Case extends CDOObject {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Case value. A case is matched in the switch selector and the value are equal.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getCase_Value()
	 * @model
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.Case#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Content</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.html.Content}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Case content.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Content</em>' containment reference list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getCase_Content()
	 * @model containment="true"
	 * @generated
	 */
	EList<Content> getContent();

} // Case
