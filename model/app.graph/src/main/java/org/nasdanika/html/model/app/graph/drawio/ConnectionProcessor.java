package org.nasdanika.html.model.app.graph.drawio;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.SourceHandler;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class ConnectionProcessor extends LayerElementProcessor<Connection> {

	public ConnectionProcessor(DrawioProcessorFactory factory) {
		super(factory);
	}

	@ProcessorElement
	@Override
	public void setElement(Connection element) {
		super.setElement(element);
		uri = URI.createURI(element.getId() + "/index.html");
	}
	
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		if (element.isTargetLink()) {
			LinkTarget linkTarget = element.getLinkTarget();
			if (linkTarget instanceof Page) {
				ProcessorInfo<WidgetFactory> ppi = registry.get(linkTarget);
				Supplier<Collection<Label>> pageLabelSupplier = ppi.getProcessor().createLabelsSupplier();
				return super.createLabelsSupplier().then(pageLabelSupplier.asFunction(this::addPageLabels));
			}
		}
		
		return super.createLabelsSupplier();
	}

	@Override
	public Object createLabel(ProgressMonitor progressMonitor) {
		Collection<EObject> documentation = getDocumentation(progressMonitor);
		if (documentation.isEmpty()) {
			return null;
		}
		
		Action action = AppFactory.eINSTANCE.createAction();
		action.setText(element.getLabel());
		action.getContent().addAll(documentation);
		configureLabel(action, progressMonitor);
		action.setLocation(uri.toString());
		return action; 
	}
	
	@Override
	public void configureLabel(Label label, ProgressMonitor progressMonitor) {
		super.configureLabel(label, progressMonitor);
		if (Util.isBlank(label.getIcon())) {
			label.setIcon("fas fa-long-arrow-alt-right");
		}
	}	
	
	@SourceHandler
	public ConnectionProcessor getSourceHandler() {
		return this;
	}

}
