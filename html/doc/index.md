``org.nasdanika.html`` bundle provides Java fluent API's for building [HTML](https://www.w3schools.com/html/) elements. The bundle is also published as a jar on [Maven Central](https://mvnrepository.maven.org/) as [org.nasdanika.html:html](https://mvnrepository.maven.org/artifact/org.nasdanika.html/html).

The bundle features the following packages:

* ${javadoc/org.nasdanika.html} contains interfaces for building HTML markup with ${javadoc/org.nasdanika.html.HTMLFactory HTMLFactory} being the entry point. A global instance of ``HTMLFactory`` can be obtained from ``HTMLFactory.INSTANCE``.
* ${javadoc/org.nasdanika.html.impl} contains implementations of the above interfaces. A client code developer typically would not need to directly use classes from this package.
* [org.nasdanika.html.factories](factories/index.html) contains an object loader and factories for loading HTML elements from YAML.
