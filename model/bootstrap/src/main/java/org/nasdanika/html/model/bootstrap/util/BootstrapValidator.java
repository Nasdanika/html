/**
 */
package org.nasdanika.html.model.bootstrap.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;
import org.nasdanika.emf.DiagnosticHelper;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.Text.Alignment;
import org.nasdanika.html.bootstrap.Text.Transform;
import org.nasdanika.html.bootstrap.Text.Weight;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.model.bootstrap.Accordion;
import org.nasdanika.html.model.bootstrap.ActionGroup;
import org.nasdanika.html.model.bootstrap.ActionGroupItem;
import org.nasdanika.html.model.bootstrap.Alert;
import org.nasdanika.html.model.bootstrap.Appearance;
import org.nasdanika.html.model.bootstrap.Badge;
import org.nasdanika.html.model.bootstrap.BootstrapElement;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.Border;
import org.nasdanika.html.model.bootstrap.Breadcrumb;
import org.nasdanika.html.model.bootstrap.Button;
import org.nasdanika.html.model.bootstrap.ButtonGroup;
import org.nasdanika.html.model.bootstrap.ButtonToolbar;
import org.nasdanika.html.model.bootstrap.Card;
import org.nasdanika.html.model.bootstrap.Collapse;
import org.nasdanika.html.model.bootstrap.Column;
import org.nasdanika.html.model.bootstrap.ColumnWidth;
import org.nasdanika.html.model.bootstrap.Container;
import org.nasdanika.html.model.bootstrap.ContentActionGroupItem;
import org.nasdanika.html.model.bootstrap.Div;
import org.nasdanika.html.model.bootstrap.Dropdown;
import org.nasdanika.html.model.bootstrap.Form;
import org.nasdanika.html.model.bootstrap.FormGroup;
import org.nasdanika.html.model.bootstrap.InputGroup;
import org.nasdanika.html.model.bootstrap.Item;
import org.nasdanika.html.model.bootstrap.LinkActionGroupItem;
import org.nasdanika.html.model.bootstrap.ListGroup;
import org.nasdanika.html.model.bootstrap.Modal;
import org.nasdanika.html.model.bootstrap.Navbar;
import org.nasdanika.html.model.bootstrap.Navs;
import org.nasdanika.html.model.bootstrap.Page;
import org.nasdanika.html.model.bootstrap.Row;
import org.nasdanika.html.model.bootstrap.Spacing;
import org.nasdanika.html.model.bootstrap.Table;
import org.nasdanika.html.model.bootstrap.TableCell;
import org.nasdanika.html.model.bootstrap.TableConfiguration;
import org.nasdanika.html.model.bootstrap.TableHeader;
import org.nasdanika.html.model.bootstrap.TableRow;
import org.nasdanika.html.model.bootstrap.TableRowContainer;
import org.nasdanika.html.model.bootstrap.TableSection;
import org.nasdanika.html.model.bootstrap.Tag;
import org.nasdanika.html.model.bootstrap.Text;
import org.nasdanika.html.model.bootstrap.Tooltip;

