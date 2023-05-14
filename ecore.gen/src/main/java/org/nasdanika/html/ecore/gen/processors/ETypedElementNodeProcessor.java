package org.nasdanika.html.ecore.gen.processors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.OutgoingEndpoint;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class ETypedElementNodeProcessor<T extends ETypedElement> extends EModelElementNodeProcessor<T> {

	public ETypedElementNodeProcessor(
			NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config,
			Context context,
			java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
	}	
	
	private WidgetFactory typeWidgetFactory;
	
	@OutgoingEndpoint("reference.name == 'eType'")
	public final void setTypeEndpoint(WidgetFactory typeWidgetFactory) {
		this.typeWidgetFactory = typeWidgetFactory;
	}
	
	private WidgetFactory genericTypeWidgetFactory;
	
	@OutgoingEndpoint("reference.name == 'eGenericType'")
	public final void setGenericTypeEndpoint(WidgetFactory genericTypeWidgetFactory) {
		this.genericTypeWidgetFactory = genericTypeWidgetFactory;
	}
	
	@Override
	public Object createWidget(Object selector, URI base, ProgressMonitor progressMonitor) {
		if (selector == EcorePackage.Literals.ETYPED_ELEMENT__ETYPE && typeWidgetFactory != null) {
			return typeWidgetFactory.createLink(base, progressMonitor);
		}
		if (selector == EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE && genericTypeWidgetFactory != null) {
			return genericTypeWidgetFactory.createLink(base, progressMonitor);
		}
		if (selector instanceof EClassNodeProcessor.ReifiedTypeSelector) {
			EClassNodeProcessor.ReifiedTypeSelector reifiedTypeSelector = (EClassNodeProcessor.ReifiedTypeSelector) selector;
			if (reifiedTypeSelector.getSelector() == EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE) {
				EGenericType genericType = getTarget().getEGenericType();
				WidgetFactory rwf = reifiedTypeSelector.getReifiedTypeWidgetFactory(genericType);
				if (rwf == null) {
					if (genericTypeWidgetFactory != null) {
						return genericTypeWidgetFactory.createLink(base, progressMonitor);
					}
				}
				return rwf.createLink(base, progressMonitor);
			}
		}		
		
		return super.createWidget(selector, base, progressMonitor);
	}

}
