package org.nasdanika.html.model.html.gen;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.Container;
import org.nasdanika.html.model.html.HtmlPackage;

/**
 * Base class for HTML Element adapters providing common functionality
 * @author Pavel
 *
 * @param <M>
 * @param <T>
 */
public abstract class HtmlElementAdapter<M extends org.nasdanika.html.model.html.HtmlElement, T extends org.nasdanika.html.HTMLElement<?>> extends AdapterImpl {
	
	protected HtmlElementAdapter(M htmlElement) {
		setTarget(htmlElement);
	}
	
	/**
	 * Creates a function which configures the element and returns it.
	 * This implementation applies attributes and adds content. Override to implement additional configuration.
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
		
		ListCompoundSupplierFactory<Object> contentFactory = new ListCompoundSupplierFactory<>("Content", EObjectAdaptable.adaptToSupplierFactoryNonNull(getContent(), Object.class));				
		
		MapCompoundSupplierFactory<EStructuralFeature,Object> configurationFactory = new MapCompoundSupplierFactory<>("Attributes and Content");
		configurationFactory.put(HtmlPackage.Literals.HTML_ELEMENT__ATTRIBUTES, attributesFactory);
		configurationFactory.put(HtmlPackage.Literals.HTML_ELEMENT__CONTENT, contentFactory);
		
		FunctionFactory<BiSupplier<T, Map<EStructuralFeature, Object>>, T> applyAttributesAndContentFunctionFactory = HtmlElementAdapter::createApplyAttributesAndContentFunction;
		FunctionFactory<T, BiSupplier<T, Map<EStructuralFeature, Object>>> configurationFunctionFactory = configurationFactory.asFunctionFactory();
		return configurationFunctionFactory.then(applyAttributesAndContentFunctionFactory).create(context);
	}

	/**
	 * This implementation returns target content. Override to customize.
	 * @return
	 */
	protected List<EObject> getContent() {
		return getTarget().getContent();
	}
	
	public static <T extends org.nasdanika.html.HTMLElement<?>> Function<BiSupplier<T, Map<EStructuralFeature, Object>>, T> createApplyAttributesAndContentFunction(Context context) {
		return new Function<BiSupplier<T,Map<EStructuralFeature,Object>>, T>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Apply attributes";
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public T execute(BiSupplier<T, Map<EStructuralFeature, Object>> input, ProgressMonitor progressMonitor) throws Exception {
				T ret = input.getFirst();
				Map<EStructuralFeature, Object> config = input.getSecond();
				for (Entry<String, Object> ae: ((Map<String,Object>) config.get(HtmlPackage.Literals.HTML_ELEMENT__ATTRIBUTES)).entrySet()) {
					ret.attribute(ae.getKey(), ae.getValue());
				}
				for (Object c: (List<Object>) config.get(HtmlPackage.Literals.HTML_ELEMENT__CONTENT)) {
					if (ret instanceof Container) {
						((Container<?>) ret).content(c);
					} else {
						ret.getContent().add(c);
					}
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
