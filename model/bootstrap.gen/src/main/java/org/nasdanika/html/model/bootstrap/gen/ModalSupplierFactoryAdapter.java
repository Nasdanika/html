package org.nasdanika.html.model.bootstrap.gen;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.MapCompoundConsumerFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marked;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.Button;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Modal;
import org.nasdanika.html.model.bootstrap.BootstrapElement;
import org.nasdanika.html.model.bootstrap.BootstrapPackage;

public class ModalSupplierFactoryAdapter extends BootstrapElementSupplierFactoryAdapter<org.nasdanika.html.model.bootstrap.Modal, org.nasdanika.html.bootstrap.Modal> {
	
	public ModalSupplierFactoryAdapter(org.nasdanika.html.model.bootstrap.Modal modal, AdapterFactory adapterFactory) {
		super(modal, adapterFactory);
	}
	
	protected Supplier<BiSupplier<Map<EStructuralFeature, HTMLElement<?>>, org.nasdanika.html.bootstrap.Modal>> createModalSupplier(Context context) {
		return new Supplier<BiSupplier<Map<EStructuralFeature, HTMLElement<?>>, org.nasdanika.html.bootstrap.Modal>>() {
	
			@Override
			public double size() {
				return 1;
			}
	
			@Override
			public String name() {
				return "Modal";
			}
	
			@Override
			public BiSupplier<Map<EStructuralFeature, HTMLElement<?>>, org.nasdanika.html.bootstrap.Modal> execute(ProgressMonitor progressMonitor) {
				BootstrapFactory bootstrapFactory = context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
				org.nasdanika.html.bootstrap.Modal modal = bootstrapFactory.modal();
				org.nasdanika.html.model.bootstrap.Modal semanticElement = getTarget();
				
				modal.centered(semanticElement.isCentered());
				modal.scrollable(semanticElement.isScrollable());
				
				String size = semanticElement.getSize();
				if (!Util.isBlank(size)) {
					switch (size) {
					case "small":
						modal.size(Breakpoint.SMALL);
						break;
					case "large":
						modal.size(Breakpoint.LARGE);
						break;
					case "extra-large":
						modal.size(Breakpoint.EXTRA_LARGE);
						break;
					default:
						throw new ConfigurationException("Unsupported modal size: " + size, EObjectAdaptable.adaptTo(semanticElement, Marked.class)); 
					}
				}
				
				Map<EStructuralFeature, HTMLElement<?>> parts = new LinkedHashMap<>();
				
				if (semanticElement.getHeader() != null) {
					parts.put(BootstrapPackage.Literals.MODAL__HEADER, modal.getHeader().toHTMLElement());
				}
				
				if (semanticElement.getBody() != null) {
					parts.put(BootstrapPackage.Literals.MODAL__BODY, modal.getBody().toHTMLElement());
				}
				
				if (semanticElement.getFooter() != null) {
					parts.put(BootstrapPackage.Literals.MODAL__FOOTER, modal.getFooter().toHTMLElement());
				}
				
				return new BiSupplier<Map<EStructuralFeature,HTMLElement<?>>, org.nasdanika.html.bootstrap.Modal>() {
	
					@Override
					public Map<EStructuralFeature, HTMLElement<?>> getFirst() {
						return parts;
					}
	
					@Override
					public org.nasdanika.html.bootstrap.Modal getSecond() {
						return modal;
					}
				};
			}
			
		};
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected Supplier<org.nasdanika.html.bootstrap.Modal> createBootstrapElementSupplier(Context context) {
		MapCompoundConsumerFactory<EStructuralFeature,HTMLElement<?>> partsFactory = new MapCompoundConsumerFactory<>("Parts");
		org.nasdanika.html.model.bootstrap.Modal semanticElement = getTarget();
		
		BootstrapElement header = semanticElement.getHeader();
		if (header != null) {
			partsFactory.put(BootstrapPackage.Literals.MODAL__HEADER, (ConsumerFactory<HTMLElement<?>>) (ConsumerFactory) EObjectAdaptable.adaptToConsumerFactoryNonNull(header, org.nasdanika.html.HTMLElement.class));
		}
		
		BootstrapElement body = semanticElement.getBody();
		if (body != null) {
			partsFactory.put(BootstrapPackage.Literals.MODAL__BODY, (ConsumerFactory<HTMLElement<?>>) (ConsumerFactory) EObjectAdaptable.adaptToConsumerFactoryNonNull(body, org.nasdanika.html.HTMLElement.class));
		}
		
		BootstrapElement footer = semanticElement.getFooter();
		if (footer != null) {
			partsFactory.put(BootstrapPackage.Literals.MODAL__FOOTER, (ConsumerFactory<HTMLElement<?>>) (ConsumerFactory) EObjectAdaptable.adaptToConsumerFactoryNonNull(footer, org.nasdanika.html.HTMLElement.class));
		}
		
		SupplierFactory<BiSupplier<Map<EStructuralFeature, HTMLElement<?>>, org.nasdanika.html.bootstrap.Modal>> modalSupplierFactory = this::createModalSupplier;
		FunctionFactory<BiSupplier<Map<EStructuralFeature, HTMLElement<?>>, org.nasdanika.html.bootstrap.Modal>, org.nasdanika.html.bootstrap.Modal> partsFunctionFactory = partsFactory.asBiSupplierFunctionFactory();
		
		@SuppressWarnings("resource")
		Consumer<org.nasdanika.html.bootstrap.Modal> dismisserBinder = new Consumer<org.nasdanika.html.bootstrap.Modal>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Binding dismisser";
			}

			@Override
			public void execute(org.nasdanika.html.bootstrap.Modal modal, ProgressMonitor progressMonitor) {
				Button dismisser = context.get(HTMLFactory.class, HTMLFactory.INSTANCE).button("x").addClass("close");
				modal.getHeader().toHTMLElement().content(dismisser);
				modal.bindDismisser(dismisser);					
			}
			
		};
		
		Supplier<Modal> modalSupplier = modalSupplierFactory.then(partsFunctionFactory).create(context);
		if (semanticElement.isDismisser() && header != null) {
			return modalSupplier.then(dismisserBinder.asFunction());
		}
		return modalSupplier;
	}

}
