/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Page Part</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for page parts.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.PagePart#getContent <em>Content</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getPagePart()
 * @model
 * @generated
 */
public interface PagePart extends org.nasdanika.html.model.bootstrap.BootstrapElement {
	/**
	 * Returns the value of the '<em><b>Content</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Custom content to display in the part.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Content</em>' containment reference list.
	 * @see org.nasdanika.html.model.app.AppPackage#getPagePart_Content()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getContent();

} // PagePart
