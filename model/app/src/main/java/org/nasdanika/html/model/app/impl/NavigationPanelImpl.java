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
import org.nasdanika.html.model.app.NavigationPanel;
import org.nasdanika.html.model.app.NavigationPanelStyle;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Navigation Panel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.NavigationPanelImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.NavigationPanelImpl#getItems <em>Items</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NavigationPanelImpl extends PagePartImpl implements NavigationPanel {
	/**
	 * The default value of the '{@link #getStyle() <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyle()
	 * @generated
	 * @ordered
	 */
	protected static final NavigationPanelStyle STYLE_EDEFAULT = NavigationPanelStyle.AUTO;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NavigationPanelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.NAVIGATION_PANEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NavigationPanelStyle getStyle() {
		return (NavigationPanelStyle)eDynamicGet(AppPackage.NAVIGATION_PANEL__STYLE, AppPackage.Literals.NAVIGATION_PANEL__STYLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStyle(NavigationPanelStyle newStyle) {
		eDynamicSet(AppPackage.NAVIGATION_PANEL__STYLE, AppPackage.Literals.NAVIGATION_PANEL__STYLE, newStyle);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<org.nasdanika.html.model.bootstrap.BootstrapElement> getItems() {
		return (EList<org.nasdanika.html.model.bootstrap.BootstrapElement>)eDynamicGet(AppPackage.NAVIGATION_PANEL__ITEMS, AppPackage.Literals.NAVIGATION_PANEL__ITEMS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AppPackage.NAVIGATION_PANEL__ITEMS:
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
			case AppPackage.NAVIGATION_PANEL__STYLE:
				return getStyle();
			case AppPackage.NAVIGATION_PANEL__ITEMS:
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
			case AppPackage.NAVIGATION_PANEL__STYLE:
				setStyle((NavigationPanelStyle)newValue);
				return;
			case AppPackage.NAVIGATION_PANEL__ITEMS:
				getItems().clear();
				getItems().addAll((Collection<? extends org.nasdanika.html.model.bootstrap.BootstrapElement>)newValue);
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
			case AppPackage.NAVIGATION_PANEL__STYLE:
				setStyle(STYLE_EDEFAULT);
				return;
			case AppPackage.NAVIGATION_PANEL__ITEMS:
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
			case AppPackage.NAVIGATION_PANEL__STYLE:
				return getStyle() != STYLE_EDEFAULT;
			case AppPackage.NAVIGATION_PANEL__ITEMS:
				return !getItems().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //NavigationPanelImpl
