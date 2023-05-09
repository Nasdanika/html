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
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;

public class EStructuralFeatureNodeProcessor<T extends EStructuralFeature> extends ETypedElementNodeProcessor<T> {

	public EStructuralFeatureNodeProcessor(
			NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config,
			Context context,
			java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
	}	
	
	/**
	 * Creating a link only if the action has content 
	 */
	@Override
	public Label createLink(String path, ProgressMonitor progressMonitor) {
		Label action = createAction(progressMonitor);
		if (action instanceof Action && !((Action) action).getContent().isEmpty()) {
			return super.createLink(path, progressMonitor);
		}
		return createLabel(progressMonitor);
	}
	
	private LabelFactory declaringClassLabelFactory;
	
	@OutgoingEndpoint("reference.name == 'eContainingClass'")
	public void setDeclaringClassEndpoint(LabelFactory declaringClassLabelFactory) {
		this.declaringClassLabelFactory = declaringClassLabelFactory;
	}
	
	@Override
	public Label createLink(Object selector, String path, ProgressMonitor progressMonitor) {
		if (selector == EcorePackage.Literals.ESTRUCTURAL_FEATURE__ECONTAINING_CLASS && declaringClassLabelFactory != null) {
			return declaringClassLabelFactory.createLink(progressMonitor);
		}
		return super.createLink(selector, path, progressMonitor);
	}	
	
}
