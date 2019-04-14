/**
 */
package org.nasdanika.html.model.html.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.html.model.html.Property;
import org.nasdanika.html.model.html.ResourceReference;
import org.nasdanika.html.model.html.ScriptReference;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Script Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.impl.ScriptReferenceImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.ScriptReferenceImpl#getRequestProperties <em>Request Properties</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScriptReferenceImpl extends EventHandlerImpl implements ScriptReference {
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
	public String getLocation() {
		return (String)eGet(HtmlPackage.Literals.RESOURCE_REFERENCE__LOCATION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLocation(String newLocation) {
		eSet(HtmlPackage.Literals.RESOURCE_REFERENCE__LOCATION, newLocation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Property> getRequestProperties() {
		return (EList<Property>)eGet(HtmlPackage.Literals.RESOURCE_REFERENCE__REQUEST_PROPERTIES, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ResourceReference.class) {
			switch (derivedFeatureID) {
				case HtmlPackage.SCRIPT_REFERENCE__LOCATION: return HtmlPackage.RESOURCE_REFERENCE__LOCATION;
				case HtmlPackage.SCRIPT_REFERENCE__REQUEST_PROPERTIES: return HtmlPackage.RESOURCE_REFERENCE__REQUEST_PROPERTIES;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ResourceReference.class) {
			switch (baseFeatureID) {
				case HtmlPackage.RESOURCE_REFERENCE__LOCATION: return HtmlPackage.SCRIPT_REFERENCE__LOCATION;
				case HtmlPackage.RESOURCE_REFERENCE__REQUEST_PROPERTIES: return HtmlPackage.SCRIPT_REFERENCE__REQUEST_PROPERTIES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ScriptReferenceImpl
