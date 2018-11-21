package org.nasdanika.html.app.impl;

import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Container.Row;
import org.nasdanika.html.bootstrap.Container.Row.Col;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;
import org.nasdanika.html.bootstrap.Theme;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;

/**
 * Application which uses Bootstrap container for content layout.
 * @author Pavel Vlasov
 *
 */
public class BootstrapContainerApplication implements Application {

	private HTMLPage page;
	protected Container container;
	protected Col header;
	protected Col navigation;
	protected Col leftPanel;
	protected Col content;
	protected Col footer;
	protected Row contentRow;

	/**
	 * Creates bootstrap CDN application
	 */
	public BootstrapContainerApplication() {
		this(BootstrapFactory.INSTANCE);
	}
	
	/**
	 * Creates bootstrap CDN application
	 */
	public BootstrapContainerApplication(BootstrapFactory factory) {
		this(factory, Theme.Default);
	}
	
	/**
	 * Creates bootstrap CDN application
	 */
	public BootstrapContainerApplication(Theme theme) {
		this(BootstrapFactory.INSTANCE, theme);
	}
	
	/**
	 * Creates bootstrap CDN application
	 */
	public BootstrapContainerApplication(BootstrapFactory factory, Theme theme) {
		this(factory, factory.getHTMLFactory().page());
		factory.bootstrapCdnHTMLPage(page, theme);
	}		
	
	/**
	 * Creates bootstrap application from bootstrap pre-configured page.
	 */
	public BootstrapContainerApplication(BootstrapFactory factory, HTMLPage page) {
		this.page = page;
		container = factory.container();
		page.body(container);
		header = container.row().col();
		navigation = container.row().col();
		contentRow = container.row();
		leftPanel = contentRow.col();
		content = contentRow.col();
		footer = container.row().col();
	}
	

	@Override
	public Application header(Object... content) {
		header.toHTMLElement().content(content);
		return this;
	}

	@Override
	public Application navigation(Object... content) {
		navigation.toHTMLElement().content(content);
		return this;
	}

	@Override
	public Application leftPanel(Object... content) {
		leftPanel.toHTMLElement().content(content);
		return this;
	}

	@Override
	public Application content(Object... content) {
		this.content.toHTMLElement().content(content);
		return this;
	}

	@Override
	public Application footer(Object... content) {
		footer.toHTMLElement().content(content);
		return this;
	}

	@Override
	public HTMLPage getHTMLPage() {
		return page;
	}

	@Override
	public Object produce(int indent) {
		return page.produce(indent);
	}
	
	@Override
	public String toString() {
		return page.toString();
	}

	@Override
	public void close() throws Exception {
		page.close();		
	}
		
	/**
	 * For demo
	 * @param args
	 */
	public static void main(String[] args) {
		Application app = new BootstrapContainerApplication(Theme.Litera) {
			
			{
				container.border(Color.DANGER);
				header.border(Color.DANGER).background(Color.PRIMARY);
				navigation.border(Color.DANGER);
				leftPanel.border(Color.DANGER).widthAuto();
				footer.border(Color.DANGER);
				content.border(Color.DANGER);
			}
			
		};
		Tag treeContainer = app.getHTMLPage().getFactory().div();
		app.header("header").navigation("navigation").leftPanel(treeContainer).content("content").footer("footer");
		
		JsTreeFactory jsTreeFactory = JsTreeFactory.INSTANCE;
		jsTreeFactory.cdn(app.getHTMLPage());
		
		FontAwesomeFactory.INSTANCE.cdn(app.getHTMLPage());
				
		JsTreeNode rootNode = jsTreeFactory.jsTreeNode();
		rootNode.icon("far fa-user");
		rootNode.text("User");
		
		app.getHTMLPage().body(jsTreeFactory.bind(treeContainer, jsTreeFactory.buildAjaxJsTree("jstree.json", null)));		
		
		System.out.println(app);
	}

}
