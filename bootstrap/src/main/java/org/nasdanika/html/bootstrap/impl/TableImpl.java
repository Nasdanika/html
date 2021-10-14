package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Table;

class TableImpl extends RowContainerImpl<org.nasdanika.html.Table, Table> implements Table  {

	TableImpl(BootstrapFactory factory, org.nasdanika.html.Table htmlTable) {
		super(factory, htmlTable);
		htmlTable.addClass("table");
	}
	
	private TableHeader header;
	private TableBody body;
	private TableFooter footer;

	@Override
	public TableHeader header() {
		if (header == null) {
			header = new TableHeaderImpl(getFactory(), htmlElement.header());
		}
		return header;
	}
	
	@Override
	public TableBody body() {
		if (body == null) {
			body = new TableBodyImpl(getFactory(), htmlElement.body());
		}
		return body;
	}
	
	@Override
	public TableFooter footer() {
		if (footer == null) {
			footer = new TableFooterImpl(getFactory(), htmlElement.footer());
		}
		return footer;
	}

	@Override
	public Table dark() {
		return dark(true);
	}

	@Override
	public Table dark(boolean dark) {
		htmlElement.addClassConditional(dark, "table-dark");
		return this;
	}

	@Override
	public Table striped() {
		return striped(true);
	}

	@Override
	public Table striped(boolean striped) {
		htmlElement.addClassConditional(striped, "table-striped");
		return this;
	}

	@Override
	public Table bordered() {
		return bordered(true);
	}

	@Override
	public Table bordered(boolean bordered) {
		htmlElement.addClassConditional(bordered, "table-bordered");
		return this;
	}

	@Override
	public Table borderless() {
		return borderless(true);
	}

	@Override
	public Table borderless(boolean borderless) {
		htmlElement.addClassConditional(borderless, "table-borderless");
		return this;
	}

	@Override
	public Table hover() {
		return hover(true);
	}

	@Override
	public Table hover(boolean hover) {
		htmlElement.addClassConditional(hover, "table-hover");
		return this;
	}

	@Override
	public Table small() {
		return small(true);
	}

	@Override
	public Table small(boolean small) {
		htmlElement.addClassConditional(small, "table-sm");
		return this;
	}

}
