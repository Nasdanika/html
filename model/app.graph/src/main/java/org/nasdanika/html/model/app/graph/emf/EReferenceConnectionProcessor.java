package org.nasdanika.html.model.app.graph.emf;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.ConnectionProcessor;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Processor;
import org.nasdanika.html.model.app.graph.Registry;

public class EReferenceConnectionProcessor implements ConnectionProcessor<URI> {
	
	protected ConnectionProcessorConfig<Processor, LabelFactory, LabelFactory, Registry<URI>> config;

	public EReferenceConnectionProcessor(ConnectionProcessorConfig<Processor, LabelFactory, LabelFactory, Registry<URI>> config) {		
		this.config = config;
	}

	@Override
	public void resolveURI(URI base, Consumer<Throwable> failureConsumer) {
		Function<Throwable, Void> failureHandler  = failure -> {
			if (failure != null) {
				failureConsumer.accept(failure);
			}
			return null;
		};
		EReferenceConnection eRefConn = ((EReferenceConnection) config.getElement());
		EReference eRef = eRefConn.getReference();
		if (eRef.isContainment()) {
			String path = eRefConn.getPath();
			URI refURI = URI.createURI(path == null ? eRef.getName() + "/" : eRef.getName() + "/" + path + "/");			
			URI resolved = refURI.resolve(base);
			EObjectNode target = ((EReferenceConnection) config.getElement()).getTarget();
			config.getRegistry().thenAccept(registry -> {
				ProcessorInfo<Processor, Registry<URI>> targetInfo = registry.getProcessorInfoMap().get(target);
				Processor targetProcessor = targetInfo.getProcessor();
				if (targetProcessor instanceof org.nasdanika.html.model.app.graph.Processor) {
					((org.nasdanika.html.model.app.graph.Processor) targetProcessor).resolveURI(resolved, failureConsumer);
				}
			}).exceptionally(failureHandler);
		}
	}

	@Override
	public Collection<Label> execute(ProgressMonitor progressMonitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double size() {
		return 1;
	}

	@Override
	public String name() {
		return "EReference connection processor: " + (((EReferenceConnection) config.getElement())).getReference().getName();
	}

}
