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
import org.nasdanika.html.model.app.Header;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.bootstrap.BootstrapElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Header</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.HeaderImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.HeaderImpl#getNavigation <em>Navigation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class HeaderImpl extends PagePartImpl implements Header {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HeaderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.HEADER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Label getTitle() {
		return (Label)eDynamicGet(AppPackage.HEADER__TITLE, AppPackage.Literals.HEADER__TITLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTitle(Label newTitle, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newTitle, AppPackage.HEADER__TITLE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTitle(Label newTitle) {
		eDynamicSet(AppPackage.HEADER__TITLE, AppPackage.Literals.HEADER__TITLE, newTitle);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<BootstrapElement> getNavigation() {
		return (EList<BootstrapElement>)eDynamicGet(AppPackage.HEADER__NAVIGATION, AppPackage.Literals.HEADER__NAVIGATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AppPackage.HEADER__TITLE:
				return basicSetTitle(null, msgs);
			case AppPackage.HEADER__NAVIGATION:
				return ((InternalEList<?>)getNavigation()).basicRemove(otherEnd, msgs);
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
			case AppPackage.HEADER__TITLE:
				return getTitle();
			case AppPackage.HEADER__NAVIGATION:
				return getNavigation();
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
			case AppPackage.HEADER__TITLE:
				setTitle((Label)newValue);
				return;
			case AppPackage.HEADER__NAVIGATION:
				getNavigation().clear();
				getNavigation().addAll((Collection<? extends BootstrapElement>)newValue);
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
			case AppPackage.HEADER__TITLE:
				setTitle((Label)null);
				return;
			case AppPackage.HEADER__NAVIGATION:
				getNavigation().clear();
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
			case AppPackage.HEADER__TITLE:
				return getTitle() != null;
			case AppPackage.HEADER__NAVIGATION:
				return !getNavigation().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //HeaderImpl
