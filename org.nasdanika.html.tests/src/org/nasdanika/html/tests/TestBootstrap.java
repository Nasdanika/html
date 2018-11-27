package org.nasdanika.html.tests;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Test;
import org.nasdanika.bank.Bank;
import org.nasdanika.bank.BankPackage;
import org.nasdanika.html.Form;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Input;
import org.nasdanika.html.InputType;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Executable;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breadcrumbs;
import org.nasdanika.html.bootstrap.Button;
import org.nasdanika.html.bootstrap.ButtonGroup;
import org.nasdanika.html.bootstrap.ButtonToolbar;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Direction;
import org.nasdanika.html.bootstrap.Dropdown;
import org.nasdanika.html.bootstrap.InputGroup;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.Placement;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.bootstrap.Table.TableBody;
import org.nasdanika.html.bootstrap.Table.TableHeader;
import org.nasdanika.html.bootstrap.Text.Alignment;
import org.nasdanika.html.bootstrap.Text.Weight;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;


public class TestBootstrap extends HTMLTestBase {
	
	//TODO - collect and output to file for checks and demos.
	
//	@Before
//	public void setUp() {
//		System.out.println("Before bootstrap tests in "+(new File(".")).getAbsolutePath());
//	}
	
	
//	@Before
//	public void loadModel() {
//		
//	}
	
	@Test
	public void testAlert() throws Exception {
		writeThemedPage("bootstrap/alert.html", "Bootstrap alert", BootstrapFactory.INSTANCE.alert(Color.INFO, "Alert"));
	}
	
	@Test
	public void testBadge() throws Exception {
		writeThemedPage(
				"bootstrap/badge.html", 
				"Bootstrap badge", 
				BootstrapFactory.INSTANCE.badge(true, Color.INFO, "Badge"),
				BootstrapFactory.INSTANCE.badgeLink("#", false, Color.WARNING, "Badge link"));
	}
	
	
	
