/**
 */
package org.nasdanika.html.model.html;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * HTML page containing other content.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.Page#getLang <em>Lang</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Page#getTitle <em>Title</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Page#getStylesheets <em>Stylesheets</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Page#getScripts <em>Scripts</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Page#getHead <em>Head</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Page#getBody <em>Body</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Page#getFacets <em>Facets</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getPage()
 * @model
 * @generated
 */
public interface Page extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Lang</b></em>' attribute.
	 * The default value is <code>"en"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Page language - ``lang`` attribute. Defaults to ``en``
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lang</em>' attribute.
	 * @see #setLang(String)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_Lang()
	 * @model default="en" required="true"
	 * @generated
	 */
	String getLang();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.Page#getLang <em>Lang</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lang</em>' attribute.
	 * @see #getLang()
	 * @generated
	 */
	void setLang(String value);

	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Page title.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_Title()
	 * @model
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.Page#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Stylesheets</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Stylesheet URL's to generate ``<link rel="stylesheet" href="<styelsheet url>" />`` elements in the page head. Advanced stylesheet declarations can be put to the ``head`` attributes.
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
	 * Script URL's to generate ``<script type="text/javascript" src="<script url>"></script>`` elements in the page head. Advanced script declarations can be put to the ``head`` attributes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Scripts</em>' attribute list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_Scripts()
	 * @model
	 * @generated
	 */
	EList<String> getScripts();

	/**
	 * Returns the value of the '<em><b>Head</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Content to be put to the head element, such as meta tags, scripts and stylesheets. Simple script and stylesheet references may added using ``scripts`` and ``stylesheets`` attributes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Head</em>' attribute.
	 * @see #setHead(String)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_Head()
	 * @model
	 * @generated
	 */
	String getHead();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.Page#getHead <em>Head</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Head</em>' attribute.
	 * @see #getHead()
	 * @generated
	 */
	void setHead(String value);

	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.html.Content}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Page body content.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Body</em>' containment reference list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_Body()
	 * @model containment="true"
	 * @generated
	 */
	EList<Content> getBody();

	/**
	 * Returns the value of the '<em><b>Facets</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.html.model.html.Facet}<code>&lt;org.nasdanika.html.model.html.Page&gt;</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Facets customize page generation, e.g. by adding scripts and stylesheets for frameworks/libraries such as Bootstrap, jsTree, or FontAwesome.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Facets</em>' containment reference list.
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_Facets()
	 * @model containment="true"
	 * @generated
	 */
	EList<Facet<Page>> getFacets();

} // Page
