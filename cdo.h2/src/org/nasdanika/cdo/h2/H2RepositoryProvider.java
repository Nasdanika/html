package org.nasdanika.cdo.h2;

import java.io.File;
import java.util.Enumeration;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.eclipse.emf.cdo.server.IRepository;
import org.eclipse.emf.cdo.server.db.CDODBUtil;
import org.eclipse.emf.cdo.server.db.mapping.IMappingStrategy;
import org.eclipse.net4j.db.IDBAdapter;
import org.eclipse.net4j.db.h2.H2Adapter;
import org.h2.jdbcx.JdbcDataSource;
import org.nasdanika.cdo.AbstractDatabaseRepositoryProvider;
import org.osgi.service.component.ComponentContext;

public class H2RepositoryProvider extends AbstractDatabaseRepositoryProvider {
	
	private static final String H2_URL_PROPERTY = ".h2.url";
	private static final String AUDITS_PROPERTY = ".audits";
	private static final String BRANCHES_PROPERTY = ".branches";
	private static final String REPO_PROPERTIES_PREFIX = ".repository.";
	
	@Override
	protected Map<String, String> getRepositoryProperties(ComponentContext context) {
		Map<String, String> props = super.getRepositoryProperties(context);
		if (isAudits(context)) {
			props.put(IRepository.Props.SUPPORTING_AUDITS, "true");
		}
		if (isBranches(context)) {
			props.put(IRepository.Props.SUPPORTING_BRANCHES, "true");
		}
		
		props.put(IRepository.Props.ENSURE_REFERENTIAL_INTEGRITY, "true"); // Configurable?
		
		Enumeration<?> pne = context.getProperties().keys();
		while (pne.hasMoreElements()) {
			Object pn = pne.nextElement();
			if (pn instanceof String && ((String) pn).startsWith(REPO_PROPERTIES_PREFIX)) {
				props.put(((String) pn).substring(0, REPO_PROPERTIES_PREFIX.length()), String.valueOf(context.getProperties().get(pn)));
			}
		}
		return props;
	}

	protected boolean isBranches(ComponentContext context) {
		return Boolean.TRUE.equals(context.getProperties().get(BRANCHES_PROPERTY));
	}

	protected boolean isAudits(ComponentContext context) {
		return Boolean.TRUE.equals(context.getProperties().get(AUDITS_PROPERTY));
	}
	
	private static final Pattern EXPANDER_PATTERN = Pattern.compile("\\{\\{(.+?)\\}\\}");	
	
	/**
	 * Expands tokens in the form of <code>{{token name}}</code> to their values.
	 * If a token is not found expansion is not processed.
	 * @param input
	 * @param env
	 * @return
	 */
	public static String interpolate(String input, Function<String, String> tokenSource) {
		if (tokenSource==null) {
			return input;
		}
		Matcher matcher = EXPANDER_PATTERN.matcher(input);
		StringBuilder output = new StringBuilder();
		int i = 0;
		while (matcher.find()) {
		    String token = matcher.group();
			String replacement = tokenSource.apply(token.substring(2, token.length()-2));
		    if (replacement != null) {
			    output.append(input.substring(i, matcher.start())).append(replacement);			    
			    i = matcher.end();
		    }
		}
		output.append(input.substring(i, input.length()));
		return output.toString();
	}	

	@Override
	protected DataSource createDataSource(ComponentContext context) {
		JdbcDataSource dataSource = new JdbcDataSource();
		Object componentName = context.getProperties().get("component.name");
		Object urlStr = context.getProperties().get(H2_URL_PROPERTY);
		if (urlStr==null) {
			// If URL is not specified then database is created in bundle's storage area.
			urlStr = context.getBundleContext().getDataFile("db").getAbsolutePath()+File.separator+componentName;
		} else if (urlStr instanceof String) {
			// Interpolate with system properties
			urlStr = interpolate((String) urlStr, token -> System.getProperty(token));
		}
		
		dataSource.setURL("jdbc:h2:"+urlStr);
		return dataSource;
	}
	
	@Override
	protected IMappingStrategy createMappingStrategy(ComponentContext context) {
		IMappingStrategy mappingStrategy = CDODBUtil.createHorizontalMappingStrategy(isAudits(context), isBranches(context));
		mappingStrategy.getProperties().put(IMappingStrategy.Props.QUALIFIED_NAMES, "true");
		return mappingStrategy;
	}
	
	@Override
	protected IDBAdapter getDBAdapter(ComponentContext context) {
		return new H2Adapter();
	}

	@Override
	protected String getDBAdapterName(ComponentContext context) {
		throw new UnsupportedOperationException();
	}
	
}
