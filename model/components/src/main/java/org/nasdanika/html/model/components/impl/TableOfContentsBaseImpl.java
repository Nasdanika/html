/**
 */
package org.nasdanika.html.model.components.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.bootstrap.impl.BootstrapElementImpl;

import org.nasdanika.html.model.components.ComponentsPackage;
import org.nasdanika.html.model.components.TableOfContentsBase;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Table Of Contents Base</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.components.impl.TableOfContentsBaseImpl#getHeader <em>Header</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.impl.TableOfContentsBaseImpl#getRole <em>Role</em>}</li>
 *   <li>{@link org.nasdanika.html.model.components.impl.TableOfContentsBaseImpl#getDepth <em>Depth</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class TableOfContentsBaseImpl extends BootstrapElementImpl implements TableOfContentsBase {
	/**
	 * The default value of the '{@link #getHeader() <em>Header</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeader()
	 * @generated
	 * @ordered
	 */
	protected static final String HEADER_EDEFAULT = "";

	/**
	 * The default value of the '{@link #getRole() <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected static final String ROLE_EDEFAULT = "";

	/**
	 * The default value of the '{@link #getDepth() <em>Depth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDepth()
	 * @generated
	 * @ordered
	 */
	protected static final int DEPTH_EDEFAULT = 3;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TableOfContentsBaseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ComponentsPackage.Literals.TABLE_OF_CONTENTS_BASE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getHeader() {
		return (String)eDynamicGet(ComponentsPackage.TABLE_OF_CONTENTS_BASE__HEADER, ComponentsPackage.Literals.TABLE_OF_CONTENTS_BASE__HEADER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHeader(String newHeader) {
		eDynamicSet(ComponentsPackage.TABLE_OF_CONTENTS_BASE__HEADER, ComponentsPackage.Literals.TABLE_OF_CONTENTS_BASE__HEADER, newHeader);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRole() {
		return (String)eDynamicGet(ComponentsPackage.TABLE_OF_CONTENTS_BASE__ROLE, ComponentsPackage.Literals.TABLE_OF_CONTENTS_BASE__ROLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRole(String newRole) {
		eDynamicSet(ComponentsPackage.TABLE_OF_CONTENTS_BASE__ROLE, ComponentsPackage.Literals.TABLE_OF_CONTENTS_BASE__ROLE, newRole);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getDepth() {
		return (Integer)eDynamicGet(ComponentsPackage.TABLE_OF_CONTENTS_BASE__DEPTH, ComponentsPackage.Literals.TABLE_OF_CONTENTS_BASE__DEPTH, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDepth(int newDepth) {
		eDynamicSet(ComponentsPackage.TABLE_OF_CONTENTS_BASE__DEPTH, ComponentsPackage.Literals.TABLE_OF_CONTENTS_BASE__DEPTH, newDepth);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ComponentsPackage.TABLE_OF_CONTENTS_BASE__HEADER:
				return getHeader();
			case ComponentsPackage.TABLE_OF_CONTENTS_BASE__ROLE:
				return getRole();
			case ComponentsPackage.TABLE_OF_CONTENTS_BASE__DEPTH:
				return getDepth();
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
			case ComponentsPackage.TABLE_OF_CONTENTS_BASE__HEADER:
				setHeader((String)newValue);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS_BASE__ROLE:
				setRole((String)newValue);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS_BASE__DEPTH:
				setDepth((Integer)newValue);
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
			case ComponentsPackage.TABLE_OF_CONTENTS_BASE__HEADER:
				setHeader(HEADER_EDEFAULT);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS_BASE__ROLE:
				setRole(ROLE_EDEFAULT);
				return;
			case ComponentsPackage.TABLE_OF_CONTENTS_BASE__DEPTH:
				setDepth(DEPTH_EDEFAULT);
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
			case ComponentsPackage.TABLE_OF_CONTENTS_BASE__HEADER:
				return HEADER_EDEFAULT == null ? getHeader() != null : !HEADER_EDEFAULT.equals(getHeader());
			case ComponentsPackage.TABLE_OF_CONTENTS_BASE__ROLE:
				return ROLE_EDEFAULT == null ? getRole() != null : !ROLE_EDEFAULT.equals(getRole());
			case ComponentsPackage.TABLE_OF_CONTENTS_BASE__DEPTH:
				return getDepth() != DEPTH_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //TableOfContentsBaseImpl
