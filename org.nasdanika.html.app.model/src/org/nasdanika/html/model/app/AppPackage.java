/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
	String eNS_URI = "urn:org.nasdanika.html.app";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.html.app";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AppPackage eINSTANCE = org.nasdanika.html.model.app.impl.AppPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.html.app.Label <em>ILabel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.app.Label
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getILabel()
	 * @generated
	 */
	int ILABEL = 0;

	/**
	 * The number of structural features of the '<em>ILabel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ILABEL_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>ILabel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ILABEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.LabelImpl <em>Label</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.LabelImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getLabel()
	 * @generated
	 */
	int LABEL = 1;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__COLOR = ILABEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__DESCRIPTION = ILABEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__ICON = ILABEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__ID = ILABEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__TEXT = ILABEL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__TOOLTIP = ILABEL_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__OUTLINE = ILABEL_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Notification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__NOTIFICATION = ILABEL_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_FEATURE_COUNT = ILABEL_FEATURE_COUNT + 8;

	/**
	 * The number of operations of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_OPERATION_COUNT = ILABEL_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link org.nasdanika.html.app.Themed <em>IThemed</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.app.Themed
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getIThemed()
	 * @generated
	 */
	int ITHEMED = 2;

	/**
	 * The number of structural features of the '<em>IThemed</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITHEMED_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>IThemed</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ITHEMED_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.app.Action <em>IAction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.app.Action
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getIAction()
	 * @generated
	 */
	int IACTION = 3;

	/**
	 * The number of structural features of the '<em>IAction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IACTION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>IAction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IACTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.ActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.ActionImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getAction()
	 * @generated
	 */
	int ACTION = 4;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__COLOR = IACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__DESCRIPTION = IACTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ICON = IACTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ID = IACTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__TEXT = IACTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__TOOLTIP = IACTION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__OUTLINE = IACTION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Notification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__NOTIFICATION = IACTION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CHILDREN = IACTION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Confirmation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CONFIRMATION = IACTION_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Context Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CONTEXT_ACTIONS = IACTION_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Float Right</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__FLOAT_RIGHT = IACTION_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ROLES = IACTION_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__DISABLED = IACTION_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Activator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ACTIVATOR = IACTION_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ITERATOR = IACTION_FEATURE_COUNT + 15;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = IACTION_FEATURE_COUNT + 16;

	/**
	 * The number of operations of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_OPERATION_COUNT = IACTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.ThemedActionImpl <em>Themed Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.ThemedActionImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getThemedAction()
	 * @generated
	 */
	int THEMED_ACTION = 5;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__COLOR = ACTION__COLOR;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__DESCRIPTION = ACTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__ICON = ACTION__ICON;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__ID = ACTION__ID;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__TEXT = ACTION__TEXT;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__TOOLTIP = ACTION__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__OUTLINE = ACTION__OUTLINE;

	/**
	 * The feature id for the '<em><b>Notification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__NOTIFICATION = ACTION__NOTIFICATION;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__CHILDREN = ACTION__CHILDREN;

	/**
	 * The feature id for the '<em><b>Confirmation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__CONFIRMATION = ACTION__CONFIRMATION;

	/**
	 * The feature id for the '<em><b>Context Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__CONTEXT_ACTIONS = ACTION__CONTEXT_ACTIONS;

	/**
	 * The feature id for the '<em><b>Float Right</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__FLOAT_RIGHT = ACTION__FLOAT_RIGHT;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__ROLES = ACTION__ROLES;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__DISABLED = ACTION__DISABLED;

	/**
	 * The feature id for the '<em><b>Activator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__ACTIVATOR = ACTION__ACTIVATOR;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__ITERATOR = ACTION__ITERATOR;

	/**
	 * The feature id for the '<em><b>Theme</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION__THEME = ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Themed Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Themed Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_ACTION_OPERATION_COUNT = ACTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.ContentActionImpl <em>Content Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.ContentActionImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getContentAction()
	 * @generated
	 */
	int CONTENT_ACTION = 6;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__COLOR = ACTION__COLOR;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__DESCRIPTION = ACTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__ICON = ACTION__ICON;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__ID = ACTION__ID;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__TEXT = ACTION__TEXT;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__TOOLTIP = ACTION__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__OUTLINE = ACTION__OUTLINE;

	/**
	 * The feature id for the '<em><b>Notification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__NOTIFICATION = ACTION__NOTIFICATION;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__CHILDREN = ACTION__CHILDREN;

	/**
	 * The feature id for the '<em><b>Confirmation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__CONFIRMATION = ACTION__CONFIRMATION;

	/**
	 * The feature id for the '<em><b>Context Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__CONTEXT_ACTIONS = ACTION__CONTEXT_ACTIONS;

	/**
	 * The feature id for the '<em><b>Float Right</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__FLOAT_RIGHT = ACTION__FLOAT_RIGHT;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__ROLES = ACTION__ROLES;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__DISABLED = ACTION__DISABLED;

	/**
	 * The feature id for the '<em><b>Activator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__ACTIVATOR = ACTION__ACTIVATOR;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__ITERATOR = ACTION__ITERATOR;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__CONTENT = ACTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Content Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION__CONTENT_TYPE = ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Content Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION_FEATURE_COUNT = ACTION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Content Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_ACTION_OPERATION_COUNT = ACTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.ThemedContentActionImpl <em>Themed Content Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.ThemedContentActionImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getThemedContentAction()
	 * @generated
	 */
	int THEMED_CONTENT_ACTION = 7;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__COLOR = CONTENT_ACTION__COLOR;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__DESCRIPTION = CONTENT_ACTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__ICON = CONTENT_ACTION__ICON;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__ID = CONTENT_ACTION__ID;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__TEXT = CONTENT_ACTION__TEXT;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__TOOLTIP = CONTENT_ACTION__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__OUTLINE = CONTENT_ACTION__OUTLINE;

	/**
	 * The feature id for the '<em><b>Notification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__NOTIFICATION = CONTENT_ACTION__NOTIFICATION;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__CHILDREN = CONTENT_ACTION__CHILDREN;

	/**
	 * The feature id for the '<em><b>Confirmation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__CONFIRMATION = CONTENT_ACTION__CONFIRMATION;

	/**
	 * The feature id for the '<em><b>Context Actions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__CONTEXT_ACTIONS = CONTENT_ACTION__CONTEXT_ACTIONS;

	/**
	 * The feature id for the '<em><b>Float Right</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__FLOAT_RIGHT = CONTENT_ACTION__FLOAT_RIGHT;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__ROLES = CONTENT_ACTION__ROLES;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__DISABLED = CONTENT_ACTION__DISABLED;

	/**
	 * The feature id for the '<em><b>Activator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__ACTIVATOR = CONTENT_ACTION__ACTIVATOR;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__ITERATOR = CONTENT_ACTION__ITERATOR;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__CONTENT = CONTENT_ACTION__CONTENT;

	/**
	 * The feature id for the '<em><b>Content Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__CONTENT_TYPE = CONTENT_ACTION__CONTENT_TYPE;

	/**
	 * The feature id for the '<em><b>Theme</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION__THEME = CONTENT_ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Themed Content Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION_FEATURE_COUNT = CONTENT_ACTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Themed Content Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THEMED_CONTENT_ACTION_OPERATION_COUNT = CONTENT_ACTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.app.ActionActivator <em>IAction Activator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.app.ActionActivator
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getIActionActivator()
	 * @generated
	 */
	int IACTION_ACTIVATOR = 8;

	/**
	 * The number of structural features of the '<em>IAction Activator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IACTION_ACTIVATOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>IAction Activator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IACTION_ACTIVATOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.app.NavigationActionActivator <em>INavigation Action Activator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.app.NavigationActionActivator
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getINavigationActionActivator()
	 * @generated
	 */
	int INAVIGATION_ACTION_ACTIVATOR = 9;

	/**
	 * The number of structural features of the '<em>INavigation Action Activator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INAVIGATION_ACTION_ACTIVATOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>INavigation Action Activator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INAVIGATION_ACTION_ACTIVATOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.app.ScriptActionActivator <em>IScript Action Activator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.app.ScriptActionActivator
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getIScriptActionActivator()
	 * @generated
	 */
	int ISCRIPT_ACTION_ACTIVATOR = 10;

	/**
	 * The number of structural features of the '<em>IScript Action Activator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISCRIPT_ACTION_ACTIVATOR_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>IScript Action Activator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISCRIPT_ACTION_ACTIVATOR_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.ActionActivatorImpl <em>Action Activator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.ActionActivatorImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getActionActivator()
	 * @generated
	 */
	int ACTION_ACTIVATOR = 11;

	/**
	 * The number of structural features of the '<em>Action Activator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_ACTIVATOR_FEATURE_COUNT = IACTION_ACTIVATOR_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Action Activator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_ACTIVATOR_OPERATION_COUNT = IACTION_ACTIVATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.NavigationActionActivatorImpl <em>Navigation Action Activator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.NavigationActionActivatorImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getNavigationActionActivator()
	 * @generated
	 */
	int NAVIGATION_ACTION_ACTIVATOR = 12;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_ACTION_ACTIVATOR__URL = INAVIGATION_ACTION_ACTIVATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Navigation Action Activator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_ACTION_ACTIVATOR_FEATURE_COUNT = INAVIGATION_ACTION_ACTIVATOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Navigation Action Activator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAVIGATION_ACTION_ACTIVATOR_OPERATION_COUNT = INAVIGATION_ACTION_ACTIVATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.ScriptActionActivatorImpl <em>Script Action Activator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.ScriptActionActivatorImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getScriptActionActivator()
	 * @generated
	 */
	int SCRIPT_ACTION_ACTIVATOR = 13;

	/**
	 * The feature id for the '<em><b>Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_ACTION_ACTIVATOR__CODE = ISCRIPT_ACTION_ACTIVATOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Script Action Activator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_ACTION_ACTIVATOR_FEATURE_COUNT = ISCRIPT_ACTION_ACTIVATOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Script Action Activator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCRIPT_ACTION_ACTIVATOR_OPERATION_COUNT = ISCRIPT_ACTION_ACTIVATOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.ContentType <em>Content Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.ContentType
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getContentType()
	 * @generated
	 */
	int CONTENT_TYPE = 14;

	/**
	 * The meta object id for the '<em>Color</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.bootstrap.Color
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getColor()
	 * @generated
	 */
	int COLOR = 15;


	/**
	 * The meta object id for the '<em>Theme</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.bootstrap.Theme
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getTheme()
	 * @generated
	 */
	int THEME = 16;


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
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Label#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see org.nasdanika.html.model.app.Label#getColor()
	 * @see #getLabel()
	 * @generated
	 */
	EAttribute getLabel_Color();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Label#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.nasdanika.html.model.app.Label#getDescription()
	 * @see #getLabel()
	 * @generated
	 */
	EAttribute getLabel_Description();

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
	 * Returns the meta object for class '{@link org.nasdanika.html.app.Themed <em>IThemed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IThemed</em>'.
	 * @see org.nasdanika.html.app.Themed
	 * @model instanceClass="org.nasdanika.html.app.Themed"
	 * @generated
	 */
	EClass getIThemed();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.app.Action <em>IAction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IAction</em>'.
	 * @see org.nasdanika.html.app.Action
	 * @model instanceClass="org.nasdanika.html.app.Action"
	 * @generated
	 */
	EClass getIAction();

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
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.app.Action#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.nasdanika.html.model.app.Action#getChildren()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Children();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#getConfirmation <em>Confirmation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Confirmation</em>'.
	 * @see org.nasdanika.html.model.app.Action#getConfirmation()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Confirmation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.app.Action#getContextActions <em>Context Actions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Context Actions</em>'.
	 * @see org.nasdanika.html.model.app.Action#getContextActions()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_ContextActions();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#isFloatRight <em>Float Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Float Right</em>'.
	 * @see org.nasdanika.html.model.app.Action#isFloatRight()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_FloatRight();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.html.model.app.Action#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Roles</em>'.
	 * @see org.nasdanika.html.model.app.Action#getRoles()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Roles();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#isDisabled <em>Disabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Disabled</em>'.
	 * @see org.nasdanika.html.model.app.Action#isDisabled()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Disabled();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.html.model.app.Action#getActivator <em>Activator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Activator</em>'.
	 * @see org.nasdanika.html.model.app.Action#getActivator()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Activator();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#getIterator <em>Iterator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Iterator</em>'.
	 * @see org.nasdanika.html.model.app.Action#getIterator()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Iterator();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.ThemedAction <em>Themed Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Themed Action</em>'.
	 * @see org.nasdanika.html.model.app.ThemedAction
	 * @generated
	 */
	EClass getThemedAction();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.ThemedAction#getTheme <em>Theme</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Theme</em>'.
	 * @see org.nasdanika.html.model.app.ThemedAction#getTheme()
	 * @see #getThemedAction()
	 * @generated
	 */
	EAttribute getThemedAction_Theme();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.ContentAction <em>Content Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content Action</em>'.
	 * @see org.nasdanika.html.model.app.ContentAction
	 * @generated
	 */
	EClass getContentAction();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.ContentAction#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see org.nasdanika.html.model.app.ContentAction#getContent()
	 * @see #getContentAction()
	 * @generated
	 */
	EAttribute getContentAction_Content();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.ContentAction#getContentType <em>Content Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content Type</em>'.
	 * @see org.nasdanika.html.model.app.ContentAction#getContentType()
	 * @see #getContentAction()
	 * @generated
	 */
	EAttribute getContentAction_ContentType();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.ThemedContentAction <em>Themed Content Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Themed Content Action</em>'.
	 * @see org.nasdanika.html.model.app.ThemedContentAction
	 * @generated
	 */
	EClass getThemedContentAction();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.app.ActionActivator <em>IAction Activator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IAction Activator</em>'.
	 * @see org.nasdanika.html.app.ActionActivator
	 * @model instanceClass="org.nasdanika.html.app.ActionActivator"
	 * @generated
	 */
	EClass getIActionActivator();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.app.NavigationActionActivator <em>INavigation Action Activator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>INavigation Action Activator</em>'.
	 * @see org.nasdanika.html.app.NavigationActionActivator
	 * @model instanceClass="org.nasdanika.html.app.NavigationActionActivator"
	 * @generated
	 */
	EClass getINavigationActionActivator();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.app.ScriptActionActivator <em>IScript Action Activator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IScript Action Activator</em>'.
	 * @see org.nasdanika.html.app.ScriptActionActivator
	 * @model instanceClass="org.nasdanika.html.app.ScriptActionActivator"
	 * @generated
	 */
	EClass getIScriptActionActivator();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.ActionActivator <em>Action Activator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action Activator</em>'.
	 * @see org.nasdanika.html.model.app.ActionActivator
	 * @generated
	 */
	EClass getActionActivator();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.NavigationActionActivator <em>Navigation Action Activator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation Action Activator</em>'.
	 * @see org.nasdanika.html.model.app.NavigationActionActivator
	 * @generated
	 */
	EClass getNavigationActionActivator();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.NavigationActionActivator#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see org.nasdanika.html.model.app.NavigationActionActivator#getUrl()
	 * @see #getNavigationActionActivator()
	 * @generated
	 */
	EAttribute getNavigationActionActivator_Url();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.ScriptActionActivator <em>Script Action Activator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script Action Activator</em>'.
	 * @see org.nasdanika.html.model.app.ScriptActionActivator
	 * @generated
	 */
	EClass getScriptActionActivator();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.ScriptActionActivator#getCode <em>Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code</em>'.
	 * @see org.nasdanika.html.model.app.ScriptActionActivator#getCode()
	 * @see #getScriptActionActivator()
	 * @generated
	 */
	EAttribute getScriptActionActivator_Code();

	/**
	 * Returns the meta object for enum '{@link org.nasdanika.html.model.app.ContentType <em>Content Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Content Type</em>'.
	 * @see org.nasdanika.html.model.app.ContentType
	 * @generated
	 */
	EEnum getContentType();

	/**
	 * Returns the meta object for data type '{@link org.nasdanika.html.bootstrap.Color <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Color</em>'.
	 * @see org.nasdanika.html.bootstrap.Color
	 * @model instanceClass="org.nasdanika.html.bootstrap.Color"
	 * @generated
	 */
	EDataType getColor();

	/**
	 * Returns the meta object for data type '{@link org.nasdanika.html.bootstrap.Theme <em>Theme</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Theme</em>'.
	 * @see org.nasdanika.html.bootstrap.Theme
	 * @model instanceClass="org.nasdanika.html.bootstrap.Theme"
	 * @generated
	 */
	EDataType getTheme();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.html.app.Label <em>ILabel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ILabel</em>'.
	 * @see org.nasdanika.html.app.Label
	 * @model instanceClass="org.nasdanika.html.app.Label"
	 * @generated
	 */
	EClass getILabel();

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
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__COLOR = eINSTANCE.getLabel_Color();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__DESCRIPTION = eINSTANCE.getLabel_Description();

		/**
		 * The meta object literal for the '<em><b>Icon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__ICON = eINSTANCE.getLabel_Icon();

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
		 * The meta object literal for the '{@link org.nasdanika.html.app.Themed <em>IThemed</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.app.Themed
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getIThemed()
		 * @generated
		 */
		EClass ITHEMED = eINSTANCE.getIThemed();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.app.Action <em>IAction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.app.Action
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getIAction()
		 * @generated
		 */
		EClass IACTION = eINSTANCE.getIAction();

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
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__CHILDREN = eINSTANCE.getAction_Children();

		/**
		 * The meta object literal for the '<em><b>Confirmation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__CONFIRMATION = eINSTANCE.getAction_Confirmation();

		/**
		 * The meta object literal for the '<em><b>Context Actions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__CONTEXT_ACTIONS = eINSTANCE.getAction_ContextActions();

		/**
		 * The meta object literal for the '<em><b>Float Right</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__FLOAT_RIGHT = eINSTANCE.getAction_FloatRight();

		/**
		 * The meta object literal for the '<em><b>Roles</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__ROLES = eINSTANCE.getAction_Roles();

		/**
		 * The meta object literal for the '<em><b>Disabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__DISABLED = eINSTANCE.getAction_Disabled();

		/**
		 * The meta object literal for the '<em><b>Activator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__ACTIVATOR = eINSTANCE.getAction_Activator();

		/**
		 * The meta object literal for the '<em><b>Iterator</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__ITERATOR = eINSTANCE.getAction_Iterator();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.ThemedActionImpl <em>Themed Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.ThemedActionImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getThemedAction()
		 * @generated
		 */
		EClass THEMED_ACTION = eINSTANCE.getThemedAction();

		/**
		 * The meta object literal for the '<em><b>Theme</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute THEMED_ACTION__THEME = eINSTANCE.getThemedAction_Theme();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.ContentActionImpl <em>Content Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.ContentActionImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getContentAction()
		 * @generated
		 */
		EClass CONTENT_ACTION = eINSTANCE.getContentAction();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_ACTION__CONTENT = eINSTANCE.getContentAction_Content();

		/**
		 * The meta object literal for the '<em><b>Content Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONTENT_ACTION__CONTENT_TYPE = eINSTANCE.getContentAction_ContentType();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.ThemedContentActionImpl <em>Themed Content Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.ThemedContentActionImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getThemedContentAction()
		 * @generated
		 */
		EClass THEMED_CONTENT_ACTION = eINSTANCE.getThemedContentAction();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.app.ActionActivator <em>IAction Activator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.app.ActionActivator
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getIActionActivator()
		 * @generated
		 */
		EClass IACTION_ACTIVATOR = eINSTANCE.getIActionActivator();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.app.NavigationActionActivator <em>INavigation Action Activator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.app.NavigationActionActivator
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getINavigationActionActivator()
		 * @generated
		 */
		EClass INAVIGATION_ACTION_ACTIVATOR = eINSTANCE.getINavigationActionActivator();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.app.ScriptActionActivator <em>IScript Action Activator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.app.ScriptActionActivator
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getIScriptActionActivator()
		 * @generated
		 */
		EClass ISCRIPT_ACTION_ACTIVATOR = eINSTANCE.getIScriptActionActivator();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.ActionActivatorImpl <em>Action Activator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.ActionActivatorImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getActionActivator()
		 * @generated
		 */
		EClass ACTION_ACTIVATOR = eINSTANCE.getActionActivator();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.NavigationActionActivatorImpl <em>Navigation Action Activator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.NavigationActionActivatorImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getNavigationActionActivator()
		 * @generated
		 */
		EClass NAVIGATION_ACTION_ACTIVATOR = eINSTANCE.getNavigationActionActivator();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAVIGATION_ACTION_ACTIVATOR__URL = eINSTANCE.getNavigationActionActivator_Url();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.ScriptActionActivatorImpl <em>Script Action Activator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.ScriptActionActivatorImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getScriptActionActivator()
		 * @generated
		 */
		EClass SCRIPT_ACTION_ACTIVATOR = eINSTANCE.getScriptActionActivator();

		/**
		 * The meta object literal for the '<em><b>Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCRIPT_ACTION_ACTIVATOR__CODE = eINSTANCE.getScriptActionActivator_Code();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.ContentType <em>Content Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.ContentType
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getContentType()
		 * @generated
		 */
		EEnum CONTENT_TYPE = eINSTANCE.getContentType();

		/**
		 * The meta object literal for the '<em>Color</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.bootstrap.Color
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getColor()
		 * @generated
		 */
		EDataType COLOR = eINSTANCE.getColor();

		/**
		 * The meta object literal for the '<em>Theme</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.bootstrap.Theme
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getTheme()
		 * @generated
		 */
		EDataType THEME = eINSTANCE.getTheme();

		/**
		 * The meta object literal for the '{@link org.nasdanika.html.app.Label <em>ILabel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.app.Label
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getILabel()
		 * @generated
		 */
		EClass ILABEL = eINSTANCE.getILabel();

	}

} //AppPackage
