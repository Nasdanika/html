package org.nasdanika.html.app.factories;

import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.SupplierFactoryFeatureObject;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.ViewBuilder;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;

/**
 * A {@link ViewBuilder} loaded from a {@link Map}.
 * @author Pavel
 *
 */
public class FloatSupplierFactory extends SupplierFactoryFeatureObject<Decorator> {
	
	public FloatSupplierFactory() {
		
	}

	@Override
	protected Function<Map<Object, Object>, Decorator> createResultFunction(Context context) {
		return new Function<Map<Object,Object>, Decorator>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Creating appearance decorator";
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public Decorator execute(Map<Object, Object> data, ProgressMonitor progressMonitor) throws Exception {
				return (target, viewGenerator) -> {
					BootstrapElement<?,?> bootstrapElement = null;		
					HTMLElement<?> htmlElement = null;		
					if (target instanceof BootstrapElement) { 
						bootstrapElement = (BootstrapElement<?, ?>) target;
						htmlElement = bootstrapElement.toHTMLElement();
					} else if (target instanceof HTMLElement) {
						htmlElement = (HTMLElement<?>) target;
						bootstrapElement = BootstrapFactory.INSTANCE.wrap(htmlElement);						
					} 					
				};
			}
		};
	}
		
}
