
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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.CollectionCompoundConsumer;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.Context;
import org.nasdanika.common.EStructuralFeatureAndEOperationMatcher;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.common.Function;
import org.nasdanika.common.MapCompoundSupplier;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Supplier.FunctionResult;
import org.nasdanika.emf.persistence.MarkerFactory;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Text;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.emf.EClassConnection;
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
import org.nasdanika.html.model.bootstrap.BootstrapElement;
import org.nasdanika.html.model.bootstrap.BootstrapFactory;
import org.nasdanika.html.model.bootstrap.Modal;
import org.nasdanika.html.model.html.HtmlFactory;
import org.nasdanika.ncore.Documented;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.util.SemanticInfo;

/**
 * Base class for node processors.
 * Groups connections by reference, creates a consumer per reference (builder), chains the labels supplier with the consumers.
 * @author Pavel
 *
 */
public class EObjectNodeProcessor<T extends EObject> implements WidgetFactory, EStructuralFeatureAndEOperationMatcher {
	
	private static final String HELP_DECORATOR_ICON = "far fa-question-circle";

	private static final String HELP_DECORATOR_STYLE = "vertical-align:super;font-size:x-small;margin-left:0.2em";

	private static AtomicInteger counter = new AtomicInteger();
	
	private int id = counter.incrementAndGet();

	/**
	 * Generated unique ID for grouping and comparing/ordering.
	 *  E.g. deciding which processor of two is responsible for combining opposite references (Ecore level and Nasdanika level)
	 *  or grouping all cross-references into one for graph generation.
	 * @return
	 */
	public int getId() {
		return id;
	}

	public static Selector<EObject> TARGET_SELECTOR = (widgetFactory, base, progressMonitor) -> {
		return ((EObjectNodeProcessor<?>) widgetFactory).getTarget();
	};		

	public static Selector<EObjectNodeProcessor<?>> SELF_SELECTOR = (widgetFactory, base, progressMonitor) -> {
		return ((EObjectNodeProcessor<?>) widgetFactory);
	};		
	
	protected java.util.function.Function<ProgressMonitor, Action> prototypeProvider;
	protected NodeProcessorConfig<WidgetFactory, WidgetFactory> config;
	protected Context context;
	protected URI uri;
	
	/**
	 * Facets are used to provide support for multiple inheritance. For example, {@link EClass} C has EClasses A and B as supertypes.
	 * EClass C node processor would extend class A node processor and have class B node processor as a facet delegating to it. 
	 */
	protected List<WidgetFactory> facets = new ArrayList<>();
	
	public EObjectNodeProcessor(
			NodeProcessorConfig<WidgetFactory, WidgetFactory> config, 
			Context context, 
			java.util.function.Function<ProgressMonitor, Action> prototypeProvider) {		
		this.config = config;
		this.context = context;
		this.prototypeProvider = prototypeProvider;
		this.uri = URI.createURI("index.html");
		// Create facets in sub-classes
	}
	
