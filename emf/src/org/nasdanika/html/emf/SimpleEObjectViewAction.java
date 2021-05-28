package org.nasdanika.html.emf;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertyComputer;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.SourceResolver;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.SectionStyle;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.LabelImpl;
import org.nasdanika.html.app.impl.PathNavigationActionActivator;
import org.nasdanika.html.app.impl.ViewGeneratorImpl;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.RowContainer.Row.Cell;
import org.nasdanika.html.bootstrap.Table;

/**
 * Base class for ViewAction adapters which do not extend {@link EObjectSingleValuePropertySource} like {@link EObjectViewAction}.
 * @author Pavel
 * @since 2015.4.3
 * @param <T>
 */
public abstract class SimpleEObjectViewAction<T extends EObject> implements ViewAction<T> {
	
	/**
	 * Feature role determines where a feature is displayed in the UI.
	 * Feature can be in zero or more roles. E.g. in ELEMENT_CHILD role to show elements in the 
	 * navigation panel and FEATURE_CHILD to show a summary as a section or CONTENT to show it in the content.
	 * @author Pavel
	 *
	 */
	protected enum FeatureRole {
		
		/**
		 * Feature is shown in the properties table.
		 */
		PROPERTY,
		
		/**
		 * Feature is shown in the content. 
		 */
		CONTENT,
		
		/**
		 * Child action for each feature element
		 */
		ELEMENT_ACTIONS,
		
		/**
		 * Child actions for feature elements sorted by text
		 */
		ELEMENT_ACTIONS_SORTED,
		
		/**
		 * Child actions for the feature, e.g. a section with action list
		 */
		FEATURE_ACTIONS
		
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
		
	@Override
	public ActionActivator getActivator() {
		String contextUri = (String) EObjectAdaptable.adaptTo(getSemanticElement(), Context.class).get(Context.BASE_URI_PROPERTY);
		Marked marked = EObjectAdaptable.adaptTo(getSemanticElement(), Marked.class);
		StringBuilder path = new StringBuilder();
		EReference eContainmentReference = getSemanticElement().eContainmentFeature();
		if (eContainmentReference == null) {
			path.append(contextUri);
			String resourcePath = resolveResourcePath(getSemanticElement().eResource());			
			if (!Util.isBlank(resourcePath)) {
				path.append(resourcePath);
			}
			EList<EObject> resourceContents = getSemanticElement().eResource().getContents();
			if (resourceContents.size() > 1) {
				String localPath = getTargetPath();
				if (Util.isBlank(localPath) && getSemanticElement().eResource() != null) {
					path.append(resourceContents.indexOf(getSemanticElement()));
				} else {
					path.append(localPath);
				}
			} else if (path.toString().endsWith("/")) {
				path.replace(path.length()-1, path.length(), "");
			}
		} else {
			path.append(Util.camelToKebab(eContainmentReference.getName()));
			if (eContainmentReference.isMany()) {
				path.append("/");			
				String localPath = getTargetPath();
				if (Util.isBlank(localPath)) {
					EObject eContainer = getSemanticElement().eContainer();
					if (eContainer != null) {
						path.append(getDefaultPath());
					}
				} else {
					path.append(localPath);
				}
			}	
		}		
		if (getSemanticElement().eClass().getEReferences().stream().filter(EReference::isContainment).findAny().isPresent()) {
			path.append("/index");
		}
		path.append(".html");				
		return new PathNavigationActionActivator(this, contextUri, path.toString(), marked == null ? null : marked.getMarker());			
	}
	
