package org.nasdanika.html.app.factories;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.EnumSupplierFactoryAttribute;
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
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Placement;

/**
 * A {@link ViewBuilder} loaded from a {@link Map}.
 * @author Pavel
 *
 */
public class BorderSupplierFactory extends SupplierFactoryFeatureObject<Decorator> {
	
	private SupplierFactoryFeature<Color> color;
	private SupplierFactoryFeature<List<String>> placement;
	
	
	public BorderSupplierFactory() {
		color = addFeature(new EnumSupplierFactoryAttribute<Color>(new StringSupplierFactoryAttribute(new Attribute<String>("color", true, false, null, null), true), Color.class, null));
		placement = addFeature(new ListSupplierFactoryAttribute<>(new ListAttribute<String>("placement", false, false, null, "Border placement - top, bottom, left, or right"), true));		
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
					BootstrapElement<?,?> bootstrapElement;		
					if (target instanceof BootstrapElement) { 
						bootstrapElement = (BootstrapElement<?, ?>) target;
					} else if (target instanceof HTMLElement) {
						bootstrapElement = BootstrapFactory.INSTANCE.wrap((HTMLElement<?>) target);
					} else {
						throw new ConfigurationException("Cannot apply border to " + target, getMarker());						
					}
					
					Color theColor = (Color) color.get(data);
					if (placement.isLoaded()) {
						Placement[] thePlacement = ((List<String>) placement.get(data)).stream().map(str -> Placement.valueOf(str.toUpperCase())).collect(Collectors.toList()).toArray(new Placement[] {});
						bootstrapElement.border(theColor, thePlacement);
					} else {
						bootstrapElement.border(theColor);
					}
				};
			}
		};
	}
		
}
