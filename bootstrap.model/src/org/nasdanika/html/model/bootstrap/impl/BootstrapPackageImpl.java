/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.DeviceSize;
import org.nasdanika.html.bootstrap.Direction;
import org.nasdanika.html.bootstrap.Placement;
import org.nasdanika.html.bootstrap.Theme;

import org.nasdanika.html.model.bootstrap.Alert;
import org.nasdanika.html.model.bootstrap.Badge;
import org.nasdanika.html.model.bootstrap.BootstrapCDNFacet;
import org.nasdanika.html.model.bootstrap.BootstrapElement;
import org.nasdanika.html.model.bootstrap.BootstrapFactory;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;

import org.nasdanika.html.model.bootstrap.ContainerBootstrapElement;
import org.nasdanika.html.model.bootstrap.ContentBootstrapElement;
import org.nasdanika.html.model.bootstrap.ListGroup;
import org.nasdanika.html.model.bootstrap.ListGroupItem;
import org.nasdanika.html.model.bootstrap.Wrap;
import org.nasdanika.html.model.bootstrap.util.BootstrapValidator;
import org.nasdanika.html.model.html.HtmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BootstrapPackageImpl extends EPackageImpl implements BootstrapPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bootstrapCDNFacetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bootstrapElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contentBootstrapElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass containerBootstrapElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wrapEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass alertEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listGroupItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass badgeEClass = null;

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
	private EDataType colorEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType deviceSizeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType directionEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType placementEDataType = null;

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
	 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private BootstrapPackageImpl() {
		super(eNS_URI, BootstrapFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link BootstrapPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static BootstrapPackage init() {
		if (isInited) return (BootstrapPackage)EPackage.Registry.INSTANCE.getEPackage(BootstrapPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredBootstrapPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		BootstrapPackageImpl theBootstrapPackage = registeredBootstrapPackage instanceof BootstrapPackageImpl ? (BootstrapPackageImpl)registeredBootstrapPackage : new BootstrapPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		HtmlPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theBootstrapPackage.createPackageContents();

		// Initialize created meta-data
		theBootstrapPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theBootstrapPackage,
			 new EValidator.Descriptor() {
				 @Override
				 public EValidator getEValidator() {
					 return BootstrapValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theBootstrapPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BootstrapPackage.eNS_URI, theBootstrapPackage);
		return theBootstrapPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBootstrapCDNFacet() {
		return bootstrapCDNFacetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBootstrapCDNFacet_Theme() {
		return (EAttribute)bootstrapCDNFacetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBootstrapElement() {
		return bootstrapElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContentBootstrapElement() {
		return contentBootstrapElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContainerBootstrapElement() {
		return containerBootstrapElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWrap() {
		return wrapEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWrap_HtmlElement() {
		return (EReference)wrapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAlert() {
		return alertEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAlert_Color() {
		return (EAttribute)alertEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getListGroup() {
		return listGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getListGroup_Items() {
		return (EReference)listGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getListGroupItem() {
		return listGroupItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getListGroupItem_Disabled() {
		return (EAttribute)listGroupItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getListGroupItem_Active() {
		return (EAttribute)listGroupItemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getListGroupItem_Color() {
		return (EAttribute)listGroupItemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getListGroupItem_Content() {
		return (EReference)listGroupItemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBadge() {
		return badgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBadge_Pill() {
		return (EAttribute)badgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBadge_Color() {
		return (EAttribute)badgeEClass.getEStructuralFeatures().get(1);
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
	public EDataType getColor() {
		return colorEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getDeviceSize() {
		return deviceSizeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getDirection() {
		return directionEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getPlacement() {
		return placementEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BootstrapFactory getBootstrapFactory() {
		return (BootstrapFactory)getEFactoryInstance();
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
		bootstrapCDNFacetEClass = createEClass(BOOTSTRAP_CDN_FACET);
		createEAttribute(bootstrapCDNFacetEClass, BOOTSTRAP_CDN_FACET__THEME);

		bootstrapElementEClass = createEClass(BOOTSTRAP_ELEMENT);

		contentBootstrapElementEClass = createEClass(CONTENT_BOOTSTRAP_ELEMENT);

		containerBootstrapElementEClass = createEClass(CONTAINER_BOOTSTRAP_ELEMENT);

		wrapEClass = createEClass(WRAP);
		createEReference(wrapEClass, WRAP__HTML_ELEMENT);

		alertEClass = createEClass(ALERT);
		createEAttribute(alertEClass, ALERT__COLOR);

		listGroupEClass = createEClass(LIST_GROUP);
		createEReference(listGroupEClass, LIST_GROUP__ITEMS);

		listGroupItemEClass = createEClass(LIST_GROUP_ITEM);
		createEAttribute(listGroupItemEClass, LIST_GROUP_ITEM__DISABLED);
		createEAttribute(listGroupItemEClass, LIST_GROUP_ITEM__ACTIVE);
		createEAttribute(listGroupItemEClass, LIST_GROUP_ITEM__COLOR);
		createEReference(listGroupItemEClass, LIST_GROUP_ITEM__CONTENT);

		badgeEClass = createEClass(BADGE);
		createEAttribute(badgeEClass, BADGE__PILL);
		createEAttribute(badgeEClass, BADGE__COLOR);

		// Create data types
		themeEDataType = createEDataType(THEME);
		colorEDataType = createEDataType(COLOR);
		deviceSizeEDataType = createEDataType(DEVICE_SIZE);
		directionEDataType = createEDataType(DIRECTION);
		placementEDataType = createEDataType(PLACEMENT);
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
		HtmlPackage theHtmlPackage = (HtmlPackage)EPackage.Registry.INSTANCE.getEPackage(HtmlPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		EGenericType g1 = createEGenericType(theHtmlPackage.getFacet());
		EGenericType g2 = createEGenericType(theHtmlPackage.getPage());
		g1.getETypeArguments().add(g2);
		bootstrapCDNFacetEClass.getEGenericSuperTypes().add(g1);
		bootstrapElementEClass.getESuperTypes().add(theHtmlPackage.getHTMLElement());
		contentBootstrapElementEClass.getESuperTypes().add(this.getBootstrapElement());
		contentBootstrapElementEClass.getESuperTypes().add(theHtmlPackage.getContent());
		containerBootstrapElementEClass.getESuperTypes().add(this.getContentBootstrapElement());
		containerBootstrapElementEClass.getESuperTypes().add(theHtmlPackage.getContainer());
		wrapEClass.getESuperTypes().add(this.getContentBootstrapElement());
		alertEClass.getESuperTypes().add(this.getContainerBootstrapElement());
		listGroupEClass.getESuperTypes().add(this.getContentBootstrapElement());
		listGroupItemEClass.getESuperTypes().add(theHtmlPackage.getModelElement());
		badgeEClass.getESuperTypes().add(this.getContainerBootstrapElement());

		// Initialize classes, features, and operations; add parameters
		initEClass(bootstrapCDNFacetEClass, BootstrapCDNFacet.class, "BootstrapCDNFacet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBootstrapCDNFacet_Theme(), this.getTheme(), "theme", null, 0, 1, BootstrapCDNFacet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bootstrapElementEClass, BootstrapElement.class, "BootstrapElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(contentBootstrapElementEClass, ContentBootstrapElement.class, "ContentBootstrapElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(containerBootstrapElementEClass, ContainerBootstrapElement.class, "ContainerBootstrapElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(wrapEClass, Wrap.class, "Wrap", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWrap_HtmlElement(), theHtmlPackage.getHTMLElement(), null, "htmlElement", null, 0, 1, Wrap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(alertEClass, Alert.class, "Alert", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAlert_Color(), this.getColor(), "color", null, 0, 1, Alert.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(listGroupEClass, ListGroup.class, "ListGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getListGroup_Items(), this.getListGroupItem(), null, "items", null, 0, -1, ListGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(listGroupItemEClass, ListGroupItem.class, "ListGroupItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getListGroupItem_Disabled(), ecorePackage.getEBoolean(), "disabled", null, 0, 1, ListGroupItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getListGroupItem_Active(), ecorePackage.getEBoolean(), "active", null, 0, 1, ListGroupItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getListGroupItem_Color(), this.getColor(), "color", null, 0, 1, ListGroupItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getListGroupItem_Content(), theHtmlPackage.getContent(), null, "content", null, 0, -1, ListGroupItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(badgeEClass, Badge.class, "Badge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBadge_Pill(), ecorePackage.getEBoolean(), "pill", null, 0, 1, Badge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBadge_Color(), this.getColor(), "color", null, 0, 1, Badge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize data types
		initEDataType(themeEDataType, Theme.class, "Theme", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(colorEDataType, Color.class, "Color", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(deviceSizeEDataType, DeviceSize.class, "DeviceSize", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(directionEDataType, Direction.class, "Direction", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(placementEDataType, Placement.class, "Placement", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
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
		  (listGroupItemEClass,
		   source,
		   new String[] {
			   "constraints", "activeDisabled"
		   });
	}

} //BootstrapPackageImpl
