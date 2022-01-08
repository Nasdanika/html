${embedded-image/png/nasdanika-logo.png}


```java
	private static Label createLabel(
			Action action, 
			Action activeAction, 
			BiFunction<Action, URI, URI> uriResolver, 
			Function<Action, String> idProvider, 
			String appearancePath,
			boolean recursive) {
		
		URI activeActionURI = uriResolver.apply(activeAction, null);
		URI uri = uriResolver.apply(action, activeActionURI);
		
		if (isBlank(action.getText()) && isBlank(action.getIcon()) && !recursive) {
			return null;
		}
		Label label = isLink(action, uri) ? AppFactory.eINSTANCE.createLink() : AppFactory.eINSTANCE.createLabel();
		configureLabel(action, activeAction, uriResolver, idProvider, appearancePath, label, recursive);
				
		return label;
	}
```