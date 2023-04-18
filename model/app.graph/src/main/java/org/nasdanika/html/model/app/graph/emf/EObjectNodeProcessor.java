package org.nasdanika.html.model.app.graph.emf;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.URINodeProcessor;
import org.nasdanika.ncore.NamedElement;

/**
 * Base class for node processors
 * @author Pavel
 *
 */
public class EObjectNodeProcessor<T> implements URINodeProcessor {
	
//	protected NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config;
	protected Context context;
//	protected T target;
//	protected URI uri;
//	protected Map<EReference, List<Entry<Element, ProcessorInfo<Object, Registry<URI>>>>> groupedChildren;	
	
	public EObjectNodeProcessor(Context context) {
		this.context = context;
	}

//	@SuppressWarnings("unchecked")
//	public EObjectNodeProcessor(
//			NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config,
//			Context context) {
//		
//		this.config = config;
//		this.context = context;
//		this.target = (T) ((EObjectNode) config.getElement()).getTarget();
//		this.uri = URI.createURI("index.html");
//		groupedChildren = org.nasdanika.common.Util.groupBy(config.getChildProcessorsInfo().entrySet(), infoEntry -> ((EReferenceConnection) infoEntry.getKey()).getReference());
//		groupedChildren.values().forEach(l -> l.sort((a,b) -> ((EReferenceConnection) a.getKey()).getIndex() - ((EReferenceConnection) b.getKey()).getIndex()));		
//	}
	
	@Override
	public void resolve(URI base) {
//		uri = uri.resolve(base);
//		
//		Function<Throwable, Void> failureHandler  = failure -> {
//			if (failure != null) {
//				failureConsumer.accept(failure);
//			}
//			return null;
//		};
//		
//		config.getRegistry().thenAccept(registry -> {
//			Map<Element, ProcessorInfo<Object, Registry<URI>>> processorInfoMap = registry.getProcessorInfoMap();
//			
//			// TODO - use endpoints.
//			for (EReferenceConnection outgoingConnection: ((EObjectNode) config.getElement()).getOutgoingConnections()) {
//				ProcessorInfo<Object, Registry<URI>> info = processorInfoMap.get(outgoingConnection);
//				Object cPrc = info.getProcessor(); 
//				if (cPrc instanceof org.nasdanika.html.model.app.graph.NodeProcessor) {
//					((org.nasdanika.html.model.app.graph.NodeProcessor) cPrc).resolve(uri, failureConsumer);
//				}
//			}
//		}).exceptionally(failureHandler);
//		
//		for (Entry<Connection, Consumer<LabelFactory>> incomingHandlerConsumerEntry: config.getIncomingHandlerConsumers().entrySet()) {
//			incomingHandlerConsumerEntry.getValue().accept(new LabelFactory() {
//
//				@Override
//				public Label createLabel() {
//					// TODO Auto-generated method stub
//					return null;
//				}
//
//				@Override
//				public Label createLink(String path) {
//					// TODO Auto-generated method stub
//					return null;
//				}
//				
//			});
//		}
//				
//		for (Entry<Connection, Consumer<LabelFactory>> outgoingHandlerConsumerEntry: config.getOutgoingHandlerConsumers().entrySet()) {
//			
//		}
//		
//		
//		System.out.println(uri);
	}

	protected Collection<Label> createLabels(ProgressMonitor progressMonitor) {		
		return Collections.singleton(createLabel(progressMonitor));
	}
	
	protected Label createLabel(ProgressMonitor progressMonitor) {
		Action testAction = AppFactory.eINSTANCE.createAction();
//		testAction.setText(target.toString());		
//		testAction.setLocation(uri.toString());
		return testAction;
	}

	@Override
	public Label createLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Label createLink(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		return new Supplier<Collection<Label>>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "purum"; //supplierName();
			}

			@Override
			public Collection<Label> execute(ProgressMonitor progressMonitor) {
				return createLabels(progressMonitor);
			}
			
		};
	}
	
//	protected String supplierName() {
//		if (target instanceof NamedElement) {
//			return "Node processor of " + ((NamedElement) target).getName();
//		}
//		if (target instanceof ENamedElement) {
//			return "Node processor of " + ((ENamedElement) target).getName();
//		}
//		return "Node processor of " + target;
//	}

}
