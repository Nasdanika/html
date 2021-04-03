/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Category</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Categories are used for grouping of its elements such as actions or properties. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.Category#getActions <em>Actions</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getCategory()
 * @model abstract="true"
 * @generated
 */
public interface Category extends Label {

	/**
	 * Returns the value of the '<em><b>Actions</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.app.Action}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actions</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getCategory_Actions()
	 * @model containment="true" required="true"
	 *        annotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	EList<Action> getActions();
} // Category
