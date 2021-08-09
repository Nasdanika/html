/**
 */
package org.nasdanika.html.model.components.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.components.ComponentsPackage;
import org.nasdanika.html.model.components.ListOfContents;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>List Of Contents</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.components.impl.ListOfContentsImpl#getOrdering <em>Ordering</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.impl.ListOfContentsImpl#isTooltips <em>Tooltips</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ListOfContentsImpl extends TableOfContentsBaseImpl implements ListOfContents {
	/**
	 * The default value of the '{@link #getOrdering() <em>Ordering</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrdering()
	 * @generated
	 * @ordered
	 */
	protected static final String ORDERING_EDEFAULT = "";

	/**
	 * The default value of the '{@link #isTooltips() <em>Tooltips</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTooltips()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TOOLTIPS_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ListOfContentsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentsPackage.Literals.LIST_OF_CONTENTS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getOrdering() {
		return (String)eDynamicGet(ComponentsPackage.LIST_OF_CONTENTS__ORDERING, ComponentsPackage.Literals.LIST_OF_CONTENTS__ORDERING, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOrdering(String newOrdering) {
		eDynamicSet(ComponentsPackage.LIST_OF_CONTENTS__ORDERING, ComponentsPackage.Literals.LIST_OF_CONTENTS__ORDERING, newOrdering);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isTooltips() {
		return (Boolean)eDynamicGet(ComponentsPackage.LIST_OF_CONTENTS__TOOLTIPS, ComponentsPackage.Literals.LIST_OF_CONTENTS__TOOLTIPS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTooltips(boolean newTooltips) {
		eDynamicSet(ComponentsPackage.LIST_OF_CONTENTS__TOOLTIPS, ComponentsPackage.Literals.LIST_OF_CONTENTS__TOOLTIPS, newTooltips);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentsPackage.LIST_OF_CONTENTS__ORDERING:
				return getOrdering();
			case ComponentsPackage.LIST_OF_CONTENTS__TOOLTIPS:
				return isTooltips();
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
			case ComponentsPackage.LIST_OF_CONTENTS__ORDERING:
				setOrdering((String)newValue);
				return;
			case ComponentsPackage.LIST_OF_CONTENTS__TOOLTIPS:
				setTooltips((Boolean)newValue);
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
			case ComponentsPackage.LIST_OF_CONTENTS__ORDERING:
				setOrdering(ORDERING_EDEFAULT);
				return;
			case ComponentsPackage.LIST_OF_CONTENTS__TOOLTIPS:
				setTooltips(TOOLTIPS_EDEFAULT);
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
			case ComponentsPackage.LIST_OF_CONTENTS__ORDERING:
				return ORDERING_EDEFAULT == null ? getOrdering() != null : !ORDERING_EDEFAULT.equals(getOrdering());
			case ComponentsPackage.LIST_OF_CONTENTS__TOOLTIPS:
				return isTooltips() != TOOLTIPS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //ListOfContentsImpl