	public URI getUri() {
		return uri;
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
	
	public EObjectNode getNode() {
		return node;
	}
	
	public NodeProcessorConfig<WidgetFactory, WidgetFactory> getConfig() {
		return config;
	}
	
	public Context getContext() {
		return context;
	}
	
	@SuppressWarnings("unchecked")
	public T getTarget() {
		return (T) node.get();
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
		Action action = createAction(getTarget(), progressMonitor);		
		if (action.getDecorator() == null && eClassWidgetFactory != null) {			
			Label helpDecorator = eClassWidgetFactory.createHelpDecorator(progressMonitor);
			action.setDecorator(helpDecorator);
		}
		if (getTarget() instanceof Documented) {
			action.getContent().addAll(EcoreUtil.copyAll(((Documented) getTarget()).getDocumentation()));
		}
		
		return action;
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
			if (irb != null  && matchEStructuralFeature(irb.nsURI(), irb.classID(), irb.referenceID(), null, eReference)) {				
				if (method.getParameterCount() != 5 ||
						!method.getParameterTypes()[0].isInstance(eReference) ||
						!method.getParameterTypes()[1].isInstance(referenceIncomingEndpoints) ||
						!method.getParameterTypes()[2].isInstance(labels) ||
						!method.getParameterTypes()[3].isInstance(incomingLabels) ||
						!method.getParameterTypes()[4].isAssignableFrom(ProgressMonitor.class)) {
					throw new IllegalArgumentException("Incoming reference builder method shall have 5 parameters compatible with: "
							+ "EReference eReference, "
							+ "List<Entry<EReferenceConnection, WidgetFactory>> referenceIncomingEndpoints, "
							+ "Collection<Label> labels, "
							+ "Map<EReferenceConnection, Collection<Label>> incomingLabels, "
							+ "ProgressMonitor progressMonitor: " + method);
				}
				try {
					method.invoke(this, eReference, referenceIncomingEndpoints, labels, incomingLabels, progressMonitor);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new ExecutionException(e);
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

				if (method.getParameterCount() != 6 ||
						!method.getParameterTypes()[0].isInstance(eOperation) ||
						!method.getParameterTypes()[1].isInstance(arguments) ||
						!method.getParameterTypes()[3].isInstance(operationIncomingEndpoints) ||
						!method.getParameterTypes()[4].isInstance(labels) ||
						!method.getParameterTypes()[5].isInstance(incomingLabels) ||
						!method.getParameterTypes()[6].isAssignableFrom(ProgressMonitor.class)) {
					throw new IllegalArgumentException("Incoming operation builder method shall have 6 parameters compatible with: "
							+ "EOperation eOperation, "
							+ "List<Object> arguments, "
							+ "List<Entry<EOperationConnection, WidgetFactory>> operationIncomingEndpoints, "
							+ "Collection<Label> labels, "
							+ "Map<EOperationConnection, Collection<Label>> incomingLabels, "
							+ "ProgressMonitor progressMonitor: " + method);
				}
				
				try {
					method.invoke(this, eOperation, arguments, operationIncomingEndpoints, labels, incomingLabels, progressMonitor);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new ExecutionException(e);
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
			if (oob != null	
					&& eOperation.getOperationID() == oob.value()					
					&& (oob.classID() == -1 
						|| (eOperation.getEContainingClass().getClassifierID() == oob.classID()
							&& eOperation.getEContainingClass().getEPackage().getNsURI().equals(oob.nsURI())))) {
				
				if (method.getParameterCount() != 6 ||
						!method.getParameterTypes()[0].isInstance(eOperation) ||
						!method.getParameterTypes()[1].isInstance(arguments) ||
						!method.getParameterTypes()[3].isInstance(operationOutgoingEndpoints) ||
						!method.getParameterTypes()[4].isInstance(labels) ||
						!method.getParameterTypes()[5].isInstance(outgoingLabels) ||
						!method.getParameterTypes()[6].isAssignableFrom(ProgressMonitor.class)) {
					throw new IllegalArgumentException("Outgoing operation builder method shall have 6 parameters compatible with: "
							+ "EOperation eOperation, "
							+ "List<Object> arguments, "
							+ "List<Entry<EOperationConnection, WidgetFactory>> operationOutgoingEndpoints, "
							+ "Collection<Label> labels, "
							+ "Map<EOperationConnection, Collection<Label>> outgoingLabels, "
							+ "ProgressMonitor progressMonitor: " + method);
				}
				try {
					method.invoke(this, eOperation, arguments, operationOutgoingEndpoints, labels, outgoingLabels, progressMonitor);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new ExecutionException(e);
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
	 * @param referenceOutgoingEndpoints
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
			if (orb != null	&& matchEStructuralFeature(orb.nsURI(), orb.classID(), orb.referenceID(), null, eReference)) {
				if (method.getParameterCount() != 5 ||
						!method.getParameterTypes()[0].isInstance(eReference) ||
						!method.getParameterTypes()[1].isInstance(referenceOutgoingEndpoints) ||
						!method.getParameterTypes()[2].isInstance(labels) ||
						!method.getParameterTypes()[3].isInstance(outgoingLabels) ||
						!method.getParameterTypes()[4].isAssignableFrom(ProgressMonitor.class)) {
					throw new IllegalArgumentException("Outgoing reference builder method shall have 5 parameters compatible with: "
							+ "EReference eReference, "
							+ "List<Entry<EReferenceConnection, WidgetFactory>> referenceOutgoingEndpoints, "
							+ "Collection<Label> labels, "
							+ "Map<EReferenceConnection, Collection<Label>> outgoingLabels, "
							+ "ProgressMonitor progressMonitor: " + method);
				}
				try {
					method.invoke(this, eReference, referenceOutgoingEndpoints, labels, outgoingLabels, progressMonitor);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new ExecutionException(e);
				}					
			}
		}
		
		addReferenceChildren(eReference, labels, outgoingLabels, progressMonitor);		
	}

	protected void addReferenceChildren(
			EReference eReference, 
			Collection<Label> labels,
			Map<EReferenceConnection, Collection<Label>> outgoingLabels, 
			ProgressMonitor progressMonitor) {
		
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
	
	private WidgetFactory eClassWidgetFactory;
	
	@OutgoingEndpoint
	public final void setEClassEndpoint(EClassConnection connection, WidgetFactory eClassWidgetFactory) {
		this.eClassWidgetFactory = eClassWidgetFactory;
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
	@Override
	public void configureLabel(Object source, Label label, ProgressMonitor progressMonitor) {
		if (source instanceof EObject) {
			EObject eObject = (EObject) source;
			if (eObject instanceof NamedElement && Util.isBlank(label.getText())) {
				label.setText(StringEscapeUtils.escapeHtml4(getName((NamedElement) eObject)));
			}
			if (label instanceof Link && uri != null) {
				((Link) label).setLocation(uri.toString());
			}

			new SemanticInfo(eObject).annotate(label);
		}
		for (WidgetFactory facet: facets) {
			facet.configureLabel(source, label, progressMonitor);
		}
	}
	
	/**
	 * Override to customize name, e.g. replace blank name with some generated name
	 * @param namedElement
	 * @return
	 */
	protected String getName(NamedElement namedElement) {
		return namedElement.getName();
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
	public String selectString(Object selector, URI base, ProgressMonitor progressMonitor) {
		return render(select(selector, base, progressMonitor), progressMonitor);
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
	
	/**
	 * Override to configure the modal. E.g. center, change size, make scrollable, etc.
	 * @param helpModal
	 */
	protected void configureHelpModal(Modal helpModal) {
//		helpModal.setSize("large");
//		helpModal.setCentered(true);		
	}
	
	/**
	 * Convenience method for creating help decorators. 
	 * Returns null if the tooltip, location and contents are blank/empty.
	 * If the contents is empty returns a label or a link with a help icon and a tooltip.
	 * Otherwise returns a link with a tooltip and a modal. The modal header links to the location if it is not empty.
	 * @param tooltip Tooltip to show
	 * @param location If not blank
	 * @param title Modal header text
	 * @param icon
	 * @param contents
	 * @param modalConfigurator
	 * @return
	 */
	public static Label createHelpDecorator(
			String tooltip,
			String location,
			String title,
			String icon,
			Collection<EObject> contents,
			java.util.function.Consumer<Modal> modalConfigurator) {
		
		if (contents == null || contents.isEmpty()) {
			if (Util.isBlank(location)) {
				if (Util.isBlank(tooltip)) {
					return null;
				}
				Label helpDecorator = AppFactory.eINSTANCE.createLabel();				
				helpDecorator.setIcon(HELP_DECORATOR_ICON);
				helpDecorator.getAttributes().put("style", createText(HELP_DECORATOR_STYLE));				
				helpDecorator.setTooltip(tooltip);
				return helpDecorator;				
			}
		}
		
		Link helpDecorator = AppFactory.eINSTANCE.createLink();
		helpDecorator.setIcon(HELP_DECORATOR_ICON);
		helpDecorator.getAttributes().put("style", createText(HELP_DECORATOR_STYLE));				
		helpDecorator.setTooltip(tooltip);
		if (contents == null || contents.isEmpty()) {
			helpDecorator.setLocation(location);
		} else {
			Modal helpModal = BootstrapFactory.eINSTANCE.createModal();
		
			if (!Util.isBlank(title)) {
				BootstrapElement header = BootstrapFactory.eINSTANCE.createBootstrapElement();
				org.nasdanika.html.model.html.Tag h2 = HtmlFactory.eINSTANCE.createTag();
				h2.setName("H2");
				header.getContent().add(h2);
				if (Util.isBlank(location)) {					
					// Label
					Label tLabel = AppFactory.eINSTANCE.createLabel();
					tLabel.setText(title);
					tLabel.setIcon(icon);
					h2.getContent().add(tLabel);					
				} else {
					// Link
					Link typeLink = AppFactory.eINSTANCE.createLink();
					typeLink.setText(title);
					typeLink.setIcon(icon);
					typeLink.setLocation(location);
					h2.getContent().add(typeLink);
				}
				helpModal.setHeader(header);
			}
			
			BootstrapElement body = BootstrapFactory.eINSTANCE.createBootstrapElement();
			body.getContent().addAll(contents);		
			helpModal.setBody(body);
			if (modalConfigurator != null) {
				modalConfigurator.accept(helpModal);
			}
			helpDecorator.setModal(helpModal);
		}
		
		return helpDecorator;
	}
	
	public static Label createHelpDecorator(
			String tooltip,
			String location,
			String title,
			String icon,
			String contents,
			java.util.function.Consumer<Modal> modalConfigurator) {
		return createHelpDecorator(
				tooltip, 
				location, 
				title, 
				icon, 
				Util.isBlank(contents) ? Collections.emptyList() : Collections.singleton(createText(contents)), 
				modalConfigurator);
	}
		
	public Label createMarkdownHelpDecorator(
			String tooltip,
			String location,
			String title,
			String icon,
			String markdown,
			java.util.function.Consumer<Modal> modalConfigurator) {
		return createHelpDecorator(
				tooltip, 
				location, 
				title, 
				icon, 
				context.get(MarkdownHelper.class, MarkdownHelper.INSTANCE).markdownToHtml(markdown),  
				modalConfigurator);
	}
	
	protected Collection<EObject> createHelpContents(URI base, ProgressMonitor progressMonitor) {
		return Collections.emptyList();
	}
	
	@Override
	public Label createHelpDecorator(URI base, ProgressMonitor progressMonitor) {
		Link link = createLink(getTarget(), null, progressMonitor);
		link.rebase(null, base);
		
		Collection<EObject> helpContents =  createHelpContents(base, progressMonitor);
		
		return createHelpDecorator(
				link.getTooltip(),
				link.getLocation(),
				link.getText(),
				link.getIcon(), 
				helpContents, 
				this::configureHelpModal);
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
	
	// --- Convenience methods --
	/**
	 * Adds textual content.
	 * @param content
	 */
	protected static void addContent(Action action, String content) {
		Text text = ContentFactory.eINSTANCE.createText();
		text.setContent(content);
		action.getContent().add(text);
	}
	
	/**
	 * Convenience method to create Text and set content in one shot.
	 * @param content
	 * @return
	 */
	public static Text createText(String content) {
		Text text = ContentFactory.eINSTANCE.createText();
		text.setContent(content);
		return text;
	}	
	
	/**
	 * @param markdown Markdown text
	 * @return Spec for interpolating markdown and then converting to HTML. 
	 */
	protected Markdown interpolatedMarkdown(String markdown, URI location, ProgressMonitor progressMonitor) {
		if (Util.isBlank(markdown)) {
			return null;
		}
		Markdown ret = ContentFactory.eINSTANCE.createMarkdown();
		Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();
		Text text = ContentFactory.eINSTANCE.createText();
		text.setContent(markdown);
		interpolator.setSource(text);
		ret.setSource(interpolator);
		ret.setStyle(true);
		
		// Creating a marker with EObject resource location for resource resolution in Markdown
		if (location != null) {
			org.nasdanika.ncore.Marker marker = context.get(MarkerFactory.class, MarkerFactory.INSTANCE).createMarker(location.toString(), progressMonitor);
			ret.getMarkers().add(marker); 
		}
		
		return ret;
	}
	
	/**
	 * Convenience method for sorting reference elements by name if they are named elements
	 * @param a
	 * @param b
	 * @return
	 */
	protected int compareElements(Entry<EReferenceConnection, Collection<Label>> a, Entry<EReferenceConnection, Collection<Label>> b) {
		EObject aObj = a.getKey().getTarget().get();
		EObject bObj = b.getKey().getTarget().get();
		
		if (aObj instanceof NamedElement) {
			String aName = ((NamedElement) aObj).getName();
			if (!Util.isBlank(aName)) {
				if (bObj instanceof NamedElement) {
					String bName = ((NamedElement) bObj).getName();
					if (!Util.isBlank(bName)) {
						return aName.compareTo(bName);
					}
				}
				return -1;
			}
		} 
		
		if (bObj instanceof NamedElement) {
			return 1;
		}
		
		return aObj.hashCode() - bObj.hashCode();
	}		
	
		
}
