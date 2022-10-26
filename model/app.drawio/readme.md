
With ${javadoc/org.nasdanika.html.model.app.drawio.ResourceFactory} [action models](../model/index.html) can be loaded from [Drawio](https://www.diagrams.net/) diagrams.
In other words, a structure of a web site/application can be defined as a diagram. 

The below table contains a list of available demos which can be used as starting points for "diagram-as-a-web-application" efforts.

Description | Site | Sources 
----------- | ---- | -------
Documentation generated from a component diagram | https://docs.nasdanika.org/demo-drawio-actions/index.html | https://github.com/Nasdanika/demo-drawio-actions
Process documentation generated from a flow diagram | https://docs.nasdanika.org/demo-drawio-flow-actions/index.html | https://github.com/Nasdanika/demo-drawio-flow-actions
A web site generated from a mind map diagram | https://docs.nasdanika.org/demo-drawio-map/index.html | https://github.com/Nasdanika/demo-drawio-map

Loading can be customized at two levels:

* Adding properties to diagram elements
* Sub-classing ``org.nasdanika.html.model.app.drawio.ResourceFactory``

The following section explains configuration properties. For sub-classing consult ``org.nasdanika.html.model.app.drawio.ResourceFactory`` javadoc and source code.

#### Configuration

##### default-connection-role

Node property. 
Value is used for ``role`` connection property (see below). 
Default connection role is inherited from semantic parents to semantic children.

##### default-connection-target-role

Node property. Value is used for ``role`` of targets of outgoing connections. 
Default connection target role is inherited from semantic parents to semantic children.
See ``target-role`` below for more details.

##### documentation

Location of [Markdown](/modules/core/modules/exec/modules/model/content/Markdown.html) documentation.
The location is resolved relative to the documentation location of the semantic parent (recursively) or relative to the Drawio document (file) location if there is no semantic parent or there is no documentation for the semantic parent.

For pages and page elements documentation may contain ``${{diagram}}`` and ``${{toc}}`` tokens to insert the page diagram and a table of contents respectively. 

The page diagram is processed to link model element to action pages.
Additional processing can be added by overriding on of ``createXXXProcessor`` methods in the resource factory and then overriding ``processEmbeddedDiagramElement`` method in the processor.
For example, shapes can be styled based on information extracted from external systems:

* Roadmap diagram elements can be styled based on status extracted from the issue tracking system.
* Component diagram elements can be styled:
    * Based on construction status extracted from the issue tracking system for a system being built.
    * Based on information extracted from monitoring tools - load, availablity - for already built systems.

##### icon

Optional icon for an action loaded from a model element. Treated as a URL if contains a slash (``/``) or as a CSS class otherwise, e.g. ``fas fa-project-diagram`` a [Font Awesome 5](https://fontawesome.com/v5/search) project diagram icon.

##### page-element

Set it to ``true`` for the root element on the page, specifically for linked pages. 

For example, on the [Living Beings](https://docs.nasdanika.org/demo-drawio-map/) diagram [Cat](https://docs.nasdanika.org/demo-drawio-map/children/1350/children/1369/index.html) is linked to the ``Cat`` page with the ``Cat`` element being the page element.
As such, semantic children of the Cat element on the Cat page (Havana Brown, Maine Coon, and Savannah) are "mounted" to the Cat element on the main diagram.   

Please note that with page linking documentation shall be provided on the linking element, e.g. the Cat element on the main diagram.

##### path

``path`` property is used to form action location (URL). By default diagram element ID is used as path. You can specify path explicitly to have [clean URL's](https://en.wikipedia.org/wiki/Clean_URL).

##### role

Defines diagram element action type (role).
If present, one of:

* ``anonymous`` - Connection target action is added to the anonymous actions of the source action. Anonymous actions are generated, but not auto-linked - they should be explicitly linked from the action content.
* ``child`` - Connection target action is added to the child actions of the source action. Child actions are shown in the left navigation panel.
* ``navigation`` - Connection target action is added to the navigation actions of the source action. Navigation actions are shown in:
    * The footer for the root action, 
    * The navigation bar for the principal action, 
    * The section navigation for sections,
    * Active action navigation for other actions.
* ``none`` - Clears role inherited from the semantic parent, if any.
* ``section`` - Connection target action is added to the section actions of the source action. Section actions are rendered as part of the parent action content.

See [Action Types](../model/index.html#action-types) for additional details.

##### root-page

If a diagram file has multiple pages with some pages linked from others annotate linked pages with ``root-page`` set to ``false``.
In this case these pages of their page elements do not appear in the root of resource.
An alternative is to subclass the resource factory and explicitly select root resource elements from which to generate a web site.

##### selector

Use ``selector`` property to link a diagram element to an element on another diagram (page).
``selector`` value shall be a [Spring boolean expression](https://docs.spring.io/spring-framework/docs/5.3.22/reference/html/core.html#expressions).
The expression is evaluated in the context of a diagram element. 

Selectors allow to "reuse" the same element in multiple diagrams.

For example, on the [Sample Integration](https://docs.nasdanika.org/demo-drawio-actions/children/218O8zdIhuJC0JnV0giS-1/children/218O8zdIhuJC0JnV0giS-2/index.html) diagram ``S3 Reference`` shape references [S3](https://docs.nasdanika.org/demo-drawio-actions/children/UEzPUAAOIrF-is8g5C7q-175/children/s3/index.html) node with ``getProperty('alias') == 's3'`` selector.

##### sort

Specifies how children shall be sorted. 
Property value shall be in the form of ``<type>[:config]`` where a type is one of:

* ``clockwise`` or ``counterclockwise`` - sorts nodes clockwise and counterclockwise respectively relative to their semantic parent. Can be used in mind maps. May take base angle in degrees as optional configuration. Base angle defaults to 90 degrees (12 o'clock) if the semantic parent node does not have a semantic parent and to the angle between the parent and its parent otherwise. [Living beings](https://docs.nasdanika.org/demo-drawio-map/) mind map uses clockwise sort.
* ``label`` - sorts by label value. This is the default sort type. Optional ``descending`` configuration.
* ``property`` - sorts by property value (string). Optional ``descending`` configuration.
 
##### target-role

If this property is set for a connection and is not ``none`` or if ``default-target-role`` is not null or ``none`` then the connection establishes a semantic parent/child role between its source and target target role specifies the child's role.
See ``role`` for a list of supported values.

The Living beings mind map uses ``default-target-role`` to establish semantic parent/child connections between nodes.

