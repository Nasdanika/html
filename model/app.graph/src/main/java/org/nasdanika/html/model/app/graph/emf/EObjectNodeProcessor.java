package org.nasdanika.html.model.app.graph.emf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.common.util.EList;
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
import org.nasdanika.common.Supplier;
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
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.URINodeProcessor;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.Property;
import org.nasdanika.ncore.util.NcoreUtil;
import org.nasdanika.ncore.util.SemanticInfo;

/**
 * Base class for node processors.
 * Groups connections by reference, creates a consumer per reference (builder), chains the labels supplier with the consumers.
 * @author Pavel
 *
 */
public class EObjectNodeProcessor<T> implements URINodeProcessor {
	
	protected java.util.function.Function<URI, Action> prototypeProvider;

	public EObjectNodeProcessor(
			NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config, 
			Context context, 
			java.util.function.Function<URI, Action> prototypeProvider) {		
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
	protected URI uri;
//	protected Map<EReference, List<Entry<Element, ProcessorInfo<Object, Registry<URI>>>>> groupedChildren;	

//	@SuppressWarnings("unchecked")
//	public EObjectNodeProcessor(
//			NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config,
//			Context context) {
//		
//		this.config = config;
//		this.context = context;
//		this.target = (T) ((EObjectNode) config.getElement()).getTarget();
//		groupedChildren = org.nasdanika.common.Util.groupBy(config.getChildProcessorsInfo().entrySet(), infoEntry -> ((EReferenceConnection) infoEntry.getKey()).getReference());
//		groupedChildren.values().forEach(l -> l.sort((a,b) -> ((EReferenceConnection) a.getKey()).getIndex() - ((EReferenceConnection) b.getKey()).getIndex()));		
//	}
	
	@Override
	public void resolve(URI base) {		
		uri = uri.resolve(base);
		for (LabelFactory oe: outgoingEndpoints.values()) {
			oe.resolve(uri);
		}
		for (LabelFactory ie: incomingEndpoints.values()) {
			ie.resolve(uri);
		}
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
		return createAction(node.getTarget());
	}

	@Override
	public Label createLabel() {
		return createLabel(node.getTarget());
	}

	@Override
	public Label createLink(String path) {
		return createLink(node.getTarget(), path);
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
				return "Labels supplier for " + node.getTarget();
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
		
		Map<EReference, List<Entry<EReferenceConnection, LabelFactory>>> groupedOutgoingEndpoints = org.nasdanika.common.Util.groupBy(outgoingEndpoints.entrySet(), e -> e.getKey().getReference());
		groupedOutgoingEndpoints
			.entrySet()
			.stream()
			.sorted((a, b) -> compareOutgoingReferences(a.getKey(), b.getKey()))
			.map(e -> createOutgoingReferenceLabelConsumer(e.getKey(), e.getValue()))
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
			List<Entry<EReferenceConnection, LabelFactory>> referenceIncomingEndpoints) {
		
		@SuppressWarnings("resource")
		MapCompoundSupplier<EReferenceConnection, Collection<Label>> endpointLabelsSupplier = new MapCompoundSupplier<>("Incoming endpoints supplier");
		if (isCallIncomingReferenceLabelsSuppliers(eReference)) {
			for (Entry<EReferenceConnection, LabelFactory> e: referenceIncomingEndpoints) {
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
			List<Entry<EReferenceConnection, LabelFactory>> referenceIncomingEndpoints) {
		
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
			List<Entry<EReferenceConnection, LabelFactory>> referenceIncomingEndpoints,
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
			List<Entry<EReferenceConnection, LabelFactory>> referenceOutgoingEndpoints) {
		
		@SuppressWarnings("resource")
		MapCompoundSupplier<EReferenceConnection, Collection<Label>> endpointLabelsSupplier = new MapCompoundSupplier<>("Outgoing endpoints supplier");
		if (isCallOutgoingReferenceLabelsSuppliers(eReference)) {
			for (Entry<EReferenceConnection, LabelFactory> e: referenceOutgoingEndpoints) {
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
			List<Entry<EReferenceConnection, LabelFactory>> referenceOutgoingEndpoints) {
		
		
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
			List<Entry<EReferenceConnection, LabelFactory>> referenceOutgoingEndpoints,
			Collection<Label> labels,
			Map<EReferenceConnection, Collection<Label>> outgoingLabels,
			ProgressMonitor progressMonitor) {

		BiConsumer<Collection<Label>, Collection<Label>> injector = getOutgoingReferenceInjector(eReference);
		if (injector != null) {
			for (Entry<EReferenceConnection, Collection<Label>> ole: outgoingLabels.entrySet()) {
				if (ole.getKey().getReference() == eReference) {
					injector.accept(ole.getValue(), labels);
				}
			}
		}
	}
	
	/**
	 * Creates and configures an action for eObject. 
	 * Override to create from prototypes.
	 * @param eObject
	 * @return
	 */
	protected Action createAction(EObject eObject) {
		Action action = newAction(eObject);
		configureLabel(eObject, action);
		return action;
	}

	/**
	 * Creates a new action using a factory. 
	 * Override to create from prototypes.
	 * @return
	 */
	protected Action newAction(EObject eObject) {
		if (prototypeProvider != null) {
			for (URI identifier: NcoreUtil.getIdentifiers(eObject)) {
				Action prototype = prototypeProvider.apply(identifier);
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
	protected Link createLink(EObject eObject, String path) {
		Link link = AppFactory.eINSTANCE.createLink();
		configureLabel(eObject, link);
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
	protected Label createLabel(EObject eObject) {
		Label label = AppFactory.eINSTANCE.createLabel();
		configureLabel(eObject, label);
		return label;		
	}
	
	/**
	 * Configures label.
	 * @param eObject
	 * @param label
	 */
	protected void configureLabel(EObject eObject, Label label) {
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
	
	/**
	 * 
	 * @param eReference
	 * @return a {@link BiConsumer} injecting incoming {@link EReference} labels (first argument) into the target node labels (second argument). 
	 */
	protected BiConsumer<Collection<Label>, Collection<Label>> getIncomingReferenceInjector(EReference eReference) {
		// TODO - referral grouping
		return (r, t) -> {
			for (Label tLabel: t) {
				if (tLabel instanceof Action) {
					Action tAction = (Action) tLabel;
					EList<Action> tSections = tAction.getSections();
//					Action refSection = 
				}
			}
		};
	}
	
	/**
	 * 
	 * @param eReference
	 * @return a {@link BiConsumer} injecting incoming {@link EReference} labels (first argument) into the target node labels (second argument). 
	 */
	protected BiConsumer<Collection<Label>, Collection<Label>> getOutgoingReferenceInjector(EReference eReference) {
		return (r, t) -> {
			for (Label tLabel: t) {
				tLabel.getChildren().addAll(r);
			}
		};
	}
	
	// TODO - reference role and "mount point" - container, can be, for example, a section for back-links.
	

}
