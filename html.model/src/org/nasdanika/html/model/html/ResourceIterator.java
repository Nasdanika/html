/**
 */
package org.nasdanika.html.model.html;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Iterator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Resource iterator loads values from a resource identified by a URL.
 * 
 * For hierarchical data formats - Json, XML, Yaml - the location URL may contain a fragment. For XML fragment is evaluated as XPath, for Json and YAML as a key path with ``[<number>]`` for selection of a list element. 
 * E.g. ``cars/makes[3]`` selects the key ``cars`` and then the key ``makes`` which is expected to be a list. If it is a list then its 4th element is returned. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.ResourceIterator#getContentType <em>Content Type</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getResourceIterator()
 * @model
 * @generated
 */
public interface ResourceIterator extends Iterator, ResourceReference {
	/**
	 * Returns the value of the '<em><b>Content Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.nasdanika.html.model.html.ContentType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resource content type.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Content Type</em>' attribute.
	 * @see org.nasdanika.html.model.html.ContentType
	 * @see #setContentType(ContentType)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getResourceIterator_ContentType()
	 * @model
	 * @generated
	 */
	ContentType getContentType();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.ResourceIterator#getContentType <em>Content Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content Type</em>' attribute.
	 * @see org.nasdanika.html.model.html.ContentType
	 * @see #getContentType()
	 * @generated
	 */
	void setContentType(ContentType value);

} // ResourceIterator
