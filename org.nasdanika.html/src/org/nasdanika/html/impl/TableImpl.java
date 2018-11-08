package org.nasdanika.html.impl;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Table;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;

class TableImpl extends RowContainerImpl<Table> implements Table {
	
	private class TableHeaderImpl extends RowContainerImpl<TableHeader> implements TableHeader {

		TableHeaderImpl() {
			super(TableImpl.this.getFactory(), "thead");			
		}
		
	}
	
	private class TableBodyImpl extends RowContainerImpl<TableBody> implements TableBody {

		TableBodyImpl() {
			super(TableImpl.this.getFactory(), "tbody");			
		}
		
	}
	
	private class TableFooterImpl extends RowContainerImpl<TableFooter> implements TableFooter {

		TableFooterImpl() {
			super(TableImpl.this.getFactory(), "tfoot");			
		}
		
	}
	
	TableImpl(HTMLFactory factory) {
		super(factory, "table");
		addClass("table");
	}

	private boolean responsive;

	@Override
	public Row row(Object... cells) {
		Row row = new RowImpl();
		for (Object cell: cells) {
			row.cell(cell);
		}
		content.add(row);
		return row;
	}
	
	@Override
	public String produce(int indent) {
		if (responsive) {
			return stringify(factory.div(super.produce(indent+1)), indent);
		}
		return super.produce(indent);
	}

	@Override
	public List<Row> rows() {
		List<Row> rows = new ArrayList<>();
		for (Object c: content) {
			if (c instanceof Row) {
				rows.add((Row) c);
			}
		}
		return rows;
	}

	@Override
	public Table content(Object... content) {
		for (Object c: content) {
			this.content.add(c);
		}
		return this;
	}
	
	private Tag colgroup;
	private TableHeader header;
	private TableBody body;
	private TableFooter footer;
	private Tag caption;
	
	@Override
	public Tag col() {
		if (colgroup == null) {
			colgroup = factory.tag(TagName.colgroup);
			content.add(colgroup);
		}
		Tag col = factory.tag(TagName.col);
		colgroup.content(col);
		return col;
	}

	@Override
	public TableHeader header() {
		if (header == null) {
			header = new TableHeaderImpl();
			content.add(header);
		}
		
		return header;
	}

	@Override
	public TableBody body() {
		if (body == null) {
			body = new TableBodyImpl();
			content.add(body);
		}
		
		return body;
	}

	@Override
	public TableFooter footer() {
		if (footer == null) {
			footer = new TableFooterImpl();
			content.add(footer);
		}
		
		return footer;
	}

	@Override
	public Table caption(Object... content) {
		if (caption == null) {
			caption = getFactory().nonEmptyTag(TagName.caption, content);
			content(caption);
		} else {
			caption.content(content);
		}
		
		return this;
	}

}
