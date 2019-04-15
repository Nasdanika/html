/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>List Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.ListGroup#getItems <em>Items</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getListGroup()
 * @model
 * @generated
 */
public interface ListGroup extends BootstrapElement {
	/**
	 * Returns the value of the '<em><b>Items</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.bootstrap.ListGroupItem}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Items</em>' containment reference list.
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getListGroup_Items()
	 * @model containment="true"
	 * @generated
	 */
	EList<ListGroupItem> getItems();

} // ListGroup
