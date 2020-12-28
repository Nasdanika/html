package org.nasdanika.html.app.factories;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.ViewBuilder;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;

/**
 * A {@link ViewBuilder} loaded from a {@link Map}.
 * @author Pavel
 *
 */
public class AppearanceSupplierFactory extends SupplierFactoryFeatureObject<Decorator> {
	
	public static final FunctionFactory<String, Color> COLOR_FROM_CODE_FACTORY = context -> Function.fromFunction(Color::fromCode, "Color from code", 1);

	
	private SupplierFactoryFeature<Color> background;
	private SupplierFactoryFeature<Map<?,?>> attributes;
	protected SupplierFactoryFeature<List<Decorator>> margin;
	protected SupplierFactoryFeature<List<Decorator>> padding;
	protected SupplierFactoryFeature<List<Decorator>> border;
	protected SupplierFactoryFeature<List<Decorator>> floatDecorator;
	protected SupplierFactoryFeature<Decorator> text;	
	
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
		
//		protected SupplierFactoryFeature<List<Decorator>> floatDecorator;
//		protected SupplierFactoryFeature<Decorator> text;
		
		// classes - in HTML element itself.
		// style
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
						for (Entry<Object, Object> ae: ((Map<Object,Object>) attributes.get(data)).entrySet()) {
							htmlElement.attribute(ae.getKey().toString(), ae.getValue());
						}
					}
					
					for (Decorator md: (List<Decorator>) margin.get(data)) {
						md.decorate(target, viewGenerator);
					}
					
					for (Decorator pd: (List<Decorator>) padding.get(data)) {
						pd.decorate(target, viewGenerator);
					}
					
					for (Decorator bd: (List<Decorator>) border.get(data)) {
						bd.decorate(target, viewGenerator);
					}
					
					for (Decorator fd: (List<Decorator>) floatDecorator.get(data)) {
						fd.decorate(target, viewGenerator);
					}
					
					if (text.isLoaded()) {
						((Decorator) text.get(data)).decorate(target, viewGenerator);
					}
					
//					class
//					style
					
				};
			}
		};
	}
	
	
//	private Appearance target;
//
//	public AppearanceSupplierFactory(Appearance target) {
//		this.target = target;
//	}
	

	
// ---	
	
//
//	private Attribute<String> id = addFeature(new Attribute<String>("id", false, false, UUID.randomUUID().toString(), null));
//	private Attribute<String> icon = addFeature(new Attribute<String>("icon", false, false, null, null));
//	private Attribute<String> text = addFeature(new Attribute<String>("text", true, false, null, null));
//	private Attribute<String> tooltip = addFeature(new Attribute<String>("tooltip", false, false, null, null));
//	private Attribute<String> color = addFeature(new Attribute<String>("color", false, false, null, null));
//	private Attribute<Boolean> outline = addFeature(new Attribute<Boolean>("outline", false, false, false, null));
//	private StringSupplierFactoryAttribute description = addFeature(new StringSupplierFactoryAttribute(new Reference("description", false, false, null, null), true));
//	private Attribute<String> notification = addFeature(new Attribute<String>("notification", false, false, null, null));
//
//	@Override
//	protected Function<Map<Object, Object>, L> createResultFunction(Context context) {			
//		return new Function<Map<Object, Object>, L>() {
//
//			@Override
//			public double size() {
//				return 1;
//			}
//
//			@Override
//			public String name() {
//				return "Creating label";
//			}
//
//			@SuppressWarnings("unchecked")
//			@Override
//			public L execute(Map<Object, Object> data, ProgressMonitor progressMonitor) throws Exception {								
//				LabelImpl ret = (LabelImpl) createLabel(context, data);
//				setData(context, data, ret);
//				return (L) ret;
//			}
//		};
//	}
//	
//	/**
//	 * Override to return ActionImpl
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	protected L createLabel(Context context, Map<Object, Object> data) {
//		return (L) new LabelImpl(createDecorator(context)); 
//	}
//	
//	protected Decorator createDecorator(Context context) {
//		return null; // TODO - Appearance.
//	}
//
//	protected void setData(Context context, Map<Object, Object> data, LabelImpl label) {
//		// TODO - interpolations where needed.
//		label.setId(data.get(id.getKey()));
//		label.setIcon((String) data.get(icon.getKey()));
//		label.setText((String) data.get(text.getKey()));
//		if (tooltip.isLoaded()) {
//			label.setTooltip((String) data.get(tooltip.getKey()));
//		}
//		if (description.isLoaded()) {
//			label.setDescription((String) data.get(description.getKey()));
//			if (!tooltip.isLoaded()) {
//				org.nasdanika.common.Util.firstPlainTextSentence(label.getDescription(), 50, 250);
//			}
//		}
//		if (color.isLoaded()) {
//			try {
//				label.setColor(Color.valueOf(context.interpolateToString((String) data.get(color.getKey()))));
//			} catch (IllegalArgumentException e) {
//				throw new ConfigurationException(e.getMessage(), e, color.getMarker());
//			}
//		}
//		
//		label.setOutline((boolean) data.get(outline.getKey()));
//		label.setNotification((String) data.get(notification.getKey()));
//	}
	
}
