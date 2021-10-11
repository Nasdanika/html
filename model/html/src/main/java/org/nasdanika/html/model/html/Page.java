/**
 */
package org.nasdanika.html.model.html;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * HTML page. The name attribute is output as title tag in the head.
 * 
 * [Overview video](https://www.youtube.com/watch?v=S28UszI-2g8) - in Russian.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.Page#getHead <em>Head</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Page#getBody <em>Body</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Page#getBuilders <em>Builders</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Page#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Page#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Page#getStylesheets <em>Stylesheets</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Page#getScripts <em>Scripts</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getPage()
 * @model
 * @generated
 */
public interface Page extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Head</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Head content.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Head</em>' containment reference list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_Head()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getHead();

	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Body content.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Body</em>' containment reference list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_Body()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getBody();

	/**
	 * Returns the value of the '<em><b>Builders</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Builders operate on an instance of ``org.nasdanika.html.HTMLPage`` created by the the page element. Builders shall be adaptable to ``org.nasdanika.common.Consumer``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Builders</em>' containment reference list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_Builders()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getBuilders();

	/**
	 * Returns the value of the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Page language - ``lang`` attribute.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Language</em>' attribute.
	 * @see #setLanguage(String)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_Language()
	 * @model
	 * @generated
	 */
	String getLanguage();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.Page#getLanguage <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Language</em>' attribute.
	 * @see #getLanguage()
	 * @generated
	 */
	void setLanguage(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.Page#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Stylesheets</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Stylesheet URL's
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Stylesheets</em>' attribute list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_Stylesheets()
	 * @model
	 * @generated
	 */
	EList<String> getStylesheets();

	/**
	 * Returns the value of the '<em><b>Scripts</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Script URL's
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Scripts</em>' attribute list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_Scripts()
	 * @model
	 * @generated
	 */
	EList<String> getScripts();

} // Page
