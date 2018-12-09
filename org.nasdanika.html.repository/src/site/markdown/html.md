# HTML

[Java API](apidocs/org.nasdanika.html/apidocs/index.html) to build HTML.

The entry point to the API is [HTMLFactory](apidocs/org.nasdanika.html/apidocs/index.html?org/nasdanika/html/HTMLFactory.html).
It can be obtained as ``HTMLFactory factory = HTMLFactory.INSTANCE;``.  

## Examples

### HTML Page

```
// HTML page
HTMLPage page = factory().page();
page.head(factory.getHTMLFactory().tag(TagName.meta).attribute("charset", "utf-8"));
page.head(factory.getHTMLFactory().tag(TagName.meta).attribute("name", "viewport").attribute("content", "width=device-width, initial-scale=1, shrink-to-fit=no"));
		
page.title("Demo");
page.stylesheet("https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css");
page.script("https://code.jquery.com/jquery-3.3.1.slim.min.js");
page.script("https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js");
page.script("https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js");
		
page.body("Hello");
```

## Stringification

[HTMLElementImpl](apidocs/org.nasdanika.html/apidocs/index.html?org/nasdanika/html/impl/HTMLElementImpl.html) has two ``stringify()`` methods to easily convert Readers, InputStreams and URL's to strings. 

Strings are returned as is, nulls are converted to an empty string. 
If an object is an instance of [Producer](apidocs/org.nasdanika.html/apidocs/index.html?org/nasdanika/html/Producer.html) then its ``produce()`` method is used for stringification.
For all other objects ``toString()`` method is used to stringify.

The below example shows how to load classloader resource:

```
System.out.println(HTMLElementImpl.stringify(getClass().getResource("test-resource.txt")));
```

HTML API uses strigification on attributes, CSS classes, and content. It allows to write compact code, e.g. to produce a script tag with script code loaded from classloader resource:

```
HTMLFactory.INSTANCE.tag(TagName.script, getClass().getResource("my-script.js"));
``` 

## Simple templating (interpolation)

HTMLFactory has several ``interpolate`` methods which replace ``{{token name}}`` entries in the input with token values obtained from [TokenSource](apidocs/org.nasdanika.html/apidocs/index.html?org/nasdanika/html/TokenSource.html), Map<String, Object> or a single key/value pair.


There is also [MutableTokenSource](apidocs/org.nasdanika.html/apidocs/index.html?org/nasdanika/html/MutableTokenSource.html] interface which can be used to accumulate tokens without having to create a map. 
MutableTokensource can be created with ``mutableTokenSource()`` factory methods.

Interpolation input can be   