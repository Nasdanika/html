${javadoc/org.nasdanika.html.factories} contains classes for loading HTML elements from YAML specifications - factories and a ${javadoc/org.nasdanika.html.factories.HTMLLoader loader}.

### Factories

* [``page``](page.html) - loads ${javadoc/org.nasdanika.html.HTMLPage HTML page}

### Code sample

#### YAML specification

```yaml
page: 
   head: <!-- Head comment -->
   body: Body content
```

#### Java code

```java
Context context = Context.EMPTY_CONTEXT;
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
ObjectLoader loader = new HTMLLoader();
Object pageFactory = loader.loadYaml(this.getClass().getResource("page-spec.yml"), monitor);

HTMLPage htmlPage = Util.callSupplier(Util.<HTMLPage>asSupplierFactory(pageFactory).create(context), monitor);
System.out.println(htmlPage.toString());
```

#### HTML output

```html
<!DOCTYPE html>
<html>
  <head><!-- Head comment --></head>
  <body>Body content</body>
</html>
```

