/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Card</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Bootstrap [card](https://getbootstrap.com/docs/4.0/components/card/).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Card#getHeader <em>Header</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Card#getBody <em>Body</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Card#getFooter <em>Footer</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getCard()
 * @model
 * @generated
 */
public interface Card extends Div {
	/**
	 * Returns the value of the '<em><b>Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Card header.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Header</em>' containment reference.
	 * @see #setHeader(Div)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getCard_Header()
	 * @model containment="true"
	 * @generated
	 */
	Div getHeader();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Card#getHeader <em>Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Header</em>' containment reference.
	 * @see #getHeader()
	 * @generated
	 */
	void setHeader(Div value);

	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.bootstrap.Div}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Card body.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Body</em>' containment reference list.
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getCard_Body()
	 * @model containment="true"
	 * @generated
	 */
	EList<Div> getBody();

	/**
	 * Returns the value of the '<em><b>Footer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Card footer.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Footer</em>' containment reference.
	 * @see #setFooter(Div)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getCard_Footer()
	 * @model containment="true"
	 * @generated
	 */
	Div getFooter();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Card#getFooter <em>Footer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Footer</em>' containment reference.
	 * @see #getFooter()
	 * @generated
	 */
	void setFooter(Div value);

} // Card
