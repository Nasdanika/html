/**
 */
package org.nasdanika.html.model.bootstrap;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.bootstrap.Breakpoint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Float</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Defines element floating - left or right - for a given breakpoint.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Float#getSide <em>Side</em>}</li>
 *   <li>{@link org.nasdanika.html.model.bootstrap.Float#getBreakpoint <em>Breakpoint</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getFloat()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='attributes'"
 * @generated
 */
public interface Float extends EObject {
	/**
	 * Returns the value of the '<em><b>Side</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Side to float to. ``left``, ``right``, or ``none``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Side</em>' attribute.
	 * @see #setSide(String)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getFloat_Side()
	 * @model required="true"
	 *        annotation="urn:org.nasdanika default-feature='true'"
	 * @generated
	 */
	String getSide();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Float#getSide <em>Side</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Side</em>' attribute.
	 * @see #getSide()
	 * @generated
	 */
	void setSide(String value);

	/**
	 * Returns the value of the '<em><b>Breakpoint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Breakpoint.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Breakpoint</em>' attribute.
	 * @see #setBreakpoint(Breakpoint)
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#getFloat_Breakpoint()
	 * @model dataType="org.nasdanika.html.model.bootstrap.Breakpoint"
	 * @generated
	 */
	Breakpoint getBreakpoint();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.bootstrap.Float#getBreakpoint <em>Breakpoint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Breakpoint</em>' attribute.
	 * @see #getBreakpoint()
	 * @generated
	 */
	void setBreakpoint(Breakpoint value);

} // Float
