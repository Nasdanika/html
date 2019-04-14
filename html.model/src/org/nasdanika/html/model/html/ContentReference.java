/**
 */
package org.nasdanika.html.model.html;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Content Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Refernces content in the same or different model. Content references may be used to organize UI model into several model files owned by different people/teams and to reuse model elements in multiple places. Such elements may contain data binding and interpolations and be used as templates.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.ContentReference#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getContentReference()
 * @model
 * @generated
 */
public interface ContentReference extends Content {
	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Content references
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Content)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getContentReference_Target()
	 * @model required="true"
	 * @generated
	 */
	Content getTarget();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.ContentReference#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Content value);

} // ContentReference
