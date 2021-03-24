/**
 */
package org.nasdanika.html.model.bootstrap.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.nasdanika.html.model.bootstrap.Accordion;
import org.nasdanika.html.model.bootstrap.ActionGroup;
import org.nasdanika.html.model.bootstrap.Alert;
import org.nasdanika.html.model.bootstrap.Appearance;
import org.nasdanika.html.model.bootstrap.Badge;
import org.nasdanika.html.model.bootstrap.BootstrapFactory;
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
import org.nasdanika.html.model.bootstrap.ContentActionGroupItem;
import org.nasdanika.html.model.bootstrap.ContentTag;
import org.nasdanika.html.model.bootstrap.Div;
import org.nasdanika.html.model.bootstrap.Dropdown;
import org.nasdanika.html.model.bootstrap.Form;
import org.nasdanika.html.model.bootstrap.FormGroup;
import org.nasdanika.html.model.bootstrap.InputGroup;
import org.nasdanika.html.model.bootstrap.LinkActionGroupItem;
import org.nasdanika.html.model.bootstrap.ListGroup;
import org.nasdanika.html.model.bootstrap.Modal;
import org.nasdanika.html.model.bootstrap.Navbar;
import org.nasdanika.html.model.bootstrap.Navs;
import org.nasdanika.html.model.bootstrap.Row;
import org.nasdanika.html.model.bootstrap.Spacing;
import org.nasdanika.html.model.bootstrap.Table;
import org.nasdanika.html.model.bootstrap.TableCell;
import org.nasdanika.html.model.bootstrap.TableHeader;
import org.nasdanika.html.model.bootstrap.TableRow;
import org.nasdanika.html.model.bootstrap.TableSection;
import org.nasdanika.html.model.bootstrap.Tag;
import org.nasdanika.html.model.bootstrap.Text;
import org.nasdanika.html.model.bootstrap.Tooltip;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BootstrapFactoryImpl extends EFactoryImpl implements BootstrapFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BootstrapFactory init() {
		try {
			BootstrapFactory theBootstrapFactory = (BootstrapFactory)EPackage.Registry.INSTANCE.getEFactory(BootstrapPackage.eNS_URI);
			if (theBootstrapFactory != null) {
				return theBootstrapFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BootstrapFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BootstrapFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case BootstrapPackage.BOOTSTRAP_PAGE: return createBootstrapPage();
			case BootstrapPackage.APPEARANCE: return createAppearance();
			case BootstrapPackage.BORDER: return createBorder();
			case BootstrapPackage.SPACING: return createSpacing();
			case BootstrapPackage.TEXT: return createText();
			case BootstrapPackage.FLOAT: return createFloat();
			case BootstrapPackage.TAG: return createTag();
			case BootstrapPackage.CONTENT_TAG: return createContentTag();
			case BootstrapPackage.DIV: return createDiv();
			case BootstrapPackage.LINK_ACTION_GROUP_ITEM: return createLinkActionGroupItem();
			case BootstrapPackage.CONTENT_ACTION_GROUP_ITEM: return createContentActionGroupItem();
			case BootstrapPackage.ACTION_GROUP: return createActionGroup();
			case BootstrapPackage.CONTAINER: return createContainer();
			case BootstrapPackage.ROW: return createRow();
			case BootstrapPackage.COLUMN_WIDTH: return createColumnWidth();
			case BootstrapPackage.COLUMN: return createColumn();
			case BootstrapPackage.TABLE_SECTION: return createTableSection();
			case BootstrapPackage.TABLE_HEADER: return createTableHeader();
			case BootstrapPackage.TABLE: return createTable();
			case BootstrapPackage.TABLE_ROW: return createTableRow();
			case BootstrapPackage.TABLE_CELL: return createTableCell();
			case BootstrapPackage.CARD: return createCard();
			case BootstrapPackage.ALERT: return createAlert();
			case BootstrapPackage.BADGE: return createBadge();
			case BootstrapPackage.BREADCRUMB: return createBreadcrumb();
			case BootstrapPackage.BUTTON: return createButton();
			case BootstrapPackage.BUTTON_GROUP: return createButtonGroup();
			case BootstrapPackage.BUTTON_TOOLBAR: return createButtonToolbar();
			case BootstrapPackage.DROPDOWN: return createDropdown();
			case BootstrapPackage.FORM: return createForm();
			case BootstrapPackage.LIST_GROUP: return createListGroup();
			case BootstrapPackage.NAVS: return createNavs();
			case BootstrapPackage.NAVBAR: return createNavbar();
			case BootstrapPackage.TOOLTIP: return createTooltip();
			case BootstrapPackage.INPUT_GROUP: return createInputGroup();
			case BootstrapPackage.FORM_GROUP: return createFormGroup();
			case BootstrapPackage.COLLAPSE: return createCollapse();
			case BootstrapPackage.MODAL: return createModal();
			case BootstrapPackage.ACCORDION: return createAccordion();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BootstrapPage createBootstrapPage() {
		BootstrapPageImpl bootstrapPage = new BootstrapPageImpl();
		return bootstrapPage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Appearance createAppearance() {
		AppearanceImpl appearance = new AppearanceImpl();
		return appearance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Border createBorder() {
		BorderImpl border = new BorderImpl();
		return border;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Spacing createSpacing() {
		SpacingImpl spacing = new SpacingImpl();
		return spacing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Text createText() {
		TextImpl text = new TextImpl();
		return text;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.nasdanika.html.model.bootstrap.Float createFloat() {
		FloatImpl float_ = new FloatImpl();
		return float_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Tag createTag() {
		TagImpl tag = new TagImpl();
		return tag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContentTag createContentTag() {
		ContentTagImpl contentTag = new ContentTagImpl();
		return contentTag;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Div createDiv() {
		DivImpl div = new DivImpl();
		return div;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LinkActionGroupItem createLinkActionGroupItem() {
		LinkActionGroupItemImpl linkActionGroupItem = new LinkActionGroupItemImpl();
		return linkActionGroupItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContentActionGroupItem createContentActionGroupItem() {
		ContentActionGroupItemImpl contentActionGroupItem = new ContentActionGroupItemImpl();
		return contentActionGroupItem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ActionGroup createActionGroup() {
		ActionGroupImpl actionGroup = new ActionGroupImpl();
		return actionGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.nasdanika.html.model.bootstrap.Container createContainer() {
		ContainerImpl container = new ContainerImpl();
		return container;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Row createRow() {
		RowImpl row = new RowImpl();
		return row;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ColumnWidth createColumnWidth() {
		ColumnWidthImpl columnWidth = new ColumnWidthImpl();
		return columnWidth;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Column createColumn() {
		ColumnImpl column = new ColumnImpl();
		return column;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TableSection createTableSection() {
		TableSectionImpl tableSection = new TableSectionImpl();
		return tableSection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TableHeader createTableHeader() {
		TableHeaderImpl tableHeader = new TableHeaderImpl();
		return tableHeader;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Table createTable() {
		TableImpl table = new TableImpl();
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TableRow createTableRow() {
		TableRowImpl tableRow = new TableRowImpl();
		return tableRow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TableCell createTableCell() {
		TableCellImpl tableCell = new TableCellImpl();
		return tableCell;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Card createCard() {
		CardImpl card = new CardImpl();
		return card;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Alert createAlert() {
		AlertImpl alert = new AlertImpl();
		return alert;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Badge createBadge() {
		BadgeImpl badge = new BadgeImpl();
		return badge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Breadcrumb createBreadcrumb() {
		BreadcrumbImpl breadcrumb = new BreadcrumbImpl();
		return breadcrumb;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Button createButton() {
		ButtonImpl button = new ButtonImpl();
		return button;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ButtonGroup createButtonGroup() {
		ButtonGroupImpl buttonGroup = new ButtonGroupImpl();
		return buttonGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ButtonToolbar createButtonToolbar() {
		ButtonToolbarImpl buttonToolbar = new ButtonToolbarImpl();
		return buttonToolbar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Dropdown createDropdown() {
		DropdownImpl dropdown = new DropdownImpl();
		return dropdown;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Form createForm() {
		FormImpl form = new FormImpl();
		return form;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ListGroup createListGroup() {
		ListGroupImpl listGroup = new ListGroupImpl();
		return listGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Navs createNavs() {
		NavsImpl navs = new NavsImpl();
		return navs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Navbar createNavbar() {
		NavbarImpl navbar = new NavbarImpl();
		return navbar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Tooltip createTooltip() {
		TooltipImpl tooltip = new TooltipImpl();
		return tooltip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InputGroup createInputGroup() {
		InputGroupImpl inputGroup = new InputGroupImpl();
		return inputGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FormGroup createFormGroup() {
		FormGroupImpl formGroup = new FormGroupImpl();
		return formGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collapse createCollapse() {
		CollapseImpl collapse = new CollapseImpl();
		return collapse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Modal createModal() {
		ModalImpl modal = new ModalImpl();
		return modal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Accordion createAccordion() {
		AccordionImpl accordion = new AccordionImpl();
		return accordion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BootstrapPackage getBootstrapPackage() {
		return (BootstrapPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BootstrapPackage getPackage() {
		return BootstrapPackage.eINSTANCE;
	}

} //BootstrapFactoryImpl