	protected String getDefaultPath() {
		return String.valueOf(((List<?>) getSemanticElement().eContainer().eGet(getSemanticElement().eContainmentFeature())).indexOf(this));
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
	
	protected Object doGenerate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
		Fragment ret = bootstrapFactory.getHTMLFactory().fragment();
		for (Diagnostic diagnostic: getDiagnostic()) {
			ret.content(bootstrapFactory.alert(HtmlEmfUtil.getSeverityColor(diagnostic.getSeverity()), StringEscapeUtils.escapeHtml4(diagnostic.getMessage())));
		}
		ret.content(propertiesTable(viewGenerator, progressMonitor));
		ret.content(diagnosticSummary(viewGenerator, progressMonitor));
		Object diagram = generateDiagram();
		if (diagram != null) {
			ret.content(diagram);
		}
		
		String description = getTargetDescription();
		if (!Util.isBlank(description)) {
			ret.content(description);
		}
		
		// Contents
		for (EStructuralFeature feature: getFeatures()) {
			if (isFeatureInRole(feature,FeatureRole.CONTENT)) {
				ret.content(featureContent(feature, viewGenerator, progressMonitor));
			}
		}				
		
		return ret;
	}	
	
	/**
	 * Returns features. This implementation returns all features not sorted.
	 * Subclasses may filter and sort.
	 * @return
	 */
	protected List<EStructuralFeature> getFeatures() {
		return getSemanticElement().eClass().getEAllStructuralFeatures();
	}
	
	/**
	 * Generates content from a feature.
	 * @param ref
	 * @param viewGenerator
	 * @param progressMonitor
	 * @return
	 */
	protected Object featureContent(EStructuralFeature feature, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.getBootstrapFactory();
		
		if (feature instanceof EReference) {
			Label fl = featureLabel(feature);
			Object listOfActions = ViewAction.listOfViewActionsSorted(referenceValue(feature), viewGenerator.label(fl), false, true, 1);
			Fragment ret = viewGenerator.getHTMLFactory().fragment(viewGenerator.processViewPart(listOfActions, progressMonitor));
			for (Diagnostic diagnostic: getFeatureDiagnostic(feature)) {
				ret.content(bootstrapFactory.alert(HtmlEmfUtil.getSeverityColor(diagnostic.getSeverity()), StringEscapeUtils.escapeHtml4(diagnostic.getMessage())));
			}			
			return ret;
		}
		
		Card ret = viewGenerator.getBootstrapFactory().card();
		ret.getHeader().toHTMLElement().content(viewGenerator.label(featureLabel(feature)));
		ret.getBody().toHTMLElement().content(featureValue(feature, getSemanticElement().eGet(feature), viewGenerator, progressMonitor));
		for (Diagnostic diagnostic: getFeatureDiagnostic(feature)) {
			ret.getFooter().toHTMLElement().content(bootstrapFactory.alert(HtmlEmfUtil.getSeverityColor(diagnostic.getSeverity()), StringEscapeUtils.escapeHtml4(diagnostic.getMessage())));
		}
	
		return ret.toString();
	}
	
	/**
	 * Override to generate a diagram to be displayed on the top of the page.
	 * @return
	 */
	protected Object generateDiagram() {
		return null;
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
		ArrayList<Action> children = new ArrayList<Action>();
		for (EStructuralFeature feature: getFeatures()) {
			if (isFeatureInRole(feature, FeatureRole.ELEMENT_ACTIONS)) {				
				children.addAll(ViewAction.adaptToViewActionNonNull(referenceValue(feature)));
			} else if (isFeatureInRole(feature, FeatureRole.ELEMENT_ACTIONS_SORTED)) {				
				children.addAll(ViewAction.adaptToViewActionNonNullSorted(referenceValue(feature)));
			}
			if (isFeatureInRole(feature, FeatureRole.FEATURE_ACTIONS)) {				
				Collection<Action> featureActions = featureActions(feature);
				if (featureActions != null) {
					featureActions.stream().filter(Objects::nonNull).forEach(children::add);
				}
			}
		}
		return children;
	}

	/**
	 * @param feature
	 * @return A collection of reference elements. Override to filter.
	 */
	@SuppressWarnings({ "unchecked"})
	protected Collection<EObject> referenceValue(EStructuralFeature feature) {
		return (Collection<EObject>) getSemanticElement().eGet(feature);
	}	
	
