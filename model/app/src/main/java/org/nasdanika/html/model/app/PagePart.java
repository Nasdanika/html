/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.model.bootstrap.BootstrapElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Page Part</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for page parts.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.PagePart#getItems <em>Items</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getPagePart()
 * @model abstract="true"
 * @generated
 */
public interface PagePart extends BootstrapElement {

	/**
	 * Returns the value of the '<em><b>Items</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Navigation items.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Items</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getPagePart_Items()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getItems();

} // PagePart
