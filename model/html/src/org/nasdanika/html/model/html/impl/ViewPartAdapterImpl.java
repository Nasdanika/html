/**
 */
package org.nasdanika.html.model.html.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.html.model.html.ViewPartAdapter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>View Part Adapter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.impl.ViewPartAdapterImpl#getFactory <em>Factory</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ViewPartAdapterImpl extends HtmlElementImpl implements ViewPartAdapter {
	/**
	 * The default value of the '{@link #getFactory() <em>Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFactory()
	 * @generated
	 * @ordered
	 */
	protected static final String FACTORY_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ViewPartAdapterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HtmlPackage.Literals.VIEW_PART_ADAPTER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFactory() {
		return (String)eDynamicGet(HtmlPackage.VIEW_PART_ADAPTER__FACTORY, HtmlPackage.Literals.VIEW_PART_ADAPTER__FACTORY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFactory(String newFactory) {
		eDynamicSet(HtmlPackage.VIEW_PART_ADAPTER__FACTORY, HtmlPackage.Literals.VIEW_PART_ADAPTER__FACTORY, newFactory);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HtmlPackage.VIEW_PART_ADAPTER__FACTORY:
				return getFactory();
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
			case HtmlPackage.VIEW_PART_ADAPTER__FACTORY:
				setFactory((String)newValue);
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
			case HtmlPackage.VIEW_PART_ADAPTER__FACTORY:
				setFactory(FACTORY_EDEFAULT);
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
			case HtmlPackage.VIEW_PART_ADAPTER__FACTORY:
				return FACTORY_EDEFAULT == null ? getFactory() != null : !FACTORY_EDEFAULT.equals(getFactory());
		}
		return super.eIsSet(featureID);
	}

} //ViewPartAdapterImpl
