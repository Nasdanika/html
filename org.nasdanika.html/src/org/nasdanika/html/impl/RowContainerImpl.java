package org.nasdanika.html.impl;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.html.Bootstrap;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.RowContainer;

class RowContainerImpl<T extends RowContainer<T>> extends UIElementImpl<T> implements RowContainer<T> {

	RowContainerImpl(HTMLFactory factory, String tagName) {
		super(factory, tagName);
	}

	class RowImpl extends UIElementImpl<Row> implements Row {
		
		RowImpl() {
			super(RowContainerImpl.this.factory, "tr");
		}
		
		class CellImpl extends UIElementImpl<Cell> implements Cell {
			
			CellImpl(boolean isHeader, Object... content) {
				super(RowContainerImpl.this.factory, isHeader ? "th" : "td");
				content(content);
			}

			@Override
			public Cell colspan(int colspan) {
				return attribute("colspan", String.valueOf(colspan));
			}

			@Override
			public Cell rowspan(int rowspan) {
				return attribute("rowspan", String.valueOf(rowspan));
			}

			@Override
			public Cell content(Object... content) {
				for (Object c: content) {
					this.content.add(c);
				}
				return this;
			}
			
		}

		@Override
		public Cell cell(Object... content) {
			CellImpl cell = new CellImpl(false, content);
			this.content.add(cell);
			return cell;
		}

		@Override
		public Cell header(Object... content) {
			CellImpl cell = new CellImpl(true, content);
			this.content.add(cell);
			return cell;
		}

		@Override
		public Row style(org.nasdanika.html.Bootstrap.Style style) {
			return attribute("class", Bootstrap.Style.PRIMARY.equals(style) ? "active" : style.name().toLowerCase());
		}
		
		@Override
		public void close() throws Exception {
			super.close();
			for (Object c: content) {
				close(c);
			}			
		}

		@Override
		public List<Cell> cells() {
			List<Cell> cells = new ArrayList<>();
			for (Object c: content) {
				if (c instanceof Cell) {
					cells.add((Cell) c);
				}
			}
			return cells;
		}

		@Override
		public Row content(Object... content) {
			for (Object c: content) {
				this.content.add(c);
			}
			return this;
		}
		
	}

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
	public Row headerRow(Object... headers) {
		Row row = new RowImpl();
		for (Object header: headers) {
			row.header(header);
		}
		content.add(row);
		return row;
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

	@SuppressWarnings("unchecked")
	@Override
	public T content(Object... content) {
		for (Object c: content) {
			this.content.add(c);
		}
		return (T) this;
	}

}
