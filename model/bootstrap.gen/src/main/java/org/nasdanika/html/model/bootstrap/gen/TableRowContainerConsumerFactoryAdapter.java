package org.nasdanika.html.model.bootstrap.gen;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.nasdanika.common.CollectionCompoundConsumerFactory;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.model.bootstrap.TableRow;

public class TableRowContainerConsumerFactoryAdapter<M extends org.nasdanika.html.model.bootstrap.TableRowContainer, T extends org.nasdanika.html.bootstrap.RowContainer<?,?>> extends BootstrapElementConsumerFactoryAdapter<M,T> {

	public TableRowContainerConsumerFactoryAdapter(M tableRowContainer, AdapterFactory adapterFactory) {
		super(tableRowContainer, adapterFactory);
	}
	
	@Override
	protected Function<HTMLElement<?>, HTMLElement<?>> createConfigureFunction(Context context) {
		EList<TableRow> rows = getTarget().getRows();
		if (rows.isEmpty()) {
			return super.createConfigureFunction(context);
		}
	
		@SuppressWarnings("rawtypes")
		List<ConsumerFactory<HTMLElement>> rowConsumers = EObjectAdaptable.adaptToConsumerFactoryNonNull(rows, org.nasdanika.html.HTMLElement.class);
		CollectionCompoundConsumerFactory<HTMLElement<?>> rowsConsumerFactory = new CollectionCompoundConsumerFactory<>("Creating rows", rowConsumers);			
		return super.createConfigureFunction(context).then(rowsConsumerFactory.asFunctionFactory().create(context));
	}

}
