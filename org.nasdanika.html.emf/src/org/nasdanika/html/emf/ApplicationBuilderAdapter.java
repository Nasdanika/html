package org.nasdanika.html.emf;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;

/**
 * Adapts EObject to {@link ApplicationBuilder}.
 * @author Pavel Vlasov
 *
 */
public class ApplicationBuilderAdapter extends AdapterImpl implements ApplicationBuilder {
	
	public ApplicationBuilderAdapter(ApplicationBuilder appBuilder) {
		this.appBuilder = appBuilder;
	}
	
	protected ApplicationBuilder appBuilder;
				
	@Override
	public boolean isAdapterForType(Object type) {
		return ApplicationBuilder.class == type;
	}
	
	@Override
	public void build(Application application) {
		appBuilder.build(application);
	}

}
