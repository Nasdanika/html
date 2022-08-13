package org.nasdanika.html.model.bootstrap.gen;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Tag;
import org.nasdanika.html.model.bootstrap.Alert;

public class AlertSupplierFactoryAdapter extends DivSupplierFactoryAdapter<Alert> {
	
	public AlertSupplierFactoryAdapter(Alert alert, AdapterFactory adapterFactory) {
		super(alert, adapterFactory);
	}
	
	@Override
	protected Function<org.nasdanika.html.Tag, org.nasdanika.html.Tag> createConfigureFunction(Context context) {
		
		@SuppressWarnings("resource")
		Consumer<org.nasdanika.html.Tag> alertConsumer = new Consumer<Tag>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Alert";
			}
			
			@Override
			public void execute(Tag tag, ProgressMonitor progressMonitor) {
				tag.addClass("alert");
				tag.addClass("alert-" + getTarget().getColor().code);
				tag.attribute("role", "alert");
			}
		};
				
		return super.createConfigureFunction(context).then(alertConsumer.asFunction());
	}

}
