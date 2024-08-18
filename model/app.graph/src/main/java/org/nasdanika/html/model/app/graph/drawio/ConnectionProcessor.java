package org.nasdanika.html.model.app.graph.drawio;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connection;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.SourceHandler;
import org.nasdanika.html.model.app.Label;

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
	public void configureLabel(Label label, ProgressMonitor progressMonitor) {
		super.configureLabel(label, progressMonitor);
		if (Util.isBlank(label.getIcon())) {
			label.setIcon("fas fa-long-arrow-alt-right");
		}
	}	
	
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		if (element.getId().equals("send-email-using")) {
			System.out.println("Here we are!");
		}
		// TODO Auto-generated method stub
		return super.createLabelsSupplier();
	}
	
	@SourceHandler
	public ConnectionProcessor getSourceHandler() {
		return this;
	}

}
