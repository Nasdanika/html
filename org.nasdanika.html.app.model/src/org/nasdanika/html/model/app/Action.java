/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This action implementation delegates to an ``org.nasdanika.html.app.Executable`` adapter or throws an exception is there is no such adapter. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.Action#getChildren <em>Children</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getConfirmation <em>Confirmation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getContextActions <em>Context Actions</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#isFloatRight <em>Float Right</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#getRoles <em>Roles</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Action#isDisabled <em>Disabled</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getAction()
 * @model superTypes="org.nasdanika.html.model.app.IAction org.nasdanika.html.model.app.Label"
 * @generated
 */
public interface Action extends org.nasdanika.html.app.Action, Label {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.app.Action}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<Action> getChildren();

	/**
	 * Returns the value of the '<em><b>Confirmation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Confirmation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Confirmation</em>' attribute.
	 * @see #setConfirmation(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Confirmation()
	 * @model
	 * @generated
	 */
	String getConfirmation();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#getConfirmation <em>Confirmation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Confirmation</em>' attribute.
	 * @see #getConfirmation()
	 * @generated
	 */
	void setConfirmation(String value);

	/**
	 * Returns the value of the '<em><b>Context Actions</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.app.Action}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context Actions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context Actions</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_ContextActions()
	 * @model containment="true"
	 * @generated
	 */
	EList<Action> getContextActions();

	/**
	 * Returns the value of the '<em><b>Float Right</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Float Right</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Float Right</em>' attribute.
	 * @see #setFloatRight(boolean)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_FloatRight()
	 * @model
	 * @generated
	 */
	boolean isFloatRight();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#isFloatRight <em>Float Right</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Float Right</em>' attribute.
	 * @see #isFloatRight()
	 * @generated
	 */
	void setFloatRight(boolean value);

	/**
	 * Returns the value of the '<em><b>Roles</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roles</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roles</em>' attribute list.
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Roles()
	 * @model
	 * @generated
	 */
	EList<String> getRoles();

	/**
	 * Returns the value of the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Disabled</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Disabled</em>' attribute.
	 * @see #setDisabled(boolean)
	 * @see org.nasdanika.html.model.app.AppPackage#getAction_Disabled()
	 * @model
	 * @generated
	 */
	boolean isDisabled();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Action#isDisabled <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Disabled</em>' attribute.
	 * @see #isDisabled()
	 * @generated
	 */
	void setDisabled(boolean value);

} // Action