	@Test
	public void testStuff() throws Exception {
		BootstrapFactory factory = BootstrapFactory.INSTANCE;
		HTMLFactory htmlFactory = factory.getHTMLFactory();
		
		Fragment content = htmlFactory.fragment();
		
		content.content(TagName.h2.create("Breadcrumbs"));
		
		Breadcrumbs breadcrumbs = factory.breadcrums();
		breadcrumbs.item("#", "First");
		breadcrumbs.item(null, "Last");
		content.content(breadcrumbs);

		
		content.content(TagName.h2.create("Button"));
		
		org.nasdanika.html.Button hButton = htmlFactory.button("Button");	
		Button<org.nasdanika.html.Button> button = factory.button(hButton, Color.PRIMARY, false);
		factory.tooltip(button, "I am a <I>tooltip</I>." , true, Placement.BOTTOM);
		content.content(button);				
		
		content.content(factory.initTooltipScript());
		
		
		content.content(TagName.h2.create("Button group"));
		
		ButtonGroup buttonGroup = factory.buttonGroup(false);
		buttonGroup.add(button);
		buttonGroup.add(button);
		content.content(buttonGroup);

		
		content.content(TagName.h2.create("Button toolbar"));
		
		ButtonToolbar toolbar = factory.buttonToolbar();
		toolbar.add(buttonGroup);
		content.content(toolbar);

		
		content.content(TagName.h2.create("Dropdown"));
		
		Dropdown dropdown = factory.dropdown(button, true, Direction.DOWN);
		dropdown.item("#", false, false, "Item 1");
		dropdown.header("Header");
		dropdown.item("#", true, false, "Item 1");
		dropdown.divider();
		dropdown.item("#", false, true, "Item 1");
		content.content(dropdown);

		
		content.content(TagName.h2.create("Input group"));
		
		InputGroup inputGroup = factory.inputGroup();
		Input input = htmlFactory.input(InputType.text);
		inputGroup.input(input);
		inputGroup.prepend("@");
		inputGroup.append("Zorro").large();
		content.content(inputGroup);
		
		content.content(TagName.h2.create("Table"));
		
		Table table = factory.table().striped();
		table.toHTMLElement().caption("My table");
		TableHeader header = table.header();
		header.headerRow("A", "B", "C");
		TableBody body = table.body();
		body.row("One", "Two", "Three");		
		content.content(table);
		
		content.content(TagName.h2.create("Form"));
		
		Form form = htmlFactory.form();
		
		form.content(factory.formGroup("Email address", htmlFactory.input(InputType.email).value("email@example.com"), "We'll never share").large().plainText());
		form.content(factory.formGroup("Password", htmlFactory.input(InputType.password).disabled(), null).small());
		form.content(factory.formGroup("Check me out", htmlFactory.input(InputType.checkbox), null).invalid("Oh, no"));

		form.content(factory.formGroup("1", htmlFactory.input(InputType.radio), null).inline());
		form.content(factory.formGroup("2", htmlFactory.input(InputType.radio), null).inline());
		form.content(factory.formGroup("3", htmlFactory.input(InputType.radio), null).inline());

		form.content(factory.formGroup("City", htmlFactory.input(InputType.text), "City").valid());
		form.content(factory.formGroup("State", htmlFactory.input(InputType.text), "State").invalid("No such state"));
		
		content.content(form);
		
		content.content(TagName.h2.create("Navs"));
		
		Navs navs = factory.tabs();
		navs.item("First", "First content");
		navs.item("Second", "Second content");
		navs.item("Third", "Third content");
		navs.item("Fourth", "Fourth content");
		
		content.content(navs);
		
		content.content(TagName.h2.create("Containers"));
		
		Container container = factory.container();
		container.row().col(content);
		org.nasdanika.html.bootstrap.Container.Row row = container.row();
		row.col("Col 1").border(Color.DARK).background(Color.WARNING).text().color(Color.PRIMARY);
		row.col("Col 2").border(Color.PRIMARY).text().weight(Weight.BOLD).alignment(Alignment.CENTER);
		row.col("Col 3").border(Color.WARNING, Placement.RIGHT).background(Color.SECONDARY).text().monospace();		
			
		// Bootstrap page
		HTMLPage bootstrapPage = factory.bootstrapCdnHTMLPage();//Theme.Cerulean);
		bootstrapPage.title("Bootstrap demo");
		bootstrapPage.body(container);
		
		System.out.println(bootstrapPage);
		
	}
	
	// TODO - move to a different class
	@Test
	public void testLoadBankModel() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(BankPackage.eNS_URI, BankPackage.eINSTANCE);
		URI uri = URI.createPlatformPluginURI("org.nasdanika.bank/Nasdanika.bank", false);
		Resource resource = resourceSet.getResource(uri, true);
		Bank bank = (Bank) resource.getContents().iterator().next();
		System.out.println(bank);
	}

	// TODO - move to a different class
	@Test
	public void testAppModel() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		class ExecutableAdapter extends AdapterImpl implements Executable {
			
			@Override
			public Object execute() {
				return "Executing "+getTarget()+" "+this;
			}
			
			@Override
			public boolean isAdapterForType(Object type) {
				return Executable.class == type;
			}
			
		}
		AdapterFactory af = new AdapterFactoryImpl() {
			
			@Override
			public boolean isFactoryForType(Object type) {
				return Executable.class == type;
			}
			
			@Override
			protected Adapter createAdapter(Notifier target, Object type) {
				if (Executable.class == type) {
					return new ExecutableAdapter();
				}
				return null;
			}
		};
		resourceSet.getAdapterFactories().add(af);
		Resource resource = resourceSet.createResource(URI.createURI("mem://test.xml")); // Some random URL.
		Action action = AppFactory.eINSTANCE.createAction();
		resource.getContents().add(action);
		System.out.println(action.execute());
		System.out.println(action.execute());
		
		System.out.println(action.getChildren());
	}
	
}
