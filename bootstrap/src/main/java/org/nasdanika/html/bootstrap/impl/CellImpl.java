package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.RowContainer;
import org.nasdanika.html.bootstrap.RowContainer.Row.Cell;

class CellImpl extends WrappingBootstrapElementImpl<org.nasdanika.html.RowContainer.Row.Cell,Cell> implements RowContainer.Row.Cell  {
	
	CellImpl(BootstrapFactory factory, org.nasdanika.html.RowContainer.Row.Cell htmlCell) {
		super(factory, htmlCell);
	}

	@Override
	public Cell color(Color color) {
		htmlElement.addClassConditional(color != null && color.code != null, "table-"+color.code);
		return this;
	}

	@Override
	public Cell backgroundColor(Color color) {
		htmlElement.addClassConditional(color != null && color.code != null, "bg-"+color.code);
		return this;
	}

}
