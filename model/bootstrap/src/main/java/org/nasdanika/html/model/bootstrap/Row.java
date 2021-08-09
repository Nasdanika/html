/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Row</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Container rows
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Row#getColumns <em>Columns</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getRow()
 * @model
 * @generated
 */
public interface Row extends BootstrapElement {
	/**
	 * Returns the value of the '<em><b>Columns</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.bootstrap.Column}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Row columns.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Columns</em>' containment reference list.
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getRow_Columns()
	 * @model containment="true"
	 * @generated
	 */
	EList<Column> getColumns();

} // Row
