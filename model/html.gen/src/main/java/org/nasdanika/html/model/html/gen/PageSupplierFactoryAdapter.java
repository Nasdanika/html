package org.nasdanika.html.model.html.gen;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.common.CollectionCompoundConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.model.html.Page;

public class PageSupplierFactoryAdapter extends AdapterImpl implements SupplierFactory<HTMLPage> {
	
	public static final String PAGE_BODY_PROPERTY = "page/body";
	public static final String PAGE_HEAD_PROPERTY = "page/head";

	public PageSupplierFactoryAdapter(Page page) {
		setTarget(page);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
	
	protected Function<Supplier.FunctionResult<List<Object>,List<Object>>, HTMLPage> createPageFunction(Context context) {
		return new Function<Supplier.FunctionResult<List<Object>,List<Object>>, HTMLPage>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "HTML Page";
			}
			
			@Override
			public HTMLPage execute(Supplier.FunctionResult<List<Object>,List<Object>> headAndBody, ProgressMonitor progressMonitor) {
				HTMLFactory htmlFactory = context.get(HTMLFactory.class, HTMLFactory.INSTANCE);
				Page semanticElement = (Page) getTarget();
				String pageName = context.interpolateToString(semanticElement.getName());
				HTMLPage ret = htmlFactory.page();
				ret.title(pageName);
				ret.lang(semanticElement.getLanguage());
				for (String styleseet: semanticElement.getStylesheets()) {
					ret.stylesheet(styleseet);
				}
				for (String script: semanticElement.getScripts()) {
					ret.script(script);
				}				
				for (Object he: headAndBody.argument()) {
					ret.head(he);
				}
				for (Object he: context.get(PAGE_HEAD_PROPERTY, List.class)) {
					ret.head(he);
				}
				for (Object be: context.get(PAGE_BODY_PROPERTY, List.class)) {
					ret.body(be);
				}
				for (Object be: headAndBody.result()) {
					ret.body(be);
				}
				return ret;
			}
		};
		
	}
	
	@Override
	public Supplier<HTMLPage> create(Context context) {
		Page page = (Page) getTarget();
		MutableContext mc = context.fork();
		mc.put(PAGE_HEAD_PROPERTY, new ArrayList<>());
		mc.put(PAGE_BODY_PROPERTY, new ArrayList<>());
		ListCompoundSupplierFactory<Object> headFactory = new ListCompoundSupplierFactory<>("Head", EObjectAdaptable.adaptToSupplierFactoryNonNull(page.getHead(), Object.class));
		ListCompoundSupplierFactory<Object> bodyFactory = new ListCompoundSupplierFactory<>("Body", EObjectAdaptable.adaptToSupplierFactoryNonNull(page.getBody(), Object.class));		
		CollectionCompoundConsumerFactory<HTMLPage> buildFactory = new CollectionCompoundConsumerFactory<>("Builders", EObjectAdaptable.adaptToConsumerFactoryNonNull(page.getBuilders(), HTMLPage.class));		
		return headFactory.then(bodyFactory.asFunctionFactory()).then(this::createPageFunction).then(buildFactory.asFunctionFactory()).create(mc);
	}	

}
