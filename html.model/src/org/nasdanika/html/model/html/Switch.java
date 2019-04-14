/**
 */
package org.nasdanika.html.model.html;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Switch</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Switch allows to select content of one of its cases of the default (otherwise) content based on a selector value matching on of the case values or not matching any value in which case the otherwise content will be selected, if it is present. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.Switch#getCases <em>Cases</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Switch#getOtherwise <em>Otherwise</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Switch#getSelector <em>Selector</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getSwitch()
 * @model
 * @generated
 */
public interface Switch extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Cases</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.html.Case}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Switch cases for matching the switch value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cases</em>' containment reference list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getSwitch_Cases()
	 * @model containment="true"
	 * @generated
	 */
	EList<Case> getCases();

	/**
	 * Returns the value of the '<em><b>Otherwise</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.html.Content}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Content to be used if no cases matched.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Otherwise</em>' containment reference list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getSwitch_Otherwise()
	 * @model containment="true"
	 * @generated
	 */
	EList<Content> getOtherwise();

	/**
	 * Returns the value of the '<em><b>Selector</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Data binding key to retrieve a value to be used to select a case.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Selector</em>' attribute.
	 * @see #setSelector(String)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getSwitch_Selector()
	 * @model
	 * @generated
	 */
	String getSelector();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.Switch#getSelector <em>Selector</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Selector</em>' attribute.
	 * @see #getSelector()
	 * @generated
	 */
	void setSelector(String value);

} // Switch
