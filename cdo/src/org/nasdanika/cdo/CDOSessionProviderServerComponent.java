package org.nasdanika.cdo;

import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.session.CDOSessionProvider;
import org.osgi.service.component.ComponentContext;

/**
 * Server and client components combined. Client is activated after the server. 
 * 
 * @author Pavel Vlasov
 *
 */
public class CDOSessionProviderServerComponent extends ServerComponent implements CDOSessionProvider {
	
	private CDOSessionProviderComponent sessionProvider = new CDOSessionProviderComponent();


	@Override
	public void activate(ComponentContext context) {
		super.activate(context);
		sessionProvider.activate(context);		
	}
	
	@Override
	public CDOSession getSession() {
		return sessionProvider.getSession();
	}
	
	@Override
	public void deactivate() {
		sessionProvider.deactivate();
		super.deactivate();
	}
	
	public void addSessionInitializer(CDOSessionInitializer initializer) {
		sessionProvider.addSessionInitializer(initializer);
	}
	
	public void removeSessionInitializer(CDOSessionInitializer initializer) {
		sessionProvider.removeSessionInitializer(initializer);
	}		

}
