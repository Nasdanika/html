package org.nasdanika.html.app.factories;

import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.DelegatingSupplierFactoryFeature;
import org.nasdanika.common.persistence.FeatureObjectAttribute;
import org.nasdanika.common.persistence.Reference;
import org.nasdanika.common.persistence.StringSupplierFactoryAttribute;
import org.nasdanika.common.persistence.SupplierFactoryFeature;
import org.nasdanika.common.persistence.SupplierFactoryFeatureObject;
import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.impl.LabelImpl;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.factories.AppearanceSupplierFactory;

public class LabelSupplierFactory<L extends Label> extends SupplierFactoryFeatureObject<L> {

	private Attribute<String> id = addFeature(new Attribute<String>("id", false, false, UUID.randomUUID().toString(), null));
	private Attribute<String> icon = addFeature(new Attribute<String>("icon", false, false, null, null));
	private Attribute<String> text = addFeature(new Attribute<String>("text", true, false, null, null));
	private Attribute<String> tooltip = addFeature(new Attribute<String>("tooltip", false, false, null, null));
	private Attribute<String> color = addFeature(new Attribute<String>("color", false, false, null, null));
	private Attribute<Boolean> outline = addFeature(new Attribute<Boolean>("outline", false, false, false, null));
	private StringSupplierFactoryAttribute description = addFeature(new StringSupplierFactoryAttribute(new Reference("description", false, false, null, null), true));
	private Attribute<String> notification = addFeature(new Attribute<String>("notification", false, false, null, null));
	protected SupplierFactoryFeature<Consumer<Object>> appearance;
	
	public LabelSupplierFactory() {
		appearance = addFeature(new DelegatingSupplierFactoryFeature<Consumer<Object>>(new FeatureObjectAttribute<AppearanceSupplierFactory>("appearance", AppearanceSupplierFactory::new, false, false, null, "Appearance"))); 
	}

	@Override
	protected Function<Map<Object, Object>, L> createResultFunction(Context context) {			
		return new Function<Map<Object, Object>, L>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Creating label";
			}

			@SuppressWarnings("unchecked")
			@Override
			public L execute(Map<Object, Object> data, ProgressMonitor progressMonitor) throws Exception {								
				LabelImpl ret = (LabelImpl) createLabel(context, data);
				setData(context, data, ret);
				return (L) ret;
			}
		};
	}
	
	/**
	 * Override to return ActionImpl
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected L createLabel(Context context, Map<Object, Object> data) throws Exception {
		Decorator decorator = (target, viewBuilder) -> {
			if (appearance.isLoaded()) {
				((Consumer<Object>) appearance.get(data)).accept(target);
			}
		};
		return (L) new LabelImpl(decorator); 
	}
	
	protected void setData(Context context, Map<Object, Object> data, LabelImpl label) {
		// TODO - interpolations where needed.
		label.setId(data.get(id.getKey()));
		label.setIcon((String) data.get(icon.getKey()));
		label.setText((String) data.get(text.getKey()));
		if (tooltip.isLoaded()) {
			label.setTooltip((String) data.get(tooltip.getKey()));
		}
		if (description.isLoaded()) {
			label.setDescription((String) data.get(description.getKey()));
			if (!tooltip.isLoaded()) {
				label.setTooltip(org.nasdanika.common.Util.firstPlainTextSentence(label.getDescription(), 50, 250));
			}
		}
		if (color.isLoaded()) {
			try {
				label.setColor(Color.fromCode(context.interpolateToString((String) data.get(color.getKey()))));
			} catch (IllegalArgumentException e) {
				throw new ConfigurationException(e.getMessage(), e, color.getMarker());
			}
		}
		
		label.setOutline((boolean) data.get(outline.getKey()));
		label.setNotification((String) data.get(notification.getKey()));
	}
	
}
