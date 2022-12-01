package org.nasdanika.html.emf;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.CollectionCompoundConsumer;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.emf.DiagnosticProvider;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.RowContainer.Row;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.emf.EObjectActionResolver.Context;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.NavigationPanel;
import org.nasdanika.html.model.app.util.ActionBuilder;
import org.nasdanika.html.model.app.util.ActionProvider;
import org.nasdanika.html.model.app.util.ActionSupplier;
import org.nasdanika.html.model.bootstrap.Alert;
import org.nasdanika.html.model.bootstrap.Appearance;
import org.nasdanika.html.model.bootstrap.BootstrapFactory;
import org.nasdanika.html.model.bootstrap.Table;
import org.nasdanika.html.model.bootstrap.TableCell;
import org.nasdanika.html.model.bootstrap.TableHeader;
import org.nasdanika.html.model.bootstrap.TableRow;
import org.nasdanika.html.model.bootstrap.TableSection;
import org.nasdanika.html.model.html.HtmlFactory;
import org.nasdanika.html.model.html.Script;
import org.nasdanika.html.model.html.Tag;
import org.nasdanika.ncore.GitMarker;
import org.nasdanika.ncore.Map;
import org.nasdanika.ncore.Marked;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NcoreFactory;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Period;
import org.nasdanika.ncore.Temporal;
import org.nasdanika.ncore.util.NcoreUtil;

public class EObjectActionBuilder<T extends EObject> extends AdapterImpl implements ActionBuilder {
	
	protected org.nasdanika.common.Context context;

	public EObjectActionBuilder(T target, org.nasdanika.common.Context context) {
		setTarget(target);
		this.context = context;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T getTarget() {
		return (T) super.getTarget();
	}
	
	protected class EObjectActionResolverAdapter extends AdapterImpl implements EObjectActionResolver {
		
		@Override
		public boolean isAdapterForType(Object type) {
			return type == EObjectActionResolver.class;
		}
		
		private CollectionCompoundConsumer<Context> accumulator;
		
		public EObjectActionResolverAdapter() {
			accumulator = new CollectionCompoundConsumer<Context>("Resolve consumer accumulator");
			accumulator.add(new org.nasdanika.common.Consumer<Context>() {

				@Override
				public double size() {
					return 1;
				}

				@Override
				public String name() {
					return "Calling resolve()";
				}

				@Override
				public void execute(Context registry, ProgressMonitor progressMonitor) {
					EObjectActionBuilder.this.resolve((Action) getTarget(), registry, progressMonitor);					
				}
				
			});
		}
		
		public void add(org.nasdanika.common.Consumer<Context> consumer) {
			accumulator.add(consumer);
		}

		/**
		 * Executes and removes itself from the list of adapters.
		 */
		@Override
		public void execute(Context context, ProgressMonitor progressMonitor) {
			accumulator.execute(context, progressMonitor);		
			((Action) getTarget()).eAdapters().remove(this);
		}

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return "Resolving " + EObjectActionBuilder.this.name();
		}
		
	}
	
	/**
	 * Calls createAction() to creates an action and contained actions, configures it to the extent possible
	 * with the action hierarchy being built and not all action being available. Configuration
	 * which requires action cross-referencing shall be performed in resolve() and be invoked
	 * by a EObjectActionResolver adapter execute() method. The adapter is created and attached in this method.
	 * Resolve adapter is composite and execute() shall be invoked only for the root action's adapter if it is present.
	 * The resolving adapter removes itself upon resolution.  
	 */
	@Override
	public Action execute(BiSupplier<Action, BiConsumer<EObject,Action>> actionAndRegistry, ProgressMonitor progressMonitor) {
		EObjectActionResolverAdapter resolver = new EObjectActionResolverAdapter();
		BiConsumer<EObject, Action> registry = actionAndRegistry.getSecond();
		Action ret = buildAction(actionAndRegistry.getFirst(), registry, resolver::add, progressMonitor);
		registry.accept(getTarget(), ret);
		ret.eAdapters().add(resolver);				
		return ret;
	}
	
	/**
	 * Creates an action and contained actions. Configures the action to the extent possible
	 * with the action hierarchy being built and not all action being available. 
	 * Configuration which requires availability of the fully constructed action hierarchy 
	 * shall be performed in commands added to resolveCommandConsumer.
	 * @param resolveConsumer Consumer of consumers to be executed at the resolve phase.
	 * @param progressMonitor
	 * @return
	 */
	protected Action buildAction(
			Action action,
			BiConsumer<EObject,Action> registry, 
			java.util.function.Consumer<org.nasdanika.common.Consumer<Context>> resolveConsumer, 
			ProgressMonitor progressMonitor) {
		Action ret = action == null ? newAction(registry, resolveConsumer, progressMonitor) : action;

		// Diagnostic
		Collection<Diagnostic> diagnostic = getDiagnostic();
		int severity = Diagnostic.OK;
		for (Diagnostic de: diagnostic) {
			severity = Math.max(severity, de.getSeverity());
		}
		
		if (severity > 0) {
			ret.setColor(getSeverityColor(severity));
		}
		
		for (Diagnostic de: diagnostic) {
			Alert diagnosticAlert = BootstrapFactory.eINSTANCE.createAlert();
			diagnosticAlert.setColor(getSeverityColor(de.getSeverity()));
			diagnosticAlert.getContent().add(createText(StringEscapeUtils.escapeHtml4(de.getMessage())));
			ret.getContent().add(diagnosticAlert);
		}		
		
		// Anonymous
		
		// Children
		
		// Sections
		
		return ret;
	}
	
