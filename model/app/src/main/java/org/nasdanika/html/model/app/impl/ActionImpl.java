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
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getRole <em>Role</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getSectionStyle <em>Section Style</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getSectionColumns <em>Section Columns</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getConfirmation <em>Confirmation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#isDisabled <em>Disabled</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#isInline <em>Inline</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#isModal <em>Modal</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getContent <em>Content</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getSections <em>Sections</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getContext <em>Context</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getContentLeft <em>Content Left</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getContentRight <em>Content Right</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getFloatLeft <em>Float Left</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.ActionImpl#getFloatRight <em>Float Right</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ActionImpl extends LinkImpl implements Action {
	/**
	 * The default value of the '{@link #getRole() <em>Role</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected static final String ROLE_EDEFAULT = "Navigation";

	/**
	 * The default value of the '{@link #getSectionStyle() <em>Section Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSectionStyle()
	 * @generated
	 * @ordered
	 */
	protected static final org.nasdanika.html.model.app.SectionStyle SECTION_STYLE_EDEFAULT = org.nasdanika.html.model.app.SectionStyle.AUTO;

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
	 * The default value of the '{@link #getConfirmation() <em>Confirmation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConfirmation()
	 * @generated
	 * @ordered
	 */
	protected static final String CONFIRMATION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isDisabled() <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISABLED_EDEFAULT = false;

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
	 * The default value of the '{@link #isModal() <em>Modal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isModal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MODAL_EDEFAULT = false;

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
	public String getRole() {
		return (String)eDynamicGet(AppPackage.ACTION__ROLE, AppPackage.Literals.ACTION__ROLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRole(String newRole) {
		eDynamicSet(AppPackage.ACTION__ROLE, AppPackage.Literals.ACTION__ROLE, newRole);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.nasdanika.html.model.app.SectionStyle getSectionStyle() {
		return (org.nasdanika.html.model.app.SectionStyle)eDynamicGet(AppPackage.ACTION__SECTION_STYLE, AppPackage.Literals.ACTION__SECTION_STYLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSectionStyle(org.nasdanika.html.model.app.SectionStyle newSectionStyle) {
		eDynamicSet(AppPackage.ACTION__SECTION_STYLE, AppPackage.Literals.ACTION__SECTION_STYLE, newSectionStyle);
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
	public String getConfirmation() {
		return (String)eDynamicGet(AppPackage.ACTION__CONFIRMATION, AppPackage.Literals.ACTION__CONFIRMATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConfirmation(String newConfirmation) {
		eDynamicSet(AppPackage.ACTION__CONFIRMATION, AppPackage.Literals.ACTION__CONFIRMATION, newConfirmation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDisabled() {
		return (Boolean)eDynamicGet(AppPackage.ACTION__DISABLED, AppPackage.Literals.ACTION__DISABLED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDisabled(boolean newDisabled) {
		eDynamicSet(AppPackage.ACTION__DISABLED, AppPackage.Literals.ACTION__DISABLED, newDisabled);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getContent() {
		return (EList<EObject>)eDynamicGet(AppPackage.ACTION__CONTENT, AppPackage.Literals.ACTION__CONTENT, true, true);
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
	public EList<org.nasdanika.html.model.app.Label> getContext() {
		return (EList<org.nasdanika.html.model.app.Label>)eDynamicGet(AppPackage.ACTION__CONTEXT, AppPackage.Literals.ACTION__CONTEXT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<org.nasdanika.html.model.app.Label> getContentLeft() {
		return (EList<org.nasdanika.html.model.app.Label>)eDynamicGet(AppPackage.ACTION__CONTENT_LEFT, AppPackage.Literals.ACTION__CONTENT_LEFT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<org.nasdanika.html.model.app.Label> getContentRight() {
		return (EList<org.nasdanika.html.model.app.Label>)eDynamicGet(AppPackage.ACTION__CONTENT_RIGHT, AppPackage.Literals.ACTION__CONTENT_RIGHT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<org.nasdanika.html.model.app.Label> getFloatLeft() {
		return (EList<org.nasdanika.html.model.app.Label>)eDynamicGet(AppPackage.ACTION__FLOAT_LEFT, AppPackage.Literals.ACTION__FLOAT_LEFT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<org.nasdanika.html.model.app.Label> getFloatRight() {
		return (EList<org.nasdanika.html.model.app.Label>)eDynamicGet(AppPackage.ACTION__FLOAT_RIGHT, AppPackage.Literals.ACTION__FLOAT_RIGHT, true, true);
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
	public boolean isModal() {
		return (Boolean)eDynamicGet(AppPackage.ACTION__MODAL, AppPackage.Literals.ACTION__MODAL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setModal(boolean newModal) {
		eDynamicSet(AppPackage.ACTION__MODAL, AppPackage.Literals.ACTION__MODAL, newModal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AppPackage.ACTION__CONTENT:
				return ((InternalEList<?>)getContent()).basicRemove(otherEnd, msgs);
			case AppPackage.ACTION__SECTIONS:
				return ((InternalEList<?>)getSections()).basicRemove(otherEnd, msgs);
			case AppPackage.ACTION__CONTEXT:
				return ((InternalEList<?>)getContext()).basicRemove(otherEnd, msgs);
			case AppPackage.ACTION__CONTENT_LEFT:
				return ((InternalEList<?>)getContentLeft()).basicRemove(otherEnd, msgs);
			case AppPackage.ACTION__CONTENT_RIGHT:
				return ((InternalEList<?>)getContentRight()).basicRemove(otherEnd, msgs);
			case AppPackage.ACTION__FLOAT_LEFT:
				return ((InternalEList<?>)getFloatLeft()).basicRemove(otherEnd, msgs);
			case AppPackage.ACTION__FLOAT_RIGHT:
				return ((InternalEList<?>)getFloatRight()).basicRemove(otherEnd, msgs);
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
			case AppPackage.ACTION__ROLE:
				return getRole();
			case AppPackage.ACTION__SECTION_STYLE:
				return getSectionStyle();
			case AppPackage.ACTION__SECTION_COLUMNS:
				return getSectionColumns();
			case AppPackage.ACTION__CONFIRMATION:
				return getConfirmation();
			case AppPackage.ACTION__DISABLED:
				return isDisabled();
			case AppPackage.ACTION__INLINE:
				return isInline();
			case AppPackage.ACTION__MODAL:
				return isModal();
			case AppPackage.ACTION__CONTENT:
				return getContent();
			case AppPackage.ACTION__SECTIONS:
				return getSections();
			case AppPackage.ACTION__CONTEXT:
				return getContext();
			case AppPackage.ACTION__CONTENT_LEFT:
				return getContentLeft();
			case AppPackage.ACTION__CONTENT_RIGHT:
				return getContentRight();
			case AppPackage.ACTION__FLOAT_LEFT:
				return getFloatLeft();
			case AppPackage.ACTION__FLOAT_RIGHT:
				return getFloatRight();
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
			case AppPackage.ACTION__ROLE:
				setRole((String)newValue);
				return;
			case AppPackage.ACTION__SECTION_STYLE:
				setSectionStyle((org.nasdanika.html.model.app.SectionStyle)newValue);
				return;
			case AppPackage.ACTION__SECTION_COLUMNS:
				setSectionColumns((Integer)newValue);
				return;
			case AppPackage.ACTION__CONFIRMATION:
				setConfirmation((String)newValue);
				return;
			case AppPackage.ACTION__DISABLED:
				setDisabled((Boolean)newValue);
				return;
			case AppPackage.ACTION__INLINE:
				setInline((Boolean)newValue);
				return;
			case AppPackage.ACTION__MODAL:
				setModal((Boolean)newValue);
				return;
			case AppPackage.ACTION__CONTENT:
				getContent().clear();
				getContent().addAll((Collection<? extends EObject>)newValue);
				return;
			case AppPackage.ACTION__SECTIONS:
				getSections().clear();
				getSections().addAll((Collection<? extends Action>)newValue);
				return;
			case AppPackage.ACTION__CONTEXT:
				getContext().clear();
				getContext().addAll((Collection<? extends org.nasdanika.html.model.app.Label>)newValue);
				return;
			case AppPackage.ACTION__CONTENT_LEFT:
				getContentLeft().clear();
				getContentLeft().addAll((Collection<? extends org.nasdanika.html.model.app.Label>)newValue);
				return;
			case AppPackage.ACTION__CONTENT_RIGHT:
				getContentRight().clear();
				getContentRight().addAll((Collection<? extends org.nasdanika.html.model.app.Label>)newValue);
				return;
			case AppPackage.ACTION__FLOAT_LEFT:
				getFloatLeft().clear();
				getFloatLeft().addAll((Collection<? extends org.nasdanika.html.model.app.Label>)newValue);
				return;
			case AppPackage.ACTION__FLOAT_RIGHT:
				getFloatRight().clear();
				getFloatRight().addAll((Collection<? extends org.nasdanika.html.model.app.Label>)newValue);
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
			case AppPackage.ACTION__ROLE:
				setRole(ROLE_EDEFAULT);
				return;
			case AppPackage.ACTION__SECTION_STYLE:
				setSectionStyle(SECTION_STYLE_EDEFAULT);
				return;
			case AppPackage.ACTION__SECTION_COLUMNS:
				setSectionColumns(SECTION_COLUMNS_EDEFAULT);
				return;
			case AppPackage.ACTION__CONFIRMATION:
				setConfirmation(CONFIRMATION_EDEFAULT);
				return;
			case AppPackage.ACTION__DISABLED:
				setDisabled(DISABLED_EDEFAULT);
				return;
			case AppPackage.ACTION__INLINE:
				setInline(INLINE_EDEFAULT);
				return;
			case AppPackage.ACTION__MODAL:
				setModal(MODAL_EDEFAULT);
				return;
			case AppPackage.ACTION__CONTENT:
				getContent().clear();
				return;
			case AppPackage.ACTION__SECTIONS:
				getSections().clear();
				return;
			case AppPackage.ACTION__CONTEXT:
				getContext().clear();
				return;
			case AppPackage.ACTION__CONTENT_LEFT:
				getContentLeft().clear();
				return;
			case AppPackage.ACTION__CONTENT_RIGHT:
				getContentRight().clear();
				return;
			case AppPackage.ACTION__FLOAT_LEFT:
				getFloatLeft().clear();
				return;
			case AppPackage.ACTION__FLOAT_RIGHT:
				getFloatRight().clear();
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
			case AppPackage.ACTION__ROLE:
				return ROLE_EDEFAULT == null ? getRole() != null : !ROLE_EDEFAULT.equals(getRole());
			case AppPackage.ACTION__SECTION_STYLE:
				return getSectionStyle() != SECTION_STYLE_EDEFAULT;
			case AppPackage.ACTION__SECTION_COLUMNS:
				return getSectionColumns() != SECTION_COLUMNS_EDEFAULT;
			case AppPackage.ACTION__CONFIRMATION:
				return CONFIRMATION_EDEFAULT == null ? getConfirmation() != null : !CONFIRMATION_EDEFAULT.equals(getConfirmation());
			case AppPackage.ACTION__DISABLED:
				return isDisabled() != DISABLED_EDEFAULT;
			case AppPackage.ACTION__INLINE:
				return isInline() != INLINE_EDEFAULT;
			case AppPackage.ACTION__MODAL:
				return isModal() != MODAL_EDEFAULT;
			case AppPackage.ACTION__CONTENT:
				return !getContent().isEmpty();
			case AppPackage.ACTION__SECTIONS:
				return !getSections().isEmpty();
			case AppPackage.ACTION__CONTEXT:
				return !getContext().isEmpty();
			case AppPackage.ACTION__CONTENT_LEFT:
				return !getContentLeft().isEmpty();
			case AppPackage.ACTION__CONTENT_RIGHT:
				return !getContentRight().isEmpty();
			case AppPackage.ACTION__FLOAT_LEFT:
				return !getFloatLeft().isEmpty();
			case AppPackage.ACTION__FLOAT_RIGHT:
				return !getFloatRight().isEmpty();
		}
		return super.eIsSet(featureID);
	}
	
} //ActionImpl
