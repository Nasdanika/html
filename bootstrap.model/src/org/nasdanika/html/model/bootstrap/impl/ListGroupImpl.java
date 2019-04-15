/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.ListGroup;
import org.nasdanika.html.model.bootstrap.ListGroupItem;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>List Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ListGroupImpl#getItems <em>Items</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ListGroupImpl extends BootstrapElementImpl implements ListGroup {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ListGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.LIST_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<ListGroupItem> getItems() {
		return (EList<ListGroupItem>)eGet(BootstrapPackage.Literals.LIST_GROUP__ITEMS, true);
	}

} //ListGroupImpl
