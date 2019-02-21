package org.nasdanika.cdo;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.cdo.net4j.CDONet4jSession;
import org.eclipse.emf.cdo.net4j.CDONet4jSessionConfiguration;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.server.net4j.CDONet4jServerUtil;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.session.CDOSessionProvider;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.connector.IConnector;
import org.eclipse.net4j.jvm.JVMUtil;
import org.eclipse.net4j.tcp.TCPUtil;
import org.eclipse.net4j.util.container.ContainerUtil;
import org.eclipse.net4j.util.container.IManagedContainer;
import org.eclipse.net4j.util.lifecycle.LifecycleUtil;
import org.osgi.service.component.ComponentContext;

public class CDOSessionProviderComponent implements CDOSessionProvider {
	
	private static final String CONNECTOR_PROPERTY = ".connector";
	private static final String TCP_PREFIX = "tcp:";
	private static final String JVM_PREFIX = "jvm:";
	private static final String REPO_NAME_PROPERTY = ".repositoryName";	
	private static final String SIGNAL_TIMEOUT_PROPERTY = ".signalTimeout";	

//	private CDOSession session;

	private IManagedContainer container;
	private CDONet4jSession session;

	public void activate(ComponentContext context) {
		container = ContainerUtil.createContainer();
		Net4jUtil.prepareContainer(container); 
		JVMUtil.prepareContainer(container);
		TCPUtil.prepareContainer(container);
		CDONet4jServerUtil.prepareContainer(container);
		
		LifecycleUtil.activate(container);
		
		Object cp = context.getProperties().get(CONNECTOR_PROPERTY);
		if (cp instanceof String) {
			String ncs = ((String) cp).trim();
			IConnector connector = null;
			if (ncs.startsWith(TCP_PREFIX)) {
				connector = TCPUtil.getConnector(container, ncs.substring(TCP_PREFIX.length()));
				LifecycleUtil.activate(connector);
			} else if (ncs.startsWith(JVM_PREFIX)) {
				connector = JVMUtil.getConnector(container, ncs.substring(JVM_PREFIX.length()));
				LifecycleUtil.activate(connector);
			}
			
			if (connector!=null) {
			    // Create configuration
			    CDONet4jSessionConfiguration configuration = CDONet4jUtil.createNet4jSessionConfiguration();
			    Object stp = context.getProperties().get(SIGNAL_TIMEOUT_PROPERTY);
			    if (stp instanceof Long) {
			    	configuration.setSignalTimeout((Long) stp);
			    }
			    		
			    configuration.setConnector(connector);
				configuration.setRepositoryName(String.valueOf(context.getProperties().get(REPO_NAME_PROPERTY)));
				
			    // Open session
			    session = configuration.openNet4jSession();
			    
			    synchronized (sessionInitializers) {
				    for (CDOSessionInitializer initializer: sessionInitializers) {
				    	initializer.init(session);
				    }
				    sessionInitializers.clear();
			    }
			}
		}		
	}
	
	private List<CDOSessionInitializer> sessionInitializers = new ArrayList<>();
	
	public void addSessionInitializer(CDOSessionInitializer initializer) {
		if (session == null) {
			synchronized (sessionInitializers) {
				sessionInitializers.add(initializer);
			}
		} else {
			initializer.init(session);
		}
	}
	
	public void removeSessionInitializer(CDOSessionInitializer initializer) {
		synchronized (sessionInitializers) {
			sessionInitializers.remove(initializer);
		}
	}
	
	@Override
	public CDOSession getSession() {
		return session;
	}
	
	public void deactivate() {
		LifecycleUtil.deactivate(session);
		LifecycleUtil.deactivate(container);
	}

}
