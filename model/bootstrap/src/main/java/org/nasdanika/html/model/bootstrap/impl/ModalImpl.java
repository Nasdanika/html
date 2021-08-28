/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.nasdanika.html.model.bootstrap.BootstrapElement;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.Modal;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Modal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ModalImpl#getHeader <em>Header</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ModalImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ModalImpl#getFooter <em>Footer</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ModalImpl#isDismisser <em>Dismisser</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ModalImpl#isScrollable <em>Scrollable</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ModalImpl#isCentered <em>Centered</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.ModalImpl#getSize <em>Size</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ModalImpl extends BootstrapElementImpl implements Modal {
	/**
	 * The default value of the '{@link #isDismisser() <em>Dismisser</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDismisser()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISMISSER_EDEFAULT = true;
	/**
	 * The default value of the '{@link #isScrollable() <em>Scrollable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isScrollable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SCROLLABLE_EDEFAULT = false;
	/**
	 * The default value of the '{@link #isCentered() <em>Centered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCentered()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CENTERED_EDEFAULT = false;
	/**
	 * The default value of the '{@link #getSize() <em>Size</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSize()
	 * @generated
	 * @ordered
	 */
	protected static final String SIZE_EDEFAULT = "";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.MODAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BootstrapElement getHeader() {
		return (BootstrapElement)eDynamicGet(BootstrapPackage.MODAL__HEADER, BootstrapPackage.Literals.MODAL__HEADER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHeader(BootstrapElement newHeader, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newHeader, BootstrapPackage.MODAL__HEADER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHeader(BootstrapElement newHeader) {
		eDynamicSet(BootstrapPackage.MODAL__HEADER, BootstrapPackage.Literals.MODAL__HEADER, newHeader);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BootstrapElement getBody() {
		return (BootstrapElement)eDynamicGet(BootstrapPackage.MODAL__BODY, BootstrapPackage.Literals.MODAL__BODY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(BootstrapElement newBody, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newBody, BootstrapPackage.MODAL__BODY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBody(BootstrapElement newBody) {
		eDynamicSet(BootstrapPackage.MODAL__BODY, BootstrapPackage.Literals.MODAL__BODY, newBody);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BootstrapElement getFooter() {
		return (BootstrapElement)eDynamicGet(BootstrapPackage.MODAL__FOOTER, BootstrapPackage.Literals.MODAL__FOOTER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFooter(BootstrapElement newFooter, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newFooter, BootstrapPackage.MODAL__FOOTER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFooter(BootstrapElement newFooter) {
		eDynamicSet(BootstrapPackage.MODAL__FOOTER, BootstrapPackage.Literals.MODAL__FOOTER, newFooter);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDismisser() {
		return (Boolean)eDynamicGet(BootstrapPackage.MODAL__DISMISSER, BootstrapPackage.Literals.MODAL__DISMISSER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDismisser(boolean newDismisser) {
		eDynamicSet(BootstrapPackage.MODAL__DISMISSER, BootstrapPackage.Literals.MODAL__DISMISSER, newDismisser);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isScrollable() {
		return (Boolean)eDynamicGet(BootstrapPackage.MODAL__SCROLLABLE, BootstrapPackage.Literals.MODAL__SCROLLABLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setScrollable(boolean newScrollable) {
		eDynamicSet(BootstrapPackage.MODAL__SCROLLABLE, BootstrapPackage.Literals.MODAL__SCROLLABLE, newScrollable);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isCentered() {
		return (Boolean)eDynamicGet(BootstrapPackage.MODAL__CENTERED, BootstrapPackage.Literals.MODAL__CENTERED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCentered(boolean newCentered) {
		eDynamicSet(BootstrapPackage.MODAL__CENTERED, BootstrapPackage.Literals.MODAL__CENTERED, newCentered);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getSize() {
		return (String)eDynamicGet(BootstrapPackage.MODAL__SIZE, BootstrapPackage.Literals.MODAL__SIZE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSize(String newSize) {
		eDynamicSet(BootstrapPackage.MODAL__SIZE, BootstrapPackage.Literals.MODAL__SIZE, newSize);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BootstrapPackage.MODAL__HEADER:
				return basicSetHeader(null, msgs);
			case BootstrapPackage.MODAL__BODY:
				return basicSetBody(null, msgs);
			case BootstrapPackage.MODAL__FOOTER:
				return basicSetFooter(null, msgs);
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
			case BootstrapPackage.MODAL__HEADER:
				return getHeader();
			case BootstrapPackage.MODAL__BODY:
				return getBody();
			case BootstrapPackage.MODAL__FOOTER:
				return getFooter();
			case BootstrapPackage.MODAL__DISMISSER:
				return isDismisser();
			case BootstrapPackage.MODAL__SCROLLABLE:
				return isScrollable();
			case BootstrapPackage.MODAL__CENTERED:
				return isCentered();
			case BootstrapPackage.MODAL__SIZE:
				return getSize();
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
			case BootstrapPackage.MODAL__HEADER:
				setHeader((BootstrapElement)newValue);
				return;
			case BootstrapPackage.MODAL__BODY:
				setBody((BootstrapElement)newValue);
				return;
			case BootstrapPackage.MODAL__FOOTER:
				setFooter((BootstrapElement)newValue);
				return;
			case BootstrapPackage.MODAL__DISMISSER:
				setDismisser((Boolean)newValue);
				return;
			case BootstrapPackage.MODAL__SCROLLABLE:
				setScrollable((Boolean)newValue);
				return;
			case BootstrapPackage.MODAL__CENTERED:
				setCentered((Boolean)newValue);
				return;
			case BootstrapPackage.MODAL__SIZE:
				setSize((String)newValue);
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
			case BootstrapPackage.MODAL__HEADER:
				setHeader((BootstrapElement)null);
				return;
			case BootstrapPackage.MODAL__BODY:
				setBody((BootstrapElement)null);
				return;
			case BootstrapPackage.MODAL__FOOTER:
				setFooter((BootstrapElement)null);
				return;
			case BootstrapPackage.MODAL__DISMISSER:
				setDismisser(DISMISSER_EDEFAULT);
				return;
			case BootstrapPackage.MODAL__SCROLLABLE:
				setScrollable(SCROLLABLE_EDEFAULT);
				return;
			case BootstrapPackage.MODAL__CENTERED:
				setCentered(CENTERED_EDEFAULT);
				return;
			case BootstrapPackage.MODAL__SIZE:
				setSize(SIZE_EDEFAULT);
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
			case BootstrapPackage.MODAL__HEADER:
				return getHeader() != null;
			case BootstrapPackage.MODAL__BODY:
				return getBody() != null;
			case BootstrapPackage.MODAL__FOOTER:
				return getFooter() != null;
			case BootstrapPackage.MODAL__DISMISSER:
				return isDismisser() != DISMISSER_EDEFAULT;
			case BootstrapPackage.MODAL__SCROLLABLE:
				return isScrollable() != SCROLLABLE_EDEFAULT;
			case BootstrapPackage.MODAL__CENTERED:
				return isCentered() != CENTERED_EDEFAULT;
			case BootstrapPackage.MODAL__SIZE:
				return SIZE_EDEFAULT == null ? getSize() != null : !SIZE_EDEFAULT.equals(getSize());
		}
		return super.eIsSet(featureID);
	}

} //ModalImpl
