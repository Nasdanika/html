/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.bootstrap.Modal;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.impl.LinkImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LinkImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LinkImpl#getModal <em>Modal</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LinkImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LinkImpl#getConfirmation <em>Confirmation</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LinkImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.impl.LinkImpl#getAction <em>Action</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LinkImpl extends LabelImpl implements Link {
	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getScript() <em>Script</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScript()
	 * @generated
	 * @ordered
	 */
	protected static final String SCRIPT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

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
	 * The default value of the '{@link #getTarget() <em>Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AppPackage.Literals.LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLocation() {
		return (String)eDynamicGet(AppPackage.LINK__LOCATION, AppPackage.Literals.LINK__LOCATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLocation(String newLocation) {
		eDynamicSet(AppPackage.LINK__LOCATION, AppPackage.Literals.LINK__LOCATION, newLocation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getScript() {
		return (String)eDynamicGet(AppPackage.LINK__SCRIPT, AppPackage.Literals.LINK__SCRIPT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setScript(String newScript) {
		eDynamicSet(AppPackage.LINK__SCRIPT, AppPackage.Literals.LINK__SCRIPT, newScript);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Modal getModal() {
		return (Modal)eDynamicGet(AppPackage.LINK__MODAL, AppPackage.Literals.LINK__MODAL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModal(Modal newModal, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newModal, AppPackage.LINK__MODAL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setModal(Modal newModal) {
		eDynamicSet(AppPackage.LINK__MODAL, AppPackage.Literals.LINK__MODAL, newModal);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getConfirmation() {
		return (String)eDynamicGet(AppPackage.LINK__CONFIRMATION, AppPackage.Literals.LINK__CONFIRMATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setConfirmation(String newConfirmation) {
		eDynamicSet(AppPackage.LINK__CONFIRMATION, AppPackage.Literals.LINK__CONFIRMATION, newConfirmation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return (String)eDynamicGet(AppPackage.LINK__NAME, AppPackage.Literals.LINK__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		eDynamicSet(AppPackage.LINK__NAME, AppPackage.Literals.LINK__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTarget() {
		return (String)eDynamicGet(AppPackage.LINK__TARGET, AppPackage.Literals.LINK__TARGET, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTarget(String newTarget) {
		eDynamicSet(AppPackage.LINK__TARGET, AppPackage.Literals.LINK__TARGET, newTarget);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Action getAction() {
		return (Action)eDynamicGet(AppPackage.LINK__ACTION, AppPackage.Literals.LINK__ACTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Action basicGetAction() {
		return (Action)eDynamicGet(AppPackage.LINK__ACTION, AppPackage.Literals.LINK__ACTION, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAction(Action newAction) {
		eDynamicSet(AppPackage.LINK__ACTION, AppPackage.Literals.LINK__ACTION, newAction);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AppPackage.LINK__MODAL:
				return basicSetModal(null, msgs);
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
			case AppPackage.LINK__LOCATION:
				return getLocation();
			case AppPackage.LINK__SCRIPT:
				return getScript();
			case AppPackage.LINK__MODAL:
				return getModal();
			case AppPackage.LINK__NAME:
				return getName();
			case AppPackage.LINK__CONFIRMATION:
				return getConfirmation();
			case AppPackage.LINK__TARGET:
				return getTarget();
			case AppPackage.LINK__ACTION:
				if (resolve) return getAction();
				return basicGetAction();
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
			case AppPackage.LINK__LOCATION:
				setLocation((String)newValue);
				return;
			case AppPackage.LINK__SCRIPT:
				setScript((String)newValue);
				return;
			case AppPackage.LINK__MODAL:
				setModal((Modal)newValue);
				return;
			case AppPackage.LINK__NAME:
				setName((String)newValue);
				return;
			case AppPackage.LINK__CONFIRMATION:
				setConfirmation((String)newValue);
				return;
			case AppPackage.LINK__TARGET:
				setTarget((String)newValue);
				return;
			case AppPackage.LINK__ACTION:
				setAction((Action)newValue);
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
			case AppPackage.LINK__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case AppPackage.LINK__SCRIPT:
				setScript(SCRIPT_EDEFAULT);
				return;
			case AppPackage.LINK__MODAL:
				setModal((Modal)null);
				return;
			case AppPackage.LINK__NAME:
				setName(NAME_EDEFAULT);
				return;
			case AppPackage.LINK__CONFIRMATION:
				setConfirmation(CONFIRMATION_EDEFAULT);
				return;
			case AppPackage.LINK__TARGET:
				setTarget(TARGET_EDEFAULT);
				return;
			case AppPackage.LINK__ACTION:
				setAction((Action)null);
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
			case AppPackage.LINK__LOCATION:
				return LOCATION_EDEFAULT == null ? getLocation() != null : !LOCATION_EDEFAULT.equals(getLocation());
			case AppPackage.LINK__SCRIPT:
				return SCRIPT_EDEFAULT == null ? getScript() != null : !SCRIPT_EDEFAULT.equals(getScript());
			case AppPackage.LINK__MODAL:
				return getModal() != null;
			case AppPackage.LINK__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case AppPackage.LINK__CONFIRMATION:
				return CONFIRMATION_EDEFAULT == null ? getConfirmation() != null : !CONFIRMATION_EDEFAULT.equals(getConfirmation());
			case AppPackage.LINK__TARGET:
				return TARGET_EDEFAULT == null ? getTarget() != null : !TARGET_EDEFAULT.equals(getTarget());
			case AppPackage.LINK__ACTION:
				return basicGetAction() != null;
		}
		return super.eIsSet(featureID);
	}

} //LinkImpl
