package org.nasdanika.html.model.app.graph.emf;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;

public class EReferenceConnectionProcessor {
	
	protected ConnectionProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config;

	public EReferenceConnectionProcessor(ConnectionProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config) {		
		this.config = config;
		// TODO - propagate resolve via handlers/endpoints
		
//		config.getRegistry().thenAccept(registry -> {
//			ProcessorInfo<Object, Registry<URI>> targetInfo = registry.getProcessorInfoMap().get(config.getElement().getTarget());
//			targetProcessor = (EObjectNodeProcessor<?>) targetInfo.getProcessor();			
//		});
	}

//	@Override
//	public void resolveURI(URI base, Consumer<Throwable> failureConsumer) {
//		Function<Throwable, Void> failureHandler  = failure -> {
//			if (failure != null) {
//				failureConsumer.accept(failure);
//			}
//			return null;
//		};
//		EReferenceConnection eRefConn = ((EReferenceConnection) config.getElement());
//		EReference eRef = eRefConn.getReference();
//		if (eRef.isContainment()) {
//			String path = eRefConn.getPath();
//			URI refURI = URI.createURI(path == null ? eRef.getName() + "/" : eRef.getName() + "/" + path + "/");			
//			URI resolved = refURI.resolve(base);
//			EObjectNode target = ((EReferenceConnection) config.getElement()).getTarget();
//			config.getRegistry().thenAccept(registry -> {
//				ProcessorInfo<Processor, Registry<URI>> targetInfo = registry.getProcessorInfoMap().get(target);
//				Processor targetProcessor = targetInfo.getProcessor();
//				if (targetProcessor instanceof org.nasdanika.html.model.app.graph.Processor) {
//					((org.nasdanika.html.model.app.graph.Processor) targetProcessor).resolveURI(resolved, failureConsumer);
//				}
//			}).exceptionally(failureHandler);
//		}
//	}
//
//	@Override
//	public Collection<Label> execute(ProgressMonitor progressMonitor) {
//		return targetProcessor == null ? Collections.emptySet() : targetProcessor.apply(progressMonitor);
//	}
//
//	@Override
//	public double size() {
//		return targetProcessor == null ? 0 : targetProcessor.size();
//	}
//
//	@Override
//	public String name() {
//		return "EReference connection processor: " + (((EReferenceConnection) config.getElement())).getReference().getName();
//	}
//	
//	@Override
//	public Diagnostic diagnose(ProgressMonitor progressMonitor) {
//		return targetProcessor == null ? ConnectionProcessor.super.diagnose(progressMonitor) : targetProcessor.diagnose(progressMonitor);
//	}
//	
//	@Override
//	public void commit(ProgressMonitor progressMonitor) {
//		if (targetProcessor != null) {
//			targetProcessor.commit(progressMonitor);
//		}
//	}
//	
//	@Override
//	public boolean rollback(ProgressMonitor progressMonitor) {
//		return targetProcessor == null ? ConnectionProcessor.super.rollback(progressMonitor) : targetProcessor.rollback(progressMonitor);
//	}
//	
//	@Override
//	public void close() {
//		if (targetProcessor != null) {
//			targetProcessor.close();
//		}
//	}

}
