/**
 */
package org.nasdanika.html.model.bootstrap.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.DeviceSize;
import org.nasdanika.html.bootstrap.Direction;
import org.nasdanika.html.bootstrap.Placement;
import org.nasdanika.html.bootstrap.Theme;

import org.nasdanika.html.model.bootstrap.*;
import org.nasdanika.html.model.html.util.DiagnosticHelper;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage
 * @generated
 */
public class BootstrapValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final BootstrapValidator INSTANCE = new BootstrapValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.nasdanika.html.model.bootstrap";

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
	public BootstrapValidator() {
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
	  return BootstrapPackage.eINSTANCE;
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
			case BootstrapPackage.BOOTSTRAP_CDN_FACET:
				return validateBootstrapCDNFacet((BootstrapCDNFacet)value, diagnostics, context);
			case BootstrapPackage.BOOTSTRAP_ELEMENT:
				return validateBootstrapElement((BootstrapElement)value, diagnostics, context);
			case BootstrapPackage.CONTENT_BOOTSTRAP_ELEMENT:
				return validateContentBootstrapElement((ContentBootstrapElement)value, diagnostics, context);
			case BootstrapPackage.CONTAINER_BOOTSTRAP_ELEMENT:
				return validateContainerBootstrapElement((ContainerBootstrapElement)value, diagnostics, context);
			case BootstrapPackage.WRAP:
				return validateWrap((Wrap)value, diagnostics, context);
			case BootstrapPackage.ALERT:
				return validateAlert((Alert)value, diagnostics, context);
			case BootstrapPackage.LIST_GROUP:
				return validateListGroup((ListGroup)value, diagnostics, context);
			case BootstrapPackage.LIST_GROUP_ITEM:
				return validateListGroupItem((ListGroupItem)value, diagnostics, context);
			case BootstrapPackage.BADGE:
				return validateBadge((Badge)value, diagnostics, context);
			case BootstrapPackage.THEME:
				return validateTheme((Theme)value, diagnostics, context);
			case BootstrapPackage.COLOR:
				return validateColor((Color)value, diagnostics, context);
			case BootstrapPackage.DEVICE_SIZE:
				return validateDeviceSize((DeviceSize)value, diagnostics, context);
			case BootstrapPackage.DIRECTION:
				return validateDirection((Direction)value, diagnostics, context);
			case BootstrapPackage.PLACEMENT:
				return validatePlacement((Placement)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBootstrapCDNFacet(BootstrapCDNFacet bootstrapCDNFacet, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)bootstrapCDNFacet, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBootstrapElement(BootstrapElement bootstrapElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)bootstrapElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContentBootstrapElement(ContentBootstrapElement contentBootstrapElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)contentBootstrapElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContainerBootstrapElement(ContainerBootstrapElement containerBootstrapElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)containerBootstrapElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateWrap(Wrap wrap, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)wrap, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAlert(Alert alert, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)alert, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateListGroup(ListGroup listGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)listGroup, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateListGroupItem(ListGroupItem listGroupItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment((EObject)listGroupItem, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms((EObject)listGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms((EObject)listGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained((EObject)listGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired((EObject)listGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves((EObject)listGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID((EObject)listGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique((EObject)listGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique((EObject)listGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateListGroupItem_activeDisabled(listGroupItem, diagnostics, context);
		return result;
	}

	/**
	 * Validates the activeDisabled constraint of '<em>List Group Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validateListGroupItem_activeDisabled(ListGroupItem listGroupItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		DiagnosticHelper diagnosticHelper = new DiagnosticHelper(diagnostics, DIAGNOSTIC_SOURCE, 0, listGroupItem);
		if (listGroupItem.isActive() && listGroupItem.isDisabled()) {
			diagnosticHelper.addDiagnostic(Diagnostic.WARNING, "Active and disabled at the same time", null, BootstrapPackage.Literals.LIST_GROUP_ITEM__DISABLED);
		}
		return diagnosticHelper.isSuccess();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBadge(Badge badge, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint((EObject)badge, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTheme(Theme theme, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
	public boolean validateDeviceSize(DeviceSize deviceSize, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDirection(Direction direction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePlacement(Placement placement, DiagnosticChain diagnostics, Map<Object, Object> context) {
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

} //BootstrapValidator
