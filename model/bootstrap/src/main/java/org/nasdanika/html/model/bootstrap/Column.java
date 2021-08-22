/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Container row column.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Column#getWidth <em>Width</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getColumn()
 * @model
 * @generated
 */
public interface Column extends BootstrapElement {
	/**
	 * Returns the value of the '<em><b>Width</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.bootstrap.ColumnWidth}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Column widths for different breakpoints.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Width</em>' containment reference list.
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getColumn_Width()
	 * @model containment="true"
	 * @generated
	 */
	EList<ColumnWidth> getWidth();

} // Column
