package org.nasdanika.html.model.bootstrap.gen;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.model.bootstrap.Appearance;
import org.nasdanika.html.model.html.gen.HtmlElementSupplierFactoryAdapter;

public abstract class BootstrapElementSupplierFactoryAdapter<M extends org.nasdanika.html.model.bootstrap.BootstrapElement, T extends org.nasdanika.html.bootstrap.BootstrapElement<?,?>> extends HtmlElementSupplierFactoryAdapter<M, org.nasdanika.html.HTMLElement<?>> {
	
	public BootstrapElementSupplierFactoryAdapter(M bootstrapElement, AdapterFactory adapterFactory) {
		super(bootstrapElement, adapterFactory);
	}

	@Override
	protected Function<HTMLElement<?>, HTMLElement<?>> createConfigureFunction(Context context) {
		Appearance appearance = getTarget().getAppearance();
		if (appearance == null) {
			return super.createConfigureFunction(context);
		}
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ConsumerFactory<HTMLElement<?>> appearanceConsumerFactory = (ConsumerFactory) EObjectAdaptable.adaptToConsumerFactoryNonNull(appearance, org.nasdanika.html.HTMLElement.class);
		return super.createConfigureFunction(context).then(appearanceConsumerFactory.asFunctionFactory().create(context));
	}
	
	@Override
	protected Supplier<HTMLElement<?>> createHTMLElementSupplier(Context context) {
		return createBootstrapElementSupplier(context).then(new Function<T, HTMLElement<?>>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Bootstrap element to HTML element";
			}

			@Override
			public HTMLElement<?> execute(T bootstrapElement, ProgressMonitor progressMonitor) {
				return bootstrapElement.toHTMLElement();
			}
			
		});
	}
		
	protected Supplier<T> createBootstrapElementSupplier(Context context) {
		throw new UnsupportedOperationException("Override this method or createHTMLElementSupplier()");
	}
	
	/**
	 * Retrieves wrapping bootstrap element.
	 * @param context
	 * @return
	 */
	protected Function<org.nasdanika.html.HTMLElement<?>, T> getWrapper(Context context) {
		return new Function<org.nasdanika.html.HTMLElement<?>, T>() {
	
			@Override
			public double size() {
				return 1;
			}
	
			@Override
			public String name() {
				return "Retrieving wrapping bootstrap element";
			}
	
			@SuppressWarnings("unchecked")
			@Override
			public T execute(HTMLElement<?> htmlElement, ProgressMonitor progressMonitor) {
				return (T) htmlElement.getData(org.nasdanika.html.bootstrap.BootstrapElement.class);
			}
			
		};
	}
	
}
