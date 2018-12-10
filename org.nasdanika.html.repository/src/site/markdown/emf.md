# EMF

[Java API](apidocs/org.nasdanika.html.emf/apidocs/index.html) for generating HTML from EClass metadata, including annotations, and EObject data.

This is work in progress. The general idea is to have adapter factories which adapt EObject's to different UI generation participants as outlined below:
* Target EObject is adapted to [Application](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/Application.html). Customization of this adapter will change a general appearance of the application. 
* Target EObject is adapted to [ApplicationBuilder](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/ApplicationBuilder.html).
* Default adapter creates [ActionApplicationBuilder](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/impl/ActionApplicationBuilder.html).
* The adapter adapts the target EObject to [Action](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/Action.html).
* It also adapts the target EObject to different view parts - header, navigation bar, navigation panel, content panel, section, footer. This would require differentiation of [ViewPart](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/ViewPart.html)s by role which can be done by creating marker interfaces extending ViewPart.
* EObject action would adapt EObject to property source. EReferences will also be converted to property sources, this would require an interface something like EReferencePropertySourceProvider with ``PropertySource adapt(EReference)`` method. EObject would be adapted to this interface to obtain EReference property sources.

In the case of dynamic web applications access to servlet API's such as request/response may also be done through the adapter mechanisim.

Context actions for EObject would include Edit/Delete, subject to the principal permissions and model modifiability. 
E.g. in the case of generated documentation neither edit or delete would be available.

For references context actions would include creation of a new reference element. 

EOperations may also be used as context actions. For this EOperations and EParameters should be annotated or have an entry in the configuration model (see below).  

The factories will be registered with EClass as a key, i.e. factory selection will be based on the target EObject class hierarchy. 
There will be a default catch-all implementation for each factory/adapter type. 
This approach will allow fine-grained customization of the generation process.

Adapters may be using EMF metadata, model annotations, and resource bundles so a good deal of UI customization may require no coding.
Also in the future a generator may be provided similar to [Codegen ECore Web UI](https://github.com/Nasdanika/codegen-ecore-web-ui) to generate adapters from a configuration
model instead of creating them manually.    