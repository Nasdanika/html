/**
 */
package org.nasdanika.html.model.html;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Binding Iterator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Iterator which retrieves values from the data context. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.DataBindingIterator#getDataBinding <em>Data Binding</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getDataBindingIterator()
 * @model
 * @generated
 */
public interface DataBindingIterator extends Iterator {
	/**
	 * Returns the value of the '<em><b>Data Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Data binding key.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Binding</em>' attribute.
	 * @see #setDataBinding(String)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getDataBindingIterator_DataBinding()
	 * @model
	 * @generated
	 */
	String getDataBinding();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.DataBindingIterator#getDataBinding <em>Data Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Binding</em>' attribute.
	 * @see #getDataBinding()
	 * @generated
	 */
	void setDataBinding(String value);

} // DataBindingIterator
