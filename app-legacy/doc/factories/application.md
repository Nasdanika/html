Specification of Bootstrap Container Application. Supports the following configuration keys:

* ``appearance`` - application container [Appearance](../../bootstrap/factories/appearance.html).
* ``content`` - application container content.
* ``content-row`` - content row **section** configuration - see "Section" below.
* ``content-panel`` - content **panel** configuration - see "Panel" below.
* ``header`` - header section configuration.
* ``fluid`` - if ``true``, then the application is built in a full width container, spanning the entire width of the viewport.
* ``footer`` - footer section configuration.
* ``navigation-bar`` - navigation bar section configuration.
* ``navigation-panel`` - navigation panel configuration. 
* ``page`` - [Bootstrap page](../../bootstrap/factories/page.html) specification. Mutually exclusive with ``theme``. If not provided then a Bootstrap CDN page is constructed to contain the application.
* ``theme`` - Bootstrap theme. Mutually exclusive with ``page``. Default Bootstrap theme or one of [Bootswatch](https://bootswatch.com/) themes. Supported values (theme names):
    * Default
    * [Cerulean](https://bootswatch.com/cerulean/)
    * [Cosmo](https://bootswatch.com/cosmo/)
    * [Cyborg](https://bootswatch.com/cyborg/)
    * [Darkly](https://bootswatch.com/darkly/)
    * [Flatly](https://bootswatch.com/flatly/)
    * [Journal](https://bootswatch.com/journal/)
    * [Litera](https://bootswatch.com/litera/)
    * [Lumen](https://bootswatch.com/lumen/)
    * [Lux](https://bootswatch.com/lux/)
    * [Materia](https://bootswatch.com/materia/)
    * [Minty](https://bootswatch.com/minty/)
    * [Pulse](https://bootswatch.com/pulse/)
    * [Sandstone](https://bootswatch.com/sandstone/)
    * [Simplex](https://bootswatch.com/simplex/)
    * [Sketchy](https://bootswatch.com/sketchy/)
    * [Slate](https://bootswatch.com/slate/)
    * [Solar](https://bootswatch.com/solar/)
    * [Spacelab](https://bootswatch.com/spacelab/)
    * [Superhero](https://bootswatch.com/superhero/)
    * [United](https://bootswatch.com/united/)
    * [Yeti](https://bootswatch.com/yeti/)

### Section

Application section supports the following configuration keys:

* ``appearance`` - specifies section [Appearance](../../bootstrap/factories/appearance.html).
* ``content`` - section content.

### Panel

Application panel extends application section and in addition to the section keys also supports ``width`` key - a map or a list of maps with the following sub-keys:

* **``size``** - panel column width size. Number from ``0`` to ``12`` or ``auto``. Default key.
* ``breakpoint``- [responsive breakpoint](https://getbootstrap.com/docs/4.5/layout/overview/#responsive-breakpoints) for this margin. Supported values:
    * ``sm`` - Small
    * ``md`` - Medium
    * ``lg`` - Large
    * ``xl`` - Extra large
