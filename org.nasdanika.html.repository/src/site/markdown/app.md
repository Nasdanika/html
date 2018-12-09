# Application

``org.nasdanika.html.app`` bundle/library provides a level of abstraction on top of [HTML](html.html), [Bootstrap](bootstrap.html), [JsTree](jstree.html), [Font Awesome](fontawesome.html), and [KnockoutJS](knockout.html) bundles.
This page outlines core concepts and shows usage examples. See [Java API](apidocs/org.nasdanika.html.app/apidocs/index.html) for additional details.

## Use in Maven projects

Add repository and dependency as shown below:

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	...	
	<repositories>
		...
		<repository>
			<id>nasdanika-html-snapshots</id>
			<name>nasdanika-html-snapshots</name>
			<url>https://www.nasdanika.org/products/html/2.0.0-SNAPSHOT/maven-repository</url>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
			<layout>default</layout>
		</repository>
		...
	</repositories>	
	...		
	<dependencies>
		...		
		<dependency>
			<groupId>org.nasdanika.html</groupId>
			<artifactId>org.nasdanika.html.app</artifactId>
			<version>2.0.0-SNAPSHOT</version>
		</dependency>
		...
	</dependencies>
	...
</project>
```

## Application

[Application](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/Application.html) interface is an abstraction of a web application consisting of the following parts:
* Header
* Navigation bar
* Navigation panel
* Content panel
* Footer

There are two implementations of this interface:

* [BootstrapContainerApplication](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/impl/BootstrapContainerApplication.html).
* [HTMLTableApplication](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/impl/HTMLTableApplication.html).

### Bootstrap application

The below code snippet shows how to build a bootstrap container application with Ajax jsTree navigation panel.

```
Application app = new BootstrapContainerApplication(Theme.Litera, false) {
	
	{
		container.border(Color.DANGER);
		header.border(Color.DANGER).background(Color.PRIMARY);
		navigationBar.border(Color.DANGER);
		navigationPanel.border(Color.DANGER).widthAuto();
		footer.border(Color.DANGER);
		contentPanel.border(Color.DANGER).toHTMLElement().style("min-height", "25em");
	}
	
};

Tag treeContainer = app.getHTMLPage().getFactory().div();
HTMLFactory htmlFactory = HTMLFactory.INSTANCE;
app
	.header("Header")
	.navigationBar("Navigation bar")
	.navigationPanel(treeContainer)
	.contentPanel(htmlFactory.overlay("Content overlay"), "Content")
	.footer("Footer");

JsTreeFactory jsTreeFactory = JsTreeFactory.INSTANCE;
jsTreeFactory.cdn(app.getHTMLPage());

FontAwesomeFactory.INSTANCE.cdn(app.getHTMLPage());
		
JsTreeNode rootNode = jsTreeFactory.jsTreeNode();
rootNode.icon("far fa-user");
rootNode.text("User");

app.getHTMLPage().body(jsTreeFactory.bind(treeContainer, jsTreeFactory.buildAjaxJsTree("'jstree.json'", "'context-menu.json'")));		

writeFile("app/bootstrap/index.html", app.toString());

// JsTree
JSONArray jsTreeNodes = new JSONArray();

JsTreeNode childNode = jsTreeFactory.jsTreeNode();
childNode.icon("far fa-user");
childNode.text("Child");
childNode.hasChildren();
childNode.id(jsTreeFactory.getHTMLFactory().nextId());
jsTreeNodes.put(childNode.toJSON());
writeFile("app/bootstrap/jstree.json", jsTreeNodes.toString());

// JsTree context menu
JsTreeContextMenuItem item = jsTreeFactory.jsTreeContextMenuItem();
item.label("Do it!");
item.icon("far fa-user");
item.action("window.location.href='http://www.nasdanika.org'; console.log('hey');");

JSONObject menu = new JSONObject();
menu.put("do-it", item.toJSON());
writeFile("app/bootstrap/context-menu.json", menu.toString());
```

The application: 

<iframe src="test-dumps/app/bootstrap/index.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = this.contentWindow.document.body.scrollHeight + 'px'"></iframe>

## Label

## Action

### Action activator

## View generator

## View part

## Application builder

ViewPartApplicationBuilder 
ActionApplicationBuilder, view parts...

