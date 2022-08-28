
``TODO - Revisit``

With ${javadoc/org.nasdanika.html.model.app.drawio.AppDrawioResourceFactory} action models can be loaded from [Drawio](https://www.diagrams.net/) diagrams.
In other words, a structure of a web site/application can be defined as a diagram. 

The below table contains a list of available demos which can be used as starting points for "diagram-as-a-web-application" efforts.

Description | Site | Sources 
----------- | ---- | -------
Documentation generated from a component diagram | https://docs.nasdanika.org/demo-drawio-actions/index.html | https://github.com/Nasdanika/demo-drawio-actions
Process documentation generated from a flow diagram | https://docs.nasdanika.org/demo-drawio-flow-actions/index.html | https://github.com/Nasdanika/demo-drawio-flow-actions
A web site generated from a mind map diagram | https://docs.nasdanika.org/demo-drawio-map/index.html | https://github.com/Nasdanika/demo-drawio-map

Loading can be customized at two levels:

* Adding properties to diagram elements
* Sub-classing ``AppDrawioResourceFactory``

The following section explains configuration properties. For sub-classing consult ``AppDrawioResourceFactory`` javadoc and source code.

#### Configuration

This section explains configuration specific for ``AppDrawioResourceFactory``. See also [Drawio](/modules/core/modules/drawio/index.html) for ${javadoc/org.nasdanika.drawio.DrawioResourceFactory} configuration properties.

##### action

[Action](Action.html) prototype URI resolved relative to the Drawio document (file) location. 
Use action prototype for fine-grained configuration of the action loaded from a given diagram element.

##### default-connection-role

Node property. Value is used for ``role`` connection property (see below). 
Default connection role is inherited from semantic parents to semantic children.

##### documentation

Location of [Markdown](/modules/core/modules/exec/modules/model/content/Markdown.html) documentation.
The location is resolved relative to the documentation location of the semantic parent (recursively) or relative to the Drawio document (file) location if there is no semantic parent or there is no documentation for the semantic parent.

For pages and page elements documentation may contain ``${{diagram}}`` and ``${{toc}}`` tokens to insert the page diagram and a table of contents respectively. 

The page diagram is processed to link model element to action pages.
Additional processing can be added by overriding ``processEmbeddedDiagramElement`` method.
For example, shapes can be styled based on information extracted from external systems:

* Roadmap diagram elements can be styled based on status extracted from the issue tracking system.
* Component diagram elements can be styled:
    * Based on construction status extracted from the issue tracking system for a system being built.
    * Based on information extracted from monitoring tools - load, availablity - for already built systems.

##### icon

Optional icon for an action loaded from a model element. Treated as a URL if contains a slash (``/``) or as a CSS class otherwise, e.g. a [Font Awesome](https://fontawesome.com/) icon.
    
##### page-element

Set it to ``true`` for the root element on the page, specifically for linked pages. 

For example, on the [Living Beings](https://docs.nasdanika.org/demo-drawio-map/) diagram [Cat](https://docs.nasdanika.org/demo-drawio-map/children/1350/children/1369/index.html) is linked to the ``Cat`` page with the ``Cat`` element being the page element. As such semantic children of the Cat element on the Cat page (Havana Brown, Maine Coon, and Savannah) are "mounted" to the Cat element on the main diagram.   

Please note that with page linking documentation shall be provided on the linking element, e.g. the Cat element on the main diagram.

##### path

``path`` property is used to form action location (URL). By default diagram element ID is used as path. You can specify path explicitly to have [clean URL's](https://en.wikipedia.org/wiki/Clean_URL).

##### root-action

If a diagram file has multiple pages with some pages linked from others annotate linked pages with ``root-action`` set to ``false``. In this case these pages of their page elements do not appear in the root of resource. An alternative is to explicitly select root resource elements from which to generate a web site.

##### role

Connection property. If present, one of:

* ``anonymous`` - Connection target action is added to the anonymous actions of the source action. Anonymous actions are generated, but not auto-linked - they should be explicitly linked from the action content.
* ``child`` - Connection target action is added to the child actions of the source action. Child actions are shown in the left navigation panel.
* ``navigation`` - Connection target action is added to the navigation actions of the source action. Navigation actions are shown in:
    * The footer for the root action, 
    * The navigation bar for the principal action, 
    * The section navigation for sections,
    * Active action navigation for other actions.
* ``none`` - Clears role inherited from the semantic parent, if any.
* ``section`` - Connection target action is added to the section actions of the source action. Section actions are rendered as part of the parent action content.

See [Action Types](#action-types) for additional details.

##### xref

Use ``xref`` property to link a diagram element to an element on another diagram (page).
``xref`` value shall be in the form of ``<property name>:<property value>``. 
For example if there is a diagram element with ``alias`` property set to ``S3``, set ``xref`` to ``alias:S3`` to link to it.
This property allows to "reuse" the same element in multiple diagrams.
