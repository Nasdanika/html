/**
 */
package org.nasdanika.html.model.app.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.exec.resources.Resource;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.NavigationPanel;
import org.nasdanika.html.model.app.SectionStyle;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getSectionColumns <em>Section Columns</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getSectionStyle <em>Section Style</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getSections <em>Sections</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getNavigation <em>Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getLeftNavigation <em>Left Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getRightNavigation <em>Right Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getFloatLeftNavigation <em>Float Left Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getFloatRightNavigation <em>Float Right Navigation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getAnonymous <em>Anonymous</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getResources <em>Resources</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#isInline <em>Inline</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#isModalActivator <em>Modal Activator</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActionImpl extends LinkImpl implements Action {
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
	 * The default value of the '{@link #isInline() <em>Inline</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInline()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INLINE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isModalActivator() <em>Modal Activator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isModalActivator()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MODAL_ACTIVATOR_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getSectionColumns() {
		return (Integer)eDynamicGet(AppPackage.ACTION__SECTION_COLUMNS, AppPackage.Literals.ACTION__SECTION_COLUMNS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSectionColumns(int newSectionColumns) {
		eDynamicSet(AppPackage.ACTION__SECTION_COLUMNS, AppPackage.Literals.ACTION__SECTION_COLUMNS, newSectionColumns);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SectionStyle getSectionStyle() {
		return (SectionStyle)eDynamicGet(AppPackage.ACTION__SECTION_STYLE, AppPackage.Literals.ACTION__SECTION_STYLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSectionStyle(SectionStyle newSectionStyle) {
		eDynamicSet(AppPackage.ACTION__SECTION_STYLE, AppPackage.Literals.ACTION__SECTION_STYLE, newSectionStyle);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Action> getSections() {
		return (EList<Action>)eDynamicGet(AppPackage.ACTION__SECTIONS, AppPackage.Literals.ACTION__SECTIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getNavigation() {
		return (EList<EObject>)eDynamicGet(AppPackage.ACTION__NAVIGATION, AppPackage.Literals.ACTION__NAVIGATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NavigationPanel getLeftNavigation() {
		return (NavigationPanel)eDynamicGet(AppPackage.ACTION__LEFT_NAVIGATION, AppPackage.Literals.ACTION__LEFT_NAVIGATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLeftNavigation(NavigationPanel newLeftNavigation, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newLeftNavigation, AppPackage.ACTION__LEFT_NAVIGATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLeftNavigation(NavigationPanel newLeftNavigation) {
		eDynamicSet(AppPackage.ACTION__LEFT_NAVIGATION, AppPackage.Literals.ACTION__LEFT_NAVIGATION, newLeftNavigation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NavigationPanel getRightNavigation() {
		return (NavigationPanel)eDynamicGet(AppPackage.ACTION__RIGHT_NAVIGATION, AppPackage.Literals.ACTION__RIGHT_NAVIGATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRightNavigation(NavigationPanel newRightNavigation, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newRightNavigation, AppPackage.ACTION__RIGHT_NAVIGATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRightNavigation(NavigationPanel newRightNavigation) {
		eDynamicSet(AppPackage.ACTION__RIGHT_NAVIGATION, AppPackage.Literals.ACTION__RIGHT_NAVIGATION, newRightNavigation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NavigationPanel getFloatLeftNavigation() {
		return (NavigationPanel)eDynamicGet(AppPackage.ACTION__FLOAT_LEFT_NAVIGATION, AppPackage.Literals.ACTION__FLOAT_LEFT_NAVIGATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFloatLeftNavigation(NavigationPanel newFloatLeftNavigation, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newFloatLeftNavigation, AppPackage.ACTION__FLOAT_LEFT_NAVIGATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFloatLeftNavigation(NavigationPanel newFloatLeftNavigation) {
		eDynamicSet(AppPackage.ACTION__FLOAT_LEFT_NAVIGATION, AppPackage.Literals.ACTION__FLOAT_LEFT_NAVIGATION, newFloatLeftNavigation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NavigationPanel getFloatRightNavigation() {
		return (NavigationPanel)eDynamicGet(AppPackage.ACTION__FLOAT_RIGHT_NAVIGATION, AppPackage.Literals.ACTION__FLOAT_RIGHT_NAVIGATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFloatRightNavigation(NavigationPanel newFloatRightNavigation, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newFloatRightNavigation, AppPackage.ACTION__FLOAT_RIGHT_NAVIGATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFloatRightNavigation(NavigationPanel newFloatRightNavigation) {
		eDynamicSet(AppPackage.ACTION__FLOAT_RIGHT_NAVIGATION, AppPackage.Literals.ACTION__FLOAT_RIGHT_NAVIGATION, newFloatRightNavigation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Action> getAnonymous() {
		return (EList<Action>)eDynamicGet(AppPackage.ACTION__ANONYMOUS, AppPackage.Literals.ACTION__ANONYMOUS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Resource> getResources() {
		return (EList<Resource>)eDynamicGet(AppPackage.ACTION__RESOURCES, AppPackage.Literals.ACTION__RESOURCES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isInline() {
		return (Boolean)eDynamicGet(AppPackage.ACTION__INLINE, AppPackage.Literals.ACTION__INLINE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInline(boolean newInline) {
		eDynamicSet(AppPackage.ACTION__INLINE, AppPackage.Literals.ACTION__INLINE, newInline);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isModalActivator() {
		return (Boolean)eDynamicGet(AppPackage.ACTION__MODAL_ACTIVATOR, AppPackage.Literals.ACTION__MODAL_ACTIVATOR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setModalActivator(boolean newModalActivator) {
		eDynamicSet(AppPackage.ACTION__MODAL_ACTIVATOR, AppPackage.Literals.ACTION__MODAL_ACTIVATOR, newModalActivator);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AppPackage.ACTION__SECTIONS:
				return ((InternalEList<?>)getSections()).basicRemove(otherEnd, msgs);
			case AppPackage.ACTION__NAVIGATION:
				return ((InternalEList<?>)getNavigation()).basicRemove(otherEnd, msgs);
			case AppPackage.ACTION__LEFT_NAVIGATION:
				return basicSetLeftNavigation(null, msgs);
			case AppPackage.ACTION__RIGHT_NAVIGATION:
				return basicSetRightNavigation(null, msgs);
			case AppPackage.ACTION__FLOAT_LEFT_NAVIGATION:
				return basicSetFloatLeftNavigation(null, msgs);
			case AppPackage.ACTION__FLOAT_RIGHT_NAVIGATION:
				return basicSetFloatRightNavigation(null, msgs);
			case AppPackage.ACTION__ANONYMOUS:
				return ((InternalEList<?>)getAnonymous()).basicRemove(otherEnd, msgs);
			case AppPackage.ACTION__RESOURCES:
				return ((InternalEList<?>)getResources()).basicRemove(otherEnd, msgs);
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
			case AppPackage.ACTION__SECTION_COLUMNS:
				return getSectionColumns();
			case AppPackage.ACTION__SECTION_STYLE:
				return getSectionStyle();
			case AppPackage.ACTION__SECTIONS:
				return getSections();
			case AppPackage.ACTION__NAVIGATION:
				return getNavigation();
			case AppPackage.ACTION__LEFT_NAVIGATION:
				return getLeftNavigation();
			case AppPackage.ACTION__RIGHT_NAVIGATION:
				return getRightNavigation();
			case AppPackage.ACTION__FLOAT_LEFT_NAVIGATION:
				return getFloatLeftNavigation();
			case AppPackage.ACTION__FLOAT_RIGHT_NAVIGATION:
				return getFloatRightNavigation();
			case AppPackage.ACTION__ANONYMOUS:
				return getAnonymous();
			case AppPackage.ACTION__RESOURCES:
				return getResources();
			case AppPackage.ACTION__INLINE:
				return isInline();
			case AppPackage.ACTION__MODAL_ACTIVATOR:
				return isModalActivator();
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
			case AppPackage.ACTION__SECTION_COLUMNS:
				setSectionColumns((Integer)newValue);
				return;
			case AppPackage.ACTION__SECTION_STYLE:
				setSectionStyle((SectionStyle)newValue);
				return;
			case AppPackage.ACTION__SECTIONS:
				getSections().clear();
				getSections().addAll((Collection<? extends Action>)newValue);
				return;
			case AppPackage.ACTION__NAVIGATION:
				getNavigation().clear();
				getNavigation().addAll((Collection<? extends EObject>)newValue);
				return;
			case AppPackage.ACTION__LEFT_NAVIGATION:
				setLeftNavigation((NavigationPanel)newValue);
				return;
			case AppPackage.ACTION__RIGHT_NAVIGATION:
				setRightNavigation((NavigationPanel)newValue);
				return;
			case AppPackage.ACTION__FLOAT_LEFT_NAVIGATION:
				setFloatLeftNavigation((NavigationPanel)newValue);
				return;
			case AppPackage.ACTION__FLOAT_RIGHT_NAVIGATION:
				setFloatRightNavigation((NavigationPanel)newValue);
				return;
			case AppPackage.ACTION__ANONYMOUS:
				getAnonymous().clear();
				getAnonymous().addAll((Collection<? extends Action>)newValue);
				return;
			case AppPackage.ACTION__RESOURCES:
				getResources().clear();
				getResources().addAll((Collection<? extends Resource>)newValue);
				return;
			case AppPackage.ACTION__INLINE:
				setInline((Boolean)newValue);
				return;
			case AppPackage.ACTION__MODAL_ACTIVATOR:
				setModalActivator((Boolean)newValue);
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
			case AppPackage.ACTION__SECTION_COLUMNS:
				setSectionColumns(SECTION_COLUMNS_EDEFAULT);
				return;
			case AppPackage.ACTION__SECTION_STYLE:
				setSectionStyle(SECTION_STYLE_EDEFAULT);
				return;
			case AppPackage.ACTION__SECTIONS:
				getSections().clear();
				return;
			case AppPackage.ACTION__NAVIGATION:
				getNavigation().clear();
				return;
			case AppPackage.ACTION__LEFT_NAVIGATION:
				setLeftNavigation((NavigationPanel)null);
				return;
			case AppPackage.ACTION__RIGHT_NAVIGATION:
				setRightNavigation((NavigationPanel)null);
				return;
			case AppPackage.ACTION__FLOAT_LEFT_NAVIGATION:
				setFloatLeftNavigation((NavigationPanel)null);
				return;
			case AppPackage.ACTION__FLOAT_RIGHT_NAVIGATION:
				setFloatRightNavigation((NavigationPanel)null);
				return;
			case AppPackage.ACTION__ANONYMOUS:
				getAnonymous().clear();
				return;
			case AppPackage.ACTION__RESOURCES:
				getResources().clear();
				return;
			case AppPackage.ACTION__INLINE:
				setInline(INLINE_EDEFAULT);
				return;
			case AppPackage.ACTION__MODAL_ACTIVATOR:
				setModalActivator(MODAL_ACTIVATOR_EDEFAULT);
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
			case AppPackage.ACTION__SECTION_COLUMNS:
				return getSectionColumns() != SECTION_COLUMNS_EDEFAULT;
			case AppPackage.ACTION__SECTION_STYLE:
				return getSectionStyle() != SECTION_STYLE_EDEFAULT;
			case AppPackage.ACTION__SECTIONS:
				return !getSections().isEmpty();
			case AppPackage.ACTION__NAVIGATION:
				return !getNavigation().isEmpty();
			case AppPackage.ACTION__LEFT_NAVIGATION:
				return getLeftNavigation() != null;
			case AppPackage.ACTION__RIGHT_NAVIGATION:
				return getRightNavigation() != null;
			case AppPackage.ACTION__FLOAT_LEFT_NAVIGATION:
				return getFloatLeftNavigation() != null;
			case AppPackage.ACTION__FLOAT_RIGHT_NAVIGATION:
				return getFloatRightNavigation() != null;
			case AppPackage.ACTION__ANONYMOUS:
				return !getAnonymous().isEmpty();
			case AppPackage.ACTION__RESOURCES:
				return !getResources().isEmpty();
			case AppPackage.ACTION__INLINE:
				return isInline() != INLINE_EDEFAULT;
			case AppPackage.ACTION__MODAL_ACTIVATOR:
				return isModalActivator() != MODAL_ACTIVATOR_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //ActionImpl
