package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.bootstrap.Table.TableHeader;

class TableHeaderImpl extends RowContainerImpl<org.nasdanika.html.Table.TableHeader> implements Table.TableHeader  {

	TableHeaderImpl(BootstrapFactory factory, org.nasdanika.html.Table.TableHeader htmlHeader) {
		super(factory, htmlHeader);
	}

	@Override
	public Table.TableHeader dark() {
		return dark(true);
	}

	@Override
	public Table.TableHeader dark(boolean dark) {
		htmlElement.addClassConditional(dark, "thead-dark");
		return this;
	}

	@Override
	public TableHeader light() {
		return light(true);
	}

	@Override
	public TableHeader light(boolean light) {
		htmlElement.addClassConditional(light, "thead-light");
		return this;
	}

}
