Label is a base class for [Action](action.html) and [Category](category.html) and these two classes inherit label configuration keys.

Below is a list of label configuration keys: 

* ``appearance`` - label [Appearance](../../bootstrap/factories/appearance.html).
* ``color`` - Bootstrap color. One of:
    * ``primary``
    * ``secondary``
    * ``success``
    * ``danger``
    * ``warning``
    * ``info``
    * ``light``
    * ``dark``
    * ``white``
* ``description`` - label description. Can be displayed in a modal dialog box in the UI.
* ``icon`` - label icon. If value contains a slash (``/``) then it is treated as an image URL. Otherwise it is treated as a CSS class. E.g. ``fas fa-cog`` for Font Awesome [cog icon](https://fontawesome.com/icons/cog?style=solid).
* ``id`` - label id. If not set then a unique label id is generated during loading.
* ``notification`` - a [badge](https://getbootstrap.com/docs/4.5/components/badge/) notification to display next to the label icon and text. E.g. a number of new messages in a mailbox.
* ``outline`` - if ``true`` then label buttons are rendered as [outline buttons](https://getbootstrap.com/docs/4.5/components/buttons/#outline-buttons).
* **``text``**  - label text. Default attribute, i.e. if label configuration value is not a map then it is used as label text.
* ``tooltip`` - label tooltip. If not present and description is present then the first sentence of the description is used as tooltip.
