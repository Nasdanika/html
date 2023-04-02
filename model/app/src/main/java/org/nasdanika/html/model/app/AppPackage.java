/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.nasdanika.html.model.bootstrap.BootstrapPackage;

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
 * @see org.nasdanika.html.model.app.AppFactory
 * @model kind="package"
 *        annotation="urn:org.nasdanika documentation-reference='doc/package-summary.md'"
 * @generated
 */
public interface AppPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "app";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "ecore://nasdanika.org/html/app";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.html.model.app";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AppPackage eINSTANCE = org.nasdanika.html.model.app.impl.AppPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.LabelImpl <em>Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.LabelImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getLabel()
	 * @generated
	 */
	int LABEL = 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__MARKERS = BootstrapPackage.BOOTSTRAP_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__URIS = BootstrapPackage.BOOTSTRAP_ELEMENT__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__DESCRIPTION = BootstrapPackage.BOOTSTRAP_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__UUID = BootstrapPackage.BOOTSTRAP_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Action Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__ACTION_PROTOTYPE = BootstrapPackage.BOOTSTRAP_ELEMENT__ACTION_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__REPRESENTATIONS = BootstrapPackage.BOOTSTRAP_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__ANNOTATIONS = BootstrapPackage.BOOTSTRAP_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__ATTRIBUTES = BootstrapPackage.BOOTSTRAP_ELEMENT__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__CONTENT = BootstrapPackage.BOOTSTRAP_ELEMENT__CONTENT;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__APPEARANCE = BootstrapPackage.BOOTSTRAP_ELEMENT__APPEARANCE;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__ACTIVE = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__DISABLED = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__COLOR = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__ID = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__TEXT = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__ICON = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__TOOLTIP = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__OUTLINE = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Notification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__NOTIFICATION = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__CHILDREN = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Decorator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__DECORATOR = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_FEATURE_COUNT = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 11;

	/**
	 * The number of operations of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_OPERATION_COUNT = BootstrapPackage.BOOTSTRAP_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.LinkImpl <em>Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.LinkImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getLink()
	 * @generated
	 */
	int LINK = 1;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__MARKERS = LABEL__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__URIS = LABEL__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__DESCRIPTION = LABEL__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__UUID = LABEL__UUID;

	/**
	 * The feature id for the '<em><b>Action Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__ACTION_PROTOTYPE = LABEL__ACTION_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__REPRESENTATIONS = LABEL__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__ANNOTATIONS = LABEL__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__ATTRIBUTES = LABEL__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__CONTENT = LABEL__CONTENT;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__APPEARANCE = LABEL__APPEARANCE;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__ACTIVE = LABEL__ACTIVE;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__DISABLED = LABEL__DISABLED;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__COLOR = LABEL__COLOR;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__ID = LABEL__ID;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__TEXT = LABEL__TEXT;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__ICON = LABEL__ICON;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__TOOLTIP = LABEL__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__OUTLINE = LABEL__OUTLINE;

	/**
	 * The feature id for the '<em><b>Notification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__NOTIFICATION = LABEL__NOTIFICATION;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__CHILDREN = LABEL__CHILDREN;

	/**
	 * The feature id for the '<em><b>Decorator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__DECORATOR = LABEL__DECORATOR;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__LOCATION = LABEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Script</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__SCRIPT = LABEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Modal</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__MODAL = LABEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__NAME = LABEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Confirmation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__CONFIRMATION = LABEL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__TARGET = LABEL_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Action</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__ACTION = LABEL_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_FEATURE_COUNT = LABEL_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_OPERATION_COUNT = LABEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.PageImpl <em>Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.PageImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getPage()
	 * @generated
	 */
	int PAGE = 2;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__MARKERS = BootstrapPackage.BOOTSTRAP_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__URIS = BootstrapPackage.BOOTSTRAP_ELEMENT__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__DESCRIPTION = BootstrapPackage.BOOTSTRAP_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__UUID = BootstrapPackage.BOOTSTRAP_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Action Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__ACTION_PROTOTYPE = BootstrapPackage.BOOTSTRAP_ELEMENT__ACTION_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__REPRESENTATIONS = BootstrapPackage.BOOTSTRAP_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__ANNOTATIONS = BootstrapPackage.BOOTSTRAP_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__ATTRIBUTES = BootstrapPackage.BOOTSTRAP_ELEMENT__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__CONTENT = BootstrapPackage.BOOTSTRAP_ELEMENT__CONTENT;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__APPEARANCE = BootstrapPackage.BOOTSTRAP_ELEMENT__APPEARANCE;

	/**
	 * The feature id for the '<em><b>Fluid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__FLUID = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Header</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__HEADER = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Navigation Bar</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__NAVIGATION_BAR = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Navigation Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__NAVIGATION_PANEL = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Content Panel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__CONTENT_PANEL = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Footer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__FOOTER = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Content Row Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__CONTENT_ROW_APPEARANCE = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_FEATURE_COUNT = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_OPERATION_COUNT = BootstrapPackage.BOOTSTRAP_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.PagePartImpl <em>Page Part</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.PagePartImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getPagePart()
	 * @generated
	 */
	int PAGE_PART = 3;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_PART__MARKERS = BootstrapPackage.BOOTSTRAP_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_PART__URIS = BootstrapPackage.BOOTSTRAP_ELEMENT__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_PART__DESCRIPTION = BootstrapPackage.BOOTSTRAP_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_PART__UUID = BootstrapPackage.BOOTSTRAP_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Action Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_PART__ACTION_PROTOTYPE = BootstrapPackage.BOOTSTRAP_ELEMENT__ACTION_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_PART__REPRESENTATIONS = BootstrapPackage.BOOTSTRAP_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_PART__ANNOTATIONS = BootstrapPackage.BOOTSTRAP_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_PART__ATTRIBUTES = BootstrapPackage.BOOTSTRAP_ELEMENT__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_PART__CONTENT = BootstrapPackage.BOOTSTRAP_ELEMENT__CONTENT;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_PART__APPEARANCE = BootstrapPackage.BOOTSTRAP_ELEMENT__APPEARANCE;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_PART__ITEMS = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Page Part</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_PART_FEATURE_COUNT = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Page Part</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_PART_OPERATION_COUNT = BootstrapPackage.BOOTSTRAP_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.HeaderImpl <em>Header</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.HeaderImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getHeader()
	 * @generated
	 */
	int HEADER = 4;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER__MARKERS = PAGE_PART__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER__URIS = PAGE_PART__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER__DESCRIPTION = PAGE_PART__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER__UUID = PAGE_PART__UUID;

	/**
	 * The feature id for the '<em><b>Action Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER__ACTION_PROTOTYPE = PAGE_PART__ACTION_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER__REPRESENTATIONS = PAGE_PART__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER__ANNOTATIONS = PAGE_PART__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER__ATTRIBUTES = PAGE_PART__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER__CONTENT = PAGE_PART__CONTENT;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER__APPEARANCE = PAGE_PART__APPEARANCE;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER__ITEMS = PAGE_PART__ITEMS;

	/**
	 * The feature id for the '<em><b>Title</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER__TITLE = PAGE_PART_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Header</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER_FEATURE_COUNT = PAGE_PART_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Header</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER_OPERATION_COUNT = PAGE_PART_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.NavigationBarImpl <em>Navigation Bar</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.NavigationBarImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getNavigationBar()
	 * @generated
	 */
	int NAVIGATION_BAR = 5;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR__MARKERS = PAGE_PART__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR__URIS = PAGE_PART__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR__DESCRIPTION = PAGE_PART__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR__UUID = PAGE_PART__UUID;

	/**
	 * The feature id for the '<em><b>Action Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR__ACTION_PROTOTYPE = PAGE_PART__ACTION_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR__REPRESENTATIONS = PAGE_PART__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR__ANNOTATIONS = PAGE_PART__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR__ATTRIBUTES = PAGE_PART__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR__CONTENT = PAGE_PART__CONTENT;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR__APPEARANCE = PAGE_PART__APPEARANCE;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR__ITEMS = PAGE_PART__ITEMS;

	/**
	 * The feature id for the '<em><b>Brand</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR__BRAND = PAGE_PART_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dark</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR__DARK = PAGE_PART_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Expand</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR__EXPAND = PAGE_PART_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Background</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR__BACKGROUND = PAGE_PART_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Navigation Bar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR_FEATURE_COUNT = PAGE_PART_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Navigation Bar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR_OPERATION_COUNT = PAGE_PART_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.NavigationPanelImpl <em>Navigation Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.NavigationPanelImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getNavigationPanel()
	 * @generated
	 */
	int NAVIGATION_PANEL = 6;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__MARKERS = PAGE_PART__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__URIS = PAGE_PART__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__DESCRIPTION = PAGE_PART__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__UUID = PAGE_PART__UUID;

	/**
	 * The feature id for the '<em><b>Action Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__ACTION_PROTOTYPE = PAGE_PART__ACTION_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__REPRESENTATIONS = PAGE_PART__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__ANNOTATIONS = PAGE_PART__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__ATTRIBUTES = PAGE_PART__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__CONTENT = PAGE_PART__CONTENT;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__APPEARANCE = PAGE_PART__APPEARANCE;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__ITEMS = PAGE_PART__ITEMS;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__STYLE = PAGE_PART_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__ID = PAGE_PART_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Label Trim Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__LABEL_TRIM_LENGTH = PAGE_PART_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Collapsible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__COLLAPSIBLE = PAGE_PART_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Js Tree Search Threshold</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__JS_TREE_SEARCH_THRESHOLD = PAGE_PART_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Navigation Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL_FEATURE_COUNT = PAGE_PART_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Navigation Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL_OPERATION_COUNT = PAGE_PART_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.ContentPanelImpl <em>Content Panel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.ContentPanelImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getContentPanel()
	 * @generated
	 */
	int CONTENT_PANEL = 7;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__MARKERS = PAGE_PART__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__URIS = PAGE_PART__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__DESCRIPTION = PAGE_PART__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__UUID = PAGE_PART__UUID;

	/**
	 * The feature id for the '<em><b>Action Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__ACTION_PROTOTYPE = PAGE_PART__ACTION_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__REPRESENTATIONS = PAGE_PART__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__ANNOTATIONS = PAGE_PART__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__ATTRIBUTES = PAGE_PART__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__CONTENT = PAGE_PART__CONTENT;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__APPEARANCE = PAGE_PART__APPEARANCE;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__ITEMS = PAGE_PART__ITEMS;

	/**
	 * The feature id for the '<em><b>Breadcrumb</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__BREADCRUMB = PAGE_PART_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__TITLE = PAGE_PART_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Left Navigation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__LEFT_NAVIGATION = PAGE_PART_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Right Navigation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__RIGHT_NAVIGATION = PAGE_PART_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Float Left Navigation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__FLOAT_LEFT_NAVIGATION = PAGE_PART_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Float Right Navigation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__FLOAT_RIGHT_NAVIGATION = PAGE_PART_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__SECTIONS = PAGE_PART_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Section Columns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__SECTION_COLUMNS = PAGE_PART_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Section Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__SECTION_STYLE = PAGE_PART_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Content Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL_FEATURE_COUNT = PAGE_PART_FEATURE_COUNT + 9;

	/**
	 * The number of operations of the '<em>Content Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL_OPERATION_COUNT = PAGE_PART_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.FooterImpl <em>Footer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.FooterImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getFooter()
	 * @generated
	 */
	int FOOTER = 8;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOOTER__MARKERS = PAGE_PART__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOOTER__URIS = PAGE_PART__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOOTER__DESCRIPTION = PAGE_PART__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOOTER__UUID = PAGE_PART__UUID;

	/**
	 * The feature id for the '<em><b>Action Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOOTER__ACTION_PROTOTYPE = PAGE_PART__ACTION_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOOTER__REPRESENTATIONS = PAGE_PART__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOOTER__ANNOTATIONS = PAGE_PART__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOOTER__ATTRIBUTES = PAGE_PART__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOOTER__CONTENT = PAGE_PART__CONTENT;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOOTER__APPEARANCE = PAGE_PART__APPEARANCE;

	/**
	 * The feature id for the '<em><b>Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOOTER__ITEMS = PAGE_PART__ITEMS;

	/**
	 * The number of structural features of the '<em>Footer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOOTER_FEATURE_COUNT = PAGE_PART_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Footer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOOTER_OPERATION_COUNT = PAGE_PART_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.ActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.ActionImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getAction()
	 * @generated
	 */
	int ACTION = 9;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__MARKERS = LINK__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__URIS = LINK__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__DESCRIPTION = LINK__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__UUID = LINK__UUID;

	/**
	 * The feature id for the '<em><b>Action Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ACTION_PROTOTYPE = LINK__ACTION_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__REPRESENTATIONS = LINK__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ANNOTATIONS = LINK__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ATTRIBUTES = LINK__ATTRIBUTES;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CONTENT = LINK__CONTENT;

	/**
	 * The feature id for the '<em><b>Appearance</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__APPEARANCE = LINK__APPEARANCE;

	/**
	 * The feature id for the '<em><b>Active</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ACTIVE = LINK__ACTIVE;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__DISABLED = LINK__DISABLED;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__COLOR = LINK__COLOR;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ID = LINK__ID;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__TEXT = LINK__TEXT;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ICON = LINK__ICON;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__TOOLTIP = LINK__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__OUTLINE = LINK__OUTLINE;

	/**
	 * The feature id for the '<em><b>Notification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__NOTIFICATION = LINK__NOTIFICATION;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CHILDREN = LINK__CHILDREN;

	/**
	 * The feature id for the '<em><b>Decorator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__DECORATOR = LINK__DECORATOR;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__LOCATION = LINK__LOCATION;

	/**
	 * The feature id for the '<em><b>Script</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__SCRIPT = LINK__SCRIPT;

	/**
	 * The feature id for the '<em><b>Modal</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__MODAL = LINK__MODAL;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__NAME = LINK__NAME;

	/**
	 * The feature id for the '<em><b>Confirmation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CONFIRMATION = LINK__CONFIRMATION;

	/**
	 * The feature id for the '<em><b>Target</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__TARGET = LINK__TARGET;

	/**
	 * The feature id for the '<em><b>Action</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ACTION = LINK__ACTION;

	/**
	 * The feature id for the '<em><b>Section Columns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__SECTION_COLUMNS = LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Section Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__SECTION_STYLE = LINK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__SECTIONS = LINK_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Navigation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__NAVIGATION = LINK_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Left Navigation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__LEFT_NAVIGATION = LINK_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Right Navigation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__RIGHT_NAVIGATION = LINK_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Float Left Navigation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__FLOAT_LEFT_NAVIGATION = LINK_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Float Right Navigation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__FLOAT_RIGHT_NAVIGATION = LINK_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Anonymous</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ANONYMOUS = LINK_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__RESOURCES = LINK_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Inline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__INLINE = LINK_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Modal Activator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__MODAL_ACTIVATOR = LINK_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = LINK_FEATURE_COUNT + 12;

	/**
	 * The operation id for the '<em>Create Link</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION___CREATE_LINK = LINK_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_OPERATION_COUNT = LINK_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.ActionReferenceImpl <em>Action Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.ActionReferenceImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getActionReference()
	 * @generated
	 */
	int ACTION_REFERENCE = 10;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_REFERENCE__TARGET = NcorePackage.REFERENCE__TARGET;

	/**
	 * The number of structural features of the '<em>Action Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_REFERENCE_FEATURE_COUNT = NcorePackage.REFERENCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Action Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_REFERENCE_OPERATION_COUNT = NcorePackage.REFERENCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.SectionStyle <em>Section Style</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.SectionStyle
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getSectionStyle()
	 * @generated
	 */
	int SECTION_STYLE = 11;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.NavigationPanelStyle <em>Navigation Panel Style</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.NavigationPanelStyle
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getNavigationPanelStyle()
	 * @generated
	 */
	int NAVIGATION_PANEL_STYLE = 12;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.Label <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Label</em>'.
	 * @see org.nasdanika.html.model.app.Label
	 * @generated
	 */
	EClass getLabel();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Label#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.nasdanika.html.model.app.Label#getId()
	 * @see #getLabel()
	 * @generated
	 */
	EAttribute getLabel_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Label#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Text</em>'.
	 * @see org.nasdanika.html.model.app.Label#getText()
	 * @see #getLabel()
	 * @generated
	 */
	EAttribute getLabel_Text();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Label#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Icon</em>'.
	 * @see org.nasdanika.html.model.app.Label#getIcon()
	 * @see #getLabel()
	 * @generated
	 */
	EAttribute getLabel_Icon();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Label#getTooltip <em>Tooltip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tooltip</em>'.
	 * @see org.nasdanika.html.model.app.Label#getTooltip()
	 * @see #getLabel()
	 * @generated
	 */
	EAttribute getLabel_Tooltip();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Label#isOutline <em>Outline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Outline</em>'.
	 * @see org.nasdanika.html.model.app.Label#isOutline()
	 * @see #getLabel()
	 * @generated
	 */
	EAttribute getLabel_Outline();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Label#getNotification <em>Notification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Notification</em>'.
	 * @see org.nasdanika.html.model.app.Label#getNotification()
	 * @see #getLabel()
	 * @generated
	 */
	EAttribute getLabel_Notification();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.app.Label#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.nasdanika.html.model.app.Label#getChildren()
	 * @see #getLabel()
	 * @generated
	 */
	EReference getLabel_Children();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.Label#getDecorator <em>Decorator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Decorator</em>'.
	 * @see org.nasdanika.html.model.app.Label#getDecorator()
	 * @see #getLabel()
	 * @generated
	 */
	EReference getLabel_Decorator();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.Link <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link</em>'.
	 * @see org.nasdanika.html.model.app.Link
	 * @generated
	 */
	EClass getLink();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Link#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.nasdanika.html.model.app.Link#getLocation()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Location();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Link#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Script</em>'.
	 * @see org.nasdanika.html.model.app.Link#getScript()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Script();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.Link#getModal <em>Modal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Modal</em>'.
	 * @see org.nasdanika.html.model.app.Link#getModal()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Modal();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Link#getConfirmation <em>Confirmation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Confirmation</em>'.
	 * @see org.nasdanika.html.model.app.Link#getConfirmation()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Confirmation();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Link#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.nasdanika.html.model.app.Link#getName()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Link#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target</em>'.
	 * @see org.nasdanika.html.model.app.Link#getTarget()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Target();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.html.model.app.Link#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Action</em>'.
	 * @see org.nasdanika.html.model.app.Link#getAction()
	 * @see #getLink()
	 * @generated
	 */
	EReference getLink_Action();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.Page <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page</em>'.
	 * @see org.nasdanika.html.model.app.Page
	 * @generated
	 */
	EClass getPage();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Page#isFluid <em>Fluid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fluid</em>'.
	 * @see org.nasdanika.html.model.app.Page#isFluid()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Fluid();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.Page#getHeader <em>Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Header</em>'.
	 * @see org.nasdanika.html.model.app.Page#getHeader()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_Header();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.Page#getNavigationBar <em>Navigation Bar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Navigation Bar</em>'.
	 * @see org.nasdanika.html.model.app.Page#getNavigationBar()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_NavigationBar();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.Page#getNavigationPanel <em>Navigation Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Navigation Panel</em>'.
	 * @see org.nasdanika.html.model.app.Page#getNavigationPanel()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_NavigationPanel();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.Page#getContentPanel <em>Content Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Content Panel</em>'.
	 * @see org.nasdanika.html.model.app.Page#getContentPanel()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_ContentPanel();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.Page#getFooter <em>Footer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Footer</em>'.
	 * @see org.nasdanika.html.model.app.Page#getFooter()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_Footer();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.Page#getContentRowAppearance <em>Content Row Appearance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Content Row Appearance</em>'.
	 * @see org.nasdanika.html.model.app.Page#getContentRowAppearance()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_ContentRowAppearance();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.PagePart <em>Page Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page Part</em>'.
	 * @see org.nasdanika.html.model.app.PagePart
	 * @generated
	 */
	EClass getPagePart();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.app.PagePart#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Items</em>'.
	 * @see org.nasdanika.html.model.app.PagePart#getItems()
	 * @see #getPagePart()
	 * @generated
	 */
	EReference getPagePart_Items();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.Header <em>Header</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Header</em>'.
	 * @see org.nasdanika.html.model.app.Header
	 * @generated
	 */
	EClass getHeader();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.Header#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Title</em>'.
	 * @see org.nasdanika.html.model.app.Header#getTitle()
	 * @see #getHeader()
	 * @generated
	 */
	EReference getHeader_Title();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.NavigationBar <em>Navigation Bar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation Bar</em>'.
	 * @see org.nasdanika.html.model.app.NavigationBar
	 * @generated
	 */
	EClass getNavigationBar();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.NavigationBar#getBrand <em>Brand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Brand</em>'.
	 * @see org.nasdanika.html.model.app.NavigationBar#getBrand()
	 * @see #getNavigationBar()
	 * @generated
	 */
	EReference getNavigationBar_Brand();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.NavigationBar#isDark <em>Dark</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dark</em>'.
	 * @see org.nasdanika.html.model.app.NavigationBar#isDark()
	 * @see #getNavigationBar()
	 * @generated
	 */
	EAttribute getNavigationBar_Dark();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.NavigationBar#getExpand <em>Expand</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expand</em>'.
	 * @see org.nasdanika.html.model.app.NavigationBar#getExpand()
	 * @see #getNavigationBar()
	 * @generated
	 */
	EAttribute getNavigationBar_Expand();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.NavigationBar#getBackground <em>Background</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Background</em>'.
	 * @see org.nasdanika.html.model.app.NavigationBar#getBackground()
	 * @see #getNavigationBar()
	 * @generated
	 */
	EAttribute getNavigationBar_Background();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.NavigationPanel <em>Navigation Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation Panel</em>'.
	 * @see org.nasdanika.html.model.app.NavigationPanel
	 * @generated
	 */
	EClass getNavigationPanel();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.NavigationPanel#getStyle <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Style</em>'.
	 * @see org.nasdanika.html.model.app.NavigationPanel#getStyle()
	 * @see #getNavigationPanel()
	 * @generated
	 */
	EAttribute getNavigationPanel_Style();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.NavigationPanel#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.nasdanika.html.model.app.NavigationPanel#getId()
	 * @see #getNavigationPanel()
	 * @generated
	 */
	EAttribute getNavigationPanel_Id();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.NavigationPanel#getLabelTrimLength <em>Label Trim Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label Trim Length</em>'.
	 * @see org.nasdanika.html.model.app.NavigationPanel#getLabelTrimLength()
	 * @see #getNavigationPanel()
	 * @generated
	 */
	EAttribute getNavigationPanel_LabelTrimLength();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.NavigationPanel#isCollapsible <em>Collapsible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Collapsible</em>'.
	 * @see org.nasdanika.html.model.app.NavigationPanel#isCollapsible()
	 * @see #getNavigationPanel()
	 * @generated
	 */
	EAttribute getNavigationPanel_Collapsible();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.NavigationPanel#getJsTreeSearchThreshold <em>Js Tree Search Threshold</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Js Tree Search Threshold</em>'.
	 * @see org.nasdanika.html.model.app.NavigationPanel#getJsTreeSearchThreshold()
	 * @see #getNavigationPanel()
	 * @generated
	 */
	EAttribute getNavigationPanel_JsTreeSearchThreshold();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.ContentPanel <em>Content Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content Panel</em>'.
	 * @see org.nasdanika.html.model.app.ContentPanel
	 * @generated
	 */
	EClass getContentPanel();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.app.ContentPanel#getBreadcrumb <em>Breadcrumb</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Breadcrumb</em>'.
	 * @see org.nasdanika.html.model.app.ContentPanel#getBreadcrumb()
	 * @see #getContentPanel()
	 * @generated
	 */
	EReference getContentPanel_Breadcrumb();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.ContentPanel#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Title</em>'.
	 * @see org.nasdanika.html.model.app.ContentPanel#getTitle()
	 * @see #getContentPanel()
	 * @generated
	 */
	EReference getContentPanel_Title();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.ContentPanel#getLeftNavigation <em>Left Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Navigation</em>'.
	 * @see org.nasdanika.html.model.app.ContentPanel#getLeftNavigation()
	 * @see #getContentPanel()
	 * @generated
	 */
	EReference getContentPanel_LeftNavigation();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.ContentPanel#getRightNavigation <em>Right Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Navigation</em>'.
	 * @see org.nasdanika.html.model.app.ContentPanel#getRightNavigation()
	 * @see #getContentPanel()
	 * @generated
	 */
	EReference getContentPanel_RightNavigation();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.ContentPanel#getFloatLeftNavigation <em>Float Left Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Float Left Navigation</em>'.
	 * @see org.nasdanika.html.model.app.ContentPanel#getFloatLeftNavigation()
	 * @see #getContentPanel()
	 * @generated
	 */
	EReference getContentPanel_FloatLeftNavigation();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.ContentPanel#getFloatRightNavigation <em>Float Right Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Float Right Navigation</em>'.
	 * @see org.nasdanika.html.model.app.ContentPanel#getFloatRightNavigation()
	 * @see #getContentPanel()
	 * @generated
	 */
	EReference getContentPanel_FloatRightNavigation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.app.ContentPanel#getSections <em>Sections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sections</em>'.
	 * @see org.nasdanika.html.model.app.ContentPanel#getSections()
	 * @see #getContentPanel()
	 * @generated
	 */
	EReference getContentPanel_Sections();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.ContentPanel#getSectionColumns <em>Section Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Section Columns</em>'.
	 * @see org.nasdanika.html.model.app.ContentPanel#getSectionColumns()
	 * @see #getContentPanel()
	 * @generated
	 */
	EAttribute getContentPanel_SectionColumns();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.ContentPanel#getSectionStyle <em>Section Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Section Style</em>'.
	 * @see org.nasdanika.html.model.app.ContentPanel#getSectionStyle()
	 * @see #getContentPanel()
	 * @generated
	 */
	EAttribute getContentPanel_SectionStyle();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.Footer <em>Footer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Footer</em>'.
	 * @see org.nasdanika.html.model.app.Footer
	 * @generated
	 */
	EClass getFooter();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see org.nasdanika.html.model.app.Action
	 * @generated
	 */
	EClass getAction();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#getSectionColumns <em>Section Columns</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Section Columns</em>'.
	 * @see org.nasdanika.html.model.app.Action#getSectionColumns()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_SectionColumns();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#getSectionStyle <em>Section Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Section Style</em>'.
	 * @see org.nasdanika.html.model.app.Action#getSectionStyle()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_SectionStyle();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.app.Action#getSections <em>Sections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sections</em>'.
	 * @see org.nasdanika.html.model.app.Action#getSections()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Sections();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.app.Action#getNavigation <em>Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Navigation</em>'.
	 * @see org.nasdanika.html.model.app.Action#getNavigation()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Navigation();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.Action#getLeftNavigation <em>Left Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left Navigation</em>'.
	 * @see org.nasdanika.html.model.app.Action#getLeftNavigation()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_LeftNavigation();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.Action#getRightNavigation <em>Right Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right Navigation</em>'.
	 * @see org.nasdanika.html.model.app.Action#getRightNavigation()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_RightNavigation();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.Action#getFloatLeftNavigation <em>Float Left Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Float Left Navigation</em>'.
	 * @see org.nasdanika.html.model.app.Action#getFloatLeftNavigation()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_FloatLeftNavigation();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.Action#getFloatRightNavigation <em>Float Right Navigation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Float Right Navigation</em>'.
	 * @see org.nasdanika.html.model.app.Action#getFloatRightNavigation()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_FloatRightNavigation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.app.Action#getAnonymous <em>Anonymous</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Anonymous</em>'.
	 * @see org.nasdanika.html.model.app.Action#getAnonymous()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Anonymous();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.app.Action#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resources</em>'.
	 * @see org.nasdanika.html.model.app.Action#getResources()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Resources();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#isInline <em>Inline</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inline</em>'.
	 * @see org.nasdanika.html.model.app.Action#isInline()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Inline();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#isModalActivator <em>Modal Activator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modal Activator</em>'.
	 * @see org.nasdanika.html.model.app.Action#isModalActivator()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_ModalActivator();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.html.model.app.Action#createLink() <em>Create Link</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Create Link</em>' operation.
	 * @see org.nasdanika.html.model.app.Action#createLink()
	 * @generated
	 */
	EOperation getAction__CreateLink();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.ActionReference <em>Action Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Reference</em>'.
	 * @see org.nasdanika.html.model.app.ActionReference
	 * @generated
	 */
	EClass getActionReference();

	/**
	 * Returns the meta object for enum '{@link org.nasdanika.html.model.app.SectionStyle <em>Section Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Section Style</em>'.
	 * @see org.nasdanika.html.model.app.SectionStyle
	 * @generated
	 */
	EEnum getSectionStyle();

	/**
	 * Returns the meta object for enum '{@link org.nasdanika.html.model.app.NavigationPanelStyle <em>Navigation Panel Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Navigation Panel Style</em>'.
	 * @see org.nasdanika.html.model.app.NavigationPanelStyle
	 * @generated
	 */
	EEnum getNavigationPanelStyle();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	AppFactory getAppFactory();

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
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.LabelImpl <em>Label</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.LabelImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getLabel()
		 * @generated
		 */
		EClass LABEL = eINSTANCE.getLabel();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__ID = eINSTANCE.getLabel_Id();

		/**
		 * The meta object literal for the '<em><b>Text</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__TEXT = eINSTANCE.getLabel_Text();

		/**
		 * The meta object literal for the '<em><b>Icon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__ICON = eINSTANCE.getLabel_Icon();

		/**
		 * The meta object literal for the '<em><b>Tooltip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__TOOLTIP = eINSTANCE.getLabel_Tooltip();

		/**
		 * The meta object literal for the '<em><b>Outline</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__OUTLINE = eINSTANCE.getLabel_Outline();

		/**
		 * The meta object literal for the '<em><b>Notification</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__NOTIFICATION = eINSTANCE.getLabel_Notification();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LABEL__CHILDREN = eINSTANCE.getLabel_Children();

		/**
		 * The meta object literal for the '<em><b>Decorator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LABEL__DECORATOR = eINSTANCE.getLabel_Decorator();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.LinkImpl <em>Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.LinkImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getLink()
		 * @generated
		 */
		EClass LINK = eINSTANCE.getLink();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__LOCATION = eINSTANCE.getLink_Location();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__SCRIPT = eINSTANCE.getLink_Script();

		/**
		 * The meta object literal for the '<em><b>Modal</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK__MODAL = eINSTANCE.getLink_Modal();

		/**
		 * The meta object literal for the '<em><b>Confirmation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__CONFIRMATION = eINSTANCE.getLink_Confirmation();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__NAME = eINSTANCE.getLink_Name();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__TARGET = eINSTANCE.getLink_Target();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LINK__ACTION = eINSTANCE.getLink_Action();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.PageImpl <em>Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.PageImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getPage()
		 * @generated
		 */
		EClass PAGE = eINSTANCE.getPage();

		/**
		 * The meta object literal for the '<em><b>Fluid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__FLUID = eINSTANCE.getPage_Fluid();

		/**
		 * The meta object literal for the '<em><b>Header</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__HEADER = eINSTANCE.getPage_Header();

		/**
		 * The meta object literal for the '<em><b>Navigation Bar</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__NAVIGATION_BAR = eINSTANCE.getPage_NavigationBar();

		/**
		 * The meta object literal for the '<em><b>Navigation Panel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__NAVIGATION_PANEL = eINSTANCE.getPage_NavigationPanel();

		/**
		 * The meta object literal for the '<em><b>Content Panel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__CONTENT_PANEL = eINSTANCE.getPage_ContentPanel();

		/**
		 * The meta object literal for the '<em><b>Footer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__FOOTER = eINSTANCE.getPage_Footer();

		/**
		 * The meta object literal for the '<em><b>Content Row Appearance</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__CONTENT_ROW_APPEARANCE = eINSTANCE.getPage_ContentRowAppearance();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.PagePartImpl <em>Page Part</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.PagePartImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getPagePart()
		 * @generated
		 */
		EClass PAGE_PART = eINSTANCE.getPagePart();

		/**
		 * The meta object literal for the '<em><b>Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE_PART__ITEMS = eINSTANCE.getPagePart_Items();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.HeaderImpl <em>Header</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.HeaderImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getHeader()
		 * @generated
		 */
		EClass HEADER = eINSTANCE.getHeader();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HEADER__TITLE = eINSTANCE.getHeader_Title();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.NavigationBarImpl <em>Navigation Bar</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.NavigationBarImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getNavigationBar()
		 * @generated
		 */
		EClass NAVIGATION_BAR = eINSTANCE.getNavigationBar();

		/**
		 * The meta object literal for the '<em><b>Brand</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_BAR__BRAND = eINSTANCE.getNavigationBar_Brand();

		/**
		 * The meta object literal for the '<em><b>Dark</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAVIGATION_BAR__DARK = eINSTANCE.getNavigationBar_Dark();

		/**
		 * The meta object literal for the '<em><b>Expand</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAVIGATION_BAR__EXPAND = eINSTANCE.getNavigationBar_Expand();

		/**
		 * The meta object literal for the '<em><b>Background</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAVIGATION_BAR__BACKGROUND = eINSTANCE.getNavigationBar_Background();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.NavigationPanelImpl <em>Navigation Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.NavigationPanelImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getNavigationPanel()
		 * @generated
		 */
		EClass NAVIGATION_PANEL = eINSTANCE.getNavigationPanel();

		/**
		 * The meta object literal for the '<em><b>Style</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAVIGATION_PANEL__STYLE = eINSTANCE.getNavigationPanel_Style();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAVIGATION_PANEL__ID = eINSTANCE.getNavigationPanel_Id();

		/**
		 * The meta object literal for the '<em><b>Label Trim Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAVIGATION_PANEL__LABEL_TRIM_LENGTH = eINSTANCE.getNavigationPanel_LabelTrimLength();

		/**
		 * The meta object literal for the '<em><b>Collapsible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAVIGATION_PANEL__COLLAPSIBLE = eINSTANCE.getNavigationPanel_Collapsible();

		/**
		 * The meta object literal for the '<em><b>Js Tree Search Threshold</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAVIGATION_PANEL__JS_TREE_SEARCH_THRESHOLD = eINSTANCE.getNavigationPanel_JsTreeSearchThreshold();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.ContentPanelImpl <em>Content Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.ContentPanelImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getContentPanel()
		 * @generated
		 */
		EClass CONTENT_PANEL = eINSTANCE.getContentPanel();

		/**
		 * The meta object literal for the '<em><b>Breadcrumb</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_PANEL__BREADCRUMB = eINSTANCE.getContentPanel_Breadcrumb();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_PANEL__TITLE = eINSTANCE.getContentPanel_Title();

		/**
		 * The meta object literal for the '<em><b>Left Navigation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_PANEL__LEFT_NAVIGATION = eINSTANCE.getContentPanel_LeftNavigation();

		/**
		 * The meta object literal for the '<em><b>Right Navigation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_PANEL__RIGHT_NAVIGATION = eINSTANCE.getContentPanel_RightNavigation();

		/**
		 * The meta object literal for the '<em><b>Float Left Navigation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_PANEL__FLOAT_LEFT_NAVIGATION = eINSTANCE.getContentPanel_FloatLeftNavigation();

		/**
		 * The meta object literal for the '<em><b>Float Right Navigation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_PANEL__FLOAT_RIGHT_NAVIGATION = eINSTANCE.getContentPanel_FloatRightNavigation();

		/**
		 * The meta object literal for the '<em><b>Sections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_PANEL__SECTIONS = eINSTANCE.getContentPanel_Sections();

		/**
		 * The meta object literal for the '<em><b>Section Columns</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_PANEL__SECTION_COLUMNS = eINSTANCE.getContentPanel_SectionColumns();

		/**
		 * The meta object literal for the '<em><b>Section Style</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_PANEL__SECTION_STYLE = eINSTANCE.getContentPanel_SectionStyle();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.FooterImpl <em>Footer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.FooterImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getFooter()
		 * @generated
		 */
		EClass FOOTER = eINSTANCE.getFooter();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.ActionImpl <em>Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.ActionImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getAction()
		 * @generated
		 */
		EClass ACTION = eINSTANCE.getAction();

		/**
		 * The meta object literal for the '<em><b>Section Columns</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__SECTION_COLUMNS = eINSTANCE.getAction_SectionColumns();

		/**
		 * The meta object literal for the '<em><b>Section Style</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__SECTION_STYLE = eINSTANCE.getAction_SectionStyle();

		/**
		 * The meta object literal for the '<em><b>Sections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__SECTIONS = eINSTANCE.getAction_Sections();

		/**
		 * The meta object literal for the '<em><b>Navigation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__NAVIGATION = eINSTANCE.getAction_Navigation();

		/**
		 * The meta object literal for the '<em><b>Left Navigation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__LEFT_NAVIGATION = eINSTANCE.getAction_LeftNavigation();

		/**
		 * The meta object literal for the '<em><b>Right Navigation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__RIGHT_NAVIGATION = eINSTANCE.getAction_RightNavigation();

		/**
		 * The meta object literal for the '<em><b>Float Left Navigation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__FLOAT_LEFT_NAVIGATION = eINSTANCE.getAction_FloatLeftNavigation();

		/**
		 * The meta object literal for the '<em><b>Float Right Navigation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__FLOAT_RIGHT_NAVIGATION = eINSTANCE.getAction_FloatRightNavigation();

		/**
		 * The meta object literal for the '<em><b>Anonymous</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__ANONYMOUS = eINSTANCE.getAction_Anonymous();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__RESOURCES = eINSTANCE.getAction_Resources();

		/**
		 * The meta object literal for the '<em><b>Inline</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__INLINE = eINSTANCE.getAction_Inline();

		/**
		 * The meta object literal for the '<em><b>Modal Activator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__MODAL_ACTIVATOR = eINSTANCE.getAction_ModalActivator();

		/**
		 * The meta object literal for the '<em><b>Create Link</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ACTION___CREATE_LINK = eINSTANCE.getAction__CreateLink();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.ActionReferenceImpl <em>Action Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.ActionReferenceImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getActionReference()
		 * @generated
		 */
		EClass ACTION_REFERENCE = eINSTANCE.getActionReference();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.SectionStyle <em>Section Style</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.SectionStyle
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getSectionStyle()
		 * @generated
		 */
		EEnum SECTION_STYLE = eINSTANCE.getSectionStyle();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.NavigationPanelStyle <em>Navigation Panel Style</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.NavigationPanelStyle
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getNavigationPanelStyle()
		 * @generated
		 */
		EEnum NAVIGATION_PANEL_STYLE = eINSTANCE.getNavigationPanelStyle();

	}

} //AppPackage
