Describe factories, loaders, link to detailed docs.

## AppLoader

			case "label":
				return new LabelSupplierFactory<Label>().load(loader, config, base, subMonitor, marker);
			case "action":
				return new ActionSupplierFactory().load(loader, config, base, subMonitor, marker);
			case "category":
				return new CategorySupplierFactory().load(loader, config, base, subMonitor, marker);
			case "action-reference":
				return new ActionReference(loader, config, base, subMonitor, marker);
			case "category-reference":
				return new CategoryReference(loader, config, base, subMonitor, marker);
			case "application":
				return new BootstrapContainerApplicationSupplierFactory().load(loader, config, base, subMonitor, marker);


## ComposedLoader

	private static final String EXEC_PREFIX = "exec-";
	private static final String APP_PREFIX = "app-";
	private static final String HTML_PREFIX = "html-";
	private static final String BOOTSTRAP_PREFIX = "bootstrap-";
	
TODO: Example of combining different loaders, e.g. loading HTML page contents from a classloader resource	


