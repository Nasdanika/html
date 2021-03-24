/**
 */
package org.nasdanika.html.model.components;

import org.nasdanika.html.model.bootstrap.Appearance;

import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text To Speech</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Uses [Google Text-to-Speech](https://cloud.google.com/text-to-speech) to synthesize 
 * voice from text or [SSML](https://cloud.google.com/text-to-speech/docs/ssml). 
 * Voice is output to an mp3 file. File name is defined by the ``path`` attribute if it is not blank. 
 * In this case path is resolved relative to the containing action. 
 * If ``path`` attribute is blank then the file name is computed as a digest of the sound bytes.
 * 
 * Generates audio tag which plays the synthesized speech.
 * 
 * Use of text to speech requires ``GOOGLE_APPLICATION_CREDENTIALS`` environment variable to be set to the location of the private key JSON file.
 * See https://developers.google.com/accounts/docs/application-default-credentials for more information.
 *    
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.components.TextToSpeech#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.TextToSpeech#getVoice <em>Voice</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.TextToSpeech#getFormat <em>Format</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.TextToSpeech#isInterpolate <em>Interpolate</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.TextToSpeech#getAppearance <em>Appearance</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.TextToSpeech#getPath <em>Path</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.TextToSpeech#isEmbed <em>Embed</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.components.ComponentsPackage#getTextToSpeech()
 * @model abstract="true"
 * @generated
 */
public interface TextToSpeech extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Language and locale code, e.g. ``en-US``. If blank, then the contextual language and locale are used - hardcoded in the UI and configurable in the CLI. 
	 * See [Supported voices and languages](https://cloud.google.com/text-to-speech/docs/voices) for a list of locales and voices.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Language</em>' attribute.
	 * @see #setLanguage(String)
	 * @see org.nasdanika.html.model.components.ComponentsPackage#getTextToSpeech_Language()
	 * @model
	 * @generated
	 */
	String getLanguage();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.components.TextToSpeech#getLanguage <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Language</em>' attribute.
	 * @see #getLanguage()
	 * @generated
	 */
	void setLanguage(String value);

	/**
	 * Returns the value of the '<em><b>Voice</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Voice name, e.g. ``en-US-Wavenet-D``. If blank, then the contextual voice is used - hardcoded in the UI and configurable in the CLI. 
	 * See [Supported voices and languages](https://cloud.google.com/text-to-speech/docs/voices) for a list of locales and voices.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Voice</em>' attribute.
	 * @see #setVoice(String)
	 * @see org.nasdanika.html.model.components.ComponentsPackage#getTextToSpeech_Voice()
	 * @model
	 * @generated
	 */
	String getVoice();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.components.TextToSpeech#getVoice <em>Voice</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Voice</em>' attribute.
	 * @see #getVoice()
	 * @generated
	 */
	void setVoice(String value);

	/**
	 * Returns the value of the '<em><b>Format</b></em>' attribute.
	 * The default value is <code>"Text"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Text format - ``Text`` or ``SSML``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Format</em>' attribute.
	 * @see #setFormat(String)
	 * @see org.nasdanika.html.model.components.ComponentsPackage#getTextToSpeech_Format()
	 * @model default="Text"
	 * @generated
	 */
	String getFormat();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.components.TextToSpeech#getFormat <em>Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Format</em>' attribute.
	 * @see #getFormat()
	 * @generated
	 */
	void setFormat(String value);

	/**
	 * Returns the value of the '<em><b>Interpolate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, text/ssml is interpolated before speech generation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Interpolate</em>' attribute.
	 * @see #setInterpolate(boolean)
	 * @see org.nasdanika.html.model.components.ComponentsPackage#getTextToSpeech_Interpolate()
	 * @model
	 * @generated
	 */
	boolean isInterpolate();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.components.TextToSpeech#isInterpolate <em>Interpolate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interpolate</em>' attribute.
	 * @see #isInterpolate()
	 * @generated
	 */
	void setInterpolate(boolean value);

	/**
	 * Returns the value of the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Appearance to apply to the generated audio tag.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Appearance</em>' containment reference.
	 * @see #setAppearance(Appearance)
	 * @see org.nasdanika.html.model.components.ComponentsPackage#getTextToSpeech_Appearance()
	 * @model containment="true"
	 * @generated
	 */
	Appearance getAppearance();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.components.TextToSpeech#getAppearance <em>Appearance</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Appearance</em>' containment reference.
	 * @see #getAppearance()
	 * @generated
	 */
	void setAppearance(Appearance value);

	/**
	 * Returns the value of the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * MP3 resource location (file name) relative to the containing action context URI.
	 * If this attribute is blank then the file name is computed as a digest of the sound bytes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Path</em>' attribute.
	 * @see #setPath(String)
	 * @see org.nasdanika.html.model.components.ComponentsPackage#getTextToSpeech_Path()
	 * @model
	 * @generated
	 */
	String getPath();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.components.TextToSpeech#getPath <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Path</em>' attribute.
	 * @see #getPath()
	 * @generated
	 */
	void setPath(String value);

	/**
	 * Returns the value of the '<em><b>Embed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, audio data is not stored to a file, but is embedded into the page using ``data:`` URI.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Embed</em>' attribute.
	 * @see #setEmbed(boolean)
	 * @see org.nasdanika.html.model.components.ComponentsPackage#getTextToSpeech_Embed()
	 * @model
	 * @generated
	 */
	boolean isEmbed();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.components.TextToSpeech#isEmbed <em>Embed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Embed</em>' attribute.
	 * @see #isEmbed()
	 * @generated
	 */
	void setEmbed(boolean value);

} // TextToSpeech
