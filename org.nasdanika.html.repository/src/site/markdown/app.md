# Application

* Actions, 
* Application classes - HTML, Bootstrap, JsTree, ... 

## Bootstrap application

```
try (Application app = new BootstrapContainerApplication(Theme.Litera) {
	
	{
		container.border(Color.DANGER);
		header.border(Color.DANGER).background(Color.PRIMARY);
		navigation.border(Color.DANGER);
		leftPanel.border(Color.DANGER).widthAuto();
		footer.border(Color.DANGER);
		content.border(Color.DANGER);
	}
	
}) {
	Tag treeContainer = app.getHTMLPage().getFactory().div();
	app.header("header").navigation("navigation").leftPanel(treeContainer).content("content").footer("footer");
	
	JsTreeFactory jsTreeFactory = JsTreeFactory.INSTANCE;
	jsTreeFactory.cdn(app.getHTMLPage());
	
	FontAwesomeFactory.INSTANCE.cdn(app.getHTMLPage());
			
	JsTreeNode rootNode = jsTreeFactory.jsTreeNode();
	rootNode.icon("far fa-user");
	rootNode.text("User");
	
	app.getHTMLPage().body(jsTreeFactory.bind(treeContainer, jsTreeFactory.buildAjaxJsTree("jstree.json", "context-menu.json")));		
	
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
}
```

<iframe src="test-dumps/app/bootstrap/index.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = this.contentWindow.document.body.scrollHeight + 'px'"></iframe>
