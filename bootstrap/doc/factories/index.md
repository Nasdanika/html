${javadoc/org.nasdanika.html.bootstrap.factories} contains classes for loading Bootstrap elements from YAML specifications - factories and a ${javadoc/org.nasdanika.html.bootstrap.factories.BootstrapLoader loader}.

### Factories

* [``appearance``](appearance.html) - loads ${javadoc/java.util.function.Consumer}<Object> which accepts either ${javadoc/org.nasdanika.html.HTMLElement} or ${javadoc/org.nasdanika.html.bootstrap.BootstrapElement} and applies Bootstrap styles and HTML attributes.
* [``page``](page.html) - loads ${javadoc/org.nasdanika.html.HTMLPage HTML page} with Bootstrap style and script elements in the head. Extends [HTML Page](../../html/factories/page.html). Supports the default Bootstrap theme and [Bootswatch](https://bootswatch.com/) themes.

### Code sample

#### YAML specification

##### Page

```yaml
page: 
   theme: Cerulean
```

##### Appearance

```yaml
appearance: 
   background: primary
   text: 
      color: ${color}
      alignment: center
      weight: bold
      transform: uppercase
      style: 
         - italic 
         - monospace
   float: 
      side: right
      breakpoint: lg
   attributes:
      class:
         - a
         - b
      style:
         border:
            left:
               - solid
               - 10px
               - black
   margin:
      size: 1
      breakpoint: md
      side: y
   padding: 2
   border: 
      color: warning
      placement: 
         - bottom
         - right
```


#### Java code

```java
Context context = Context.singleton("color", "success");
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
ObjectLoader loader = new BootstrapLoader();
Object appearanceFactory = loader.loadYaml(this.getClass().getResource("appearance-spec.yml"), monitor);
		
Consumer<Object> appearance = Util.callSupplier(Util.<Consumer<Object>>asSupplierFactory(appearanceFactory).create(context), monitor);
Tag div = TagName.div.create("I'm styled");		
appearance.accept(div);
		
BootstrapPageSupplierFactory pageFactory = (BootstrapPageSupplierFactory) loader.loadYaml(HTMLTestBase.class.getResource("bootstrap-page-spec.yml"), monitor);
HTMLPage bootstrapPage = Util.callSupplier(pageFactory.create(Context.EMPTY_CONTEXT), monitor); 

bootstrapPage.title("Appearance demo");
bootstrapPage.body(div);
		
System.out.println(bootstrapPage);
```

#### HTML output

```html
<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/cerulean/bootstrap.min.css" id="nsd-bootstrap-theme-stylesheet"/>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js"></script>
    <title>Appearance demo</title>
  </head>
  <body>
    <div style="border-left:solid 10px black" class="bg-primary a b my-md-1 p-2 border-bottom border-right border-warning float-lg-right text-success font-italic text-monospace text-center text-uppercase font-weight-bold">I'm styled</div>
  </body>
</html>
```

