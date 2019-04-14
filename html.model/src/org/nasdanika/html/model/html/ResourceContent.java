/**
 */
package org.nasdanika.html.model.html;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Content loaded from a URL. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.ResourceContent#isModel <em>Model</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getResourceContent()
 * @model
 * @generated
 */
public interface ResourceContent extends Content, ResourceReference {
	/**
	 * Returns the value of the '<em><b>Model</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If this attribute is set to true then resource is loaded as a model and then the model content is evaluated to produce HTML. 
	 * This option can be used when the target model is not available at the modeling time so ``ContentReference`` can be used or when the location URL contains interpolation tokens. 
	 * 
	 * If this attribute is set to false then the resource is loaded as text.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model</em>' attribute.
	 * @see #setModel(boolean)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getResourceContent_Model()
	 * @model
	 * @generated
	 */
	boolean isModel();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.ResourceContent#isModel <em>Model</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' attribute.
	 * @see #isModel()
	 * @generated
	 */
	void setModel(boolean value);

} // ResourceContent
