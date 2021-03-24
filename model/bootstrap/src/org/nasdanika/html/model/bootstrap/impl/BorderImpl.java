/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.Border;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Border</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.BorderImpl#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.BorderImpl#isTop <em>Top</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.BorderImpl#isBottom <em>Bottom</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.BorderImpl#isLeft <em>Left</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.BorderImpl#isRight <em>Right</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BorderImpl extends MinimalEObjectImpl.Container implements Border {
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
	 * The default value of the '{@link #isTop() <em>Top</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTop()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TOP_EDEFAULT = true;

	/**
	 * The default value of the '{@link #isBottom() <em>Bottom</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBottom()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BOTTOM_EDEFAULT = true;

	/**
	 * The default value of the '{@link #isLeft() <em>Left</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLeft()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LEFT_EDEFAULT = true;

	/**
	 * The default value of the '{@link #isRight() <em>Right</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRight()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RIGHT_EDEFAULT = true;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BorderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.BORDER;
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
	public String getColor() {
		return (String)eDynamicGet(BootstrapPackage.BORDER__COLOR, BootstrapPackage.Literals.BORDER__COLOR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColor(String newColor) {
		eDynamicSet(BootstrapPackage.BORDER__COLOR, BootstrapPackage.Literals.BORDER__COLOR, newColor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isTop() {
		return (Boolean)eDynamicGet(BootstrapPackage.BORDER__TOP, BootstrapPackage.Literals.BORDER__TOP, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTop(boolean newTop) {
		eDynamicSet(BootstrapPackage.BORDER__TOP, BootstrapPackage.Literals.BORDER__TOP, newTop);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isBottom() {
		return (Boolean)eDynamicGet(BootstrapPackage.BORDER__BOTTOM, BootstrapPackage.Literals.BORDER__BOTTOM, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBottom(boolean newBottom) {
		eDynamicSet(BootstrapPackage.BORDER__BOTTOM, BootstrapPackage.Literals.BORDER__BOTTOM, newBottom);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isLeft() {
		return (Boolean)eDynamicGet(BootstrapPackage.BORDER__LEFT, BootstrapPackage.Literals.BORDER__LEFT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLeft(boolean newLeft) {
		eDynamicSet(BootstrapPackage.BORDER__LEFT, BootstrapPackage.Literals.BORDER__LEFT, newLeft);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRight() {
		return (Boolean)eDynamicGet(BootstrapPackage.BORDER__RIGHT, BootstrapPackage.Literals.BORDER__RIGHT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRight(boolean newRight) {
		eDynamicSet(BootstrapPackage.BORDER__RIGHT, BootstrapPackage.Literals.BORDER__RIGHT, newRight);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BootstrapPackage.BORDER__COLOR:
				return getColor();
			case BootstrapPackage.BORDER__TOP:
				return isTop();
			case BootstrapPackage.BORDER__BOTTOM:
				return isBottom();
			case BootstrapPackage.BORDER__LEFT:
				return isLeft();
			case BootstrapPackage.BORDER__RIGHT:
				return isRight();
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
			case BootstrapPackage.BORDER__COLOR:
				setColor((String)newValue);
				return;
			case BootstrapPackage.BORDER__TOP:
				setTop((Boolean)newValue);
				return;
			case BootstrapPackage.BORDER__BOTTOM:
				setBottom((Boolean)newValue);
				return;
			case BootstrapPackage.BORDER__LEFT:
				setLeft((Boolean)newValue);
				return;
			case BootstrapPackage.BORDER__RIGHT:
				setRight((Boolean)newValue);
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
			case BootstrapPackage.BORDER__COLOR:
				setColor(COLOR_EDEFAULT);
				return;
			case BootstrapPackage.BORDER__TOP:
				setTop(TOP_EDEFAULT);
				return;
			case BootstrapPackage.BORDER__BOTTOM:
				setBottom(BOTTOM_EDEFAULT);
				return;
			case BootstrapPackage.BORDER__LEFT:
				setLeft(LEFT_EDEFAULT);
				return;
			case BootstrapPackage.BORDER__RIGHT:
				setRight(RIGHT_EDEFAULT);
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
			case BootstrapPackage.BORDER__COLOR:
				return COLOR_EDEFAULT == null ? getColor() != null : !COLOR_EDEFAULT.equals(getColor());
			case BootstrapPackage.BORDER__TOP:
				return isTop() != TOP_EDEFAULT;
			case BootstrapPackage.BORDER__BOTTOM:
				return isBottom() != BOTTOM_EDEFAULT;
			case BootstrapPackage.BORDER__LEFT:
				return isLeft() != LEFT_EDEFAULT;
			case BootstrapPackage.BORDER__RIGHT:
				return isRight() != RIGHT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //BorderImpl
