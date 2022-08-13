package org.nasdanika.html.model.bootstrap.gen;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.html.bootstrap.BootstrapFactory;

public class TableSupplierFactoryAdapter extends BootstrapElementSupplierFactoryAdapter<org.nasdanika.html.model.bootstrap.Table, org.nasdanika.html.bootstrap.Table> {
	
	public TableSupplierFactoryAdapter(org.nasdanika.html.model.bootstrap.Table table, AdapterFactory adapterFactory) {
		super(table, adapterFactory);
	}

	/**
	 * Creates a table, configuration shall be performed in {@link TableCellConsumerFactoryAdapter}.
	 * @param context
	 * @return
	 */
	protected Supplier<org.nasdanika.html.HTMLElement<?>> createTableSupplier(Context context) {
		return new Supplier<org.nasdanika.html.HTMLElement<?>>() {
	
			@Override
			public double size() {
				return 1;
			}
	
			@Override
			public String name() {
				return "Bootstrap table";
			}
	
			@Override
			public org.nasdanika.html.HTMLElement<?> execute(ProgressMonitor progressMonitor) {
				return context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE).table().toHTMLElement();
			}
			
		};
	}
	
	@Override
	protected Supplier<org.nasdanika.html.bootstrap.Table> createBootstrapElementSupplier(Context context) {
		
		SupplierFactory<org.nasdanika.html.HTMLElement<?>> tableSupplierFactory = this::createTableSupplier;
		ConsumerFactory<org.nasdanika.html.HTMLElement<?>> tableConsumerFactory = new TableConsumerFactoryAdapter(getTarget(), adapterFactory);
		FunctionFactory<org.nasdanika.html.HTMLElement<?>,org.nasdanika.html.bootstrap.Table> unWrapperFactory = this::getWrapper;
		
		return tableSupplierFactory.then(tableConsumerFactory.asFunctionFactory()).then(unWrapperFactory).create(context);
	}

}
