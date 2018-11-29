/**
 */
package org.nasdanika.html.model.app;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Navigation Action Activator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.NavigationActionActivator#getHref <em>Href</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getNavigationActionActivator()
 * @model superTypes="org.nasdanika.html.model.app.INavigationActionActivator org.nasdanika.html.model.app.ActionActivator"
 * @generated
 */
public interface NavigationActionActivator extends org.nasdanika.html.app.NavigationActionActivator, ActionActivator {
	/**
	 * Returns the value of the '<em><b>Href</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Href</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Href</em>' attribute.
	 * @see #setHref(String)
	 * @see org.nasdanika.html.model.app.AppPackage#getNavigationActionActivator_Href()
	 * @model
	 * @generated
	 */
	String getHref();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.NavigationActionActivator#getHref <em>Href</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Href</em>' attribute.
	 * @see #getHref()
	 * @generated
	 */
	void setHref(String value);

} // NavigationActionActivator
