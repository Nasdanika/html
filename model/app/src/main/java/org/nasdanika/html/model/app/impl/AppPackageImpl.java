/**
 */
package org.nasdanika.html.model.app.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.resources.ResourcesPackage;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.ActionReference;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.ContentPanel;
import org.nasdanika.html.model.app.Footer;
import org.nasdanika.html.model.app.Header;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.NavigationBar;
import org.nasdanika.html.model.app.NavigationPanel;
import org.nasdanika.html.model.app.NavigationPanelStyle;
import org.nasdanika.html.model.app.Page;
import org.nasdanika.html.model.app.PagePart;
import org.nasdanika.html.model.app.SectionStyle;
import org.nasdanika.html.model.app.util.AppValidator;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.html.HtmlPackage;

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
	private EClass linkEClass = null;

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
	private EClass pagePartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass headerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass navigationBarEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass navigationPanelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contentPanelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass footerEClass = null;

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
	private EClass actionReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum sectionStyleEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum navigationPanelStyleEEnum = null;

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

		// Initialize simple dependencies
		BootstrapPackage.eINSTANCE.eClass();
		HtmlPackage.eINSTANCE.eClass();
		ExecPackage.eINSTANCE.eClass();

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
	public EClass getLabel() {
		return labelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLabel_Help() {
		return (EReference)labelEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLabel_Id() {
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
	public EReference getLabel_Children() {
		return (EReference)labelEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLink() {
		return linkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLink_Location() {
		return (EAttribute)linkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLink_Script() {
		return (EAttribute)linkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLink_Modal() {
		return (EReference)linkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLink_Confirmation() {
		return (EAttribute)linkEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLink_Name() {
		return (EAttribute)linkEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLink_Target() {
		return (EAttribute)linkEClass.getEStructuralFeatures().get(5);
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
	public EAttribute getPage_Fluid() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPage_Header() {
		return (EReference)pageEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPage_NavigationBar() {
		return (EReference)pageEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPage_NavigationPanel() {
		return (EReference)pageEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPage_ContentPanel() {
		return (EReference)pageEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPage_Footer() {
		return (EReference)pageEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPage_ContentRowAppearance() {
		return (EReference)pageEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPagePart() {
		return pagePartEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPagePart_Items() {
		return (EReference)pagePartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHeader() {
		return headerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getHeader_Title() {
		return (EReference)headerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNavigationBar() {
		return navigationBarEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getNavigationBar_Brand() {
		return (EReference)navigationBarEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNavigationBar_Dark() {
		return (EAttribute)navigationBarEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNavigationBar_Expand() {
		return (EAttribute)navigationBarEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNavigationBar_Background() {
		return (EAttribute)navigationBarEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNavigationPanel() {
		return navigationPanelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNavigationPanel_Style() {
		return (EAttribute)navigationPanelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNavigationPanel_Id() {
		return (EAttribute)navigationPanelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContentPanel() {
		return contentPanelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContentPanel_Title() {
		return (EReference)contentPanelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContentPanel_Breadcrumb() {
		return (EReference)contentPanelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContentPanel_LeftNavigation() {
		return (EReference)contentPanelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContentPanel_RightNavigation() {
		return (EReference)contentPanelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContentPanel_FloatLeftNavigation() {
		return (EReference)contentPanelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContentPanel_FloatRightNavigation() {
		return (EReference)contentPanelEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getContentPanel_Sections() {
		return (EReference)contentPanelEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContentPanel_SectionColumns() {
		return (EAttribute)contentPanelEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getContentPanel_SectionStyle() {
		return (EAttribute)contentPanelEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFooter() {
		return footerEClass;
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
	public EAttribute getAction_SectionColumns() {
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
	public EReference getAction_Sections() {
		return (EReference)actionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAction_Navigation() {
		return (EReference)actionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAction_LeftNavigation() {
		return (EReference)actionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAction_RightNavigation() {
		return (EReference)actionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAction_FloatLeftNavigation() {
		return (EReference)actionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAction_FloatRightNavigation() {
		return (EReference)actionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAction_Anonymous() {
		return (EReference)actionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAction_Resources() {
		return (EReference)actionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_Inline() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getAction_ModalActivator() {
		return (EAttribute)actionEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getActionReference() {
		return actionReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getActionReference_Target() {
		return (EReference)actionReferenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getNavigationPanelStyle() {
		return navigationPanelStyleEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getSectionStyle() {
		return sectionStyleEEnum;
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
		labelEClass = createEClass(LABEL);
		createEAttribute(labelEClass, LABEL__ID);
		createEAttribute(labelEClass, LABEL__TEXT);
		createEAttribute(labelEClass, LABEL__ICON);
		createEAttribute(labelEClass, LABEL__TOOLTIP);
		createEAttribute(labelEClass, LABEL__OUTLINE);
		createEAttribute(labelEClass, LABEL__NOTIFICATION);
		createEReference(labelEClass, LABEL__CHILDREN);
		createEReference(labelEClass, LABEL__HELP);

		linkEClass = createEClass(LINK);
		createEAttribute(linkEClass, LINK__LOCATION);
		createEAttribute(linkEClass, LINK__SCRIPT);
		createEReference(linkEClass, LINK__MODAL);
		createEAttribute(linkEClass, LINK__CONFIRMATION);
		createEAttribute(linkEClass, LINK__NAME);
		createEAttribute(linkEClass, LINK__TARGET);

		pageEClass = createEClass(PAGE);
		createEAttribute(pageEClass, PAGE__FLUID);
		createEReference(pageEClass, PAGE__HEADER);
		createEReference(pageEClass, PAGE__NAVIGATION_BAR);
		createEReference(pageEClass, PAGE__NAVIGATION_PANEL);
		createEReference(pageEClass, PAGE__CONTENT_PANEL);
		createEReference(pageEClass, PAGE__FOOTER);
		createEReference(pageEClass, PAGE__CONTENT_ROW_APPEARANCE);

		pagePartEClass = createEClass(PAGE_PART);
		createEReference(pagePartEClass, PAGE_PART__ITEMS);

		headerEClass = createEClass(HEADER);
		createEReference(headerEClass, HEADER__TITLE);

		navigationBarEClass = createEClass(NAVIGATION_BAR);
		createEReference(navigationBarEClass, NAVIGATION_BAR__BRAND);
		createEAttribute(navigationBarEClass, NAVIGATION_BAR__DARK);
		createEAttribute(navigationBarEClass, NAVIGATION_BAR__EXPAND);
		createEAttribute(navigationBarEClass, NAVIGATION_BAR__BACKGROUND);

		navigationPanelEClass = createEClass(NAVIGATION_PANEL);
		createEAttribute(navigationPanelEClass, NAVIGATION_PANEL__STYLE);
		createEAttribute(navigationPanelEClass, NAVIGATION_PANEL__ID);

		contentPanelEClass = createEClass(CONTENT_PANEL);
		createEReference(contentPanelEClass, CONTENT_PANEL__BREADCRUMB);
		createEReference(contentPanelEClass, CONTENT_PANEL__TITLE);
		createEReference(contentPanelEClass, CONTENT_PANEL__LEFT_NAVIGATION);
		createEReference(contentPanelEClass, CONTENT_PANEL__RIGHT_NAVIGATION);
		createEReference(contentPanelEClass, CONTENT_PANEL__FLOAT_LEFT_NAVIGATION);
		createEReference(contentPanelEClass, CONTENT_PANEL__FLOAT_RIGHT_NAVIGATION);
		createEReference(contentPanelEClass, CONTENT_PANEL__SECTIONS);
		createEAttribute(contentPanelEClass, CONTENT_PANEL__SECTION_COLUMNS);
		createEAttribute(contentPanelEClass, CONTENT_PANEL__SECTION_STYLE);

		footerEClass = createEClass(FOOTER);

		actionEClass = createEClass(ACTION);
		createEAttribute(actionEClass, ACTION__SECTION_COLUMNS);
		createEAttribute(actionEClass, ACTION__SECTION_STYLE);
		createEReference(actionEClass, ACTION__SECTIONS);
		createEReference(actionEClass, ACTION__NAVIGATION);
		createEReference(actionEClass, ACTION__LEFT_NAVIGATION);
		createEReference(actionEClass, ACTION__RIGHT_NAVIGATION);
		createEReference(actionEClass, ACTION__FLOAT_LEFT_NAVIGATION);
		createEReference(actionEClass, ACTION__FLOAT_RIGHT_NAVIGATION);
		createEReference(actionEClass, ACTION__ANONYMOUS);
		createEReference(actionEClass, ACTION__RESOURCES);
		createEAttribute(actionEClass, ACTION__INLINE);
		createEAttribute(actionEClass, ACTION__MODAL_ACTIVATOR);

		actionReferenceEClass = createEClass(ACTION_REFERENCE);
		createEReference(actionReferenceEClass, ACTION_REFERENCE__TARGET);

		// Create enums
		sectionStyleEEnum = createEEnum(SECTION_STYLE);
		navigationPanelStyleEEnum = createEEnum(NAVIGATION_PANEL_STYLE);
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
		BootstrapPackage theBootstrapPackage = (BootstrapPackage)EPackage.Registry.INSTANCE.getEPackage(BootstrapPackage.eNS_URI);
		ResourcesPackage theResourcesPackage = (ResourcesPackage)EPackage.Registry.INSTANCE.getEPackage(ResourcesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		labelEClass.getESuperTypes().add(theBootstrapPackage.getBootstrapElement());
		labelEClass.getESuperTypes().add(theBootstrapPackage.getItem());
		linkEClass.getESuperTypes().add(this.getLabel());
		pageEClass.getESuperTypes().add(theBootstrapPackage.getBootstrapElement());
		pagePartEClass.getESuperTypes().add(theBootstrapPackage.getBootstrapElement());
		headerEClass.getESuperTypes().add(this.getPagePart());
		navigationBarEClass.getESuperTypes().add(this.getPagePart());
		navigationPanelEClass.getESuperTypes().add(this.getPagePart());
		contentPanelEClass.getESuperTypes().add(this.getPagePart());
		footerEClass.getESuperTypes().add(this.getPagePart());
		actionEClass.getESuperTypes().add(this.getLink());

		// Initialize classes, features, and operations; add parameters
		initEClass(labelEClass, Label.class, "Label", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLabel_Id(), ecorePackage.getEString(), "id", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Text(), ecorePackage.getEString(), "text", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Icon(), ecorePackage.getEString(), "icon", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Tooltip(), ecorePackage.getEString(), "tooltip", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Outline(), ecorePackage.getEBoolean(), "outline", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLabel_Notification(), ecorePackage.getEString(), "notification", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLabel_Children(), ecorePackage.getEObject(), null, "children", null, 0, -1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLabel_Help(), theBootstrapPackage.getModal(), null, "help", null, 0, 1, Label.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(linkEClass, Link.class, "Link", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLink_Location(), ecorePackage.getEString(), "location", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLink_Script(), ecorePackage.getEString(), "script", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getLink_Modal(), theBootstrapPackage.getModal(), null, "modal", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLink_Confirmation(), ecorePackage.getEString(), "confirmation", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLink_Name(), ecorePackage.getEString(), "name", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLink_Target(), ecorePackage.getEString(), "target", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pageEClass, Page.class, "Page", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPage_Fluid(), ecorePackage.getEBoolean(), "fluid", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPage_Header(), this.getHeader(), null, "header", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPage_NavigationBar(), this.getNavigationBar(), null, "navigationBar", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPage_NavigationPanel(), this.getNavigationPanel(), null, "navigationPanel", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPage_ContentPanel(), this.getContentPanel(), null, "contentPanel", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPage_Footer(), this.getFooter(), null, "footer", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPage_ContentRowAppearance(), theBootstrapPackage.getAppearance(), null, "contentRowAppearance", null, 0, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pagePartEClass, PagePart.class, "PagePart", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPagePart_Items(), ecorePackage.getEObject(), null, "items", null, 0, -1, PagePart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(headerEClass, Header.class, "Header", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getHeader_Title(), this.getLabel(), null, "title", null, 0, 1, Header.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(navigationBarEClass, NavigationBar.class, "NavigationBar", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNavigationBar_Brand(), this.getLabel(), null, "brand", null, 0, 1, NavigationBar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNavigationBar_Dark(), ecorePackage.getEBoolean(), "dark", null, 0, 1, NavigationBar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNavigationBar_Expand(), theBootstrapPackage.getBreakpoint(), "expand", "LARGE", 0, 1, NavigationBar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNavigationBar_Background(), theBootstrapPackage.getColor(), "background", null, 0, 1, NavigationBar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(navigationPanelEClass, NavigationPanel.class, "NavigationPanel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNavigationPanel_Style(), this.getNavigationPanelStyle(), "style", "Auto", 0, 1, NavigationPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNavigationPanel_Id(), ecorePackage.getEString(), "id", null, 0, 1, NavigationPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(contentPanelEClass, ContentPanel.class, "ContentPanel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getContentPanel_Breadcrumb(), this.getLabel(), null, "breadcrumb", null, 0, -1, ContentPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContentPanel_Title(), this.getLabel(), null, "title", null, 0, 1, ContentPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContentPanel_LeftNavigation(), this.getNavigationPanel(), null, "leftNavigation", null, 0, 1, ContentPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContentPanel_RightNavigation(), this.getNavigationPanel(), null, "rightNavigation", null, 0, 1, ContentPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContentPanel_FloatLeftNavigation(), this.getNavigationPanel(), null, "floatLeftNavigation", null, 0, 1, ContentPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContentPanel_FloatRightNavigation(), this.getNavigationPanel(), null, "floatRightNavigation", null, 0, 1, ContentPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getContentPanel_Sections(), this.getContentPanel(), null, "sections", null, 0, -1, ContentPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContentPanel_SectionColumns(), ecorePackage.getEInt(), "sectionColumns", "3", 0, 1, ContentPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getContentPanel_SectionStyle(), this.getSectionStyle(), "sectionStyle", "Auto", 0, 1, ContentPanel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(footerEClass, Footer.class, "Footer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(actionEClass, Action.class, "Action", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAction_SectionColumns(), ecorePackage.getEInt(), "sectionColumns", "3", 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_SectionStyle(), this.getSectionStyle(), "sectionStyle", "Auto", 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Sections(), this.getAction(), null, "sections", null, 0, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Navigation(), ecorePackage.getEObject(), null, "navigation", null, 0, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_LeftNavigation(), this.getNavigationPanel(), null, "leftNavigation", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_RightNavigation(), this.getNavigationPanel(), null, "rightNavigation", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_FloatLeftNavigation(), this.getNavigationPanel(), null, "floatLeftNavigation", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_FloatRightNavigation(), this.getNavigationPanel(), null, "floatRightNavigation", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Anonymous(), this.getAction(), null, "anonymous", null, 0, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Resources(), theResourcesPackage.getResource(), null, "resources", null, 0, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Inline(), ecorePackage.getEBoolean(), "inline", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_ModalActivator(), ecorePackage.getEBoolean(), "modalActivator", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionReferenceEClass, ActionReference.class, "ActionReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getActionReference_Target(), this.getAction(), null, "target", null, 1, 1, ActionReference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(sectionStyleEEnum, SectionStyle.class, "SectionStyle");
		addEEnumLiteral(sectionStyleEEnum, SectionStyle.AUTO);
		addEEnumLiteral(sectionStyleEEnum, SectionStyle.ACTION_GROUP);
		addEEnumLiteral(sectionStyleEEnum, SectionStyle.CARD);
		addEEnumLiteral(sectionStyleEEnum, SectionStyle.CARD_PILL);
		addEEnumLiteral(sectionStyleEEnum, SectionStyle.CARD_TAB);
		addEEnumLiteral(sectionStyleEEnum, SectionStyle.HEADER);
		addEEnumLiteral(sectionStyleEEnum, SectionStyle.PILL);
		addEEnumLiteral(sectionStyleEEnum, SectionStyle.TAB);
		addEEnumLiteral(sectionStyleEEnum, SectionStyle.TABLE);

		initEEnum(navigationPanelStyleEEnum, NavigationPanelStyle.class, "NavigationPanelStyle");
		addEEnumLiteral(navigationPanelStyleEEnum, NavigationPanelStyle.AUTO);
		addEEnumLiteral(navigationPanelStyleEEnum, NavigationPanelStyle.CARDS);
		addEEnumLiteral(navigationPanelStyleEEnum, NavigationPanelStyle.TREE);

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
			   "documentation", "Application model."
		   });
		addAnnotation
		  (labelEClass,
		   source,
		   new String[] {
			   "documentation", "Label is a text and an icon with a tooltip, notification badge, and help dialog. Labels can have children. Label is a base class for Link."
		   });
		addAnnotation
		  (getLabel_Id(),
		   source,
		   new String[] {
			   "documentation", "Unique label ID, autogenerated by default."
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
		  (getLabel_Children(),
		   source,
		   new String[] {
			   "documentation", "Label children to build UI elements like trees, lists, navigation bars and drop-downs."
		   });
		addAnnotation
		  (getLabel_Help(),
		   source,
		   new String[] {
			   "documentation", "Help modal."
		   });
		addAnnotation
		  (getLink_Location(),
		   source,
		   new String[] {
			   "documentation", "Link URL relative to the ancestor URL or ``base-uri``.\n\n``${{{base-uri}}}`` token can be used in the activator to define the uri relative to the base generation URI (output folder) instead of the parent URI. \nIt might be useful it the parent URI is an absolute external URI.\n``${{{base-uri}}}`` ends with a slash, so there is no need to add a slash. E.g. ``${{{base-uri}}}index.html``."
		   });
		addAnnotation
		  (getLink_Script(),
		   source,
		   new String[] {
			   "documentation", "Script to execute on link click (activation)."
		   });
		addAnnotation
		  (getLink_Modal(),
		   source,
		   new String[] {
			   "documentation", "Modal which opens on link activation."
		   });
		addAnnotation
		  (getLink_Confirmation(),
		   source,
		   new String[] {
			   "documentation", "Confirmation to display in a confirmation dialog before action activation to give the user an opportunity to cancel the action. E.g. confirmation of deletion."
		   });
		addAnnotation
		  (getLink_Name(),
		   source,
		   new String[] {
			   "documentation", "Link name attribute if not blank for referencing sections."
		   });
		addAnnotation
		  (getLink_Target(),
		   source,
		   new String[] {
			   "documentation", "Link name attribute if not blank for referencing sections."
		   });
		addAnnotation
		  (pageEClass,
		   source,
		   new String[] {
			   "documentation", "Application page consisting of several parts - header, navigation bar, navigation panel, content panel, and footer. Page extends Tag, but ``name`` attribute shall not be used - it defaults to ``div``."
		   });
		addAnnotation
		  (getPage_Header(),
		   source,
		   new String[] {
			   "documentation", "Page header is displayed on the top of the page."
		   });
		addAnnotation
		  (getPage_NavigationBar(),
		   source,
		   new String[] {
			   "documentation", "Navigation bar is displayed below the header."
		   });
		addAnnotation
		  (getPage_NavigationPanel(),
		   source,
		   new String[] {
			   "documentation", "Navigation panel is positioned on the left of the content panel below the navigation bar."
		   });
		addAnnotation
		  (getPage_ContentPanel(),
		   source,
		   new String[] {
			   "documentation", "Content panel is positioned on the right of the navigation panel below the navigation bar."
		   });
		addAnnotation
		  (pagePartEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for page parts."
		   });
		addAnnotation
		  (getPagePart_Items(),
		   source,
		   new String[] {
			   "documentation", "Navigation items."
		   });
		addAnnotation
		  (headerEClass,
		   source,
		   new String[] {
			   "documentation", "Page header has a title on the left and a navigation bar on the right."
		   });
		addAnnotation
		  (getHeader_Title(),
		   source,
		   new String[] {
			   "documentation", "Header title displayed on the left."
		   });
		addAnnotation
		  (navigationBarEClass,
		   source,
		   new String[] {
			   "documentation", "Navigation bar has a brand and navigation items."
		   });
		addAnnotation
		  (getNavigationBar_Brand(),
		   source,
		   new String[] {
			   "documentation", "Brand label displayed on the left."
		   });
		addAnnotation
		  (navigationPanelEClass,
		   source,
		   new String[] {
			   "documentation", "Navigation panel contains navigation items. Supports several styles."
		   });
		addAnnotation
		  (getNavigationPanel_Style(),
		   source,
		   new String[] {
			   "documentation", "Panel style"
		   });
		addAnnotation
		  (getNavigationPanel_Id(),
		   source,
		   new String[] {
			   "documentation", "ID for jsTree to store state between pages, e.g. expaned nodes."
		   });
		addAnnotation
		  (contentPanelEClass,
		   source,
		   new String[] {
			   "documentation", "Content panel displays the primary page conent and can have a navigation bar and several navigation panels."
		   });
		addAnnotation
		  (getContentPanel_Breadcrumb(),
		   source,
		   new String[] {
			   "documentation", "Breadcrumb items. Displayed on the top of the content panel."
		   });
		addAnnotation
		  (getContentPanel_Title(),
		   source,
		   new String[] {
			   "documentation", "Content title"
		   });
		addAnnotation
		  (getContentPanel_LeftNavigation(),
		   source,
		   new String[] {
			   "documentation", "Navigation panel to display on the left of the content."
		   });
		addAnnotation
		  (getContentPanel_RightNavigation(),
		   source,
		   new String[] {
			   "documentation", "Navigation panel to display on the right of the content."
		   });
		addAnnotation
		  (getContentPanel_FloatLeftNavigation(),
		   source,
		   new String[] {
			   "documentation", "Navigation panel which float to the left of the content."
		   });
		addAnnotation
		  (getContentPanel_FloatRightNavigation(),
		   source,
		   new String[] {
			   "documentation", "Navigation panel which floats to the right of the content."
		   });
		addAnnotation
		  (getContentPanel_Sections(),
		   source,
		   new String[] {
			   "documentation", "Content sections."
		   });
		addAnnotation
		  (getContentPanel_SectionColumns(),
		   source,
		   new String[] {
			   "documentation", "Applicable to section style \"Card\". Defines how many columns shall be in a row of section cards."
		   });
		addAnnotation
		  (getContentPanel_SectionStyle(),
		   source,
		   new String[] {
			   "documentation", "Defines how to generate section children."
		   });
		addAnnotation
		  (footerEClass,
		   source,
		   new String[] {
			   "documentation", "Footer is displayed below the navigation and content panels."
		   });
		addAnnotation
		  (actionEClass,
		   source,
		   new String[] {
			   "documentation", "Actions form a hierarchy. Application pages are generated from actions. "
		   });
		addAnnotation
		  (getAction_SectionColumns(),
		   source,
		   new String[] {
			   "documentation", "Applicable to section style \"Card\". Defines how many columns shall be in a row of section cards."
		   });
		addAnnotation
		  (getAction_SectionStyle(),
		   source,
		   new String[] {
			   "documentation", "Defines how to generate section children."
		   });
		addAnnotation
		  (getAction_Sections(),
		   source,
		   new String[] {
			   "documentation", "Actions which are generated into content sections. Id\'s of section actions are used to create URL fragments."
		   });
		addAnnotation
		  (getAction_Navigation(),
		   source,
		   new String[] {
			   "documentation", "Navigation items are displayed in the footer the root action, in the navigation bar for the principal action, and in the content panel navigation bar for the active action."
		   });
		addAnnotation
		  (getAction_LeftNavigation(),
		   source,
		   new String[] {
			   "documentation", "Left navigation panel"
		   });
		addAnnotation
		  (getAction_RightNavigation(),
		   source,
		   new String[] {
			   "documentation", "Right navigation panel."
		   });
		addAnnotation
		  (getAction_FloatLeftNavigation(),
		   source,
		   new String[] {
			   "documentation", "Float left navigation panel."
		   });
		addAnnotation
		  (getAction_FloatRightNavigation(),
		   source,
		   new String[] {
			   "documentation", "Float right navigation panel."
		   });
		addAnnotation
		  (getAction_Anonymous(),
		   source,
		   new String[] {
			   "documentation", "Actions which are not shown in the containing action UI, but for which pages are generated and can be explicitly referenced, e.g. from content. "
		   });
		addAnnotation
		  (getAction_Resources(),
		   source,
		   new String[] {
			   "documentation", "Resources referenced by the page. Resource names are resolved relative to the page location."
		   });
		addAnnotation
		  (getAction_Inline(),
		   source,
		   new String[] {
			   "documentation", "Inline action\'s content is displayed instead of an action link in navigation panels."
		   });
		addAnnotation
		  (getAction_ModalActivator(),
		   source,
		   new String[] {
			   "documentation", "Inline action\'s content is displayed in a modal dialog which opens on a click on the action\'s link."
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
		  (getLabel_Text(),
		   source,
		   new String[] {
			   "default-feature", "true"
		   });
		addAnnotation
		  (getLabel_Help(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getLink_Location(),
		   source,
		   new String[] {
			   "exclusive-with", "script modal"
		   });
		addAnnotation
		  (getLink_Script(),
		   source,
		   new String[] {
			   "exclusive-with", "location modal"
		   });
		addAnnotation
		  (getLink_Modal(),
		   source,
		   new String[] {
			   "homogenous", "true",
			   "exclusive-with", "location script"
		   });
		addAnnotation
		  (getLink_Name(),
		   source,
		   new String[] {
			   "exclusive-with", "script modal location"
		   });
		addAnnotation
		  (getLink_Target(),
		   source,
		   new String[] {
			   "exclusive-with", "script modal location"
		   });
		addAnnotation
		  (getPage_Header(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getPage_NavigationBar(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getPage_NavigationPanel(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getPage_ContentPanel(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getPage_Footer(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getPage_ContentRowAppearance(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getContentPanel_LeftNavigation(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getContentPanel_RightNavigation(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getContentPanel_FloatLeftNavigation(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getContentPanel_FloatRightNavigation(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getContentPanel_Sections(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getAction_Sections(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getAction_LeftNavigation(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getAction_RightNavigation(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getAction_FloatLeftNavigation(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getAction_FloatRightNavigation(),
		   source,
		   new String[] {
			   "homogenous", "true"
		   });
		addAnnotation
		  (getAction_Inline(),
		   source,
		   new String[] {
			   "exclusive-with", "location binding script modal"
		   });
		addAnnotation
		  (getAction_ModalActivator(),
		   source,
		   new String[] {
			   "exclusive-with", "location binding script inline"
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
		  (linkEClass,
		   source,
		   new String[] {
			   "constraints", "activator"
		   });
	}

} //AppPackageImpl
