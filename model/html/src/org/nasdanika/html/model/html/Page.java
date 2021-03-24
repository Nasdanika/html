/**
 */
package org.nasdanika.html.model.html;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.nasdanika.ncore.NamedElement;

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
 *   <li>{@link org.nasdanika.html.model.html.Page#isFontAwesome <em>Font Awesome</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Page#isLineAwesome <em>Line Awesome</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Page#isJsTree <em>Js Tree</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Page#isGithubMarkdownCss <em>Github Markdown Css</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.Page#isHighlightJs <em>Highlight Js</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.html.model.html.HtmlPackage#getPage()
 * @model
 * @generated
 */
public interface Page extends NamedElement {
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
	 * Builders operate on an instance of ``org.nasdanika.html.HTMLPage`` created by the the page element.
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
	 * Returns the value of the '<em><b>Font Awesome</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If this attribute is set to true [Font Awesome](https://fontawesome.com/) CDN stylesheet reference is added to the head.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Font Awesome</em>' attribute.
	 * @see #setFontAwesome(boolean)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_FontAwesome()
	 * @model
	 * @generated
	 */
	boolean isFontAwesome();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.Page#isFontAwesome <em>Font Awesome</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Font Awesome</em>' attribute.
	 * @see #isFontAwesome()
	 * @generated
	 */
	void setFontAwesome(boolean value);

	/**
	 * Returns the value of the '<em><b>Line Awesome</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If this attribute is set to true [Line Awesome](https://icons8.com/line-awesome/) CDN stylesheet reference is added to the head.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Line Awesome</em>' attribute.
	 * @see #setLineAwesome(boolean)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_LineAwesome()
	 * @model
	 * @generated
	 */
	boolean isLineAwesome();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.Page#isLineAwesome <em>Line Awesome</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Line Awesome</em>' attribute.
	 * @see #isLineAwesome()
	 * @generated
	 */
	void setLineAwesome(boolean value);

	/**
	 * Returns the value of the '<em><b>Js Tree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If this attribute is set to true [jsTree](https://www.jstree.com/) CDN script and stylesheet references are added to the head.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Js Tree</em>' attribute.
	 * @see #setJsTree(boolean)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_JsTree()
	 * @model
	 * @generated
	 */
	boolean isJsTree();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.Page#isJsTree <em>Js Tree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Js Tree</em>' attribute.
	 * @see #isJsTree()
	 * @generated
	 */
	void setJsTree(boolean value);

	/**
	 * Returns the value of the '<em><b>Github Markdown Css</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If this attribute is set to true [GitHub Markdown CSS](https://github.com/sindresorhus/github-markdown-css) CDN stylesheet reference is added to the head.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Github Markdown Css</em>' attribute.
	 * @see #setGithubMarkdownCss(boolean)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_GithubMarkdownCss()
	 * @model
	 * @generated
	 */
	boolean isGithubMarkdownCss();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.Page#isGithubMarkdownCss <em>Github Markdown Css</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Github Markdown Css</em>' attribute.
	 * @see #isGithubMarkdownCss()
	 * @generated
	 */
	void setGithubMarkdownCss(boolean value);

	/**
	 * Returns the value of the '<em><b>Highlight Js</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If this attribute is set to true [highlight.js](https://highlightjs.org/) CDN script and stylesheet references are added to the head as well as the initialization script in order to provide syntax highlighting in markdown fenced blocks.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Highlight Js</em>' attribute.
	 * @see #setHighlightJs(boolean)
	 * @see org.nasdanika.html.model.html.HtmlPackage#getPage_HighlightJs()
	 * @model
	 * @generated
	 */
	boolean isHighlightJs();

	/**
	 * Sets the value of the '{@link org.nasdanika.html.model.html.Page#isHighlightJs <em>Highlight Js</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Highlight Js</em>' attribute.
	 * @see #isHighlightJs()
	 * @generated
	 */
	void setHighlightJs(boolean value);

} // Page
