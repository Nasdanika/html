
package org.nasdanika.html.model.app.graph.emf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.CollectionCompoundConsumer;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.Context;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.common.Function;
import org.nasdanika.common.MapCompoundSupplier;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Supplier.FunctionResult;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.graph.emf.Connection;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.EOperationConnection;
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
import org.nasdanika.html.Tag;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.gen.AppAdapterFactory;
import org.nasdanika.html.model.app.graph.WidgetFactory;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.util.SemanticInfo;

/**
 * Base class for node processors.
 * Groups connections by reference, creates a consumer per reference (builder), chains the labels supplier with the consumers.
 * @author Pavel
 *
 */
public class EObjectNodeProcessor<T extends EObject> implements WidgetFactory {
	
	protected java.util.function.Function<ProgressMonitor, Action> prototypeProvider;
	protected NodeProcessorConfig<WidgetFactory, WidgetFactory> config;
	protected Context context;
	protected URI uri;
	
	public EObjectNodeProcessor(
			NodeProcessorConfig<WidgetFactory, WidgetFactory> config, 
			Context context, 
			java.util.function.Function<ProgressMonitor, Action> prototypeProvider) {		
		this.config = config;
		this.context = context;
		this.prototypeProvider = prototypeProvider;
		this.uri = URI.createURI("index.html");
	}
	
	protected Map<EObjectNode, ProcessorInfo<Object>> childProcessors;
	
	@ChildProcessors
	public void setChildProcessors(Map<EObjectNode, ProcessorInfo<Object>> childProcessors) {
		this.childProcessors = childProcessors;
	}
	
	protected ConnectionProcessor parentProcessor;
	
	@ParentProcessor
	public void setParentProcessor(ConnectionProcessor parentProcessor) {
		this.parentProcessor = parentProcessor;
	}

	protected EObjectNode node;
	
	@ProcessorElement
	public void setNode(EObjectNode node) {
		this.node = node;
	}
	
	@SuppressWarnings("unchecked")
	public T getTarget() {
		return (T) node.getTarget();
	}
	
	public NodeProcessorConfig<WidgetFactory, WidgetFactory> getConfig() {
		return config;
	}
	
	protected Map<EReferenceConnection, WidgetFactory> incomingReferenceEndpoints = new LinkedHashMap<>();
	protected Map<EReferenceConnection, WidgetFactory> outgoingReferenceEndpoints = new LinkedHashMap<>();
	
	@IncomingEndpoint
	public void setIncomingRefernceEndpoint(EReferenceConnection connection, WidgetFactory endpoint) {
		incomingReferenceEndpoints.put(connection, endpoint);
	}
		
	@OutgoingEndpoint
	public void setOutgoingRefernceEndpoint(EReferenceConnection connection, WidgetFactory endpoint) {
		outgoingReferenceEndpoints.put(connection, endpoint);
	}
	
	protected Map<EOperationConnection, WidgetFactory> incomingOperationEndpoints = new LinkedHashMap<>();
	protected Map<EOperationConnection, WidgetFactory> outgoingOperationEndpoints = new LinkedHashMap<>();
	
	@IncomingEndpoint
	public void setIncominOperationgEndpoint(EOperationConnection connection, WidgetFactory endpoint) {
		incomingOperationEndpoints.put(connection, endpoint);
	}
		
	@OutgoingEndpoint
	public void setOutgoingOperationEndpoint(EOperationConnection connection, WidgetFactory endpoint) {
		outgoingOperationEndpoints.put(connection, endpoint);
	}
		
	@IncomingHandler
	public WidgetFactory getIncomingHandler(Connection connection) {		
		return this;
	}
	
	@OutgoingHandler
	public WidgetFactory getOutgoingHandler(Connection connection) {
		return this;
	}

	protected Collection<Label> createLabels(ProgressMonitor progressMonitor) {		
		return Collections.singleton(createAction(progressMonitor));
	}
	
