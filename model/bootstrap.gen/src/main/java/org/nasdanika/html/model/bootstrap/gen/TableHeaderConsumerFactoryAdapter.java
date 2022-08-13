package org.nasdanika.html.model.bootstrap.gen;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.HTMLElement;

/**
 * Creates a respective section in the table based on the containment reference - header, body, footer.
 * @author Pavel
 *
 */
public class TableHeaderConsumerFactoryAdapter extends TableSectionConsumerFactoryAdapter<org.nasdanika.html.model.bootstrap.TableHeader, org.nasdanika.html.bootstrap.Table.TableHeader> {

	public TableHeaderConsumerFactoryAdapter(org.nasdanika.html.model.bootstrap.TableHeader tableHeader, AdapterFactory adapterFactory) {
		super(tableHeader, adapterFactory);
	}
	
	@Override
	protected Function<HTMLElement<?>, HTMLElement<?>> createConfigureFunction(Context context) {
		@SuppressWarnings("resource")
		Consumer<org.nasdanika.html.bootstrap.Table.TableHeader> configurator = new Consumer<org.nasdanika.html.bootstrap.Table.TableHeader>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Table header dark, light";
			}
			
			@Override
			public void execute(org.nasdanika.html.bootstrap.Table.TableHeader header, ProgressMonitor progressMonitor) {
				org.nasdanika.html.model.bootstrap.TableHeader semanticElement = getTarget();
				header.light(semanticElement.isLight());
				header.dark(semanticElement.isDark());
			}
		};
			
		Function<HTMLElement<?>, HTMLElement<?>> headerConfigureFunction = getWrapper(context).then(configurator.asFunction()).then(toHTMLElement(context));
		return super.createConfigureFunction(context).then(headerConfigureFunction);
	}
	
	
	
	// TODO - dark, light
	
	
	
}
