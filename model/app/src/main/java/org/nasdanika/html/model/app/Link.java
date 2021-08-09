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
	 * Activator is either a URL if the activator type is Reference, or a script if the activator type is Script or Bind.
	 * 
	 * The application generator generates a page for an action only if the activator type is Reference, the URL does not start with ``./`` and when resolved is relative to the base URI and below it. 
	 * Using ``./`` is a way to reference externally generated content. For example, create a hierarchy/federation of sites.
	 * 
	 * For the reference activator type activator URL is resolved against the first ancestor action which also has reference activator type. If there is no such action, the URL is resolved against the base URI.
	 * During generation action URL is de-resolved against the base URI to produce a relative resource/file path. 
	 * 
	 * ``${{{base-uri}}}`` token can be used in the activator to define the uri relative to the base generation URI (output folder) instead of the parent URI. It might be useful it the parent URI is an absolute external URI.
	 * ``${{{base-uri}}}`` ends with a slash, so there is no need to add a slash. E.g. ``${{{base-uri}}}index.html``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getLink_Location()
	 * @model
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
	 * Activator is either a URL if the activator type is Reference, or a script if the activator type is Script or Bind.
	 * 
	 * The application generator generates a page for an action only if the activator type is Reference, the URL does not start with ``./`` and when resolved is relative to the base URI and below it. 
	 * Using ``./`` is a way to reference externally generated content. For example, create a hierarchy/federation of sites.
	 * 
	 * For the reference activator type activator URL is resolved against the first ancestor action which also has reference activator type. If there is no such action, the URL is resolved against the base URI.
	 * During generation action URL is de-resolved against the base URI to produce a relative resource/file path. 
	 * 
	 * ``${{{base-uri}}}`` token can be used in the activator to define the uri relative to the base generation URI (output folder) instead of the parent URI. It might be useful it the parent URI is an absolute external URI.
	 * ``${{{base-uri}}}`` ends with a slash, so there is no need to add a slash. E.g. ``${{{base-uri}}}index.html``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Script</em>' attribute.
	 * @see #setScript(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getLink_Script()
	 * @model
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
	 * Activator is either a URL if the activator type is Reference, or a script if the activator type is Script or Bind.
	 * 
	 * The application generator generates a page for an action only if the activator type is Reference, the URL does not start with ``./`` and when resolved is relative to the base URI and below it. 
	 * Using ``./`` is a way to reference externally generated content. For example, create a hierarchy/federation of sites.
	 * 
	 * For the reference activator type activator URL is resolved against the first ancestor action which also has reference activator type. If there is no such action, the URL is resolved against the base URI.
	 * During generation action URL is de-resolved against the base URI to produce a relative resource/file path. 
	 * 
	 * ``${{{base-uri}}}`` token can be used in the activator to define the uri relative to the base generation URI (output folder) instead of the parent URI. It might be useful it the parent URI is an absolute external URI.
	 * ``${{{base-uri}}}`` ends with a slash, so there is no need to add a slash. E.g. ``${{{base-uri}}}index.html``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Binding</em>' attribute.
	 * @see #setBinding(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getLink_Binding()
	 * @model
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

} // Link
