/**
 */
package org.nasdanika.html.model.app;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Header</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Page header has a title on the left and a navigation bar on the right.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.app.Header#getTitle <em>Title</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.app.AppPackage#getHeader()
 * @model
 * @generated
 */
public interface Header extends PagePart {
	/**
	 * Returns the value of the '<em><b>Title</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Header title displayed on the left.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Title</em>' containment reference.
	 * @see #setTitle(Label)
	 * @see org.nasdanika.html.model.app.AppPackage#getHeader_Title()
	 * @model containment="true"
	 * @generated
	 */
	Label getTitle();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.app.Header#getTitle <em>Title</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' containment reference.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(Label value);

} // Header
