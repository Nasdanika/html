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
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Text;

/**
 * A {@link ViewBuilder} loaded from a {@link Map}.
 * @author Pavel
 *
 */
public class TextSupplierFactory extends SupplierFactoryFeatureObject<Consumer<Object>> {

	private SupplierFactoryFeature<Color> color;
	private SupplierFactoryFeature<List<String>> style;	
	private SupplierFactoryFeature<Text.Alignment> alignment;
	private SupplierFactoryFeature<Text.Transform> transform;
	private SupplierFactoryFeature<Text.Weight> weight;
	
	public TextSupplierFactory() {
		color = addFeature(new FunctionSupplierFactoryAttribute<String,Color>(new StringSupplierFactoryAttribute(new Attribute<String>("color", true, false, null, "Text color"), true), AppearanceSupplierFactory.COLOR_FROM_CODE_FACTORY));
		style = addFeature(new ListSupplierFactoryAttribute<>(new ListAttribute<String>("style", false, false, null, "Text style, single value or a list of: italic, monospace, nowrap, truncate"), true));		
				
		FunctionFactory<String, Text.Alignment> alignmentFactory = context -> Function.fromFunction(str -> str == null ? null : Text.Alignment.valueOf(str.toUpperCase()), "Alignment from lower case", 1);
		alignment = addFeature(new FunctionSupplierFactoryAttribute<String,Text.Alignment>(new StringSupplierFactoryAttribute(new Attribute<String>("alignment", false, false, null, null), true), alignmentFactory));
		
		FunctionFactory<String, Text.Transform> transformFactory = context -> Function.fromFunction(str -> str == null ? null : Text.Transform.valueOf(str.toUpperCase()), "Transform from lower case", 1);
		transform = addFeature(new FunctionSupplierFactoryAttribute<String,Text.Transform>(new StringSupplierFactoryAttribute(new Attribute<String>("transform", false, false, null, null), true), transformFactory));
		
		FunctionFactory<String, Text.Weight> weightFactory = context -> Function.fromFunction(str -> str == null ? null : Text.Weight.valueOf(str.toUpperCase()), "Weight from lower case", 1);
		weight = addFeature(new FunctionSupplierFactoryAttribute<String,Text.Weight>(new StringSupplierFactoryAttribute(new Attribute<String>("weight", false, false, null, null), true), weightFactory));		
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
						throw new ConfigurationException("Cannot apply text to " + target, getMarker());						
					} 	
					
					Text<?> bsText = bootstrapElement.text();
					
					if (color.isLoaded()) {
						bsText.color((Color) color.get(data));
					}
					
					for (String s: (List<String>) style.get(data)) {
						switch (s) {
						case "monospace":
							bsText.monospace();
							break;
						case "italic":
							bsText.italic();
							break;
						case "nowrap":
							bsText.nowrap();
							break;
						case "truncate":
							bsText.truncate();
							break;
						default:
							throw new ConfigurationException("Invalid text style value: " + s, style.getMarker());						
						}
					}		
					
					if (alignment.isLoaded()) {
						bsText.alignment((Text.Alignment) alignment.get(data));
					}
					
					if (transform.isLoaded()) {
						bsText.transform((Text.Transform) transform.get(data));
					}
					
					if (weight.isLoaded()) {
						bsText.weight((Text.Weight) weight.get(data));
					}
					
				};
			}
		};
	}
		
}
