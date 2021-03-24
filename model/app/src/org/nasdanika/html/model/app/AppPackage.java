/**
 */
package org.nasdanika.html.model.app;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;

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
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__TEXT = ILABEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__ICON = ILABEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__TOOLTIP = ILABEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__OUTLINE = ILABEL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Notification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL__NOTIFICATION = ILABEL_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_FEATURE_COUNT = ILABEL_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Label</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LABEL_OPERATION_COUNT = ILABEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.html.model.app.impl.CategoryImpl <em>Category</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.model.app.impl.CategoryImpl
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getCategory()
	 * @generated
	 */
	int CATEGORY = 2;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__COLOR = LABEL__COLOR;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__TEXT = LABEL__TEXT;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__ICON = LABEL__ICON;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__TOOLTIP = LABEL__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__OUTLINE = LABEL__OUTLINE;

	/**
	 * The feature id for the '<em><b>Notification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__NOTIFICATION = LABEL__NOTIFICATION;

	/**
	 * The number of structural features of the '<em>Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_FEATURE_COUNT = LABEL_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Category</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY_OPERATION_COUNT = LABEL_OPERATION_COUNT + 0;

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
	int ACTION__COLOR = LABEL__COLOR;

	/**
	 * The feature id for the '<em><b>Text</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__TEXT = LABEL__TEXT;

	/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ICON = LABEL__ICON;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__TOOLTIP = LABEL__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Outline</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__OUTLINE = LABEL__OUTLINE;

	/**
	 * The feature id for the '<em><b>Notification</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__NOTIFICATION = LABEL__NOTIFICATION;

	/**
	 * The feature id for the '<em><b>Role</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__ROLE = LABEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Section Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__SECTION_STYLE = LABEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Section Columns</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__SECTION_COLUMNS = LABEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__LOCATION = LABEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Script</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__SCRIPT = LABEL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__BINDING = LABEL_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Confirmation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CONFIRMATION = LABEL_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Disabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__DISABLED = LABEL_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Markdown Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__MARKDOWN_CONTENT = LABEL_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Page Template</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__PAGE_TEMPLATE = LABEL_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION__CONTENT = LABEL_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_FEATURE_COUNT = LABEL_FEATURE_COUNT + 11;

	/**
	 * The number of operations of the '<em>Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTION_OPERATION_COUNT = LABEL_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '<em>Color</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.bootstrap.Color
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getColor()
	 * @generated
	 */
	int COLOR = 5;


	/**
	 * The meta object id for the '<em>Section Style</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.html.app.SectionStyle
	 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getSectionStyle()
	 * @generated
	 */
	int SECTION_STYLE = 6;


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
	 * Returns the meta object for class '{@link org.nasdanika.html.model.app.Category <em>Category</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Category</em>'.
	 * @see org.nasdanika.html.model.app.Category
	 * @generated
	 */
	EClass getCategory();

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
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.nasdanika.html.model.app.Action#getLocation()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Location();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#getScript <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Script</em>'.
	 * @see org.nasdanika.html.model.app.Action#getScript()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Script();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Binding</em>'.
	 * @see org.nasdanika.html.model.app.Action#getBinding()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Binding();

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
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#getMarkdownContent <em>Markdown Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Markdown Content</em>'.
	 * @see org.nasdanika.html.model.app.Action#getMarkdownContent()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_MarkdownContent();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#getPageTemplate <em>Page Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Template</em>'.
	 * @see org.nasdanika.html.model.app.Action#getPageTemplate()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_PageTemplate();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.html.model.app.Action#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see org.nasdanika.html.model.app.Action#getContent()
	 * @see #getAction()
	 * @generated
	 */
	EAttribute getAction_Content();

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
	 * Returns the meta object for data type '{@link org.nasdanika.html.app.SectionStyle <em>Section Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Section Style</em>'.
	 * @see org.nasdanika.html.app.SectionStyle
	 * @model instanceClass="org.nasdanika.html.app.SectionStyle"
	 * @generated
	 */
	EDataType getSectionStyle();

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
		 * The meta object literal for the '{@link org.nasdanika.html.app.Label <em>ILabel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.app.Label
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getILabel()
		 * @generated
		 */
		EClass ILABEL = eINSTANCE.getILabel();

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
		 * The meta object literal for the '{@link org.nasdanika.html.model.app.impl.CategoryImpl <em>Category</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.model.app.impl.CategoryImpl
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getCategory()
		 * @generated
		 */
		EClass CATEGORY = eINSTANCE.getCategory();

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
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__LOCATION = eINSTANCE.getAction_Location();

		/**
		 * The meta object literal for the '<em><b>Script</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__SCRIPT = eINSTANCE.getAction_Script();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__BINDING = eINSTANCE.getAction_Binding();

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
		 * The meta object literal for the '<em><b>Markdown Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__MARKDOWN_CONTENT = eINSTANCE.getAction_MarkdownContent();

		/**
		 * The meta object literal for the '<em><b>Page Template</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__PAGE_TEMPLATE = eINSTANCE.getAction_PageTemplate();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTION__CONTENT = eINSTANCE.getAction_Content();

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
		 * The meta object literal for the '<em>Section Style</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.html.app.SectionStyle
		 * @see org.nasdanika.html.model.app.impl.AppPackageImpl#getSectionStyle()
		 * @generated
		 */
		EDataType SECTION_STYLE = eINSTANCE.getSectionStyle();

	}

} //AppPackage
