/**
 */
package org.nasdanika.html.model.app;


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
 *   <li>{@link org.nasdanika.html.model.app.Link#getBinding <em>Binding</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.Link#getConfirmation <em>Confirmation</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getLink()
 * @model
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
	 * @model annotation="urn:org.nasdanika exclusive-with='script binding'"
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
	 * @model annotation="urn:org.nasdanika exclusive-with='location binding'"
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
	 * Returns the value of the '<em><b>Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * JavaScript which performs binding of the link action.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Binding</em>' attribute.
	 * @see #setBinding(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getLink_Binding()
	 * @model annotation="urn:org.nasdanika exclusive-with='script location'"
	 * @generated
	 */
	String getBinding();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Link#getBinding <em>Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' attribute.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(String value);

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

} // Link
