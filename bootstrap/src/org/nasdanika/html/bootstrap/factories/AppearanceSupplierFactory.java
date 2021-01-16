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
import org.nasdanika.common.persistence.DelegatingSupplierFactoryFeature;
import org.nasdanika.common.persistence.Feature;
import org.nasdanika.common.persistence.FeatureObjectAttribute;
import org.nasdanika.common.persistence.FeatureObjectListAttribute;
import org.nasdanika.common.persistence.FunctionSupplierFactoryAttribute;
import org.nasdanika.common.persistence.InterpolatedMapSupplierFactoryAttribute;
import org.nasdanika.common.persistence.ListSupplierFactoryAttribute;
import org.nasdanika.common.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.common.persistence.SupplierFactoryFeature;
import org.nasdanika.common.persistence.SupplierFactoryFeatureObject;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;

/**
 * A {@link ViewBuilder} loaded from a {@link Map}.
 * @author Pavel
 *
 */
public class AppearanceSupplierFactory extends SupplierFactoryFeatureObject<Consumer<Object>> {
	
	public static final FunctionFactory<String, Color> COLOR_FROM_CODE_FACTORY = context -> Function.fromFunction(Color::fromCode, "Color from code", 1);
	
	private SupplierFactoryFeature<Color> background;
	private SupplierFactoryFeature<Map<?,?>> attributes;
	protected SupplierFactoryFeature<List<Consumer<Object>>> margin;
	protected SupplierFactoryFeature<List<Consumer<Object>>> padding;
	protected SupplierFactoryFeature<List<Consumer<Object>>> border;
	protected SupplierFactoryFeature<List<Consumer<Object>>> floatDecorator;
	protected SupplierFactoryFeature<Consumer<Object>> text;	
	
	public AppearanceSupplierFactory() {
		background = addFeature(new FunctionSupplierFactoryAttribute<String,Color>(new StringSupplierFactoryAttribute(new Attribute<String>("background", true, false, null, null), true), COLOR_FROM_CODE_FACTORY));

		attributes =  addFeature(
				new InterpolatedMapSupplierFactoryAttribute(
						new DelegatingSupplierFactoryFeature<>(				
								new Attribute<>("attributes", false, false, null, "A map of HTML element attributes"))));
		
		Feature<List<SpacingSupplierFactory>> marginListAttribute = new FeatureObjectListAttribute<>("margin", () -> new SpacingSupplierFactory(BootstrapElement::margin), false, false, null, null);
		margin = addFeature(new ListSupplierFactoryAttribute<>(marginListAttribute , true));

		Feature<List<SpacingSupplierFactory>> paddingListAttribute = new FeatureObjectListAttribute<>("padding", () -> new SpacingSupplierFactory(BootstrapElement::padding), false, false, null, null);
		padding = addFeature(new ListSupplierFactoryAttribute<>(paddingListAttribute , true));

		Feature<List<BorderSupplierFactory>> borderListAttribute = new FeatureObjectListAttribute<>("border", BorderSupplierFactory::new, false, false, null, null);
		border = addFeature(new ListSupplierFactoryAttribute<>(borderListAttribute , true));

		Feature<List<FloatSupplierFactory>> floatListAttribute = new FeatureObjectListAttribute<>("float", FloatSupplierFactory::new, false, false, null, null);
		floatDecorator = addFeature(new ListSupplierFactoryAttribute<>(floatListAttribute , true));

		text = addFeature(new DelegatingSupplierFactoryFeature<>(new FeatureObjectAttribute<>("text", TextSupplierFactory::new, false, false, null, null)));
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
					BootstrapElement<?,?> bootstrapElement = null;		
					HTMLElement<?> htmlElement = null;		
					if (target instanceof BootstrapElement) { 
						bootstrapElement = (BootstrapElement<?, ?>) target;
						htmlElement = bootstrapElement.toHTMLElement();
					} else if (target instanceof HTMLElement) {
						htmlElement = (HTMLElement<?>) target;
						bootstrapElement = BootstrapFactory.INSTANCE.wrap(htmlElement);						
					} 
					if (background.isLoaded()) {
						if (bootstrapElement == null) {
							throw new ConfigurationException("Cannot apply background to " + target, getMarker());
						}
						bootstrapElement.background((Color) background.get(data));
					}					
					if (attributes.isLoaded()) {
						if (htmlElement == null) {
							throw new ConfigurationException("Cannot apply attributes to " + target, getMarker());
						}
						htmlElement.attributes((Map<?,?>) attributes.get(data));
					}
					
					for (Consumer<Object> md: (List<Consumer<Object>>) margin.get(data)) {
						md.accept(target);
					}
					
					for (Consumer<Object> pd: (List<Consumer<Object>>) padding.get(data)) {
						pd.accept(target);
					}
					
					for (Consumer<Object> bd: (List<Consumer<Object>>) border.get(data)) {
						bd.accept(target);
					}
					
					for (Consumer<Object> fd: (List<Consumer<Object>>) floatDecorator.get(data)) {
						fd.accept(target);
					}
					
					if (text.isLoaded()) {
						((Consumer<Object>) text.get(data)).accept(target);
					}
					
				};
			}
		};
	}
	
}
