/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Row Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for containers of rows - table, header, body, footer.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.TableRowContainer#getRows <em>Rows</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableRowContainer()
 * @model abstract="true"
 * @generated
 */
public interface TableRowContainer extends BootstrapElement {
	/**
	 * Returns the value of the '<em><b>Rows</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.bootstrap.TableRow}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Table rows.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rows</em>' containment reference list.
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getTableRowContainer_Rows()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	EList<TableRow> getRows();

} // TableRowContainer
