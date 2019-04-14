/**
 */
package org.nasdanika.html.model.html;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Iterator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Iterator produces zero or more values. For each value returned by the iterator a new context is created and generation of the iterator's containing element is performed in the value context.
 * Iterators can also be used for conditional logic as an alternative to ``switch`` - if iterator value is ``null`` or ``false`` the content is not evaluated. 
 * If it is ``true`` it is executed once in the parent context.
 * If the value is not iterable the content is evaluated once with a new context constructed from the value.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getIterator()
 * @model abstract="true"
 * @generated
 */
public interface Iterator extends ModelElement {
} // Iterator
