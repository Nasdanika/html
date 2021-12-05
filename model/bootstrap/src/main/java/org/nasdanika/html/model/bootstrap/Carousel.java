/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Carousel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Carousel#getSlides <em>Slides</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Carousel#isControls <em>Controls</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Carousel#isIndicator <em>Indicator</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Carousel#isCrossFade <em>Cross Fade</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Carousel#isRide <em>Ride</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Carousel#getInterval <em>Interval</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getCarousel()
 * @model
 * @generated
 */
public interface Carousel extends Div {
	/**
	 * Returns the value of the '<em><b>Slides</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.bootstrap.Slide}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Slides</em>' containment reference list.
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getCarousel_Slides()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	EList<Slide> getSlides();

	/**
	 * Returns the value of the '<em><b>Controls</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Controls</em>' attribute.
	 * @see #setControls(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getCarousel_Controls()
	 * @model
	 * @generated
	 */
	boolean isControls();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Carousel#isControls <em>Controls</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Controls</em>' attribute.
	 * @see #isControls()
	 * @generated
	 */
	void setControls(boolean value);

	/**
	 * Returns the value of the '<em><b>Indicator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indicator</em>' attribute.
	 * @see #setIndicator(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getCarousel_Indicator()
	 * @model
	 * @generated
	 */
	boolean isIndicator();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Carousel#isIndicator <em>Indicator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Indicator</em>' attribute.
	 * @see #isIndicator()
	 * @generated
	 */
	void setIndicator(boolean value);

	/**
	 * Returns the value of the '<em><b>Cross Fade</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cross Fade</em>' attribute.
	 * @see #setCrossFade(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getCarousel_CrossFade()
	 * @model
	 * @generated
	 */
	boolean isCrossFade();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Carousel#isCrossFade <em>Cross Fade</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cross Fade</em>' attribute.
	 * @see #isCrossFade()
	 * @generated
	 */
	void setCrossFade(boolean value);

	/**
	 * Returns the value of the '<em><b>Ride</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ride</em>' attribute.
	 * @see #setRide(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getCarousel_Ride()
	 * @model default="true"
	 * @generated
	 */
	boolean isRide();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Carousel#isRide <em>Ride</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ride</em>' attribute.
	 * @see #isRide()
	 * @generated
	 */
	void setRide(boolean value);

	/**
	 * Returns the value of the '<em><b>Interval</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interval</em>' attribute.
	 * @see #setInterval(String)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getCarousel_Interval()
	 * @model
	 * @generated
	 */
	String getInterval();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Carousel#getInterval <em>Interval</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interval</em>' attribute.
	 * @see #getInterval()
	 * @generated
	 */
	void setInterval(String value);

} // Carousel