	/**
	 * Called by createAction(). 
	 * If target is and instance of {@link ModelElement} and its <code>actionPrototype</code> reference is set then,
	 * if the prototype is {@link Action}, its copy is created and returned.
	 * Otherwise the prototype is adapted to {@link ActionProvider} and the provider is used to create
	 * a new Action and return. 
	 * 
	 * If the target is not and instance of ModelElement or actionPrototype is not set, then this implementation calls {@link AppFactory} createAction().
	 * 
	 * Override to customize action creation before it gets populated by createAction().
	 * @param registry
	 * @param resolveConsumer
	 * @param progressMonitor
	 * @return
	 */
	protected Action newAction(
			BiConsumer<EObject,Action> registry, 
			java.util.function.Consumer<org.nasdanika.common.Consumer<Context>> resolveConsumer, 
			ProgressMonitor progressMonitor) {
		
		T semanticElement = getTarget();
		if (semanticElement instanceof ModelElement) {
			EObject actionPrototype = ((ModelElement) semanticElement).getActionPrototype();
			if (actionPrototype instanceof Action) {
				Action copy = EcoreUtil.copy((Action) actionPrototype);
				copy.setUuid(UUID.randomUUID().toString());
				TreeIterator<EObject> cit = copy.eAllContents();
				while (cit.hasNext()) {
					EObject next = cit.next();
					if (next instanceof ModelElement) {
						((ModelElement) next).setUuid(UUID.randomUUID().toString());
					}
				}
				return copy;
			}
			if (actionPrototype != null) {
				ActionProvider actionProvider = Objects.requireNonNull((ActionProvider) EcoreUtil.getRegisteredAdapter(actionPrototype, ActionProvider.class), "Cannot adapt " + actionPrototype + " to " + ActionProvider.class);
				Action ret = actionProvider.execute(registry, progressMonitor);
				ret.setUuid(UUID.randomUUID().toString());				
				TreeIterator<EObject> cit = ret.eAllContents();
				while (cit.hasNext()) {
					EObject next = cit.next();
					if (next instanceof ModelElement) {
						((ModelElement) next).setUuid(UUID.randomUUID().toString());
					}
				}
				return ret;
			}
		}
		return AppFactory.eINSTANCE.createAction();
	}
	
	protected NavigationPanel createLeftNavigation(Context context, ProgressMonitor progressMonitor) {	
		return null;
	}
	
	protected NavigationPanel createRightNavigation(Context context, ProgressMonitor progressMonitor) {	
		return null;
	}
	
	protected NavigationPanel createFloatLeftNavigation(Context context, ProgressMonitor progressMonitor) {	
		return null;
	}
	
	protected NavigationPanel createFloatRightNavigation(Context context, ProgressMonitor progressMonitor) {	
		return null;
	}
	
	/**
	 * @return Diagnostics for the semantic element. This implementation adapts target to {@link DiagnosticProvider} and delegates to it if not null or returns an empty collection.
	 */
	protected Collection<Diagnostic> getDiagnostic() {
		Object adapter = EcoreUtil.getRegisteredAdapter(getTarget(), DiagnosticProvider.class);		
		return adapter instanceof DiagnosticProvider ? ((DiagnosticProvider) adapter).getDiagnostic() : Collections.emptyList();
	}
	
	/**
	 * Creates an action with a diagnostic summary for the object's contained objects recursively.
	 * This action is added to object's action navigation actions. 
	 * @return Diagnostic aciton. This implementation return null.
	 */
	protected Action createDiagnosticAction(Action action, Context context, ProgressMonitor progressMonitor) {
		return null;
	}	
	
	/**
	 * Used to report property diagnostic in the properties table.
	 * @return Diagnostics for a {@link EStructuralFeature} of the semantic element. 
	 * This implementation adapts target to {@link DiagnosticProvider} and delegates to it if not null or returns an empty collection. 
	 */
	protected Collection<Diagnostic> getFeatureDiagnostic(EStructuralFeature feature) {
		Object adapter = EcoreUtil.getRegisteredAdapter(getTarget(), DiagnosticProvider.class);		
		return adapter instanceof DiagnosticProvider ? ((DiagnosticProvider) adapter).getFeatureDiagnostic(feature) : Collections.emptyList();
	}
		
	protected void resolve(Action action, Context context, ProgressMonitor progressMonitor) {
		action.setLeftNavigation(createLeftNavigation(context, progressMonitor));
		action.setFloatLeftNavigation(createFloatLeftNavigation(context, progressMonitor));
		action.setRightNavigation(createRightNavigation(context, progressMonitor));
		action.setFloatRightNavigation(createFloatRightNavigation(context, progressMonitor));
		
		// Navigation (context)
		Action diagnosticAction = createDiagnosticAction(action, context, progressMonitor);
		if (diagnosticAction != null) {
			action.getNavigation().add(diagnosticAction);
		}
		
		// Content
		
		/*
		 * TODO - iterate over representations
		 * for drawio representations - decode, go trough the elements and for elements with target-uri property:
		 * - find the target in the resource set
		 * - if found - get its action
		 * - if action is present - set link if the link is blank (or is a page link?), set tooltip if the tooltip is blank.
		 * then encode the representation and add to this action's representation under the same key to be used in action rendering.
		 */
		
		// Properties table
		Table propertiesTable = createPropertiesTable(action, context, progressMonitor);
		if (propertiesTable != null) {
			action.getContent().add(propertiesTable);
		}
		
		// Resources
	}
	
