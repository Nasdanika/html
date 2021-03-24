/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;

import org.nasdanika.html.app.SectionStyle;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Category;

import org.nasdanika.html.model.app.util.AppValidator;

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
	private EClass iLabelEClass = null;

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
	private EClass categoryEClass = null;

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
	private EDataType colorEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType sectionStyleEDataType = null;

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

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theAppPackage,
			 new EValidator.Descriptor() {
				 @Override
				 public EValidator getEValidator() {
					 return AppValidator.INSTANCE;
				 }
			 });

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
	public EClass getILabel() {
		return iLabelEClass;
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
	public EAttribute getLabel_Text() {
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
	public EAttribute getLabel_Tooltip() {
		return (EAttribute)labelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLabel_Outline() {
		return (EAttribute)labelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLabel_Notification() {
		return (EAttribute)labelEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCategory() {
		return categoryEClass;
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
	public EAttribute getAction_Role() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_SectionStyle() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_SectionColumns() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_Location() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_Script() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_Binding() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_Confirmation() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_Disabled() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_MarkdownContent() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_PageTemplate() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_Content() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(10);
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
	public EDataType getSectionStyle() {
		return sectionStyleEDataType;
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
		createEAttribute(labelEClass, LABEL__TEXT);
		createEAttribute(labelEClass, LABEL__ICON);
		createEAttribute(labelEClass, LABEL__TOOLTIP);
		createEAttribute(labelEClass, LABEL__OUTLINE);
		createEAttribute(labelEClass, LABEL__NOTIFICATION);

		categoryEClass = createEClass(CATEGORY);

		iActionEClass = createEClass(IACTION);

		actionEClass = createEClass(ACTION);
		createEAttribute(actionEClass, ACTION__ROLE);
		createEAttribute(actionEClass, ACTION__SECTION_STYLE);
		createEAttribute(actionEClass, ACTION__SECTION_COLUMNS);
		createEAttribute(actionEClass, ACTION__LOCATION);
		createEAttribute(actionEClass, ACTION__SCRIPT);
		createEAttribute(actionEClass, ACTION__BINDING);
		createEAttribute(actionEClass, ACTION__CONFIRMATION);
		createEAttribute(actionEClass, ACTION__DISABLED);
		createEAttribute(actionEClass, ACTION__MARKDOWN_CONTENT);
		createEAttribute(actionEClass, ACTION__PAGE_TEMPLATE);
		createEAttribute(actionEClass, ACTION__CONTENT);

		// Create data types
		colorEDataType = createEDataType(COLOR);
		sectionStyleEDataType = createEDataType(SECTION_STYLE);
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
		categoryEClass.getESuperTypes().add(this.getLabel());
		actionEClass.getESuperTypes().add(this.getLabel());
		actionEClass.getESuperTypes().add(this.getIAction());

		// Initialize classes, features, and operations; add parameters
		initEClass(iLabelEClass, Label.class, "ILabel", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(labelEClass, org.nasdanika.html.model.app.Label.class, "Label", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLabel_Color(), this.getColor(), "color", null, 0, 1, org.nasdanika.html.model.app.Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Text(), ecorePackage.getEString(), "text", null, 0, 1, org.nasdanika.html.model.app.Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Icon(), ecorePackage.getEString(), "icon", null, 0, 1, org.nasdanika.html.model.app.Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Tooltip(), ecorePackage.getEString(), "tooltip", null, 0, 1, org.nasdanika.html.model.app.Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Outline(), ecorePackage.getEBoolean(), "outline", null, 0, 1, org.nasdanika.html.model.app.Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Notification(), ecorePackage.getEString(), "notification", null, 0, 1, org.nasdanika.html.model.app.Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(categoryEClass, Category.class, "Category", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iActionEClass, Action.class, "IAction", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(actionEClass, org.nasdanika.html.model.app.Action.class, "Action", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAction_Role(), ecorePackage.getEString(), "role", "Navigation", 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_SectionStyle(), this.getSectionStyle(), "sectionStyle", null, 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_SectionColumns(), ecorePackage.getEInt(), "sectionColumns", "3", 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Location(), ecorePackage.getEString(), "location", null, 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Script(), ecorePackage.getEString(), "script", null, 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Binding(), ecorePackage.getEString(), "binding", null, 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Confirmation(), ecorePackage.getEString(), "confirmation", null, 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Disabled(), ecorePackage.getEBoolean(), "disabled", null, 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_MarkdownContent(), ecorePackage.getEString(), "markdownContent", null, 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_PageTemplate(), ecorePackage.getEString(), "pageTemplate", null, 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Content(), ecorePackage.getEString(), "content", null, 0, 1, org.nasdanika.html.model.app.Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(colorEDataType, Color.class, "Color", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(sectionStyleEDataType, SectionStyle.class, "SectionStyle", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// urn:org.nasdanika
		createUrnorgAnnotations();
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
			   "documentation", "Application model."
		   });
		addAnnotation
		  (labelEClass,
		   source,
		   new String[] {
			   "documentation", "Label is a base class for actions and categories."
		   });
		addAnnotation
		  (getLabel_Color(),
		   source,
		   new String[] {
			   "documentation", "Label bootstrap color."
		   });
		addAnnotation
		  (getLabel_Text(),
		   source,
		   new String[] {
			   "documentation", "Label text."
		   });
		addAnnotation
		  (getLabel_Icon(),
		   source,
		   new String[] {
			   "documentation", "Label icon. Treated as URL if contains ``/`` or as a CSS class otherwise. E.g. ``fas fa-wrench`` would be treated as a CSS class."
		   });
		addAnnotation
		  (getLabel_Tooltip(),
		   source,
		   new String[] {
			   "documentation", "Label tooltip."
		   });
		addAnnotation
		  (getLabel_Outline(),
		   source,
		   new String[] {
			   "documentation", "For some label representations specifies that the label shall be displayed as an outline instead of a solid fill."
		   });
		addAnnotation
		  (getLabel_Notification(),
		   source,
		   new String[] {
			   "documentation", "Notification to display next to the label. E.g. a number of new messages in an inbox."
		   });
		addAnnotation
		  (categoryEClass,
		   source,
		   new String[] {
			   "documentation", "Categories are used for grouping of its elements such as actions or properties. "
		   });
		addAnnotation
		  (actionEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for concrete action classes - Action and Partition. These sub-classes are semantically equivalent and differ only in diagram representation - Action is represented by a node, Partition by a container node.\nAction can be a child of another action or of an action category. It may contain content and action elements - abstract actions and action categories. It may also contain action mappings - logical names of actions referenced by this action\'s content."
		   });
		addAnnotation
		  (getAction_Role(),
		   source,
		   new String[] {
			   "documentation", "Action roles are used in \"wiring\" of actions into the generated Web UI.\n\nFor the root action its children are displayed depending on their rolw as follows:\n\n* Navigation: \n    * The first navigation child is called \"Principal\" and is displayed in the navbar brand. \n    * The remaining navigation children are displayed in navs on the right in the header.\n* Context children are displayed in the footer.\n\nFor the principal action (the first navigation child of the root action):\n\n* Navigation actions are displayed in the navigation panel on the left.\n* Context actions are displayed in the navbar.\n\nFor other non-section actions navigation children are displayed in the navigation panel and context children are displayed in right-floating navs on the top of the content panel.\n\nSection actions are displayed as part of the content panel body of their parent. Their navigation children are treated as sections and display of their context children depends on the section style.\n\nContent left and Content right acitons are displayed on the left and right of the content body respectively.\n\nView and Edit actions are applicable for properties and property sources."
		   });
		addAnnotation
		  (getAction_SectionStyle(),
		   source,
		   new String[] {
			   "documentation", "Defines how to generate section children."
		   });
		addAnnotation
		  (getAction_SectionColumns(),
		   source,
		   new String[] {
			   "documentation", "Applicable to section style \"Card\". Defines how many columns shall be in a row of section cards."
		   });
		addAnnotation
		  (getAction_Location(),
		   source,
		   new String[] {
			   "documentation", "Activator is either a URL if the activator type is Reference, or a script if the activator type is Script or Bind.\n\nThe application generator generates a page for an action only if the activator type is Reference, the URL does not start with ``./`` and when resolved is relative to the base URI and below it. \nUsing ``./`` is a way to reference externally generated content. For example, create a hierarchy/federation of sites.\n\nFor the reference activator type activator URL is resolved against the first ancestor action which also has reference activator type. If there is no such action, the URL is resolved against the base URI.\nDuring generation action URL is de-resolved against the base URI to produce a relative resource/file path. \n\n``${{{base-uri}}}`` token can be used in the activator to define the uri relative to the base generation URI (output folder) instead of the parent URI. It might be useful it the parent URI is an absolute external URI.\n``${{{base-uri}}}`` ends with a slash, so there is no need to add a slash. E.g. ``${{{base-uri}}}index.html``."
		   });
		addAnnotation
		  (getAction_Script(),
		   source,
		   new String[] {
			   "documentation", "Activator is either a URL if the activator type is Reference, or a script if the activator type is Script or Bind.\n\nThe application generator generates a page for an action only if the activator type is Reference, the URL does not start with ``./`` and when resolved is relative to the base URI and below it. \nUsing ``./`` is a way to reference externally generated content. For example, create a hierarchy/federation of sites.\n\nFor the reference activator type activator URL is resolved against the first ancestor action which also has reference activator type. If there is no such action, the URL is resolved against the base URI.\nDuring generation action URL is de-resolved against the base URI to produce a relative resource/file path. \n\n``${{{base-uri}}}`` token can be used in the activator to define the uri relative to the base generation URI (output folder) instead of the parent URI. It might be useful it the parent URI is an absolute external URI.\n``${{{base-uri}}}`` ends with a slash, so there is no need to add a slash. E.g. ``${{{base-uri}}}index.html``."
		   });
		addAnnotation
		  (getAction_Binding(),
		   source,
		   new String[] {
			   "documentation", "Activator is either a URL if the activator type is Reference, or a script if the activator type is Script or Bind.\n\nThe application generator generates a page for an action only if the activator type is Reference, the URL does not start with ``./`` and when resolved is relative to the base URI and below it. \nUsing ``./`` is a way to reference externally generated content. For example, create a hierarchy/federation of sites.\n\nFor the reference activator type activator URL is resolved against the first ancestor action which also has reference activator type. If there is no such action, the URL is resolved against the base URI.\nDuring generation action URL is de-resolved against the base URI to produce a relative resource/file path. \n\n``${{{base-uri}}}`` token can be used in the activator to define the uri relative to the base generation URI (output folder) instead of the parent URI. It might be useful it the parent URI is an absolute external URI.\n``${{{base-uri}}}`` ends with a slash, so there is no need to add a slash. E.g. ``${{{base-uri}}}index.html``."
		   });
		addAnnotation
		  (getAction_Confirmation(),
		   source,
		   new String[] {
			   "documentation", "Confirmation to display in a confirmation dialog before action activation to give the user an opportunity to cancel the action. E.g. confirmation of deletion."
		   });
		addAnnotation
		  (getAction_Disabled(),
		   source,
		   new String[] {
			   "documentation", "If true, then action is displayed as disabled. "
		   });
		addAnnotation
		  (getAction_MarkdownContent(),
		   source,
		   new String[] {
			   "documentation", "[Markdown](https://en.wikipedia.org/wiki/Markdown) text. If this attribute contains text, the text is converted to HTML, interpolated and used as the first content element.\nThe primary purpose of this attribute is rapid development/prototyping of web applications/sites. "
		   });
		addAnnotation
		  (getAction_PageTemplate(),
		   source,
		   new String[] {
			   "documentation", "URI of the page template to use for generation of a Web Application. The URI is resolved relative to the action\'s containing resource.\nDefaults to ``platform:/plugin/org.nasdanika.vinci.templates/pages/default/primary.vinci`` built-in template.\n\n## Built-in page templates\n\n``org.nasdanika.vinci.templates`` plugin, which is part of the Vinci distribution, provides the following page templates (organized by theme and header color):\n\n* Default\n    * Primary\n        * ``platform:/plugin/org.nasdanika.vinci.templates/pages/default/primary.vinci``\n        * ``platform:/plugin/org.nasdanika.vinci.templates/pages/default/primary-fluid.vinci`` - fluid container.\n* Cerulean\n    * Primary\n        * ``platform:/plugin/org.nasdanika.vinci.templates/pages/cerulean/primary.vinci``\n        * ``platform:/plugin/org.nasdanika.vinci.templates/pages/cerulean/primary-fluid.vinci`` - fluid container.\n        * ``platform:/plugin/org.nasdanika.vinci.templates/pages/cerulean/primary-dark.vinci`` - dark navigation bar.\n        * ``platform:/plugin/org.nasdanika.vinci.templates/pages/cerulean/primary-dark-fluid.vinci`` - dark navigation bar, fluid container.\n    * Dark \n        * ``platform:/plugin/org.nasdanika.vinci.templates/pages/cerulean/dark.vinci``\n        * ``platform:/plugin/org.nasdanika.vinci.templates/pages/cerulean/dark-fluid.vinci`` - fluid container.\n    "
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
		  (labelEClass,
		   source,
		   new String[] {
			   "constraints", "color"
		   });
		addAnnotation
		  (actionEClass,
		   source,
		   new String[] {
			   "constraints", "sectionStyle"
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
		  (getAction_MarkdownContent(),
		   source,
		   new String[] {
			   "content-type", "text/markdown"
		   });
	}

} //AppPackageImpl
