package org.nasdanika.html.model.html.gen;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.ComposeableAdapterFactory;
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
		
	protected AdapterFactory adapterFactory;
	
	protected AdapterFactory getRootAdapterFactory() {
		return adapterFactory instanceof ComposeableAdapterFactory ? ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory() : adapterFactory;
	}
	
	protected HtmlElementAdapter(M htmlElement, AdapterFactory adapterFactory) {
		setTarget(htmlElement);
		this.adapterFactory = adapterFactory;
	}
	
	protected Adapter getRegisteredAdapter(EObject eObject, Object type) {
		Adapter result = EcoreUtil.getExistingAdapter(eObject, type);
		if (result != null) {
			return result;
		}

		AdapterFactory factory = getRootAdapterFactory();
		return factory == null ? null : factory.adaptNew(eObject, type);
	}	
	
	/**
	 * Creates a function which configures the element and returns it.
	 * This implementation applies attributes and adds content. Override to implement additional configuration.
	 * You may chain configuration functions with <code>.then</code>
	 * @param context
	 * @return
	 */
	protected Function<T, T> createConfigureFunction(Context context) {
		MapCompoundSupplierFactory<String,Object> attributesFactory = new MapCompoundSupplierFactory<>("Attributes");
		for (Entry<String, EObject> ae: getTarget().getAttributes()) {
			EObject value = ae.getValue();
			attributesFactory.put(ae.getKey(), EObjectAdaptable.adaptToSupplierFactoryNonNull(value, Object.class, getRootAdapterFactory()));
		}
		
		ListCompoundSupplierFactory<Object> contentFactory = new ListCompoundSupplierFactory<>("Content", EObjectAdaptable.adaptToSupplierFactoryNonNull(getContent(), Object.class));				
		
		MapCompoundSupplierFactory<EStructuralFeature,Object> configurationFactory = new MapCompoundSupplierFactory<>("Attributes and Content");
		configurationFactory.put(HtmlPackage.Literals.HTML_ELEMENT__ATTRIBUTES, attributesFactory);
		configurationFactory.put(HtmlPackage.Literals.HTML_ELEMENT__CONTENT, contentFactory);
		
		FunctionFactory<Supplier.FunctionResult<T, Map<EStructuralFeature, Object>>, T> applyAttributesAndContentFunctionFactory = HtmlElementAdapter::createApplyAttributesAndContentFunction;
		FunctionFactory<T, Supplier.FunctionResult<T, Map<EStructuralFeature, Object>>> configurationFunctionFactory = configurationFactory.asFunctionFactory();
		return configurationFunctionFactory.then(applyAttributesAndContentFunctionFactory).create(context);
	}

	/**
	 * This implementation returns target content. Override to customize.
	 * @return
	 */
	protected List<EObject> getContent() {
		return getTarget().getContent();
	}
	
	public static <T extends org.nasdanika.html.HTMLElement<?>> Function<Supplier.FunctionResult<T, Map<EStructuralFeature, Object>>, T> createApplyAttributesAndContentFunction(Context context) {
		return new Function<Supplier.FunctionResult<T,Map<EStructuralFeature,Object>>, T>() {
			
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
			public T execute(Supplier.FunctionResult<T, Map<EStructuralFeature, Object>> input, ProgressMonitor progressMonitor) {
				T ret = input.argument();
				Map<EStructuralFeature, Object> config = input.result();
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
