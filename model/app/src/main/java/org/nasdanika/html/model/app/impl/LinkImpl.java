/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Link;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.LinkImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LinkImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LinkImpl#getBinding <em>Binding</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LinkImpl extends LabelImpl implements Link {
	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getScript() <em>Script</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScript()
	 * @generated
	 * @ordered
	 */
	protected static final String SCRIPT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getBinding() <em>Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBinding()
	 * @generated
	 * @ordered
	 */
	protected static final String BINDING_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLocation() {
		return (String)eDynamicGet(AppPackage.LINK__LOCATION, AppPackage.Literals.LINK__LOCATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLocation(String newLocation) {
		eDynamicSet(AppPackage.LINK__LOCATION, AppPackage.Literals.LINK__LOCATION, newLocation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getScript() {
		return (String)eDynamicGet(AppPackage.LINK__SCRIPT, AppPackage.Literals.LINK__SCRIPT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setScript(String newScript) {
		eDynamicSet(AppPackage.LINK__SCRIPT, AppPackage.Literals.LINK__SCRIPT, newScript);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBinding() {
		return (String)eDynamicGet(AppPackage.LINK__BINDING, AppPackage.Literals.LINK__BINDING, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBinding(String newBinding) {
		eDynamicSet(AppPackage.LINK__BINDING, AppPackage.Literals.LINK__BINDING, newBinding);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AppPackage.LINK__LOCATION:
				return getLocation();
			case AppPackage.LINK__SCRIPT:
				return getScript();
			case AppPackage.LINK__BINDING:
				return getBinding();
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
			case AppPackage.LINK__LOCATION:
				setLocation((String)newValue);
				return;
			case AppPackage.LINK__SCRIPT:
				setScript((String)newValue);
				return;
			case AppPackage.LINK__BINDING:
				setBinding((String)newValue);
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
			case AppPackage.LINK__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case AppPackage.LINK__SCRIPT:
				setScript(SCRIPT_EDEFAULT);
				return;
			case AppPackage.LINK__BINDING:
				setBinding(BINDING_EDEFAULT);
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
			case AppPackage.LINK__LOCATION:
				return LOCATION_EDEFAULT == null ? getLocation() != null : !LOCATION_EDEFAULT.equals(getLocation());
			case AppPackage.LINK__SCRIPT:
				return SCRIPT_EDEFAULT == null ? getScript() != null : !SCRIPT_EDEFAULT.equals(getScript());
			case AppPackage.LINK__BINDING:
				return BINDING_EDEFAULT == null ? getBinding() != null : !BINDING_EDEFAULT.equals(getBinding());
		}
		return super.eIsSet(featureID);
	}

} //LinkImpl
