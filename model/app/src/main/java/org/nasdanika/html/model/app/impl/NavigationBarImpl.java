/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.NavigationBar;
import org.nasdanika.html.model.bootstrap.BootstrapFactory;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Navigation Bar</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.NavigationBarImpl#getBrand <em>Brand</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.NavigationBarImpl#isDark <em>Dark</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.NavigationBarImpl#getExpand <em>Expand</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.NavigationBarImpl#getBackground <em>Background</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NavigationBarImpl extends PagePartImpl implements NavigationBar {
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
	 * The default value of the '{@link #getExpand() <em>Expand</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpand()
	 * @generated
	 * @ordered
	 */
	protected static final Breakpoint EXPAND_EDEFAULT = (Breakpoint)BootstrapFactory.eINSTANCE.createFromString(BootstrapPackage.eINSTANCE.getBreakpoint(), "LARGE");

	/**
	 * The default value of the '{@link #getBackground() <em>Background</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBackground()
	 * @generated
	 * @ordered
	 */
	protected static final Color BACKGROUND_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NavigationBarImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.NAVIGATION_BAR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Label getBrand() {
		return (Label)eDynamicGet(AppPackage.NAVIGATION_BAR__BRAND, AppPackage.Literals.NAVIGATION_BAR__BRAND, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBrand(Label newBrand, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newBrand, AppPackage.NAVIGATION_BAR__BRAND, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBrand(Label newBrand) {
		eDynamicSet(AppPackage.NAVIGATION_BAR__BRAND, AppPackage.Literals.NAVIGATION_BAR__BRAND, newBrand);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDark() {
		return (Boolean)eDynamicGet(AppPackage.NAVIGATION_BAR__DARK, AppPackage.Literals.NAVIGATION_BAR__DARK, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDark(boolean newDark) {
		eDynamicSet(AppPackage.NAVIGATION_BAR__DARK, AppPackage.Literals.NAVIGATION_BAR__DARK, newDark);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Breakpoint getExpand() {
		return (Breakpoint)eDynamicGet(AppPackage.NAVIGATION_BAR__EXPAND, AppPackage.Literals.NAVIGATION_BAR__EXPAND, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setExpand(Breakpoint newExpand) {
		eDynamicSet(AppPackage.NAVIGATION_BAR__EXPAND, AppPackage.Literals.NAVIGATION_BAR__EXPAND, newExpand);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Color getBackground() {
		return (Color)eDynamicGet(AppPackage.NAVIGATION_BAR__BACKGROUND, AppPackage.Literals.NAVIGATION_BAR__BACKGROUND, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBackground(Color newBackground) {
		eDynamicSet(AppPackage.NAVIGATION_BAR__BACKGROUND, AppPackage.Literals.NAVIGATION_BAR__BACKGROUND, newBackground);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AppPackage.NAVIGATION_BAR__BRAND:
				return basicSetBrand(null, msgs);
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
			case AppPackage.NAVIGATION_BAR__BRAND:
				return getBrand();
			case AppPackage.NAVIGATION_BAR__DARK:
				return isDark();
			case AppPackage.NAVIGATION_BAR__EXPAND:
				return getExpand();
			case AppPackage.NAVIGATION_BAR__BACKGROUND:
				return getBackground();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case AppPackage.NAVIGATION_BAR__BRAND:
				setBrand((Label)newValue);
				return;
			case AppPackage.NAVIGATION_BAR__DARK:
				setDark((Boolean)newValue);
				return;
			case AppPackage.NAVIGATION_BAR__EXPAND:
				setExpand((Breakpoint)newValue);
				return;
			case AppPackage.NAVIGATION_BAR__BACKGROUND:
				setBackground((Color)newValue);
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
			case AppPackage.NAVIGATION_BAR__BRAND:
				setBrand((Label)null);
				return;
			case AppPackage.NAVIGATION_BAR__DARK:
				setDark(DARK_EDEFAULT);
				return;
			case AppPackage.NAVIGATION_BAR__EXPAND:
				setExpand(EXPAND_EDEFAULT);
				return;
			case AppPackage.NAVIGATION_BAR__BACKGROUND:
				setBackground(BACKGROUND_EDEFAULT);
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
			case AppPackage.NAVIGATION_BAR__BRAND:
				return getBrand() != null;
			case AppPackage.NAVIGATION_BAR__DARK:
				return isDark() != DARK_EDEFAULT;
			case AppPackage.NAVIGATION_BAR__EXPAND:
				return EXPAND_EDEFAULT == null ? getExpand() != null : !EXPAND_EDEFAULT.equals(getExpand());
			case AppPackage.NAVIGATION_BAR__BACKGROUND:
				return BACKGROUND_EDEFAULT == null ? getBackground() != null : !BACKGROUND_EDEFAULT.equals(getBackground());
		}
		return super.eIsSet(featureID);
	}

} //NavigationBarImpl
