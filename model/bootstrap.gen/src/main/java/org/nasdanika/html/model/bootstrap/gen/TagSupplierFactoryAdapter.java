package org.nasdanika.html.model.bootstrap.gen;

import org.nasdanika.common.Consumer;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.model.bootstrap.Appearance;

public class TagSupplierFactoryAdapter<T extends org.nasdanika.html.model.bootstrap.Tag> extends org.nasdanika.html.model.html.gen.TagSupplierFactoryAdapter<T> {
	
	public TagSupplierFactoryAdapter(T tag) {
		super(tag);
	}
	
	@Override
	protected Function<org.nasdanika.html.Tag, org.nasdanika.html.Tag> createConfigureFunction(Context context) throws Exception {
		Appearance appearance = getTarget().getAppearance();
		if (appearance == null) {
			return super.createConfigureFunction(context);
		}
		
		Consumer<org.nasdanika.html.Tag> appearanceConsumer = EObjectAdaptable.adaptToConsumerFactoryNonNull(appearance, org.nasdanika.html.Tag.class).create(context);
		return super.createConfigureFunction(context).then(appearanceConsumer.asFunction());
	}

}
