package org.nasdanika.html.model.bootstrap.gen;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Placement;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.Text.Alignment;
import org.nasdanika.html.bootstrap.Text.Transform;
import org.nasdanika.html.bootstrap.Text.Weight;
import org.nasdanika.html.model.bootstrap.Appearance;
import org.nasdanika.html.model.bootstrap.Border;
import org.nasdanika.html.model.bootstrap.Float;
import org.nasdanika.html.model.bootstrap.Spacing;
import org.nasdanika.html.model.bootstrap.Text;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.html.model.html.gen.HtmlElementAdapter;

public class AppearanceConsumerFactoryAdapter extends AdapterImpl implements ConsumerFactory<HTMLElement<?>> {
	
	public AppearanceConsumerFactoryAdapter(Appearance target) {
		setTarget(target);
	}
	
	@Override
	public Appearance getTarget() {
		return (Appearance) super.getTarget();
	}
	
	private java.util.function.Consumer<HTMLElement<?>> backgroundBuilder = htmlElement -> {
		Color background = AppearanceConsumerFactoryAdapter.this.getTarget().getBackground();
		if (background != null) {
			BootstrapElement<?, ?> bootstrapElement = BootstrapFactory.INSTANCE.wrap(htmlElement);
			bootstrapElement.background(background);
		}							
	};
	
	private java.util.function.Consumer<HTMLElement<?>> borderBuilder = htmlElement -> {
		BootstrapElement<?, ?> bootstrapElement = BootstrapFactory.INSTANCE.wrap(htmlElement);
		for (Border border: AppearanceConsumerFactoryAdapter.this.getTarget().getBorder()) {
			Color color = border.getColor();
			if (border.isBottom() || border.isLeft() || border.isRight() || border.isTop()) {
				if (border.isBottom()) {
					bootstrapElement.border(color, Placement.BOTTOM);
				}
				if (border.isTop()) {
					bootstrapElement.border(color, Placement.TOP);
				}
				if (border.isLeft()) {
					bootstrapElement.border(color, Placement.LEFT);
				}
				if (border.isRight()) {
					bootstrapElement.border(color, Placement.RIGHT);
				}
			} else {
				bootstrapElement.border(color);				
			}				
		} 
		Color bgColor = AppearanceConsumerFactoryAdapter.this.getTarget().getBackground();
		if (bgColor != null) {
			bootstrapElement.background(bgColor);
		}							
	};

	private java.util.function.Consumer<HTMLElement<?>> spacingBuilder = htmlElement -> {
		BootstrapElement<?, ?> bootstrapElement = BootstrapFactory.INSTANCE.wrap(htmlElement);
		for (Spacing margin : AppearanceConsumerFactoryAdapter.this.getTarget().getMargin()) {
			Size size = margin.getSize();
			Breakpoint breakpoint = margin.getBreakpoint();
			if (breakpoint == null) {
				breakpoint = Breakpoint.DEFAULT;
			}
			
			if (margin.isBottom() || margin.isLeft() || margin.isRight() || margin.isTop() || margin.isX() || margin.isY()) {
				if (margin.isBottom()) {
					bootstrapElement.margin().bottom(breakpoint, size);
				}
				if (margin.isTop()) {
					bootstrapElement.margin().top(breakpoint, size);
				}
				if (margin.isLeft()) {
					bootstrapElement.margin().left(breakpoint, size);
				}
				if (margin.isRight()) {
					bootstrapElement.margin().right(breakpoint, size);
				}
				
				if (margin.isX()) {
					bootstrapElement.margin().x(breakpoint, size);
				}
				if (margin.isY()) {
					bootstrapElement.margin().y(breakpoint, size);
				}						
			} else {
				bootstrapElement.margin().all(breakpoint, size);				
			}					
		} 

		for (Spacing padding : AppearanceConsumerFactoryAdapter.this.getTarget().getPadding()) {
			Size size = padding.getSize();
			Breakpoint breakpoint = padding.getBreakpoint();
			if (breakpoint == null) {
				breakpoint = Breakpoint.DEFAULT;
			}
			
			if (padding.isBottom() || padding.isLeft() || padding.isRight() || padding.isTop() || padding.isX() || padding.isY()) {
				if (padding.isBottom()) {
					bootstrapElement.padding().bottom(breakpoint, size);
				}
				if (padding.isTop()) {
					bootstrapElement.padding().top(breakpoint, size);
				}
				if (padding.isLeft()) {
					bootstrapElement.padding().left(breakpoint, size);
				}
				if (padding.isRight()) {
					bootstrapElement.padding().right(breakpoint, size);
				}
				
				if (padding.isX()) {
					bootstrapElement.padding().x(breakpoint, size);
				}
				if (padding.isY()) {
					bootstrapElement.padding().y(breakpoint, size);
				}						
			} else {
				bootstrapElement.padding().all(breakpoint, size);
			}
		}
		
	};
	
