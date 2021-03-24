/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.Button;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Button</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ButtonImpl#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ButtonImpl#isOutline <em>Outline</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ButtonImpl extends DivImpl implements Button {
	/**
	 * The default value of the '{@link #getColor() <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColor()
	 * @generated
	 * @ordered
	 */
	protected static final String COLOR_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isOutline() <em>Outline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOutline()
	 * @generated
	 * @ordered
	 */
	protected static final boolean OUTLINE_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ButtonImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.BUTTON;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getColor() {
		return (String)eDynamicGet(BootstrapPackage.BUTTON__COLOR, BootstrapPackage.Literals.BUTTON__COLOR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColor(String newColor) {
		eDynamicSet(BootstrapPackage.BUTTON__COLOR, BootstrapPackage.Literals.BUTTON__COLOR, newColor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isOutline() {
		return (Boolean)eDynamicGet(BootstrapPackage.BUTTON__OUTLINE, BootstrapPackage.Literals.BUTTON__OUTLINE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOutline(boolean newOutline) {
		eDynamicSet(BootstrapPackage.BUTTON__OUTLINE, BootstrapPackage.Literals.BUTTON__OUTLINE, newOutline);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BootstrapPackage.BUTTON__COLOR:
				return getColor();
			case BootstrapPackage.BUTTON__OUTLINE:
				return isOutline();
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
			case BootstrapPackage.BUTTON__COLOR:
				setColor((String)newValue);
				return;
			case BootstrapPackage.BUTTON__OUTLINE:
				setOutline((Boolean)newValue);
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
			case BootstrapPackage.BUTTON__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
			case BootstrapPackage.BUTTON__OUTLINE:
				setOutline(OUTLINE_EDEFAULT);
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
			case BootstrapPackage.BUTTON__COLOR:
				return COLOR_EDEFAULT == null ? getColor() != null : !COLOR_EDEFAULT.equals(getColor());
			case BootstrapPackage.BUTTON__OUTLINE:
				return isOutline() != OUTLINE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //ButtonImpl
