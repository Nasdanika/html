package org.nasdanika.cdo;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.emf.cdo.net4j.CDONet4jSession;
import org.eclipse.emf.cdo.net4j.CDONet4jSessionConfiguration;
import org.eclipse.emf.cdo.net4j.CDONet4jUtil;
import org.eclipse.emf.cdo.server.CDOServerUtil;
import org.eclipse.emf.cdo.server.IRepository;
import org.eclipse.emf.cdo.server.mem.MEMStoreUtil;
import org.eclipse.emf.cdo.server.net4j.CDONet4jServerUtil;
import org.eclipse.emf.cdo.session.CDOSession;
import org.eclipse.emf.cdo.session.CDOSessionProvider;
import org.eclipse.net4j.Net4jUtil;
import org.eclipse.net4j.acceptor.IAcceptor;
import org.eclipse.net4j.jvm.IJVMConnector;
import org.eclipse.net4j.jvm.JVMUtil;
import org.eclipse.net4j.tcp.TCPUtil;
import org.eclipse.net4j.util.container.ContainerUtil;
import org.eclipse.net4j.util.container.IManagedContainer;
import org.eclipse.net4j.util.lifecycle.LifecycleUtil;

/**
 * Utility class which creates a repository with memory store and opens and initializes a session.
 * @author Pavel Vlasov
 *
 */
public class MemoryCDOSessionProvider implements AutoCloseable, CDOSessionProvider {

	private static final AtomicLong COUNTER = new AtomicLong();
	private IRepository repository;
	
	private IAcceptor acceptor;
	private IManagedContainer serverContainer;	
	
	private IManagedContainer providerContainer;
	private CDONet4jSession session;

	public MemoryCDOSessionProvider(CDOSessionInitializer... cdoSessionInitializers) {
		String id = Long.toString(COUNTER.incrementAndGet(), Character.MAX_RADIX)+"_"+Long.toString(System.currentTimeMillis(), Character.MAX_RADIX);
		
		// Repository
		repository = CDOServerUtil.createRepository(
				this.getClass().getName()+"_repository_"+id, 
				MEMStoreUtil.createMEMStore(), 
				Collections.<String,String>emptyMap());
					
		LifecycleUtil.activate(repository);
		
		// Server
		serverContainer = ContainerUtil.createContainer();
		Net4jUtil.prepareContainer(serverContainer); 
		JVMUtil.prepareContainer(serverContainer);
		TCPUtil.prepareContainer(serverContainer);
		CDONet4jServerUtil.prepareContainer(serverContainer);
		
		LifecycleUtil.activate(serverContainer);
		CDOServerUtil.addRepository(serverContainer, repository);
				
		String jvmChannelDescription = "jvm_mem_acceptor_"+id;
		acceptor = JVMUtil.getAcceptor(serverContainer, jvmChannelDescription);
		LifecycleUtil.activate(acceptor);
		
		// Session provider
		providerContainer = ContainerUtil.createContainer();
		Net4jUtil.prepareContainer(providerContainer); 
		JVMUtil.prepareContainer(providerContainer);
		TCPUtil.prepareContainer(providerContainer);
		CDONet4jServerUtil.prepareContainer(providerContainer);
		
		LifecycleUtil.activate(providerContainer);
		
		IJVMConnector connector = JVMUtil.getConnector(providerContainer, jvmChannelDescription);
		LifecycleUtil.activate(connector);
			
	    // Create configuration
	    CDONet4jSessionConfiguration configuration = createNet4jSessionConfiguration();
	    		
	    configuration.setConnector(connector);
		configuration.setRepositoryName(repository.getName());
		
	    // Open session
	    session = configuration.openNet4jSession();
	    
	    for (CDOSessionInitializer initializer: cdoSessionInitializers) {
	    	initializer.init(session);
	    }
	}

	protected CDONet4jSessionConfiguration createNet4jSessionConfiguration() {
		return CDONet4jUtil.createNet4jSessionConfiguration();
	}

	@Override
	public void close() throws Exception {
		LifecycleUtil.deactivate(session);
		LifecycleUtil.deactivate(providerContainer);
		
		if (acceptor != null) {
			LifecycleUtil.deactivate(acceptor);
		}
		LifecycleUtil.deactivate(serverContainer);
		serverContainer = null;
		
		if (repository!=null) {
			LifecycleUtil.deactivate(repository);
		}		
	}

	@Override
	public CDOSession getSession() {
		return session;
	}

}
