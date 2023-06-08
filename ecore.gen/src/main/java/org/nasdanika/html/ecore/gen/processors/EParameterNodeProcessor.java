package org.nasdanika.html.ecore.gen.processors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.OutgoingEndpoint;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class EParameterNodeProcessor extends ETypedElementNodeProcessor<EParameter> {

	public EParameterNodeProcessor(
			NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config,
			Context context,
			java.util.function.Function<ProgressMonitor, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
	}	
	
	private WidgetFactory operationWidgetFactory;
	
	@OutgoingEndpoint("reference.name == 'eOperation'")
	public final void setDeclaringClassEndpoint(WidgetFactory operationWidgetFactory) {
		this.operationWidgetFactory = operationWidgetFactory;
	}
	
	@Override
	public Object createWidget(Object selector, URI base, ProgressMonitor progressMonitor) {
		if (selector == EcorePackage.Literals.EPARAMETER__EOPERATION && operationWidgetFactory != null) {
			return operationWidgetFactory.createLink(base, progressMonitor);
		}
		return super.createWidget(selector, base, progressMonitor);
	}	

}
