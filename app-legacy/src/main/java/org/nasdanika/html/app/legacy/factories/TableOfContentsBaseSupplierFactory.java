package org.nasdanika.html.app.factories;

import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.SupplierFactoryFeatureObject;
import org.nasdanika.html.app.viewparts.TableOfContentsBaseViewPart;

public abstract class TableOfContentsBaseSupplierFactory<T extends TableOfContentsBaseViewPart> extends SupplierFactoryFeatureObject<T> {

	protected Attribute<String> header = addFeature(new Attribute<String>("header", false, false, null, null));
	protected Attribute<String> role = addFeature(new Attribute<String>("role", false, false, "navigation", null));
	protected Attribute<Integer> depth = addFeature(new Attribute<Integer>("depth", false, false, 3, null));
	protected Attribute<Boolean> tooltip = addFeature(new Attribute<Boolean>("tooltip", false, false, false, null));

	@Override
	protected Function<Map<Object, Object>, T> createResultFunction(Context context) {			
		return new Function<Map<Object, Object>, T>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Creating table of contents";
			}

			@Override
			public T execute(Map<Object, Object> data, ProgressMonitor progressMonitor) throws Exception {								
				return createTableOfContents(context, data);
			}
		};
	}
	
	protected abstract T createTableOfContents(Context context, Map<Object, Object> data) throws Exception;
		
}
