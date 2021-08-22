package org.nasdanika.html.model.bootstrap.gen;

import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.model.bootstrap.Appearance;
import org.nasdanika.html.model.html.gen.HtmlElementConsumerFactoryAdapter;

public abstract class BootstrapElementConsumerFactoryAdapter<M extends org.nasdanika.html.model.bootstrap.BootstrapElement, T extends org.nasdanika.html.bootstrap.BootstrapElement<?,?>> extends HtmlElementConsumerFactoryAdapter<M, org.nasdanika.html.HTMLElement<?>> {
	
	public BootstrapElementConsumerFactoryAdapter(M bootstrapElement) {
		super(bootstrapElement);
	}

	@Override
	protected Function<HTMLElement<?>, HTMLElement<?>> createConfigureFunction(Context context) throws Exception {
		Appearance appearance = getTarget().getAppearance();
		if (appearance == null) {
			return super.createConfigureFunction(context);
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ConsumerFactory<HTMLElement<?>> appearanceConsumerFactory = (ConsumerFactory) EObjectAdaptable.adaptToConsumerFactoryNonNull(getTarget(), org.nasdanika.html.HTMLElement.class);
		return super.createConfigureFunction(context).then(appearanceConsumerFactory.asFunctionFactory().create(context));
	}

}
