/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.internal.cdo.CDOObjectImpl;

import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.NavigationActionActivator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Navigation Action Activator</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.NavigationActionActivatorImpl#getUrl <em>Url</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NavigationActionActivatorImpl extends CDOObjectImpl implements NavigationActionActivator {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NavigationActionActivatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.NAVIGATION_ACTION_ACTIVATOR;
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
	@Override
	public String getUrl() {
		return (String)eGet(AppPackage.Literals.NAVIGATION_ACTION_ACTIVATOR__URL, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setUrl(String newUrl) {
		eSet(AppPackage.Literals.NAVIGATION_ACTION_ACTIVATOR__URL, newUrl);
	}

} //NavigationActionActivatorImpl
