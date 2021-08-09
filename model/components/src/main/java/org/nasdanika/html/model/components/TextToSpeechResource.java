/**
 */
package org.nasdanika.html.model.components;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text To Speech Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Speaks resource specified in the ``location`` attribute.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.components.TextToSpeechResource#getLocation <em>Location</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.components.ComponentsPackage#getTextToSpeechResource()
 * @model
 * @generated
 */
public interface TextToSpeechResource extends TextToSpeech {
	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Markdown resource location. The resource location is resolved relative to the model resource.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.nasdanika.html.model.components.ComponentsPackage#getTextToSpeechResource_Location()
	 * @model required="true"
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.components.TextToSpeechResource#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

} // TextToSpeechResource
