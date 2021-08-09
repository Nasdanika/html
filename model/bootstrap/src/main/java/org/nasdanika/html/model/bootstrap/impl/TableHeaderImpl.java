/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.TableHeader;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Header</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TableHeaderImpl#isDark <em>Dark</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.TableHeaderImpl#isLight <em>Light</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TableHeaderImpl extends TableSectionImpl implements TableHeader {
	/**
	 * The default value of the '{@link #isDark() <em>Dark</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDark()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DARK_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isLight() <em>Light</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLight()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LIGHT_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TableHeaderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.TABLE_HEADER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDark() {
		return (Boolean)eDynamicGet(BootstrapPackage.TABLE_HEADER__DARK, BootstrapPackage.Literals.TABLE_HEADER__DARK, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDark(boolean newDark) {
		eDynamicSet(BootstrapPackage.TABLE_HEADER__DARK, BootstrapPackage.Literals.TABLE_HEADER__DARK, newDark);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isLight() {
		return (Boolean)eDynamicGet(BootstrapPackage.TABLE_HEADER__LIGHT, BootstrapPackage.Literals.TABLE_HEADER__LIGHT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLight(boolean newLight) {
		eDynamicSet(BootstrapPackage.TABLE_HEADER__LIGHT, BootstrapPackage.Literals.TABLE_HEADER__LIGHT, newLight);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BootstrapPackage.TABLE_HEADER__DARK:
				return isDark();
			case BootstrapPackage.TABLE_HEADER__LIGHT:
				return isLight();
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
			case BootstrapPackage.TABLE_HEADER__DARK:
				setDark((Boolean)newValue);
				return;
			case BootstrapPackage.TABLE_HEADER__LIGHT:
				setLight((Boolean)newValue);
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
			case BootstrapPackage.TABLE_HEADER__DARK:
				setDark(DARK_EDEFAULT);
				return;
			case BootstrapPackage.TABLE_HEADER__LIGHT:
				setLight(LIGHT_EDEFAULT);
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
			case BootstrapPackage.TABLE_HEADER__DARK:
				return isDark() != DARK_EDEFAULT;
			case BootstrapPackage.TABLE_HEADER__LIGHT:
				return isLight() != LIGHT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //TableHeaderImpl
