package org.nasdanika.html.app.factories;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.nasdanika.common.persistence.Feature;
import org.nasdanika.common.persistence.FeatureObjectListAttribute;
import org.nasdanika.common.persistence.ListSupplierFactoryAttribute;
import org.nasdanika.common.persistence.SupplierFactoryFeature;

/**
 * @author Pavel
 *
 */
public class BootstrapContainerApplicationPanelSupplierFactory extends BootstrapContainerApplicationSectionSupplierFactory {

	protected SupplierFactoryFeature<List<Consumer<Object>>> width;
	
	public BootstrapContainerApplicationPanelSupplierFactory() {
		Feature<List<ColumnWidthSupplierFactory>> widthListAttribute = new FeatureObjectListAttribute<>("width", ColumnWidthSupplierFactory::new, false, false, null, null);
		width = addFeature(new ListSupplierFactoryAttribute<>(widthListAttribute , true));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void decorate(Map<Object, Object> data, Object target) {
		if (width.isLoaded()) {
			for (Consumer<Object> wc: (List<Consumer<Object>>) width.get(data)) {
				wc.accept(target);
			}			
		}
	}
		
}
