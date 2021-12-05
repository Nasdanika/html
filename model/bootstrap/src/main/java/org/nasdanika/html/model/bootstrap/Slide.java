/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Slide</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Slide#getInterval <em>Interval</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Slide#getCaptions <em>Captions</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getSlide()
 * @model
 * @generated
 */
public interface Slide extends Div {
	/**
	 * Returns the value of the '<em><b>Interval</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interval</em>' attribute.
	 * @see #setInterval(Integer)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getSlide_Interval()
	 * @model
	 * @generated
	 */
	Integer getInterval();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Slide#getInterval <em>Interval</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interval</em>' attribute.
	 * @see #getInterval()
	 * @generated
	 */
	void setInterval(Integer value);

	/**
	 * Returns the value of the '<em><b>Captions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Captions</em>' containment reference list.
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getSlide_Captions()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getCaptions();

} // Slide
