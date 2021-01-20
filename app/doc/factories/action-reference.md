Reference to an action defined in another file/resource.

Action and category references allow to break down large action hierarchies into multiple resources possibly stored in different places and maintained by different teams.
This allows to build federated applications assembled from multiple contributions. 
E.g. a knowledge base portal with information pulled from multiple locations. 
This documentation site is built from multiple federated action specification resources with some of them handcrafted and some generated from different sources.
For example, [EMF Models](${base-uri}../../../model-doc/index.html) documentation actions were generated from EMF models.

Action reference supports the following configuration keys:

* **``target``** - URL of the action definition YAML resource. Resolved relative to the current resource URL. Default key.
* ``path`` - If path is specified it is used as a prefix for target action URL. Paths can be used to partition URL space to avoid URL clashes. 
E.g. the target action may have its ``href`` as ``index.html`` and an action ``index.html`` ``href`` may already exist in the hierarchy. 
In this case setting path to, say, ``my-submodule/`` will result in the URL of the target action to be ``my-submodule/index.html``.

