/**
 */
package org.nasdanika.html.model.app.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.NavigationBar;
import org.nasdanika.html.model.bootstrap.BootstrapElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Navigation Bar</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.NavigationBarImpl#getBrand <em>Brand</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.NavigationBarImpl#getItems <em>Items</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NavigationBarImpl extends PagePartImpl implements NavigationBar {
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
	@SuppressWarnings("unchecked")
	@Override
	public EList<BootstrapElement> getItems() {
		return (EList<BootstrapElement>)eDynamicGet(AppPackage.NAVIGATION_BAR__ITEMS, AppPackage.Literals.NAVIGATION_BAR__ITEMS, true, true);
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
			case AppPackage.NAVIGATION_BAR__ITEMS:
				return ((InternalEList<?>)getItems()).basicRemove(otherEnd, msgs);
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
			case AppPackage.NAVIGATION_BAR__ITEMS:
				return getItems();
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
			case AppPackage.NAVIGATION_BAR__ITEMS:
				getItems().clear();
				getItems().addAll((Collection<? extends BootstrapElement>)newValue);
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
			case AppPackage.NAVIGATION_BAR__ITEMS:
				getItems().clear();
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
			case AppPackage.NAVIGATION_BAR__ITEMS:
				return !getItems().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //NavigationBarImpl