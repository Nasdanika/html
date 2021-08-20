package org.nasdanika.html.model.html.gen;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;

public abstract class HtmlElementSupplierFactoryAdapter<M extends org.nasdanika.html.model.html.HtmlElement, T extends org.nasdanika.html.HTMLElement<?>> extends AdapterImpl implements SupplierFactory<T> {
	
	protected HtmlElementSupplierFactoryAdapter(M htmlElement) {
		setTarget(htmlElement);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
	
	/**
	 * Creates a function which configures the element and returns it.
	 * This implementation applies attributes. Override to implement additional configuration.
	 * You may chain configuration functions with <code>.then</code>
	 * @param context
	 * @return
	 * @throws Exception
	 */
	protected Function<T, T> createConfigureFunction(Context context) throws Exception {
		MapCompoundSupplierFactory<String,Object> attributesFactory = new MapCompoundSupplierFactory<>("Attributes");
		for (Entry<String, EObject> ae: getTarget().getAttributes()) {
			EObject value = ae.getValue();
			attributesFactory.put(ae.getKey(), EObjectAdaptable.adaptToSupplierFactoryNonNull(value, Object.class));			
		}
		FunctionFactory<BiSupplier<T, Map<String, Object>>, T> applyAttributesFunctionFactory = HtmlElementSupplierFactoryAdapter::createApplyAttributesFunction;
		return attributesFactory.<T>asFunctionFactory().then(applyAttributesFunctionFactory).create(context);
	}
	
	public static <T extends org.nasdanika.html.HTMLElement<?>> Function<BiSupplier<T, Map<String, Object>>, T> createApplyAttributesFunction(Context context) {
		return new Function<BiSupplier<T,Map<String,Object>>, T>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Apply attributes";
			}
			
			@Override
			public T execute(BiSupplier<T, Map<String, Object>> input, ProgressMonitor progressMonitor) throws Exception {
				T ret = input.getFirst();
				for (Entry<String, Object> ae: input.getSecond().entrySet()) {
					ret.attribute(ae.getKey(), ae.getValue());
				}
				return ret;
			}
		};
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public M getTarget() {
		return (M) super.getTarget();
	}

}
