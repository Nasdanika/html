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
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.Spacing;

/**
 * A {@link ViewBuilder} loaded from a {@link Map}.
 * @author Pavel
 *
 */
public class SpacingSupplierFactory extends SupplierFactoryFeatureObject<Decorator> {
	
	private java.util.function.Function<BootstrapElement<?, ?>, Spacing<?>> spacingProvider;

	private SupplierFactoryFeature<Size> size;
	private SupplierFactoryFeature<Breakpoint> breakpoint;
	private SupplierFactoryFeature<List<String>> position;
	
	public SpacingSupplierFactory(java.util.function.Function<BootstrapElement<?,?>, Spacing<?>> spacingProvider) {
		this.spacingProvider = spacingProvider;
		
		FunctionFactory<String, Size> sizeFactory = context -> Function.fromFunction(Size::fromCode, "Size from code", 1);
		size = addFeature(new FunctionSupplierFactoryAttribute<String,Size>(new StringSupplierFactoryAttribute(new Attribute<String>("size", true, true, null, null), true), sizeFactory));
		
		FunctionFactory<String, Breakpoint> breakpointFactory = context -> Function.fromFunction(Breakpoint::fromCode, "Breakpoint from code", 1);
		breakpoint = addFeature(new FunctionSupplierFactoryAttribute<String,Breakpoint>(new StringSupplierFactoryAttribute(new Attribute<String>("breakpoint", false, false, "", null), true), breakpointFactory));
	
		position = new ListSupplierFactoryAttribute<>(new ListAttribute<String>("position", false, false, null, "Spacing position - top, bottom, left, right, x, or y"), true);
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
					if (target instanceof BootstrapElement) { 
						bootstrapElement = (BootstrapElement<?, ?>) target;
					} else if (target instanceof HTMLElement) {
						bootstrapElement = BootstrapFactory.INSTANCE.wrap((HTMLElement<?>) target);
					} else {
						throw new ConfigurationException("Cannot apply spacing to " + target, getMarker());						
					}
					Spacing<?> spacing = spacingProvider.apply(bootstrapElement);
					Size theSize = (Size) size.get(data);
					Breakpoint theBreakpoint = (Breakpoint) breakpoint.get(data); 
					if (position.isLoaded()) {
						for (String p: (List<String>) position.get(data)) {
							switch (p) {
							case "x":
								spacing.x(theBreakpoint, theSize);
								break;
							case "y":
								spacing.y(theBreakpoint, theSize);
								break;
							case "top":
								spacing.top(theBreakpoint, theSize);
								break;
							case "bottom":
								spacing.bottom(theBreakpoint, theSize);
								break;
							case "left":
								spacing.left(theBreakpoint, theSize);
								break;
							case "right":
								spacing.right(theBreakpoint, theSize);
								break;
							default:
								throw new ConfigurationException("Invalid position value: " + p, position.getMarker());						
							}
						}
					} else {
						spacing.all(theBreakpoint, theSize);
					}
//					for (Spacing margin : AppearanceSupplierFactory.this.target.getMargin()) {
//					Size size = org.nasdanika.html.bootstrap.Size.fromCode(margin.getSize());
//					String bpStr = margin.getBreakpoint();
//					Breakpoint breakpoint = Util.isBlank(bpStr) ? Breakpoint.DEFAULT : Breakpoint.fromLabel(bpStr);
//					
//					if ((margin.isBottom() && margin.isLeft() && margin.isRight() && margin.isTop()) || (margin.isX() && margin.isY())) {
//						bootstrapElement.margin().all(breakpoint, size);
//					} else {
//						if (margin.isBottom()) {
//							bootstrapElement.margin().bottom(breakpoint, size);
//						}
//						if (margin.isTop()) {
//							bootstrapElement.margin().top(breakpoint, size);
//						}
//						if (margin.isLeft()) {
//							bootstrapElement.margin().left(breakpoint, size);
//						}
//						if (margin.isRight()) {
//							bootstrapElement.margin().right(breakpoint, size);
//						}
//						
//						if (margin.isX()) {
//							bootstrapElement.margin().x(breakpoint, size);
//						}
//						if (margin.isY()) {
//							bootstrapElement.margin().y(breakpoint, size);
//						}						
//					}					
//				}
	//
//				for (Spacing padding : AppearanceSupplierFactory.this.target.getPadding()) {
//					Size size = org.nasdanika.html.bootstrap.Size.fromCode(padding.getSize());
//					String bpStr = padding.getBreakpoint();
//					Breakpoint breakpoint = Util.isBlank(bpStr) ? Breakpoint.DEFAULT : Breakpoint.fromLabel(bpStr);
//					if (!Util.isBlank(bpStr)) {
//						for (Breakpoint candidate: Breakpoint.values()) {
//							if (candidate.label.equals(bpStr)) {
//								breakpoint = candidate;
//								break;
//							}
//						}
//					}
//					
//					if ((padding.isBottom() && padding.isLeft() && padding.isRight() && padding.isTop()) || (padding.isX() && padding.isY())) {
//						bootstrapElement.padding().all(breakpoint, size);
//					} else {
//						if (padding.isBottom()) {
//							bootstrapElement.padding().bottom(breakpoint, size);
//						}
//						if (padding.isTop()) {
//							bootstrapElement.padding().top(breakpoint, size);
//						}
//						if (padding.isLeft()) {
//							bootstrapElement.padding().left(breakpoint, size);
//						}
//						if (padding.isRight()) {
//							bootstrapElement.padding().right(breakpoint, size);
//						}
//						
//						if (padding.isX()) {
//							bootstrapElement.padding().x(breakpoint, size);
//						}
//						if (padding.isY()) {
//							bootstrapElement.padding().y(breakpoint, size);
//						}						
//					}					
//				}
					
					
				};
				
			}
		};
	}
		
}
