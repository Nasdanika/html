Bootsrap page loads ${javadoc/org.nasdanika.html.HTMLPage HTML page} with Bootstrap style and script elements in the head. 
It extends [HTML Page](../../html/factories/page.html) and as such supports all of its configuration keys plus:

* ``cdn`` - if ``true`` (default) then stylesheet and script entries pointing to Bootstrap CDN are added to the page head. If ``false`` those entries shall be explicitly added. When ``theme`` is specified ``cdn`` cannot be ``false``.
* ``theme`` - Bootstrap theme - default or one of [Bootswatch](https://bootswatch.com/) themes. Supported values (theme names):
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
    