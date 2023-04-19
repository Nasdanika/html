package org.nasdanika.html.model.app.graph.emf;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;

public class EReferenceConnectionProcessor {

//	String path = eRefConn.getPath();
//	URI refURI = URI.createURI(path == null ? eRef.getName() + "/" : eRef.getName() + "/" + path + "/");
	
	// TODO - sourceURI and targetURI?
	
	protected ConnectionProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config;

	public EReferenceConnectionProcessor(ConnectionProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config) {		
		this.config = config;		
		config.getSourceEndpoint().thenAccept(se -> config.setTargetHandler(createTargetHandler(se, config)));
		config.getTargetEndpoint().thenAccept(te -> config.setSourceHandler(createSourceHandler(te, config)));
	}
	
	protected LabelFactory createTargetHandler(LabelFactory sourceEndpoint, ConnectionProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config) {
		// TODO - URI resolution
		return sourceEndpoint;
	}
		
	protected LabelFactory createSourceHandler(LabelFactory targetEndpoint, ConnectionProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config) {
		// TODO - URI resolution
		return targetEndpoint;
	}

}
