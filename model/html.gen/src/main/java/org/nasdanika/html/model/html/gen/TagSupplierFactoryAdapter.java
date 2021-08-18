package org.nasdanika.html.model.html.gen;

import java.util.List;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLFactory;

public class TagSupplierFactoryAdapter extends HtmlElementSupplierFactoryAdapter<org.nasdanika.html.model.html.Tag, org.nasdanika.html.Tag> {
	
	public TagSupplierFactoryAdapter(org.nasdanika.html.model.html.Tag tag) {
		super(tag);
	}
	
	protected Function<List<Object>, org.nasdanika.html.Tag> createTagFunction(Context context) {
		return new Function<List<Object>, org.nasdanika.html.Tag>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Tag";
			}
			
			@Override
			public org.nasdanika.html.Tag execute(List<Object> content, ProgressMonitor progressMonitor) throws Exception {
				HTMLFactory htmlFactory = context.get(HTMLFactory.class, HTMLFactory.INSTANCE);
				String tagName = context.interpolateToString(((org.nasdanika.html.model.html.Tag) getTarget()).getName());
				org.nasdanika.html.Tag ret = htmlFactory.tag(tagName);
				for (Object ce: content) {
					ret.content(ce);
				}
				return ret;
			}
		};
		
	}
	
	@Override
	public Supplier<org.nasdanika.html.Tag> create(Context context) throws Exception {
		ListCompoundSupplierFactory<Object> contentFactory = new ListCompoundSupplierFactory<>("Content", EObjectAdaptable.adaptToSupplierFactoryNonNull(getTarget().getContent(), Object.class));				
		return contentFactory.then(this::createTagFunction).then(this::createConfigureFunction).create(context);
	}	

}
