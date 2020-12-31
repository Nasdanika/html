package org.nasdanika.html.factories;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.ListSupplierFactoryAttribute;
import org.nasdanika.common.persistence.ReferenceList;
import org.nasdanika.common.persistence.SupplierFactoryFeature;
import org.nasdanika.common.persistence.SupplierFactoryFeatureObject;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;

public class HTMLPageSupplierFactory extends SupplierFactoryFeatureObject<HTMLPage> {
	
	private SupplierFactoryFeature<List<Object>> head;
	private SupplierFactoryFeature<List<Object>> body;

	public HTMLPageSupplierFactory() {
		body = addFeature(new ListSupplierFactoryAttribute<>(new ReferenceList<>("body", true, false, null, null), true));
		head = addFeature(new ListSupplierFactoryAttribute<>(new ReferenceList<>("head", false, false, null, null), true));
	}

	@Override
	protected Function<Map<Object, Object>, HTMLPage> createResultFunction(Context context) {
		return new Function<Map<Object,Object>, HTMLPage>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Creating HTML Page application";
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public HTMLPage execute(Map<Object, Object> data, ProgressMonitor progressMonitor) throws Exception {
				HTMLFactory factory = context.get(HTMLFactory.class, HTMLFactory.INSTANCE);
				HTMLPage page = configure(context, data, factory.page(), progressMonitor);
				if (head.isLoaded()) {
					for (Object ce: (List<Object>) head.get(data)) {
						if (ce instanceof InputStream) {
							try {
								page.head(DefaultConverter.INSTANCE.toString((InputStream) ce));
							} catch (IOException e) {
								throw new ConfigurationException(e.getMessage(), e, head.getMarker());
							}
						} else {
							page.head(ce);
						}
					}
					
				}
				if (body.isLoaded()) {
					for (Object ce: (List<Object>) body.get(data)) {
						if (ce instanceof InputStream) {
							try {
								page.body(DefaultConverter.INSTANCE.toString((InputStream) ce));
							} catch (IOException e) {
								throw new ConfigurationException(e.getMessage(), e, body.getMarker());
							}
						} else {
							page.body(ce);
						}
					}
					
				}
				return page;
			}
		};
	}
	
	protected HTMLPage configure(Context context, Map<Object, Object> data, HTMLPage page, ProgressMonitor progressMonitor) {
		return page;
	}

}
