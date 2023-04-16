package org.nasdanika.html.model.app.graph.emf;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.ConnectionProcessor;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Processor;
import org.nasdanika.html.model.app.graph.Registry;

public class EReferenceConnectionProcessor implements ConnectionProcessor<URI> {
	
	public EReferenceConnectionProcessor(
			ConnectionProcessorConfig<Processor, LabelFactory, LabelFactory, Registry<URI>> config,
			ProgressMonitor progressMonitor) {
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public void resolveURI(URI base) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Label> execute(ProgressMonitor progressMonitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

}
