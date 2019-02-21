package org.nasdanika.cdo;

import javax.sql.DataSource;

import org.eclipse.emf.cdo.server.IStore;
import org.eclipse.emf.cdo.server.db.CDODBUtil;
import org.eclipse.emf.cdo.server.db.mapping.IMappingStrategy;
import org.eclipse.net4j.db.DBUtil;
import org.eclipse.net4j.db.IDBAdapter;
import org.eclipse.net4j.db.IDBConnectionProvider;
import org.osgi.service.component.ComponentContext;

public abstract class AbstractDatabaseRepositoryProvider extends AbstractRepositoryProvider {
	
	protected IMappingStrategy createMappingStrategy(ComponentContext context) {
		IMappingStrategy ret = CDODBUtil.createMappingStrategy("horizontal");
		ret.getProperties().put("qualifiedNames", "true");
		return ret;
	}
	
	protected abstract DataSource createDataSource(ComponentContext context) throws Exception;

	protected String getDBAdapterName(ComponentContext context) {
		throw new UnsupportedOperationException();
	};
	
	protected IDBAdapter getDBAdapter(ComponentContext context) throws Exception {
		return DBUtil.getDBAdapter(getDBAdapterName(context));
	}
	
	protected IDBConnectionProvider createConnectionProvider(ComponentContext context) throws Exception {
		return DBUtil.createConnectionProvider(createDataSource(context));
	}

	@Override
	protected IStore createStore(ComponentContext context) throws Exception {
		return CDODBUtil.createStore(createMappingStrategy(context), getDBAdapter(context), createConnectionProvider(context));
	}

}
