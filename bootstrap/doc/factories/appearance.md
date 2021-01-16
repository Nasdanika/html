Appearance applies Bootstrap styles and HTML attributes loaded from YAML to a target ${javadoc/org.nasdanika.html.bootstrap.BootstrapElement Bootstrap} or ${javadoc/org.nasdanika.html.HTMLElement HTML} element.

The following sections provide information about configuration keys supported by appearance and its sub-elements. 
Keys in bold are default keys, i.e. non-map configuration values are treated as values for those keys.

### Appearance

* **``background``** - Background [bootstrap color](https://getbootstrap.com/docs/4.0/utilities/colors/) code. Default key. Valid values:
    * ``primary``
    * ``secondary``
    * ``success``
    * ``danger``
    * ``warning``
    * ``info``
    * ``light``
    * ``dark``
    * ``white``
    * ``transparent``
* ``attributes`` - a map of HTML attributes. For all top-level keys except ``class``, ``style``, and ``data`` attribute value is produced by converting the value to string for scalars and to JSON string for lists and maps.
    * ``children`` key is ignored - it is used to define a hierarchy of attributes.
	 * For ``class`` attribute its value is formed by concatenating elements using space as a separator. If elements are hierarchical then class name is formed by concatenation with a dash (``-``) as a separator.
	 * If value of ``data`` attribute is a map then keys of that map get concatenated with ``data`` using dash (``-``) as a separator, the same applies to nested maps. Non-map values become attribute values - scalars are converted to string, 
	 * lists are converted to JSON string.
	 * ``style`` can be defined as a string, list or map. If style is defined as a list, all list values are concatenated with space as a separator - it is a convent way for long unstructured definitions.	If style value is a map then the value and its contained map values are processed in the following fashion:
	     * Keys are concatenated with dash as a separator.
	     * List values are concatenated with space as a separator.
* ``margin`` - margin spacing specification, supports the following sub-keys:
    * **``size``** - a number from ``0`` to ``5`` or ``auto``. Default key, so a margin can be defined as ``margin: 3``.
    * ``breakpoint`` - [responsive breakpoint](https://getbootstrap.com/docs/4.0/layout/overview/#responsive-breakpoints) for this margin. Supported values:
        * ``sm`` - Small
        * ``md`` - Medium
        * ``lg`` - Large
        * ``xl`` - Extra large
    * ``side`` - margin side: ``top``, ``bottom``, ``left``, ``right``, ``x``, or ``y``. A single value or a list. If not specified margin applies to all sides.    
* ``padding`` - padding spacing specification. Configured in the same as ``margin`` above.
* ``border`` - border specification. Configuration sub-keys:
    * **``color``** - Bootstrap border color code. Default key. Valid values:
        * ``primary``
        * ``secondary``
        * ``success``
        * ``danger``
        * ``warning``
        * ``info``
        * ``light``
        * ``dark``
        * ``white``
    * ``placement`` - border placement: ``top``, ``bottom``, ``left``, or ``right``. A single value or a list. If not specified then border is applied to all sides.
* ``float``:
    * **``side``** - ``left``, ``right``, or ``none``.
    * ``breakpoint`` - responsive breakpoint, see ``margin`` for valid values.
* ``text`` - text styling. Sub-keys:
    * **``color``** - Bootstrap text color. One of:
        * ``primary``
        * ``secondary``
        * ``danger``
        * ``warning``
        * ``info``
        * ``light``
        * ``dark``
        * ``body``	
        * ``muted``
        * ``white``
        * ``black-50`` - Black 50% transparent
        * ``white-50`` - White 50% transparent
    * ``style`` - Text style, single value or a list of: 
        * ``italic``
        * ``monospace``
        * ``nowrap``
        * ``truncate``
    * ``alignment``:
        * ``left``
        * ``center``
        * ``right``
        * ``justify``
    * ``transform``:
        * ``lowercase``
        * ``uppercase``
        * ``capitalize``
    * ``weight``:
        * ``light``
        * ``normal``
        * ``bold``       
