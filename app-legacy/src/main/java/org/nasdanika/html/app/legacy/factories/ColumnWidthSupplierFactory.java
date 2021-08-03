package org.nasdanika.html.app.factories;

import java.util.Map;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.FunctionSupplierFactoryAttribute;
import org.nasdanika.common.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.common.persistence.SupplierFactoryFeature;
import org.nasdanika.common.persistence.SupplierFactoryFeatureObject;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Size;

/**
 * @author Pavel
 *
 */
public class ColumnWidthSupplierFactory extends SupplierFactoryFeatureObject<Consumer<Object>> {

	private SupplierFactoryFeature<Size> size;
	private SupplierFactoryFeature<Breakpoint> breakpoint;
	
	public ColumnWidthSupplierFactory() {
		FunctionFactory<String, Size> sizeFactory = context -> Function.fromFunction(Size::fromCode, "Size from code", 1);
		size = addFeature(new FunctionSupplierFactoryAttribute<String,Size>(new StringSupplierFactoryAttribute(new Attribute<String>("size", true, true, null, null), true), sizeFactory));
		
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
				return "Creating column width decorator";
			}
			
			@Override
			public Consumer<Object> execute(Map<Object, Object> data, ProgressMonitor progressMonitor) throws Exception {
				return target -> ((Container.Row.Col) target).width((Breakpoint) breakpoint.get(data), (Size) size.get(data));
			}
		};
	}
		
}