	private java.util.function.Consumer<HTMLElement<?>> textBuilder = htmlElement -> {
		BootstrapElement<?, ?> bootstrapElement = BootstrapFactory.INSTANCE.wrap(htmlElement);
		Text text = AppearanceConsumerFactoryAdapter.this.getTarget().getText();
		if (text != null) {
			org.nasdanika.html.bootstrap.Text<?> bsText = bootstrapElement.text();

			Color color = text.getColor();
			if (color != null) {						
				bsText.color(color);
			}
			
			Alignment alignment = text.getAlignment();
			if (alignment != null) {						
				bsText.alignment(alignment);
			}				

			Transform transform = text.getTransform();
			if (transform != null) {						
				bsText.transform(transform);
			}				

			Weight weight = text.getWeight();
			if (weight != null) {						
				bsText.weight(weight);
			}				
			
			bsText.monospace(text.isMonospace());
			bsText.italic(text.isItalic());
			bsText.nowrap(text.isNowrap());
			bsText.truncate(text.isTruncate());
			
		}
	};
			
	private java.util.function.Consumer<HTMLElement<?>> floatBuilder = htmlElement -> {
		BootstrapElement<?, ?> bootstrapElement = BootstrapFactory.INSTANCE.wrap(htmlElement);
		for (Float _float: AppearanceConsumerFactoryAdapter.this.getTarget().getFloat()) {
			org.nasdanika.html.bootstrap.Float<?> bsFloat = bootstrapElement._float();
			Breakpoint breakpoint = _float.getBreakpoint();
			if (breakpoint == null) {
				breakpoint = Breakpoint.DEFAULT;
			}
			switch (_float.getSide()) {
			case "left":
				bsFloat.left(breakpoint);
				break;
			case "right":
				bsFloat.right(breakpoint);
				break;
			case "none":
				bsFloat.none(breakpoint);
				break;
			default:
				throw new IllegalArgumentException("Invalid float side: "+_float.getSide()+", shall be left, right, or none");
			}
		}
	};	
	
	@Override
	public Consumer<HTMLElement<?>> create(Context context) throws Exception {
		MapCompoundSupplierFactory<String,Object> attributesFactory = new MapCompoundSupplierFactory<>("Attributes");
		for (Entry<String, EObject> ae: getTarget().getAttributes()) {
			EObject value = ae.getValue();
			attributesFactory.put(ae.getKey(), EObjectAdaptable.adaptToSupplierFactoryNonNull(value, Object.class));			
		}
		
		ListCompoundSupplierFactory<Object> contentFactory = new ListCompoundSupplierFactory<>("Content"); // Empty				
		
		MapCompoundSupplierFactory<EStructuralFeature,Object> configurationFactory = new MapCompoundSupplierFactory<>("Attributes and Content");
		configurationFactory.put(HtmlPackage.Literals.HTML_ELEMENT__ATTRIBUTES, attributesFactory);
		configurationFactory.put(HtmlPackage.Literals.HTML_ELEMENT__CONTENT, contentFactory);
		
		FunctionFactory<HTMLElement<?>, BiSupplier<HTMLElement<?>, Map<EStructuralFeature, Object>>> configurationFunctionFactory = configurationFactory.asFunctionFactory();
		FunctionFactory<BiSupplier<HTMLElement<?>, Map<EStructuralFeature, Object>>, HTMLElement<?>> applyAttributesAndContentFunctionFactory = HtmlElementAdapter::createApplyAttributesAndContentFunction;		
		
		return configurationFunctionFactory
			.then(applyAttributesAndContentFunctionFactory)
			.create(context)
			.then(Consumer.fromConsumer(backgroundBuilder, "Background", 1).asFunction())
			.then(Consumer.fromConsumer(borderBuilder, "Border", 1).asFunction())
			.then(Consumer.fromConsumer(spacingBuilder, "Spacing", 1).asFunction())
			.then(Consumer.fromConsumer(textBuilder, "Text", 1).asFunction())
			.then(Consumer.fromConsumer(floatBuilder, "Float", 1));						
	}		
	
}
