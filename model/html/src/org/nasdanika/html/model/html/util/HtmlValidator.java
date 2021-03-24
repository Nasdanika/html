/**
 */
package org.nasdanika.html.model.html.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.nasdanika.html.model.html.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.nasdanika.html.model.html.HtmlPackage
 * @generated
 */
public class HtmlValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final HtmlValidator INSTANCE = new HtmlValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.nasdanika.html.model.html";

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
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HtmlValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return HtmlPackage.eINSTANCE;
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
			case HtmlPackage.HTML_ELEMENT:
				return validateHtmlElement((HtmlElement)value, diagnostics, context);
			case HtmlPackage.CONTAINER:
				return validateContainer((Container)value, diagnostics, context);
			case HtmlPackage.TAG:
				return validateTag((Tag)value, diagnostics, context);
			case HtmlPackage.CONTENT_TAG:
				return validateContentTag((ContentTag)value, diagnostics, context);
			case HtmlPackage.PAGE:
				return validatePage((Page)value, diagnostics, context);
			case HtmlPackage.STYLESHEET:
				return validateStylesheet((Stylesheet)value, diagnostics, context);
			case HtmlPackage.STYLESHEET_RESOURCE:
				return validateStylesheetResource((StylesheetResource)value, diagnostics, context);
			case HtmlPackage.STYLESHEET_REFERENCE:
				return validateStylesheetReference((StylesheetReference)value, diagnostics, context);
			case HtmlPackage.SCRIPT:
				return validateScript((Script)value, diagnostics, context);
			case HtmlPackage.SCRIPT_RESOURCE:
				return validateScriptResource((ScriptResource)value, diagnostics, context);
			case HtmlPackage.SCRIPT_REFERENCE:
				return validateScriptReference((ScriptReference)value, diagnostics, context);
			case HtmlPackage.VIEW_PART_ADAPTER:
				return validateViewPartAdapter((ViewPartAdapter)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateHtmlElement(HtmlElement htmlElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(htmlElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContainer(Container container, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(container, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTag(Tag tag, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(tag, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(tag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(tag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(tag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(tag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(tag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(tag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(tag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(tag, diagnostics, context);
		if (result || diagnostics != null) result &= validateTag_attributes(tag, diagnostics, context);
		return result;
	}

	/**
	 * Validates the attributes constraint of '<em>Tag</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTag_attributes(Tag tag, DiagnosticChain diagnostics, Map<Object, Object> context) {
		// TODO implement the constraint
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
						 new Object[] { "attributes", getObjectLabel(tag, context) },
						 new Object[] { tag },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContentTag(ContentTag contentTag, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(contentTag, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(contentTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(contentTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(contentTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(contentTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(contentTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(contentTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(contentTag, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(contentTag, diagnostics, context);
		if (result || diagnostics != null) result &= validateTag_attributes(contentTag, diagnostics, context);
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
	public boolean validateStylesheet(Stylesheet stylesheet, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(stylesheet, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStylesheetResource(StylesheetResource stylesheetResource, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(stylesheetResource, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStylesheetReference(StylesheetReference stylesheetReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(stylesheetReference, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScript(Script script, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(script, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScriptResource(ScriptResource scriptResource, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scriptResource, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateScriptReference(ScriptReference scriptReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(scriptReference, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateViewPartAdapter(ViewPartAdapter viewPartAdapter, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(viewPartAdapter, diagnostics, context);
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

} //HtmlValidator
