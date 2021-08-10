/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Content Action Group Item</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Action Group Item with content. Click on the item shows the content in the content container.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.ContentActionGroupItem#getContent <em>Content</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getContentActionGroupItem()
 * @model
 * @generated
 */
public interface ContentActionGroupItem extends ActionGroupItem {

	/**
	 * Returns the value of the '<em><b>Content</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Container content. 
	 * 
	 * Content elements are adapted to ${javadoc/org.nasdanika.common.SupplierFactory} for generation of HTML content.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Content</em>' containment reference list.
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getContentActionGroupItem_Content()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getContent();
} // ContentActionGroupItem
