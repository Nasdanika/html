TODO - list of available templates, copy from index.md

Supported keys:

* ``header``
* ``navigation-bar``
* ``navigation-panel``
* ``content-panel``
* ``footer``
* ``appearance``
* ``actions``
* ``template`` - Application template URL. If present the template is used to build the application before this specification. 
* ``fluid`` - If ``true``, then the application is built in a full width container, spanning the entire width of the viewport.
* ``page`` - Bootstrap page specification.
    * ``theme`` - Bootstrap theme. One of:
        *	Cerulean
        *	Cosmo
        *	Cyborg
        *	Darkly
        *	Flatly
        *	Journal
        *	Litera
        *	Lumen
        *	Lux
        *	Materia
        *	Minty	
        *	Pulse
        *	Sandstone
        *	Simplex
        *	Sketchy
        *	Slate
        *	Solar
        *	Spacelab
        *	Superhero
        *	United
        *	Yeti    
    *  ``cdn`` - if ``true`` (default), Bootstrap CDN stylesheets and scripts are added to the page.
    * ``scripts``
    * ``script-references``
    * ``stylesheets``
    * ``stylesheet-references``
    * ``font-awesome`` - If this attribute is set to true (default) Font Awesome CDN stylesheet reference is added to the head.
    * ``github-markdown-css`` - If this attribute is set to true (default) GitHub Markdown CSS CDN stylesheet reference is added to the head.
    * ``highlight-js`` - If this attribute is set to true (default) highlight.js CDN script and stylesheet references are added to the head as well as the initialization script in order to provide syntax highlighting in markdown fenced blocks.
    * ``js-tree`` - If this attribute is set to true (default) jsTree CDN script and stylesheet references are added to the head.
    * ``line-awesome`` - If this attribute is set to true (default) Line Awesome CDN stylesheet reference is added to the head.
    * ``head``
    * ``body``

Header, navigation-bar, navigation-panel, content-panel and footer support the following keys:

* ``appearance``
* ``content``

navigation-panel and content-panel also support ``width`` key which shall be either a number (default breakpoint) of a map of Bootstrap breakpoints to numbers. 
Breakpoint keys: 

* ``default``
* ``sm`` - Small
* ``md`` - Medium
* ``lg`` - Large
* ``xl`` - Extra large
