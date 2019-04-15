/**
 */
package org.nasdanika.html.model.bootstrap.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.nasdanika.html.model.bootstrap.BootstrapFactory;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.ListGroupItem;

import org.nasdanika.html.model.html.HtmlFactory;

import org.nasdanika.html.model.html.provider.ModelElementItemProvider;

/**
 * This is the item provider adapter for a {@link org.nasdanika.html.model.bootstrap.ListGroupItem} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ListGroupItemItemProvider extends ModelElementItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ListGroupItemItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addDisabledPropertyDescriptor(object);
			addActivePropertyDescriptor(object);
			addColorPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Disabled feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDisabledPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ListGroupItem_disabled_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ListGroupItem_disabled_feature", "_UI_ListGroupItem_type"),
				 BootstrapPackage.Literals.LIST_GROUP_ITEM__DISABLED,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Active feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActivePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ListGroupItem_active_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ListGroupItem_active_feature", "_UI_ListGroupItem_type"),
				 BootstrapPackage.Literals.LIST_GROUP_ITEM__ACTIVE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Color feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addColorPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ListGroupItem_color_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ListGroupItem_color_feature", "_UI_ListGroupItem_type"),
				 BootstrapPackage.Literals.LIST_GROUP_ITEM__COLOR,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(BootstrapPackage.Literals.LIST_GROUP_ITEM__CONTENT);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns ListGroupItem.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ListGroupItem"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = crop(((ListGroupItem)object).getDescription());
		return label == null || label.length() == 0 ?
			getString("_UI_ListGroupItem_type") :
			getString("_UI_ListGroupItem_type") + " " + label;
	}


	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ListGroupItem.class)) {
			case BootstrapPackage.LIST_GROUP_ITEM__DISABLED:
			case BootstrapPackage.LIST_GROUP_ITEM__ACTIVE:
			case BootstrapPackage.LIST_GROUP_ITEM__COLOR:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case BootstrapPackage.LIST_GROUP_ITEM__CONTENT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(BootstrapPackage.Literals.LIST_GROUP_ITEM__CONTENT,
				 BootstrapFactory.eINSTANCE.createWrap()));

		newChildDescriptors.add
			(createChildParameter
				(BootstrapPackage.Literals.LIST_GROUP_ITEM__CONTENT,
				 BootstrapFactory.eINSTANCE.createAlert()));

		newChildDescriptors.add
			(createChildParameter
				(BootstrapPackage.Literals.LIST_GROUP_ITEM__CONTENT,
				 BootstrapFactory.eINSTANCE.createListGroup()));

		newChildDescriptors.add
			(createChildParameter
				(BootstrapPackage.Literals.LIST_GROUP_ITEM__CONTENT,
				 BootstrapFactory.eINSTANCE.createBadge()));

		newChildDescriptors.add
			(createChildParameter
				(BootstrapPackage.Literals.LIST_GROUP_ITEM__CONTENT,
				 HtmlFactory.eINSTANCE.createContentReference()));

		newChildDescriptors.add
			(createChildParameter
				(BootstrapPackage.Literals.LIST_GROUP_ITEM__CONTENT,
				 HtmlFactory.eINSTANCE.createResourceContent()));

		newChildDescriptors.add
			(createChildParameter
				(BootstrapPackage.Literals.LIST_GROUP_ITEM__CONTENT,
				 HtmlFactory.eINSTANCE.createText()));

		newChildDescriptors.add
			(createChildParameter
				(BootstrapPackage.Literals.LIST_GROUP_ITEM__CONTENT,
				 HtmlFactory.eINSTANCE.createContentGenerator()));

		newChildDescriptors.add
			(createChildParameter
				(BootstrapPackage.Literals.LIST_GROUP_ITEM__CONTENT,
				 HtmlFactory.eINSTANCE.createContainer()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return BootstrapEditPlugin.INSTANCE;
	}

}
