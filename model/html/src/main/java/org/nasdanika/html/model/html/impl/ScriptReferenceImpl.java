/**
 */
package org.nasdanika.html.model.html.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.exec.impl.ModelElementImpl;

import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.html.model.html.ScriptReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Script Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.impl.ScriptReferenceImpl#getSrc <em>Src</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScriptReferenceImpl extends ModelElementImpl implements ScriptReference {
	/**
	 * The default value of the '{@link #getSrc() <em>Src</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSrc()
	 * @generated
	 * @ordered
	 */
	protected static final String SRC_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScriptReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HtmlPackage.Literals.SCRIPT_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSrc() {
		return (String)eDynamicGet(HtmlPackage.SCRIPT_REFERENCE__SRC, HtmlPackage.Literals.SCRIPT_REFERENCE__SRC, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSrc(String newSrc) {
		eDynamicSet(HtmlPackage.SCRIPT_REFERENCE__SRC, HtmlPackage.Literals.SCRIPT_REFERENCE__SRC, newSrc);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HtmlPackage.SCRIPT_REFERENCE__SRC:
				return getSrc();
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
			case HtmlPackage.SCRIPT_REFERENCE__SRC:
				setSrc((String)newValue);
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
			case HtmlPackage.SCRIPT_REFERENCE__SRC:
				setSrc(SRC_EDEFAULT);
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
			case HtmlPackage.SCRIPT_REFERENCE__SRC:
				return SRC_EDEFAULT == null ? getSrc() != null : !SRC_EDEFAULT.equals(getSrc());
		}
		return super.eIsSet(featureID);
	}

} //ScriptReferenceImpl
