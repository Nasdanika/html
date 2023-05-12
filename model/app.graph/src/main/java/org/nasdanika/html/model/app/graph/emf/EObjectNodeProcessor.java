
package org.nasdanika.html.model.app.graph.emf;

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
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.CollectionCompoundConsumer;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.MapCompoundSupplier;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Supplier.FunctionResult;
import org.nasdanika.common.Util;
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
import org.nasdanika.html.Tag;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.gen.AppAdapterFactory;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.URINodeProcessor;
import org.nasdanika.html.model.app.graph.WidgetFactory;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.util.NcoreUtil;
import org.nasdanika.ncore.util.SemanticInfo;

/**
 * Base class for node processors.
 * Groups connections by reference, creates a consumer per reference (builder), chains the labels supplier with the consumers.
 * @author Pavel
 *
 */
public class EObjectNodeProcessor<T extends EObject> implements URINodeProcessor {
	
	protected java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider;

	public EObjectNodeProcessor(
			NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, 
			Context context, 
			java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider) {		
		this.config = config;
		this.context = context;
		this.prototypeProvider = prototypeProvider;
		this.uri = URI.createURI("index.html");
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
	
	@SuppressWarnings("unchecked")
	protected T getTarget() {
		return (T) node.getTarget();
	}
	
	protected Map<EReferenceConnection, WidgetFactory> incomingEndpoints = new LinkedHashMap<>();
	protected Map<EReferenceConnection, WidgetFactory> outgoingEndpoints = new LinkedHashMap<>();
	
	@IncomingEndpoint
	public void setIncomingEndpoint(EReferenceConnection connection, WidgetFactory endpoint) {
		incomingEndpoints.put(connection, endpoint);
	}
		
	@OutgoingEndpoint
	public void setOutgoingEndpoint(EReferenceConnection connection, WidgetFactory endpoint) {
		outgoingEndpoints.put(connection, endpoint);
	}
	
	@IncomingHandler
	public WidgetFactory getIncomingHandler(EReferenceConnection connection) {
		return this;
	}
		
	@OutgoingHandler
	public WidgetFactory getOutgoingHandler(EReferenceConnection connection) {
		return this;
	}
	
	protected NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config;
	protected Context context;
	protected URI uri;

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
	
	protected List<Consumer<Collection<Label>>> getReferenceLabelBuilders() {		
		List<Consumer<Collection<Label>>> ret = new ArrayList<>();
		
		Map<EReference, List<Entry<EReferenceConnection, WidgetFactory>>> groupedOutgoingEndpoints = org.nasdanika.common.Util.groupBy(outgoingEndpoints.entrySet(), e -> e.getKey().getReference());
		groupedOutgoingEndpoints
			.entrySet()
			.stream()
			.sorted((a, b) -> compareOutgoingReferences(a.getKey(), b.getKey()))
			.map(e -> createOutgoingReferenceLabelConsumer(e.getKey(), e.getValue()))
			.filter(Objects::nonNull)
			.forEach(ret::add);
		
		Map<EReference, List<Entry<EReferenceConnection, WidgetFactory>>> groupedIncomingEndpoints = org.nasdanika.common.Util.groupBy(incomingEndpoints.entrySet(), e -> e.getKey().getReference());
		groupedIncomingEndpoints
			.entrySet()
			.stream()
			.sorted((a, b) -> compareIncomingReferences(a.getKey(), b.getKey()))
			.map(e -> createIncomingReferenceLabelConsumer(e.getKey(), e.getValue()))
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
	 * @return
	 */
	protected Consumer<Collection<Label>> createIncomingReferenceLabelConsumer(
			EReference eReference, 
			List<Entry<EReferenceConnection, WidgetFactory>> referenceIncomingEndpoints) {
		
		@SuppressWarnings("resource")
		MapCompoundSupplier<EReferenceConnection, Collection<Label>> endpointLabelsSupplier = new MapCompoundSupplier<>("Incoming endpoints supplier");
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
			for (URI identifier: NcoreUtil.getIdentifiers(eObject)) {
				Action prototype = prototypeProvider.apply(identifier, progressMonitor);
				if (prototype != null) {
					return prototype;
				}				
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
		if (eObject instanceof ENamedElement && Util.isBlank(label.getText())) {
			label.setText(((ENamedElement) eObject).getName());
			// TODO - escape, annotation
		}
		if (eObject instanceof NamedElement && Util.isBlank(label.getText())) {
			label.setText(StringEscapeUtils.escapeHtml4(((NamedElement) eObject).getName()));
		}
		if (label instanceof Link && uri != null) {
			((Link) label).setLocation(uri.toString());
		}

		new SemanticInfo(eObject).annotate(label);
		
		// TODO - icon, toolitp, ...
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
		for (WidgetFactory oe: outgoingEndpoints.values()) {
			oe.resolve(uri, progressMonitor);
		}
		for (WidgetFactory ie: incomingEndpoints.values()) {
			ie.resolve(uri, progressMonitor);
		}
	}
	
	@Override
	public String createWidgetString(Object selector, URI base, ProgressMonitor progressMonitor) {
		return render(createWidget(selector, base, progressMonitor), progressMonitor);
	}
	
	@Override
	public Object createWidget(Object selector, URI base, ProgressMonitor progressMonitor) {
		return null;
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
		if (referenceLabelBuilders == null || referenceLabelBuilders.isEmpty()) {
			return doCreateLabelsSupplier();
		}
		@SuppressWarnings("resource")
		CollectionCompoundConsumer<Collection<Label>> collectionCompoundConsumer = new CollectionCompoundConsumer<Collection<Label>>("Reference Label Builder");
		referenceLabelBuilders.forEach(collectionCompoundConsumer::add);
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