	/**
	 * Creates a properties table from properties
	 * @param context
	 * @param progressMonitor
	 * @return Table or null if there are no properties.
	 */
	protected Table createPropertiesTable(Action action, Context context, ProgressMonitor progressMonitor) {
		List<ETypedElement> properties = getProperties();
		if (properties == null || properties.isEmpty()) {
			return null;
		}
		
		Table ret = BootstrapFactory.eINSTANCE.createTable();
		EList<TableRow> rows = ret.getRows();
		for (ETypedElement property: properties) {
			Object propertyValue = getTypedElementValue(property);
			Collection<Diagnostic> featureDiagnostic = property instanceof EStructuralFeature ? getFeatureDiagnostic((EStructuralFeature) property) : Collections.emptyList();
			int severity = Diagnostic.OK;
			for (Diagnostic diagnostic: featureDiagnostic) {
				severity = Math.max(severity, diagnostic.getSeverity());
			}
			
			boolean isSetOrNotEmpty = property instanceof EStructuralFeature && ((EStructuralFeature) property).isChangeable() ? isSet((EStructuralFeature) property) : !isEmptyValue(property, propertyValue);
			if (isSetOrNotEmpty || !featureDiagnostic.isEmpty()) {				
				TableRow propertyRow = BootstrapFactory.eINSTANCE.createTableRow();
				rows.add(propertyRow);
				
//				if (severity > 0) {
//					propertyRow.setColor(getSeverityColor(severity));
//				}
				
				EList<TableCell> propertyCells = propertyRow.getCells();
	
				TableCell propertyHeader = BootstrapFactory.eINSTANCE.createTableCell();
				propertyHeader.setHeader(true);
				propertyCells.add(propertyHeader);
				propertyHeader.getContent().add(createETypedElementLabel(property, false));
				if (severity > 0) {
					Appearance headerAppearance = BootstrapFactory.eINSTANCE.createAppearance();
					propertyHeader.setAppearance(headerAppearance);
					org.nasdanika.html.model.bootstrap.Text aText = BootstrapFactory.eINSTANCE.createText();
					headerAppearance.setText(aText);
					aText.setColor(getSeverityColor(severity));
				}
				
				TableCell propertyValueCell = BootstrapFactory.eINSTANCE.createTableCell();
				propertyCells.add(propertyValueCell);
				if (isSetOrNotEmpty) {
					EObject renderedValue = renderValue(action, property, propertyValue, context, progressMonitor);
					if (renderedValue != null) {
						propertyValueCell.getContent().add(renderedValue);
					}
				}
				for (Diagnostic diagnostic: featureDiagnostic) {
					Alert diagnosticAlert = BootstrapFactory.eINSTANCE.createAlert();
					diagnosticAlert.setColor(getSeverityColor(diagnostic.getSeverity()));
					diagnosticAlert.getContent().add(createText(StringEscapeUtils.escapeHtml4(diagnostic.getMessage())));
					propertyValueCell.getContent().add(diagnosticAlert);
				}
				
			}
		}
				
		return ret;
	}
	
	protected boolean isSet(EStructuralFeature feature) {
		if (feature == NcorePackage.Literals.MODEL_ELEMENT__URI) {
			return NcoreUtil.getUri(getTarget()) != null;
		}
		return getTarget().eIsSet((EStructuralFeature) feature);
	}
	
	protected List<ETypedElement> getProperties() {
		List<ETypedElement> properties = new ArrayList<>();
		if (getTarget() instanceof Marked) {
			properties.add(NcorePackage.Literals.MARKED__MARKERS);
		}
		if (getTarget() instanceof ModelElement) {
			properties.add(NcorePackage.Literals.MODEL_ELEMENT__URI);
		}
		if (getTarget() instanceof Period) {
			properties.add(NcorePackage.Literals.PERIOD__START);
			properties.add(NcorePackage.Literals.PERIOD__END);
			properties.add(NcorePackage.Literals.PERIOD__DURATION);
			
		}
//		ret.add(NcorePackage.Literals.MODEL_ELEMENT__UUID);
		return properties;
	}
			
	/**
	 * Creates a label for {@link ETypedElement}s such as {@link EAttribute} and {@link EReference} to be used
	 * in (table) headers, tabs, etc. This method creates a label using {@link AppFactory} instance, passes it to configureETypedElementLabel and returns.
	 * @param eTypedElement Typed element for which to create a label.
	 * @param inModal If true then the label is created for use in a modal dialog and shall not contain triggers for other modal dialogs.
	 * @return
	 */
	protected Label createETypedElementLabel(ETypedElement eTypedElement, boolean inModal) {
		Label label = AppFactory.eINSTANCE.createLabel();
		configureETypedElementLabel(eTypedElement, label, inModal);		
		return label;
	}

	/**
	 * Configures a typed element label or action.   
	 * @param eTypedElement
	 * @param label
	 * @param inModal
	 */
	protected void configureETypedElementLabel(ETypedElement eTypedElement, Label label, boolean inModal) {
		label.setText(typedElementLabelText(eTypedElement));		
		label.setIcon(typedElementIcon(eTypedElement));
		
//        icon: far fa-question-circle
//        tooltip: Some quick info
//        attributes:
//          style:
//            content-text: "vertical-align:super;font-size:small"
//        modal:                             
//          header:
//            content:
//              content-text: Very informative indeed!
//            appearance:
//              background: LIGHT                              
//          body:
//            content:
//              content-text: Extensive description of the first navigation action...
//          size: large
//          centered: true                                         		
		
		// Tooltip help example
//		Label helpDecorator = AppFactory.eINSTANCE.createLabel();
//		helpDecorator.setTooltip("Purum/param");
//		helpDecorator.setIcon("far fa-question-circle");
//		helpDecorator.getAttributes().put("style", createText("vertical-align:super;font-size:small"));
//		label.setDecorator(helpDecorator);
		
		// Modal help example
//		Link helpDecorator = AppFactory.eINSTANCE.createLink();
//		helpDecorator.setTooltip("Purum/param");
//		helpDecorator.setIcon("far fa-question-circle");
//		helpDecorator.getAttributes().put("style", createText("vertical-align:super;font-size:x-small;margin-left:0.2em"));
//		Modal helpModal = BootstrapFactory.eINSTANCE.createModal();
//		helpModal.setSize("large");
//		helpModal.setCentered(true);
//		BootstrapElement header = BootstrapFactory.eINSTANCE.createBootstrapElement();
//		header.getContent().add(createText("Very informative"));
//		helpModal.setHeader(header);
//		BootstrapElement body = BootstrapFactory.eINSTANCE.createBootstrapElement();
//		body.getContent().add(createText("Extensive description"));		
//		helpModal.setBody(body);
//		helpDecorator.setModal(helpModal);
//		
//		label.setDecorator(helpDecorator);
		
		// TODO - tooltip, description
	}
	
	protected String typedElementLabelText(ETypedElement type) {
		if (type == NcorePackage.Literals.MARKED__MARKERS) {
			@SuppressWarnings("unchecked")
			EList<Marker> markers = (EList<Marker>) getTarget().eGet((EStructuralFeature) type);
			if (markers != null && markers.size() == 1 &&  markers.get(0) instanceof GitMarker) {
				EMap<String, String> remotes = ((GitMarker) markers.get(0)).getRemotes();
				if (remotes.size() == 1) {
					return StringUtils.capitalize(remotes.get(0).getKey());
				}
			}
		}
		
		return NcoreUtil.getNasdanikaAnnotationDetail(type, EmfUtil.LABEL_KEY, Util.nameToLabel(type.getName()));
	}
	
