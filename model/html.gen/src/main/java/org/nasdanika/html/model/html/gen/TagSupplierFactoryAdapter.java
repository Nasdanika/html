package org.nasdanika.html.model.html.gen;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.html.HTMLFactory;

public class TagSupplierFactoryAdapter<T extends org.nasdanika.html.model.html.Tag> extends HtmlElementSupplierFactoryAdapter<T, org.nasdanika.html.Tag> {
	
	public TagSupplierFactoryAdapter(T tag) {
		super(tag);
	}
	
	protected Supplier<org.nasdanika.html.Tag> createHTMLElementSupplier(Context context) {
		return new Supplier<org.nasdanika.html.Tag>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Tag";
			}
			
			@Override
			public org.nasdanika.html.Tag execute(ProgressMonitor progressMonitor) throws Exception {
				HTMLFactory htmlFactory = context.get(HTMLFactory.class, HTMLFactory.INSTANCE);
				String tagName = context.interpolateToString(getTarget().getName());
				return htmlFactory.tag(tagName);
			}
		};
		
	}

}
