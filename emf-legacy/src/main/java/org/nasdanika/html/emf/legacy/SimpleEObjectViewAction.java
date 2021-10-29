package org.nasdanika.html.emf.legacy;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.DateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.common.Context;
import org.nasdanika.common.ContextSupplier;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertyComputer;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.SourceResolver;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.OrderedListType;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.SectionStyle;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.ActionImpl;
import org.nasdanika.html.app.impl.LabelImpl;
import org.nasdanika.html.app.impl.PathNavigationActionActivator;
import org.nasdanika.html.app.impl.ViewGeneratorImpl;
import org.nasdanika.html.app.viewparts.ListOfActionsViewPart;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.RowContainer.Row.Cell;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.ncore.util.NcoreUtil;

import com.ibm.icu.util.Calendar;

/**
 * Base class for ViewAction adapters which do not extend {@link EObjectSingleValuePropertySource} like {@link EObjectViewAction}.
 * @author Pavel
 * @since 2015.4.3
 * @param <T>
 */
public abstract class SimpleEObjectViewAction<T extends EObject> implements ViewAction<T> {
	
	public static final String DOC_URI = "doc-uri";
	public static final String PROPERTIES_TABLE_ACTION = "properties-table";
	public static final String DIAGNOSTIC_SUMMARY_ACTION = "diagnostic-summary";
	public static final String CONTENT_ROLE = "content";
	public static final String HEAD_ROLE = "head";

	/**
	 * Member role determines where a {@link EStructuralFeature} or {@link EOperation} is displayed in the UI.
	 * Member can be in zero or more roles. E.g. in ELEMENT_CHILD role to show elements in the 
	 * navigation panel and FEATURE_CHILD to show a summary as a section or CONTENT to show it in the content.
	 * @author Pavel
	 *
	 */
	public enum MemberRole {
		
		/**
		 * Member is shown in the properties table.
		 */
		PROPERTY,
		
		/**
		 * Member is shown in the head - above the description. 
		 */
		HEAD,
		
		/**
		 * Feature is shown in the content. 
		 */
		CONTENT,
		
		/**
		 * Child action for each membmer element
		 */
		ELEMENTS,
		
		/**
		 * Child actions for feature member sorted by text
		 */
		ELEMENTS_SORTED,
		
		/**
		 * Child actions for the member, e.g. a section with action list
		 */
		ACTIONS,
				
		/**
		 * No role.
		 */
		NONE;
		
		public final String LITERAL;
		
		private MemberRole() {
			this.LITERAL = name().toLowerCase().replace('_', '-');
		}
		
	}
	
	private T semanticElement;
		
	public SimpleEObjectViewAction(T semanticElement) {
		this.semanticElement = semanticElement;		
	}

	/**
	 * @param resource
	 * @return Path for {@link Resource}. 
	 */
	protected abstract String resolveResourcePath(Resource resource);
	
	/**
	 * @return Path for the target.
	 */
	protected abstract String getTargetPath();
	
	protected abstract String getTargetDescription();

	/**
	 * @return Diagnostics for the semantic element. This implementation returns an empty list.
	 */
	protected Collection<Diagnostic> getDiagnostic() {
		return Collections.emptyList();
	}
	
	/**
	 * Override to output diagnostic summary, e.g. a table or a list.
	 * @param viewGenerator
	 * @param progressMonitor
	 * @return
	 */
	protected Object diagnosticSummary(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		return null;
	}	
	
	/**
	 * @return Diagnostics for a {@link EStructuralFeature} of the semantic element. 
	 * This implementation returns an empty list. 
	 */
	protected Collection<Diagnostic> getFeatureDiagnostic(EStructuralFeature feature) {
		return Collections.emptyList();
	}
		
	/**
	 * TODO - extract into an adapter on its own.
	 */
	@Override
	public ActionActivator getActivator() {
		return EObjectAdaptable.adaptTo(getSemanticElement(), NavigationActionActivator.class);
	}
	
