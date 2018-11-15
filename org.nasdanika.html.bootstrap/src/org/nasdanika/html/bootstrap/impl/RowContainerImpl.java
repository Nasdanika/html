package org.nasdanika.html.bootstrap.impl;

import java.util.ArrayList;
import java.util.List;

import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.RowContainer;

class RowContainerImpl<T extends org.nasdanika.html.RowContainer<T>, B extends BootstrapElement<T,B>> extends WrappingBootstrapElementImpl<T,B> implements RowContainer<T,B>  {

	RowContainerImpl(BootstrapFactory factory, T htmlRowContainer) {
		super(factory, htmlRowContainer);
	}

	@Override
	public List<Row> rows() {
		List<Row> ret = new ArrayList<>();
		for (org.nasdanika.html.RowContainer.Row htmlCell: htmlElement.rows()) {
			ret.add(new RowImpl(getFactory(), htmlCell));
		}
		return ret;
	}

	@Override
	public Row row(Object... cells) {
		return new RowImpl(getFactory(), htmlElement.row(cells));
	}

	@Override
	public Row headerRow(Object... headers) {
		return new RowImpl(getFactory(), htmlElement.headerRow(headers));
	}

}
