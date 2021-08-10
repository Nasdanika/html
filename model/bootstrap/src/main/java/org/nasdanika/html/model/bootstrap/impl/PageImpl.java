/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.Page;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.PageImpl#isCdn <em>Cdn</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.PageImpl#getTheme <em>Theme</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PageImpl extends org.nasdanika.html.model.html.impl.PageImpl implements Page {
	/**
	 * The default value of the '{@link #isCdn() <em>Cdn</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCdn()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CDN_EDEFAULT = true;

	/**
	 * The default value of the '{@link #getTheme() <em>Theme</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTheme()
	 * @generated
	 * @ordered
	 */
	protected static final Theme THEME_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.PAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isCdn() {
		return (Boolean)eDynamicGet(BootstrapPackage.PAGE__CDN, BootstrapPackage.Literals.PAGE__CDN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCdn(boolean newCdn) {
		eDynamicSet(BootstrapPackage.PAGE__CDN, BootstrapPackage.Literals.PAGE__CDN, newCdn);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Theme getTheme() {
		return (Theme)eDynamicGet(BootstrapPackage.PAGE__THEME, BootstrapPackage.Literals.PAGE__THEME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTheme(Theme newTheme) {
		eDynamicSet(BootstrapPackage.PAGE__THEME, BootstrapPackage.Literals.PAGE__THEME, newTheme);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BootstrapPackage.PAGE__CDN:
				return isCdn();
			case BootstrapPackage.PAGE__THEME:
				return getTheme();
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
			case BootstrapPackage.PAGE__CDN:
				setCdn((Boolean)newValue);
				return;
			case BootstrapPackage.PAGE__THEME:
				setTheme((Theme)newValue);
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
			case BootstrapPackage.PAGE__CDN:
				setCdn(CDN_EDEFAULT);
				return;
			case BootstrapPackage.PAGE__THEME:
				setTheme(THEME_EDEFAULT);
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
			case BootstrapPackage.PAGE__CDN:
				return isCdn() != CDN_EDEFAULT;
			case BootstrapPackage.PAGE__THEME:
				return THEME_EDEFAULT == null ? getTheme() != null : !THEME_EDEFAULT.equals(getTheme());
		}
		return super.eIsSet(featureID);
	}

} //PageImpl
