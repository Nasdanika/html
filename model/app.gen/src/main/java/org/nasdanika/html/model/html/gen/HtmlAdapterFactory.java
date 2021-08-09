package org.nasdanika.html.model.html.gen;

import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.exec.gen.ExecutionParticpantAdapterFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.model.html.HtmlPackage;
import org.nasdanika.html.model.html.Page;

/**
 * Provides adapters for the Engineering model elements.
 * @author Pavel
 *
 */
public class HtmlAdapterFactory extends ExecutionParticpantAdapterFactory {
	
	public HtmlAdapterFactory() {
		ClassLoader classLoader = getClassLoader();
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<org.nasdanika.html.Tag>, org.nasdanika.html.model.html.Tag>(
					HtmlPackage.Literals.TAG, 
					Util.getSupplierFactoryClass(org.nasdanika.html.Tag.class), 
					classLoader, 
					TagSupplierFactoryAdapter::new));		
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<HTMLPage>, Page>(
					HtmlPackage.Literals.PAGE, 
					Util.getSupplierFactoryClass(HTMLPage.class), 
					classLoader, 
					PageSupplierFactoryAdapter::new));		
				
	}
	
}
