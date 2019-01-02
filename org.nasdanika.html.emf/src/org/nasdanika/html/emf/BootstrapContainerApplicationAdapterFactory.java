package org.nasdanika.html.emf;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.impl.BootstrapContainerApplication;

/**
 * Adapts EObject to {@link Application}. This implementation adapts to {@link BootstrapContainerApplication}.
 * @author Pavel Vlasov
 *
 */
public class BootstrapContainerApplicationAdapterFactory extends ComposeableAdapterFactoryImpl {
	
	@Override
	public boolean isFactoryForType(Object type) {
		return Application.class == type;
	}
		
	@Override
	protected Adapter createAdapter(Notifier target) {
		return new ApplicationAdapter(createApplication(target));
	}

	protected Application createApplication(Notifier target) {
		return new BootstrapContainerApplication();
	}

}
