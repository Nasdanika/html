package org.nasdanika.cdo;

import org.eclipse.emf.cdo.server.IStore;
import org.eclipse.emf.cdo.server.mem.MEMStoreUtil;
import org.osgi.service.component.ComponentContext;

public class MemoryRepositoryProvider extends AbstractRepositoryProvider {

	@Override
	protected IStore createStore(ComponentContext context) {
		return MEMStoreUtil.createMEMStore();
	}

}
