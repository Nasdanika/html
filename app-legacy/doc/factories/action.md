Action extends [Label](label.html). It supports of label's configuration keys and also the following keys:

* ``content`` - action content to be displayed in the content panel for active actions.
* ``children`` - action children - actions or [categories](category.html).
* ``confirmation`` - if specified, a click on the action link opens an alert dialog with the provided confirmation message. 
* ``disabled`` - if ``true`` action is displayed as disabled.
* ``inline`` - if ``true`` action content is displayed in the navigation parts instead of the action link. Currently supported in navigation bars. Can be used to inline search or login forms.
* ``href`` - Action URL relative to the parent action URL or ``base-uri`` context property for the root action. Interpolated and as such can contain ``${{base-uri}}`` token. 
It can be useful when the parent action points to an external location and a child action needs to have its href resolved relative to the base URI. 
For example, this site's root action references the Nasdanika site and the principal action uses ``base-uri`` property in its ``href``. If ``href`` is not specified and an action
has content then ``href`` is derived from action ``id`` by adding ``.html`` extension. As such if ``id`` is also not specified an action URL is randomly generated. This behavior
frees application developers from having to think about aciton URL's and avoiding clashes when URL's are not of a concern - e.g. users know only the entry point URL (e.g. root or principal action URL's)
and then navigate from it to other actions.
* ``roles`` - A single value or a list of action roles. If not specified then ``navigation`` role is assumed. Therefore, if you need an action with no roles you'll need so supply an empty list. Roles:
    * ``content-left``
    * ``content-right``
    * ``context``
    * ``navigation``
    * ``section``
* ``script`` - JavaSript code to execute when action is activated. Mutually exclusive with ``href``.
* ``section-style`` - style of section children. One of the following values:
    * ``action-group`` - section labels (icons and text) are rendered as a [list of actions](https://getbootstrap.com/docs/4.5/components/list-group/#javascript-behavior). Click on a label displays action content on the right of the action group.
    * ``auto`` - this is the default "style", which, actually, defines a sequence of sections styles:
        * ``tab`` for the top-level sections.
        * ``action-group`` for the second level.
        * ``default`` for subsequent levels.
    * ``card`` - sections are rendered in [cards](https://getbootstrap.com/docs/4.5/components/card/) organized in [card columns](https://getbootstrap.com/docs/4.2/components/card/#card-columns).    
    * ``card-pill`` - sections are rendered as [navigation pills in a card](https://getbootstrap.com/docs/4.5/components/card/#navigation).
    * ``card-pill`` - sections are rendered as [navigation tabs in a card](https://getbootstrap.com/docs/4.5/components/card/#navigation).
    * ``default`` - sections are rendered as DIV's with Hx headers where x starts with 3 and increases for each additional paragraph level up to H6.
    * ``pill`` - sections labels are rendered as [pills](https://getbootstrap.com/docs/4.5/components/navs/#pills). Click on a pill shows section content.
    * ``tab`` - sections labels are rendered as [tabs](https://getbootstrap.com/docs/4.5/components/navs/#tabs). Click on a tab shows section content.
	 * ``table`` - sections are rendered in a table with section label in the first column and section content in the second. Categories are rendered as category labels in cells spanning both columns.    
* ``section-columns`` - number of card columns if section style is ``card``. Default is ``3``.
