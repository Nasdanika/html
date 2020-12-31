package org.nasdanika.html.app.factories;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.DelegatingSupplierFactoryFeature;
import org.nasdanika.common.persistence.FeatureObjectAttribute;
import org.nasdanika.common.persistence.FunctionSupplierFactoryAttribute;
import org.nasdanika.common.persistence.ListAttribute;
import org.nasdanika.common.persistence.ListSupplierFactoryAttribute;
import org.nasdanika.common.persistence.ReferenceList;
import org.nasdanika.common.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.common.persistence.SupplierFactoryFeature;
import org.nasdanika.common.persistence.SupplierFactoryFeatureObject;
import org.nasdanika.html.Container;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.ViewBuilder;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Float;
import org.nasdanika.html.bootstrap.factories.AppearanceSupplierFactory;

/**
 * @author Pavel
 *
 */
public class BootstrapContainerApplicationSectionSupplierFactory extends SupplierFactoryFeatureObject<Consumer<Object>> {

	protected SupplierFactoryFeature<Consumer<Object>> appearance;
	private SupplierFactoryFeature<List<Object>> content;
	
	public BootstrapContainerApplicationSectionSupplierFactory() {
		appearance = addFeature(new DelegatingSupplierFactoryFeature<Consumer<Object>>(new FeatureObjectAttribute<AppearanceSupplierFactory>("appearance", AppearanceSupplierFactory::new, false, false, null, "Appearance"))); 
		content = addFeature(new ListSupplierFactoryAttribute<>(new ReferenceList<>("content", false, false, null, null), true));
	}

	@Override
	protected Function<Map<Object, Object>, Consumer<Object>> createResultFunction(Context context) {
		return new Function<Map<Object,Object>, Consumer<Object>>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Creating section view builder";
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public Consumer<Object> execute(Map<Object, Object> data, ProgressMonitor progressMonitor) throws Exception {
				return target -> {
					if (appearance.isLoaded()) {
						((Consumer<Object>) appearance.get(data)).accept(target);
					}
					if (content.isLoaded()) {
						Object theTarget = target;
						if (theTarget instanceof BootstrapElement) { 
							theTarget = ((BootstrapElement<?, ?>) theTarget).toHTMLElement();
						} 
						
						if (!(theTarget instanceof Consumer)) {
							throw new ConfigurationException("Cannot add content to " + theTarget, getMarker());						
						} 	
						
						Consumer<Object> consumer = (Consumer<Object>) theTarget;
						
						for (Object ce: (List<Object>) data.get(content.getKey())) {
							if (ce instanceof InputStream) {
								try {
									consumer.accept(DefaultConverter.INSTANCE.toString((InputStream) ce));
								} catch (IOException e) {
									throw new ConfigurationException(e.getMessage(), e, content.getMarker());
								}
							} else {
								consumer.accept(ce);
							}
						}
					}	
					decorate(data, target);
				};
			}
		};
	}
	
	protected void decorate(Map<Object, Object> data, Object target) {}	
		
}
