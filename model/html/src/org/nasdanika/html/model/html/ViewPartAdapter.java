/**
 */
package org.nasdanika.html.model.html;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>View Part Adapter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * View part adapter delegates generation of HTML to an adapter created by a named adapter factory.
 * The factory shall be for ${javadoc/org.nasdanika.html.app.ViewPart$Supplier$Factory} type.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.ViewPartAdapter#getFactory <em>Factory</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getViewPartAdapter()
 * @model
 * @generated
 */
public interface ViewPartAdapter extends HtmlElement {
	/**
	 * Returns the value of the '<em><b>Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Named adapter factory.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Factory</em>' attribute.
	 * @see #setFactory(String)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getViewPartAdapter_Factory()
	 * @model required="true"
	 * @generated
	 */
	String getFactory();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.ViewPartAdapter#getFactory <em>Factory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Factory</em>' attribute.
	 * @see #getFactory()
	 * @generated
	 */
	void setFactory(String value);

} // ViewPartAdapter
