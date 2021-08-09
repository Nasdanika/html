/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.html.model.bootstrap.ActionGroup;
import org.nasdanika.html.model.bootstrap.ActionGroupItem;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ActionGroupImpl#isFlush <em>Flush</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ActionGroupImpl#getItems <em>Items</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActionGroupImpl extends DivImpl implements ActionGroup {
	/**
	 * The default value of the '{@link #isFlush() <em>Flush</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFlush()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FLUSH_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.ACTION_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFlush() {
		return (Boolean)eDynamicGet(BootstrapPackage.ACTION_GROUP__FLUSH, BootstrapPackage.Literals.ACTION_GROUP__FLUSH, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFlush(boolean newFlush) {
		eDynamicSet(BootstrapPackage.ACTION_GROUP__FLUSH, BootstrapPackage.Literals.ACTION_GROUP__FLUSH, newFlush);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<ActionGroupItem> getItems() {
		return (EList<ActionGroupItem>)eDynamicGet(BootstrapPackage.ACTION_GROUP__ITEMS, BootstrapPackage.Literals.ACTION_GROUP__ITEMS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BootstrapPackage.ACTION_GROUP__ITEMS:
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
			case BootstrapPackage.ACTION_GROUP__FLUSH:
				return isFlush();
			case BootstrapPackage.ACTION_GROUP__ITEMS:
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
			case BootstrapPackage.ACTION_GROUP__FLUSH:
				setFlush((Boolean)newValue);
				return;
			case BootstrapPackage.ACTION_GROUP__ITEMS:
				getItems().clear();
				getItems().addAll((Collection<? extends ActionGroupItem>)newValue);
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
			case BootstrapPackage.ACTION_GROUP__FLUSH:
				setFlush(FLUSH_EDEFAULT);
				return;
			case BootstrapPackage.ACTION_GROUP__ITEMS:
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
			case BootstrapPackage.ACTION_GROUP__FLUSH:
				return isFlush() != FLUSH_EDEFAULT;
			case BootstrapPackage.ACTION_GROUP__ITEMS:
				return !getItems().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ActionGroupImpl
