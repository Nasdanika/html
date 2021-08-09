/**
 */
package org.nasdanika.html.model.components.impl;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.nasdanika.html.model.bootstrap.Appearance;

import org.nasdanika.html.model.components.ComponentsPackage;
import org.nasdanika.html.model.components.TextToSpeech;

import org.nasdanika.ncore.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Text To Speech</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.components.impl.TextToSpeechImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.impl.TextToSpeechImpl#getVoice <em>Voice</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.impl.TextToSpeechImpl#getFormat <em>Format</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.impl.TextToSpeechImpl#isInterpolate <em>Interpolate</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.impl.TextToSpeechImpl#getAppearance <em>Appearance</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.impl.TextToSpeechImpl#getPath <em>Path</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.impl.TextToSpeechImpl#isEmbed <em>Embed</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TextToSpeechImpl extends ModelElementImpl implements TextToSpeech {
	/**
	 * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String LANGUAGE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getVoice() <em>Voice</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVoice()
	 * @generated
	 * @ordered
	 */
	protected static final String VOICE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getFormat() <em>Format</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormat()
	 * @generated
	 * @ordered
	 */
	protected static final String FORMAT_EDEFAULT = "Text";

	/**
	 * The default value of the '{@link #isInterpolate() <em>Interpolate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInterpolate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INTERPOLATE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isEmbed() <em>Embed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEmbed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EMBED_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TextToSpeechImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentsPackage.Literals.TEXT_TO_SPEECH;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLanguage() {
		return (String)eDynamicGet(ComponentsPackage.TEXT_TO_SPEECH__LANGUAGE, ComponentsPackage.Literals.TEXT_TO_SPEECH__LANGUAGE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLanguage(String newLanguage) {
		eDynamicSet(ComponentsPackage.TEXT_TO_SPEECH__LANGUAGE, ComponentsPackage.Literals.TEXT_TO_SPEECH__LANGUAGE, newLanguage);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVoice() {
		return (String)eDynamicGet(ComponentsPackage.TEXT_TO_SPEECH__VOICE, ComponentsPackage.Literals.TEXT_TO_SPEECH__VOICE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVoice(String newVoice) {
		eDynamicSet(ComponentsPackage.TEXT_TO_SPEECH__VOICE, ComponentsPackage.Literals.TEXT_TO_SPEECH__VOICE, newVoice);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFormat() {
		return (String)eDynamicGet(ComponentsPackage.TEXT_TO_SPEECH__FORMAT, ComponentsPackage.Literals.TEXT_TO_SPEECH__FORMAT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFormat(String newFormat) {
		eDynamicSet(ComponentsPackage.TEXT_TO_SPEECH__FORMAT, ComponentsPackage.Literals.TEXT_TO_SPEECH__FORMAT, newFormat);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isInterpolate() {
		return (Boolean)eDynamicGet(ComponentsPackage.TEXT_TO_SPEECH__INTERPOLATE, ComponentsPackage.Literals.TEXT_TO_SPEECH__INTERPOLATE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInterpolate(boolean newInterpolate) {
		eDynamicSet(ComponentsPackage.TEXT_TO_SPEECH__INTERPOLATE, ComponentsPackage.Literals.TEXT_TO_SPEECH__INTERPOLATE, newInterpolate);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Appearance getAppearance() {
		return (Appearance)eDynamicGet(ComponentsPackage.TEXT_TO_SPEECH__APPEARANCE, ComponentsPackage.Literals.TEXT_TO_SPEECH__APPEARANCE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAppearance(Appearance newAppearance, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newAppearance, ComponentsPackage.TEXT_TO_SPEECH__APPEARANCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAppearance(Appearance newAppearance) {
		eDynamicSet(ComponentsPackage.TEXT_TO_SPEECH__APPEARANCE, ComponentsPackage.Literals.TEXT_TO_SPEECH__APPEARANCE, newAppearance);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPath() {
		return (String)eDynamicGet(ComponentsPackage.TEXT_TO_SPEECH__PATH, ComponentsPackage.Literals.TEXT_TO_SPEECH__PATH, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPath(String newPath) {
		eDynamicSet(ComponentsPackage.TEXT_TO_SPEECH__PATH, ComponentsPackage.Literals.TEXT_TO_SPEECH__PATH, newPath);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isEmbed() {
		return (Boolean)eDynamicGet(ComponentsPackage.TEXT_TO_SPEECH__EMBED, ComponentsPackage.Literals.TEXT_TO_SPEECH__EMBED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEmbed(boolean newEmbed) {
		eDynamicSet(ComponentsPackage.TEXT_TO_SPEECH__EMBED, ComponentsPackage.Literals.TEXT_TO_SPEECH__EMBED, newEmbed);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ComponentsPackage.TEXT_TO_SPEECH__APPEARANCE:
				return basicSetAppearance(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentsPackage.TEXT_TO_SPEECH__LANGUAGE:
				return getLanguage();
			case ComponentsPackage.TEXT_TO_SPEECH__VOICE:
				return getVoice();
			case ComponentsPackage.TEXT_TO_SPEECH__FORMAT:
				return getFormat();
			case ComponentsPackage.TEXT_TO_SPEECH__INTERPOLATE:
				return isInterpolate();
			case ComponentsPackage.TEXT_TO_SPEECH__APPEARANCE:
				return getAppearance();
			case ComponentsPackage.TEXT_TO_SPEECH__PATH:
				return getPath();
			case ComponentsPackage.TEXT_TO_SPEECH__EMBED:
				return isEmbed();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ComponentsPackage.TEXT_TO_SPEECH__LANGUAGE:
				setLanguage((String)newValue);
				return;
			case ComponentsPackage.TEXT_TO_SPEECH__VOICE:
				setVoice((String)newValue);
				return;
			case ComponentsPackage.TEXT_TO_SPEECH__FORMAT:
				setFormat((String)newValue);
				return;
			case ComponentsPackage.TEXT_TO_SPEECH__INTERPOLATE:
				setInterpolate((Boolean)newValue);
				return;
			case ComponentsPackage.TEXT_TO_SPEECH__APPEARANCE:
				setAppearance((Appearance)newValue);
				return;
			case ComponentsPackage.TEXT_TO_SPEECH__PATH:
				setPath((String)newValue);
				return;
			case ComponentsPackage.TEXT_TO_SPEECH__EMBED:
				setEmbed((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ComponentsPackage.TEXT_TO_SPEECH__LANGUAGE:
				setLanguage(LANGUAGE_EDEFAULT);
				return;
			case ComponentsPackage.TEXT_TO_SPEECH__VOICE:
				setVoice(VOICE_EDEFAULT);
				return;
			case ComponentsPackage.TEXT_TO_SPEECH__FORMAT:
				setFormat(FORMAT_EDEFAULT);
				return;
			case ComponentsPackage.TEXT_TO_SPEECH__INTERPOLATE:
				setInterpolate(INTERPOLATE_EDEFAULT);
				return;
			case ComponentsPackage.TEXT_TO_SPEECH__APPEARANCE:
				setAppearance((Appearance)null);
				return;
			case ComponentsPackage.TEXT_TO_SPEECH__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case ComponentsPackage.TEXT_TO_SPEECH__EMBED:
				setEmbed(EMBED_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ComponentsPackage.TEXT_TO_SPEECH__LANGUAGE:
				return LANGUAGE_EDEFAULT == null ? getLanguage() != null : !LANGUAGE_EDEFAULT.equals(getLanguage());
			case ComponentsPackage.TEXT_TO_SPEECH__VOICE:
				return VOICE_EDEFAULT == null ? getVoice() != null : !VOICE_EDEFAULT.equals(getVoice());
			case ComponentsPackage.TEXT_TO_SPEECH__FORMAT:
				return FORMAT_EDEFAULT == null ? getFormat() != null : !FORMAT_EDEFAULT.equals(getFormat());
			case ComponentsPackage.TEXT_TO_SPEECH__INTERPOLATE:
				return isInterpolate() != INTERPOLATE_EDEFAULT;
			case ComponentsPackage.TEXT_TO_SPEECH__APPEARANCE:
				return getAppearance() != null;
			case ComponentsPackage.TEXT_TO_SPEECH__PATH:
				return PATH_EDEFAULT == null ? getPath() != null : !PATH_EDEFAULT.equals(getPath());
			case ComponentsPackage.TEXT_TO_SPEECH__EMBED:
				return isEmbed() != EMBED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //TextToSpeechImpl