	/**
	 * Creates action for the node.
	 * @param progressMonitor
	 * @return
	 */
	protected Label createAction(ProgressMonitor progressMonitor) {
		return createAction(getTarget(), progressMonitor);
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
				return "Labels supplier for " + getTarget();
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
	 * Comparator for operation binding sorting
	 * @return
	 */
	protected int compareIncomingOperations(EOperation aOp, List<Object> aArgs, EOperation bOp, List<Object> bArgs) {
		return aOp.getName().compareTo(bOp.getName());
	}
	
	/**
	 * Comparator for operator binding sorting
	 * @return
	 */
	protected int compareOutgoingOperations(EOperation aOp, List<Object> aArgs, EOperation bOp, List<Object> bArgs) {
		return aOp.getName().compareTo(bOp.getName());
	}	
	
	protected List<Consumer<Collection<Label>>> getReferenceLabelBuilders() {		
		List<Consumer<Collection<Label>>> ret = new ArrayList<>();
		
		Map<EReference, List<Entry<EReferenceConnection, WidgetFactory>>> groupedOutgoingReferenceEndpoints = org.nasdanika.common.Util.groupBy(outgoingReferenceEndpoints.entrySet(), e -> e.getKey().getReference());
		groupedOutgoingReferenceEndpoints
			.entrySet()
			.stream()
			.sorted((a, b) -> compareOutgoingReferences(a.getKey(), b.getKey()))
			.map(e -> createOutgoingReferenceLabelConsumer(e.getKey(), e.getValue()))
			.filter(Objects::nonNull)
			.forEach(ret::add);
		
		Map<EReference, List<Entry<EReferenceConnection, WidgetFactory>>> groupedIncomingReferenceEndpoints = org.nasdanika.common.Util.groupBy(incomingReferenceEndpoints.entrySet(), e -> e.getKey().getReference());
		groupedIncomingReferenceEndpoints
			.entrySet()
			.stream()
			.sorted((a, b) -> compareIncomingReferences(a.getKey(), b.getKey()))
			.map(e -> createIncomingReferenceLabelConsumer(e.getKey(), e.getValue()))
			.filter(Objects::nonNull)
			.forEach(ret::add);
		
		return ret;
	}
	
	protected List<Consumer<Collection<Label>>> getOperationLabelBuilders() {		
		List<Consumer<Collection<Label>>> ret = new ArrayList<>();		
		record Binding(EOperation operation, List<Object> arguments) {};

		Map<Binding, List<Entry<EOperationConnection, WidgetFactory>>> groupedOutgoingOperationEndpoints = org.nasdanika.common.Util.groupBy(outgoingOperationEndpoints.entrySet(), e -> new Binding(e.getKey().getOperation(), e.getKey().getArguments()));
		groupedOutgoingOperationEndpoints
			.entrySet()
			.stream()
			.sorted((a, b) -> compareOutgoingOperations(a.getKey().operation(), a.getKey().arguments(), b.getKey().operation(), b.getKey().arguments()))
			.map(e -> createOutgoingOperationLabelConsumer(e.getKey().operation(), e.getKey().arguments(), e.getValue()))
			.filter(Objects::nonNull)
			.forEach(ret::add);
		
		Map<Binding, List<Entry<EOperationConnection, WidgetFactory>>> groupedIncomingOperationEndpoints = org.nasdanika.common.Util.groupBy(incomingOperationEndpoints.entrySet(), e -> new Binding(e.getKey().getOperation(), e.getKey().getArguments()));
		groupedIncomingOperationEndpoints
			.entrySet()
			.stream()
			.sorted((a, b) -> compareIncomingOperations(a.getKey().operation(), a.getKey().arguments(), b.getKey().operation(), b.getKey().arguments()))
			.map(e -> createIncomingOperationLabelConsumer(e.getKey().operation(), e.getKey().arguments(), e.getValue()))
			.filter(Objects::nonNull)
			.forEach(ret::add);
				
		return ret;
	}
	
	// --- Label building methods ---
	
	/**
	 * 
	 * @param eReference
	 * @return true if lables suppliers shall be called to create labels/actions. 
	 * This implementation returns false. 
	 */
	protected boolean isCallIncomingReferenceLabelsSuppliers(EReference eReference) {
		return false;
	}
	
	/**
	 * 
	 * @param eReference
	 * @return true if lables suppliers shall be called to create labels/actions. 
	 * This implementation returns true for containment references, i.e. actions for child objects shall be created. 
	 */
	protected boolean isCallOutgoingReferenceLabelsSuppliers(EReference eReference) {
		return eReference != null && eReference.isContainment();
	}
		
	/**
	 * 
	 * @param eOperation
	 * @return true if lables suppliers shall be called to create labels/actions. 
	 * This implementation returns false. 
	 */
	protected boolean isCallIncomingOperationLabelsSuppliers(EOperation eOperation, List<Object> arguments) {
		return false;
	}
	
	/**
	 * 
	 * @param eOperation
	 * @return true if lables suppliers shall be called to create labels/actions. 
	 * This implementation returns false. 
	 */
	protected boolean isCallOutgoingOperationLabelsSuppliers(EOperation eOperation, List<Object> arguments) {
		return false;
	}	
		
	/**
	 * 
	 * @return
	 */
	protected Consumer<Collection<Label>> createIncomingReferenceLabelConsumer(
			EReference eReference, 
			List<Entry<EReferenceConnection, WidgetFactory>> referenceIncomingEndpoints) {
		
		@SuppressWarnings("resource")
		MapCompoundSupplier<EReferenceConnection, Collection<Label>> endpointLabelsSupplier = new MapCompoundSupplier<>("Incoming reference endpoints supplier");
		if (isCallIncomingReferenceLabelsSuppliers(eReference)) {
			for (Entry<EReferenceConnection, WidgetFactory> e: referenceIncomingEndpoints) {
				endpointLabelsSupplier.put(e.getKey(), e.getValue().createLabelsSupplier());
			}
		}
		
		Consumer<Supplier.FunctionResult<Collection<Label>, Map<EReferenceConnection, Collection<Label>>>> referenceLabelBuilder = createIncomingReferenceLabelBuilder(eReference, referenceIncomingEndpoints);						
		Function<Collection<Label>, FunctionResult<Collection<Label>, Map<EReferenceConnection, Collection<Label>>>> endpointLabelsFunction = endpointLabelsSupplier.asFunction();
		return endpointLabelsFunction.then(referenceLabelBuilder);
	}
	
	/**
	 * Builds target labels
	 * @param eReference
	 * @return
	 */
	protected Consumer<Supplier.FunctionResult<Collection<Label>, Map<EReferenceConnection, Collection<Label>>>> createIncomingReferenceLabelBuilder(
			EReference eReference,
			List<Entry<EReferenceConnection, WidgetFactory>> referenceIncomingEndpoints) {
		
		return new Consumer<Supplier.FunctionResult<Collection<Label>,Map<EReferenceConnection,Collection<Label>>>>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Incoming reference label builder";
			}
			
			@Override
			public void execute(FunctionResult<Collection<Label>, Map<EReferenceConnection, Collection<Label>>> arg, ProgressMonitor progressMonitor) {
				buildIncomingReference(eReference, referenceIncomingEndpoints, arg.argument(), arg.result(), progressMonitor);
			}
		};
		
	}
	
	/**
	 * Called by builder/consumer's execute();
	 * @param eReference
	 * @param referenceIncomingEndpoints
	 * @param labels
	 * @param incomingLabels
	 * @param progressMonitor
	 */
	protected void buildIncomingReference(
			EReference eReference,
			List<Entry<EReferenceConnection, WidgetFactory>> referenceIncomingEndpoints,
			Collection<Label> labels,
			Map<EReferenceConnection, Collection<Label>> incomingLabels,
			ProgressMonitor progressMonitor) {

		for (Method method: getClass().getMethods()) {
			IncomingReferenceBuilder irb = method.getAnnotation(IncomingReferenceBuilder.class);
			if (irb != null 
					&& eReference.getFeatureID() == irb.referenceID()
					&& eReference.getEContainingClass().getClassifierID() == irb.classID()
					&& eReference.getEContainingClass().getEPackage().getNsURI().equals(irb.nsURI())) {
				int pc = method.getParameterCount();
				if (pc == 4) {
					try {
						method.invoke(this, referenceIncomingEndpoints, labels, incomingLabels, progressMonitor);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new ExecutionException(e);
					}
				} else if (pc == 5) {
					try {
						method.invoke(this, eReference, referenceIncomingEndpoints, labels, incomingLabels, progressMonitor);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new ExecutionException(e);
					}					
				} else {
					throw new NasdanikaException("A method anotated with " + IncomingReferenceBuilder.class + " shall have 4 or 5 parameters: " + method);
				}
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	protected Consumer<Collection<Label>> createIncomingOperationLabelConsumer(
			EOperation eOperation,
			List<Object> arguments,
			List<Entry<EOperationConnection, WidgetFactory>> operationIncomingEndpoints) {
		
		@SuppressWarnings("resource")
		MapCompoundSupplier<EOperationConnection, Collection<Label>> endpointLabelsSupplier = new MapCompoundSupplier<>("Incoming operation endpoints supplier");
		if (isCallIncomingOperationLabelsSuppliers(eOperation, arguments)) {
			for (Entry<EOperationConnection, WidgetFactory> e: operationIncomingEndpoints) {
				endpointLabelsSupplier.put(e.getKey(), e.getValue().createLabelsSupplier());
			}
		}
		
		Consumer<Supplier.FunctionResult<Collection<Label>, Map<EOperationConnection, Collection<Label>>>> operationLabelBuilder = createIncomingOperationLabelBuilder(eOperation, arguments, operationIncomingEndpoints);						
		Function<Collection<Label>, FunctionResult<Collection<Label>, Map<EOperationConnection, Collection<Label>>>> endpointLabelsFunction = endpointLabelsSupplier.asFunction();
		return endpointLabelsFunction.then(operationLabelBuilder);
	}
	
	/**
	 * Builds target labels
	 * @param eReference
	 * @return
	 */
	protected Consumer<Supplier.FunctionResult<Collection<Label>, Map<EOperationConnection, Collection<Label>>>> createIncomingOperationLabelBuilder(
			EOperation eOperation,
			List<Object> arguments,
			List<Entry<EOperationConnection, WidgetFactory>> operationIncomingEndpoints) {
		
		return new Consumer<Supplier.FunctionResult<Collection<Label>,Map<EOperationConnection,Collection<Label>>>>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Incoming operation label builder";
			}
			
			@Override
			public void execute(FunctionResult<Collection<Label>, Map<EOperationConnection, Collection<Label>>> arg, ProgressMonitor progressMonitor) {
				buildIncomingOperation(eOperation, arguments, operationIncomingEndpoints, arg.argument(), arg.result(), progressMonitor);
			}
		};
		
	}
	
	/**
	 * Called by builder/consumer's execute();
	 */
	protected void buildIncomingOperation(
			EOperation eOperation,
			List<Object> arguments,
			List<Entry<EOperationConnection, WidgetFactory>> operationIncomingEndpoints,
			Collection<Label> labels,
			Map<EOperationConnection, Collection<Label>> incomingLabels,
			ProgressMonitor progressMonitor) {
		
		for (Method method: getClass().getMethods()) {
			IncomingOperationBuilder iob = method.getAnnotation(IncomingOperationBuilder.class);
			if (iob != null 
					&& eOperation.getOperationID() == iob.operationID()
					&& eOperation.getEContainingClass().getClassifierID() == iob.classID()
					&& eOperation.getEContainingClass().getEPackage().getNsURI().equals(iob.nsURI())) {
				int pc = method.getParameterCount();
				if (pc == 4) {
					try {
						method.invoke(this, operationIncomingEndpoints, labels, incomingLabels, progressMonitor);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new ExecutionException(e);
					}
				} else if (pc == 5) {
					try {
						method.invoke(this, eOperation, operationIncomingEndpoints, labels, incomingLabels, progressMonitor);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new ExecutionException(e);
					}					
				} else {
					throw new NasdanikaException("A method anotated with " + OutgoingReferenceBuilder.class + " shall have 4 or 5 parameters: " + method);
				}
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	protected Consumer<Collection<Label>> createOutgoingOperationLabelConsumer(
			EOperation eOperation,
			List<Object> arguments,
			List<Entry<EOperationConnection, WidgetFactory>> operationOutgoingEndpoints) {
		
		@SuppressWarnings("resource")
		MapCompoundSupplier<EOperationConnection, Collection<Label>> endpointLabelsSupplier = new MapCompoundSupplier<>("Outgoing operation endpoints supplier");
		if (isCallOutgoingOperationLabelsSuppliers(eOperation, arguments)) {
			for (Entry<EOperationConnection, WidgetFactory> e: operationOutgoingEndpoints) {
				endpointLabelsSupplier.put(e.getKey(), e.getValue().createLabelsSupplier());
			}
		}
		
		Consumer<Supplier.FunctionResult<Collection<Label>, Map<EOperationConnection, Collection<Label>>>> operationLabelBuilder = createOutgoingOperationLabelBuilder(eOperation, arguments, operationOutgoingEndpoints);						
		Function<Collection<Label>, FunctionResult<Collection<Label>, Map<EOperationConnection, Collection<Label>>>> endpointLabelsFunction = endpointLabelsSupplier.asFunction();
		return endpointLabelsFunction.then(operationLabelBuilder);
	}
	
	/**
	 * Builds target labels
	 * @param eReference
	 * @return
	 */
	protected Consumer<Supplier.FunctionResult<Collection<Label>, Map<EOperationConnection, Collection<Label>>>> createOutgoingOperationLabelBuilder(
			EOperation eOperation,
			List<Object> arguments,
			List<Entry<EOperationConnection, WidgetFactory>> operationOutgoingEndpoints) {
		
		return new Consumer<Supplier.FunctionResult<Collection<Label>,Map<EOperationConnection,Collection<Label>>>>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Outgoing operation label builder";
			}
			
			@Override
			public void execute(FunctionResult<Collection<Label>, Map<EOperationConnection, Collection<Label>>> arg, ProgressMonitor progressMonitor) {
				buildIncomingOperation(eOperation, arguments, operationOutgoingEndpoints, arg.argument(), arg.result(), progressMonitor);
			}
		};
		
	}
	
	/**
	 * Called by builder/consumer's execute();
	 */
	protected void buildOutgoingOperation(
			EOperation eOperation,
			List<Object> arguments,
			List<Entry<EOperationConnection, WidgetFactory>> operationOutgoingEndpoints,
			Collection<Label> labels,
			Map<EOperationConnection, Collection<Label>> outgoingLabels,
			ProgressMonitor progressMonitor) {
		
		for (Method method: getClass().getMethods()) {
			OutgoingOperationBuilder oob = method.getAnnotation(OutgoingOperationBuilder.class);
			if (oob != null	&& eOperation.getOperationID() == oob.value()) {
				int pc = method.getParameterCount();
				if (pc == 4) {
					try {
						method.invoke(this, operationOutgoingEndpoints, labels, outgoingLabels, progressMonitor);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new ExecutionException(e);
					}
				} else if (pc == 5) {
					try {
						method.invoke(this, eOperation, operationOutgoingEndpoints, labels, outgoingLabels, progressMonitor);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new ExecutionException(e);
					}					
				} else {
					throw new NasdanikaException("A method anotated with " + OutgoingOperationBuilder.class + " shall have 4 or 5 parameters: " + method);
				}
			}
		}
	}
		
	/**
	 * 
	 * @return
	 */
	protected Consumer<Collection<Label>> createOutgoingReferenceLabelConsumer(
			EReference eReference, 
			List<Entry<EReferenceConnection, WidgetFactory>> referenceOutgoingEndpoints) {
		
		@SuppressWarnings("resource")
		MapCompoundSupplier<EReferenceConnection, Collection<Label>> endpointLabelsSupplier = new MapCompoundSupplier<>("Outgoing endpoints supplier");
		if (isCallOutgoingReferenceLabelsSuppliers(eReference)) {
			for (Entry<EReferenceConnection, WidgetFactory> e: referenceOutgoingEndpoints) {
				endpointLabelsSupplier.put(e.getKey(), e.getValue().createLabelsSupplier());
			}
		}
		
		Consumer<Supplier.FunctionResult<Collection<Label>, Map<EReferenceConnection, Collection<Label>>>> referenceLabelBuilder = createOutgoingReferenceLabelBuilder(eReference, referenceOutgoingEndpoints);						
		Function<Collection<Label>, FunctionResult<Collection<Label>, Map<EReferenceConnection, Collection<Label>>>> endpointLabelsFunction = endpointLabelsSupplier.asFunction();
		return endpointLabelsFunction.then(referenceLabelBuilder);
	}
	
	/**
	 * Builds target labels
	 * @param eReference
	 * @return
	 */
	protected Consumer<Supplier.FunctionResult<Collection<Label>, Map<EReferenceConnection, Collection<Label>>>> createOutgoingReferenceLabelBuilder(
			EReference eReference,
			List<Entry<EReferenceConnection, WidgetFactory>> referenceOutgoingEndpoints) {
		
		
		return new Consumer<Supplier.FunctionResult<Collection<Label>,Map<EReferenceConnection,Collection<Label>>>>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Outgoing reference label builder";
			}
			
			@Override
			public void execute(
					FunctionResult<Collection<Label>, Map<EReferenceConnection, Collection<Label>>> arg, 
					ProgressMonitor progressMonitor) {
				
				buildOutgoingReference(
						eReference, 
						referenceOutgoingEndpoints, 
						arg.argument(), 
						arg.result(), 
						progressMonitor);
			}
		};
		
	}
	
	/**
	 * Called by builder/consumer's execute();
	 * @param eReference
	 * @param referenceIncomingEndpoints
	 * @param labels
	 * @param incomingLabels
	 * @param progressMonitor
	 */
	protected void buildOutgoingReference(
			EReference eReference,
			List<Entry<EReferenceConnection, WidgetFactory>> referenceOutgoingEndpoints,
			Collection<Label> labels,
			Map<EReferenceConnection, Collection<Label>> outgoingLabels,
			ProgressMonitor progressMonitor) {
		
		for (Method method: getClass().getMethods()) {
			OutgoingReferenceBuilder orb = method.getAnnotation(OutgoingReferenceBuilder.class);
			if (orb != null	&& eReference.getFeatureID() == orb.value()) {
				int pc = method.getParameterCount();
				if (pc == 4) {
					try {
						method.invoke(this, referenceOutgoingEndpoints, labels, outgoingLabels, progressMonitor);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new ExecutionException(e);
					}
				} else if (pc == 5) {
					try {
						method.invoke(this, eReference, referenceOutgoingEndpoints, labels, outgoingLabels, progressMonitor);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new ExecutionException(e);
					}					
				} else {
					throw new NasdanikaException("A method anotated with " + OutgoingReferenceBuilder.class + " shall have 4 or 5 parameters: " + method);
				}
			}
		}
		
		addReferenceChildren(eReference, labels, outgoingLabels, progressMonitor);		
	}

	protected void addReferenceChildren(EReference eReference, Collection<Label> labels,
			Map<EReferenceConnection, Collection<Label>> outgoingLabels, ProgressMonitor progressMonitor) {
		for (Label tLabel: labels) {
			Label refLabel = createLabel(eReference, progressMonitor);
			for (Entry<EReferenceConnection, Collection<Label>> re: outgoingLabels.entrySet()) {
				refLabel.getChildren().addAll(re.getValue());
			}
			if (!refLabel.getChildren().isEmpty()) {
				tLabel.getChildren().add(refLabel);
			}
		}
	}
	
	/**
	 * Creates and configures an action for eObject. 
	 * Override to create from prototypes.
	 * @param eObject
	 * @return
	 */
	protected Action createAction(EObject eObject, ProgressMonitor progressMonitor) {
		Action action = newAction(eObject, progressMonitor);
		configureLabel(eObject, action, progressMonitor);
		return action;
	}

	/**
	 * Creates a new action using a factory. 
	 * Override to create from prototypes.
	 * @return
	 */
	protected Action newAction(EObject eObject, ProgressMonitor progressMonitor) {
		if (prototypeProvider != null) {
			Action prototype = prototypeProvider.apply(progressMonitor);
			if (prototype != null) {
				return prototype;
			}				
		}
		return AppFactory.eINSTANCE.createAction();
	}
	
	/**
	 * Creates and configures a link for eObject. 
	 * Override to create from prototypes.
	 * @param eObject
	 * @return
	 */
	protected Link createLink(EObject eObject, String path, ProgressMonitor progressMonitor) {
		Link link = AppFactory.eINSTANCE.createLink();
		configureLabel(eObject, link, progressMonitor);
		if (Util.isBlank(path)) {
			link.setLocation(uri.toString());
		} else {
			link.setLocation(URI.createURI(path).resolve(uri).toString());
		}
		return link;
	}
	
	/**
	 * Creates and configures a label for eObject. 
	 * Override to create from prototypes.
	 * @param eObject
	 * @return
	 */
	protected Label createLabel(EObject eObject, ProgressMonitor progressMonitor) {
		Label label = AppFactory.eINSTANCE.createLabel();
		configureLabel(eObject, label, progressMonitor);
		return label;		
	}
	
	/**
	 * Configures label.
	 * @param eObject
	 * @param label
	 */
	protected void configureLabel(EObject eObject, Label label, ProgressMonitor progressMonitor) {
		if (eObject instanceof NamedElement && Util.isBlank(label.getText())) {
			label.setText(StringEscapeUtils.escapeHtml4(((NamedElement) eObject).getName()));
		}
		if (label instanceof Link && uri != null) {
			((Link) label).setLocation(uri.toString());
		}

		new SemanticInfo(eObject).annotate(label);
	}
	
	protected String render(Object object, ProgressMonitor progressMonitor) {
		if (object instanceof EObject) {
			Adapter adapter = AppAdapterFactory.INSTANCE.adapt((EObject) object, SupplierFactory.Provider.class);
			if (adapter instanceof SupplierFactory.Provider) {
				SupplierFactory<Tag> supplierFactory = ((SupplierFactory.Provider) adapter).getFactory(Tag.class);
				Supplier<Tag> supplier = supplierFactory.create(context);
				Tag tag = supplier.call(progressMonitor, null, Status.FAIL, Status.ERROR);
				return tag.toString();
			}
			// Adapt to just supplier factory here and see what comes out - string, stream, ...?			
		} else if (object instanceof Iterable) {
			StringBuilder ret = new StringBuilder();
			((Iterable<?>) object).forEach(e -> ret.append(render(e, progressMonitor)));
			return ret.toString();
		}
		if (object instanceof Stream) {
			return ((Stream<?>) object).map(e -> render(e, progressMonitor)).collect(Collectors.joining());
		}

		return object == null ? "" : object.toString();
	}
	
	// --- WidgetFactory methods ---
	
	@Override
	public void resolve(URI base, ProgressMonitor progressMonitor) {		
		uri = uri.resolve(base);
		for (WidgetFactory oe: outgoingReferenceEndpoints.values()) {
			oe.resolve(uri, progressMonitor);
		}
		for (WidgetFactory ie: incomingReferenceEndpoints.values()) {
			ie.resolve(uri, progressMonitor);
		}
	}
	
	@Override
	public String createWidgetString(Object selector, URI base, ProgressMonitor progressMonitor) {
		return render(createWidget(selector, base, progressMonitor), progressMonitor);
	}
	
	@Override
	public String createLinkString(URI base, ProgressMonitor progressMonitor) {
		return render(createLink(base, progressMonitor), progressMonitor);
	}
	
	@Override
	public Object createLink(URI base, ProgressMonitor progressMonitor) {
		Link link = createLink(getTarget(), null, progressMonitor);
		link.rebase(null, base);
		return link;
	}
		
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		List<Consumer<Collection<Label>>> referenceLabelBuilders = getReferenceLabelBuilders();
		List<Consumer<Collection<Label>>> operationLabelBuilders = getOperationLabelBuilders();
		if (referenceLabelBuilders == null && operationLabelBuilders == null || referenceLabelBuilders.isEmpty() && operationLabelBuilders.isEmpty()) {
			return doCreateLabelsSupplier();
		}
		@SuppressWarnings("resource")
		CollectionCompoundConsumer<Collection<Label>> collectionCompoundConsumer = new CollectionCompoundConsumer<Collection<Label>>("Reference Label Builder");
		referenceLabelBuilders.forEach(collectionCompoundConsumer::add);
		operationLabelBuilders.forEach(collectionCompoundConsumer::add);
		return doCreateLabelsSupplier().then(collectionCompoundConsumer.asFunction());
	}
	
	@Override
	public String createLabelString(ProgressMonitor progressMonitor) {
		return render(createLabel(progressMonitor), progressMonitor);
	}
	
	@Override
	public Object createLabel(ProgressMonitor progressMonitor) {
		return createLabel(getTarget(), progressMonitor);
	}
	
}
