/**
 */
package org.nasdanika.html.model.html;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Facet</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Model element facets customize elements for a specific purpose. For example a data binding framework facet would provide a convenient way to set data binding attributes.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getFacet()
 * @model abstract="true"
 * @generated
 */
public interface Facet<T> extends ModelElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This method is used by the editor to select facets applicable for a particular model element.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean isFacetFor(T target);

} // Facet
