/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ScriptActionActivator;
import org.nasdanika.html.app.Themed;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.ContentAction;
import org.nasdanika.html.model.app.ContentType;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.ThemedAction;
import org.nasdanika.html.model.app.ThemedContentAction;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class AppPackageImpl extends EPackageImpl implements AppPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass labelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iThemedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass themedActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contentActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass themedContentActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iActionActivatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iNavigationActionActivatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iScriptActionActivatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass actionActivatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass navigationActionActivatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scriptActionActivatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum contentTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType colorEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType themeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iLabelEClass = null;

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
	 * @see org.nasdanika.html.model.app.AppPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private AppPackageImpl() {
		super(eNS_URI, AppFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link AppPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static AppPackage init() {
		if (isInited) return (AppPackage)EPackage.Registry.INSTANCE.getEPackage(AppPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredAppPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		AppPackageImpl theAppPackage = registeredAppPackage instanceof AppPackageImpl ? (AppPackageImpl)registeredAppPackage : new AppPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theAppPackage.createPackageContents();

		// Initialize created meta-data
		theAppPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theAppPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(AppPackage.eNS_URI, theAppPackage);
		return theAppPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLabel() {
		return labelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLabel_Color() {
		return (EAttribute)labelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLabel_Description() {
		return (EAttribute)labelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLabel_Icon() {
		return (EAttribute)labelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLabel_Id() {
		return (EAttribute)labelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLabel_Text() {
		return (EAttribute)labelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLabel_Tooltip() {
		return (EAttribute)labelEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLabel_Outline() {
		return (EAttribute)labelEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLabel_Notification() {
		return (EAttribute)labelEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIThemed() {
		return iThemedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIAction() {
		return iActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAction() {
		return actionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAction_Children() {
		return (EReference)actionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_Confirmation() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_FloatRight() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_Disabled() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAction_Activator() {
		return (EReference)actionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_Iterator() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAction_Category() {
		return (EReference)actionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_Roles() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getThemedAction() {
		return themedActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getThemedAction_Theme() {
		return (EAttribute)themedActionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContentAction() {
		return contentActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContentAction_Content() {
		return (EAttribute)contentActionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContentAction_ContentType() {
		return (EAttribute)contentActionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getThemedContentAction() {
		return themedContentActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIActionActivator() {
		return iActionActivatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getINavigationActionActivator() {
		return iNavigationActionActivatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIScriptActionActivator() {
		return iScriptActionActivatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getActionActivator() {
		return actionActivatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNavigationActionActivator() {
		return navigationActionActivatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNavigationActionActivator_Url() {
		return (EAttribute)navigationActionActivatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getScriptActionActivator() {
		return scriptActionActivatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getScriptActionActivator_Code() {
		return (EAttribute)scriptActionActivatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getContentType() {
		return contentTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getColor() {
		return colorEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getTheme() {
		return themeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getILabel() {
		return iLabelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AppFactory getAppFactory() {
		return (AppFactory)getEFactoryInstance();
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
		iLabelEClass = createEClass(ILABEL);

		labelEClass = createEClass(LABEL);
		createEAttribute(labelEClass, LABEL__COLOR);
		createEAttribute(labelEClass, LABEL__DESCRIPTION);
		createEAttribute(labelEClass, LABEL__ICON);
		createEAttribute(labelEClass, LABEL__ID);
		createEAttribute(labelEClass, LABEL__TEXT);
		createEAttribute(labelEClass, LABEL__TOOLTIP);
		createEAttribute(labelEClass, LABEL__OUTLINE);
		createEAttribute(labelEClass, LABEL__NOTIFICATION);

		iThemedEClass = createEClass(ITHEMED);

		iActionEClass = createEClass(IACTION);

		actionEClass = createEClass(ACTION);
		createEReference(actionEClass, ACTION__CHILDREN);
		createEAttribute(actionEClass, ACTION__CONFIRMATION);
		createEAttribute(actionEClass, ACTION__FLOAT_RIGHT);
		createEAttribute(actionEClass, ACTION__DISABLED);
		createEReference(actionEClass, ACTION__ACTIVATOR);
		createEAttribute(actionEClass, ACTION__ITERATOR);
		createEReference(actionEClass, ACTION__CATEGORY);
		createEAttribute(actionEClass, ACTION__ROLES);

		themedActionEClass = createEClass(THEMED_ACTION);
		createEAttribute(themedActionEClass, THEMED_ACTION__THEME);

		contentActionEClass = createEClass(CONTENT_ACTION);
		createEAttribute(contentActionEClass, CONTENT_ACTION__CONTENT);
		createEAttribute(contentActionEClass, CONTENT_ACTION__CONTENT_TYPE);

		themedContentActionEClass = createEClass(THEMED_CONTENT_ACTION);

		iActionActivatorEClass = createEClass(IACTION_ACTIVATOR);

		iNavigationActionActivatorEClass = createEClass(INAVIGATION_ACTION_ACTIVATOR);

		iScriptActionActivatorEClass = createEClass(ISCRIPT_ACTION_ACTIVATOR);

		actionActivatorEClass = createEClass(ACTION_ACTIVATOR);

		navigationActionActivatorEClass = createEClass(NAVIGATION_ACTION_ACTIVATOR);
		createEAttribute(navigationActionActivatorEClass, NAVIGATION_ACTION_ACTIVATOR__URL);

		scriptActionActivatorEClass = createEClass(SCRIPT_ACTION_ACTIVATOR);
		createEAttribute(scriptActionActivatorEClass, SCRIPT_ACTION_ACTIVATOR__CODE);

		// Create enums
		contentTypeEEnum = createEEnum(CONTENT_TYPE);

		// Create data types
		colorEDataType = createEDataType(COLOR);
		themeEDataType = createEDataType(THEME);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		labelEClass.getESuperTypes().add(this.getILabel());
		actionEClass.getESuperTypes().add(this.getIAction());
		actionEClass.getESuperTypes().add(this.getLabel());
		themedActionEClass.getESuperTypes().add(this.getAction());
		themedActionEClass.getESuperTypes().add(this.getIThemed());
		contentActionEClass.getESuperTypes().add(this.getAction());
		themedContentActionEClass.getESuperTypes().add(this.getContentAction());
		themedContentActionEClass.getESuperTypes().add(this.getThemedAction());
		actionActivatorEClass.getESuperTypes().add(this.getIActionActivator());
		navigationActionActivatorEClass.getESuperTypes().add(this.getINavigationActionActivator());
		navigationActionActivatorEClass.getESuperTypes().add(this.getActionActivator());
		scriptActionActivatorEClass.getESuperTypes().add(this.getIScriptActionActivator());
		scriptActionActivatorEClass.getESuperTypes().add(this.getActionActivator());

		// Initialize classes, features, and operations; add parameters
		initEClass(iLabelEClass, org.nasdanika.html.app.Label.class, "ILabel", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(labelEClass, Label.class, "Label", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLabel_Color(), this.getColor(), "color", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Description(), ecorePackage.getEString(), "description", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Icon(), ecorePackage.getEString(), "icon", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Id(), ecorePackage.getEString(), "id", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Text(), ecorePackage.getEString(), "text", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Tooltip(), ecorePackage.getEString(), "tooltip", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Outline(), ecorePackage.getEBoolean(), "outline", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Notification(), ecorePackage.getEString(), "notification", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iThemedEClass, Themed.class, "IThemed", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(iActionEClass, Action.class, "IAction", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(actionEClass, org.nasdanika.html.model.app.Action.class, "Action", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAction_Children(), this.getAction(), null, "children", null, 0, -1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Confirmation(), ecorePackage.getEString(), "confirmation", null, 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_FloatRight(), ecorePackage.getEBoolean(), "floatRight", null, 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Disabled(), ecorePackage.getEBoolean(), "disabled", null, 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Activator(), this.getActionActivator(), null, "activator", null, 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Iterator(), ecorePackage.getEString(), "iterator", null, 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Category(), this.getLabel(), null, "category", null, 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Roles(), ecorePackage.getEString(), "roles", null, 0, -1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(themedActionEClass, ThemedAction.class, "ThemedAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getThemedAction_Theme(), this.getTheme(), "theme", null, 0, 1, ThemedAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contentActionEClass, ContentAction.class, "ContentAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getContentAction_Content(), ecorePackage.getEString(), "content", null, 0, 1, ContentAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContentAction_ContentType(), this.getContentType(), "contentType", null, 0, 1, ContentAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(themedContentActionEClass, ThemedContentAction.class, "ThemedContentAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iActionActivatorEClass, ActionActivator.class, "IActionActivator", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(iNavigationActionActivatorEClass, NavigationActionActivator.class, "INavigationActionActivator", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(iScriptActionActivatorEClass, ScriptActionActivator.class, "IScriptActionActivator", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(actionActivatorEClass, org.nasdanika.html.model.app.ActionActivator.class, "ActionActivator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(navigationActionActivatorEClass, org.nasdanika.html.model.app.NavigationActionActivator.class, "NavigationActionActivator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNavigationActionActivator_Url(), ecorePackage.getEString(), "url", null, 0, 1, org.nasdanika.html.model.app.NavigationActionActivator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scriptActionActivatorEClass, org.nasdanika.html.model.app.ScriptActionActivator.class, "ScriptActionActivator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScriptActionActivator_Code(), ecorePackage.getEString(), "code", null, 0, 1, org.nasdanika.html.model.app.ScriptActionActivator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(contentTypeEEnum, ContentType.class, "ContentType");
		addEEnumLiteral(contentTypeEEnum, ContentType.TEXT);
		addEEnumLiteral(contentTypeEEnum, ContentType.HTML);
		addEEnumLiteral(contentTypeEEnum, ContentType.MARKDOWN);

		// Initialize data types
		initEDataType(colorEDataType, Color.class, "Color", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(themeEDataType, Theme.class, "Theme", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //AppPackageImpl
