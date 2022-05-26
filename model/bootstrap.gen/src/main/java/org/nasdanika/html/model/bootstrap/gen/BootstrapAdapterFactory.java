package org.nasdanika.html.model.bootstrap.gen;

import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.model.bootstrap.Appearance;
import org.nasdanika.html.model.bootstrap.BootstrapElement;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.Page;
import org.nasdanika.html.model.bootstrap.TableCell;
import org.nasdanika.html.model.bootstrap.TableHeader;
import org.nasdanika.html.model.bootstrap.TableRow;
import org.nasdanika.html.model.bootstrap.TableSection;
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
				new FunctionAdapterFactory<ConsumerFactory<HTMLElement<?>>, BootstrapElement>(
					BootstrapPackage.Literals.BOOTSTRAP_ELEMENT, 
					Util.getConsumerFactoryClass(htmlElementClass), 
					classLoader, 
					e -> new BootstrapElementConsumerFactoryAdapter<>(e, this)));
								
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<org.nasdanika.html.Tag>, org.nasdanika.html.model.bootstrap.Tag>(
					BootstrapPackage.Literals.TAG, 
					Util.getSupplierFactoryClass(org.nasdanika.html.Tag.class), 
					classLoader, 
					e -> new TagSupplierFactoryAdapter<>(e, this)));		
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<HTMLElement<?>>, org.nasdanika.html.model.bootstrap.Modal>(
					BootstrapPackage.Literals.MODAL, 
					Util.getSupplierFactoryClass(htmlElementClass), 
					classLoader, 
					e -> new ModalSupplierFactoryAdapter(e, this)));		
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<org.nasdanika.html.Tag>, org.nasdanika.html.model.bootstrap.Alert>(
					BootstrapPackage.Literals.ALERT, 
					Util.getSupplierFactoryClass(org.nasdanika.html.Tag.class), 
					classLoader, 
					e -> new AlertSupplierFactoryAdapter(e, this)));		
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<org.nasdanika.html.Tag>, org.nasdanika.html.model.bootstrap.Carousel>(
					BootstrapPackage.Literals.CAROUSEL, 
					Util.getSupplierFactoryClass(org.nasdanika.html.Tag.class), 
					classLoader, 
					e -> new CarouselSupplierFactoryAdapter(e, this)));		
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<org.nasdanika.html.Tag>, org.nasdanika.html.model.bootstrap.Slide>(
					BootstrapPackage.Literals.SLIDE, 
					Util.getSupplierFactoryClass(org.nasdanika.html.Tag.class), 
					classLoader, 
					e -> new SlideSupplierFactoryAdapter(e, this)));		
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<HTMLElement<?>>, org.nasdanika.html.model.bootstrap.Card>(
					BootstrapPackage.Literals.CARD, 
					Util.getSupplierFactoryClass(htmlElementClass), 
					classLoader, 
					e -> new CardSupplierFactoryAdapter(e, this)));		
		
		// Table		
		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<HTMLElement<?>>, TableHeader>(
					BootstrapPackage.Literals.TABLE_HEADER, 
					Util.getConsumerFactoryClass(htmlElementClass), 
					classLoader, 
					e -> new TableHeaderConsumerFactoryAdapter(e, this)));
		
		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<HTMLElement<?>>, TableSection>(
					BootstrapPackage.Literals.TABLE_SECTION, 
					Util.getConsumerFactoryClass(htmlElementClass), 
					classLoader, 
					e -> new TableSectionConsumerFactoryAdapter(e, this)));
		
		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<HTMLElement<?>>, TableRow>(
					BootstrapPackage.Literals.TABLE_ROW, 
					Util.getConsumerFactoryClass(htmlElementClass), 
					classLoader, 
					e -> new TableRowConsumerFactoryAdapter(e, this)));
		
		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<HTMLElement<?>>, TableCell>(
					BootstrapPackage.Literals.TABLE_CELL, 
					Util.getConsumerFactoryClass(htmlElementClass), 
					classLoader, 
					e -> new TableCellConsumerFactoryAdapter(e, this)));
		
		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<HTMLElement<?>>, org.nasdanika.html.model.bootstrap.Table>(
					BootstrapPackage.Literals.TABLE, 
					Util.getConsumerFactoryClass(htmlElementClass), 
					classLoader, 
					e -> new TableConsumerFactoryAdapter(e, this)));
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<HTMLElement<?>>, org.nasdanika.html.model.bootstrap.Table>(
					BootstrapPackage.Literals.TABLE, 
					Util.getSupplierFactoryClass(htmlElementClass), 
					classLoader, 
					e -> new TableSupplierFactoryAdapter(e, this)));		
		
	}
	
}