	protected String typedElementIcon(ETypedElement member) {
		return NcoreUtil.getNasdanikaAnnotationDetail(member, EmfUtil.ICON_KEY, NcoreUtil.getNasdanikaAnnotationDetail(member.getEType(), EmfUtil.ICON_KEY));
	}	
	
	/**
	 * Adapts child eObject to {@link ActionSupplier}.
	 * If there is a resolver adapter attached to the child and resolveConsumer is not null, calls resolveConsumer with the adapter as an argument.
	 * @param child
	 * @return
	 */
	protected ActionProvider adaptChild(EObject child) {
		return EObjectAdaptable.adaptTo(child, ActionProvider.class);
	}	
	
	protected Action createChildAction(
			EObject child, 
			BiConsumer<EObject, Action> registry,
			Consumer<org.nasdanika.common.Consumer<Context>> resolveConsumer,
			ProgressMonitor progressMonitor) {
		
		ActionProvider provider = adaptChild(child);
		if (provider == null) {
			return null;
		}
		
		Action ret = provider.execute(registry, progressMonitor);
		
		if (ret != null && resolveConsumer != null) {
			Adapter childResolver = EcoreUtil.getExistingAdapter(ret, EObjectActionResolver.class);
			if (childResolver instanceof EObjectActionResolver) {
				resolveConsumer.accept((EObjectActionResolver) childResolver);
			}
		}
		
		return ret;
	}

	@Override
	public double size() {
		return 1;
	}

	@Override
	public String name() {
		return "Action provider for " + getTarget();
	}
	
	/**
	 * Empty values are not shown in property tables unless there is a diagnostic to show.
	 * This method returns true if value is null, an empty string, an empty collection, false for booleans, or zero for numbers.
	 * @param member
	 * @param value
	 * @return
	 */
	protected boolean isEmptyValue(ETypedElement member, Object value) {
		if (value == null) {
			return true;
		}
		if (value instanceof Collection) {
			return ((Collection<?>) value).isEmpty();
		}
		if (value instanceof String) {
			return Util.isBlank((String) value);		
		}
		if (value instanceof Number) {
			if (value.equals(0)) {
				return true;
			}
			
			if (value instanceof Double) {				
				if (((Double) value).isNaN()) {
					return true;
				}
				return Math.abs((Double) value) < 2 * Double.MIN_VALUE;
			}
			
			if (value instanceof Float) {
				if (((Float) value).isNaN()) {
					return true;
				}
				return Math.abs((Float) value) < 2 * Float.MIN_VALUE;
			}
		}
		
		if (value instanceof Temporal) {
			Temporal temporal = (Temporal) value;
			return temporal.getInstant() == null 
					&& temporal.getBase() == null
					&& temporal.getLowerBounds().isEmpty()
					&& temporal.getUpperBounds().isEmpty();
		}
		
		return Boolean.FALSE.equals(value);
	}
	
	protected Object getTypedElementValue(ETypedElement typedElement) {
		return getTypedElementValue(getTarget(), typedElement);
	}
		
	protected Object getTypedElementValue(EObject eObject, ETypedElement typedElement) {		
		if (typedElement == EcorePackage.Literals.EOBJECT___ECONTAINER) {
			return eObject.eContainer();
		}
		
		if (typedElement == NcorePackage.Literals.MODEL_ELEMENT__URI) {
			return NcoreUtil.getUri(eObject);
		}
		
		if (typedElement instanceof EStructuralFeature) {
			return eObject.eGet((EStructuralFeature) typedElement);
		}
		
		EOperation eOp = (EOperation) typedElement;
		try {
			return eObject.eInvoke(eOp, getArguments(eObject, eOp));
		} catch (InvocationTargetException e) {
			throw new ExecutionException(e, this);
		}
	}
	
	protected EList<?> getArguments(EObject eObject, EOperation eOp) {
		if (eOp.getEParameters().isEmpty()) {
			return ECollections.emptyEList();
		}
		throw new UnsupportedOperationException();
	}	
	
