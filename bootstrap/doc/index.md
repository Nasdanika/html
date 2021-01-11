``org.nasdanika.html.bootstrap`` bundle provides Java fluent API's for building [Bootstrap 4](https://getbootstrap.com/docs/4.5/getting-started/introduction/) elements. The bundle is also published as a jar on [Maven Central](https://search.maven.org/) as [org.nasdanika.html:bootstrap](https://search.maven.org/artifact/org.nasdanika.html/bootstrap).

The bundle features the following packages:

* ${javadoc/org.nasdanika.html.bootstrap} contains interfaces for building Bootstrap HTML markup with ${javadoc/org.nasdanika.html.bootstrap.BootstrapFactory BootstrapFactory} being the entry point. A global instance of ``BootstrapFactory`` can be obtained from ``BootstrapFactory.INSTANCE``.
* ${javadoc/org.nasdanika.html.bootstrap.impl} contains implementations of the above interfaces. A client code developer typically would not need to directly use classes from this package.
* [org.nasdanika.html.bootstrap.factories](factories/index.html) contains an object loader and factories for loading Bootstrap elements from YAML.
