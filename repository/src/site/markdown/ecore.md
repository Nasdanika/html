# Ecore

[Ecore](apidocs/org.nasdanika.html.ecore/apidocs/index.html) bundle provides classes for generating documentation for [Ecore models](https://www.eclipse.org/ecoretools/) 
leveraging [Application](app.html) and [EMF](emf.html) - it uses [ViewAction](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/ViewAction.html) adapters for model elements
and [BootstrapContainerRouterApplication](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/impl/BootstrapContainerRouterApplication.html).

## Example

The below code generates [documentation](tests/dumps/ecore/index.html) for the (Nasdanika bank)[../bank/index.html] model.

```
EcoreDocumentationGenerator generator = new EcoreDocumentationGenerator("Nasdanika Bank Model", null);
generator.loadGenModel("urn:org.nasdanika.bank");
generator.generate(new File("target/test-dumps/ecore"));		
``` 

This code is executed as part of automated build - the build creates an Eclipse product based on an Eclipse application.
The application runs automated tests including generation of the model documentation.
Once the Eclipse product is built, the build process launches it. 
