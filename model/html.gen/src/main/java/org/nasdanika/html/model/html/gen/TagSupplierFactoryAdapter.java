package org.nasdanika.html.model.html.gen;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;

public class TagSupplierFactoryAdapter extends AdapterImpl implements SupplierFactory<org.nasdanika.html.Tag> {
	
	public TagSupplierFactoryAdapter(org.nasdanika.html.model.html.Tag tag) {
		setTarget(tag);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
	
	protected Function<BiSupplier<List<Object>, Map<String, Object>>, org.nasdanika.html.Tag> createTagFunction(Context context) {
		return new Function<BiSupplier<List<Object>,Map<String,Object>>, Tag>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Tag";
			}
			
			@Override
			public Tag execute(BiSupplier<List<Object>, Map<String, Object>> input, ProgressMonitor progressMonitor) throws Exception {
				HTMLFactory htmlFactory = context.get(HTMLFactory.class, HTMLFactory.INSTANCE);
				String tagName = context.interpolateToString(((org.nasdanika.html.model.html.Tag) getTarget()).getName());
				org.nasdanika.html.Tag ret = htmlFactory.tag(tagName);
				for (Object ce: input.getFirst()) {
					ret.content(ce);
				}
				for (Entry<String, Object> ae: input.getSecond().entrySet()) {
					ret.attribute(ae.getKey(), ae.getValue());
				}
				return ret;
			}
		};
		
	}
	
	@Override
	public Supplier<org.nasdanika.html.Tag> create(Context context) throws Exception {
		org.nasdanika.html.model.html.Tag tag = (org.nasdanika.html.model.html.Tag) getTarget();
		ListCompoundSupplierFactory<Object> contentFactory = new ListCompoundSupplierFactory<>("Content");
		for (EObject ce: tag.getContent()) {
			contentFactory.add(Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(ce, Object.class), "Cannot to adapt to SupplierFactory: " + ce));
		}		
		
		MapCompoundSupplierFactory<String,Object> attributesFactory = new MapCompoundSupplierFactory<>("Attributes");
		for (Entry<String, EObject> ae: tag.getAttributes()) {
			EObject value = ae.getValue();
			attributesFactory.put(ae.getKey(), Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(value, Object.class), "Cannot to adapt to SupplierFactory: " + value));			
		}
		
		return contentFactory.then(attributesFactory.asFunctionFactory()).then(this::createTagFunction).create(context);
	}	

}