	/**
	 * Adds textual content.
	 * @param content
	 */
	protected static void addContent(Action action, String content) {
		if (!Util.isBlank(content)) {
			Text text = createText(content);
			action.getContent().add(text);
		}
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
	 * Renders remote link for GitHub, returns text otherwise.
	 * @param marker
	 * @param remoteUrl
	 * @return
	 */
	protected EObject gitRemoteLink(GitMarker marker, String remoteUrl) {
		GitLinkResolver gitLinkResolver = null;
		if (context != null) {
			gitLinkResolver = context.get(GitLinkResolver.class);
		}
		if (gitLinkResolver == null) {
			gitLinkResolver = GitLinkResolver.INSTANCE;
		}
		String remoteLocation = gitLinkResolver.resolve(marker, remoteUrl);
		String position = marker.getPosition();
		EList<String> headRefs = marker.getHeadRefs();
		if (remoteLocation == null) {			
			org.nasdanika.html.Table table = HTMLFactory.INSTANCE.table();
			
			Row remoteRow = table.row();
			remoteRow.header("Remote");
			remoteRow.cell(remoteUrl);
			
			Row pathRow = table.row();
			pathRow.header("Path");
			pathRow.cell(marker.getPath());
			
			if (!Util.isBlank(position)) {
				Row positionRow = table.row();			
				positionRow.header("Positon");
				positionRow.cell(position);
			}
			
			if (!headRefs.isEmpty()) {
				Row refsRow = table.row();
				refsRow.header("Refs");
				if (headRefs.size() == 1) {
					refsRow.cell(headRefs.get(0));
				} else {
					refsRow.cell(HTMLFactory.INSTANCE.ul(headRefs));
				}				
			}
			
			return createText(table.toString());			
		}
		
		StringBuilder textBuilder = new StringBuilder(marker.getPath());			
		if (!Util.isBlank(position)) {
			textBuilder.append(" ").append(position);
		}
		
		Link link = AppFactory.eINSTANCE.createLink();
		link.setText(textBuilder.toString());
		link.setLocation(remoteLocation);
		if (!headRefs.isEmpty()) {
			link.setTooltip(String.join(", ", headRefs));
		}
		if (remoteUrl.startsWith("https://github.com")) {
			link.setIcon("fab fa-github");
		}		
		return link;						
	}

	/**
	 * Renders value.
	 * @param base Context/base action.
	 * @param typedElement Optional typed element for customizing behavior.
	 * @param value Value to render
	 * @param context Context
	 * @param progressMonitor progress monitor.
	 * @return rendered value
	 */
	protected EObject renderValue(
			Action base, 
			ETypedElement typedElement,
			Object value, 
			Context context, 
			ProgressMonitor progressMonitor) {		
		if (isEmptyValue(typedElement, value)) {
			return null;
		}
		
		if (value instanceof Instant) {
			return renderValue(base, typedElement, new Date(((Instant) value).toEpochMilli()), context, progressMonitor);
		}
		
		if (value == Boolean.TRUE) {
			return createText("<i class=\"fas fa-check\"></i>");
		}
		
		if (value instanceof Date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime((Date) value);
			String datePart = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault()).format((Date) value);
			if (calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 0 && calendar.get(Calendar.SECOND) == 0) {
				return createText(datePart);
			}
			String timePart = DateFormat.getTimeInstance().format((Date) value);
			return createText(datePart + " " + timePart);
		}
		
		if (value instanceof GitMarker) {
			GitMarker marker = (GitMarker) value;
			EMap<String, String> remotes = marker.getRemotes();
			if (remotes.size() == 1) {
				return gitRemoteLink(marker, remotes.get(0).getValue());
			} else if (!remotes.isEmpty()) {				
				// TODO - a list name -> link
				throw new UnsupportedOperationException("Multiple remotes are not yet supported");
			}
		}
		
		if (value instanceof Marker) {
			Marker marker = (Marker) value;
			StringBuilder textBuilder = new StringBuilder(marker.getLocation());
			String position = marker.getPosition();			
			if (!Util.isBlank(position)) {
				textBuilder.append(" ").append(position);
			}
			return createText(textBuilder.toString());
		}
		
		if (value instanceof Temporal) {
			return temporalValue(base, typedElement, (Temporal) value, context, progressMonitor);
		}
		
		if (value instanceof Duration) {
			return createText(Temporal.formatDuration((Duration) value));
		}		
		
		if (value instanceof EObject) {
			Action valueAction = context.getAction((EObject) value);
			if (valueAction == null) {
				return EcoreUtil.copy((EObject) value); // Returning copy to be handled at later stages, e.g. render markdown during site generation. 
			}
			
			URI location = context.resolve(valueAction, base);
			if (location == null) { 
				throw new IllegalArgumentException("Cannot resolve value action URI: " + value);
			}
			Link link = AppFactory.eINSTANCE.createLink();
			link.setText(valueAction.getText());
			link.setIcon(valueAction.getIcon());
			link.setTooltip(valueAction.getTooltip());
			link.setLocation(location.toString());
			return link;
		} else if (value instanceof EMap) {
			return createEMapTable(typedElement, (EMap<?,?>) value, base, context, progressMonitor);
		} else if (value instanceof Collection) {
			Collection<?> vc = (Collection<?>) value;
			if (vc.isEmpty()) {
				return null;
			}
			if (vc.size() == 1) {
				return renderValue(base, typedElement, vc.iterator().next(), context, progressMonitor);
			}			
			
			return renderList((Collection<?>) value, true, null, base, typedElement, context, progressMonitor);
		}
		
		if (typedElement == NcorePackage.Literals.MODEL_ELEMENT__URI) {
			return createText(value + " <i class='far fa-copy nsd-copy-to-clipboard' style='cursor:pointer' title='Copy URI to clipboard' onclick='navigator.clipboard.writeText(\"" + StringEscapeUtils.escapeHtml4(String.valueOf(value)) + "\")'/>");
		}
		
		return createText(String.valueOf(value));
	}
	
	/**
	 * Functional interface that provides content for an element, e.g. in a list - description or child items for a nested list.
	 * @author Pavel
	 *
	 */
	public interface ContentProvider<T> {
		
		/**
		 * Creates element content
		 * @param element Element for which create content. 
		 * @param base Context/base action.
		 * @param typedElement Optional typed element for customizing behavior.
		 * @param context Context
		 * @param progressMonitor progress monitor.
		 * @return rendered value
		 */
		List<EObject> createContent(
				T element,
				Action base, 
				ETypedElement typedElement,
				Context context, 
				ProgressMonitor progressMonitor);		
		
	}
	
	/**
	 * Renders a list.
	 * @param base Context/base action.
	 * @param typedElement Optional typed element for customizing behavior.
	 * @param value Value to render
	 * @param context Context
	 * @param progressMonitor progress monitor.
	 * @return null for null or empty collections or a list.
	 */
	protected <E> org.nasdanika.html.model.html.Tag renderList(
			Collection<E> elements,
			boolean ordered,
			ContentProvider<E> contentProvider,
			Action base, 
			ETypedElement typedElement,
			Context context, 
			ProgressMonitor progressMonitor) {		
		if (elements == null || elements.isEmpty()) {
			return null;
		}
			
		org.nasdanika.html.model.html.Tag ol = HtmlFactory.eINSTANCE.createTag();
		ol.setName(ordered ? "ol" : "ul");
		for (E element: elements) {
			org.nasdanika.html.model.html.Tag li = HtmlFactory.eINSTANCE.createTag();
			li.setName("li");
			ol.getContent().add(li);
			li.getContent().add(renderValue(base, typedElement, element, context, progressMonitor));
			if (contentProvider != null) {
				List<EObject> content = contentProvider.createContent(element, base, typedElement, context, progressMonitor);
				if (content != null) {
					li.getContent().addAll(content);
				}
			}
		}
		return ol;
	}
		
