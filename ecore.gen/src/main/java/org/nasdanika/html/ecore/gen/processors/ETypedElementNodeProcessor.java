package org.nasdanika.html.ecore.gen.processors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.OutgoingEndpoint;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;

public class ETypedElementNodeProcessor<T extends ETypedElement> extends EModelElementNodeProcessor<T> {

	public ETypedElementNodeProcessor(
			NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config,
			Context context,
			java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
	}	
	
	private LabelFactory typeLabelFactory;
	
	@OutgoingEndpoint("reference.name == 'eType'")
	public void setTypeEndpoint(LabelFactory typeLabelFactory) {
		this.typeLabelFactory = typeLabelFactory;
	}
	
	@Override
	public Label createLink(Object selector, String path, ProgressMonitor progressMonitor) {
		if (selector == EcorePackage.Literals.ETYPED_ELEMENT__ETYPE && typeLabelFactory != null) {
			return typeLabelFactory.createLink(progressMonitor);
		}
		return super.createLink(selector, path, progressMonitor);
	}

}
