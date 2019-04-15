/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.bootstrap.Color;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.ListGroupItem;

import org.nasdanika.html.model.html.Content;

import org.nasdanika.html.model.html.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>List Group Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ListGroupItemImpl#isDisabled <em>Disabled</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ListGroupItemImpl#isActive <em>Active</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ListGroupItemImpl#getColor <em>Color</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ListGroupItemImpl#getContent <em>Content</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ListGroupItemImpl extends ModelElementImpl implements ListGroupItem {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ListGroupItemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.LIST_GROUP_ITEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDisabled() {
		return (Boolean)eGet(BootstrapPackage.Literals.LIST_GROUP_ITEM__DISABLED, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDisabled(boolean newDisabled) {
		eSet(BootstrapPackage.Literals.LIST_GROUP_ITEM__DISABLED, newDisabled);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isActive() {
		return (Boolean)eGet(BootstrapPackage.Literals.LIST_GROUP_ITEM__ACTIVE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setActive(boolean newActive) {
		eSet(BootstrapPackage.Literals.LIST_GROUP_ITEM__ACTIVE, newActive);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Color getColor() {
		return (Color)eGet(BootstrapPackage.Literals.LIST_GROUP_ITEM__COLOR, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setColor(Color newColor) {
		eSet(BootstrapPackage.Literals.LIST_GROUP_ITEM__COLOR, newColor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Content> getContent() {
		return (EList<Content>)eGet(BootstrapPackage.Literals.LIST_GROUP_ITEM__CONTENT, true);
	}

} //ListGroupItemImpl
