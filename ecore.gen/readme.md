With ``ecore`` module you can generate documentation for Ecore models.

## Features

* Generated diagrams with tooltips and links from the shapes to model elements pages: 
    * [Package diagrams](../../../core/modules/flow/package-summary-diagram.html) - shows package elements and their relationships.
    * [Class context diagrams](../../../core/modules/flow/FlowElement-diagram.html) - shows a class, related elements and relationships. Configurable relationship depth, 1 by default.
    * [Class inheritance diagrams](../../../core/modules/flow/FlowElement-all-supertypes.html) - shows a class and all of its supertypes. 
* Rich documentation using [Markdown](../../../core/modules/exec/modules/model/content/Markdown.html) with support of embedded images and diagrams.
* Javadoc integration.
* [Load specification](../../../core/modules/flow/Activity-load-specification.html) for models which are loaded with [Nasdanika EMF Persistence](../../../core/modules/emf/index.html#persistence).
* Model documentation cross-referencing.
* [Site-scoped integrated search](../../../../search.html), including searching in diagrams[^1].
* Detection of broken links, including broken links in diagrams.

Traceability from documentation to model sources stored in a version control system, e.g. Git, can be added if needed - remote location and commit.

[^1]: This and detection of broken links are features of [Nasdanika Application Framework](../models/modules/app/index.html), which is used to generate HTML sites from action models generated from Ecore models by this module.

## Examples

* [Ncore](https://docs.nasdanika.org/modules/core/modules/ncore/index.html)
* [Diagram](https://docs.nasdanika.org/modules/core/modules/diagram/modules/model/index.html)
* [Exec](https://docs.nasdanika.org/modules/core/modules/exec/modules/model/index.html)
* [HTML](https://docs.nasdanika.org/modules/html/modules/models/modules/html/modules/model/index.html)
* [Bootstrap](https://docs.nasdanika.org/modules/html/modules/models/modules/bootstrap/modules/model/index.html)
* [Application](https://docs.nasdanika.org/modules/html/modules/models/modules/app/modules/model/index.html)
* [Engineering](https://docs.nasdanika.org/modules/engineering/modules/model/index.html)
    
## Documenting models

Models are documented in [Markdown](../../../core/modules/exec/modules/model/content/Markdown.html) using model annotations. 

There are the following ways to document the model:

* ``http://www.eclipse.org/emf/2002/GenModel`` (GenModel) annotation, ``documentation`` details. With this method you have to set "Suppress GenModel Annotations" to ``false`` in the GenModel editor (``true`` by default).
* ``urn:org.nasdanika`` annotation:
    * ``documentation-reference`` - location of a resource (file, URL) containing markdown documentation for an element. The location is resolved relative to the model (``.ecore`` file) location.
    * ``load-doc`` - Load documentation if different from the general documentation. 
    * ``load-doc-reference`` - location of a resource (file, URL) containing markdown load documentation for an element. The location is resolved relative to the model (``.ecore`` file) location.

Resource references in Markdown fenced blocks (images and diagrams) are resolved relative to the ``.ecore`` file location for documentation embedded into the model (GenModel and ``documentation-reference``)
and relative to documentation resources for referenced documentation.
This is achieved by adding 

It works AS-IS when all generation steps are performed on the same machine. 
If you decide to separate generation steps, e.g. generate action models and publish them to a binary repository and generate Web sites from them later, then you will need to
package image and diagram resources together with the action model and modify the markers.
One simple way to do it is to package the action model together with the ``.ecore`` model - in this case you'll just need to relativize marker locations.

### Referencing EClassifiers

You can reference EClassifiers available in the model resource set using ``${{classifier/<classifier name>[@<package namespace URI>]}}`` token.

Examples:

* ``${{classifier/Activity}}`` - ``Activity`` classifier in the same EPackage. Classifiers in the same EPackage can also be addressed by adding ``.html`` to the classifier name, e.g. ``[Activity](Activity.html)``.
* ``${{classifier/Activity@urn:org.nasdanika.flow}}`` - ``Activity`` classifier in EPackage with ``urn:org.nasdanika.flow`` namespace URI.

During generation you will need to pass EPackage path computer to ``org.nasdanika.html.ecore.EcoreActionSupplierAdapterFactory`` constructor.
See [TestNasdanikaDocEngineeringGen.generateEcoreActionModel()](https://github.com/Nasdanika/nasdanika.github.io/blob/main/src/test/java/org/nasdanika/docs/engineering/TestNasdanikaDocEngineeringGen.java#L199) for an example.

### Javadoc

To link EClassifier instance class names to their javadocs you'll need to pass a Javadoc resolver to ``EcoreActionSupplierAdapterFactory`` constructor.
See ``TestNasdanikaDocEngineeringGen.generateEcoreActionModel()`` above for an example.  

To create links to Javadoc in makrdown you may use ``${{javadoc/<fully qualified class name>}}`` replacement tokens.

For example, ``${{javadoc/org.nasdanika.common.Context}}`` would be replaced with ${javadoc/org.nasdanika.common.Context}.

You will need to add a Javadoc resolver to the generation context as exemplified [here](https://github.com/Nasdanika/nasdanika.github.io/blob/main/src/test/java/org/nasdanika/docs/engineering/TestNasdanikaDocEngineeringGen.java#L735).

## Generation steps

To use add [ecore](https://mvnrepository.com/artifact/org.nasdanika.html/ecore) dependency to ``pom.xml``, e.g.

```xml
<dependency>
  <groupId>org.nasdanika.html</groupId>
  <artifactId>ecore</artifactId>
  <version>2022.4.0</version>
</dependency>
```

Then write generation logic following the below examples:

* [TestNasdanikaDocEngineeringGen.java](https://github.com/Nasdanika/nasdanika.github.io/blob/main/src/test/java/org/nasdanika/docs/engineering/TestNasdanikaDocEngineeringGen.java) - generator of this web site.
* [TestEcoreDoc.java](https://github.com/Nasdanika/html/blob/master/ecore/src/test/java/org/nasdanika/html/ecore/tests/TestEcoreDoc.java) - a test generating documentation for a few models.

### Generate action model

If you are generating documentation for cross-referencing models, you may need to copy the models so their physical relative location on disk matches their relative logical location in Eclipse (project name). 
For example, [Ncore](../../../core/modules/ncore/index.html) physical location relative to the Git root directory is ``core/ncore``, but its logical (project) name in Eclipse is ``org.nasdanika.ncore``.
Therefore, in order for the [flow](../../../core/modules/flow/index.html) model to reference Ncore classes the Ncore model is copied from ``core/ncore`` to ``org.nasdanika.ncore`` in a Maven target directory. 
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
    - https://cdn.jsdelivr.net/npm/mermaid/dist/mermaid.min.js
  body:
    app-page:
      fluid: true
```        

