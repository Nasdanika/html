/**
 */
package org.nasdanika.html.model.html;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * HTML model
 * <!-- end-model-doc -->
 * @see org.nasdanika.html.model.html.HtmlFactory
 * @model kind="package"
 * @generated
 */
public interface HtmlPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "html";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "urn:org.nasdanika..html.model.html";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.html.model.html";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	HtmlPackage eINSTANCE = org.nasdanika.html.model.html.impl.HtmlPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.HtmlElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.HtmlElementImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getHtmlElement()
	 * @generated
	 */
	int HTML_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_ELEMENT__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_ELEMENT__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_ELEMENT_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_ELEMENT_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.ContainerImpl <em>Container</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.ContainerImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getContainer()
	 * @generated
	 */
	int CONTAINER = 1;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__CONTENT = 0;

	/**
	 * The feature id for the '<em><b>Markdown Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER__MARKDOWN_CONTENT = 1;

	/**
	 * The number of structural features of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Container</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTAINER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.TagImpl <em>Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.TagImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getTag()
	 * @generated
	 */
	int TAG = 2;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__TITLE = HTML_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__DESCRIPTION = HTML_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__NAME = HTML_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG__ATTRIBUTES = HTML_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FEATURE_COUNT = HTML_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_OPERATION_COUNT = HTML_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.ContentTagImpl <em>Content Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.ContentTagImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getContentTag()
	 * @generated
	 */
	int CONTENT_TAG = 3;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_TAG__TITLE = TAG__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_TAG__DESCRIPTION = TAG__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_TAG__NAME = TAG__NAME;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_TAG__ATTRIBUTES = TAG__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_TAG__CONTENT = TAG_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markdown Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_TAG__MARKDOWN_CONTENT = TAG_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Content Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_TAG_FEATURE_COUNT = TAG_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Content Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_TAG_OPERATION_COUNT = TAG_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.PageImpl <em>Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.PageImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getPage()
	 * @generated
	 */
	int PAGE = 4;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__TITLE = NcorePackage.NAMED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__DESCRIPTION = NcorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__NAME = NcorePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Head</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__HEAD = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__BODY = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Builders</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__BUILDERS = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__LANGUAGE = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Font Awesome</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__FONT_AWESOME = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Line Awesome</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__LINE_AWESOME = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Js Tree</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__JS_TREE = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Github Markdown Css</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__GITHUB_MARKDOWN_CSS = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Highlight Js</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__HIGHLIGHT_JS = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_FEATURE_COUNT = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The number of operations of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_OPERATION_COUNT = NcorePackage.NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.StylesheetImpl <em>Stylesheet</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.StylesheetImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getStylesheet()
	 * @generated
	 */
	int STYLESHEET = 5;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLESHEET__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLESHEET__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLESHEET__CODE = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Stylesheet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLESHEET_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Stylesheet</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLESHEET_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.StylesheetResourceImpl <em>Stylesheet Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.StylesheetResourceImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getStylesheetResource()
	 * @generated
	 */
	int STYLESHEET_RESOURCE = 6;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLESHEET_RESOURCE__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLESHEET_RESOURCE__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLESHEET_RESOURCE__LOCATION = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Stylesheet Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLESHEET_RESOURCE_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Stylesheet Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLESHEET_RESOURCE_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.StylesheetReferenceImpl <em>Stylesheet Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.StylesheetReferenceImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getStylesheetReference()
	 * @generated
	 */
	int STYLESHEET_REFERENCE = 7;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLESHEET_REFERENCE__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLESHEET_REFERENCE__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Href</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLESHEET_REFERENCE__HREF = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Stylesheet Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLESHEET_REFERENCE_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Stylesheet Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLESHEET_REFERENCE_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.ScriptImpl <em>Script</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.ScriptImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getScript()
	 * @generated
	 */
	int SCRIPT = 8;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT__CODE = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Script</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.ScriptResourceImpl <em>Script Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.ScriptResourceImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getScriptResource()
	 * @generated
	 */
	int SCRIPT_RESOURCE = 9;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_RESOURCE__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_RESOURCE__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_RESOURCE__LOCATION = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Script Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_RESOURCE_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Script Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_RESOURCE_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.ScriptReferenceImpl <em>Script Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.ScriptReferenceImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getScriptReference()
	 * @generated
	 */
	int SCRIPT_REFERENCE = 10;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_REFERENCE__TITLE = NcorePackage.MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_REFERENCE__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Src</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_REFERENCE__SRC = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Script Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_REFERENCE_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Script Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_REFERENCE_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.html.impl.ViewPartAdapterImpl <em>View Part Adapter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.html.impl.ViewPartAdapterImpl
	 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getViewPartAdapter()
	 * @generated
	 */
	int VIEW_PART_ADAPTER = 11;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_PART_ADAPTER__TITLE = HTML_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_PART_ADAPTER__DESCRIPTION = HTML_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Factory</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_PART_ADAPTER__FACTORY = HTML_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>View Part Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_PART_ADAPTER_FEATURE_COUNT = HTML_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>View Part Adapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIEW_PART_ADAPTER_OPERATION_COUNT = HTML_ELEMENT_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.HtmlElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.nasdanika.html.model.html.HtmlElement
	 * @generated
	 */
	EClass getHtmlElement();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.Container <em>Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Container</em>'.
	 * @see org.nasdanika.html.model.html.Container
	 * @generated
	 */
	EClass getContainer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.Container#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Content</em>'.
	 * @see org.nasdanika.html.model.html.Container#getContent()
	 * @see #getContainer()
	 * @generated
	 */
	EReference getContainer_Content();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Container#getMarkdownContent <em>Markdown Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Markdown Content</em>'.
	 * @see org.nasdanika.html.model.html.Container#getMarkdownContent()
	 * @see #getContainer()
	 * @generated
	 */
	EAttribute getContainer_MarkdownContent();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.Tag <em>Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag</em>'.
	 * @see org.nasdanika.html.model.html.Tag
	 * @generated
	 */
	EClass getTag();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Tag#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.nasdanika.html.model.html.Tag#getName()
	 * @see #getTag()
	 * @generated
	 */
	EAttribute getTag_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.Tag#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.nasdanika.html.model.html.Tag#getAttributes()
	 * @see #getTag()
	 * @generated
	 */
	EReference getTag_Attributes();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.ContentTag <em>Content Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content Tag</em>'.
	 * @see org.nasdanika.html.model.html.ContentTag
	 * @generated
	 */
	EClass getContentTag();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.Page <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page</em>'.
	 * @see org.nasdanika.html.model.html.Page
	 * @generated
	 */
	EClass getPage();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.Page#getHead <em>Head</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Head</em>'.
	 * @see org.nasdanika.html.model.html.Page#getHead()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_Head();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.Page#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Body</em>'.
	 * @see org.nasdanika.html.model.html.Page#getBody()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_Body();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.html.Page#getBuilders <em>Builders</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Builders</em>'.
	 * @see org.nasdanika.html.model.html.Page#getBuilders()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_Builders();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Page#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see org.nasdanika.html.model.html.Page#getLanguage()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Language();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Page#isFontAwesome <em>Font Awesome</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Font Awesome</em>'.
	 * @see org.nasdanika.html.model.html.Page#isFontAwesome()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_FontAwesome();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Page#isLineAwesome <em>Line Awesome</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Awesome</em>'.
	 * @see org.nasdanika.html.model.html.Page#isLineAwesome()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_LineAwesome();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Page#isJsTree <em>Js Tree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Js Tree</em>'.
	 * @see org.nasdanika.html.model.html.Page#isJsTree()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_JsTree();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Page#isGithubMarkdownCss <em>Github Markdown Css</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Github Markdown Css</em>'.
	 * @see org.nasdanika.html.model.html.Page#isGithubMarkdownCss()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_GithubMarkdownCss();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Page#isHighlightJs <em>Highlight Js</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Highlight Js</em>'.
	 * @see org.nasdanika.html.model.html.Page#isHighlightJs()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_HighlightJs();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.Stylesheet <em>Stylesheet</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stylesheet</em>'.
	 * @see org.nasdanika.html.model.html.Stylesheet
	 * @generated
	 */
	EClass getStylesheet();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Stylesheet#getCode <em>Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code</em>'.
	 * @see org.nasdanika.html.model.html.Stylesheet#getCode()
	 * @see #getStylesheet()
	 * @generated
	 */
	EAttribute getStylesheet_Code();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.StylesheetResource <em>Stylesheet Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stylesheet Resource</em>'.
	 * @see org.nasdanika.html.model.html.StylesheetResource
	 * @generated
	 */
	EClass getStylesheetResource();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.StylesheetResource#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.nasdanika.html.model.html.StylesheetResource#getLocation()
	 * @see #getStylesheetResource()
	 * @generated
	 */
	EAttribute getStylesheetResource_Location();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.StylesheetReference <em>Stylesheet Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stylesheet Reference</em>'.
	 * @see org.nasdanika.html.model.html.StylesheetReference
	 * @generated
	 */
	EClass getStylesheetReference();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.StylesheetReference#getHref <em>Href</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Href</em>'.
	 * @see org.nasdanika.html.model.html.StylesheetReference#getHref()
	 * @see #getStylesheetReference()
	 * @generated
	 */
	EAttribute getStylesheetReference_Href();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.Script <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script</em>'.
	 * @see org.nasdanika.html.model.html.Script
	 * @generated
	 */
	EClass getScript();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.Script#getCode <em>Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code</em>'.
	 * @see org.nasdanika.html.model.html.Script#getCode()
	 * @see #getScript()
	 * @generated
	 */
	EAttribute getScript_Code();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.ScriptResource <em>Script Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script Resource</em>'.
	 * @see org.nasdanika.html.model.html.ScriptResource
	 * @generated
	 */
	EClass getScriptResource();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.ScriptResource#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.nasdanika.html.model.html.ScriptResource#getLocation()
	 * @see #getScriptResource()
	 * @generated
	 */
	EAttribute getScriptResource_Location();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.ScriptReference <em>Script Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script Reference</em>'.
	 * @see org.nasdanika.html.model.html.ScriptReference
	 * @generated
	 */
	EClass getScriptReference();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.ScriptReference#getSrc <em>Src</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Src</em>'.
	 * @see org.nasdanika.html.model.html.ScriptReference#getSrc()
	 * @see #getScriptReference()
	 * @generated
	 */
	EAttribute getScriptReference_Src();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.html.ViewPartAdapter <em>View Part Adapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>View Part Adapter</em>'.
	 * @see org.nasdanika.html.model.html.ViewPartAdapter
	 * @generated
	 */
	EClass getViewPartAdapter();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.html.ViewPartAdapter#getFactory <em>Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Factory</em>'.
	 * @see org.nasdanika.html.model.html.ViewPartAdapter#getFactory()
	 * @see #getViewPartAdapter()
	 * @generated
	 */
	EAttribute getViewPartAdapter_Factory();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	HtmlFactory getHtmlFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.HtmlElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.HtmlElementImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getHtmlElement()
		 * @generated
		 */
		EClass HTML_ELEMENT = eINSTANCE.getHtmlElement();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.ContainerImpl <em>Container</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.ContainerImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getContainer()
		 * @generated
		 */
		EClass CONTAINER = eINSTANCE.getContainer();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTAINER__CONTENT = eINSTANCE.getContainer_Content();

		/**
		 * The meta object literal for the '<em><b>Markdown Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTAINER__MARKDOWN_CONTENT = eINSTANCE.getContainer_MarkdownContent();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.TagImpl <em>Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.TagImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getTag()
		 * @generated
		 */
		EClass TAG = eINSTANCE.getTag();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG__NAME = eINSTANCE.getTag_Name();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG__ATTRIBUTES = eINSTANCE.getTag_Attributes();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.ContentTagImpl <em>Content Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.ContentTagImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getContentTag()
		 * @generated
		 */
		EClass CONTENT_TAG = eINSTANCE.getContentTag();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.PageImpl <em>Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.PageImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getPage()
		 * @generated
		 */
		EClass PAGE = eINSTANCE.getPage();

		/**
		 * The meta object literal for the '<em><b>Head</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__HEAD = eINSTANCE.getPage_Head();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__BODY = eINSTANCE.getPage_Body();

		/**
		 * The meta object literal for the '<em><b>Builders</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__BUILDERS = eINSTANCE.getPage_Builders();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__LANGUAGE = eINSTANCE.getPage_Language();

		/**
		 * The meta object literal for the '<em><b>Font Awesome</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__FONT_AWESOME = eINSTANCE.getPage_FontAwesome();

		/**
		 * The meta object literal for the '<em><b>Line Awesome</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__LINE_AWESOME = eINSTANCE.getPage_LineAwesome();

		/**
		 * The meta object literal for the '<em><b>Js Tree</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__JS_TREE = eINSTANCE.getPage_JsTree();

		/**
		 * The meta object literal for the '<em><b>Github Markdown Css</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__GITHUB_MARKDOWN_CSS = eINSTANCE.getPage_GithubMarkdownCss();

		/**
		 * The meta object literal for the '<em><b>Highlight Js</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__HIGHLIGHT_JS = eINSTANCE.getPage_HighlightJs();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.StylesheetImpl <em>Stylesheet</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.StylesheetImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getStylesheet()
		 * @generated
		 */
		EClass STYLESHEET = eINSTANCE.getStylesheet();

		/**
		 * The meta object literal for the '<em><b>Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLESHEET__CODE = eINSTANCE.getStylesheet_Code();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.StylesheetResourceImpl <em>Stylesheet Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.StylesheetResourceImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getStylesheetResource()
		 * @generated
		 */
		EClass STYLESHEET_RESOURCE = eINSTANCE.getStylesheetResource();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLESHEET_RESOURCE__LOCATION = eINSTANCE.getStylesheetResource_Location();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.StylesheetReferenceImpl <em>Stylesheet Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.StylesheetReferenceImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getStylesheetReference()
		 * @generated
		 */
		EClass STYLESHEET_REFERENCE = eINSTANCE.getStylesheetReference();

		/**
		 * The meta object literal for the '<em><b>Href</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLESHEET_REFERENCE__HREF = eINSTANCE.getStylesheetReference_Href();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.ScriptImpl <em>Script</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.ScriptImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getScript()
		 * @generated
		 */
		EClass SCRIPT = eINSTANCE.getScript();

		/**
		 * The meta object literal for the '<em><b>Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT__CODE = eINSTANCE.getScript_Code();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.ScriptResourceImpl <em>Script Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.ScriptResourceImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getScriptResource()
		 * @generated
		 */
		EClass SCRIPT_RESOURCE = eINSTANCE.getScriptResource();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_RESOURCE__LOCATION = eINSTANCE.getScriptResource_Location();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.ScriptReferenceImpl <em>Script Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.ScriptReferenceImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getScriptReference()
		 * @generated
		 */
		EClass SCRIPT_REFERENCE = eINSTANCE.getScriptReference();

		/**
		 * The meta object literal for the '<em><b>Src</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_REFERENCE__SRC = eINSTANCE.getScriptReference_Src();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.html.impl.ViewPartAdapterImpl <em>View Part Adapter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.html.impl.ViewPartAdapterImpl
		 * @see org.nasdanika.html.model.html.impl.HtmlPackageImpl#getViewPartAdapter()
		 * @generated
		 */
		EClass VIEW_PART_ADAPTER = eINSTANCE.getViewPartAdapter();

		/**
		 * The meta object literal for the '<em><b>Factory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIEW_PART_ADAPTER__FACTORY = eINSTANCE.getViewPartAdapter_Factory();

	}

} //HtmlPackage
