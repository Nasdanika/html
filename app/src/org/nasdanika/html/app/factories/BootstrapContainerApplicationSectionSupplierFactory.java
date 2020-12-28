package org.nasdanika.html.app.factories;

import java.util.List;
import java.util.Map;

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
public class BootstrapContainerApplicationSectionSupplierFactory extends SupplierFactoryFeatureObject<ViewBuilder> {

	private SupplierFactoryFeature<List<String>> side;
	private SupplierFactoryFeature<Breakpoint> breakpoint;
	
	public BootstrapContainerApplicationSectionSupplierFactory() {
		side = addFeature(new ListSupplierFactoryAttribute<>(new ListAttribute<String>("side", true, true, null, "Float side - left, right, or none"), true));
		
		FunctionFactory<String, Breakpoint> breakpointFactory = context -> Function.fromFunction(Breakpoint::fromCode, "Breakpoint from code", 1);
		breakpoint = addFeature(new FunctionSupplierFactoryAttribute<String,Breakpoint>(new StringSupplierFactoryAttribute(new Attribute<String>("breakpoint", false, false, "", null), true), breakpointFactory));
	}

	@Override
	protected Function<Map<Object, Object>, ViewBuilder> createResultFunction(Context context) {
		return new Function<Map<Object,Object>, ViewBuilder>() {
			
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
			public ViewBuilder execute(Map<Object, Object> data, ProgressMonitor progressMonitor) throws Exception {
				return (target, viewGenerator, monitor) -> {
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
					
					BootstrapContainerApplicationSectionSupplierFactory.this.decorate(data, target, viewGenerator);
				};
			}
		};
	}
	
	protected void decorate(Map<Object, Object> data, Object target, ViewGenerator viewGenerator) {}	
		
}
