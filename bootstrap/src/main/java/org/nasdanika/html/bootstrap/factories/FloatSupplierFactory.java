package org.nasdanika.html.bootstrap.factories;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.FunctionSupplierFactoryAttribute;
import org.nasdanika.common.persistence.ListAttribute;
import org.nasdanika.common.persistence.ListSupplierFactoryAttribute;
import org.nasdanika.common.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.common.persistence.SupplierFactoryFeature;
import org.nasdanika.common.persistence.SupplierFactoryFeatureObject;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Float;

/**
 * A {@link ViewBuilder} loaded from a {@link Map}.
 * @author Pavel
 *
 */
public class FloatSupplierFactory extends SupplierFactoryFeatureObject<Consumer<Object>> {

	private SupplierFactoryFeature<List<String>> side;
	private SupplierFactoryFeature<Breakpoint> breakpoint;
	
	public FloatSupplierFactory() {
		side = addFeature(new ListSupplierFactoryAttribute<>(new ListAttribute<String>("side", true, true, null, "Float side - left, right, or none"), true));
		
		FunctionFactory<String, Breakpoint> breakpointFactory = context -> Function.fromFunction(Breakpoint::fromCode, "Breakpoint from code", 1);
		breakpoint = addFeature(new FunctionSupplierFactoryAttribute<String,Breakpoint>(new StringSupplierFactoryAttribute(new Attribute<String>("breakpoint", false, false, "", null), true), breakpointFactory));
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
				return "Creating appearance decorator";
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public Consumer<Object> execute(Map<Object, Object> data, ProgressMonitor progressMonitor) throws Exception {
				return (target) -> {
					BootstrapElement<?,?> bootstrapElement;		
					if (target instanceof BootstrapElement) { 
						bootstrapElement = (BootstrapElement<?, ?>) target;
					} else if (target instanceof HTMLElement) {
						bootstrapElement = BootstrapFactory.INSTANCE.wrap((HTMLElement<?>) target);						
					} else {
						throw new ConfigurationException("Cannot apply float to " + target, getMarker());						
					}
					Float<?> bsFloat = bootstrapElement._float();
					
					Breakpoint theBreakpoint = (Breakpoint) breakpoint.get(data); 
					for (String p: (List<String>) side.get(data)) {
						switch (p) {
						case "left":
							bsFloat.left(theBreakpoint);
							break;
						case "right":
							bsFloat.right(theBreakpoint);
							break;
						case "none":
							bsFloat.none(theBreakpoint);
							break;
						default:
							throw new ConfigurationException("Invalid float side value: " + p, side.getMarker());						
						}
					}					
				};
			}
		};
	}
		
}
