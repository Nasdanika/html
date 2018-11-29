/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.ScriptActionActivator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Script Action Activator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.ScriptActionActivatorImpl#getCode <em>Code</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScriptActionActivatorImpl extends CDOObjectImpl implements ScriptActionActivator {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScriptActionActivatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.SCRIPT_ACTION_ACTIVATOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCode() {
		return (String)eGet(AppPackage.Literals.SCRIPT_ACTION_ACTIVATOR__CODE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCode(String newCode) {
		eSet(AppPackage.Literals.SCRIPT_ACTION_ACTIVATOR__CODE, newCode);
	}

} //ScriptActionActivatorImpl
