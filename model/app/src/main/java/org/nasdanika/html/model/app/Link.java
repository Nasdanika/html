/**
 */
package org.nasdanika.html.model.app;

import org.nasdanika.html.model.bootstrap.Modal;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.Link#getLocation <em>Location</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Link#getScript <em>Script</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Link#getModal <em>Modal</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Link#getConfirmation <em>Confirmation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Link#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Link#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getLink()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='activator'"
 * @generated
 */
public interface Link extends Label {
	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Link URL relative to the ancestor URL or ``base-uri``.
	 * 
	 * ``${{{base-uri}}}`` token can be used in the activator to define the uri relative to the base generation URI (output folder) instead of the parent URI. 
	 * It might be useful it the parent URI is an absolute external URI.
	 * ``${{{base-uri}}}`` ends with a slash, so there is no need to add a slash. E.g. ``${{{base-uri}}}index.html``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getLink_Location()
	 * @model annotation="urn:org.nasdanika exclusive-with='script modal'"
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Link#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

	/**
	 * Returns the value of the '<em><b>Script</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Script to execute on link click (activation).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Script</em>' attribute.
	 * @see #setScript(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getLink_Script()
	 * @model annotation="urn:org.nasdanika exclusive-with='location modal'"
	 * @generated
	 */
	String getScript();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Link#getScript <em>Script</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script</em>' attribute.
	 * @see #getScript()
	 * @generated
	 */
	void setScript(String value);

	/**
	 * Returns the value of the '<em><b>Modal</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Modal which opens on link activation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Modal</em>' containment reference.
	 * @see #setModal(Modal)
	 * @see org.nasdanika.html.model.app.AppPackage#getLink_Modal()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true' exclusive-with='location script'"
	 * @generated
	 */
	Modal getModal();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Link#getModal <em>Modal</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modal</em>' containment reference.
	 * @see #getModal()
	 * @generated
	 */
	void setModal(Modal value);

	/**
	 * Returns the value of the '<em><b>Confirmation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Confirmation to display in a confirmation dialog before action activation to give the user an opportunity to cancel the action. E.g. confirmation of deletion.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Confirmation</em>' attribute.
	 * @see #setConfirmation(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getLink_Confirmation()
	 * @model
	 * @generated
	 */
	String getConfirmation();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Link#getConfirmation <em>Confirmation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Confirmation</em>' attribute.
	 * @see #getConfirmation()
	 * @generated
	 */
	void setConfirmation(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Link name attribute if not blank for referencing sections.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getLink_Name()
	 * @model annotation="urn:org.nasdanika exclusive-with='script modal location'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Link#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Link name attribute if not blank for referencing sections.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' attribute.
	 * @see #setTarget(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getLink_Target()
	 * @model annotation="urn:org.nasdanika exclusive-with='script modal location'"
	 * @generated
	 */
	String getTarget();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Link#getTarget <em>Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' attribute.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(String value);

} // Link
