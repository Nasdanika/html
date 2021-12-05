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
import org.nasdanika.html.model.bootstrap.Slide;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Slide</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.SlideImpl#getInterval <em>Interval</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.SlideImpl#getCaptions <em>Captions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SlideImpl extends DivImpl implements Slide {
	/**
	 * The default value of the '{@link #getInterval() <em>Interval</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterval()
	 * @generated
	 * @ordered
	 */
	protected static final Integer INTERVAL_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SlideImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.SLIDE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Integer getInterval() {
		return (Integer)eDynamicGet(BootstrapPackage.SLIDE__INTERVAL, BootstrapPackage.Literals.SLIDE__INTERVAL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInterval(Integer newInterval) {
		eDynamicSet(BootstrapPackage.SLIDE__INTERVAL, BootstrapPackage.Literals.SLIDE__INTERVAL, newInterval);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getCaptions() {
		return (EList<EObject>)eDynamicGet(BootstrapPackage.SLIDE__CAPTIONS, BootstrapPackage.Literals.SLIDE__CAPTIONS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BootstrapPackage.SLIDE__CAPTIONS:
				return ((InternalEList<?>)getCaptions()).basicRemove(otherEnd, msgs);
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
			case BootstrapPackage.SLIDE__INTERVAL:
				return getInterval();
			case BootstrapPackage.SLIDE__CAPTIONS:
				return getCaptions();
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
			case BootstrapPackage.SLIDE__INTERVAL:
				setInterval((Integer)newValue);
				return;
			case BootstrapPackage.SLIDE__CAPTIONS:
				getCaptions().clear();
				getCaptions().addAll((Collection<? extends EObject>)newValue);
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
			case BootstrapPackage.SLIDE__INTERVAL:
				setInterval(INTERVAL_EDEFAULT);
				return;
			case BootstrapPackage.SLIDE__CAPTIONS:
				getCaptions().clear();
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
			case BootstrapPackage.SLIDE__INTERVAL:
				return INTERVAL_EDEFAULT == null ? getInterval() != null : !INTERVAL_EDEFAULT.equals(getInterval());
			case BootstrapPackage.SLIDE__CAPTIONS:
				return !getCaptions().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SlideImpl
