/**
 */
package org.nasdanika.html.model.html.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.nasdanika.html.model.html.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class HtmlFactoryImpl extends EFactoryImpl implements HtmlFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static HtmlFactory init() {
		try {
			HtmlFactory theHtmlFactory = (HtmlFactory)EPackage.Registry.INSTANCE.getEFactory(HtmlPackage.eNS_URI);
			if (theHtmlFactory != null) {
				return theHtmlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new HtmlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HtmlFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case HtmlPackage.TAG: return createTag();
			case HtmlPackage.PAGE: return createPage();
			case HtmlPackage.STYLESHEET: return createStylesheet();
			case HtmlPackage.STYLESHEET_REFERENCE: return createStylesheetReference();
			case HtmlPackage.SCRIPT: return createScript();
			case HtmlPackage.SCRIPT_REFERENCE: return createScriptReference();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Tag createTag() {
		TagImpl tag = new TagImpl();
		return tag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Page createPage() {
		PageImpl page = new PageImpl();
		return page;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Stylesheet createStylesheet() {
		StylesheetImpl stylesheet = new StylesheetImpl();
		return stylesheet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StylesheetReference createStylesheetReference() {
		StylesheetReferenceImpl stylesheetReference = new StylesheetReferenceImpl();
		return stylesheetReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Script createScript() {
		ScriptImpl script = new ScriptImpl();
		return script;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScriptReference createScriptReference() {
		ScriptReferenceImpl scriptReference = new ScriptReferenceImpl();
		return scriptReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HtmlPackage getHtmlPackage() {
		return (HtmlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static HtmlPackage getPackage() {
		return HtmlPackage.eINSTANCE;
	}

} //HtmlFactoryImpl
