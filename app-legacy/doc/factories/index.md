${javadoc/org.nasdanika.html.app.factories} contains classes for loading application elements from YAML specifications - factories and two loaders.

### Loaders

#### AppLoader

${javadoc/org.nasdanika.html.app.factories.AppLoader} loads application elements:

* [action](action.html) - action specification.
* [action-reference](action-reference.html) - loads action specification from a URL resolved relative to the current YAML specification resource URL. 
* [application](application.html) - application specification.
* [category](category.html) - categories allow to group actions. 
* [category-reference](category-reference.html) - loads action specification from a URL resolved relative to the current YAML specification resource URL. 
* [label](label.html) - base for category and action. Typically not used on its own.

#### ComposedLoader

${javadoc/org.nasdanika.html.app.factories.ComposedLoader} delegates loading calls to other loader using prefixes:

* ``exec-`` - removes the prefix and calls [Exec loader](${base-uri}../../core/exec/general/loader.html). E.g. ``exec-markdown`` calls [markdown](${base-uri}../../core/exec/content/markdown.html).
It allows to "wire" general purpose generators into the application and actions, primarily content components.
For example, load action content from markdown or make an [HTTP Call](${base-uri}../../core/exec/content/http-call.html) to produce action content.
* ``html-`` - removes the prefix and calls [HTML loader](${base-uri}../html/factories/index.html).
* ``bootstrap-`` - removes the prefix and calls [Bootstrap loader](${base-uri}../bootstrap/factories/index.html). 
* ``app-`` - removes the prefix and calls App loader described in the previous section. 

### Generating a static site

This section explains how to generate a static web site from an application hierarchy and an application template.

#### Application

