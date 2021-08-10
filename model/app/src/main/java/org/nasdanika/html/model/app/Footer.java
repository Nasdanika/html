/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Footer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Footer is displayed below the navigation and content panels.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.Footer#getItems <em>Items</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getFooter()
 * @model
 * @generated
 */
public interface Footer extends PagePart {
	/**
	 * Returns the value of the '<em><b>Items</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.bootstrap.BootstrapElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Footer navigation items.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Items</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getFooter_Items()
	 * @model containment="true"
	 * @generated
	 */
	EList<org.nasdanika.html.model.bootstrap.BootstrapElement> getItems();

} // Footer
