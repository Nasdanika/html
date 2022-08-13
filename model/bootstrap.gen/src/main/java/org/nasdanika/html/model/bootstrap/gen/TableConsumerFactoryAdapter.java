package org.nasdanika.html.model.bootstrap.gen;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.MapCompoundConsumerFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;
import org.nasdanika.html.model.bootstrap.Table;
import org.nasdanika.html.model.bootstrap.TableHeader;
import org.nasdanika.html.model.bootstrap.TableSection;

public class TableConsumerFactoryAdapter extends TableRowContainerConsumerFactoryAdapter<org.nasdanika.html.model.bootstrap.Table, org.nasdanika.html.bootstrap.Table> {

	public TableConsumerFactoryAdapter(org.nasdanika.html.model.bootstrap.Table table, AdapterFactory adapterFactory) {
		super(table, adapterFactory);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Function<HTMLElement<?>, HTMLElement<?>> createConfigureFunction(Context context) {
		MapCompoundConsumerFactory<EStructuralFeature,HTMLElement<?>> partsFactory = new MapCompoundConsumerFactory<>("Sections");
		org.nasdanika.html.model.bootstrap.Table semanticElement = getTarget();
		
		TableHeader header = semanticElement.getHeader();
		if (header != null) {
			partsFactory.put(BootstrapPackage.Literals.TABLE__HEADER, (ConsumerFactory<HTMLElement<?>>) (ConsumerFactory) EObjectAdaptable.adaptToConsumerFactoryNonNull(header, org.nasdanika.html.HTMLElement.class));
		}
		
		TableSection body = semanticElement.getBody();
		if (body != null) {
			partsFactory.put(BootstrapPackage.Literals.TABLE__BODY, (ConsumerFactory<HTMLElement<?>>) (ConsumerFactory) EObjectAdaptable.adaptToConsumerFactoryNonNull(body, org.nasdanika.html.HTMLElement.class));
		}
		
		TableSection footer = semanticElement.getFooter();
		if (footer != null) {
			partsFactory.put(BootstrapPackage.Literals.TABLE__FOOTER, (ConsumerFactory<HTMLElement<?>>) (ConsumerFactory) EObjectAdaptable.adaptToConsumerFactoryNonNull(footer, org.nasdanika.html.HTMLElement.class));
		}
		
		FunctionFactory<BiSupplier<Map<EStructuralFeature, HTMLElement<?>>, HTMLElement<?>>, HTMLElement<?>> partsFunctionFactory = partsFactory.asBiSupplierFunctionFactory();
		
		return super.createConfigureFunction(context).then(getWrapper(context)).then(createTableFunction(context)).then(partsFunctionFactory.create(context));
	}
		
	/**
	 * Takes table, creates sections as needed and performs other configuration. 
	 * @param context
	 * @return {@link BiSupplier} with a map of sections as first and the argument table as second.
	 */
	protected Function<org.nasdanika.html.bootstrap.Table, BiSupplier<Map<EStructuralFeature, HTMLElement<?>>, HTMLElement<?>>> createTableFunction(Context context) {
		return new Function<org.nasdanika.html.bootstrap.Table, BiSupplier<Map<EStructuralFeature, HTMLElement<?>>, HTMLElement<?>>>() {
	
			@Override
			public double size() {
				return 1;
			}
	
			@Override
			public String name() {
				return "Table";
			}

			@Override
			public BiSupplier<Map<EStructuralFeature, HTMLElement<?>>, HTMLElement<?>> execute(org.nasdanika.html.bootstrap.Table table, ProgressMonitor progressMonitor) {
				Map<EStructuralFeature, HTMLElement<?>> sectionsMap = new HashMap<>();
				Table semanticElement = getTarget();
				if (semanticElement.getHeader() != null) {
					sectionsMap.put(BootstrapPackage.Literals.TABLE__HEADER, table.header().toHTMLElement());
				}
				
				if (semanticElement.getBody() != null) {
					sectionsMap.put(BootstrapPackage.Literals.TABLE__BODY, table.body().toHTMLElement());
				}
				
				if (semanticElement.getFooter() != null) {
					sectionsMap.put(BootstrapPackage.Literals.TABLE__FOOTER, table.footer().toHTMLElement());
				}
								
				table.dark(semanticElement.isDark());
				table.striped(semanticElement.isStriped());
				table.bordered(semanticElement.isBordered());
				table.borderless(semanticElement.isBorderless());
				table.hover(semanticElement.isHover());
				table.small(semanticElement.isSmall());
				
				return new BiSupplier<Map<EStructuralFeature,HTMLElement<?>>, HTMLElement<?>>() {
					
					@Override
					public Map<EStructuralFeature, HTMLElement<?>> getFirst() {
						return sectionsMap;
					}
					
					@Override
					public HTMLElement<?> getSecond() {
						return table.toHTMLElement();
					}
					
				};
			}
			
		};
	}
		
}
