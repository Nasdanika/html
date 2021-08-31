package org.nasdanika.html.model.app.gen;

import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.ContentPanel;
import org.nasdanika.html.model.app.Footer;
import org.nasdanika.html.model.app.Header;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.NavigationBar;
import org.nasdanika.html.model.app.NavigationPanel;
import org.nasdanika.html.model.app.Page;
import org.nasdanika.html.model.bootstrap.gen.BootstrapAdapterFactory;

/**
 * @author Pavel
 *
 */
public class AppAdapterFactory extends BootstrapAdapterFactory {
	
	public static AppAdapterFactory INSTANCE = new AppAdapterFactory();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AppAdapterFactory() {
		ClassLoader classLoader = getClassLoader();
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<HTMLElement<?>>, Page>(
					AppPackage.Literals.PAGE, 
					(Class) SupplierFactory.class, 
					classLoader, 
					PageSupplierFactoryAdapter::new));
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory.Provider, Label>(
					AppPackage.Literals.LABEL, 
					SupplierFactory.Provider.class, 
					classLoader, 
					LabelSupplierFactoryProviderAdapter::new));
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory.Provider, Link>(
					AppPackage.Literals.LINK, 
					SupplierFactory.Provider.class, 
					classLoader, 
					LinkSupplierFactoryProviderAdapter::new));
		
		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<HTMLElement<?>>, ContentPanel>(
					AppPackage.Literals.CONTENT_PANEL, 
					(Class) ConsumerFactory.class, 
					classLoader, 
					ContentPanelConsumerFactoryAdapter::new));		
		
		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<HTMLElement<?>>, Footer>(
					AppPackage.Literals.FOOTER, 
					(Class) ConsumerFactory.class, 
					classLoader, 
					FooterConsumerFactoryAdapter::new));		
		
		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<HTMLElement<?>>, Header>(
					AppPackage.Literals.HEADER, 
					(Class) ConsumerFactory.class, 
					classLoader, 
					HeaderConsumerFactoryAdapter::new));		
		
		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<HTMLElement<?>>, NavigationBar>(
					AppPackage.Literals.NAVIGATION_BAR, 
					(Class) ConsumerFactory.class, 
					classLoader, 
					NavigationBarConsumerFactoryAdapter::new));		
		
		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<HTMLElement<?>>, NavigationPanel>(
					AppPackage.Literals.NAVIGATION_PANEL, 
					(Class) ConsumerFactory.class, 
					classLoader, 
					NavigationPanelConsumerFactoryAdapter::new));		
				
	}
	
}
