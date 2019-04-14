/**
 */
package org.nasdanika.html.model.html;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for content.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.Content#getIterator <em>Iterator</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Content#getFacets <em>Facets</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Content#getDataBinding <em>Data Binding</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getContent()
 * @model abstract="true"
 * @generated
 */
public interface Content extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Iterator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Iterator repeats content zero or more times. For each iteration the iterator sets a new context for the content to retrieve data from.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Iterator</em>' containment reference.
	 * @see #setIterator(Iterator)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getContent_Iterator()
	 * @model containment="true"
	 * @generated
	 */
	Iterator getIterator();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.Content#getIterator <em>Iterator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Iterator</em>' containment reference.
	 * @see #getIterator()
	 * @generated
	 */
	void setIterator(Iterator value);

	/**
	 * Returns the value of the '<em><b>Facets</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.html.Facet}<code>&lt;org.nasdanika.html.model.html.Content&gt;</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Content facet. For example, a data binding framework facet.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Facets</em>' containment reference list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getContent_Facets()
	 * @model containment="true"
	 * @generated
	 */
	EList<Facet<Content>> getFacets();

	/**
	 * Returns the value of the '<em><b>Data Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Content data binding key. Details of data binding are content type and data value specific and vary between different elements. 
	 * 
	 * For example, for ``select`` if data value is String (text) it will be used for for value, if it is a function or a map then it will be used to retrieve value and options.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Binding</em>' attribute.
	 * @see #setDataBinding(String)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getContent_DataBinding()
	 * @model
	 * @generated
	 */
	String getDataBinding();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.Content#getDataBinding <em>Data Binding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Binding</em>' attribute.
	 * @see #getDataBinding()
	 * @generated
	 */
	void setDataBinding(String value);

} // Content
