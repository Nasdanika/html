/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.Card;
import org.nasdanika.html.model.bootstrap.Div;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Card</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.CardImpl#getHeader <em>Header</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.CardImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.CardImpl#getFooter <em>Footer</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CardImpl extends DivImpl implements Card {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CardImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.CARD;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Div getHeader() {
		return (Div)eDynamicGet(BootstrapPackage.CARD__HEADER, BootstrapPackage.Literals.CARD__HEADER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHeader(Div newHeader, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newHeader, BootstrapPackage.CARD__HEADER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHeader(Div newHeader) {
		eDynamicSet(BootstrapPackage.CARD__HEADER, BootstrapPackage.Literals.CARD__HEADER, newHeader);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Div> getBody() {
		return (EList<Div>)eDynamicGet(BootstrapPackage.CARD__BODY, BootstrapPackage.Literals.CARD__BODY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Div getFooter() {
		return (Div)eDynamicGet(BootstrapPackage.CARD__FOOTER, BootstrapPackage.Literals.CARD__FOOTER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFooter(Div newFooter, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newFooter, BootstrapPackage.CARD__FOOTER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFooter(Div newFooter) {
		eDynamicSet(BootstrapPackage.CARD__FOOTER, BootstrapPackage.Literals.CARD__FOOTER, newFooter);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BootstrapPackage.CARD__HEADER:
				return basicSetHeader(null, msgs);
			case BootstrapPackage.CARD__BODY:
				return ((InternalEList<?>)getBody()).basicRemove(otherEnd, msgs);
			case BootstrapPackage.CARD__FOOTER:
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
			case BootstrapPackage.CARD__HEADER:
				return getHeader();
			case BootstrapPackage.CARD__BODY:
				return getBody();
			case BootstrapPackage.CARD__FOOTER:
				return getFooter();
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
			case BootstrapPackage.CARD__HEADER:
				setHeader((Div)newValue);
				return;
			case BootstrapPackage.CARD__BODY:
				getBody().clear();
				getBody().addAll((Collection<? extends Div>)newValue);
				return;
			case BootstrapPackage.CARD__FOOTER:
				setFooter((Div)newValue);
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
			case BootstrapPackage.CARD__HEADER:
				setHeader((Div)null);
				return;
			case BootstrapPackage.CARD__BODY:
				getBody().clear();
				return;
			case BootstrapPackage.CARD__FOOTER:
				setFooter((Div)null);
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
			case BootstrapPackage.CARD__HEADER:
				return getHeader() != null;
			case BootstrapPackage.CARD__BODY:
				return !getBody().isEmpty();
			case BootstrapPackage.CARD__FOOTER:
				return getFooter() != null;
		}
		return super.eIsSet(featureID);
	}

} //CardImpl
