package org.nasdanika.html.impl;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.RowContainer;
import org.nasdanika.html.Table;

class TableImpl extends RowContainerImpl<Table> implements Table {
	
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
	public Table bordered(boolean bordered) {
		if (bordered) {
			addClass("table-bordered");
		} else {
			removeClass("table-bordered");
		}
		return this;
	}

	@Override
	public Table hover(boolean hover) {
		if (hover) {
			addClass("table-hover");
		} else {
			removeClass("table-hover");
		}
		return this;
	}

	@Override
	public Table striped(boolean striped) {
		if (striped) {
			addClass("table-striped");
		} else {
			removeClass("table-striped");
		}
		return this;
	}

	@Override
	public Table condensed(boolean condensed) {
		if (condensed) {
			addClass("table-condensed");
		} else {
			removeClass("table-condensed");
		}
		return this;
	}

	@Override
	public Table responsive(boolean responsive) {
		this.responsive = responsive;
		return this;
	}

	@Override
	public Table bordered() {
		return bordered(true);
	}

	@Override
	public Table hover() {
		return hover(true);
	}

	@Override
	public Table striped() {
		return striped(true);
	}

	@Override
	public Table condensed() {
		return condensed(true);
	}

	@Override
	public Table responsive() {
		return responsive(true);
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
	
	private RowContainer<?> header;
	private RowContainer<?> body;
	private RowContainer<?> footer;	

	@Override
	public RowContainer<?> header() {
		if (header == null) {
			header = new RowContainerImpl<>(factory, "thead");
			content.add(header);
		}
		
		return header;
	}

	@Override
	public RowContainer<?> body() {
		if (body == null) {
			body = new RowContainerImpl<>(factory, "tbody");
			content.add(body);
		}
		
		return body;
	}

	@Override
	public RowContainer<?> footer() {
		if (footer == null) {
			footer = new RowContainerImpl<>(factory, "tfoot");
			content.add(footer);
		}
		
		return footer;
	}

}
