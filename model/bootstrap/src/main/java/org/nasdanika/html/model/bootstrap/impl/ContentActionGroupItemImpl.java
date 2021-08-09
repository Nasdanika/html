/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.ContentActionGroupItem;

import org.nasdanika.html.model.html.HtmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Content Action Group Item</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ContentActionGroupItemImpl#getContent <em>Content</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ContentActionGroupItemImpl#getMarkdownContent <em>Markdown Content</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContentActionGroupItemImpl extends ActionGroupItemImpl implements ContentActionGroupItem {
	/**
	 * The default value of the '{@link #getMarkdownContent() <em>Markdown Content</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMarkdownContent()
	 * @generated
	 * @ordered
	 */
	protected static final String MARKDOWN_CONTENT_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContentActionGroupItemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.CONTENT_ACTION_GROUP_ITEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getContent() {
		return (EList<EObject>)eDynamicGet(BootstrapPackage.CONTENT_ACTION_GROUP_ITEM__CONTENT, HtmlPackage.Literals.CONTAINER__CONTENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMarkdownContent() {
		return (String)eDynamicGet(BootstrapPackage.CONTENT_ACTION_GROUP_ITEM__MARKDOWN_CONTENT, HtmlPackage.Literals.CONTAINER__MARKDOWN_CONTENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMarkdownContent(String newMarkdownContent) {
		eDynamicSet(BootstrapPackage.CONTENT_ACTION_GROUP_ITEM__MARKDOWN_CONTENT, HtmlPackage.Literals.CONTAINER__MARKDOWN_CONTENT, newMarkdownContent);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BootstrapPackage.CONTENT_ACTION_GROUP_ITEM__CONTENT:
				return ((InternalEList<?>)getContent()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BootstrapPackage.CONTENT_ACTION_GROUP_ITEM__CONTENT:
				return getContent();
			case BootstrapPackage.CONTENT_ACTION_GROUP_ITEM__MARKDOWN_CONTENT:
				return getMarkdownContent();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BootstrapPackage.CONTENT_ACTION_GROUP_ITEM__CONTENT:
				getContent().clear();
				getContent().addAll((Collection<? extends EObject>)newValue);
				return;
			case BootstrapPackage.CONTENT_ACTION_GROUP_ITEM__MARKDOWN_CONTENT:
				setMarkdownContent((String)newValue);
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
			case BootstrapPackage.CONTENT_ACTION_GROUP_ITEM__CONTENT:
				getContent().clear();
				return;
			case BootstrapPackage.CONTENT_ACTION_GROUP_ITEM__MARKDOWN_CONTENT:
				setMarkdownContent(MARKDOWN_CONTENT_EDEFAULT);
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
			case BootstrapPackage.CONTENT_ACTION_GROUP_ITEM__CONTENT:
				return !getContent().isEmpty();
			case BootstrapPackage.CONTENT_ACTION_GROUP_ITEM__MARKDOWN_CONTENT:
				return MARKDOWN_CONTENT_EDEFAULT == null ? getMarkdownContent() != null : !MARKDOWN_CONTENT_EDEFAULT.equals(getMarkdownContent());
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == org.nasdanika.html.model.html.Container.class) {
			switch (derivedFeatureID) {
				case BootstrapPackage.CONTENT_ACTION_GROUP_ITEM__CONTENT: return HtmlPackage.CONTAINER__CONTENT;
				case BootstrapPackage.CONTENT_ACTION_GROUP_ITEM__MARKDOWN_CONTENT: return HtmlPackage.CONTAINER__MARKDOWN_CONTENT;
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
		if (baseClass == org.nasdanika.html.model.html.Container.class) {
			switch (baseFeatureID) {
				case HtmlPackage.CONTAINER__CONTENT: return BootstrapPackage.CONTENT_ACTION_GROUP_ITEM__CONTENT;
				case HtmlPackage.CONTAINER__MARKDOWN_CONTENT: return BootstrapPackage.CONTENT_ACTION_GROUP_ITEM__MARKDOWN_CONTENT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ContentActionGroupItemImpl
