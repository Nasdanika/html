/**
 */
package org.nasdanika.html.model.components;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text To Speech Text</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Speaks ``text`` attribute.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.components.TextToSpeechText#getText <em>Text</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.components.ComponentsPackage#getTextToSpeechText()
 * @model
 * @generated
 */
public interface TextToSpeechText extends TextToSpeech {
	/**
	 * Returns the value of the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Text to speak, in case of  [SSML](https://cloud.google.com/text-to-speech/docs/ssml) format ``<speak>`` and  ``</speak>`` opening and closing tags are implied.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Text</em>' attribute.
	 * @see #setText(String)
	 * @see org.nasdanika.html.model.components.ComponentsPackage#getTextToSpeechText_Text()
	 * @model required="true"
	 * @generated
	 */
	String getText();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.components.TextToSpeechText#getText <em>Text</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' attribute.
	 * @see #getText()
	 * @generated
	 */
	void setText(String value);

} // TextToSpeechText
