package org.nasdanika.html.model.app.graph.emf;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.ENamedElement;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Processor;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.URINodeProcessor;
import org.nasdanika.ncore.NamedElement;

/**
 * Base class for node processors
 * @author Pavel
 *
 */
public class EObjectNodeProcessor<T> implements URINodeProcessor {
	
	protected NodeProcessorConfig<Processor, LabelFactory, LabelFactory, Registry<URI>> config;
	protected Context context;
	protected T target;
	protected URI uri;

	@SuppressWarnings("unchecked")
	public EObjectNodeProcessor(
			NodeProcessorConfig<Processor, LabelFactory, LabelFactory, Registry<URI>> config,
			Context context) {
		
		this.config = config;
		this.context = context;
		this.target = (T) ((EObjectNode) config.getElement()).getTarget();
		this.uri = URI.createURI("index.html");
	}
	
	@Override
	public void resolveURI(URI base, Consumer<Throwable> failureConsumer) {
		uri = uri.resolve(base);
		
		Function<Throwable, Void> failureHandler  = failure -> {
			if (failure != null) {
				failureConsumer.accept(failure);
			}
			return null;
		};
		
		config.getRegistry().thenAccept(registry -> {
			Map<Element, ProcessorInfo<Processor, Registry<URI>>> processorInfoMap = registry.getProcessorInfoMap();
			for (EReferenceConnection outgoingConnection: ((EObjectNode) config.getElement()).getOutgoingConnections()) {
				ProcessorInfo<Processor, Registry<URI>> info = processorInfoMap.get(outgoingConnection);
				Processor cPrc = info.getProcessor(); 
				if (cPrc instanceof org.nasdanika.html.model.app.graph.Processor) {
					((org.nasdanika.html.model.app.graph.Processor) cPrc).resolveURI(uri, failureConsumer);
				}
			}
		}).exceptionally(failureHandler);
		
		for (Entry<Connection, Consumer<LabelFactory>> incomingHandlerConsumerEntry: config.getIncomingHandlerConsumers().entrySet()) {
			incomingHandlerConsumerEntry.getValue().accept(new LabelFactory() {

				@Override
				public Label createLabel() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public Label createLink() {
					// TODO Auto-generated method stub
					return null;
				}
				
			});
		}
				
		for (Entry<Connection, Consumer<LabelFactory>> outgoingHandlerConsumerEntry: config.getOutgoingHandlerConsumers().entrySet()) {
			
		}
		
		
		System.out.println(uri);
	}

	@Override
	public Collection<Label> execute(ProgressMonitor progressMonitor) {
				
		// TODO Auto-generated method stub - builders, contributors (functions, then) etc.
		return null;
	}

	@Override
	public double size() {
		return 1;
	}

	@Override
	public String name() {
		if (target instanceof NamedElement) {
			return "Node processor of " + ((NamedElement) target).getName();
		}
		if (target instanceof ENamedElement) {
			return "Node processor of " + ((ENamedElement) target).getName();
		}
		return "Node processor of " + target;
	}

}