	/**
	 * @param feature
	 * @return A collection of {@link Action} wrapping the feature. This implementation returns a singleton of list of actions section. May return null instead of an empty collection
	 * The returned collection may contain null elements - they will be filtered out. 
	 */
	protected Collection<Action> featureActions(EStructuralFeature feature) {
		if (feature.isDerived() || getSemanticElement().eIsSet(feature)) {
			Object featureValue = getSemanticElement().eGet(feature);
			if (featureValue == null) {
				return Collections.emptyList();
			}
			if (featureValue instanceof Collection && ((Collection<?>) featureValue).isEmpty()) {
				return Collections.emptyList();
			}			
			
			EStructuralFeatureViewActionImpl<T, EStructuralFeature> featureSection = new EStructuralFeatureViewActionImpl<T, EStructuralFeature>(getSemanticElement(), feature) {
				
				@Override
				public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
					if (feature instanceof EReference) {
						@SuppressWarnings("unchecked")
						Object listOfActions = ViewAction.listOfViewActionsSorted((Collection<EObject>) featureValue, null, false, true, 1);
						return viewGenerator.processViewPart(listOfActions, progressMonitor);
					}
					
					return featureValue(feature, featureValue, viewGenerator, progressMonitor);
				}
				
			};
			
			featureSection.getRoles().add(Action.Role.SECTION); 
			featureSection.setSectionStyle(SectionStyle.DEFAULT);
			featureSection.setText(featureLabelText(feature)); 		
			featureSection.setIcon(featureIcon(feature));
			featureSection.setDescription(featureDescription(feature));
			featureSection.setActivator(new PathNavigationActionActivator(featureSection, ((NavigationActionActivator) getActivator()).getUrl(null), "#feature-" + feature.getName(), getMarker()));
	
			return Collections.singleton(featureSection);
		}
		return Collections.emptyList();
	}
	
	protected LabelImpl featureLabel(EStructuralFeature feature) {
		LabelImpl ret = new LabelImpl();
		ret.setText(featureLabelText(feature));
		ret.setIcon(featureIcon(feature));
		String featureDescription = featureDescription(feature);
		ret.setDescription(featureDescription);
		if (!Util.isBlank(featureDescription)) {
			ret.setTooltip(Util.firstPlainTextSentence(featureDescription, 50, 250));
		}
		
		return ret;		
	}

	protected String featureLabelText(EStructuralFeature feature) {
		return EmfUtil.getNasdanikaAnnotationDetail(feature, EmfUtil.LABEL_KEY, Util.nameToLabel(feature.getName()));
	}
	
