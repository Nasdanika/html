/**
 */
package org.nasdanika.html.model.app;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Script Action Activator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.ScriptActionActivator#getCode <em>Code</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getScriptActionActivator()
 * @model abstract="true" superTypes="org.nasdanika.html.model.app.IScriptActionActivator org.nasdanika.html.model.app.ActionActivator"
 * @generated
 */
public interface ScriptActionActivator extends org.nasdanika.html.app.ScriptActionActivator, ActionActivator {
	/**
	 * Returns the value of the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Code</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Code</em>' attribute.
	 * @see #setCode(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getScriptActionActivator_Code()
	 * @model
	 * @generated
	 */
	String getCode();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.ScriptActionActivator#getCode <em>Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Code</em>' attribute.
	 * @see #getCode()
	 * @generated
	 */
	void setCode(String value);

} // ScriptActionActivator