Application can be loaded from one of templates provided in ``org.nasdanika.html.app`` bundle ``org.nasdanika.html.app.templates.*`` packages or from another location.
This section provides code of the [Cerulean dark](https://nasdanika.org/html-app-demo/cerulean/index.html) template: 

##### YAML spec

```yaml
app-application:
   page:
      theme: Cerulean
      head:
         exec-interpolator:
            exec-resource: ../dark-head.html   
   appearance:
      margin: 
         size: 1
         side: top
      border: dark         
```

##### Common page head

``dark-head.html`` referenced in the above YAML specification.

```html
<title>${page-title}</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/line-awesome/1.3.0/line-awesome/css/line-awesome.min.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.11/themes/default/style.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.11/jstree.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/github-markdown-css/4.0.0/github-markdown.css"/>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.5.0/styles/default.min.css"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.5.0/highlight.min.js"></script>
<script>hljs.initHighlightingOnLoad();</script>

<script>
	$(document).ready( function() {
	    $('.nsd-app-header').addClass('bg-dark');
	    $('.nsd-app-header .nav-link').addClass('text-light');
	    $('.nsd-root-action').addClass(['display-4', 'text-light']);
	
	    $('.nsd-app-navbar').addClass(['bg-light', 'p-0']);
	
	    $('.nsd-app-navigation-panel').addClass(['col-auto', 'border-right', 'border-light', 'pb-1']); 
	    $('.nsd-app-content-panel').addClass(['col', 'p-1']);
	    $('.nsd-app-footer').addClass(['bg-dark', 'text-light', 'text-center']);
	    $('.nsd-app-footer .nsd-footer-action').addClass(['font-italic', 'text-light']);
	});
</script>

<style>
	.nsd-label-icon {
	    height:0.8em;
	    margin-bottom:0.2em;
	    margin-right:0.1em;
	}
	.nsd-app-content-row {
	    min-height: 25rem;
	}
	.nsd-root-action {
	    text-decoration: none;
	}
	@media print {
	  div.nsd-app-navigation-panel {
	    display: none;
	  }
	}
</style>
```

#### Action hierarchy

This section provides a fragment of the root resource of an action hierarchy specification.
You can find [the complete action hierarchy](https://github.com/Nasdanika/html/blob/develop/app.tests/src/org/nasdanika/html/app/tests/app/root.yml) on GitHub. 

```yml
# Demonstrates an action application and different application roles.
# See https://nasdanika.org/builds/develop/doc/reference/knowledge-base/html/app/factories/index.html for configuration reference 
app-action:
   icon: fa fa-cog
   text: My Application
   tooltip: The root action of the application
   href: index.html
   content: ${view-part}
   children:
   # Root action children. The first navigation child is the principal action. 
      - app-action-reference: principal.yml
   # Subsequent navigation children of the root action are displayed in the top right corner. 
      - app-action:
         text: Root navigation child 1
         # No href - dynamically generated random page URL.
         content: I'm a the root navigation child 1
      - app-action:
         text: Root navigation child 2
         # No href - dynamically generated random page URL.
         content: I'm a the root navigation child 2
      - app-action:
         text: Root navigation drop-down
         # Drop-down action shall not have content. If it does children are ignored.
         children:
           - app-category:
               text: My category
               icon: fas fa-anchor
               path: root-navigation-my-category/
               actions:
                  - app-action:
                     text: Item 1
                     href: item-1.html
                     content:                  
                        exec-markdown: 
                           exec-interpolator: Hello, ``${color}``!
                  - app-action:
                     text: Item 2
                     href: item-2.html
                     content:                  
                        exec-styled-markdown: |+2 
                          An example of inline markdown styled with GitHub CSS.
                          
                          ### List 
                          
                          * Bullet 1
                          * Bullet 2 
           - app-category:
               # No text - anonymous category - displayed as a separator
               path: root-navigation-anonymous-category/
               actions:
                  - app-action:
                     text: Item 3
                     href: item-3.html
                     content: Item 3 content.                 
                  - app-action:
                     text: Item 4
                     href: item-4.html
                     content: Item 4 content.
                     icon: fa fa-book 
      # For inline actions their content is displayed instead of text and action, where applicable - currently in navigation bars.
      - app-action:
         inline: true
         content: <form><input type="text" placeholder="Search"></input></form>                 
   # Context children of the root action are displayed in the footer                 
      - app-action:
         text: Footer 1
         href: footer-1.html
         content: Footer info 1
         role: context
      - app-action:
         text: Footer 2
         href: footer-2.html
         content: footer info 2
         role: context               
```

#### Java code

```java
public void generateBootstrapActionApplication() throws Exception {
	ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
	MutableContext context = Context.singleton("color", "success").fork(); // For token replacement
	ViewPart viewPart = (v,p) -> "Dynamic content obtained from the context"; // Demo of dynamic action content
	context.put("view-part", viewPart);
	String base = "tmp://base/";
	context.put(Context.BASE_URI_PROPERTY, base);
	
	ComposedLoader loader = new ComposedLoader();
	Object actionFactory = loader.loadYaml(this.getClass().getResource("app/root.yml"), monitor);
	Action action = Util.callSupplier(Util.<Action>asSupplierFactory(actionFactory).create(context), monitor);

	// In this example we iterate over all themes. Remove the cycle if you need to generate only for one theme. 
	for (Theme theme: Theme.values()) {
		writeAction(context, base, theme, action, action.getChildren().get(0), action, monitor);
	}
}

private void writeAction(Context context, String base, Theme theme, Action root, Action principal, Action active, ProgressMonitor monitor) throws Exception {
	MutableContext actionContext = context.fork();		
	// Generating only for actions with content and URL activator.
	if (!active.isEmpty() && active.getActivator() instanceof NavigationActionActivator) {
		NavigationActionActivator activator = (NavigationActionActivator) active.getActivator();
		String actionURI = activator.getUrl(null);
		actionContext.put(Context.BASE_URI_PROPERTY, actionURI);
		actionContext.put("page-title", active.getText());
		ApplicationBuilder builder = new ActionApplicationBuilder(actionContext, root, principal, active);
		
		// Using a built-in template.
		String themePath = theme == Theme.Default ? "bootstrap" : theme.name().toLowerCase();
		String resourceName = "org/nasdanika/html/app/templates/" + themePath + "/" + (theme == Theme.Slate ? "primary" : "dark") + ".yml";
		Application app = Util.callSupplier(((BootstrapContainerApplicationSupplierFactory) composedLoader.loadYaml(getClass().getClassLoader().getResource(resourceName), monitor)).create(actionContext), monitor);
		
		builder.build(app, monitor);

		String url = ((NavigationActionActivator) active.getActivator()).getUrl(null);
		if (url != null && url.startsWith(base)) {			
			String path = "app/" + themePath + "/" + url.substring(base.length());
			writeFile(path, app.toString());
		}			
	}		
	for (Action child: active.getChildren()) {
		writeAction(actionContext, base, theme, root, principal, child, monitor);
	}
}
	
/**
 * Writes a text file.
 * @param path
 * @param content
 */
private void writeFile(String path, String content) throws IOException {
	File target = new File(("target/test-dumps/"+path).replace("/", File.separator));
	File parent = target.getParentFile();
	if (!parent.exists()) {
		if (!parent.mkdirs()) {
			Assert.fail("Cannot create "+parent.getAbsolutePath());
		}
	}
	
	System.out.println("Writing to "+target.getAbsolutePath());
	try (Writer writer = new FileWriter(target)) {
		writer.write(content);
	}		
	
}
```

### Dynamic web application

[ExecController](https://github.com/Nasdanika/spring-exec/blob/main/src/main/java/org/nasdanika/spring/exec/controllers/ExecController.java) of [Spring Exec](https://github.com/Nasdanika/spring-exec) project on GitHub
demonstrates how to use actions, application, and loaders in a dynamic web application.


### Application templates

Application templates are available as classloader resources under ``org/nasdanika/html/app/templates/``. 
E.g. ``org/nasdanika/html/app/templates/cerulean/dark.yml`` classloader resource contains Cerulean dark template.
Check [sources](https://github.com/Nasdanika/html/tree/develop/app/src/org/nasdanika/html/app/templates) or bundle/jar content for template availability.
