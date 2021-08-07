/**
 */
package org.nasdanika.html.model.html.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.exec.impl.ModelElementImpl;

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
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#getHead <em>Head</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#getBuilders <em>Builders</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#getLanguage <em>Language</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#isFontAwesome <em>Font Awesome</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#isLineAwesome <em>Line Awesome</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#isJsTree <em>Js Tree</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#isGithubMarkdownCss <em>Github Markdown Css</em>}</li>
 *   <li>{@link org.nasdanika.html.model.html.impl.PageImpl#isHighlightJs <em>Highlight Js</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PageImpl extends ModelElementImpl implements Page {
	/**
	 * The default value of the '{@link #getLanguage() <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLanguage()
	 * @generated
	 * @ordered
	 */
	protected static final String LANGUAGE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isFontAwesome() <em>Font Awesome</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFontAwesome()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FONT_AWESOME_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isLineAwesome() <em>Line Awesome</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isLineAwesome()
	 * @generated
	 * @ordered
	 */
	protected static final boolean LINE_AWESOME_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isJsTree() <em>Js Tree</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isJsTree()
	 * @generated
	 * @ordered
	 */
	protected static final boolean JS_TREE_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isGithubMarkdownCss() <em>Github Markdown Css</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isGithubMarkdownCss()
	 * @generated
	 * @ordered
	 */
	protected static final boolean GITHUB_MARKDOWN_CSS_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isHighlightJs() <em>Highlight Js</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHighlightJs()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HIGHLIGHT_JS_EDEFAULT = false;

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
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getHead() {
		return (EList<EObject>)eDynamicGet(HtmlPackage.PAGE__HEAD, HtmlPackage.Literals.PAGE__HEAD, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getBody() {
		return (EList<EObject>)eDynamicGet(HtmlPackage.PAGE__BODY, HtmlPackage.Literals.PAGE__BODY, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getBuilders() {
		return (EList<EObject>)eDynamicGet(HtmlPackage.PAGE__BUILDERS, HtmlPackage.Literals.PAGE__BUILDERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLanguage() {
		return (String)eDynamicGet(HtmlPackage.PAGE__LANGUAGE, HtmlPackage.Literals.PAGE__LANGUAGE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLanguage(String newLanguage) {
		eDynamicSet(HtmlPackage.PAGE__LANGUAGE, HtmlPackage.Literals.PAGE__LANGUAGE, newLanguage);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return (String)eDynamicGet(HtmlPackage.PAGE__NAME, HtmlPackage.Literals.PAGE__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		eDynamicSet(HtmlPackage.PAGE__NAME, HtmlPackage.Literals.PAGE__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFontAwesome() {
		return (Boolean)eDynamicGet(HtmlPackage.PAGE__FONT_AWESOME, HtmlPackage.Literals.PAGE__FONT_AWESOME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFontAwesome(boolean newFontAwesome) {
		eDynamicSet(HtmlPackage.PAGE__FONT_AWESOME, HtmlPackage.Literals.PAGE__FONT_AWESOME, newFontAwesome);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isLineAwesome() {
		return (Boolean)eDynamicGet(HtmlPackage.PAGE__LINE_AWESOME, HtmlPackage.Literals.PAGE__LINE_AWESOME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLineAwesome(boolean newLineAwesome) {
		eDynamicSet(HtmlPackage.PAGE__LINE_AWESOME, HtmlPackage.Literals.PAGE__LINE_AWESOME, newLineAwesome);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isJsTree() {
		return (Boolean)eDynamicGet(HtmlPackage.PAGE__JS_TREE, HtmlPackage.Literals.PAGE__JS_TREE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setJsTree(boolean newJsTree) {
		eDynamicSet(HtmlPackage.PAGE__JS_TREE, HtmlPackage.Literals.PAGE__JS_TREE, newJsTree);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isGithubMarkdownCss() {
		return (Boolean)eDynamicGet(HtmlPackage.PAGE__GITHUB_MARKDOWN_CSS, HtmlPackage.Literals.PAGE__GITHUB_MARKDOWN_CSS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setGithubMarkdownCss(boolean newGithubMarkdownCss) {
		eDynamicSet(HtmlPackage.PAGE__GITHUB_MARKDOWN_CSS, HtmlPackage.Literals.PAGE__GITHUB_MARKDOWN_CSS, newGithubMarkdownCss);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isHighlightJs() {
		return (Boolean)eDynamicGet(HtmlPackage.PAGE__HIGHLIGHT_JS, HtmlPackage.Literals.PAGE__HIGHLIGHT_JS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHighlightJs(boolean newHighlightJs) {
		eDynamicSet(HtmlPackage.PAGE__HIGHLIGHT_JS, HtmlPackage.Literals.PAGE__HIGHLIGHT_JS, newHighlightJs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case HtmlPackage.PAGE__HEAD:
				return ((InternalEList<?>)getHead()).basicRemove(otherEnd, msgs);
			case HtmlPackage.PAGE__BODY:
				return ((InternalEList<?>)getBody()).basicRemove(otherEnd, msgs);
			case HtmlPackage.PAGE__BUILDERS:
				return ((InternalEList<?>)getBuilders()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case HtmlPackage.PAGE__HEAD:
				return getHead();
			case HtmlPackage.PAGE__BODY:
				return getBody();
			case HtmlPackage.PAGE__BUILDERS:
				return getBuilders();
			case HtmlPackage.PAGE__LANGUAGE:
				return getLanguage();
			case HtmlPackage.PAGE__NAME:
				return getName();
			case HtmlPackage.PAGE__FONT_AWESOME:
				return isFontAwesome();
			case HtmlPackage.PAGE__LINE_AWESOME:
				return isLineAwesome();
			case HtmlPackage.PAGE__JS_TREE:
				return isJsTree();
			case HtmlPackage.PAGE__GITHUB_MARKDOWN_CSS:
				return isGithubMarkdownCss();
			case HtmlPackage.PAGE__HIGHLIGHT_JS:
				return isHighlightJs();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case HtmlPackage.PAGE__HEAD:
				getHead().clear();
				getHead().addAll((Collection<? extends EObject>)newValue);
				return;
			case HtmlPackage.PAGE__BODY:
				getBody().clear();
				getBody().addAll((Collection<? extends EObject>)newValue);
				return;
			case HtmlPackage.PAGE__BUILDERS:
				getBuilders().clear();
				getBuilders().addAll((Collection<? extends EObject>)newValue);
				return;
			case HtmlPackage.PAGE__LANGUAGE:
				setLanguage((String)newValue);
				return;
			case HtmlPackage.PAGE__NAME:
				setName((String)newValue);
				return;
			case HtmlPackage.PAGE__FONT_AWESOME:
				setFontAwesome((Boolean)newValue);
				return;
			case HtmlPackage.PAGE__LINE_AWESOME:
				setLineAwesome((Boolean)newValue);
				return;
			case HtmlPackage.PAGE__JS_TREE:
				setJsTree((Boolean)newValue);
				return;
			case HtmlPackage.PAGE__GITHUB_MARKDOWN_CSS:
				setGithubMarkdownCss((Boolean)newValue);
				return;
			case HtmlPackage.PAGE__HIGHLIGHT_JS:
				setHighlightJs((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case HtmlPackage.PAGE__HEAD:
				getHead().clear();
				return;
			case HtmlPackage.PAGE__BODY:
				getBody().clear();
				return;
			case HtmlPackage.PAGE__BUILDERS:
				getBuilders().clear();
				return;
			case HtmlPackage.PAGE__LANGUAGE:
				setLanguage(LANGUAGE_EDEFAULT);
				return;
			case HtmlPackage.PAGE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case HtmlPackage.PAGE__FONT_AWESOME:
				setFontAwesome(FONT_AWESOME_EDEFAULT);
				return;
			case HtmlPackage.PAGE__LINE_AWESOME:
				setLineAwesome(LINE_AWESOME_EDEFAULT);
				return;
			case HtmlPackage.PAGE__JS_TREE:
				setJsTree(JS_TREE_EDEFAULT);
				return;
			case HtmlPackage.PAGE__GITHUB_MARKDOWN_CSS:
				setGithubMarkdownCss(GITHUB_MARKDOWN_CSS_EDEFAULT);
				return;
			case HtmlPackage.PAGE__HIGHLIGHT_JS:
				setHighlightJs(HIGHLIGHT_JS_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case HtmlPackage.PAGE__HEAD:
				return !getHead().isEmpty();
			case HtmlPackage.PAGE__BODY:
				return !getBody().isEmpty();
			case HtmlPackage.PAGE__BUILDERS:
				return !getBuilders().isEmpty();
			case HtmlPackage.PAGE__LANGUAGE:
				return LANGUAGE_EDEFAULT == null ? getLanguage() != null : !LANGUAGE_EDEFAULT.equals(getLanguage());
			case HtmlPackage.PAGE__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
			case HtmlPackage.PAGE__FONT_AWESOME:
				return isFontAwesome() != FONT_AWESOME_EDEFAULT;
			case HtmlPackage.PAGE__LINE_AWESOME:
				return isLineAwesome() != LINE_AWESOME_EDEFAULT;
			case HtmlPackage.PAGE__JS_TREE:
				return isJsTree() != JS_TREE_EDEFAULT;
			case HtmlPackage.PAGE__GITHUB_MARKDOWN_CSS:
				return isGithubMarkdownCss() != GITHUB_MARKDOWN_CSS_EDEFAULT;
			case HtmlPackage.PAGE__HIGHLIGHT_JS:
				return isHighlightJs() != HIGHLIGHT_JS_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //PageImpl
