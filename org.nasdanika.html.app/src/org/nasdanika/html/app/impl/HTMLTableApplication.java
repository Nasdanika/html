package org.nasdanika.html.app.impl;

import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.RowContainer.Row;
import org.nasdanika.html.RowContainer.Row.Cell;
import org.nasdanika.html.Table;
import org.nasdanika.html.app.Application;

/**
 * HTML Application which uses HTML {@link Table} as application backing HTML structure.
 * Subclass to customize appearance.
 * @author Pavel Vlasov
 *
 */
public class HTMLTableApplication implements Application {
	
	protected Table table;
	protected Cell header;
	protected Cell navigationBar;
	protected Cell leftPanel;
	protected Cell content;
	protected Cell footer;
	
	private HTMLPage page;
	
	public HTMLTableApplication() {
		this(HTMLFactory.INSTANCE);
	}
	
	public HTMLTableApplication(HTMLFactory factory) {
		this(factory.page());
	}

	public HTMLTableApplication(HTMLPage page) {
		this.page = page;
		table = page.getFactory().table();
		page.body(table);
		header = table.header().row().cell().colspan(2);
		navigationBar = table.body().row().cell().colspan(2);
		Row contentRow = table.body().row();
		leftPanel = contentRow.cell();
		content = contentRow.cell();		
		footer = table.footer().row().cell().colspan(2);		
	}

	@Override
	public Application header(Object... content) {
		header.content(content);
		return this;
	}

	@Override
	public Application navigationBar(Object... content) {
		navigationBar.content(content);
		return this;
	}

	@Override
	public Application leftPanel(Object... content) {
		leftPanel.content(content);
		return this;
	}

	@Override
	public Application content(Object... content) {
		this.content.content(content);
		return this;
	}

	@Override
	public Application footer(Object... content) {
		footer.content(content);
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
		
}
