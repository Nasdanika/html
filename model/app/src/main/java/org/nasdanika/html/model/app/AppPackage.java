/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__ID = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__COLOR = 2;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__TEXT = 3;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__ICON = 4;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__TOOLTIP = 5;

	/**
	 * The feature id for the '<em><b>Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__OUTLINE = 6;

	/**
	 * The feature id for the '<em><b>Notification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__NOTIFICATION = 7;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__CHILDREN = 8;

	/**
	 * The number of structural features of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_OPERATION_COUNT = 0;

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
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__ID = LABEL__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__DESCRIPTION = LABEL__DESCRIPTION;

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
	 * The number of structural features of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_FEATURE_COUNT = LABEL_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_OPERATION_COUNT = LABEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.ActionImpl <em>Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.ActionImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getAction()
	 * @generated
	 */
	int ACTION = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ID = LINK__ID;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__DESCRIPTION = LINK__DESCRIPTION;

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
	 * The feature id for the '<em><b>Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ROLE = LINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Section Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__SECTION_STYLE = LINK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Section Columns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__SECTION_COLUMNS = LINK_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Confirmation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CONFIRMATION = LINK_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__DISABLED = LINK_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Inline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__INLINE = LINK_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Modal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__MODAL = LINK_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Content</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CONTENT = LINK_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Sections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__SECTIONS = LINK_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Context</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CONTEXT = LINK_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Content Left</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CONTENT_LEFT = LINK_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Content Right</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CONTENT_RIGHT = LINK_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Float Left</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__FLOAT_LEFT = LINK_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Float Right</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__FLOAT_RIGHT = LINK_FEATURE_COUNT + 13;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = LINK_FEATURE_COUNT + 14;

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
	int COLOR = 4;


	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.SectionStyle <em>Section Style</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.SectionStyle
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getSectionStyle()
	 * @generated
	 */
	int SECTION_STYLE = 3;


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
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.Action <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Action</em>'.
	 * @see org.nasdanika.html.model.app.Action
	 * @generated
	 */
	EClass getAction();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#getRole <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Role</em>'.
	 * @see org.nasdanika.html.model.app.Action#getRole()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Role();

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
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.app.Action#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Content</em>'.
	 * @see org.nasdanika.html.model.app.Action#getContent()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Content();

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
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.app.Action#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Context</em>'.
	 * @see org.nasdanika.html.model.app.Action#getContext()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_Context();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.app.Action#getContentLeft <em>Content Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Content Left</em>'.
	 * @see org.nasdanika.html.model.app.Action#getContentLeft()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_ContentLeft();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.app.Action#getContentRight <em>Content Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Content Right</em>'.
	 * @see org.nasdanika.html.model.app.Action#getContentRight()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_ContentRight();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.app.Action#getFloatLeft <em>Float Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Float Left</em>'.
	 * @see org.nasdanika.html.model.app.Action#getFloatLeft()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_FloatLeft();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.html.model.app.Action#getFloatRight <em>Float Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Float Right</em>'.
	 * @see org.nasdanika.html.model.app.Action#getFloatRight()
	 * @see #getAction()
	 * @generated
	 */
	EReference getAction_FloatRight();

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
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__ID = eINSTANCE.getLabel_Id();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__COLOR = eINSTANCE.getLabel_Color();

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
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LABEL__DESCRIPTION = eINSTANCE.getLabel_Description();

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
		 * The meta object literal for the '<em><b>Role</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__ROLE = eINSTANCE.getAction_Role();

		/**
		 * The meta object literal for the '<em><b>Section Style</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__SECTION_STYLE = eINSTANCE.getAction_SectionStyle();

		/**
		 * The meta object literal for the '<em><b>Section Columns</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__SECTION_COLUMNS = eINSTANCE.getAction_SectionColumns();

		/**
		 * The meta object literal for the '<em><b>Confirmation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__CONFIRMATION = eINSTANCE.getAction_Confirmation();

		/**
		 * The meta object literal for the '<em><b>Disabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__DISABLED = eINSTANCE.getAction_Disabled();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__CONTENT = eINSTANCE.getAction_Content();

		/**
		 * The meta object literal for the '<em><b>Sections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__SECTIONS = eINSTANCE.getAction_Sections();

		/**
		 * The meta object literal for the '<em><b>Context</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__CONTEXT = eINSTANCE.getAction_Context();

		/**
		 * The meta object literal for the '<em><b>Content Left</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__CONTENT_LEFT = eINSTANCE.getAction_ContentLeft();

		/**
		 * The meta object literal for the '<em><b>Content Right</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__CONTENT_RIGHT = eINSTANCE.getAction_ContentRight();

		/**
		 * The meta object literal for the '<em><b>Float Left</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__FLOAT_LEFT = eINSTANCE.getAction_FloatLeft();

		/**
		 * The meta object literal for the '<em><b>Float Right</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTION__FLOAT_RIGHT = eINSTANCE.getAction_FloatRight();

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