	protected String featureDescription(EStructuralFeature feature) {
		String classSegment = Util.camelToKebab(feature.getEContainingClass().getName());
		String featureSegment = Util.camelToKebab(feature.getName());
		URL descriptionResource = getClass().getResource(classSegment + "--" + featureSegment + ".md");
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
		
	protected boolean isFeatureInRole(EStructuralFeature feature, FeatureRole role) {
		if (feature instanceof EAttribute) {
			return role == FeatureRole.PROPERTY;
		}
		
		EReference ref = (EReference) feature;
		
		if (ref.isMany()) {
			if (ref.isContainment()) {
				return role == FeatureRole.ELEMENT_ACTIONS_SORTED;
			}
			return role == FeatureRole.FEATURE_ACTIONS;
		}
		
		return role == FeatureRole.PROPERTY;
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
		return EmfUtil.getNasdanikaAnnotationDetail(getSemanticElement().eClass(), EmfUtil.ICON_KEY);
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

	/**
	 * @return Action context built from the resource set context plus link resolver and "base-uri" property containing relative URI's of the site root. 
	 */
	protected Context getContext() {
		URI thisUri = URI.createURI(((NavigationActionActivator) getActivator()).getUrl(null));
		Context targetContext = EObjectAdaptable.adaptTo(getSemanticElement(), Context.class);
		URI baseUri = URI.createURI(targetContext.get(Context.BASE_URI_PROPERTY).toString());
		URI relativeBaseUri = baseUri.deresolve(thisUri, true, true, true);
		MutableContext ret = targetContext.fork();
		ret.put(Context.BASE_URI_PROPERTY, relativeBaseUri);
		
		ret.put("link", new PropertyComputer() {
			
			@SuppressWarnings("unchecked")
			@Override
			public <U> U compute(Context context, String key, String path, Class<U> type) {
				URI uri = URI.createURI(path);
				EObject eObj = getSemanticElement().eResource().getResourceSet().getEObject(uri, false);
				if (eObj == null) {
					return null;
				}

				Action action = ViewAction.adaptToViewActionNonNull(eObj);
				return (U) ((NavigationActionActivator) action.getActivator()).getUrl(thisUri.toString());
			}
		});
		
		return ret;
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
		
		for (EStructuralFeature sf: getFeatures()) {			
			if (isFeatureInRole(sf, FeatureRole.PROPERTY)) {
				Collection<Diagnostic> featureDiagnostic = getFeatureDiagnostic(sf);
				Object fv = getSemanticElement().eGet(sf);
				if (featureDiagnostic.isEmpty() && isEmptyFeatureValue(sf, fv)) {
					continue;
				}
				Object featureValue = featureValue(sf, fv, viewGenerator, progressMonitor);
				if (featureValue != null || !featureDiagnostic.isEmpty()) {
					Row fRow = pTable.row(); 
					LabelImpl fl = featureLabel(sf);
					Cell nameHeader = fRow.header(viewGenerator.label(fl));
					nameHeader.toHTMLElement().content(org.nasdanika.html.app.impl.Util.descriptionModal(viewGenerator, fl));
					
					Cell valueCell = featureValue == null ? fRow.cell() : fRow.cell(featureValue);
					
					for (Diagnostic diagnostic: featureDiagnostic) {
						valueCell.toHTMLElement().content(bootstrapFactory.alert(HtmlEmfUtil.getSeverityColor(diagnostic.getSeverity()), StringEscapeUtils.escapeHtml4(diagnostic.getMessage())));
					}
				}
			}
		}		
		
		return pTable;
	}	
	
	/**
	 * Empty feature values are not shown in property tables unless there is a diagnostic to show.
	 * This method returns true if value is null, an empty string, false for booleans, or zero for numbers.
	 * @param feature
	 * @param value
	 * @return
	 */
	protected boolean isEmptyFeatureValue(EStructuralFeature feature, Object value) {
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
	
	protected Object featureValue(EStructuralFeature feature, Object value, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {		
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
				return featureValue(feature, vc.iterator().next(), viewGenerator, progressMonitor);
			}			
			HTMLFactory htmlFactory = viewGenerator.getHTMLFactory();
			Tag ret = htmlFactory.tag(TagName.ol);
			for (Object e: (Iterable<?>) value) {
				ret.content(htmlFactory.tag(TagName.li, featureValue(feature, e, viewGenerator, progressMonitor)));
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
		if (ec == null || ec.eClass().getEAllReferences().stream().filter(r -> isFeatureInRole(r, FeatureRole.ELEMENT_ACTIONS) || isFeatureInRole(r, FeatureRole.ELEMENT_ACTIONS_SORTED)).count() == 1) {
			return null;
		}		
		Action parent = getParent();
		if (parent == null) {
			return null;
		}
		LabelImpl category = featureLabel(cf);
		category.setId(cf.getName());
		category.setId(parent.getId() + "-feature-category-" + cf.getName());
		return category;
	}

	protected String featureIcon(EStructuralFeature feature) {
		return EmfUtil.getNasdanikaAnnotationDetail(feature, EmfUtil.ICON_KEY, EmfUtil.getNasdanikaAnnotationDetail(feature.getEType(), EmfUtil.ICON_KEY));
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
		return SectionStyle.DEFAULT;
	}

	@Override
	public T getSemanticElement() {
		return semanticElement;
	}
	
}
