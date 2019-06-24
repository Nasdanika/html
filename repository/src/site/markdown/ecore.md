# Ecore

[Ecore](apidocs/org.nasdanika.html.ecore/apidocs/index.html) bundle provides classes for generating documentation for [Ecore models](https://www.eclipse.org/ecoretools/) 
leveraging [Application](app.html) and [EMF](emf.html) - it uses [ViewAction](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/ViewAction.html) adapters for model elements
and [BootstrapContainerRouterApplication](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/impl/BootstrapContainerRouterApplication.html).

The documentation generator uses documentation annotations to generate element descriptions. 
It treats them as markdown.
In order for the generator to be able to read the annotations "Suppress GenModel Annotations" shall be set to ``false`` in the GenModel - ``true`` by default.

## Example

The below code generates [documentation](tests/dumps/ecore/index.html) for the [Nasdanika bank](../bank/index.html) model.

```
EcoreDocumentationGenerator generator = new EcoreDocumentationGenerator("Nasdanika Bank Model", null, null);
generator.loadGenModel("urn:org.nasdanika.bank");
Container<InputStream> fsc = new FileSystemContainer(new File("target/test-dumps/ecore"));
ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
BiFunction<org.nasdanika.common.resources.File<InputStream>, Object, InputStream> encoder = (file, contents) -> {
	InputStream ret = DefaultConverter.INSTANCE.convert(contents, InputStream.class);
	if (ret == null) {
		// toString() conversion
		ret = DefaultConverter.INSTANCE.convert(String.valueOf(contents), InputStream.class);
	}
	return ret;
};
generator.generate(fsc.adapt(null, encoder), progressMonitor);		
``` 

This code is executed as part of automated build - the build creates an Eclipse product based on an Eclipse application.
The application runs automated tests including generation of the model documentation.
Once the Eclipse product is built, the build process launches it. 

## Localization

Model documentation can be localized by:

* Providing ``ResourceLocator`` to the generator to localize "Package" and "Summary" strings - the last argument of the constructor.
* Adding an adapter factory for ``ResourceLocator`` to the generator adapter factory - below it is done by overriding ``createAdapterFactory()`` method to use a resource locator which uses model annotations with source ``urn:org.nasdanika``.
It reads ``label`` and ``documentation`` keys from the annotation and the mapper function adds ``_ru`` to the keys to retrieve localized values.

```
EcoreDocumentationGenerator generator = new EcoreDocumentationGenerator(
		"Модель Банка Насданики", 
		"Общее описание модели - обычно в случае если документации пакетов недостаточно",
		UI.RU) {
				
	@Override
	protected EcoreViewActionAdapterFactory createAdapterFactory() {
		EcoreViewActionAdapterFactory adapterFactory = super.createAdapterFactory();
		
		adapterFactory.registerAdapterFactory(
				new FunctionAdapterFactory<ResourceLocator, EModelElement>(
						EcorePackage.Literals.EMODEL_ELEMENT, 
						ResourceLocator.class, 
						this.getClass().getClassLoader(), 
						RussianResourceLocator::new));
		
		return adapterFactory;
	}
	
};
generator.loadGenModel("urn:org.nasdanika.bank");
Container<InputStream> fsc = new FileSystemContainer(new File("target/test-dumps/ecore/ru"));
ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
BiFunction<org.nasdanika.common.resources.File<InputStream>, Object, InputStream> encoder = (file, contents) -> {
	InputStream ret = DefaultConverter.INSTANCE.convert(contents, InputStream.class);
	if (ret == null) {
		// toString() conversion
		ret = DefaultConverter.INSTANCE.convert(String.valueOf(contents), InputStream.class);
	}
	return ret;
};
generator.generate(fsc.adapt(null, encoder), progressMonitor);		
```

You can see the localized version [documentation](tests/dumps/ecore/ru/index.html) - the localization is partial, just to demonstrate that it works - only the Bank package and Account class label and part of documentation are localized.
Localization of UI elements like tabs will be added later.
