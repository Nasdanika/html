package org.nasdanika.html.app.factories;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.DelegatingSupplierFactoryFeature;
import org.nasdanika.common.persistence.EnumSupplierFactoryAttribute;
import org.nasdanika.common.persistence.Feature;
import org.nasdanika.common.persistence.FeatureObjectListAttribute;
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
	
	private SupplierFactoryFeature<Color> background;
	private SupplierFactoryFeature<Map<?,?>> attributes;
	protected SupplierFactoryFeature<List<Decorator>> margin;
	protected SupplierFactoryFeature<List<Decorator>> padding;
	protected SupplierFactoryFeature<List<Decorator>> border;
	protected SupplierFactoryFeature<List<Decorator>> floatDecorator;
	protected SupplierFactoryFeature<Decorator> text;	
	
//	private EnumAttribute<Color> background = addFeature(new EnumAttribute<Color>("background", Color.class, true, false, null, "Bootstrap background color"));
	
	public AppearanceSupplierFactory() {
		background = addFeature(new EnumSupplierFactoryAttribute<Color>(new StringSupplierFactoryAttribute(new Attribute<String>("background", true, false, null, null), true), Color.class, null));
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
					
//					floatDecorator;
//					text;
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
//	
//	@Override
//	public Supplier<ViewBuilder> create(Context context) throws Exception {
//
//		ViewBuilder spacingBuilder = new ViewBuilder() {
//			
//			@Override
//			public void build(Object target, ViewGenerator viewGenerator, ProgressMonitor monitor) {
//				org.nasdanika.html.bootstrap.BootstrapElement<?,?> bootstrapElement = (org.nasdanika.html.bootstrap.BootstrapElement<?,?>) target;
//				
//			}
//			
//		};
//		
//		ViewBuilder textBuilder = new ViewBuilder() {
//			
//			@Override
//			public void build(Object target, ViewGenerator viewGenerator, ProgressMonitor monitor) {
//				org.nasdanika.html.bootstrap.BootstrapElement<?,?> bootstrapElement = (org.nasdanika.html.bootstrap.BootstrapElement<?,?>) target;
//				Text text = AppearanceSupplierFactory.this.target.getText();
//				if (text != null) {
//					org.nasdanika.html.bootstrap.Text<?> bsText = bootstrapElement.text();
//
//					String colorStr = text.getColor();
//					if (!Util.isBlank(colorStr)) {						
//						bsText.color(org.nasdanika.html.bootstrap.Color.fromLabel(colorStr));
//					}
//					
//					String alignmentStr = text.getAlignment();
//					if (!Util.isBlank(alignmentStr)) {						
//						bsText.alignment(org.nasdanika.html.bootstrap.Text.Alignment.valueOf(alignmentStr.toUpperCase()));
//					}				
//
//					String transformStr = text.getTransform();
//					if (!Util.isBlank(transformStr)) {						
//						bsText.transform(org.nasdanika.html.bootstrap.Text.Transform.valueOf(transformStr.toUpperCase()));
//					}				
//
//					String weightStr = text.getWeight();
//					if (!Util.isBlank(weightStr)) {						
//						bsText.weight(org.nasdanika.html.bootstrap.Text.Weight.valueOf(weightStr.toUpperCase()));
//					}				
//					
//					bsText.monospace(text.isMonospace());
//					bsText.italic(text.isItalic());
//					bsText.nowrap(text.isNowrap());
//					bsText.truncate(text.isTruncate());
//					
//				}
//			}
//			
//		};
//				
//		ViewBuilder floatBuilder = new ViewBuilder() {
//			
//			@Override
//			public void build(Object target, ViewGenerator viewGenerator, ProgressMonitor monitor) {
//				org.nasdanika.html.bootstrap.BootstrapElement<?,?> bootstrapElement = (org.nasdanika.html.bootstrap.BootstrapElement<?,?>) target;
//				for (Float _float: AppearanceSupplierFactory.this.target.getFloat()) {
//					org.nasdanika.html.bootstrap.Float<?> bsFloat = bootstrapElement._float();
//					Breakpoint breakpoint = Breakpoint.fromLabel(_float.getBreakpoint());
//					switch (_float.getSide()) {
//					case "Left":
//						bsFloat.left(breakpoint);
//						break;
//					case "Right":
//						bsFloat.right(breakpoint);
//						break;
//					case "None":
//						bsFloat.none(breakpoint);
//						break;
//					default:
//						throw new IllegalArgumentException("Invalid float side: "+_float.getSide()+", shall be Left, Right, or None");
//					}
//				}
//			}
//			
//		};
//		
//		java.util.function.Function<Object, Object> wrapper = target -> {
//			if (target instanceof org.nasdanika.html.bootstrap.BootstrapElement) {
//				return (org.nasdanika.html.bootstrap.BootstrapElement<?,?>) target;
//			} 
//			if (target instanceof HTMLElement) {
//				return BootstrapFactory.INSTANCE.wrap((HTMLElement<?>) target);
//			} 
//			return BootstrapFactory.INSTANCE.wrap(HTMLFactory.INSTANCE.span(target));									
//		};
//		
//		ViewBuilder composedBuilder = backgroundBuilder
//				.compose(borderBuilder)
//				.compose(spacingBuilder)
//				.compose(textBuilder)
//				.compose(floatBuilder).before(wrapper);
//
//		Supplier<ViewBuilder> appearanceSupplier = Supplier.from(composedBuilder, "Appearance");
//		
//		if (AppearanceSupplierFactory.this.target.getAttributes().isEmpty()) {		
//			return appearanceSupplier;
//		}
//		
//		@SuppressWarnings("resource")
//		MapCompoundSupplier<String,Object> attrsSupplier = new MapCompoundSupplier<>("Attributes");
//		
//		for (AbstractEntry e: AppearanceSupplierFactory.this.target.getAttributes()) {
//			if (e.isEnabled()) {
//				attrsSupplier.put(e.getName(), EObjectAdaptable.adaptToSupplierFactory(e, Object.class).create(context));
//			}
//		}
//		
//		return appearanceSupplier.then(attrsSupplier.asFunction()).then(bs -> new ViewBuilder() {
//
//			@Override
//			public void build(Object target, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
//				bs.getFirst().build(target, viewGenerator, progressMonitor);
//				org.nasdanika.html.HTMLElement<?> htmlElement;
//				if (target instanceof org.nasdanika.html.bootstrap.BootstrapElement<?,?>) {
//					htmlElement = ((org.nasdanika.html.bootstrap.BootstrapElement<?,?>) target).toHTMLElement();  
//				} else if (target instanceof org.nasdanika.html.HTMLElement) {
//					htmlElement = (org.nasdanika.html.HTMLElement<?>) target;
//				} else {
//					htmlElement = target == null || target instanceof String && Util.isBlank((String) target) ? null : viewGenerator.get(HTMLFactory.class).span(target);
//				}
//				if (htmlElement != null) {
//					htmlElement.attributes(bs.getSecond());
//				}
//			}
//			
//		});
//				
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
