/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Column Width</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Column width for a given breakpoint.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.ColumnWidth#getBreakpoint <em>Breakpoint</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.ColumnWidth#getWidth <em>Width</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getColumnWidth()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='breakpoint width'"
 * @generated
 */
public interface ColumnWidth extends EObject {
	/**
	 * Returns the value of the '<em><b>Breakpoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Breakpoint.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Breakpoint</em>' attribute.
	 * @see #setBreakpoint(String)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getColumnWidth_Breakpoint()
	 * @model
	 * @generated
	 */
	String getBreakpoint();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.ColumnWidth#getBreakpoint <em>Breakpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Breakpoint</em>' attribute.
	 * @see #getBreakpoint()
	 * @generated
	 */
	void setBreakpoint(String value);

	/**
	 * Returns the value of the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Column width.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(String)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getColumnWidth_Width()
	 * @model
	 * @generated
	 */
	String getWidth();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.ColumnWidth#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(String value);

} // ColumnWidth
