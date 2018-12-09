# HTML

[Java API](apidocs/html/index.html) to build HTML.

The entry point to the API is [HTMLFactory](apidocs/html/index.html?org/nasdanika/html/HTMLFactory.html).
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

## Simple templating (interpolation)

HTMLFactory has several ``interpolate`` methods which replace ``{{token name}}`` entries in the input with token values obtained from [TokenSource](apidocs/html/index.html?org/nasdanika/html/TokenSource.html), Map<String, Object> or a single key/value pair.


There is also [MutableTokenSource](apidocs/html/index.html?org/nasdanika/html/MutableTokenSource.html] interface which can be used to accumulate tokens without having to create a map. 
MutableTokensource can be created with ``mutableTokenSource()`` factory methods.

Interpolation input can be   