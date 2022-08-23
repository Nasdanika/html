package org.nasdanika.html.model.app.drawio;

import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.graph.processor.ProcessorConfig;

public class ConnectionProcessor extends ElementProcessor {
	
	public ConnectionProcessor(ResourceFactory resourceFactory, ProcessorConfig<ElementProcessor> config) {
		super(resourceFactory, config);
	}
	
	public EReference getConnectionRole(Connection connection) {
		String connectionRoleProperty = resourceFactory.getConnectionRoleProperty();		
		if (!Util.isBlank(connectionRoleProperty)) {
			String roleName = connection.getProperty(connectionRoleProperty);
			if (!Util.isBlank(roleName)) {
				return resourceFactory.resolveConnectionRole(roleName);
			}
		}
		
		return null;
	}		

}
