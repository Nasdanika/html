# Application Model

EMF implementation of the Application API. It can be used for application prototyping and as a transformation step in the application rendering process.

* [Model documentation](app-model-doc)
* [Java API documentation](apidocs/org.nasdanika.html.app.model/apidocs/index.html)
* [Sample model](NasdanikaBank.app)

Install the application model editor from the p2 repository and use New > Nasdanika > Application model to create a new model.

## Templating

The application model can also be used for templating. Currently there is no template engine implementation. Future releases might provide template engine.
One possible implemntation of such an engine is to use token source to interpolate ``{{...}}`` tokens. 
Token source would also be used to retrieve ``iterator`` value which should be a collection of token sources. 
Each such token source will be used for template instantiation. 
A bit more advanced approach is to have an iterator class/function taking a token source and returning a collection of token sources. 
``class:`` prefix can be used to specify that the iterator expression is not a token name but a class name. 

