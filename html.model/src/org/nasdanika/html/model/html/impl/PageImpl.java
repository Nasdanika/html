/**
 */
package org.nasdanika.html.model.html.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.html.model.html.Content;
import org.nasdanika.html.model.html.Facet;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.html.model.html.Page;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#getLang <em>Lang</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#getStylesheets <em>Stylesheets</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#getScripts <em>Scripts</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#getHead <em>Head</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#getFacets <em>Facets</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PageImpl extends ModelElementImpl implements Page {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return HtmlPackage.Literals.PAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLang() {
		return (String)eGet(HtmlPackage.Literals.PAGE__LANG, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLang(String newLang) {
		eSet(HtmlPackage.Literals.PAGE__LANG, newLang);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTitle() {
		return (String)eGet(HtmlPackage.Literals.PAGE__TITLE, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTitle(String newTitle) {
		eSet(HtmlPackage.Literals.PAGE__TITLE, newTitle);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getStylesheets() {
		return (EList<String>)eGet(HtmlPackage.Literals.PAGE__STYLESHEETS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getScripts() {
		return (EList<String>)eGet(HtmlPackage.Literals.PAGE__SCRIPTS, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getHead() {
		return (String)eGet(HtmlPackage.Literals.PAGE__HEAD, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHead(String newHead) {
		eSet(HtmlPackage.Literals.PAGE__HEAD, newHead);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Content> getBody() {
		return (EList<Content>)eGet(HtmlPackage.Literals.PAGE__BODY, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Facet<Page>> getFacets() {
		return (EList<Facet<Page>>)eGet(HtmlPackage.Literals.PAGE__FACETS, true);
	}

} //PageImpl
