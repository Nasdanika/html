/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.BootstrapPage;

import org.nasdanika.html.model.html.impl.PageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.BootstrapPageImpl#isCdn <em>Cdn</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.BootstrapPageImpl#getTheme <em>Theme</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BootstrapPageImpl extends PageImpl implements BootstrapPage {
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
	protected static final String THEME_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BootstrapPageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.BOOTSTRAP_PAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isCdn() {
		return (Boolean)eDynamicGet(BootstrapPackage.BOOTSTRAP_PAGE__CDN, BootstrapPackage.Literals.BOOTSTRAP_PAGE__CDN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCdn(boolean newCdn) {
		eDynamicSet(BootstrapPackage.BOOTSTRAP_PAGE__CDN, BootstrapPackage.Literals.BOOTSTRAP_PAGE__CDN, newCdn);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTheme() {
		return (String)eDynamicGet(BootstrapPackage.BOOTSTRAP_PAGE__THEME, BootstrapPackage.Literals.BOOTSTRAP_PAGE__THEME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTheme(String newTheme) {
		eDynamicSet(BootstrapPackage.BOOTSTRAP_PAGE__THEME, BootstrapPackage.Literals.BOOTSTRAP_PAGE__THEME, newTheme);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BootstrapPackage.BOOTSTRAP_PAGE__CDN:
				return isCdn();
			case BootstrapPackage.BOOTSTRAP_PAGE__THEME:
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
			case BootstrapPackage.BOOTSTRAP_PAGE__CDN:
				setCdn((Boolean)newValue);
				return;
			case BootstrapPackage.BOOTSTRAP_PAGE__THEME:
				setTheme((String)newValue);
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
			case BootstrapPackage.BOOTSTRAP_PAGE__CDN:
				setCdn(CDN_EDEFAULT);
				return;
			case BootstrapPackage.BOOTSTRAP_PAGE__THEME:
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
			case BootstrapPackage.BOOTSTRAP_PAGE__CDN:
				return isCdn() != CDN_EDEFAULT;
			case BootstrapPackage.BOOTSTRAP_PAGE__THEME:
				return THEME_EDEFAULT == null ? getTheme() != null : !THEME_EDEFAULT.equals(getTheme());
		}
		return super.eIsSet(featureID);
	}

} //BootstrapPageImpl
