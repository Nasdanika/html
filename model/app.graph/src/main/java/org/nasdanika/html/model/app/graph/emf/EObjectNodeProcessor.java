package org.nasdanika.html.model.app.graph.emf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.CollectionCompoundConsumer;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.ChildProcessors;
import org.nasdanika.graph.processor.IncomingEndpoint;
import org.nasdanika.graph.processor.IncomingHandler;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.OutgoingEndpoint;
import org.nasdanika.graph.processor.OutgoingHandler;
import org.nasdanika.graph.processor.ParentProcessor;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.URINodeProcessor;

/**
 * Base class for node processors.
 * Groups connections by reference, creates a consumer per reference (builder), chains the labels supplier with the consumers.
 * @author Pavel
 *
 */
public class EObjectNodeProcessor<T> implements URINodeProcessor {
	
	public EObjectNodeProcessor(NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config, Context context) {
		this.config = config;
		this.context = context;
	}
	
	protected Map<EObjectNode, ProcessorInfo<Object, Registry<URI>>> childProcessors;
	
	@ChildProcessors
	public void setChildProcessors(Map<EObjectNode, ProcessorInfo<Object, Registry<URI>>> childProcessors) {
		this.childProcessors = childProcessors;
	}
	
	protected EReferenceConnectionProcessor parentProcessor;
	
	@ParentProcessor
	public void setParentProcessor(EReferenceConnectionProcessor parentProcessor) {
		this.parentProcessor = parentProcessor;
	}

	protected EObjectNode node;
	
	@ProcessorElement
	public void setNode(EObjectNode node) {
		this.node = node;
	}
	
	protected Map<EReferenceConnection, LabelFactory> incomingEndpoints = new LinkedHashMap<>();
	protected Map<EReferenceConnection, LabelFactory> outgoingEndpoints = new LinkedHashMap<>();
	
	@IncomingEndpoint
	public void setIncomingEndpoint(EReferenceConnection connection, LabelFactory endpoint) {
		incomingEndpoints.put(connection, endpoint);
	}
		
	@OutgoingEndpoint
	public void setOutgoingEndpoint(EReferenceConnection connection, LabelFactory endpoint) {
		outgoingEndpoints.put(connection, endpoint);
	}
	
	@IncomingHandler
	public LabelFactory getIncomingHandler(EReferenceConnection connection) {
		return this;
	}
		
	@OutgoingHandler
	public LabelFactory getOutgoingHandler(EReferenceConnection connection) {
		return this;
	}
	
	protected NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config;
	protected Context context;
//	protected T target;
//	protected URI uri;
//	protected Map<EReference, List<Entry<Element, ProcessorInfo<Object, Registry<URI>>>>> groupedChildren;	

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

	/**
	 * @return Supplier of labels with object's own information, without references.
	 * Reference-related information is added by reference consumers/builders.  
	 */
	protected Supplier<Collection<Label>> doCreateLabelsSupplier() {
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
	
//	/**
//	 * Override to filter out references which should not contribute to label building.
//	 * @param eReference
//	 * @return
//	 */
//	protected boolean isBuilderReference(EReference eReference) {
//		return true; // TODO - from configuration and possibly annotations
//	}
	
	/**
	 * Comparator for reference sorting
	 * @param a
	 * @param b
	 * @return
	 */
	protected int compareIncomingReferences(EReference a, EReference b) {
		return a.getName().compareTo(b.getName());
	}
	
	/**
	 * Comparator for reference sorting
	 * @param a
	 * @param b
	 * @return
	 */
	protected int compareOutgoingReferences(EReference a, EReference b) {
		return a.getName().compareTo(b.getName());
	}
	
	/**
	 * 
	 * @return
	 */
	protected Consumer<Collection<Label>> createIncomingReferenceLabelConsumer(EReference eReference, List<Entry<EReferenceConnection, LabelFactory>> referenceIncomingEndpoints) {
		return null;
	}
	
	protected List<Consumer<Collection<Label>>> getReferenceLabelBuilders() {		
		List<Consumer<Collection<Label>>> ret = new ArrayList<>();
		
		Map<EReference, List<Entry<EReferenceConnection, LabelFactory>>> groupedOutgoingEndpoints = org.nasdanika.common.Util.groupBy(outgoingEndpoints.entrySet(), e -> e.getKey().getReference());
		groupedOutgoingEndpoints
			.entrySet()
			.stream()
			.sorted((a, b) -> compareOutgoingReferences(a.getKey(), b.getKey()))
			.map(e -> createIncomingReferenceLabelConsumer(e.getKey(), e.getValue()))
			.filter(Objects::nonNull)
			.forEach(ret::add);
		
		Map<EReference, List<Entry<EReferenceConnection, LabelFactory>>> groupedIncomingEndpoints = org.nasdanika.common.Util.groupBy(incomingEndpoints.entrySet(), e -> e.getKey().getReference());
		groupedIncomingEndpoints
			.entrySet()
			.stream()
			.sorted((a, b) -> compareIncomingReferences(a.getKey(), b.getKey()))
			.map(e -> createIncomingReferenceLabelConsumer(e.getKey(), e.getValue()))
			.filter(Objects::nonNull)
			.forEach(ret::add);
		
		return ret;
	}
	
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		List<Consumer<Collection<Label>>> referenceLabelBuilders = getReferenceLabelBuilders();
		if (referenceLabelBuilders == null || referenceLabelBuilders.isEmpty()) {
			return doCreateLabelsSupplier();
		}
		@SuppressWarnings("resource")
		CollectionCompoundConsumer<Collection<Label>> collectionCompoundConsumer = new CollectionCompoundConsumer<Collection<Label>>("Reference Label Builder");
		referenceLabelBuilders.forEach(collectionCompoundConsumer::add);
		return doCreateLabelsSupplier().then(collectionCompoundConsumer.asFunction());
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
