package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.InputBase;
import org.nasdanika.html.Select;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.HTMLElement.Event;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breadcrumbs;
import org.nasdanika.html.bootstrap.Button;
import org.nasdanika.html.bootstrap.ButtonGroup;
import org.nasdanika.html.bootstrap.ButtonToolbar;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.DeviceSize;
import org.nasdanika.html.bootstrap.Direction;
import org.nasdanika.html.bootstrap.Dropdown;
import org.nasdanika.html.bootstrap.FormGroup;
import org.nasdanika.html.bootstrap.InputGroup;
import org.nasdanika.html.bootstrap.Navbar;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.Placement;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.RowContainer.Row.Cell;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.bootstrap.Theme;

/**
 * HTML factory which relies on Bootstrap styles and scripts.
 * @author Pavel
 *
 */
public class DefaultBootstrapFactory implements BootstrapFactory {
	
	private HTMLFactory htmlFactory;

	public DefaultBootstrapFactory(HTMLFactory htmlFactory) {
		this.htmlFactory = htmlFactory;
	}
	
	@Override
	public HTMLFactory getHTMLFactory() {
		return htmlFactory;
	}

	@Override
	public Tag alert(Color color, Object... content) {
		return getHTMLFactory().div(content)
				.addClass("alert")
				.addClassConditional(color != null && color.code != null, "alert-"+color.code)
				.attribute("role", "alert");
	}

	@Override
	public Tag badge(boolean pill, Color color, Object... content) {
		Tag ret = getHTMLFactory().span(content)
				.addClass("badge")
				.addClassConditional(color != null && color.code != null, "badge-"+color.code)
				.addClassConditional(pill, "badge-pill");
		return ret;
	}

	@Override
	public Tag badge(boolean pill, Object... content) {
		Tag ret = getHTMLFactory().span(content)
				.addClass("badge")
				.addClassConditional(pill, "badge-pill");
		return ret;
	}

	@Override
	public Tag badgeLink(Object href, boolean pill, Color color, Object... content) {
		Tag ret = getHTMLFactory().link(href, content)
				.addClass("badge")
				.addClassConditional(color != null && color.code != null, "badge-"+color.code)
				.addClassConditional(pill, "badge-pill");
		return ret;
	}

	@Override
	public Breadcrumbs breadcrums() {
		return new BreadcrumbsImpl(this);
	}

	@Override
	public <H extends HTMLElement<?>> Button<H> button(H htmlElement, Color color, boolean outline) {
		return new ButtonImpl<H>(this, htmlElement, color, outline);
	}

	@Override
	public ButtonGroup buttonGroup(boolean vertical) {
		return new ButtonGroupImpl(this, vertical);
	}

	@Override
	public ButtonToolbar buttonToolbar() {
		return new ButtonToolbarImpl(this);
	}

	@Override
	public Dropdown dropdown(Button<?> button, boolean split, Direction direction) {
		return new DropdownImpl(this, button, split, direction);
	}
	
	@Override
	public InputGroup inputGroup() {
		return new InputGroupImpl(this);
	}
	
	@Override
	public Table table(org.nasdanika.html.Table htmlTable) {
		return new TableImpl(this, htmlTable);
	}
	
	@Override
	public Table table() {
		return table(htmlFactory.table());
	}
	
	@Override
	public Row row(org.nasdanika.html.RowContainer.Row htmlRow) {
		return new RowImpl(this, htmlRow);
	}
	
	@Override
	public Cell cell(org.nasdanika.html.RowContainer.Row.Cell htmlCell) {
		return new CellImpl(this, htmlCell);
	}
	
	@Override
	public FormGroup formGroup(Object label, InputBase<?> input, Object hint) {
		return new FormGroupImpl(this, label, input, hint);
	}
	
	@Override
	public <B extends BootstrapElement<?,?>> B tooltip(B bootstrapElement, Object tooltip, boolean html, Placement placement) {
		tooltip(bootstrapElement.toHTMLElement(), tooltip, html, placement);
		return bootstrapElement;
	}
	
	@Override
	public <H extends HTMLElement<?>> H tooltip(H htmlElement, Object tooltip, boolean html, Placement placement) {
		htmlElement
			.attribute("data-toggle", "tooltip")
			.attribute("data-placement", placement.name().toLowerCase())			
			.attribute("data-html", "true", html)
			.attribute("title", tooltip);		
		return htmlElement;
	}
	
	@Override
	public Tag initTooltipScript() {
		return htmlFactory.nonEmptyTag(TagName.script, "$(function () { $('[data-toggle=\"tooltip\"]').tooltip(); });");
	}
	
	@Override
	public Navs pills() {
		return new NavsImpl(this, true);
	}
	
	@Override
	public Navs tabs() {
		return new NavsImpl(this, false);
	}
	
	@Override
	public Container container() {
		return container(htmlFactory.div());
	}

	@Override
	public Container container(Tag div) {
		return new ContainerImpl(this, div, false);
	}

	@Override
	public Container fluidContainer() {
		return fluidContainer(htmlFactory.div());
	}

	@Override
	public Container fluidContainer(Tag div) {
		return new ContainerImpl(this, div, true);
	}
	
	@Override
	public <P extends HTMLPage> P bootstrapHTMLPage(P page) {
		page
			.head(getHTMLFactory().tag(TagName.meta).attribute("charset", "utf-8"))
			.head(getHTMLFactory().tag(TagName.meta).attribute("name", "viewport").attribute("content", "width=device-width, initial-scale=1, shrink-to-fit=no"));
		return page;
	}
	
	@Override
	public <P extends HTMLPage> P bootstrapCdnHTMLPage(P page, Theme theme) {
		theme.cdn(page);
		P bootstrapPage = bootstrapHTMLPage(page);
		bootstrapPage.script("https://code.jquery.com/jquery-3.3.1.min.js");
		bootstrapPage.script("https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js");
		bootstrapPage.script("https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js");
		return page;
	}
	
	@Override
	public <P extends HTMLPage> P bootstrapCdnHTMLPage(P page) {
		return bootstrapCdnHTMLPage(page, Theme.Default);
	}
	
	@Override
	public HTMLPage bootstrapHTMLPage() {
		return bootstrapHTMLPage(getHTMLFactory().page());
	}
	
	@Override
	public HTMLPage bootstrapCdnHTMLPage(Theme theme) {
		return bootstrapCdnHTMLPage(bootstrapHTMLPage(), theme);
	}
	
	@Override
	public HTMLPage bootstrapCdnHTMLPage() {
		return bootstrapCdnHTMLPage(Theme.Default);
	}

	@Override
	public Select themeSelect(Theme selected) {
		Select select = getHTMLFactory().select();
		select.on(Event.change, "document.getElementById('"+Theme.STYLESHEET_ID+"').href = this.value;");
		for (Theme theme: Theme.values()) {
			select.option(theme.stylesheetCdnURL, theme.name(), theme == selected, false);
		}
		return select;
	}	
	
	@Override
	public <H extends HTMLElement<?>> H display(H element, int level) {
		element.addClass("display-"+level);
		return element;
	}

	@Override
	public Navbar navbar(DeviceSize expandSize, boolean dark, Color background, HTMLElement<?> brand) {
		return new NavbarImpl(this, expandSize, dark, background, brand);
	}
	
}
