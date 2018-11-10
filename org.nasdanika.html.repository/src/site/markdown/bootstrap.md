# Bootstrap

[Java API](apidocs/bootstrap/index.html) to build [Bootstrap](https://getbootstrap.com/) HTML.

The entry point to the API is [BootstrapFactory](apidocs/bootstrap/index.html?org/nasdanika/html/bootstrap/BootstrapFactory.html).
It can be obtained as ``BootstrapFactory factory = BootstrapFactory.INSTANCE;``.  

## Basic example

```
BootstrapFactory factory = BootstrapFactory.INSTANCE;
System.out.println(factory.alert(Color.INFO, "Alert"));
System.out.println(factory.badge(true, Color.INFO, "Badge"));
System.out.println(factory.badgeLink("#", false, Color.WARNING, "Badge link"));
```

## Breadcrumbs

```		
Breadcrumbs breadcrumbs = factory.breadcrums();
breadcrumbs.item("#", "First");
breadcrumbs.item(null, "Last");
System.out.println(breadcrumbs);
```

## Button

```
HTMLFactory htmlFactory = factory.getHTMLFactory();		
org.nasdanika.html.Button hButton = htmlFactory.button("Button");	
Button<org.nasdanika.html.Button> button = factory.button(hButton, Color.PRIMARY, false);
System.out.println(button);
```

## Button group

```		
ButtonGroup buttonGroup = factory.buttonGroup(false);
buttonGroup.add(button);
buttonGroup.add(button);
System.out.println(buttonGroup);
```

## Button toolbar

```		
ButtonToolbar toolbar = factory.buttonToolbar();
toolbar.add(buttonGroup);
System.out.println(toolbar);
```
	
## Dropdown

```		
Dropdown dropdown = factory.dropdown(button, true, Direction.DOWN);
dropdown.item("#", false, false, "Item 1");
dropdown.header("Header");
dropdown.item("#", true, false, "Item 1");
dropdown.divider();
dropdown.item("#", false, true, "Item 1");
System.out.println(dropdown);
```

## Input group
		
```		
InputGroup inputGroup = factory.inputGroup();
Input input = htmlFactory.input(InputType.text);
inputGroup.input(input);
inputGroup.prepend("@");
inputGroup.append("Something").large();
System.out.println(inputGroup);
```
	
## Table

``` 		
Table table = factory.table().striped();
table.toHTMLElement().caption("My table");
TableHeader header = table.header();
header.headerRow("A", "B", "C");
TableBody body = table.body();
body.row("One", "Two", "Three");		
System.out.println(table);
```

## Form		

```		
Form form = htmlFactory.form();
		
form.content(factory.formGroup("Email address", htmlFactory.input(InputType.email).value("email@example.com"), "We'll never share").large().plainText());
form.content(factory.formGroup("Password", htmlFactory.input(InputType.password).disabled(), null).small());
form.content(factory.formGroup("Check me out", htmlFactory.input(InputType.checkbox), null).invalid("Oh, no"));

form.content(factory.formGroup("1", htmlFactory.input(InputType.radio), null).inline());
form.content(factory.formGroup("2", htmlFactory.input(InputType.radio), null).inline());
form.content(factory.formGroup("3", htmlFactory.input(InputType.radio), null).inline());

form.content(factory.formGroup("City", htmlFactory.input(InputType.text), "City").valid());
form.content(factory.formGroup("State", htmlFactory.input(InputType.text), "State").invalid("No such state"));
```