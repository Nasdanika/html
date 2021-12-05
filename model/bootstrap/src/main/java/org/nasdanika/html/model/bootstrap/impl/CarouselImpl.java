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
import org.nasdanika.html.model.bootstrap.Carousel;
import org.nasdanika.html.model.bootstrap.Slide;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Carousel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.CarouselImpl#getSlides <em>Slides</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.CarouselImpl#isControls <em>Controls</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.CarouselImpl#isIndicator <em>Indicator</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.CarouselImpl#isCrossFade <em>Cross Fade</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.CarouselImpl#isRide <em>Ride</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.impl.CarouselImpl#getInterval <em>Interval</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CarouselImpl extends DivImpl implements Carousel {
	/**
	 * The default value of the '{@link #isControls() <em>Controls</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isControls()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONTROLS_EDEFAULT = false;
	/**
	 * The default value of the '{@link #isIndicator() <em>Indicator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIndicator()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INDICATOR_EDEFAULT = false;
	/**
	 * The default value of the '{@link #isCrossFade() <em>Cross Fade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isCrossFade()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CROSS_FADE_EDEFAULT = false;
	/**
	 * The default value of the '{@link #isRide() <em>Ride</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRide()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RIDE_EDEFAULT = true;
	/**
	 * The default value of the '{@link #getInterval() <em>Interval</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterval()
	 * @generated
	 * @ordered
	 */
	protected static final String INTERVAL_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CarouselImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BootstrapPackage.Literals.CAROUSEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Slide> getSlides() {
		return (EList<Slide>)eDynamicGet(BootstrapPackage.CAROUSEL__SLIDES, BootstrapPackage.Literals.CAROUSEL__SLIDES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isControls() {
		return (Boolean)eDynamicGet(BootstrapPackage.CAROUSEL__CONTROLS, BootstrapPackage.Literals.CAROUSEL__CONTROLS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setControls(boolean newControls) {
		eDynamicSet(BootstrapPackage.CAROUSEL__CONTROLS, BootstrapPackage.Literals.CAROUSEL__CONTROLS, newControls);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIndicator() {
		return (Boolean)eDynamicGet(BootstrapPackage.CAROUSEL__INDICATOR, BootstrapPackage.Literals.CAROUSEL__INDICATOR, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIndicator(boolean newIndicator) {
		eDynamicSet(BootstrapPackage.CAROUSEL__INDICATOR, BootstrapPackage.Literals.CAROUSEL__INDICATOR, newIndicator);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isCrossFade() {
		return (Boolean)eDynamicGet(BootstrapPackage.CAROUSEL__CROSS_FADE, BootstrapPackage.Literals.CAROUSEL__CROSS_FADE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setCrossFade(boolean newCrossFade) {
		eDynamicSet(BootstrapPackage.CAROUSEL__CROSS_FADE, BootstrapPackage.Literals.CAROUSEL__CROSS_FADE, newCrossFade);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRide() {
		return (Boolean)eDynamicGet(BootstrapPackage.CAROUSEL__RIDE, BootstrapPackage.Literals.CAROUSEL__RIDE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRide(boolean newRide) {
		eDynamicSet(BootstrapPackage.CAROUSEL__RIDE, BootstrapPackage.Literals.CAROUSEL__RIDE, newRide);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getInterval() {
		return (String)eDynamicGet(BootstrapPackage.CAROUSEL__INTERVAL, BootstrapPackage.Literals.CAROUSEL__INTERVAL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInterval(String newInterval) {
		eDynamicSet(BootstrapPackage.CAROUSEL__INTERVAL, BootstrapPackage.Literals.CAROUSEL__INTERVAL, newInterval);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case BootstrapPackage.CAROUSEL__SLIDES:
				return ((InternalEList<?>)getSlides()).basicRemove(otherEnd, msgs);
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
			case BootstrapPackage.CAROUSEL__SLIDES:
				return getSlides();
			case BootstrapPackage.CAROUSEL__CONTROLS:
				return isControls();
			case BootstrapPackage.CAROUSEL__INDICATOR:
				return isIndicator();
			case BootstrapPackage.CAROUSEL__CROSS_FADE:
				return isCrossFade();
			case BootstrapPackage.CAROUSEL__RIDE:
				return isRide();
			case BootstrapPackage.CAROUSEL__INTERVAL:
				return getInterval();
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
			case BootstrapPackage.CAROUSEL__SLIDES:
				getSlides().clear();
				getSlides().addAll((Collection<? extends Slide>)newValue);
				return;
			case BootstrapPackage.CAROUSEL__CONTROLS:
				setControls((Boolean)newValue);
				return;
			case BootstrapPackage.CAROUSEL__INDICATOR:
				setIndicator((Boolean)newValue);
				return;
			case BootstrapPackage.CAROUSEL__CROSS_FADE:
				setCrossFade((Boolean)newValue);
				return;
			case BootstrapPackage.CAROUSEL__RIDE:
				setRide((Boolean)newValue);
				return;
			case BootstrapPackage.CAROUSEL__INTERVAL:
				setInterval((String)newValue);
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
			case BootstrapPackage.CAROUSEL__SLIDES:
				getSlides().clear();
				return;
			case BootstrapPackage.CAROUSEL__CONTROLS:
				setControls(CONTROLS_EDEFAULT);
				return;
			case BootstrapPackage.CAROUSEL__INDICATOR:
				setIndicator(INDICATOR_EDEFAULT);
				return;
			case BootstrapPackage.CAROUSEL__CROSS_FADE:
				setCrossFade(CROSS_FADE_EDEFAULT);
				return;
			case BootstrapPackage.CAROUSEL__RIDE:
				setRide(RIDE_EDEFAULT);
				return;
			case BootstrapPackage.CAROUSEL__INTERVAL:
				setInterval(INTERVAL_EDEFAULT);
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
			case BootstrapPackage.CAROUSEL__SLIDES:
				return !getSlides().isEmpty();
			case BootstrapPackage.CAROUSEL__CONTROLS:
				return isControls() != CONTROLS_EDEFAULT;
			case BootstrapPackage.CAROUSEL__INDICATOR:
				return isIndicator() != INDICATOR_EDEFAULT;
			case BootstrapPackage.CAROUSEL__CROSS_FADE:
				return isCrossFade() != CROSS_FADE_EDEFAULT;
			case BootstrapPackage.CAROUSEL__RIDE:
				return isRide() != RIDE_EDEFAULT;
			case BootstrapPackage.CAROUSEL__INTERVAL:
				return INTERVAL_EDEFAULT == null ? getInterval() != null : !INTERVAL_EDEFAULT.equals(getInterval());
		}
		return super.eIsSet(featureID);
	}

} //CarouselImpl
