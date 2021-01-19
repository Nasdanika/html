``org.nasdanika.html.app`` bundle provides a high-level Java API's for building HTLM applications, such as static web sites and dynamic applications.
The bundle is also published as a jar on [Maven Central](https://search.maven.org/) as [org.nasdanika.html:app](https://search.maven.org/artifact/org.nasdanika.html/app).

The bundle features the following packages:

* ${javadoc/org.nasdanika.html.app} contains interfaces for building ${javadoc/org.nasdanika.html.app.Applicatoin applications} from a hierarchy of ${javadoc/org.nasdanika.html.app.Action actions}. 
* ${javadoc/org.nasdanika.html.app.impl} contains implementations of the above interfaces. 
* [org.nasdanika.html.app.factories](factories/index.html) contains an object loader and factories for loading application elements from YAML.
* ``org.nasdanika.html.app.templates.*`` packages contain application templates for different ${javadoc/org.nasdanika.html.bootstrap.Theme Bootstrap themes}. 
* ${javadoc/org.nasdanika.html.app.viewparts} contains view parts for rendering of different parts of the application. 
* ${javadoc/org.nasdanika.html.app.viewparts.descriptors} contains view parts for rendering descriptors, e.g. generating Web UI from [group input specification](${base-uri}../../core/exec/general/group.html). [Spring Exec](https://github.com/Nasdanika/spring-exec) repository on GitHub provides an example of such rendering.

### Core concepts

Nasdanika HTML application consists of multiple parts as shown on the illustration below. 

![application parts](https://nasdanika.org/downloads/application-parts.png)

All parts are optional.
You can find a demo application for different themes at the links below:

* [Default](https://nasdanika.org/html-app-demo/bootstrap/index.html)
* [Cerulean](https://nasdanika.org/html-app-demo/cerulean/index.html)
* [Cosmo](https://nasdanika.org/html-app-demo/cosmo/index.html)
* [Cyborg](https://nasdanika.org/html-app-demo/cyborg/index.html)
* [Darkly](https://nasdanika.org/html-app-demo/darkly/index.html)
* [Flatly](https://nasdanika.org/html-app-demo/flatly/index.html)
* [Journal](https://nasdanika.org/html-app-demo/journal/index.html)
* [Litera](https://nasdanika.org/html-app-demo/litera/index.html)
* [Lumen](https://nasdanika.org/html-app-demo/lumen/index.html)
* [Lux](https://nasdanika.org/html-app-demo/lux/index.html)
* [Materia](https://nasdanika.org/html-app-demo/materia/index.html)
* [Minty](https://nasdanika.org/html-app-demo/minty/index.html)
* [Pulse](https://nasdanika.org/html-app-demo/pulse/index.html)
* [Sandstone](https://nasdanika.org/html-app-demo/sandstone/index.html)
* [Simplex](https://nasdanika.org/html-app-demo/simplex/index.html)
* [Sketchy](https://nasdanika.org/html-app-demo/sketchy/index.html)
* [Slate](https://nasdanika.org/html-app-demo/slate/index.html)
* [Solar](https://nasdanika.org/html-app-demo/solar/index.html)
* [Spacelab](https://nasdanika.org/html-app-demo/spacelab/index.html)
* [Superhero](https://nasdanika.org/html-app-demo/superhero/index.html)
* [United](https://nasdanika.org/html-app-demo/united/index.html)
* [Yeti](https://nasdanika.org/html-app-demo/yeti/index.html)

This documentation site is also generated from an action hierarchy using the [Cerulean](https://bootswatch.com/cerulean/) theme.

Applications can be built using ${javadoc/org.nasdanika.html.app.ApplicationBuilder application builders} with the ${javadoc/org.nasdanika.html.app.impl.ActionApplicationBuilder action application builder} 
building an application from a hierarchy of actions. Action role defines where an action is displayed in the application as explained below.

The action-centric approach allows developers to focus on the functionality of their application in terms of actions and select action roles and application themes later. 
Also, changing of the action role does not change the application functionality, just the appearance.
It allows to adjust the application as it evolves. For example, an action of a documentation site might be in a section role if it doesn't have a lot of content and then change its role to navigation.

#### Action UI life cycle

In the UI an action goes through the following stages:

* Display - an action link is displayed to the user. The link may have an icon, text or both. It may also have a tooltip. Inline actions don't go through this stage - they go directly to the last stage. 
Some actions are always visible to the user, e.g. the root and principal actions (see below). Some are only shown in a context of another active action, e.g. context and section actions.
* Activation - user clicks on the action link. Actions may have URL, script, and binding activators. URL activator navigates to a URL, script activator executes a script, and a binding activator executes a script on page load allowing to perform custom action binding, e.g. set [Knockout](https://knockoutjs.com/) binding attributes.
* Display of action content. At this stage action content is displayed to the user. For inline actions content is displayed where the link is displayed for non-inline actions. For other actions content is displayed in the content panel. 

#### Action types

An application page is generated from 3 actions:

* Root action - the root of the action hierarchy. It is displayed in the header with its navigation children (see action roles), except the first one, displayed in the root navigation bar on the top right.
On this page "Nasdanika" is the root action.
Context children of the root action are displayed in the footer.
* Principal action - by default the first navigation child of the root action. Displayed in the [navigation bar brand](https://getbootstrap.com/docs/4.0/components/navbar/#brand) on the left. On this page "Documentation" is the principal action.
Context children of the principal action are displayed in the navigation bar on the right of the principal action.
Navigation children of the principal action are displayed recursively in the navigation panel. On this page it is a tree of documentation actions.
* Active action - any action in the hierarchy which is currently "active", i.e. it's content is displayed in the content panel and it is shown as active/selected in the navigation panel or navigation bar. On this page ``Reference/Knowledge Base/HTML/App`` is the active action - it is selected in the navigation tree and your are reading its content. Active action path is displayed in the [breadcrumb](https://getbootstrap.com/docs/4.0/components/breadcrumb/).
    * Context children of the active action are displayed in in the active action navigation bar to the right of the action title.
    * Section children are displayed as page sections with their context children displayed in section navigation bars to the right of section titles (for paragraph section style).
    * Content left children are displayed in the left content navigation panel.
    * Content right children are displayed in the right content navigation panel.   

#### Action roles

Action can be in zero or more roles. Action roles define where the action and its content (for sections) is displayed. 
This section explains action roles. It also provides a role-centric explanation of where actions are displayed depending on their role and parent action type.

##### Navigation		

* For the root action the first navigation child is the principal. Subsequent navigation children, if any, are displayed in the root navigation bar on the top right.
* Navigation children of the principal action and their navigation children recursively are displayed in the navigation panel.
		
##### Context		

* Context children of the root action are displayed in the footer.
* Context children of the principal action are displayed in the navigation bar.
* Active action context children are displayed in the active action navigation bar to the right of the action title.
* Section action context children are displayed in the section navigation bar to the right of the section title.
		
##### Section		

Section children of the active action are shown as sections in the content panel of their non-section ancestor.
Sections can be nested.
There are different ways to display sections, they are defined by the parent action's ${javadoc/org.nasdanika.html.app.SectionStyle section style}.
		
##### Content left		

Content left children of the active action are shown in a navigation panel to the left of the action content.

##### Content right		

Content right children of the active action are shown in a navigation panel to the right of the action content.

#### Application parts

This section describes application parts and provides parts-centric view on where actions are displayed in addition to type and role centric views provided in the previous sections.

##### Header

Displays the root action on the left. 

###### Root navigation

Displays second+ navigation children of the root, if any, on the right.

##### Navigation bar

Displays the principal action in the brand and its context children, if any, on the right of the brand.

##### Navigation panel

Recursively displays navigation children of the principal action. The navigation panel (and content left/right navigation panel) are adaptive - they change their appearance depending on the action hierarchy depth and categorization:

* If there are no categories and tree depth is 1 then actions are displayed as a list.
* If navigation action tree depth is more than one then actions are displayed in a tree.
* If root actions of the panel are categorized then they are displayed in category cards as a list or tree depending on the tree depth.

##### Content panel

Displays breadcrumb with active action path, active action title, content and content of its section children. 
If active action is a section then its first non-section ancestor is used as an "effective" active action. 

###### Active action navigation bar

Displays context children of the active action.

###### Sections

Section children of the active action. 

###### Content left navigation panel 

Adaptive navigation panel displaying ``content left`` children of the active action. Support for section content left and right panels will be added in the future releases.

###### Content right navigation panel

Adaptive navigation panel displaying ``content right`` children of the active action.

##### Footer

Displays context children of the root action.