	/**
	 * For non-section actions which are contained in section actions (e.g. context actions) 
	 * computes section path to use a a path prefix.
	 * @param action
	 * @return Empty string if there are no sections, or a path ending with /
	 */
	public static String sectionPath(Action action) {
		if (action != null && action.isInRole(Action.Role.SECTION)) {
			StringBuilder path = new StringBuilder(sectionPath(action.getParent()));
			if (action instanceof ViewAction) {
				EObject actionSemanticElement = ((ViewAction<?>) action).getSemanticElement();
				EReference eContainmentReference = actionSemanticElement.eContainmentFeature();
				if (eContainmentReference != null) {
					path.append(Util.camelToKebab(eContainmentReference.getName())).append("/");
					if (eContainmentReference.isMany()) {
						EObject eContainer = actionSemanticElement.eContainer();
						if (eContainer != null) {
							int index = ((List<?>) actionSemanticElement.eContainer().eGet(eContainmentReference)).indexOf(actionSemanticElement);
							path.append(index);
							path.append("/");			
						}
					}	
					if (action instanceof EStructuralFeatureViewAction) {
						path.append(((EStructuralFeatureViewAction<?, ?>) action).getETypedElement().getName()).append("/");
						if (action instanceof EStructuralFeatureElementViewAction) {
							path.append(((EStructuralFeatureElementViewAction<?, ?, ?>) action).getElementIndex()).append("/");
						}
					}
					return path.toString();
				}
			}			
			return path.append(action.getId()).append("/").toString();
		}
		return "";
	}
		
	/**
	 * Override to return false if generated content should not be cached.
	 * @return
	 */
	protected boolean isCacheContent() {
		return true;
	}
	
	protected Optional<Object> content;
	protected List<Object> headContributions = new ArrayList<>();