import org.nasdanika.html.model.html.util.HtmlValidator;

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
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HtmlValidator htmlValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BootstrapValidator() {
		super();
		htmlValidator = HtmlValidator.INSTANCE;
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
			case BootstrapPackage.BOOTSTRAP_ELEMENT:
				return validateBootstrapElement((BootstrapElement)value, diagnostics, context);
			case BootstrapPackage.PAGE:
				return validatePage((Page)value, diagnostics, context);
			case BootstrapPackage.APPEARANCE:
				return validateAppearance((Appearance)value, diagnostics, context);
			case BootstrapPackage.BORDER:
				return validateBorder((Border)value, diagnostics, context);
			case BootstrapPackage.SPACING:
				return validateSpacing((Spacing)value, diagnostics, context);
			case BootstrapPackage.TEXT:
				return validateText((Text)value, diagnostics, context);
			case BootstrapPackage.FLOAT:
				return validateFloat((org.nasdanika.html.model.bootstrap.Float)value, diagnostics, context);
			case BootstrapPackage.TAG:
				return validateTag((Tag)value, diagnostics, context);
			case BootstrapPackage.MODAL:
				return validateModal((Modal)value, diagnostics, context);
			case BootstrapPackage.DIV:
				return validateDiv((Div)value, diagnostics, context);
			case BootstrapPackage.ITEM:
				return validateItem((Item)value, diagnostics, context);
			case BootstrapPackage.ACTION_GROUP_ITEM:
				return validateActionGroupItem((ActionGroupItem)value, diagnostics, context);
			case BootstrapPackage.LINK_ACTION_GROUP_ITEM:
				return validateLinkActionGroupItem((LinkActionGroupItem)value, diagnostics, context);
			case BootstrapPackage.CONTENT_ACTION_GROUP_ITEM:
				return validateContentActionGroupItem((ContentActionGroupItem)value, diagnostics, context);
			case BootstrapPackage.ACTION_GROUP:
				return validateActionGroup((ActionGroup)value, diagnostics, context);
			case BootstrapPackage.CONTAINER:
				return validateContainer((Container)value, diagnostics, context);
			case BootstrapPackage.ROW:
				return validateRow((Row)value, diagnostics, context);
			case BootstrapPackage.COLUMN_WIDTH:
				return validateColumnWidth((ColumnWidth)value, diagnostics, context);
			case BootstrapPackage.COLUMN:
				return validateColumn((Column)value, diagnostics, context);
			case BootstrapPackage.TABLE_ROW_CONTAINER:
				return validateTableRowContainer((TableRowContainer)value, diagnostics, context);
			case BootstrapPackage.TABLE_SECTION:
				return validateTableSection((TableSection)value, diagnostics, context);
			case BootstrapPackage.TABLE_HEADER:
				return validateTableHeader((TableHeader)value, diagnostics, context);
			case BootstrapPackage.TABLE_CONFIGURATION:
				return validateTableConfiguration((TableConfiguration)value, diagnostics, context);
			case BootstrapPackage.TABLE:
				return validateTable((Table)value, diagnostics, context);
			case BootstrapPackage.TABLE_ROW:
				return validateTableRow((TableRow)value, diagnostics, context);
			case BootstrapPackage.TABLE_CELL:
				return validateTableCell((TableCell)value, diagnostics, context);
			case BootstrapPackage.CARD:
				return validateCard((Card)value, diagnostics, context);
			case BootstrapPackage.ALERT:
				return validateAlert((Alert)value, diagnostics, context);
			case BootstrapPackage.BADGE:
				return validateBadge((Badge)value, diagnostics, context);
			case BootstrapPackage.BREADCRUMB:
				return validateBreadcrumb((Breadcrumb)value, diagnostics, context);
			case BootstrapPackage.BUTTON:
				return validateButton((Button)value, diagnostics, context);
			case BootstrapPackage.BUTTON_GROUP:
				return validateButtonGroup((ButtonGroup)value, diagnostics, context);
			case BootstrapPackage.BUTTON_TOOLBAR:
				return validateButtonToolbar((ButtonToolbar)value, diagnostics, context);
			case BootstrapPackage.DROPDOWN:
				return validateDropdown((Dropdown)value, diagnostics, context);
			case BootstrapPackage.FORM:
				return validateForm((Form)value, diagnostics, context);
			case BootstrapPackage.LIST_GROUP:
				return validateListGroup((ListGroup)value, diagnostics, context);
			case BootstrapPackage.NAVS:
				return validateNavs((Navs)value, diagnostics, context);
			case BootstrapPackage.NAVBAR:
				return validateNavbar((Navbar)value, diagnostics, context);
			case BootstrapPackage.TOOLTIP:
				return validateTooltip((Tooltip)value, diagnostics, context);
			case BootstrapPackage.INPUT_GROUP:
				return validateInputGroup((InputGroup)value, diagnostics, context);
			case BootstrapPackage.FORM_GROUP:
				return validateFormGroup((FormGroup)value, diagnostics, context);
			case BootstrapPackage.COLLAPSE:
				return validateCollapse((Collapse)value, diagnostics, context);
			case BootstrapPackage.ACCORDION:
				return validateAccordion((Accordion)value, diagnostics, context);
			case BootstrapPackage.THEME:
				return validateTheme((Theme)value, diagnostics, context);
			case BootstrapPackage.COLOR:
				return validateColor((Color)value, diagnostics, context);
			case BootstrapPackage.SIZE:
				return validateSize((Size)value, diagnostics, context);
			case BootstrapPackage.BREAKPOINT:
				return validateBreakpoint((Breakpoint)value, diagnostics, context);
			case BootstrapPackage.TEXT_ALIGNMENT:
				return validateTextAlignment((Alignment)value, diagnostics, context);
			case BootstrapPackage.TEXT_TRANSFORM:
				return validateTextTransform((Transform)value, diagnostics, context);
			case BootstrapPackage.TEXT_WEIGHT:
				return validateTextWeight((Weight)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBootstrapElement(BootstrapElement bootstrapElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(bootstrapElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePage(Page page, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(page, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(page, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(page, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(page, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(page, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(page, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(page, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(page, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(page, diagnostics, context);
		if (result || diagnostics != null) result &= validatePage_theme(page, diagnostics, context);
		return result;
	}

	/**
	 * Validates the theme constraint of '<em>Page</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean validatePage_theme(Page page, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (diagnostics != null && !page.isCdn() && page.getTheme() != null) {
			DiagnosticHelper helper = new DiagnosticHelper(diagnostics, DIAGNOSTIC_SOURCE, 0, page);
			helper.error("Theme can only be specified with cdn set to 'true'", BootstrapPackage.Literals.PAGE__THEME);					
			return helper.isSuccess();
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAppearance(Appearance appearance, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(appearance, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(appearance, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(appearance, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(appearance, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(appearance, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(appearance, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(appearance, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(appearance, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(appearance, diagnostics, context);
		if (result || diagnostics != null) result &= validateAppearance_border_overlap(appearance, diagnostics, context);
		if (result || diagnostics != null) result &= validateAppearance_background(appearance, diagnostics, context);
		if (result || diagnostics != null) result &= validateAppearance_attributes(appearance, diagnostics, context);
		return result;
	}

	/**
	 * Validates the border_overlap constraint of '<em>Appearance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAppearance_border_overlap(Appearance appearance, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "border_overlap", getObjectLabel(appearance, context) },
						 new Object[] { appearance },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the background constraint of '<em>Appearance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAppearance_background(Appearance appearance, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "background", getObjectLabel(appearance, context) },
						 new Object[] { appearance },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the attributes constraint of '<em>Appearance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAppearance_attributes(Appearance appearance, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "attributes", getObjectLabel(appearance, context) },
						 new Object[] { appearance },
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
	public boolean validateBorder(Border border, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(border, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(border, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(border, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(border, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(border, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(border, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(border, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(border, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(border, diagnostics, context);
		if (result || diagnostics != null) result &= validateBorder_placement(border, diagnostics, context);
		if (result || diagnostics != null) result &= validateBorder_color(border, diagnostics, context);
		return result;
	}

	/**
	 * Validates the placement constraint of '<em>Border</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBorder_placement(Border border, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "placement", getObjectLabel(border, context) },
						 new Object[] { border },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the color constraint of '<em>Border</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBorder_color(Border border, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "color", getObjectLabel(border, context) },
						 new Object[] { border },
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
	public boolean validateSpacing(Spacing spacing, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(spacing, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(spacing, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(spacing, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(spacing, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(spacing, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(spacing, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(spacing, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(spacing, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(spacing, diagnostics, context);
		if (result || diagnostics != null) result &= validateSpacing_size(spacing, diagnostics, context);
		if (result || diagnostics != null) result &= validateSpacing_breakpoint(spacing, diagnostics, context);
		return result;
	}

	/**
	 * Validates the size constraint of '<em>Spacing</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSpacing_size(Spacing spacing, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "size", getObjectLabel(spacing, context) },
						 new Object[] { spacing },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the breakpoint constraint of '<em>Spacing</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSpacing_breakpoint(Spacing spacing, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "breakpoint", getObjectLabel(spacing, context) },
						 new Object[] { spacing },
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
	public boolean validateText(Text text, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(text, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(text, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(text, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(text, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(text, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(text, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(text, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(text, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(text, diagnostics, context);
		if (result || diagnostics != null) result &= validateText_attributes(text, diagnostics, context);
		return result;
	}

	/**
	 * Validates the attributes constraint of '<em>Text</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateText_attributes(Text text, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "attributes", getObjectLabel(text, context) },
						 new Object[] { text },
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
	public boolean validateFloat(org.nasdanika.html.model.bootstrap.Float float_, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(float_, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(float_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(float_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(float_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(float_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(float_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(float_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(float_, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(float_, diagnostics, context);
		if (result || diagnostics != null) result &= validateFloat_attributes(float_, diagnostics, context);
		return result;
	}

	/**
	 * Validates the attributes constraint of '<em>Float</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFloat_attributes(org.nasdanika.html.model.bootstrap.Float float_, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "attributes", getObjectLabel(float_, context) },
						 new Object[] { float_ },
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
		if (result || diagnostics != null) result &= htmlValidator.validateTag_attributes(tag, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDiv(Div div, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(div, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(div, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(div, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(div, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(div, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(div, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(div, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(div, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(div, diagnostics, context);
		if (result || diagnostics != null) result &= htmlValidator.validateTag_attributes(div, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateItem(Item item, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(item, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(item, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(item, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(item, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(item, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(item, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(item, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(item, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(item, diagnostics, context);
		if (result || diagnostics != null) result &= validateItem_color(item, diagnostics, context);
		if (result || diagnostics != null) result &= validateItem_activeAndDisabled(item, diagnostics, context);
		return result;
	}

	/**
	 * Validates the color constraint of '<em>Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateItem_color(Item item, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "color", getObjectLabel(item, context) },
						 new Object[] { item },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the activeAndDisabled constraint of '<em>Item</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateItem_activeAndDisabled(Item item, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "activeAndDisabled", getObjectLabel(item, context) },
						 new Object[] { item },
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
	public boolean validateActionGroupItem(ActionGroupItem actionGroupItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(actionGroupItem, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(actionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(actionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(actionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(actionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(actionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(actionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(actionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(actionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateItem_color(actionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateItem_activeAndDisabled(actionGroupItem, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLinkActionGroupItem(LinkActionGroupItem linkActionGroupItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(linkActionGroupItem, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(linkActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(linkActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(linkActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(linkActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(linkActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(linkActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(linkActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(linkActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateItem_color(linkActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateItem_activeAndDisabled(linkActionGroupItem, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContentActionGroupItem(ContentActionGroupItem contentActionGroupItem, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(contentActionGroupItem, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(contentActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(contentActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(contentActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(contentActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(contentActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(contentActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(contentActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(contentActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateItem_color(contentActionGroupItem, diagnostics, context);
		if (result || diagnostics != null) result &= validateItem_activeAndDisabled(contentActionGroupItem, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateActionGroup(ActionGroup actionGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(actionGroup, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(actionGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(actionGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(actionGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(actionGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(actionGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(actionGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(actionGroup, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(actionGroup, diagnostics, context);
		if (result || diagnostics != null) result &= htmlValidator.validateTag_attributes(actionGroup, diagnostics, context);
		return result;
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
	public boolean validateRow(Row row, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(row, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateColumnWidth(ColumnWidth columnWidth, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(columnWidth, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(columnWidth, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(columnWidth, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(columnWidth, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(columnWidth, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(columnWidth, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(columnWidth, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(columnWidth, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(columnWidth, diagnostics, context);
		if (result || diagnostics != null) result &= validateColumnWidth_breakpoint(columnWidth, diagnostics, context);
		if (result || diagnostics != null) result &= validateColumnWidth_width(columnWidth, diagnostics, context);
		return result;
	}

	/**
	 * Validates the breakpoint constraint of '<em>Column Width</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateColumnWidth_breakpoint(ColumnWidth columnWidth, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "breakpoint", getObjectLabel(columnWidth, context) },
						 new Object[] { columnWidth },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * Validates the width constraint of '<em>Column Width</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateColumnWidth_width(ColumnWidth columnWidth, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
						 new Object[] { "width", getObjectLabel(columnWidth, context) },
						 new Object[] { columnWidth },
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
	public boolean validateColumn(Column column, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(column, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableRowContainer(TableRowContainer tableRowContainer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tableRowContainer, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableSection(TableSection tableSection, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tableSection, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableHeader(TableHeader tableHeader, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tableHeader, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableConfiguration(TableConfiguration tableConfiguration, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tableConfiguration, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTable(Table table, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(table, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableRow(TableRow tableRow, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tableRow, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTableCell(TableCell tableCell, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tableCell, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCard(Card card, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(card, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(card, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(card, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(card, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(card, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(card, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(card, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(card, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(card, diagnostics, context);
		if (result || diagnostics != null) result &= htmlValidator.validateTag_attributes(card, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAlert(Alert alert, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(alert, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(alert, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(alert, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(alert, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(alert, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(alert, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(alert, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(alert, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(alert, diagnostics, context);
		if (result || diagnostics != null) result &= htmlValidator.validateTag_attributes(alert, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBadge(Badge badge, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(badge, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(badge, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(badge, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(badge, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(badge, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(badge, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(badge, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(badge, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(badge, diagnostics, context);
		if (result || diagnostics != null) result &= htmlValidator.validateTag_attributes(badge, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBreadcrumb(Breadcrumb breadcrumb, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(breadcrumb, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateButton(Button button, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(button, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(button, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(button, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(button, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(button, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(button, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(button, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(button, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(button, diagnostics, context);
		if (result || diagnostics != null) result &= htmlValidator.validateTag_attributes(button, diagnostics, context);
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateButtonGroup(ButtonGroup buttonGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(buttonGroup, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateButtonToolbar(ButtonToolbar buttonToolbar, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(buttonToolbar, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDropdown(Dropdown dropdown, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(dropdown, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateForm(Form form, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(form, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateListGroup(ListGroup listGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(listGroup, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNavs(Navs navs, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(navs, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNavbar(Navbar navbar, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(navbar, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTooltip(Tooltip tooltip, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tooltip, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInputGroup(InputGroup inputGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(inputGroup, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFormGroup(FormGroup formGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(formGroup, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCollapse(Collapse collapse, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(collapse, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateModal(Modal modal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(modal, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAccordion(Accordion accordion, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(accordion, diagnostics, context);
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
	public boolean validateSize(Size size, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBreakpoint(Breakpoint breakpoint, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTextAlignment(Alignment textAlignment, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTextTransform(Transform textTransform, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTextWeight(Weight textWeight, DiagnosticChain diagnostics, Map<Object, Object> context) {
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
