package org.nasdanika.html.model.bootstrap.gen;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.nasdanika.common.CollectionCompoundConsumerFactory;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.bootstrap.RowContainer;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.model.bootstrap.TableCell;
import org.nasdanika.html.model.bootstrap.TableRow;

/**
 * Adds a row to the consumed {@link RowContainer}
 * @author Pavel
 *
 */
public class TableRowConsumerFactoryAdapter extends BootstrapElementConsumerFactoryAdapter<org.nasdanika.html.model.bootstrap.TableRow, org.nasdanika.html.bootstrap.RowContainer<?,?>> {

	public TableRowConsumerFactoryAdapter(org.nasdanika.html.model.bootstrap.TableRow tableRow, AdapterFactory adapterFactory) {
		super(tableRow, adapterFactory);
	}
	
	@Override
	protected Function<HTMLElement<?>, HTMLElement<?>> createConfigureFunction(Context context) throws Exception {
		EList<TableCell> cells = getTarget().getCells();	
		@SuppressWarnings("rawtypes")
		List<ConsumerFactory<HTMLElement>> cellConsumers = EObjectAdaptable.adaptToConsumerFactoryNonNull(cells, org.nasdanika.html.HTMLElement.class);
		CollectionCompoundConsumerFactory<HTMLElement<?>> rowsConsumerFactory = new CollectionCompoundConsumerFactory<>("Creating cells", cellConsumers);
		
		return getWrapper(context)
				.then(createTableRowFunction(context))
				.then(toHTMLElement(context))
				.then(super.createConfigureFunction(context))
				.then(rowsConsumerFactory.asFunctionFactory().create(context));
	}
	
	/**
	 * Takes {@link RowContainer}, creates a row, configures and returns for passing to cell consumers. 
	 * @param context
	 * @return Row instance to pass to cell consumers.
	 */
	protected Function<org.nasdanika.html.bootstrap.RowContainer<?,?>, org.nasdanika.html.bootstrap.RowContainer.Row> createTableRowFunction(Context context) {
		return new Function<org.nasdanika.html.bootstrap.RowContainer<?,?>, org.nasdanika.html.bootstrap.RowContainer.Row>() {
	
			@Override
			public double size() {
				return 1;
			}
	
			@Override
			public String name() {
				return "Table row";
			}

			@Override
			public org.nasdanika.html.bootstrap.RowContainer.Row execute(org.nasdanika.html.bootstrap.RowContainer<?,?> rowContainer, ProgressMonitor progressMonitor) throws Exception {
				TableRow semanticElement = getTarget();
				Row ret = rowContainer.row();
				ret.color(semanticElement.getColor());
				ret.backgroundColor(semanticElement.getBackground());
				return ret;
			}
			
		};
	}	

}