	/**
	 * Delegates to doGenerate to perform content generation. Caches if isCacheContent() returns true.
	 */
	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		if (isCacheContent() && content != null) {
			Consumer<Object> headContentConsumer = viewGenerator.getHeadContentConsumer();
			if (headContentConsumer != null) {
				headContributions.forEach(headContentConsumer);
			}			
			return content.isPresent() ? content.get() : null;
		}
		if (!isCacheContent()) {
			return doGenerate(viewGenerator, progressMonitor);
		}
		Fragment contentValue = viewGenerator.getHTMLFactory().fragment();
		ViewGeneratorImpl vg = new ViewGeneratorImpl(viewGenerator, headContributions::add, contentValue::content);
		contentValue.content(doGenerate(vg, progressMonitor));
		content = contentValue == null ? Optional.empty() : Optional.of(contentValue);
		Consumer<Object> headContentConsumer = viewGenerator.getHeadContentConsumer();
		if (headContentConsumer != null) {
			headContributions.forEach(headContentConsumer);
		}
		return contentValue;
	}
	
	/**
	 * Override to generate content before description and member sections.
	 * @param viewGenerator
	 * @param progressMonitor
	 * @return
	 */
	protected Object generateHead(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		Fragment ret = viewGenerator.getHTMLFactory().fragment();
		
		for (Action action: getChildren()) {
			if (action.isInRole(HEAD_ROLE)) {
				ret.content(action.generate(viewGenerator, progressMonitor));
			}
		}						

		return ret;
	}
	
	/**
	 * UI actions
	 * @return collection containing properties table and diagnostic summary actions.
	 */
	protected Collection<Action> getActions() {
		Collection<Action> actions = new ArrayList<>();
		
		ActionImpl propertiesTableAction = createPropertiesTableAction();
		if (propertiesTableAction != null) {
			actions.add(propertiesTableAction);
		}
		
		ActionImpl diagnosticSummaryAction = createDiagnosticSummaryAction();
		if (diagnosticSummaryAction != null) {
			actions.add(diagnosticSummaryAction);
		}
		
		return actions;
	}
	
	protected Object doGenerate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
		Fragment ret = bootstrapFactory.getHTMLFactory().fragment();
		for (Diagnostic diagnostic: getDiagnostic()) {
			ret.content(bootstrapFactory.alert(HtmlEmfUtil.getSeverityColor(diagnostic.getSeverity()), StringEscapeUtils.escapeHtml4(diagnostic.getMessage())));
		}
		Object head = generateHead(viewGenerator, progressMonitor);
		if (head != null) {
			ret.content(head);
		}
		
		String description = getTargetDescription();
		if (!Util.isBlank(description)) {
			ret.content(description);
		}
		
		for (Action action: getChildren()) {
			if (action.isInRole(CONTENT_ROLE)) {
				ret.content(action.generate(viewGenerator, progressMonitor));
			}
		}						
		
		return ret;
	}	
	
	/**
	 * Returns features and operations. This implementation returns all features not sorted.
	 * Subclasses may filter and sort.
	 * @return
	 */
	protected List<ETypedElement> getMembers() {
		EClass semanticEClass = getSemanticElement().eClass();
		List<ETypedElement> ret = new ArrayList<>(semanticEClass.getEAllStructuralFeatures());
		ret.addAll(semanticEClass.getEAllOperations());
		return ret;
	}

	/**
	 * Provides arguments for {@link EOperation}.
	 * This implementation returns an empty list for no-arg operations and throws {@link UnsupportedOperationException} otherwise.
	 * @param member
	 * @return
	 */
	protected EList<?> bind(EOperation member) {
		if (member.getEParameters().isEmpty()) {
			return ECollections.emptyEList();
		}
		throw new UnsupportedOperationException("Override to supply arguments for " + member);
	}
	
	protected List<Action> children;

	/**
	 * Call collectChildren() if children is null. Otherwise returns previously collected children.
	 * @return child actions. 
	 */
	@Override
	public List<Action> getChildren() {
		if (children == null) {
			children = collectChildren();
		}
		return children;
	}

	protected List<Action> collectChildren() {
		ArrayList<Action> children = new ArrayList<>();
		
		Collection<Action> actions = getActions();
		if (actions != null) {
			children.addAll(actions);
		}
		
		for (ETypedElement member: getMembers()) {
			if (isMemberInRole(member, MemberRole.ELEMENTS)) {				
				Collection<Action> childrenActions = childrenActions(member);
				if (childrenActions != null) {
					children.addAll(childrenActions);
				}
			} else if (isMemberInRole(member, MemberRole.ELEMENTS_SORTED)) {				
				Collection<Action> childrenActions = childrenActionsSorted(member);
				if (childrenActions != null) {
					children.addAll(childrenActions);
				}
			}
			if (isMemberInRole(member, MemberRole.ACTIONS)) {				
				Collection<Action> featureActions = memberActions(member);
				if (featureActions != null) {
					featureActions.stream().filter(Objects::nonNull).forEach(children::add);
				}
			}
		}
		return children;
	}
	
	protected ActionImpl createDiagnosticSummaryAction() {
		ActionImpl diagnosticSummaryAction = new ActionImpl() {
			
			@Override
			public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
				return diagnosticSummary(viewGenerator, progressMonitor);
			}
			
			@Override
			public String getText() {
				return actionText(DIAGNOSTIC_SUMMARY_ACTION, "Diagnostic");
			}
			
			@Override
			public String getIcon() {
				return actionIcon(DIAGNOSTIC_SUMMARY_ACTION, null);
			}
			
			@Override
			public boolean isInRole(String role) {
				return isActionInRole(DIAGNOSTIC_SUMMARY_ACTION, role, HEAD_ROLE);
			}
			
			@Override
			public boolean isEmpty() {
				return isInRole(CONTENT_ROLE) 
						|| isInRole(Action.Role.SECTION)
						|| isInRole(HEAD_ROLE);
			}
			
		};
		
		ActionActivator activator = getActivator();
		if (activator instanceof NavigationActionActivator) {
			if (diagnosticSummaryAction.isInRole(Action.Role.SECTION) || diagnosticSummaryAction.isInRole(CONTENT_ROLE) || diagnosticSummaryAction.isInRole(HEAD_ROLE)) {
				diagnosticSummaryAction.setActivator(new PathNavigationActionActivator(diagnosticSummaryAction, ((NavigationActionActivator) activator).getUrl(null), "#actions-" + DIAGNOSTIC_SUMMARY_ACTION, getMarker()));
			} else {
				diagnosticSummaryAction.setActivator(new PathNavigationActionActivator(diagnosticSummaryAction, ((NavigationActionActivator) activator).getUrl(null), "actions/" + DIAGNOSTIC_SUMMARY_ACTION + ".html", getMarker()));
			}
		}
		
		return diagnosticSummaryAction;
	}
	
	protected ActionImpl createPropertiesTableAction() {
		ActionImpl propertiesTableAction = new ActionImpl() {
			
			@Override
			public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
				return propertiesTable(viewGenerator, progressMonitor);
			}
			
			@Override
			public String getText() {
				return actionText(PROPERTIES_TABLE_ACTION, "Properties");
			}
			
			@Override
			public String getIcon() {
				return actionIcon(PROPERTIES_TABLE_ACTION, null);
			}
			
			@Override
			public boolean isInRole(String role) {
				return isActionInRole(PROPERTIES_TABLE_ACTION, role, HEAD_ROLE);
			}
			
			@Override
			public boolean isEmpty() {
				return isInRole(CONTENT_ROLE) 
						|| isInRole(Action.Role.SECTION)
						|| isInRole(HEAD_ROLE);
			}
			
		};
		
		ActionActivator activator = getActivator();
		if (activator instanceof NavigationActionActivator) {
			if (propertiesTableAction.isInRole(Action.Role.SECTION) || propertiesTableAction.isInRole(HEAD_ROLE) || propertiesTableAction.isInRole(CONTENT_ROLE)) {
				propertiesTableAction.setActivator(new PathNavigationActionActivator(propertiesTableAction, ((NavigationActionActivator) activator).getUrl(null), "#actions-" + PROPERTIES_TABLE_ACTION, getMarker()));
			} else {
				propertiesTableAction.setActivator(new PathNavigationActionActivator(propertiesTableAction, ((NavigationActionActivator) activator).getUrl(null), "actions/" + PROPERTIES_TABLE_ACTION + ".html", getMarker()));
			}
		}
		
		return propertiesTableAction;
	}	
	
	@SuppressWarnings("unchecked")
	protected Collection<Action> childrenActions(ETypedElement member) {
		if (member instanceof EReference) {
			return adaptMemberElementsToViewActionsNonNull(member, referenceValue((EReference) member));
		}
		if (member instanceof EOperation) {
			try {
				Object result = getSemanticElement().eInvoke((EOperation) member, bind((EOperation) member));
				if (result == null) {
					return Collections.emptyList();
				}
				if (result instanceof EObject) {
					return adaptMemberElementsToViewActionsNonNull(member, Collections.singleton((EObject) result)); 
				}
				if (result instanceof Collection) {
					return adaptMemberElementsToViewActionsNonNull(member, (Collection<EObject>) result); 					
				}
			} catch (InvocationTargetException e) {
				throw new ConfigurationException("Error invoking " + member + ": " + e.getMessage(), e, getMarker());
			}			
		}
		throw new UnsupportedOperationException("Override to implement not covered cases");
	}

	protected Collection<Action> childrenActionsSorted(ETypedElement member) {
		Collection<Action> childrenActions = childrenActions(member);
		if (childrenActions == null) {
			return childrenActions;
		}
		List<Action> ret = new ArrayList<>(childrenActions);
		ret.sort((a,b) -> a.getText().compareTo(b.getText()));
		return ret;
	}

	/**
	 * @param reference
	 * @return A collection of reference elements. Override to filter.
	 */
	@SuppressWarnings({ "unchecked"})
	protected Collection<EObject> referenceValue(EReference reference) {
		if (reference.isMany()) {
			return (Collection<EObject>) getSemanticElement().eGet(reference);
		}
		return Collections.singleton((EObject) getSemanticElement().eGet(reference));
	}	
	
	/**
	 * @param feature
	 * @return A collection of {@link Action} wrapping the feature. This implementation returns a singleton of list of actions section. May return null instead of an empty collection
	 * The returned collection may contain null elements - they will be filtered out. 
	 */
	protected Collection<Action> memberActions(ETypedElement member) {
		String memberTypeQualifier = Util.camelToKebab(member.eClass().getName());
		if (member instanceof EStructuralFeature) {
			EStructuralFeature feature = (EStructuralFeature) member;
			if (feature.isDerived() || getSemanticElement().eIsSet(feature)) {
				Object featureValue = getSemanticElement().eGet(feature);
				if (featureValue == null) {
					return Collections.emptyList();
				}
				if (featureValue instanceof Collection && ((Collection<?>) featureValue).isEmpty()) {
					return Collections.emptyList();
				}			
				
				EStructuralFeatureViewActionImpl<T, EStructuralFeature, SimpleEObjectViewAction<T>> featureAction = new EStructuralFeatureViewActionImpl<T, EStructuralFeature, SimpleEObjectViewAction<T>>(this, feature) {
					
					@Override
					public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
						if (feature instanceof EReference) {
							@SuppressWarnings("unchecked")
							Object listOfActions = listOfMemberElementsViewActionsSorted(member, featureValue instanceof Collection ? (Collection<EObject>) featureValue : Collections.singleton((EObject) featureValue), null, false, true, 1);
							return viewGenerator.processViewPart(listOfActions, progressMonitor);
						}
						
						return memberValue(feature, featureValue, viewGenerator, progressMonitor);
					}
					
					@Override
					public boolean isInRole(String role) {
						return SimpleEObjectViewAction.this.isMemberActionInRole(member, role);
					}
					
					@Override
					public boolean isEmpty() {
						return false;
					}
					
				};
				
				featureAction.setSectionStyle(SectionStyle.DEFAULT);
				if (featureAction.isInRole(Action.Role.SECTION)) {
					featureAction.setActivator(new PathNavigationActionActivator(featureAction, ((NavigationActionActivator) getActivator()).getUrl(null), "#" + memberTypeQualifier + "-" + member.getName(), getMarker()));
				} else {
					featureAction.setActivator(new PathNavigationActionActivator(featureAction, ((NavigationActionActivator) getActivator()).getUrl(null), memberTypeQualifier + "/" + member.getName() + ".html", getMarker()));
				}				
		
				return Collections.singleton(featureAction);
			}
			
			return Collections.emptyList();
		}
		
		EOperationViewActionImpl<T, SimpleEObjectViewAction<T>> operationAction = new EOperationViewActionImpl<T, SimpleEObjectViewAction<T>>(this, (EOperation) member) {
			
			@Override
			public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
				try {
					Object result = getSemanticElement().eInvoke((EOperation) member, bind((EOperation) member));
					return memberValue(member, result, viewGenerator, progressMonitor);
				} catch (InvocationTargetException e) {
					throw new ConfigurationException("Error invoking " + member + ": " + e.getMessage(), e, getMarker());
				}			
			}
			
			@Override
			public boolean isInRole(String role) {
				return SimpleEObjectViewAction.this.isMemberActionInRole(member, role);
			}
			
			@Override
			public boolean isEmpty() {
				return false;
			}
			
		};
		
		operationAction.setSectionStyle(SectionStyle.DEFAULT);

		if (operationAction.isInRole(Action.Role.SECTION)) {			
			operationAction.setActivator(new PathNavigationActionActivator(operationAction, ((NavigationActionActivator) getActivator()).getUrl(null), "#" + memberTypeQualifier + "-" + member.getName(), getMarker()));
		} else {			
			operationAction.setActivator(new PathNavigationActionActivator(operationAction, ((NavigationActionActivator) getActivator()).getUrl(null), memberTypeQualifier + "/" + member.getName() + ".html", getMarker()));
		}				

		return Collections.singleton(operationAction);
		
	}
	
	protected boolean isMemberActionInRole(ETypedElement member, String role) {
		return Action.Role.SECTION.equals(role);
	}
	
	/**
	 * Interpolation.
	 */
	@Override
	public String memberDescription(ETypedElement member) {
		String classSegment = Util.camelToKebab(member instanceof EStructuralFeature ? ((EStructuralFeature) member).getEContainingClass().getName() : ((EOperation) member).getEContainingClass().getName()); // Just operation name, override to add support of signatures.  
		String memberSegment = Util.camelToKebab(member.getName());
		URL descriptionResource = getClass().getResource(classSegment + (member instanceof EStructuralFeature ? "--" : "---") + memberSegment + ".md");
		if (descriptionResource == null) {
			return null;
		}
		try {
			String descriptionString = DefaultConverter.INSTANCE.toString(descriptionResource.openStream());
			return getContext().get(MarkdownHelper.class, MarkdownHelper.INSTANCE).markdownToHtml(getContext().interpolateToString(descriptionString));
		} catch (Exception e) {
			return "Exception rendering description: " + e;
		}
	}
	
	protected boolean isMemberInRole(ETypedElement member, MemberRole role) {
		if (member instanceof EAttribute) {
			return role == MemberRole.PROPERTY;
		} else if (member instanceof EReference) {
			EReference ref = (EReference) member;
			
			if (ref.isMany()) {
				if (ref.isContainment()) {
					return role == MemberRole.ELEMENTS_SORTED;
				}
				return role == MemberRole.ACTIONS;
			}
			
			return role == MemberRole.PROPERTY;
		}
		
		return false; // EOperations are not invoked by default - shall be explicitly specified.
	}	
		
	@Override
	public boolean isDisabled() {
		return false;
	}

	@Override
	public String getConfirmation() {
		return null;
	}

	@Override
	public boolean isInRole(String role) {
		return Action.Role.NAVIGATION.equals(role);
	}

	@Override
	public Action getParent() {
		EObject eContainer = getSemanticElement().eContainer();
		if (eContainer != null) {
			return EObjectAdaptable.adaptTo(eContainer, ViewAction.class);
		}
		return null;
	}

	@Override
	public String getIcon() {
		return NcoreUtil.getNasdanikaAnnotationDetail(getSemanticElement().eClass(), EmfUtil.ICON_KEY);
	}

	@Override
	public String getText() {
		return getSemanticElement().toString();
	}

	@Override
	public String getTooltip() {
		String description = getTargetDescription();
		if (!Util.isBlank(description)) {
			return Util.firstPlainTextSentence(description, 50, 250);
		}
		
		return null;
	}

	@Override
	public Color getColor() {
		return null;
	}

	@Override
	public boolean isOutline() {
		return false;
	}

	@Override
	public String getDescription() {
		URL descriptionResource = getClass().getResource(Util.camelToKebab(getSemanticElement().eClass().getName())+".md");
		if (descriptionResource == null) {
			return null;
		}
		try {
			String descriptionString = DefaultConverter.INSTANCE.toString(descriptionResource.openStream());
			return getContext().get(MarkdownHelper.class, MarkdownHelper.INSTANCE).markdownToHtml(getContext().interpolateToString(descriptionString));
		} catch (Exception e) {
			return "Exception rendering description: " + e;
		}
	}
	
	protected Table	propertiesTable(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.getBootstrapFactory();
		Table pTable = bootstrapFactory.table().bordered(); 
		pTable.toHTMLElement().style().width("auto") ;
		
		Marker marker = getMarker();
		if (marker != null) {
			Row locationRow = pTable.row(); 
			locationRow.header("Location");
			locationRow.cell(location(marker, viewGenerator, progressMonitor));			
		}
		
		for (ETypedElement member: getMembers()) {	
			if (isMemberInRole(member, MemberRole.PROPERTY)) {
				Collection<Diagnostic> featureDiagnostic = member instanceof EStructuralFeature ? getFeatureDiagnostic((EStructuralFeature) member) : Collections.emptyList();
				Object mv;
				if (member instanceof EStructuralFeature) {
					mv = getSemanticElement().eGet((EStructuralFeature) member);
				} else {
					try {
						mv = getSemanticElement().eInvoke((EOperation) member, bind((EOperation) member));
					} catch (InvocationTargetException e) {
						throw new ConfigurationException("Error invoking " + member + ": " + e.getMessage(), e, getMarker());
					}			
				}
				if (featureDiagnostic.isEmpty() && isEmptyMemberValue(member, mv)) {
					continue;
				}
				Object memberValue = memberValue(member, mv, viewGenerator, progressMonitor);
				if (memberValue != null || !featureDiagnostic.isEmpty()) {
					Row fRow = pTable.row(); 
					LabelImpl fl = memberLabel(member);
					Cell nameHeader = fRow.header(viewGenerator.label(fl));
					nameHeader.toHTMLElement().content(org.nasdanika.html.app.impl.Util.descriptionModal(viewGenerator, fl));
					
					Cell valueCell = memberValue == null ? fRow.cell() : fRow.cell(memberValue);
					
					for (Diagnostic diagnostic: featureDiagnostic) {
						valueCell.toHTMLElement().content(bootstrapFactory.alert(HtmlEmfUtil.getSeverityColor(diagnostic.getSeverity()), StringEscapeUtils.escapeHtml4(diagnostic.getMessage())));
					}
				}
			}
		}		
		
		return pTable;
	}	
	
	/**
	 * Empty member values are not shown in property tables unless there is a diagnostic to show.
	 * This method returns true if value is null, an empty string, false for booleans, or zero for numbers.
	 * @param member
	 * @param value
	 * @return
	 */
	protected boolean isEmptyMemberValue(ETypedElement member, Object value) {
		if (value == null) {
			return true;
		}
		if (value instanceof String) {
			return Util.isBlank((String) value);		
		}
		if (value instanceof Number) {
			return ((Number) value).equals(0);
		}
		return Boolean.FALSE.equals(value);
	}
	
	protected Object location(Marker marker, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		Context context = getContext();
		if (context == null) {
			return marker;
		}
		
		SourceResolver sourceResolver = context.get(SourceResolver.class);
		if (sourceResolver == null) {
			return marker;
		}
		
		SourceResolver.Link source = sourceResolver.getSource(marker);
		if (source == null) {
			return marker;
		}
				
		if (Util.isBlank(source.getLocation())) {
			return source.getText();
		}
		
		return viewGenerator.getHTMLFactory().link(source.getLocation(), source.getText());
	}
	
	protected Object memberValue(ETypedElement member, Object value, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {		
		if (value instanceof Instant) {
			return memberValue(member, new Date(((Instant) value).toEpochMilli()), viewGenerator, progressMonitor);
		}
		
		if (value instanceof Date) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime((Date) value);
			String datePart = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.getDefault()).format((Date) value);
			if (calendar.get(Calendar.HOUR_OF_DAY) == 0 && calendar.get(Calendar.MINUTE) == 0 && calendar.get(Calendar.SECOND) == 0) {
				return datePart;
			}
			String timePart = DateFormat.getTimeInstance().format((Date) value);
			return datePart + " " + timePart;
			
		}
		
		if (value instanceof EObject) {
			Action va = EObjectAdaptable.adaptTo((EObject) value, ViewAction.class);
			if (va != null) {
				return viewGenerator.link(va);
			}
		} else if (value instanceof Collection) {
			Collection<?> vc = (Collection<?>) value;
			if (vc.isEmpty()) {
				return null;
			}
			if (vc.size() == 1) {
				return memberValue(member, vc.iterator().next(), viewGenerator, progressMonitor);
			}			
			HTMLFactory htmlFactory = viewGenerator.getHTMLFactory();
			Tag ret = htmlFactory.tag(TagName.ol);
			for (Object e: (Iterable<?>) value) {
				ret.content(htmlFactory.tag(TagName.li, memberValue(member, e, viewGenerator, progressMonitor)));
			}
			return ret;
		}
		return value;
	}

	@Override
	public String getNotification() {
		return null;
	}

	@Override
	public Label getCategory() {
		EReference cf = getSemanticElement().eContainmentFeature();
		if (cf == null) {
			return null;
		}
		
		EObject ec = getSemanticElement().eContainer();
		
		// not exactly valid approach - should use isChildFeature of the container view action, but need to "peel" proxy for that.
		if (ec == null || ec.eClass().getEAllReferences().stream().filter(r -> isMemberInRole(r, MemberRole.ELEMENTS) || isMemberInRole(r, MemberRole.ELEMENTS_SORTED)).count() == 1) {
			return null;
		}		
		Action parent = getParent();
		if (parent == null) {
			return null;
		}
		if (parent instanceof ViewAction) {
			return ((ViewAction<?>) parent).memberCategory(cf);
		}
		
		LabelImpl category = memberLabel(cf);
		if (category == null) {
			return null;
		}
		category.setId(parent.getId() + "-feature-category-" + cf.getName());
		return category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getSemanticElement() == null) ? 0 : getSemanticElement().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;		
		SimpleEObjectViewAction<?> other = (SimpleEObjectViewAction<?>) obj;
		return getSemanticElement() == other.getSemanticElement();
	}
	
	@Override
	public String toString() {
		return super.toString() + " -> " + getSemanticElement();
	}
	
	protected Marker getMarker() {
		Marked marked = EObjectAdaptable.adaptTo(getSemanticElement(), Marked.class);
		return marked == null ? null : marked.getMarker();
	}
	
	@Override
	public SectionStyle getSectionStyle() {
		return getSectionChildren().size() > 1 ? SectionStyle.TAB : SectionStyle.DEFAULT;
	}

	@Override
	public T getSemanticElement() {
		return semanticElement;
	}

	protected List<Action> adaptMemberElementsToViewActionsNonNull(ETypedElement member, Collection<? extends EObject> elements) {
		return elements.stream().map(ViewAction::adaptToViewActionNonNull).collect(Collectors.toList());
	}

	protected List<Action> adaptMemberElementsToViewActionsNonNullSorted(ETypedElement member, Collection<? extends EObject> elements) {
		return elements.stream().map(ViewAction::adaptToViewActionNonNull).sorted((a,b) -> a.getText().compareTo(b.getText())).collect(Collectors.toList());
	}

	protected Object listOfMemberElementsViewActions(ETypedElement member, Collection<? extends EObject> elements, Object header, boolean sort, boolean tooltip, int depth) { 
		if (elements.isEmpty()) {
			return null;
		}
		return new ListOfActionsViewPart(adaptMemberElementsToViewActionsNonNull(member, elements), header, tooltip, depth, OrderedListType.ROTATE);
	}

	protected Object listOfMemberElementsViewActionsSorted(ETypedElement member, Collection<? extends EObject> elements, Object header, boolean sort, boolean tooltip, int depth) { 
		if (elements.isEmpty()) {
			return null;
		}
		return new ListOfActionsViewPart(adaptMemberElementsToViewActionsNonNullSorted(member, elements), header, tooltip, depth, OrderedListType.ROTATE);
	}
	
	// --- Action customization
	
	/**
	 * Allows to customize UI actions roles, e.g. properties-table and diagnostic-summary.
	 * @param actionName
	 * @param role
	 * @return
	 */
	protected boolean isActionInRole(String action, String role, String... defaultRoles) {
		for (String dr: defaultRoles) {
			if (dr != null && dr.equals(role)) {
				return true;
			}
		}
		return false;
	}
	
	protected String actionIcon(String action, String defaultValue) {
		return defaultValue;
	}

	protected String actionText(String action, String defaultValue) {
		return defaultValue;
	}
		
}
