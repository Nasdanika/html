package org.nasdanika.html.model.bootstrap.gen;

import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.model.bootstrap.Appearance;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.Page;
import org.nasdanika.html.model.html.gen.HtmlAdapterFactory;

/**
 * Provides adapters for the Engineering model elements.
 * @author Pavel
 *
 */
public class BootstrapAdapterFactory extends HtmlAdapterFactory {
	
	public BootstrapAdapterFactory() {
		ClassLoader classLoader = getClassLoader();
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<HTMLPage>, Page>(
					BootstrapPackage.Literals.PAGE, 
					Util.getSupplierFactoryClass(HTMLPage.class), 
					classLoader, 
					PageSupplierFactoryAdapter::new));		
						
		@SuppressWarnings({ "unchecked", "rawtypes" })
		Class<HTMLElement<?>> htmlElementClass = (Class) HTMLElement.class;
		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<HTMLElement<?>>, Appearance>(
					BootstrapPackage.Literals.APPEARANCE, 
					Util.getConsumerFactoryClass(htmlElementClass), 
					classLoader, 
					AppearanceConsumerFactoryAdapter::new));
		
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<org.nasdanika.html.Tag>, org.nasdanika.html.model.bootstrap.Tag>(
					BootstrapPackage.Literals.TAG, 
					Util.getSupplierFactoryClass(org.nasdanika.html.Tag.class), 
					classLoader, 
					TagSupplierFactoryAdapter::new));		
		
	}
	
}
