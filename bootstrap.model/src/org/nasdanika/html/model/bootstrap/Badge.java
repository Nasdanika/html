/**
 */
package org.nasdanika.html.model.bootstrap;

import org.nasdanika.html.bootstrap.Color;

import org.nasdanika.html.model.html.Container;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Badge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Badge#isPill <em>Pill</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Badge#getColor <em>Color</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getBadge()
 * @model
 * @generated
 */
public interface Badge extends BootstrapElement, Container {
	/**
	 * Returns the value of the '<em><b>Pill</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Pill doc...
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Pill</em>' attribute.
	 * @see #setPill(boolean)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getBadge_Pill()
	 * @model
	 * @generated
	 */
	boolean isPill();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Badge#isPill <em>Pill</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pill</em>' attribute.
	 * @see #isPill()
	 * @generated
	 */
	void setPill(boolean value);

	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(Color)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getBadge_Color()
	 * @model dataType="org.nasdanika.html.model.bootstrap.Color"
	 * @generated
	 */
	Color getColor();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Badge#getColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #getColor()
	 * @generated
	 */
	void setColor(Color value);

} // Badge
