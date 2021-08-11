package org.nasdanika.html.model.app.gen;

import java.io.InputStream;

import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Page;
import org.nasdanika.html.model.bootstrap.gen.BootstrapAdapterFactory;

/**
 * @author Pavel
 *
 */
public class AppAdapterFactory extends BootstrapAdapterFactory {
	
	public AppAdapterFactory() {
		ClassLoader classLoader = getClassLoader();
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<InputStream>, Page>(
					AppPackage.Literals.PAGE, 
					Util.getSupplierFactoryClass(InputStream.class), 
					classLoader, 
					PageSupplierFactoryAdapter::new));		
				
	}
	
}
