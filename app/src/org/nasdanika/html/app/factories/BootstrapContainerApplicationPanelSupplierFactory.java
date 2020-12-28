package org.nasdanika.html.app.factories;

import java.util.List;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Feature;
import org.nasdanika.common.persistence.FeatureObjectListAttribute;
import org.nasdanika.common.persistence.FunctionSupplierFactoryAttribute;
import org.nasdanika.common.persistence.ListAttribute;
import org.nasdanika.common.persistence.ListSupplierFactoryAttribute;
import org.nasdanika.common.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.common.persistence.SupplierFactoryFeature;
import org.nasdanika.common.persistence.SupplierFactoryFeatureObject;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.ViewBuilder;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Float;

/**
 * @author Pavel
 *
 */
public class BootstrapContainerApplicationPanelSupplierFactory extends BootstrapContainerApplicationSectionSupplierFactory {

	protected SupplierFactoryFeature<List<Decorator>> width;
	
	public BootstrapContainerApplicationPanelSupplierFactory() {
		Feature<List<ColumnWidthSupplierFactory>> widthListAttribute = new FeatureObjectListAttribute<>("width", ColumnWidthSupplierFactory::new, false, false, null, null);
		width = addFeature(new ListSupplierFactoryAttribute<>(widthListAttribute , true));
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void decorate(Map<Object, Object> data, Object target, ViewGenerator viewGenerator) {
		if (width.isLoaded()) {
			for (Decorator wd: (List<Decorator>) width.get(data)) {
				wd.decorate(target, viewGenerator);
			}			
		}
	}
		
}
