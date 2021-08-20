/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Float</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.FloatImpl#getSide <em>Side</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.FloatImpl#getBreakpoint <em>Breakpoint</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FloatImpl extends MinimalEObjectImpl.Container implements org.nasdanika.html.model.bootstrap.Float {
	/**
	 * The default value of the '{@link #getSide() <em>Side</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSide()
	 * @generated
	 * @ordered
	 */
	protected static final String SIDE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getBreakpoint() <em>Breakpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBreakpoint()
	 * @generated
	 * @ordered
	 */
	protected static final Breakpoint BREAKPOINT_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FloatImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.FLOAT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSide() {
		return (String)eDynamicGet(BootstrapPackage.FLOAT__SIDE, BootstrapPackage.Literals.FLOAT__SIDE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSide(String newSide) {
		eDynamicSet(BootstrapPackage.FLOAT__SIDE, BootstrapPackage.Literals.FLOAT__SIDE, newSide);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Breakpoint getBreakpoint() {
		return (Breakpoint)eDynamicGet(BootstrapPackage.FLOAT__BREAKPOINT, BootstrapPackage.Literals.FLOAT__BREAKPOINT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBreakpoint(Breakpoint newBreakpoint) {
		eDynamicSet(BootstrapPackage.FLOAT__BREAKPOINT, BootstrapPackage.Literals.FLOAT__BREAKPOINT, newBreakpoint);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BootstrapPackage.FLOAT__SIDE:
				return getSide();
			case BootstrapPackage.FLOAT__BREAKPOINT:
				return getBreakpoint();
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
			case BootstrapPackage.FLOAT__SIDE:
				setSide((String)newValue);
				return;
			case BootstrapPackage.FLOAT__BREAKPOINT:
				setBreakpoint((Breakpoint)newValue);
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
			case BootstrapPackage.FLOAT__SIDE:
				setSide(SIDE_EDEFAULT);
				return;
			case BootstrapPackage.FLOAT__BREAKPOINT:
				setBreakpoint(BREAKPOINT_EDEFAULT);
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
			case BootstrapPackage.FLOAT__SIDE:
				return SIDE_EDEFAULT == null ? getSide() != null : !SIDE_EDEFAULT.equals(getSide());
			case BootstrapPackage.FLOAT__BREAKPOINT:
				return BREAKPOINT_EDEFAULT == null ? getBreakpoint() != null : !BREAKPOINT_EDEFAULT.equals(getBreakpoint());
		}
		return super.eIsSet(featureID);
	}

} //FloatImpl