	/**
	 * Creates a table from EMap entries
	 * @param typedElement Optional classifier to customize behavior
	 * @param eMap EMap
	 * @param action Context/base action for relativising references
	 * @param context Context
	 * @param progressMonitor Progress montior
	 * @return null if emap is empty or null, a table with keys and values otherwise.
	 */
	protected Table createEMapTable(
			ETypedElement typedElement,
			EMap<?,?> eMap, 
			Action action, 
			Context context, 
			ProgressMonitor progressMonitor) {
		if (eMap == null || eMap.isEmpty()) {
			return null;
		}
		
		Table ret = BootstrapFactory.eINSTANCE.createTable();
		EList<TableRow> rows = ret.getRows();
		for (Object key: sortKeys(eMap.keySet())) {
			Object value = eMap.get(key);
			TableRow entryRow = BootstrapFactory.eINSTANCE.createTableRow();
			rows.add(entryRow);
			EList<TableCell> entryCells = entryRow.getCells();

			TableCell keyHeader = BootstrapFactory.eINSTANCE.createTableCell();
			keyHeader.setHeader(true);
			entryCells.add(keyHeader);
			keyHeader.getContent().add(createText(String.valueOf(key)));
			
			TableCell valueCell = BootstrapFactory.eINSTANCE.createTableCell();
			entryCells.add(valueCell);
			EObject renderedValue = renderValue(action, typedElement, value, context, progressMonitor);
			if (renderedValue != null) {
				valueCell.getContent().add(renderedValue);
			}
		}
				
		return ret;
	}
	
	protected <K> List<K> sortKeys(Set<K> keys) {
		return keys.stream().sorted((a,b) -> String.valueOf(a).compareTo(String.valueOf(b))).collect(Collectors.toList());
	}
	
	protected ColumnBuilder<EObject> createColumnBuilder(ETypedElement typedElement) {
		return createColumnBuilder(typedElement, createETypedElementLabel(typedElement, false));
	}
	
	protected ColumnBuilder<EObject> createColumnBuilder(ETypedElement typedElement, String label) {
		return createColumnBuilder(typedElement, createText(label));
	}
	
