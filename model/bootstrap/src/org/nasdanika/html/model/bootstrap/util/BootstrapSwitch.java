/**
 */
package org.nasdanika.html.model.bootstrap.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.nasdanika.html.model.bootstrap.Accordion;
import org.nasdanika.html.model.bootstrap.ActionGroup;
import org.nasdanika.html.model.bootstrap.ActionGroupItem;
import org.nasdanika.html.model.bootstrap.Alert;
import org.nasdanika.html.model.bootstrap.Appearance;
import org.nasdanika.html.model.bootstrap.Badge;
import org.nasdanika.html.model.bootstrap.BootstrapElement;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.BootstrapPage;
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
import org.nasdanika.html.model.bootstrap.ContentTag;
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

import org.nasdanika.html.model.html.HtmlElement;
import org.nasdanika.html.model.html.Page;

import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.nasdanika.html.model.bootstrap.BootstrapPackage
 * @generated
 */
public class BootstrapSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BootstrapPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BootstrapSwitch() {
		if (modelPackage == null) {
			modelPackage = BootstrapPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case BootstrapPackage.BOOTSTRAP_ELEMENT: {
				BootstrapElement bootstrapElement = (BootstrapElement)theEObject;
				T result = caseBootstrapElement(bootstrapElement);
				if (result == null) result = caseHtmlElement(bootstrapElement);
				if (result == null) result = caseModelElement(bootstrapElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.BOOTSTRAP_PAGE: {
				BootstrapPage bootstrapPage = (BootstrapPage)theEObject;
				T result = caseBootstrapPage(bootstrapPage);
				if (result == null) result = casePage(bootstrapPage);
				if (result == null) result = caseNamedElement(bootstrapPage);
				if (result == null) result = caseModelElement(bootstrapPage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.APPEARANCE: {
				Appearance appearance = (Appearance)theEObject;
				T result = caseAppearance(appearance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.BORDER: {
				Border border = (Border)theEObject;
				T result = caseBorder(border);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.SPACING: {
				Spacing spacing = (Spacing)theEObject;
				T result = caseSpacing(spacing);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.TEXT: {
				Text text = (Text)theEObject;
				T result = caseText(text);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.FLOAT: {
				org.nasdanika.html.model.bootstrap.Float float_ = (org.nasdanika.html.model.bootstrap.Float)theEObject;
				T result = caseFloat(float_);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.TAG: {
				Tag tag = (Tag)theEObject;
				T result = caseTag(tag);
				if (result == null) result = caseHtml_Tag(tag);
				if (result == null) result = caseBootstrapElement(tag);
				if (result == null) result = caseHtmlElement(tag);
				if (result == null) result = caseModelElement(tag);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.CONTENT_TAG: {
				ContentTag contentTag = (ContentTag)theEObject;
				T result = caseContentTag(contentTag);
				if (result == null) result = caseHtml_ContentTag(contentTag);
				if (result == null) result = caseBootstrapElement(contentTag);
				if (result == null) result = caseHtml_Tag(contentTag);
				if (result == null) result = caseHtml_Container(contentTag);
				if (result == null) result = caseHtmlElement(contentTag);
				if (result == null) result = caseModelElement(contentTag);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.DIV: {
				Div div = (Div)theEObject;
				T result = caseDiv(div);
				if (result == null) result = caseTag(div);
				if (result == null) result = caseHtml_Tag(div);
				if (result == null) result = caseBootstrapElement(div);
				if (result == null) result = caseHtmlElement(div);
				if (result == null) result = caseModelElement(div);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.ITEM: {
				Item item = (Item)theEObject;
				T result = caseItem(item);
				if (result == null) result = caseModelElement(item);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.ACTION_GROUP_ITEM: {
				ActionGroupItem actionGroupItem = (ActionGroupItem)theEObject;
				T result = caseActionGroupItem(actionGroupItem);
				if (result == null) result = caseItem(actionGroupItem);
				if (result == null) result = caseModelElement(actionGroupItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.LINK_ACTION_GROUP_ITEM: {
				LinkActionGroupItem linkActionGroupItem = (LinkActionGroupItem)theEObject;
				T result = caseLinkActionGroupItem(linkActionGroupItem);
				if (result == null) result = caseActionGroupItem(linkActionGroupItem);
				if (result == null) result = caseItem(linkActionGroupItem);
				if (result == null) result = caseModelElement(linkActionGroupItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.CONTENT_ACTION_GROUP_ITEM: {
				ContentActionGroupItem contentActionGroupItem = (ContentActionGroupItem)theEObject;
				T result = caseContentActionGroupItem(contentActionGroupItem);
				if (result == null) result = caseActionGroupItem(contentActionGroupItem);
				if (result == null) result = caseHtml_Container(contentActionGroupItem);
				if (result == null) result = caseItem(contentActionGroupItem);
				if (result == null) result = caseModelElement(contentActionGroupItem);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.ACTION_GROUP: {
				ActionGroup actionGroup = (ActionGroup)theEObject;
				T result = caseActionGroup(actionGroup);
				if (result == null) result = caseDiv(actionGroup);
				if (result == null) result = caseTag(actionGroup);
				if (result == null) result = caseHtml_Tag(actionGroup);
				if (result == null) result = caseBootstrapElement(actionGroup);
				if (result == null) result = caseHtmlElement(actionGroup);
				if (result == null) result = caseModelElement(actionGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.CONTAINER: {
				Container container = (Container)theEObject;
				T result = caseContainer(container);
				if (result == null) result = caseBootstrapElement(container);
				if (result == null) result = caseHtmlElement(container);
				if (result == null) result = caseModelElement(container);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.ROW: {
				Row row = (Row)theEObject;
				T result = caseRow(row);
				if (result == null) result = caseBootstrapElement(row);
				if (result == null) result = caseHtmlElement(row);
				if (result == null) result = caseModelElement(row);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.COLUMN_WIDTH: {
				ColumnWidth columnWidth = (ColumnWidth)theEObject;
				T result = caseColumnWidth(columnWidth);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.COLUMN: {
				Column column = (Column)theEObject;
				T result = caseColumn(column);
				if (result == null) result = caseBootstrapElement(column);
				if (result == null) result = caseHtml_Container(column);
				if (result == null) result = caseHtmlElement(column);
				if (result == null) result = caseModelElement(column);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.TABLE_ROW_CONTAINER: {
				TableRowContainer tableRowContainer = (TableRowContainer)theEObject;
				T result = caseTableRowContainer(tableRowContainer);
				if (result == null) result = caseBootstrapElement(tableRowContainer);
				if (result == null) result = caseHtmlElement(tableRowContainer);
				if (result == null) result = caseModelElement(tableRowContainer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.TABLE_SECTION: {
				TableSection tableSection = (TableSection)theEObject;
				T result = caseTableSection(tableSection);
				if (result == null) result = caseTableRowContainer(tableSection);
				if (result == null) result = caseBootstrapElement(tableSection);
				if (result == null) result = caseHtmlElement(tableSection);
				if (result == null) result = caseModelElement(tableSection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.TABLE_HEADER: {
				TableHeader tableHeader = (TableHeader)theEObject;
				T result = caseTableHeader(tableHeader);
				if (result == null) result = caseTableSection(tableHeader);
				if (result == null) result = caseTableRowContainer(tableHeader);
				if (result == null) result = caseBootstrapElement(tableHeader);
				if (result == null) result = caseHtmlElement(tableHeader);
				if (result == null) result = caseModelElement(tableHeader);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.TABLE_CONFIGURATION: {
				TableConfiguration tableConfiguration = (TableConfiguration)theEObject;
				T result = caseTableConfiguration(tableConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.TABLE: {
				Table table = (Table)theEObject;
				T result = caseTable(table);
				if (result == null) result = caseTableRowContainer(table);
				if (result == null) result = caseTableConfiguration(table);
				if (result == null) result = caseBootstrapElement(table);
				if (result == null) result = caseHtmlElement(table);
				if (result == null) result = caseModelElement(table);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.TABLE_ROW: {
				TableRow tableRow = (TableRow)theEObject;
				T result = caseTableRow(tableRow);
				if (result == null) result = caseBootstrapElement(tableRow);
				if (result == null) result = caseHtmlElement(tableRow);
				if (result == null) result = caseModelElement(tableRow);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.TABLE_CELL: {
				TableCell tableCell = (TableCell)theEObject;
				T result = caseTableCell(tableCell);
				if (result == null) result = caseHtml_Container(tableCell);
				if (result == null) result = caseBootstrapElement(tableCell);
				if (result == null) result = caseHtmlElement(tableCell);
				if (result == null) result = caseModelElement(tableCell);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.CARD: {
				Card card = (Card)theEObject;
				T result = caseCard(card);
				if (result == null) result = caseDiv(card);
				if (result == null) result = caseTag(card);
				if (result == null) result = caseHtml_Tag(card);
				if (result == null) result = caseBootstrapElement(card);
				if (result == null) result = caseHtmlElement(card);
				if (result == null) result = caseModelElement(card);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.ALERT: {
				Alert alert = (Alert)theEObject;
				T result = caseAlert(alert);
				if (result == null) result = caseDiv(alert);
				if (result == null) result = caseTag(alert);
				if (result == null) result = caseHtml_Tag(alert);
				if (result == null) result = caseBootstrapElement(alert);
				if (result == null) result = caseHtmlElement(alert);
				if (result == null) result = caseModelElement(alert);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.BADGE: {
				Badge badge = (Badge)theEObject;
				T result = caseBadge(badge);
				if (result == null) result = caseDiv(badge);
				if (result == null) result = caseTag(badge);
				if (result == null) result = caseHtml_Tag(badge);
				if (result == null) result = caseBootstrapElement(badge);
				if (result == null) result = caseHtmlElement(badge);
				if (result == null) result = caseModelElement(badge);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.BREADCRUMB: {
				Breadcrumb breadcrumb = (Breadcrumb)theEObject;
				T result = caseBreadcrumb(breadcrumb);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.BUTTON: {
				Button button = (Button)theEObject;
				T result = caseButton(button);
				if (result == null) result = caseDiv(button);
				if (result == null) result = caseTag(button);
				if (result == null) result = caseHtml_Tag(button);
				if (result == null) result = caseBootstrapElement(button);
				if (result == null) result = caseHtmlElement(button);
				if (result == null) result = caseModelElement(button);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.BUTTON_GROUP: {
				ButtonGroup buttonGroup = (ButtonGroup)theEObject;
				T result = caseButtonGroup(buttonGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.BUTTON_TOOLBAR: {
				ButtonToolbar buttonToolbar = (ButtonToolbar)theEObject;
				T result = caseButtonToolbar(buttonToolbar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.DROPDOWN: {
				Dropdown dropdown = (Dropdown)theEObject;
				T result = caseDropdown(dropdown);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.FORM: {
				Form form = (Form)theEObject;
				T result = caseForm(form);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.LIST_GROUP: {
				ListGroup listGroup = (ListGroup)theEObject;
				T result = caseListGroup(listGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.NAVS: {
				Navs navs = (Navs)theEObject;
				T result = caseNavs(navs);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.NAVBAR: {
				Navbar navbar = (Navbar)theEObject;
				T result = caseNavbar(navbar);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.TOOLTIP: {
				Tooltip tooltip = (Tooltip)theEObject;
				T result = caseTooltip(tooltip);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.INPUT_GROUP: {
				InputGroup inputGroup = (InputGroup)theEObject;
				T result = caseInputGroup(inputGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.FORM_GROUP: {
				FormGroup formGroup = (FormGroup)theEObject;
				T result = caseFormGroup(formGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.COLLAPSE: {
				Collapse collapse = (Collapse)theEObject;
				T result = caseCollapse(collapse);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.MODAL: {
				Modal modal = (Modal)theEObject;
				T result = caseModal(modal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BootstrapPackage.ACCORDION: {
				Accordion accordion = (Accordion)theEObject;
				T result = caseAccordion(accordion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBootstrapElement(BootstrapElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBootstrapPage(BootstrapPage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Appearance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Appearance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAppearance(Appearance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Border</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Border</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBorder(Border object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Spacing</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Spacing</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpacing(Spacing object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Text</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Text</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseText(Text object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Float</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Float</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFloat(org.nasdanika.html.model.bootstrap.Float object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tag</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tag</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTag(Tag object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Content Tag</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Content Tag</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContentTag(ContentTag object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Div</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Div</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDiv(Div object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseItem(Item object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Group Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Group Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionGroupItem(ActionGroupItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Link Action Group Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Link Action Group Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLinkActionGroupItem(LinkActionGroupItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Content Action Group Item</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Content Action Group Item</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContentActionGroupItem(ContentActionGroupItem object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Action Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Action Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseActionGroup(ActionGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContainer(Container object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Row</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Row</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRow(Row object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Column Width</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Column Width</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseColumnWidth(ColumnWidth object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Column</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Column</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseColumn(Column object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Row Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Row Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTableRowContainer(TableRowContainer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Section</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Section</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTableSection(TableSection object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Header</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Header</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTableHeader(TableHeader object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Configuration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTableConfiguration(TableConfiguration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTable(Table object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Row</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Row</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTableRow(TableRow object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Table Cell</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Table Cell</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTableCell(TableCell object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Card</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Card</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCard(Card object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Alert</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Alert</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAlert(Alert object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Badge</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Badge</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBadge(Badge object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Breadcrumb</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Breadcrumb</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBreadcrumb(Breadcrumb object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Button</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Button</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseButton(Button object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Button Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Button Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseButtonGroup(ButtonGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Button Toolbar</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Button Toolbar</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseButtonToolbar(ButtonToolbar object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Dropdown</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Dropdown</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDropdown(Dropdown object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Form</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Form</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseForm(Form object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>List Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>List Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseListGroup(ListGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navs</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navs</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavs(Navs object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Navbar</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Navbar</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNavbar(Navbar object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tooltip</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tooltip</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTooltip(Tooltip object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Input Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInputGroup(InputGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Form Group</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Form Group</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFormGroup(FormGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collapse</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collapse</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCollapse(Collapse object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Modal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Modal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModal(Modal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Accordion</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Accordion</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAccordion(Accordion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelElement(ModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHtmlElement(HtmlElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Page</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Page</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePage(Page object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tag</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tag</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHtml_Tag(org.nasdanika.html.model.html.Tag object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Container</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Container</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHtml_Container(org.nasdanika.html.model.html.Container object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Content Tag</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Content Tag</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseHtml_ContentTag(org.nasdanika.html.model.html.ContentTag object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //BootstrapSwitch
