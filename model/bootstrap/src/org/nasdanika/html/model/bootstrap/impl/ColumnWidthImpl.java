/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.ColumnWidth;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Column Width</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ColumnWidthImpl#getBreakpoint <em>Breakpoint</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ColumnWidthImpl#getWidth <em>Width</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ColumnWidthImpl extends MinimalEObjectImpl.Container implements ColumnWidth {
	/**
	 * The default value of the '{@link #getBreakpoint() <em>Breakpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBreakpoint()
	 * @generated
	 * @ordered
	 */
	protected static final String BREAKPOINT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected static final String WIDTH_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ColumnWidthImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.COLUMN_WIDTH;
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
	public String getBreakpoint() {
		return (String)eDynamicGet(BootstrapPackage.COLUMN_WIDTH__BREAKPOINT, BootstrapPackage.Literals.COLUMN_WIDTH__BREAKPOINT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBreakpoint(String newBreakpoint) {
		eDynamicSet(BootstrapPackage.COLUMN_WIDTH__BREAKPOINT, BootstrapPackage.Literals.COLUMN_WIDTH__BREAKPOINT, newBreakpoint);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getWidth() {
		return (String)eDynamicGet(BootstrapPackage.COLUMN_WIDTH__WIDTH, BootstrapPackage.Literals.COLUMN_WIDTH__WIDTH, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWidth(String newWidth) {
		eDynamicSet(BootstrapPackage.COLUMN_WIDTH__WIDTH, BootstrapPackage.Literals.COLUMN_WIDTH__WIDTH, newWidth);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BootstrapPackage.COLUMN_WIDTH__BREAKPOINT:
				return getBreakpoint();
			case BootstrapPackage.COLUMN_WIDTH__WIDTH:
				return getWidth();
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
			case BootstrapPackage.COLUMN_WIDTH__BREAKPOINT:
				setBreakpoint((String)newValue);
				return;
			case BootstrapPackage.COLUMN_WIDTH__WIDTH:
				setWidth((String)newValue);
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
			case BootstrapPackage.COLUMN_WIDTH__BREAKPOINT:
				setBreakpoint(BREAKPOINT_EDEFAULT);
				return;
			case BootstrapPackage.COLUMN_WIDTH__WIDTH:
				setWidth(WIDTH_EDEFAULT);
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
			case BootstrapPackage.COLUMN_WIDTH__BREAKPOINT:
				return BREAKPOINT_EDEFAULT == null ? getBreakpoint() != null : !BREAKPOINT_EDEFAULT.equals(getBreakpoint());
			case BootstrapPackage.COLUMN_WIDTH__WIDTH:
				return WIDTH_EDEFAULT == null ? getWidth() != null : !WIDTH_EDEFAULT.equals(getWidth());
		}
		return super.eIsSet(featureID);
	}

} //ColumnWidthImpl
