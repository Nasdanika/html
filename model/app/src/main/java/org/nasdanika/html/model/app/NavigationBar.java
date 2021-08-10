/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Navigation Bar</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Navigation bar has a brand and navigation items.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.NavigationBar#getBrand <em>Brand</em>}</li>
 *   <li>{@link org.nasdanika.html.model.app.NavigationBar#getItems <em>Items</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getNavigationBar()
 * @model
 * @generated
 */
public interface NavigationBar extends PagePart {
	/**
	 * Returns the value of the '<em><b>Brand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Brand label displayed on the left.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Brand</em>' containment reference.
	 * @see #setBrand(Label)
	 * @see org.nasdanika.html.model.app.AppPackage#getNavigationBar_Brand()
	 * @model containment="true"
	 * @generated
	 */
	Label getBrand();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.NavigationBar#getBrand <em>Brand</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Brand</em>' containment reference.
	 * @see #getBrand()
	 * @generated
	 */
	void setBrand(Label value);

	/**
	 * Returns the value of the '<em><b>Items</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.bootstrap.BootstrapElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Navigation items. Typically links, but may contain custom content.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Items</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getNavigationBar_Items()
	 * @model containment="true"
	 * @generated
	 */
	EList<org.nasdanika.html.model.bootstrap.BootstrapElement> getItems();

} // NavigationBar
