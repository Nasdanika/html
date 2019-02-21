package org.nasdanika.cdo;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.cdo.server.CDOServerUtil;
import org.eclipse.emf.cdo.server.IRepository;
import org.eclipse.emf.cdo.server.IStore;
import org.eclipse.net4j.util.lifecycle.LifecycleUtil;
import org.osgi.service.component.ComponentContext;

public abstract class AbstractRepositoryProvider implements RepositoryProvider {
		
	protected static final String REPO_NAME_PROPERTY = ".repositoryName";
	
	private IRepository repository;
	
	protected Map<String, String> getRepositoryProperties(ComponentContext context) {
		return new HashMap<>();		
	}
	
	protected abstract IStore createStore(ComponentContext context) throws Exception;

	public void activate(ComponentContext context) throws Exception {
		Object rn = context.getProperties().get(REPO_NAME_PROPERTY);
		Object componentName = context.getProperties().get("component.name");
		String repoName = rn instanceof String ? (String) rn : String.valueOf(componentName);
		repository = CDOServerUtil.createRepository(repoName, createStore(context), getRepositoryProperties(context));
		LifecycleUtil.activate(repository);
	}

	@Override
	public IRepository getRepository() {
		return repository;
	}
	
	public void deactivate() {
		if (repository!=null) {
			LifecycleUtil.deactivate(repository);
		}
	}
	
}
