package org.nasdanika.html.ecore.gen.processors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.OutgoingEndpoint;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;
import org.nasdanika.html.model.app.graph.Registry;

public class EStructuralFeatureNodeProcessor<T extends EStructuralFeature> extends ETypedElementNodeProcessor<T> {

	public EStructuralFeatureNodeProcessor(
			NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config,
			Context context,
			java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
	}	
	
	/**
	 * Creating a link only if the action has content 
	 */
	@Override
	public Object createLink(URI base, ProgressMonitor progressMonitor) {
		Label action = createAction(progressMonitor);
		if (action instanceof Action && !((Action) action).getContent().isEmpty()) {
			return super.createLink(base, progressMonitor);
		}
		return createLabel(progressMonitor);
	}
	
	private WidgetFactory declaringClassWidgetFactory;
	
	@OutgoingEndpoint("reference.name == 'eContainingClass'")
	public final void setDeclaringClassEndpoint(WidgetFactory declaringClassWidgetFactory) {
		this.declaringClassWidgetFactory = declaringClassWidgetFactory;
	}
	
	@Override
	public Object createWidget(Object selector, URI base, ProgressMonitor progressMonitor) {
		if (selector == EcorePackage.Literals.ESTRUCTURAL_FEATURE__ECONTAINING_CLASS && declaringClassWidgetFactory != null) {
			return declaringClassWidgetFactory.createLink(base, progressMonitor);
		}
		return super.createWidget(selector, base, progressMonitor);
	}	
	
}
