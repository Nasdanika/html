package org.nasdanika.html.model.app.graph.drawio;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.graph.processor.SourceHandler;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;

public class ConnectionProcessor extends LinkTargetProcessor<Connection> {

	public ConnectionProcessor(DrawioProcessorFactory factory) {
		super(factory);
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
		configureLabel(action);
		action.setLocation(element.getId() + "/index.html");
		return action; 
	}
	
	@Override
	public void configureLabel(Label label) {
		super.configureLabel(label);
		if (Util.isBlank(label.getIcon())) {
			label.setIcon("fas fa-long-arrow-alt-right");
		}
	}	
	
	@SourceHandler
	public ConnectionProcessor getSourceHandler() {
		return this;
	}

}