	protected ColumnBuilder<EObject> createColumnBuilder(ETypedElement typedElement, EObject label) {
		return new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement tElement, 
					Context context,
					ProgressMonitor progressMonitor) {
				header.getContent().add(label);				
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement tElement, 
					Context context,
					ProgressMonitor progressMonitor) {
				
				Object value = getTypedElementValue(rowElement, typedElement);
				EObject renderedValue = renderValue(base, typedElement, value, context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);
				}
			}
		};
	}
	
	/**
	 * Creates a column builder for the row element itself.
	 * @param label
	 * @return
	 */
	protected ColumnBuilder<EObject> createColumnBuilder(String label) {
		return createColumnBuilder(createText(label));
	}
	
	/**
	 * Creates a column builder for the row element itself.
	 * @param label
	 * @return
	 */
	protected ColumnBuilder<EObject> createColumnBuilder(EObject label) {
		return new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement tElement, 
					Context context,
					ProgressMonitor progressMonitor) {
				header.getContent().add(label);				
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement tElement, 
					Context context,
					ProgressMonitor progressMonitor) {
				
				EObject renderedValue = renderValue(base, null, (Object) rowElement, context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);
				}
			}
		};
	}
	
	
	protected List<ColumnBuilder<? super EObject>> createColumnBuilders(Collection<ETypedElement> typedElements) {
		return typedElements.stream().map(this::createColumnBuilder).collect(Collectors.toList());
	}
	
	protected List<ColumnBuilder<? super EObject>> createColumnBuilders(ETypedElement... typedElements) {
		return createColumnBuilders(Arrays.asList(typedElements));
	}

	/**
	 * Builds a static table.
	 * @param <T>
	 * @param elements
	 * @param base
	 * @param typedElement
	 * @param context
	 * @param progressMonitor
	 * @param columnBuilders
	 * @return
	 */
	@SafeVarargs
	public static <T> Table buildTable(
			Collection<? extends T> elements, 
			Action base, 
			ETypedElement typedElement,
			Context context, 
			ProgressMonitor progressMonitor,			
			ColumnBuilder<? super T>... columnBuilders) {
		return buildTable(elements, Arrays.asList(columnBuilders), base, typedElement, context, progressMonitor);
	}
	
	/**
	 * Builds a static table
	 * @param <T>
	 * @param elements
	 * @param columnBuilders
	 * @param base
	 * @param typedElement
	 * @param context
	 * @param progressMonitor
	 * @return
	 */
	public static <T> Table buildTable(
			Collection<? extends T> elements, 
			Collection<ColumnBuilder<? super T>> columnBuilders,
			Action base, 
			ETypedElement typedElement,
			Context context, 
			ProgressMonitor progressMonitor) {
		Table ret = BootstrapFactory.eINSTANCE.createTable();
		TableHeader header = BootstrapFactory.eINSTANCE.createTableHeader();
		ret.setHeader(header);
		TableRow headerRow = BootstrapFactory.eINSTANCE.createTableRow();
		header.getRows().add(headerRow);
		EList<TableCell> headerRowCells = headerRow.getCells();
		for (ColumnBuilder<? super T> cb: columnBuilders) {
			TableCell headerCell = BootstrapFactory.eINSTANCE.createTableCell();
			headerCell.setHeader(true);
			headerRowCells.add(headerCell);
			cb.buildHeader(headerCell, base, typedElement, context, progressMonitor);
		}
		
		TableSection body = BootstrapFactory.eINSTANCE.createTableSection();
		ret.setBody(body);
		EList<TableRow> bodyRows = body.getRows();
		for (T element: elements) {
			TableRow elementRow = BootstrapFactory.eINSTANCE.createTableRow();
			bodyRows.add(elementRow);
			EList<TableCell> elementRowCells = elementRow.getCells();
			for (ColumnBuilder<? super T> cb: columnBuilders) {
				TableCell elementCell = BootstrapFactory.eINSTANCE.createTableCell();
				elementRowCells.add(elementCell);
				cb.buildCell(element, elementCell, base, typedElement, context, progressMonitor);
			}
		}
		return ret;
	}
		
	protected DynamicColumnBuilder<EObject> createDynamicColumnBuilder(ETypedElement typedElement, boolean visible) {
		return createDynamicColumnBuilder(typedElement, createETypedElementLabel(typedElement, false), visible);
	}
	
	protected DynamicColumnBuilder<EObject> createDynamicColumnBuilder(ETypedElement typedElement, String label, boolean visible) {
		return createDynamicColumnBuilder(typedElement, createText(label), visible);
	}
	
	protected DynamicColumnBuilder<EObject> createDynamicColumnBuilder(ETypedElement typedElement, EObject label, boolean visible) {
		return new DynamicColumnBuilder<EObject>() {

			@Override
			public Map buildHeader(
					Action base, 
					ETypedElement tElement, 
					Context context,
					ProgressMonitor progressMonitor) {
				
				org.nasdanika.ncore.Map header = NcoreFactory.eINSTANCE.createMap();
				if (visible) {
					header.put("visible", visible);
				}
				header.put("key", typedElement.getName()); // TODO - signature for EOperations
				header.put("label", label);
				return header;
			}

			@Override
			public void buildCell(
					EObject rowElement, 
					Map row, 
					Action base, 
					ETypedElement tElement, 
					Context context,
					ProgressMonitor progressMonitor) {
				
				Object value = getTypedElementValue(rowElement, typedElement);
				EObject renderedValue = renderValue(base, typedElement, value, context, progressMonitor);
				if (renderedValue != null) {
					row.put(typedElement.getName(), renderedValue); // TODO - signature for EOperations
				}
				
			}
		};
	}
	
	/**
	 * Creates a dynamic column builder for the row element itself.
	 * @param label
	 * @return
	 */
	protected DynamicColumnBuilder<EObject> createDynamicColumnBuilder(String label, boolean visible) {
		return createDynamicColumnBuilder(createText(label), visible);
	}
	
	/**
	 * Creates a dynamic column builder for the row element itself.
	 * @param label
	 * @return
	 */
	protected DynamicColumnBuilder<EObject> createDynamicColumnBuilder(EObject label, boolean visible) {
		return new DynamicColumnBuilder<EObject>() {
			
			@Override
			public org.nasdanika.ncore.Map buildHeader(
					Action base, 
					ETypedElement tElement, 
					Context context,
					ProgressMonitor progressMonitor) {				
				org.nasdanika.ncore.Map header = NcoreFactory.eINSTANCE.createMap();
				if (visible) {
					header.put("visible", visible);
				}
				header.put("key", "__this");
				header.put("label", label);
				return header;
			}

			@Override
			public void buildCell(
					EObject rowElement, 
					Map row, 
					Action base, 
					ETypedElement tElement, 
					Context context,
					ProgressMonitor progressMonitor) {
				
				EObject renderedValue = renderValue(base, null, (Object) rowElement, context, progressMonitor);
				if (renderedValue != null) {
					row.put("__this", renderedValue);
				}				
			}
		};
	}
	
	protected List<DynamicColumnBuilder<? super EObject>> createDynamicColumnBuilders(Collection<ETypedElement> typedElements) {
		return typedElements.stream().map(te -> createDynamicColumnBuilder(te, true)).collect(Collectors.toList());
	}
	
	protected List<DynamicColumnBuilder<? super EObject>> createDynamicColumnBuilders(ETypedElement... typedElements) {
		return createDynamicColumnBuilders(Arrays.asList(typedElements));
	}
	
	/**
	 * Builds a dynamic configurable table.
	 * @param <T>
	 * @param elements
	 * @param base
	 * @param typedElement
	 * @param configKey Browser local storage key for table configuration.
	 * @param appId Id for the application div. Generated if null.
	 * @param context
	 * @param progressMonitor
	 * @param columnBuilders
	 * @return
	 */
	@SafeVarargs
	public static <T> Tag buildDynamicTable(
			Collection<? extends T> elements, 
			Action base, 
			ETypedElement typedElement,
			String configKey,
			String appId,
			Context context, 
			ProgressMonitor progressMonitor,			
			DynamicColumnBuilder<? super T>... columnBuilders) {
		return buildDynamicTable(elements, Arrays.asList(columnBuilders), base, typedElement, configKey, appId, context, progressMonitor);
	}
	
	/**
	 * Builds a dynamic configurable table.
	 * @param <T>
	 * @param elements
	 * @param columnBuilders
	 * @param base
	 * @param typedElement
	 * @param configKey Browser local storage key for table configuration.
	 * @param appId Id for the application div. Generated if null.
	 * @param context
	 * @param progressMonitor
	 * @return
	 */
	public static <T> Tag buildDynamicTable(
			Collection<? extends T> elements, 
			Collection<DynamicColumnBuilder<? super T>> columnBuilders,
			Action base, 
			ETypedElement typedElement,
			String configKey,
			String appId,
			Context context, 
			ProgressMonitor progressMonitor) {

		org.nasdanika.ncore.List columns = NcoreFactory.eINSTANCE.createList();
		for (DynamicColumnBuilder<? super T> cb: columnBuilders) {
			org.nasdanika.ncore.Map column = cb.buildHeader(base, typedElement, context, progressMonitor);
			columns.getValue().add(column);
		}
		
		org.nasdanika.ncore.List items = NcoreFactory.eINSTANCE.createList();
		for (T element: elements) {
			org.nasdanika.ncore.Map item = NcoreFactory.eINSTANCE.createMap();
			for (DynamicColumnBuilder<? super T> cb: columnBuilders) {
				cb.buildCell(element, item, base, typedElement, context, progressMonitor);
			}
			items.getValue().add(item);
		}
		Tag table = HtmlFactory.eINSTANCE.createTag();
		table.setName("nsd-table");
		table.getAttributes().put(":columns", columns);
		table.getAttributes().put(":items", items);
		if (!Util.isBlank(configKey)) {
			table.getAttributes().put("config-key", createText(configKey));			
		}
		
		Tag appDiv = HtmlFactory.eINSTANCE.createTag();
		appDiv.getContent().add(table);
		appDiv.setName("div");
		if (Util.isBlank(appId)) {
			appId = HTMLFactory.INSTANCE.nextId()+"-vue-app";
		}
		appDiv.getAttributes().put("id", createText(appId));
		
		Tag wrapperDiv = HtmlFactory.eINSTANCE.createTag();
		wrapperDiv.getContent().add(appDiv);
		wrapperDiv.setName("div");
		
		Script script = HtmlFactory.eINSTANCE.createScript();
		wrapperDiv.getContent().add(script);
		String code = "new Vue({ el: '#" + appId + "' });";
		script.setSource(createText(code));
		
		return wrapperDiv;
	}
	
	/**
	 * Creates an action with a table of typed element values.
	 * @param typedElement
	 * @param columnBuilders
	 * @return
	 */
	protected Action createTableAction(
			ETypedElement typedElement,
			Collection<ColumnBuilder<? super EObject>> columnBuilders,
			Action base, 
			Context context, 
			ProgressMonitor progressMonitor) {
		Action ret = AppFactory.eINSTANCE.createAction();
		configureETypedElementLabel(typedElement, ret, false);
		
		@SuppressWarnings("unchecked")
		Table table = buildTable(
				(Collection<EObject>) getTypedElementValue(typedElement), 
				columnBuilders, 
				base, 
				typedElement, 
				context, 
				progressMonitor);
		
		ret.getContent().add(table);
		return ret;
	}

	/**
	 * Creates an action with a table of typed element values.
	 * @param typedElement
	 * @param columnBuilders
	 * @return
	 */
	protected Action createTableAction(
			ETypedElement typedElement,
			Action base, 
			Context context, 
			ProgressMonitor progressMonitor,						
			ETypedElement... columnElements) {
		return createTableAction(
				typedElement, 
				createColumnBuilders(columnElements), 
				base, 
				context, 
				progressMonitor);
	}
	
	public static Color getSeverityColor(int severity) {
		switch (severity) {
		case Diagnostic.OK:
			return Color.SUCCESS;
		case Diagnostic.CANCEL:
			return Color.SECONDARY;
		case Diagnostic.ERROR:
			return Color.DANGER;
		case Diagnostic.INFO:
			return Color.INFO;
		case Diagnostic.WARNING:
			return Color.WARNING;
		default:
			return Color.PRIMARY;
		}
	}
	
	// --- Temporal ---
	
	protected EObject temporalValue(
			Action baseAction, 
			ETypedElement typedElement,
			Temporal temporal, 
			Context context, 
			ProgressMonitor progressMonitor) {
		
//		if (temporal instanceof Event) {
//			return super.memberValue(member, temporal, viewGenerator, progressMonitor);
//		}
		
		Temporal normalized = temporal.normalize();
		Instant instant = normalized.getInstant();
		if (instant != null) {
			return renderValue(baseAction, typedElement, instant, context, progressMonitor);
		}
		
//		Duration offset = normalized.getOffset();
//		Temporal temporalBase = normalized.getBase();
//		if ((offset == null || offset.equals(Duration.ZERO)) && temporalBase != null) {
//			return "Coincides with " 
//					+ temporalValue(typedElement, temporalBase, viewGenerator, progressMonitor, withModalTrigger)
//					+ (withModalTrigger ? temporalInfoModalTrigger(member, temporal, viewGenerator, progressMonitor) : "");
//		}
//		
//		if (temporalBase != null) {
//			return Temporal.formatDuration(offset.abs())
//					+ (offset.isNegative() ? " before " : " after ")
//					+ temporalValue(member, temporalBase, viewGenerator, progressMonitor, withModalTrigger)
//					+ (withModalTrigger ? temporalInfoModalTrigger(member, temporal, viewGenerator, progressMonitor) : "");
//		}
//		
//		// Using containment
//		EObject tc = temporal.eContainer();
//		if (tc != null) {
//			ViewAction<?> tcv = EObjectAdaptable.adaptTo(tc, ViewAction.class);
//			EReference cf = temporal.eContainmentFeature();
//			return (tcv == null ? tc : viewGenerator.link(tcv)) + (cf == null ? "" : " " + cf.getName()) + bounds(member, temporal, viewGenerator, progressMonitor, withModalTrigger);
//		}
//		
//		// Should never be the case - should be handled as an empty value.
//		return temporal.toString()
//				+ (withModalTrigger ? temporalInfoModalTrigger(member, temporal, viewGenerator, progressMonitor) : "");
		
		throw new UnsupportedOperationException("Relative temporals are not supported yet");
	}

