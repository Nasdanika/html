With ``ecore`` module you can generate documentation for Ecore models.

To use add [ecore](https://mvnrepository.com/artifact/org.nasdanika.html/ecore) dependency to ``pom.xml``, e.g.

```xml
<dependency>
  <groupId>org.nasdanika.html</groupId>
  <artifactId>ecore</artifactId>
  <version>2022.1.1</version>
</dependency>
```

Then write generation logic following the below examples:

* [TestNasdanikaDocEngineeringGen.java](https://github.com/Nasdanika/nasdanika.github.io/blob/main/src/test/java/org/nasdanika/docs/engineering/TestNasdanikaDocEngineeringGen.java) - generator of this web site.
* [TestEcoreDoc.java](https://github.com/Nasdanika/html/blob/master/ecore/src/test/java/org/nasdanika/html/ecore/tests/TestEcoreDoc.java) - a test generating documentation for a few models.

## Generation steps

### Generate action model

If you are generating documentation for cross-referencing models, you may need to copy the models so their physical relative location on disk matches their relative logical location in Eclipse (project name). 
For example, [Ncore](https://docs.nasdanika.org/modules/core/modules/ncore/index.html) physical location relative to the Git root directory is ``core/ncore``, but its logical (project) name in Eclipse is ``org.nasdanika.ncore``.
Therefore, in order for the [flow](https://docs.nasdanika.org/modules/core/modules/ncore/index.html) model to reference Ncore classes the Ncore model is copied from ``core/ncore`` to ``org.nasdanika.ncore`` in a Maven target directory. 
The flow model is copied in a similar way.

### Generate resource model

Once you have generated action models, mount them to a root action model (defined in ``actions.yml`` in the examples above) and generate a resource model using a page template - defined in ``page-template.yml`` in the examples.

#### Root action model

```yml
app-action:
  icon: https://docs.nasdanika.org/images/nasdanika-logo.png
  uri: nasdanika:test/action/s1/s2
  text: Nasdanika
  location: https://www.nasdanika.org 
  tooltip: Root action
  children:
    - app-action:
        icon: fa fa-book
        text: Documentation
        id: doc
        location: ${base-uri}index.html 
        tooltip: Documentation
        content:
          content-text: TODO  
        children:
          - app-action-reference: "../../../../../../../target/model-doc/actions/ncore.genmodel.xml#/"
          - app-action-reference: "../../../../../../../target/model-doc/actions/exec.genmodel.xml#/"
          - app-action-reference: "../../../../../../../target/model-doc/actions/flow.genmodel.xml#/"
```

#### Page template

```yml
bootstrap-page:
  cdn: true
  theme: Cerulean
  name: Application Page Template
  language: EN
  stylesheets: 
    - https://cdn.jsdelivr.net/gh/Nasdanika/html@master/model/app.gen/web-resources/css/app.css 
    - https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css
    - https://cdn.jsdelivr.net/npm/jstree@3.3.11/dist/themes/default/style.min.css
    - https://cdn.jsdelivr.net/npm/github-markdown-css@5.1.0/github-markdown.min.css
    - https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.3.1/build/styles/default.min.css
    - https://cdn.jsdelivr.net/npm/bootstrap-vue@2.21.2/dist/bootstrap-vue.css
  scripts:
    - https://cdn.jsdelivr.net/gh/Nasdanika/html@master/model/app.gen/web-resources/js/common.js 
    - https://cdn.jsdelivr.net/gh/Nasdanika/html@master/model/app.gen/web-resources/js/dark-head.js 
    - https://cdn.jsdelivr.net/npm/jstree@3.3.11/dist/jstree.min.js
    - https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.3.1/build/highlight.min.js
    - https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.min.js
    - https://cdn.jsdelivr.net/npm/bootstrap-vue@2.21.2/dist/bootstrap-vue.min.js
    - https://cdn.jsdelivr.net/gh/Nasdanika/html@master/model/app.gen/web-resources/js/components/table.js
  body:
    app-page:
      fluid: true
      navigation-panel:
        style: COLLAPSIBLE_CARDS
        collapsible: true
```        

## Examples

* [Ncore](https://docs.nasdanika.org/modules/core/modules/ncore/index.html)
* [Diagram](https://docs.nasdanika.org/modules/core/modules/diagram/modules/model/index.html)
* [Flow](https://docs.nasdanika.org/modules/core/modules/flow/index.html)
* [Exec](https://docs.nasdanika.org/modules/core/modules/exec/modules/model/index.html)
* [HTML](https://docs.nasdanika.org/modules/html/modules/models/modules/html/modules/model/index.html)
* [Bootstrap](https://docs.nasdanika.org/modules/html/modules/models/modules/bootstrap/modules/model/index.html)
* [Application](https://docs.nasdanika.org/modules/html/modules/models/modules/app/modules/model/index.html)
* [Engineering](https://docs.nasdanika.org/modules/engineering/modules/model/index.html)
 


          