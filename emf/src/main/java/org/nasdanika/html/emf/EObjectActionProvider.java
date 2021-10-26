package org.nasdanika.html.emf;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.CollectionCompoundConsumer;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.emf.DiagnosticProvider;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Text;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.emf.EObjectActionResolver.Context;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.NavigationPanel;
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

import com.ibm.icu.util.Calendar;

public class EObjectActionProvider<T extends EObject> extends AdapterImpl implements ActionProvider {
	
	public EObjectActionProvider(T target) {
		setTarget(target);
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
				public void execute(Context registry, ProgressMonitor progressMonitor) throws Exception {
					EObjectActionProvider.this.resolve((Action) getTarget(), registry, progressMonitor);					
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
		public void execute(Context context, ProgressMonitor progressMonitor) throws Exception {
			accumulator.execute(context, progressMonitor);		
			((Action) getTarget()).eAdapters().remove(this);
		}

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return "Resolving " + EObjectActionProvider.this.name();
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
	public Action execute(BiConsumer<EObject,Action> registry, ProgressMonitor progressMonitor) throws Exception {
		EObjectActionResolverAdapter resolver = new EObjectActionResolverAdapter();
		Action ret = createAction(registry, resolver::add, progressMonitor);
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
	protected Action createAction(
			BiConsumer<EObject,Action> registry, 
			java.util.function.Consumer<org.nasdanika.common.Consumer<Context>> resolveConsumer, 
			ProgressMonitor progressMonitor) throws Exception {
		Action ret = AppFactory.eINSTANCE.createAction();

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
	
	protected NavigationPanel createLeftNavigation(Context context, ProgressMonitor progressMonitor) throws Exception {
	
		return null;
	}
	
	protected NavigationPanel createRightNavigation(Context context, ProgressMonitor progressMonitor) throws Exception {
	
		return null;
	}
	
	protected NavigationPanel createFloatLeftNavigation(Context context, ProgressMonitor progressMonitor) throws Exception {
	
		return null;
	}
	
	protected NavigationPanel createFloatRightNavigation(Context context, ProgressMonitor progressMonitor) throws Exception {
	
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
		
	protected void resolve(Action action, Context context, ProgressMonitor progressMonitor) throws Exception {
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
	 * @throws Exception
	 */
	protected Table createPropertiesTable(Action action, Context context, ProgressMonitor progressMonitor) throws Exception {
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
			
			boolean isEmptyValue = isEmptyValue(property, propertyValue);
			if (!isEmptyValue || !featureDiagnostic.isEmpty()) {				
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
				if (!isEmptyValue) {
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
	
	protected List<ETypedElement> getProperties() {
		return Collections.emptyList();
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
		// TODO - tooltip, description
	}
	
	protected String typedElementLabelText(ETypedElement type) {
		return EmfUtil.getNasdanikaAnnotationDetail(type, EmfUtil.LABEL_KEY, Util.nameToLabel(type.getName()));
	}
	
	protected String typedElementIcon(ETypedElement member) {
		return EmfUtil.getNasdanikaAnnotationDetail(member, EmfUtil.ICON_KEY, EmfUtil.getNasdanikaAnnotationDetail(member.getEType(), EmfUtil.ICON_KEY));
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
			ProgressMonitor progressMonitor) throws Exception {
		
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
			return ((Number) value).equals(0);
		}
		return Boolean.FALSE.equals(value);
	}
	
	protected Object getTypedElementValue(ETypedElement typedElement) throws Exception {
		return getTypedElementValue(getTarget(), typedElement);
	}
		
	protected Object getTypedElementValue(EObject eObject, ETypedElement typedElement) throws Exception {
		if (typedElement instanceof EStructuralFeature) {
			return eObject.eGet((EStructuralFeature) typedElement);
		}
		
		if (typedElement == EcorePackage.Literals.EOBJECT___ECONTAINER) {
			return eObject.eContainer();
		}
		
		EOperation eOp = (EOperation) typedElement;
		return eObject.eInvoke(eOp, getArguments(eObject, eOp));
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
	 * Renders value.
	 * @param base Context/base action.
	 * @param typedElement Optional typed element for customizing behavior.
	 * @param value Value to render
	 * @param context Context
	 * @param progressMonitor progress monitor.
	 * @return rendered value
	 * @throws Exception
	 */
	protected EObject renderValue(
			Action base, 
			ETypedElement typedElement,
			Object value, 
			Context context, 
			ProgressMonitor progressMonitor) throws Exception {		
		if (value == null) {
			return null;
		}
		if (value instanceof Instant) {
			return renderValue(base, typedElement, new Date(((Instant) value).toEpochMilli()), context, progressMonitor);
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
		
		if (value instanceof EObject) {
			Action valueAction = context.getAction((EObject) value);
			if (valueAction == null) {
				return EcoreUtil.copy((EObject) value); // Returning copy to be handled at later stages, e.g. render markdown during site generation. 
			}
			
			Link link = AppFactory.eINSTANCE.createLink();
			link.setText(valueAction.getText());
			link.setIcon(valueAction.getIcon());
			link.setTooltip(valueAction.getTooltip());
			link.setLocation(context.resolve(valueAction, base).toString());
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
		 * @throws Exception
		 */
		List<EObject> createContent(
				T element,
				Action base, 
				ETypedElement typedElement,
				Context context, 
				ProgressMonitor progressMonitor) throws Exception;		
		
	}
	
	/**
	 * Renders a list.
	 * @param base Context/base action.
	 * @param typedElement Optional typed element for customizing behavior.
	 * @param value Value to render
	 * @param context Context
	 * @param progressMonitor progress monitor.
	 * @return null for null or empty collections or a list.
	 * @throws Exception
	 */
	protected <E> org.nasdanika.html.model.html.Tag renderList(
			Collection<E> elements,
			boolean ordered,
			ContentProvider<E> contentProvider,
			Action base, 
			ETypedElement typedElement,
			Context context, 
			ProgressMonitor progressMonitor) throws Exception {		
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
	 * @throws Exception
	 */
	protected Table createEMapTable(
			ETypedElement typedElement,
			EMap<?,?> eMap, 
			Action action, 
			Context context, 
			ProgressMonitor progressMonitor) throws Exception {
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
		return new ColumnBuilder<EObject>() {
			
			@Override
			public void buildHeader(
					TableCell header, 
					Action base, 
					ETypedElement tElement, 
					Context context,
					ProgressMonitor progressMonitor) throws Exception {
				header.getContent().add(createETypedElementLabel(typedElement, false));				
			}
			
			@Override
			public void buildCell(
					EObject rowElement, 
					TableCell cell, 
					Action base, 
					ETypedElement tElement, 
					Context context,
					ProgressMonitor progressMonitor) throws Exception {
				
				Object value = getTypedElementValue(rowElement, typedElement);
				EObject renderedValue = renderValue(base, typedElement, value, context, progressMonitor);
				if (renderedValue != null) {
					cell.getContent().add(renderedValue);
				}
			}
		};
	}
	
	protected List<ColumnBuilder<EObject>> createColumnBuilders(Collection<ETypedElement> typedElements) {
		return typedElements.stream().map(this::createColumnBuilder).collect(Collectors.toList());
	}
	
	protected List<ColumnBuilder<EObject>> createColumnBuilders(ETypedElement... typedElements) {
		return createColumnBuilders(Arrays.asList(typedElements));
	}

	@SafeVarargs
	public static <T> Table buildTable(
			Collection<T> elements, 
			Action base, 
			ETypedElement typedElement,
			Context context, 
			ProgressMonitor progressMonitor,			
			ColumnBuilder<T>... columnBuilders) throws Exception {
		return buildTable(elements, Arrays.asList(columnBuilders), base, typedElement, context, progressMonitor);
	}
	
	public static <T> Table buildTable(
			Collection<T> elements, 
			Collection<ColumnBuilder<T>> columnBuilders,
			Action base, 
			ETypedElement typedElement,
			Context context, 
			ProgressMonitor progressMonitor) throws Exception {
		Table ret = BootstrapFactory.eINSTANCE.createTable();
		TableHeader header = BootstrapFactory.eINSTANCE.createTableHeader();
		ret.setHeader(header);
		TableRow headerRow = BootstrapFactory.eINSTANCE.createTableRow();
		header.getRows().add(headerRow);
		EList<TableCell> headerRowCells = headerRow.getCells();
		for (ColumnBuilder<T> cb: columnBuilders) {
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
			for (ColumnBuilder<T> cb: columnBuilders) {
				TableCell elementCell = BootstrapFactory.eINSTANCE.createTableCell();
				elementRowCells.add(elementCell);
				cb.buildCell(element, elementCell, base, typedElement, context, progressMonitor);
			}
		}
		return ret;
	}
	
	/**
	 * Creates an action with a table of typed element values.
	 * @param typedElement
	 * @param columnBuilders
	 * @return
	 * @throws Exception 
	 */
	protected Action createTableAction(
			ETypedElement typedElement,
			Collection<ColumnBuilder<EObject>> columnBuilders,
			Action base, 
			Context context, 
			ProgressMonitor progressMonitor) throws Exception {
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
	 * @throws Exception 
	 */
	protected Action createTableAction(
			ETypedElement typedElement,
			Action base, 
			Context context, 
			ProgressMonitor progressMonitor,						
			ETypedElement... columnElements) throws Exception {
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
	
	
}
