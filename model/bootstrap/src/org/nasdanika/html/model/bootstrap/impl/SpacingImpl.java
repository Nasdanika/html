/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.Spacing;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Spacing</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.SpacingImpl#getSize <em>Size</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.SpacingImpl#getBreakpoint <em>Breakpoint</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.SpacingImpl#isTop <em>Top</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.SpacingImpl#isBottom <em>Bottom</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.SpacingImpl#isLeft <em>Left</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.SpacingImpl#isRight <em>Right</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.SpacingImpl#isX <em>X</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.SpacingImpl#isY <em>Y</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SpacingImpl extends MinimalEObjectImpl.Container implements Spacing {
	/**
	 * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected static final String SIZE_EDEFAULT = null;

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
	 * The default value of the '{@link #isX() <em>X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isX()
	 * @generated
	 * @ordered
	 */
	protected static final boolean X_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isY() <em>Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isY()
	 * @generated
	 * @ordered
	 */
	protected static final boolean Y_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpacingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.SPACING;
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
	public String getSize() {
		return (String)eDynamicGet(BootstrapPackage.SPACING__SIZE, BootstrapPackage.Literals.SPACING__SIZE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSize(String newSize) {
		eDynamicSet(BootstrapPackage.SPACING__SIZE, BootstrapPackage.Literals.SPACING__SIZE, newSize);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBreakpoint() {
		return (String)eDynamicGet(BootstrapPackage.SPACING__BREAKPOINT, BootstrapPackage.Literals.SPACING__BREAKPOINT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBreakpoint(String newBreakpoint) {
		eDynamicSet(BootstrapPackage.SPACING__BREAKPOINT, BootstrapPackage.Literals.SPACING__BREAKPOINT, newBreakpoint);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isTop() {
		return (Boolean)eDynamicGet(BootstrapPackage.SPACING__TOP, BootstrapPackage.Literals.SPACING__TOP, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTop(boolean newTop) {
		eDynamicSet(BootstrapPackage.SPACING__TOP, BootstrapPackage.Literals.SPACING__TOP, newTop);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isBottom() {
		return (Boolean)eDynamicGet(BootstrapPackage.SPACING__BOTTOM, BootstrapPackage.Literals.SPACING__BOTTOM, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBottom(boolean newBottom) {
		eDynamicSet(BootstrapPackage.SPACING__BOTTOM, BootstrapPackage.Literals.SPACING__BOTTOM, newBottom);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isLeft() {
		return (Boolean)eDynamicGet(BootstrapPackage.SPACING__LEFT, BootstrapPackage.Literals.SPACING__LEFT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLeft(boolean newLeft) {
		eDynamicSet(BootstrapPackage.SPACING__LEFT, BootstrapPackage.Literals.SPACING__LEFT, newLeft);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRight() {
		return (Boolean)eDynamicGet(BootstrapPackage.SPACING__RIGHT, BootstrapPackage.Literals.SPACING__RIGHT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRight(boolean newRight) {
		eDynamicSet(BootstrapPackage.SPACING__RIGHT, BootstrapPackage.Literals.SPACING__RIGHT, newRight);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isX() {
		return (Boolean)eDynamicGet(BootstrapPackage.SPACING__X, BootstrapPackage.Literals.SPACING__X, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setX(boolean newX) {
		eDynamicSet(BootstrapPackage.SPACING__X, BootstrapPackage.Literals.SPACING__X, newX);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isY() {
		return (Boolean)eDynamicGet(BootstrapPackage.SPACING__Y, BootstrapPackage.Literals.SPACING__Y, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setY(boolean newY) {
		eDynamicSet(BootstrapPackage.SPACING__Y, BootstrapPackage.Literals.SPACING__Y, newY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BootstrapPackage.SPACING__SIZE:
				return getSize();
			case BootstrapPackage.SPACING__BREAKPOINT:
				return getBreakpoint();
			case BootstrapPackage.SPACING__TOP:
				return isTop();
			case BootstrapPackage.SPACING__BOTTOM:
				return isBottom();
			case BootstrapPackage.SPACING__LEFT:
				return isLeft();
			case BootstrapPackage.SPACING__RIGHT:
				return isRight();
			case BootstrapPackage.SPACING__X:
				return isX();
			case BootstrapPackage.SPACING__Y:
				return isY();
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
			case BootstrapPackage.SPACING__SIZE:
				setSize((String)newValue);
				return;
			case BootstrapPackage.SPACING__BREAKPOINT:
				setBreakpoint((String)newValue);
				return;
			case BootstrapPackage.SPACING__TOP:
				setTop((Boolean)newValue);
				return;
			case BootstrapPackage.SPACING__BOTTOM:
				setBottom((Boolean)newValue);
				return;
			case BootstrapPackage.SPACING__LEFT:
				setLeft((Boolean)newValue);
				return;
			case BootstrapPackage.SPACING__RIGHT:
				setRight((Boolean)newValue);
				return;
			case BootstrapPackage.SPACING__X:
				setX((Boolean)newValue);
				return;
			case BootstrapPackage.SPACING__Y:
				setY((Boolean)newValue);
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
			case BootstrapPackage.SPACING__SIZE:
				setSize(SIZE_EDEFAULT);
				return;
			case BootstrapPackage.SPACING__BREAKPOINT:
				setBreakpoint(BREAKPOINT_EDEFAULT);
				return;
			case BootstrapPackage.SPACING__TOP:
				setTop(TOP_EDEFAULT);
				return;
			case BootstrapPackage.SPACING__BOTTOM:
				setBottom(BOTTOM_EDEFAULT);
				return;
			case BootstrapPackage.SPACING__LEFT:
				setLeft(LEFT_EDEFAULT);
				return;
			case BootstrapPackage.SPACING__RIGHT:
				setRight(RIGHT_EDEFAULT);
				return;
			case BootstrapPackage.SPACING__X:
				setX(X_EDEFAULT);
				return;
			case BootstrapPackage.SPACING__Y:
				setY(Y_EDEFAULT);
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
			case BootstrapPackage.SPACING__SIZE:
				return SIZE_EDEFAULT == null ? getSize() != null : !SIZE_EDEFAULT.equals(getSize());
			case BootstrapPackage.SPACING__BREAKPOINT:
				return BREAKPOINT_EDEFAULT == null ? getBreakpoint() != null : !BREAKPOINT_EDEFAULT.equals(getBreakpoint());
			case BootstrapPackage.SPACING__TOP:
				return isTop() != TOP_EDEFAULT;
			case BootstrapPackage.SPACING__BOTTOM:
				return isBottom() != BOTTOM_EDEFAULT;
			case BootstrapPackage.SPACING__LEFT:
				return isLeft() != LEFT_EDEFAULT;
			case BootstrapPackage.SPACING__RIGHT:
				return isRight() != RIGHT_EDEFAULT;
			case BootstrapPackage.SPACING__X:
				return isX() != X_EDEFAULT;
			case BootstrapPackage.SPACING__Y:
				return isY() != Y_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //SpacingImpl