//	protected Object bounds(ETypedElement member, Temporal temporal, ViewGenerator viewGenerator, ProgressMonitor progressMonitor, boolean withModalTrigger) {
//		List<Temporal> lowerBounds = reduceBounds(temporal.getLowerBounds(), (a,b) -> a.after(b));
//		List<Temporal> upperBounds = reduceBounds(temporal.getUpperBounds(), (a,b) -> a.before(b));
//		if (lowerBounds.isEmpty() && upperBounds.isEmpty()) {
//			return "";
//		}
//			
//		Function<Temporal, String> temporalToString = t -> String.valueOf(temporalValue(member, t, viewGenerator, progressMonitor, withModalTrigger));
//			
//		String lowerBoundsList = String.join(" or ", lowerBounds.stream().map(temporalToString).collect(Collectors.toList()));
//		String upperBoundsList = String.join(" or ", upperBounds.stream().map(temporalToString).collect(Collectors.toList()));
//		if (lowerBounds.isEmpty()) {
//			return " before " + upperBoundsList;
//		} 
//		
//		if (upperBounds.isEmpty()) {
//			return " after " + lowerBoundsList;
//		}
//		
//		return " between " + lowerBoundsList + " and " + upperBoundsList;
//	}
//	
//	/**
//	 * Retains only the latest bounds.
//	 * @param bounds
//	 * @param comparator Returns true if the first argument supercedes the second, false if the second argument supercedes the first and null if unknown.
//	 * @return
//	 */
//	private static List<Temporal> reduceBounds(List<Temporal> bounds, BiFunction<Temporal, Temporal, Boolean> comparator) {
//		List<Temporal> ret = new ArrayList<>();
//		for (Temporal bound: bounds) {
//			boolean superceded = false;
//			Iterator<Temporal> rit = ret.iterator();			
//			while (rit.hasNext()) {
//				Temporal rBound = rit.next();
//				Boolean result = comparator.apply(bound, rBound);
//				if (result == Boolean.TRUE) {
//					rit.remove();
//				} else if (result != null) {
//					superceded = true;
//				}
//			}
//			
//			if (!superceded) {
//				ret.add(bound);
//			}
//		}
//		return ret;
//	}	
	
}
