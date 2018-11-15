package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Table;

class TableBodyImpl extends RowContainerImpl<org.nasdanika.html.Table.TableBody,Table.TableBody> implements Table.TableBody  {

	TableBodyImpl(BootstrapFactory factory, org.nasdanika.html.Table.TableBody htmlBody) {
		super(factory, htmlBody);
	}

}
