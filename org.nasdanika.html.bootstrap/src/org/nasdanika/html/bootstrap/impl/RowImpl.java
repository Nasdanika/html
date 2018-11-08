package org.nasdanika.html.bootstrap.impl;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.RowContainer;
import org.nasdanika.html.bootstrap.RowContainer.Row;

class RowImpl extends WrappingBootstrapElementImpl<org.nasdanika.html.RowContainer.Row> implements RowContainer.Row  {

	RowImpl(BootstrapFactory factory, org.nasdanika.html.RowContainer.Row htmlRow) {
		super(factory, htmlRow);
	}

	@Override
	public Row color(Color color) {
		htmlElement.addClass("table-"+color.code);
		return this;
	}

	@Override
	public Row backgroundColor(Color color) {
		htmlElement.addClass("bg-"+color.code);
		return this;
	}

	@Override
	public Cell cell(Object... content) {
		return new CellImpl(getFactory(), htmlElement.cell(content));
	}

	@Override
	public Cell header(Object... content) {
		return new CellImpl(getFactory(), htmlElement.header(content));
	}

	@Override
	public List<Cell> cells() {
		List<Cell> ret = new ArrayList<>();
		for (org.nasdanika.html.RowContainer.Row.Cell htmlCell: htmlElement.cells()) {
			ret.add(new CellImpl(getFactory(), htmlCell));
		}
		return ret;
	}


}
