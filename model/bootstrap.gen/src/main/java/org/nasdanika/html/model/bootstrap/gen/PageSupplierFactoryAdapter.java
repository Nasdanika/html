package org.nasdanika.html.model.bootstrap.gen;

import java.util.List;

import org.nasdanika.common.Supplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.model.bootstrap.Page;

public class PageSupplierFactoryAdapter extends org.nasdanika.html.model.html.gen.PageSupplierFactoryAdapter {
	
	public PageSupplierFactoryAdapter(Page page) {
		super(page);
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
				return "Bootstrap Page";
			}
			
			@Override
			public HTMLPage execute(Supplier.FunctionResult<List<Object>,List<Object>> headAndBody, ProgressMonitor progressMonitor) {
				BootstrapFactory factory = context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
				HTMLFactory htmlFactory = factory.getHTMLFactory();
				Page semanticElement = (Page) getTarget();
				String pageName = context.interpolateToString(semanticElement.getName());
				HTMLPage ret = htmlFactory.page();
				ret.title(pageName);
				ret.lang(semanticElement.getLanguage());
				
				if (semanticElement.isCdn()) {
					factory.bootstrapCdnHTMLPage(ret, semanticElement.getTheme());
				} else if (semanticElement.getTheme() != null) {
					throw new ConfigurationException("Theme cannot be specified when 'cdn' is set to 'false'");			
				}
				
				factory.bootstrapHTMLPage(ret);
				
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

}
