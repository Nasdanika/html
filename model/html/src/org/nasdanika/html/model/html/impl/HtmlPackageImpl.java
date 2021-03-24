/**
 */
package org.nasdanika.html.model.html.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.html.model.html.ContentTag;
import org.nasdanika.html.model.html.HtmlElement;
import org.nasdanika.html.model.html.HtmlFactory;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.html.model.html.Page;
import org.nasdanika.html.model.html.Script;
import org.nasdanika.html.model.html.ScriptReference;
import org.nasdanika.html.model.html.ScriptResource;
import org.nasdanika.html.model.html.Stylesheet;
import org.nasdanika.html.model.html.StylesheetReference;
import org.nasdanika.html.model.html.StylesheetResource;
import org.nasdanika.html.model.html.Tag;
import org.nasdanika.html.model.html.ViewPartAdapter;

import org.nasdanika.html.model.html.util.HtmlValidator;

import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class HtmlPackageImpl extends EPackageImpl implements HtmlPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass htmlElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tagEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contentTagEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stylesheetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stylesheetResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stylesheetReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scriptEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scriptResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scriptReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass viewPartAdapterEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.nasdanika.html.model.html.HtmlPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private HtmlPackageImpl() {
		super(eNS_URI, HtmlFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link HtmlPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static HtmlPackage init() {
		if (isInited) return (HtmlPackage)EPackage.Registry.INSTANCE.getEPackage(HtmlPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredHtmlPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		HtmlPackageImpl theHtmlPackage = registeredHtmlPackage instanceof HtmlPackageImpl ? (HtmlPackageImpl)registeredHtmlPackage : new HtmlPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		NcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theHtmlPackage.createPackageContents();

		// Initialize created meta-data
		theHtmlPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theHtmlPackage,
			 new EValidator.Descriptor() {
				 @Override
				 public EValidator getEValidator() {
					 return HtmlValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theHtmlPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(HtmlPackage.eNS_URI, theHtmlPackage);
		return theHtmlPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHtmlElement() {
		return htmlElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContainer() {
		return containerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContainer_Content() {
		return (EReference)containerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContainer_MarkdownContent() {
		return (EAttribute)containerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTag() {
		return tagEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTag_Name() {
		return (EAttribute)tagEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTag_Attributes() {
		return (EReference)tagEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContentTag() {
		return contentTagEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPage() {
		return pageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPage_Head() {
		return (EReference)pageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPage_Body() {
		return (EReference)pageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPage_Builders() {
		return (EReference)pageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPage_Language() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPage_FontAwesome() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPage_LineAwesome() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPage_JsTree() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPage_GithubMarkdownCss() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPage_HighlightJs() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStylesheet() {
		return stylesheetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStylesheet_Code() {
		return (EAttribute)stylesheetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStylesheetResource() {
		return stylesheetResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStylesheetResource_Location() {
		return (EAttribute)stylesheetResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStylesheetReference() {
		return stylesheetReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStylesheetReference_Href() {
		return (EAttribute)stylesheetReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScript() {
		return scriptEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScript_Code() {
		return (EAttribute)scriptEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScriptResource() {
		return scriptResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScriptResource_Location() {
		return (EAttribute)scriptResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScriptReference() {
		return scriptReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScriptReference_Src() {
		return (EAttribute)scriptReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getViewPartAdapter() {
		return viewPartAdapterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getViewPartAdapter_Factory() {
		return (EAttribute)viewPartAdapterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public HtmlFactory getHtmlFactory() {
		return (HtmlFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		htmlElementEClass = createEClass(HTML_ELEMENT);

		containerEClass = createEClass(CONTAINER);
		createEReference(containerEClass, CONTAINER__CONTENT);
		createEAttribute(containerEClass, CONTAINER__MARKDOWN_CONTENT);

		tagEClass = createEClass(TAG);
		createEAttribute(tagEClass, TAG__NAME);
		createEReference(tagEClass, TAG__ATTRIBUTES);

		contentTagEClass = createEClass(CONTENT_TAG);

		pageEClass = createEClass(PAGE);
		createEReference(pageEClass, PAGE__HEAD);
		createEReference(pageEClass, PAGE__BODY);
		createEReference(pageEClass, PAGE__BUILDERS);
		createEAttribute(pageEClass, PAGE__LANGUAGE);
		createEAttribute(pageEClass, PAGE__FONT_AWESOME);
		createEAttribute(pageEClass, PAGE__LINE_AWESOME);
		createEAttribute(pageEClass, PAGE__JS_TREE);
		createEAttribute(pageEClass, PAGE__GITHUB_MARKDOWN_CSS);
		createEAttribute(pageEClass, PAGE__HIGHLIGHT_JS);

		stylesheetEClass = createEClass(STYLESHEET);
		createEAttribute(stylesheetEClass, STYLESHEET__CODE);

		stylesheetResourceEClass = createEClass(STYLESHEET_RESOURCE);
		createEAttribute(stylesheetResourceEClass, STYLESHEET_RESOURCE__LOCATION);

		stylesheetReferenceEClass = createEClass(STYLESHEET_REFERENCE);
		createEAttribute(stylesheetReferenceEClass, STYLESHEET_REFERENCE__HREF);

		scriptEClass = createEClass(SCRIPT);
		createEAttribute(scriptEClass, SCRIPT__CODE);

		scriptResourceEClass = createEClass(SCRIPT_RESOURCE);
		createEAttribute(scriptResourceEClass, SCRIPT_RESOURCE__LOCATION);

		scriptReferenceEClass = createEClass(SCRIPT_REFERENCE);
		createEAttribute(scriptReferenceEClass, SCRIPT_REFERENCE__SRC);

		viewPartAdapterEClass = createEClass(VIEW_PART_ADAPTER);
		createEAttribute(viewPartAdapterEClass, VIEW_PART_ADAPTER__FACTORY);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		NcorePackage theNcorePackage = (NcorePackage)EPackage.Registry.INSTANCE.getEPackage(NcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		htmlElementEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		tagEClass.getESuperTypes().add(this.getHtmlElement());
		contentTagEClass.getESuperTypes().add(this.getTag());
		contentTagEClass.getESuperTypes().add(this.getContainer());
		pageEClass.getESuperTypes().add(theNcorePackage.getNamedElement());
		stylesheetEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		stylesheetResourceEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		stylesheetReferenceEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		scriptEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		scriptResourceEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		scriptReferenceEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		viewPartAdapterEClass.getESuperTypes().add(this.getHtmlElement());

		// Initialize classes, features, and operations; add parameters
		initEClass(htmlElementEClass, HtmlElement.class, "HtmlElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(containerEClass, org.nasdanika.html.model.html.Container.class, "Container", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContainer_Content(), ecorePackage.getEObject(), null, "content", null, 0, -1, org.nasdanika.html.model.html.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContainer_MarkdownContent(), ecorePackage.getEString(), "markdownContent", null, 0, 1, org.nasdanika.html.model.html.Container.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tagEClass, Tag.class, "Tag", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTag_Name(), ecorePackage.getEString(), "name", null, 0, 1, Tag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTag_Attributes(), theNcorePackage.getAbstractEntry(), null, "attributes", null, 0, -1, Tag.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contentTagEClass, ContentTag.class, "ContentTag", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(pageEClass, Page.class, "Page", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPage_Head(), ecorePackage.getEObject(), null, "head", null, 0, -1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPage_Body(), ecorePackage.getEObject(), null, "body", null, 0, -1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPage_Builders(), ecorePackage.getEObject(), null, "builders", null, 0, -1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_Language(), ecorePackage.getEString(), "language", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_FontAwesome(), ecorePackage.getEBoolean(), "fontAwesome", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_LineAwesome(), ecorePackage.getEBoolean(), "lineAwesome", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_JsTree(), ecorePackage.getEBoolean(), "jsTree", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_GithubMarkdownCss(), ecorePackage.getEBoolean(), "githubMarkdownCss", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_HighlightJs(), ecorePackage.getEBoolean(), "highlightJs", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stylesheetEClass, Stylesheet.class, "Stylesheet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStylesheet_Code(), ecorePackage.getEString(), "code", null, 1, 1, Stylesheet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stylesheetResourceEClass, StylesheetResource.class, "StylesheetResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStylesheetResource_Location(), ecorePackage.getEString(), "location", null, 1, 1, StylesheetResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stylesheetReferenceEClass, StylesheetReference.class, "StylesheetReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStylesheetReference_Href(), ecorePackage.getEString(), "href", null, 1, 1, StylesheetReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scriptEClass, Script.class, "Script", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScript_Code(), ecorePackage.getEString(), "code", null, 1, 1, Script.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scriptResourceEClass, ScriptResource.class, "ScriptResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScriptResource_Location(), ecorePackage.getEString(), "location", null, 1, 1, ScriptResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scriptReferenceEClass, ScriptReference.class, "ScriptReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScriptReference_Src(), ecorePackage.getEString(), "src", null, 1, 1, ScriptReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(viewPartAdapterEClass, ViewPartAdapter.class, "ViewPartAdapter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getViewPartAdapter_Factory(), ecorePackage.getEString(), "factory", null, 1, 1, ViewPartAdapter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
		// urn:org.nasdanika
		createUrnorgAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/GenModel</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGenModelAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/GenModel";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "documentation", "HTML model"
		   });
		addAnnotation
		  (htmlElementEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for other HTML and bootstrap elements. "
		   });
		addAnnotation
		  (containerEClass,
		   source,
		   new String[] {
			   "documentation", "Container of content such as text and HTML markup."
		   });
		addAnnotation
		  (getContainer_Content(),
		   source,
		   new String[] {
			   "documentation", "Container content. \n\nContent elements are adapted to ${javadoc/org.nasdanika.common.SupplierFactory} for generation of HTML content."
		   });
		addAnnotation
		  (getContainer_MarkdownContent(),
		   source,
		   new String[] {
			   "documentation", "[Markdown](https://en.wikipedia.org/wiki/Markdown) text. If this attribute contains text, the text is converted to HTML, interpolated and used as the first content element."
		   });
		addAnnotation
		  (tagEClass,
		   source,
		   new String[] {
			   "documentation", "HTML Tag"
		   });
		addAnnotation
		  (getTag_Name(),
		   source,
		   new String[] {
			   "documentation", "Tag name."
		   });
		addAnnotation
		  (getTag_Attributes(),
		   source,
		   new String[] {
			   "documentation", "Tag attributes.\n\n## Interpolation\n\nAttribute values are interpolated, i.e. tokens in the form of ``${token name[|default value]}`` are replaced with the contextual values or default values, if any. Examples:\n\n* ``${my-style}`` - Token without a default value.\n* ``${font-weight|bold}`` - Token with a default value.\n\n## Regular attributes\n\nFor all top-level entries except ``class``, ``style``, and ``data`` attribute value is produced by converting the value to string for scalars and to JSON string for lists and maps. \nFor attributes which do not start with ``data-`` a warning is issued if the value is not a scalar, i.e. a list or a map.\n\n## Class\n\nFor class attribute its value is formed by concantenating elements using space as a separator. If elements are hierarchical then class name is formed by concatenation with a dash (``-``) as a separator.\n\n## Data\n\nIf value of ``data`` attbibute is a map then keys of that map get concatenated with ``data`` using dash (``-``) as a separator, them same applies to nested maps. Non-map values become attribute values - scalars are converted to string, lists are converted to JSON string.\n\n## Style\n\nStyle can be defined as a string, list or map. If style is defined as a list, all list values are concatenated with a space as a separator - it is a convent way for long unstructured definitions.\n\nIf style value is a map then the value and its contained map values are processed in the following fashion:\n\n* Keys are concatenated with dash as a separator.\n* List values are contcatenated wtih space as a separator.\n"
		   });
		addAnnotation
		  (contentTagEClass,
		   source,
		   new String[] {
			   "documentation", "HTML Tag with content."
		   });
		addAnnotation
		  (pageEClass,
		   source,
		   new String[] {
			   "documentation", "HTML page. The name attribute is output as title tag in the head.\n\n[Overview video](https://www.youtube.com/watch?v=S28UszI-2g8) - in Russian."
		   });
		addAnnotation
		  (getPage_Head(),
		   source,
		   new String[] {
			   "documentation", "Head content."
		   });
		addAnnotation
		  (getPage_Body(),
		   source,
		   new String[] {
			   "documentation", "Body content."
		   });
		addAnnotation
		  (getPage_Builders(),
		   source,
		   new String[] {
			   "documentation", "Builders operate on an instance of ``org.nasdanika.html.HTMLPage`` created by the the page element."
		   });
		addAnnotation
		  (getPage_Language(),
		   source,
		   new String[] {
			   "documentation", "Page language - ``lang`` attribute."
		   });
		addAnnotation
		  (getPage_FontAwesome(),
		   source,
		   new String[] {
			   "documentation", "If this attribute is set to true [Font Awesome](https://fontawesome.com/) CDN stylesheet reference is added to the head."
		   });
		addAnnotation
		  (getPage_LineAwesome(),
		   source,
		   new String[] {
			   "documentation", "If this attribute is set to true [Line Awesome](https://icons8.com/line-awesome/) CDN stylesheet reference is added to the head."
		   });
		addAnnotation
		  (getPage_JsTree(),
		   source,
		   new String[] {
			   "documentation", "If this attribute is set to true [jsTree](https://www.jstree.com/) CDN script and stylesheet references are added to the head."
		   });
		addAnnotation
		  (getPage_GithubMarkdownCss(),
		   source,
		   new String[] {
			   "documentation", "If this attribute is set to true [GitHub Markdown CSS](https://github.com/sindresorhus/github-markdown-css) CDN stylesheet reference is added to the head."
		   });
		addAnnotation
		  (getPage_HighlightJs(),
		   source,
		   new String[] {
			   "documentation", "If this attribute is set to true [highlight.js](https://highlightjs.org/) CDN script and stylesheet references are added to the head as well as the initialization script in order to provide syntax highlighting in markdown fenced blocks."
		   });
		addAnnotation
		  (stylesheetEClass,
		   source,
		   new String[] {
			   "documentation", "CSS stylesheet with code stored in the model element ``code`` attribute."
		   });
		addAnnotation
		  (getStylesheet_Code(),
		   source,
		   new String[] {
			   "documentation", "Stylesheet code."
		   });
		addAnnotation
		  (stylesheetResourceEClass,
		   source,
		   new String[] {
			   "documentation", "CSS stylesheet with code loaded from a resource specified in the ``location`` attribute."
		   });
		addAnnotation
		  (getStylesheetResource_Location(),
		   source,
		   new String[] {
			   "documentation", "Stylesheet code location relative to the model resource location."
		   });
		addAnnotation
		  (stylesheetReferenceEClass,
		   source,
		   new String[] {
			   "documentation", "Reference to an external stylesheet."
		   });
		addAnnotation
		  (getStylesheetReference_Href(),
		   source,
		   new String[] {
			   "documentation", "Stylesheet URL."
		   });
		addAnnotation
		  (scriptEClass,
		   source,
		   new String[] {
			   "documentation", "Script with code stored in the model element ``code`` attribute."
		   });
		addAnnotation
		  (getScript_Code(),
		   source,
		   new String[] {
			   "documentation", "Script code."
		   });
		addAnnotation
		  (scriptResourceEClass,
		   source,
		   new String[] {
			   "documentation", "Script which loads code from a resource specified in the ``location`` attribute."
		   });
		addAnnotation
		  (getScriptResource_Location(),
		   source,
		   new String[] {
			   "documentation", "Script code location relative to the model resource location."
		   });
		addAnnotation
		  (scriptReferenceEClass,
		   source,
		   new String[] {
			   "documentation", "References external script."
		   });
		addAnnotation
		  (getScriptReference_Src(),
		   source,
		   new String[] {
			   "documentation", "Script URL."
		   });
		addAnnotation
		  (viewPartAdapterEClass,
		   source,
		   new String[] {
			   "documentation", "View part adapter delegates generation of HTML to an adapter created by a named adapter factory.\nThe factory shall be for ${javadoc/org.nasdanika.html.app.ViewPart$Supplier$Factory} type."
		   });
		addAnnotation
		  (getViewPartAdapter_Factory(),
		   source,
		   new String[] {
			   "documentation", "Named adapter factory."
		   });
	}

	/**
	 * Initializes the annotations for <b>urn:org.nasdanika</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createUrnorgAnnotations() {
		String source = "urn:org.nasdanika";
		addAnnotation
		  (getContainer_MarkdownContent(),
		   source,
		   new String[] {
			   "content-type", "text/markdown"
		   });
		addAnnotation
		  (getStylesheet_Code(),
		   source,
		   new String[] {
			   "content-type", "text/code"
		   });
		addAnnotation
		  (getScript_Code(),
		   source,
		   new String[] {
			   "content-type", "text/code"
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";
		addAnnotation
		  (tagEClass,
		   source,
		   new String[] {
			   "constraints", "attributes"
		   });
	}

} //HtmlPackageImpl
