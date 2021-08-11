/**
 */
package org.nasdanika.html.model.app.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.ContentPanel;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.NavigationPanel;
import org.nasdanika.html.model.app.SectionStyle;
import org.nasdanika.html.model.bootstrap.BootstrapElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Content Panel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.ContentPanelImpl#getBreadcrumb <em>Breadcrumb</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ContentPanelImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ContentPanelImpl#getNavigation <em>Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ContentPanelImpl#getLeftNavigation <em>Left Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ContentPanelImpl#getRightNavigation <em>Right Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ContentPanelImpl#getFloatLeftNavigation <em>Float Left Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ContentPanelImpl#getFloatRightNavigation <em>Float Right Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ContentPanelImpl#getSections <em>Sections</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ContentPanelImpl#getSectionColumns <em>Section Columns</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ContentPanelImpl#getSectionStyle <em>Section Style</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ContentPanelImpl extends PagePartImpl implements ContentPanel {
	/**
	 * The default value of the '{@link #getSectionColumns() <em>Section Columns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSectionColumns()
	 * @generated
	 * @ordered
	 */
	protected static final int SECTION_COLUMNS_EDEFAULT = 3;

	/**
	 * The default value of the '{@link #getSectionStyle() <em>Section Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSectionStyle()
	 * @generated
	 * @ordered
	 */
	protected static final SectionStyle SECTION_STYLE_EDEFAULT = SectionStyle.AUTO;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContentPanelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.CONTENT_PANEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Label getTitle() {
		return (Label)eDynamicGet(AppPackage.CONTENT_PANEL__TITLE, AppPackage.Literals.CONTENT_PANEL__TITLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTitle(Label newTitle, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newTitle, AppPackage.CONTENT_PANEL__TITLE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTitle(Label newTitle) {
		eDynamicSet(AppPackage.CONTENT_PANEL__TITLE, AppPackage.Literals.CONTENT_PANEL__TITLE, newTitle);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<BootstrapElement> getNavigation() {
		return (EList<BootstrapElement>)eDynamicGet(AppPackage.CONTENT_PANEL__NAVIGATION, AppPackage.Literals.CONTENT_PANEL__NAVIGATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Label> getBreadcrumb() {
		return (EList<Label>)eDynamicGet(AppPackage.CONTENT_PANEL__BREADCRUMB, AppPackage.Literals.CONTENT_PANEL__BREADCRUMB, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NavigationPanel getLeftNavigation() {
		return (NavigationPanel)eDynamicGet(AppPackage.CONTENT_PANEL__LEFT_NAVIGATION, AppPackage.Literals.CONTENT_PANEL__LEFT_NAVIGATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftNavigation(NavigationPanel newLeftNavigation, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newLeftNavigation, AppPackage.CONTENT_PANEL__LEFT_NAVIGATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLeftNavigation(NavigationPanel newLeftNavigation) {
		eDynamicSet(AppPackage.CONTENT_PANEL__LEFT_NAVIGATION, AppPackage.Literals.CONTENT_PANEL__LEFT_NAVIGATION, newLeftNavigation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NavigationPanel getRightNavigation() {
		return (NavigationPanel)eDynamicGet(AppPackage.CONTENT_PANEL__RIGHT_NAVIGATION, AppPackage.Literals.CONTENT_PANEL__RIGHT_NAVIGATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightNavigation(NavigationPanel newRightNavigation, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newRightNavigation, AppPackage.CONTENT_PANEL__RIGHT_NAVIGATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRightNavigation(NavigationPanel newRightNavigation) {
		eDynamicSet(AppPackage.CONTENT_PANEL__RIGHT_NAVIGATION, AppPackage.Literals.CONTENT_PANEL__RIGHT_NAVIGATION, newRightNavigation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NavigationPanel getFloatLeftNavigation() {
		return (NavigationPanel)eDynamicGet(AppPackage.CONTENT_PANEL__FLOAT_LEFT_NAVIGATION, AppPackage.Literals.CONTENT_PANEL__FLOAT_LEFT_NAVIGATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFloatLeftNavigation(NavigationPanel newFloatLeftNavigation, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newFloatLeftNavigation, AppPackage.CONTENT_PANEL__FLOAT_LEFT_NAVIGATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFloatLeftNavigation(NavigationPanel newFloatLeftNavigation) {
		eDynamicSet(AppPackage.CONTENT_PANEL__FLOAT_LEFT_NAVIGATION, AppPackage.Literals.CONTENT_PANEL__FLOAT_LEFT_NAVIGATION, newFloatLeftNavigation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NavigationPanel getFloatRightNavigation() {
		return (NavigationPanel)eDynamicGet(AppPackage.CONTENT_PANEL__FLOAT_RIGHT_NAVIGATION, AppPackage.Literals.CONTENT_PANEL__FLOAT_RIGHT_NAVIGATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFloatRightNavigation(NavigationPanel newFloatRightNavigation, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newFloatRightNavigation, AppPackage.CONTENT_PANEL__FLOAT_RIGHT_NAVIGATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFloatRightNavigation(NavigationPanel newFloatRightNavigation) {
		eDynamicSet(AppPackage.CONTENT_PANEL__FLOAT_RIGHT_NAVIGATION, AppPackage.Literals.CONTENT_PANEL__FLOAT_RIGHT_NAVIGATION, newFloatRightNavigation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<ContentPanel> getSections() {
		return (EList<ContentPanel>)eDynamicGet(AppPackage.CONTENT_PANEL__SECTIONS, AppPackage.Literals.CONTENT_PANEL__SECTIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getSectionColumns() {
		return (Integer)eDynamicGet(AppPackage.CONTENT_PANEL__SECTION_COLUMNS, AppPackage.Literals.CONTENT_PANEL__SECTION_COLUMNS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSectionColumns(int newSectionColumns) {
		eDynamicSet(AppPackage.CONTENT_PANEL__SECTION_COLUMNS, AppPackage.Literals.CONTENT_PANEL__SECTION_COLUMNS, newSectionColumns);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SectionStyle getSectionStyle() {
		return (SectionStyle)eDynamicGet(AppPackage.CONTENT_PANEL__SECTION_STYLE, AppPackage.Literals.CONTENT_PANEL__SECTION_STYLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSectionStyle(SectionStyle newSectionStyle) {
		eDynamicSet(AppPackage.CONTENT_PANEL__SECTION_STYLE, AppPackage.Literals.CONTENT_PANEL__SECTION_STYLE, newSectionStyle);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AppPackage.CONTENT_PANEL__BREADCRUMB:
				return ((InternalEList<?>)getBreadcrumb()).basicRemove(otherEnd, msgs);
			case AppPackage.CONTENT_PANEL__TITLE:
				return basicSetTitle(null, msgs);
			case AppPackage.CONTENT_PANEL__NAVIGATION:
				return ((InternalEList<?>)getNavigation()).basicRemove(otherEnd, msgs);
			case AppPackage.CONTENT_PANEL__LEFT_NAVIGATION:
				return basicSetLeftNavigation(null, msgs);
			case AppPackage.CONTENT_PANEL__RIGHT_NAVIGATION:
				return basicSetRightNavigation(null, msgs);
			case AppPackage.CONTENT_PANEL__FLOAT_LEFT_NAVIGATION:
				return basicSetFloatLeftNavigation(null, msgs);
			case AppPackage.CONTENT_PANEL__FLOAT_RIGHT_NAVIGATION:
				return basicSetFloatRightNavigation(null, msgs);
			case AppPackage.CONTENT_PANEL__SECTIONS:
				return ((InternalEList<?>)getSections()).basicRemove(otherEnd, msgs);
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
			case AppPackage.CONTENT_PANEL__BREADCRUMB:
				return getBreadcrumb();
			case AppPackage.CONTENT_PANEL__TITLE:
				return getTitle();
			case AppPackage.CONTENT_PANEL__NAVIGATION:
				return getNavigation();
			case AppPackage.CONTENT_PANEL__LEFT_NAVIGATION:
				return getLeftNavigation();
			case AppPackage.CONTENT_PANEL__RIGHT_NAVIGATION:
				return getRightNavigation();
			case AppPackage.CONTENT_PANEL__FLOAT_LEFT_NAVIGATION:
				return getFloatLeftNavigation();
			case AppPackage.CONTENT_PANEL__FLOAT_RIGHT_NAVIGATION:
				return getFloatRightNavigation();
			case AppPackage.CONTENT_PANEL__SECTIONS:
				return getSections();
			case AppPackage.CONTENT_PANEL__SECTION_COLUMNS:
				return getSectionColumns();
			case AppPackage.CONTENT_PANEL__SECTION_STYLE:
				return getSectionStyle();
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
			case AppPackage.CONTENT_PANEL__BREADCRUMB:
				getBreadcrumb().clear();
				getBreadcrumb().addAll((Collection<? extends Label>)newValue);
				return;
			case AppPackage.CONTENT_PANEL__TITLE:
				setTitle((Label)newValue);
				return;
			case AppPackage.CONTENT_PANEL__NAVIGATION:
				getNavigation().clear();
				getNavigation().addAll((Collection<? extends BootstrapElement>)newValue);
				return;
			case AppPackage.CONTENT_PANEL__LEFT_NAVIGATION:
				setLeftNavigation((NavigationPanel)newValue);
				return;
			case AppPackage.CONTENT_PANEL__RIGHT_NAVIGATION:
				setRightNavigation((NavigationPanel)newValue);
				return;
			case AppPackage.CONTENT_PANEL__FLOAT_LEFT_NAVIGATION:
				setFloatLeftNavigation((NavigationPanel)newValue);
				return;
			case AppPackage.CONTENT_PANEL__FLOAT_RIGHT_NAVIGATION:
				setFloatRightNavigation((NavigationPanel)newValue);
				return;
			case AppPackage.CONTENT_PANEL__SECTIONS:
				getSections().clear();
				getSections().addAll((Collection<? extends ContentPanel>)newValue);
				return;
			case AppPackage.CONTENT_PANEL__SECTION_COLUMNS:
				setSectionColumns((Integer)newValue);
				return;
			case AppPackage.CONTENT_PANEL__SECTION_STYLE:
				setSectionStyle((SectionStyle)newValue);
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
			case AppPackage.CONTENT_PANEL__BREADCRUMB:
				getBreadcrumb().clear();
				return;
			case AppPackage.CONTENT_PANEL__TITLE:
				setTitle((Label)null);
				return;
			case AppPackage.CONTENT_PANEL__NAVIGATION:
				getNavigation().clear();
				return;
			case AppPackage.CONTENT_PANEL__LEFT_NAVIGATION:
				setLeftNavigation((NavigationPanel)null);
				return;
			case AppPackage.CONTENT_PANEL__RIGHT_NAVIGATION:
				setRightNavigation((NavigationPanel)null);
				return;
			case AppPackage.CONTENT_PANEL__FLOAT_LEFT_NAVIGATION:
				setFloatLeftNavigation((NavigationPanel)null);
				return;
			case AppPackage.CONTENT_PANEL__FLOAT_RIGHT_NAVIGATION:
				setFloatRightNavigation((NavigationPanel)null);
				return;
			case AppPackage.CONTENT_PANEL__SECTIONS:
				getSections().clear();
				return;
			case AppPackage.CONTENT_PANEL__SECTION_COLUMNS:
				setSectionColumns(SECTION_COLUMNS_EDEFAULT);
				return;
			case AppPackage.CONTENT_PANEL__SECTION_STYLE:
				setSectionStyle(SECTION_STYLE_EDEFAULT);
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
			case AppPackage.CONTENT_PANEL__BREADCRUMB:
				return !getBreadcrumb().isEmpty();
			case AppPackage.CONTENT_PANEL__TITLE:
				return getTitle() != null;
			case AppPackage.CONTENT_PANEL__NAVIGATION:
				return !getNavigation().isEmpty();
			case AppPackage.CONTENT_PANEL__LEFT_NAVIGATION:
				return getLeftNavigation() != null;
			case AppPackage.CONTENT_PANEL__RIGHT_NAVIGATION:
				return getRightNavigation() != null;
			case AppPackage.CONTENT_PANEL__FLOAT_LEFT_NAVIGATION:
				return getFloatLeftNavigation() != null;
			case AppPackage.CONTENT_PANEL__FLOAT_RIGHT_NAVIGATION:
				return getFloatRightNavigation() != null;
			case AppPackage.CONTENT_PANEL__SECTIONS:
				return !getSections().isEmpty();
			case AppPackage.CONTENT_PANEL__SECTION_COLUMNS:
				return getSectionColumns() != SECTION_COLUMNS_EDEFAULT;
			case AppPackage.CONTENT_PANEL__SECTION_STYLE:
				return getSectionStyle() != SECTION_STYLE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //ContentPanelImpl
