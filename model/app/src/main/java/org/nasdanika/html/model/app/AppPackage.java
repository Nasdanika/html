/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;

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
 * Application model.
 * <!-- end-model-doc -->
 * @see org.nasdanika.html.model.app.AppFactory
 * @model kind="package"
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
	String eNS_URI = "urn:org.nasdanika.html.model.app";

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
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__DESCRIPTION = BootstrapPackage.BOOTSTRAP_ELEMENT__DESCRIPTION;

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
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__TEXT = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__ICON = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__TOOLTIP = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__OUTLINE = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Notification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__NOTIFICATION = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__CHILDREN = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Help</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__HELP = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_FEATURE_COUNT = BootstrapPackage.BOOTSTRAP_ELEMENT_FEATURE_COUNT + 10;

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
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__DESCRIPTION = LABEL__DESCRIPTION;

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
	 * The feature id for the '<em><b>Children</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__CHILDREN = LABEL__CHILDREN;

	/**
	 * The feature id for the '<em><b>Help</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__HELP = LABEL__HELP;

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
	 * The feature id for the '<em><b>Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__BINDING = LABEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Confirmation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__CONFIRMATION = LABEL_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_FEATURE_COUNT = LABEL_FEATURE_COUNT + 4;

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
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__DESCRIPTION = BootstrapPackage.BOOTSTRAP_ELEMENT__DESCRIPTION;

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
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_PART__DESCRIPTION = BootstrapPackage.BOOTSTRAP_ELEMENT__DESCRIPTION;

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
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HEADER__DESCRIPTION = PAGE_PART__DESCRIPTION;

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
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR__DESCRIPTION = PAGE_PART__DESCRIPTION;

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
	 * The number of structural features of the '<em>Navigation Bar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_BAR_FEATURE_COUNT = PAGE_PART_FEATURE_COUNT + 1;

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
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL__DESCRIPTION = PAGE_PART__DESCRIPTION;

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
	 * The number of structural features of the '<em>Navigation Panel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_PANEL_FEATURE_COUNT = PAGE_PART_FEATURE_COUNT + 1;

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
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_PANEL__DESCRIPTION = PAGE_PART__DESCRIPTION;

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
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOOTER__DESCRIPTION = PAGE_PART__DESCRIPTION;

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
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__DESCRIPTION = LINK__DESCRIPTION;

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
	 * The feature id for the '<em><b>Children</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CHILDREN = LINK__CHILDREN;

	/**
	 * The feature id for the '<em><b>Help</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__HELP = LINK__HELP;

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
	 * The feature id for the '<em><b>Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__BINDING = LINK__BINDING;

	/**
	 * The feature id for the '<em><b>Confirmation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CONFIRMATION = LINK__CONFIRMATION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ID = LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Section Columns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__SECTION_COLUMNS = LINK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Section Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__SECTION_STYLE = LINK_FEATURE_COUNT + 2;

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
	 * The feature id for the '<em><b>Modal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__MODAL = LINK_FEATURE_COUNT + 11;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = LINK_FEATURE_COUNT + 12;

	/**
	 * The number of operations of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_OPERATION_COUNT = LINK_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.Color <em>Color</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.Color
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getColor()
	 * @generated
	 */
	int COLOR = 11;


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
	 * The meta object id for the '{@link org.nasdanika.html.model.app.SectionStyle <em>Section Style</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.SectionStyle
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getSectionStyle()
	 * @generated
	 */
	int SECTION_STYLE = 10;


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
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.Label#getHelp <em>Help</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Help</em>'.
	 * @see org.nasdanika.html.model.app.Label#getHelp()
	 * @see #getLabel()
	 * @generated
	 */
	EReference getLabel_Help();

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
	 * Returns the meta object for the reference '{@link org.nasdanika.html.model.app.Label#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Children</em>'.
	 * @see org.nasdanika.html.model.app.Label#getChildren()
	 * @see #getLabel()
	 * @generated
	 */
	EReference getLabel_Children();

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
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Link#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Binding</em>'.
	 * @see org.nasdanika.html.model.app.Link#getBinding()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Binding();

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
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.ContentPanel <em>Content Panel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content Panel</em>'.
	 * @see org.nasdanika.html.model.app.ContentPanel
	 * @generated
	 */
	EClass getContentPanel();

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
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.nasdanika.html.model.app.Action#getId()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Id();

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
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#isModal <em>Modal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modal</em>'.
	 * @see org.nasdanika.html.model.app.Action#isModal()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Modal();

	/**
	 * Returns the meta object for enum '{@link org.nasdanika.html.model.app.Color <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Color</em>'.
	 * @see org.nasdanika.html.model.app.Color
	 * @generated
	 */
	EEnum getColor();

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
	 * Returns the meta object for enum '{@link org.nasdanika.html.model.app.SectionStyle <em>Section Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Section Style</em>'.
	 * @see org.nasdanika.html.model.app.SectionStyle
	 * @generated
	 */
	EEnum getSectionStyle();

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
		 * The meta object literal for the '<em><b>Help</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LABEL__HELP = eINSTANCE.getLabel_Help();

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
		 * The meta object literal for the '<em><b>Children</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LABEL__CHILDREN = eINSTANCE.getLabel_Children();

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
		 * The meta object literal for the '<em><b>Binding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__BINDING = eINSTANCE.getLink_Binding();

		/**
		 * The meta object literal for the '<em><b>Confirmation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__CONFIRMATION = eINSTANCE.getLink_Confirmation();

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
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.ContentPanelImpl <em>Content Panel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.ContentPanelImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getContentPanel()
		 * @generated
		 */
		EClass CONTENT_PANEL = eINSTANCE.getContentPanel();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_PANEL__TITLE = eINSTANCE.getContentPanel_Title();

		/**
		 * The meta object literal for the '<em><b>Breadcrumb</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_PANEL__BREADCRUMB = eINSTANCE.getContentPanel_Breadcrumb();

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
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__ID = eINSTANCE.getAction_Id();

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
		 * The meta object literal for the '<em><b>Modal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__MODAL = eINSTANCE.getAction_Modal();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.Color <em>Color</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.Color
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getColor()
		 * @generated
		 */
		EEnum COLOR = eINSTANCE.getColor();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.NavigationPanelStyle <em>Navigation Panel Style</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.NavigationPanelStyle
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getNavigationPanelStyle()
		 * @generated
		 */
		EEnum NAVIGATION_PANEL_STYLE = eINSTANCE.getNavigationPanelStyle();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.SectionStyle <em>Section Style</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.SectionStyle
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getSectionStyle()
		 * @generated
		 */
		EEnum SECTION_STYLE = eINSTANCE.getSectionStyle();

	}

} //AppPackage
