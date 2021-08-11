/**
 */
package org.nasdanika.html.model.app.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;
import org.nasdanika.html.model.app.*;
import org.nasdanika.html.model.bootstrap.util.BootstrapValidator;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.nasdanika.html.model.app.AppPackage
 * @generated
 */
public class AppValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final AppValidator INSTANCE = new AppValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.nasdanika.html.model.app";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BootstrapValidator bootstrapValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AppValidator() {
		super();
		bootstrapValidator = BootstrapValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return AppPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case AppPackage.LABEL:
				return validateLabel((Label)value, diagnostics, context);
			case AppPackage.LINK:
				return validateLink((Link)value, diagnostics, context);
			case AppPackage.PAGE:
				return validatePage((Page)value, diagnostics, context);
			case AppPackage.PAGE_PART:
				return validatePagePart((PagePart)value, diagnostics, context);
			case AppPackage.HEADER:
				return validateHeader((Header)value, diagnostics, context);
			case AppPackage.NAVIGATION_BAR:
				return validateNavigationBar((NavigationBar)value, diagnostics, context);
			case AppPackage.NAVIGATION_PANEL:
				return validateNavigationPanel((NavigationPanel)value, diagnostics, context);
			case AppPackage.CONTENT_PANEL:
				return validateContentPanel((ContentPanel)value, diagnostics, context);
			case AppPackage.FOOTER:
				return validateFooter((Footer)value, diagnostics, context);
			case AppPackage.ACTION:
				return validateAction((Action)value, diagnostics, context);
			case AppPackage.SECTION_STYLE:
				return validateSectionStyle((SectionStyle)value, diagnostics, context);
			case AppPackage.COLOR:
				return validateColor((Color)value, diagnostics, context);
			case AppPackage.NAVIGATION_PANEL_STYLE:
				return validateNavigationPanelStyle((NavigationPanelStyle)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLabel(Label label, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(label, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(label, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(label, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(label, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(label, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(label, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(label, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(label, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(label, diagnostics, context);
		if (result || diagnostics != null) result &= validateLabel_color(label, diagnostics, context);
		if (result || diagnostics != null) result &= bootstrapValidator.validateItem_activeAndDisabled(label, diagnostics, context);
		return result;
	}

	/**
	 * Validates the color constraint of '<em>Label</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLabel_color(Label label, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO override the constraint, if desired
		// -> uncomment the scaffolding
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false) {
			if (diagnostics != null) {
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "color", getObjectLabel(label, context) },
						 new Object[] { label },
						 context));
			}
			return false;
		}
		return bootstrapValidator.validateItem_color(label, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLink(Link link, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(link, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(link, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(link, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(link, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(link, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(link, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(link, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(link, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(link, diagnostics, context);
		if (result || diagnostics != null) result &= validateLabel_color(link, diagnostics, context);
		if (result || diagnostics != null) result &= bootstrapValidator.validateItem_activeAndDisabled(link, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePage(Page page, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(page, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePagePart(PagePart pagePart, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(pagePart, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHeader(Header header, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(header, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNavigationBar(NavigationBar navigationBar, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(navigationBar, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNavigationPanel(NavigationPanel navigationPanel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(navigationPanel, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContentPanel(ContentPanel contentPanel, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(contentPanel, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFooter(Footer footer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(footer, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAction(Action action, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(action, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(action, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(action, diagnostics, context);
		if (result || diagnostics != null) result &= validateLabel_color(action, diagnostics, context);
		if (result || diagnostics != null) result &= bootstrapValidator.validateItem_activeAndDisabled(action, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSectionStyle(SectionStyle sectionStyle, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateColor(Color color, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNavigationPanelStyle(NavigationPanelStyle navigationPanelStyle, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //AppValidator
