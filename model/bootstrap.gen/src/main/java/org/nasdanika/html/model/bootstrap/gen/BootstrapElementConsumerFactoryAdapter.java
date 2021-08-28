package org.nasdanika.html.model.bootstrap.gen;

import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.model.bootstrap.Appearance;
import org.nasdanika.html.model.html.gen.HtmlElementConsumerFactoryAdapter;

public class BootstrapElementConsumerFactoryAdapter<M extends org.nasdanika.html.model.bootstrap.BootstrapElement, T extends org.nasdanika.html.bootstrap.BootstrapElement<?,?>> extends HtmlElementConsumerFactoryAdapter<M, org.nasdanika.html.HTMLElement<?>> {
	
	public BootstrapElementConsumerFactoryAdapter(M bootstrapElement) {
		super(bootstrapElement);
	}

	@Override
	protected Function<HTMLElement<?>, HTMLElement<?>> createConfigureFunction(Context context) throws Exception {
		Appearance appearance = getTarget().getAppearance();
		Function<HTMLElement<?>, HTMLElement<?>> configureFunction = super.createConfigureFunction(context);
		if (appearance == null) {
			return configureFunction;
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ConsumerFactory<HTMLElement<?>> appearanceConsumerFactory = (ConsumerFactory) EObjectAdaptable.adaptToConsumerFactoryNonNull(appearance, org.nasdanika.html.HTMLElement.class);
		return configureFunction.then(appearanceConsumerFactory.asFunctionFactory().create(context));
	}

}
