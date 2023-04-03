package org.nasdanika.html.model.bootstrap.gen;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.MapCompoundConsumerFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.model.bootstrap.BootstrapElement;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;

public class CardSupplierFactoryAdapter extends BootstrapElementSupplierFactoryAdapter<org.nasdanika.html.model.bootstrap.Card, org.nasdanika.html.bootstrap.Card> {
	
	public CardSupplierFactoryAdapter(org.nasdanika.html.model.bootstrap.Card card, AdapterFactory adapterFactory) {
		super(card, adapterFactory);
	}
	
	protected Supplier<Supplier.FunctionResult<Map<EStructuralFeature, HTMLElement<?>>, org.nasdanika.html.bootstrap.Card>> createCardSupplier(Context context) {
		return new Supplier<Supplier.FunctionResult<Map<EStructuralFeature, HTMLElement<?>>, org.nasdanika.html.bootstrap.Card>>() {
	
			@Override
			public double size() {
				return 1;
			}
	
			@Override
			public String name() {
				return "Card";
			}
	
			@Override
			public Supplier.FunctionResult<Map<EStructuralFeature, HTMLElement<?>>, org.nasdanika.html.bootstrap.Card> execute(ProgressMonitor progressMonitor) {
				BootstrapFactory bootstrapFactory = context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
				org.nasdanika.html.bootstrap.Card card = bootstrapFactory.card();
				org.nasdanika.html.model.bootstrap.Card semanticElement = getTarget();
				
				Map<EStructuralFeature, HTMLElement<?>> parts = new LinkedHashMap<>();
				
				if (semanticElement.getHeader() != null) {
					parts.put(BootstrapPackage.Literals.CARD__HEADER, card.getHeader().toHTMLElement());
				}
				
				if (semanticElement.getBody() != null) {
					parts.put(BootstrapPackage.Literals.CARD__BODY, card.getBody().toHTMLElement());
				}
				
				if (semanticElement.getFooter() != null) {
					parts.put(BootstrapPackage.Literals.CARD__FOOTER, card.getFooter().toHTMLElement());
				}
				
				return new Supplier.FunctionResult<Map<EStructuralFeature,HTMLElement<?>>, org.nasdanika.html.bootstrap.Card>(parts, card);
			}
			
		};
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Supplier<org.nasdanika.html.bootstrap.Card> createBootstrapElementSupplier(Context context) {
		MapCompoundConsumerFactory<EStructuralFeature,HTMLElement<?>> partsFactory = new MapCompoundConsumerFactory<>("Parts");
		org.nasdanika.html.model.bootstrap.Card semanticElement = getTarget();
		
		BootstrapElement header = semanticElement.getHeader();
		if (header != null) {
			partsFactory.put(BootstrapPackage.Literals.CARD__HEADER, (ConsumerFactory<HTMLElement<?>>) (ConsumerFactory) EObjectAdaptable.adaptToConsumerFactoryNonNull(header, org.nasdanika.html.HTMLElement.class));
		}
		
		BootstrapElement body = semanticElement.getBody();
		if (body != null) {
			partsFactory.put(BootstrapPackage.Literals.CARD__BODY, (ConsumerFactory<HTMLElement<?>>) (ConsumerFactory) EObjectAdaptable.adaptToConsumerFactoryNonNull(body, org.nasdanika.html.HTMLElement.class));
		}
		
		BootstrapElement footer = semanticElement.getFooter();
		if (footer != null) {
			partsFactory.put(BootstrapPackage.Literals.CARD__FOOTER, (ConsumerFactory<HTMLElement<?>>) (ConsumerFactory) EObjectAdaptable.adaptToConsumerFactoryNonNull(footer, org.nasdanika.html.HTMLElement.class));
		}
		
		SupplierFactory<Supplier.FunctionResult<Map<EStructuralFeature, HTMLElement<?>>, org.nasdanika.html.bootstrap.Card>> cardSupplierFactory = this::createCardSupplier;
		FunctionFactory<Supplier.FunctionResult<Map<EStructuralFeature, HTMLElement<?>>, org.nasdanika.html.bootstrap.Card>, org.nasdanika.html.bootstrap.Card> partsFunctionFactory = partsFactory.asResultFunctionFactory();		
		
		return cardSupplierFactory.then(partsFunctionFactory).create(context);
	}

}
