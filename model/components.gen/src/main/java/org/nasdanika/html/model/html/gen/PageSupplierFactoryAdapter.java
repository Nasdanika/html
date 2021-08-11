package org.nasdanika.html.model.html.gen;

import java.util.List;
import java.util.Objects;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.CompoundConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.model.html.Page;

public class PageSupplierFactoryAdapter extends AdapterImpl implements SupplierFactory<HTMLPage> {
	
	public PageSupplierFactoryAdapter(Page page) {
		setTarget(page);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
	
	protected Function<BiSupplier<List<Object>,List<Object>>, HTMLPage> createPageFunction(Context context) {
		return new Function<BiSupplier<List<Object>,List<Object>>, HTMLPage>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "HTML Page";
			}
			
			@Override
			public HTMLPage execute(BiSupplier<List<Object>,List<Object>> headAndBody, ProgressMonitor progressMonitor) throws Exception {
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
				for (Object he: headAndBody.getFirst()) {
					ret.head(he);
				}
				for (Object be: headAndBody.getSecond()) {
					ret.body(be);
				}
				return ret;
			}
		};
		
	}
	
	@Override
	public Supplier<HTMLPage> create(Context context) throws Exception {
		Page page = (Page) getTarget();
		ListCompoundSupplierFactory<Object> headFactory = new ListCompoundSupplierFactory<>("Head");
		for (EObject he: page.getHead()) {
			headFactory.add(Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(he, Object.class), "Cannot to adapt to SupplierFactory: " + he));
		}		

		ListCompoundSupplierFactory<Object> bodyFactory = new ListCompoundSupplierFactory<>("Body");
		for (EObject be: page.getBody()) {
			bodyFactory.add(Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(be, Object.class), "Cannot to adapt to SupplierFactory: " + be));
		}		
		
		CompoundConsumerFactory<HTMLPage> buildFactory = new CompoundConsumerFactory<>("Builders");
		for (EObject be: page.getBuilders()) {
			buildFactory.add(Objects.requireNonNull(EObjectAdaptable.adaptToConsumerFactory(be, HTMLPage.class), "Cannot to adapt to ConsumerFactory: " + be));
		}		
		
		return headFactory.then(bodyFactory.asFunctionFactory()).then(this::createPageFunction).then(buildFactory.asFunctionFactory()).create(context);
	}	

}