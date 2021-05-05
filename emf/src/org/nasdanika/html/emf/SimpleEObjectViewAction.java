package org.nasdanika.html.emf;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
import org.nasdanika.html.app.impl.ActionImpl;
import org.nasdanika.html.app.impl.LabelImpl;
import org.nasdanika.html.app.impl.PathNavigationActionActivator;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.Table;

/**
 * Base class for ViewAction adapters which do not use extend {@link EObjectSingleValuePropertySource} like {@link EObjectViewAction}.
 * @author Pavel
 * @since 2015.4.3
 * @param <T>
 */
public abstract class SimpleEObjectViewAction<T extends EObject> implements ViewAction {
	
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
		 * A child action for the feature, e.g. a section with action list
		 */
		FEATURE_ACTION
		
	}
	
	protected T target;
		
	public SimpleEObjectViewAction(T target) {
		this.target = target;		
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
		
	@Override
	public ActionActivator getActivator() {
		String contextUri = (String) EObjectAdaptable.adaptTo(target, Context.class).get(Context.BASE_URI_PROPERTY);
		Marked marked = EObjectAdaptable.adaptTo(target, Marked.class);
		StringBuilder path = new StringBuilder();
		EReference eContainmentReference = target.eContainmentFeature();
		if (eContainmentReference == null) {
			path.append(contextUri);
			String resourcePath = resolveResourcePath(target.eResource());			
			if (!Util.isBlank(resourcePath)) {
				path.append(resourcePath);
			}
			EList<EObject> resourceContents = target.eResource().getContents();
			if (resourceContents.size() > 1) {
				String localPath = getTargetPath();
				if (Util.isBlank(localPath) && target.eResource() != null) {
					path.append(resourceContents.indexOf(target));
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
					EObject eContainer = target.eContainer();
					if (eContainer != null) {
						path.append(getDefaultPath());
					}
				} else {
					path.append(localPath);
				}
			}	
		}		
		if (target.eClass().getEReferences().stream().filter(EReference::isContainment).findAny().isPresent()) {
			path.append("/index");
		}
		path.append(".html");				
		return new PathNavigationActionActivator(this, contextUri, path.toString(), marked == null ? null : marked.getMarker());			
	}
	
	protected String getDefaultPath() {
		return String.valueOf(((List<?>) target.eContainer().eGet(target.eContainmentFeature())).indexOf(this));
	}
	
	/**
	 * Override to return false if generated content should not be cached.
	 * @return
	 */
	protected boolean isCacheContent() {
		return true;
	}
	
	protected Optional<Object> content;

	/**
	 * Delegates to doGenerate to perform content generation. Caches if isCacheContent() returns true.
	 */
	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		if (isCacheContent() && content != null) {
			return content.isPresent() ? content.get() : null;
		}
		if (!isCacheContent()) {
			return doGenerate(viewGenerator, progressMonitor);
		}
		Object contentValue = doGenerate(viewGenerator, progressMonitor);
		content = contentValue == null ? Optional.empty() : Optional.of(contentValue);
		return contentValue;
	}
	
	protected Object doGenerate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
		Fragment ret = bootstrapFactory.getHTMLFactory().fragment();
		ret.content(propertiesTable(viewGenerator, progressMonitor));
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
		return target.eClass().getEAllStructuralFeatures();
	}
	
	/**
	 * Generates content from a feature.
	 * @param ref
	 * @param viewGenerator
	 * @param progressMonitor
	 * @return
	 */
	protected Object featureContent(EStructuralFeature feature, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		if (feature instanceof EReference) {
			Object listOfActions = ViewAction.listOfViewActionsSorted(referenceValue(feature), Util.nameToLabel(feature.getName()), false, true, 1);
			return viewGenerator.processViewPart(listOfActions, progressMonitor);
		}
		
		Card ret = viewGenerator.getBootstrapFactory().card();
		ret.getHeader().toHTMLElement().content(Util.nameToLabel(feature.getName()));
		ret.getBody().toHTMLElement().content(featureValue(feature, target.eGet(feature), viewGenerator, progressMonitor));
	
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
			if (isFeatureInRole(feature, FeatureRole.FEATURE_ACTION)) {				
				Action featureAction = featureAction(feature);
				if (featureAction != null) {
					children.add(featureAction);
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
		return (Collection<EObject>) target.eGet(feature);
	}	
	
	/**
	 * @param feature
	 * @return {@link Action} wrapping the feature. This implementation returns a list of actions section. May return null
	 */
	protected Action featureAction(EStructuralFeature feature) {
		if (feature.isDerived() || target.eIsSet(feature)) {
			Object featureValue = target.eGet(feature);
			if (featureValue == null) {
				return null;
			}
			if (featureValue instanceof Collection && ((Collection<?>) featureValue).isEmpty()) {
				return null;
			}			
			
			ActionImpl featureSection = new ActionImpl() {
				
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
			featureSection.setText(Util.nameToLabel(feature.getName())); 			
			featureSection.setActivator(new PathNavigationActionActivator(featureSection, ((NavigationActionActivator) getActivator()).getUrl(null), "#feature-" + feature.getName(), getMarker()));
	
			return featureSection;
		}
		return null;
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
			return role == FeatureRole.FEATURE_ACTION;
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
		EObject eContainer = target.eContainer();
		if (eContainer != null) {
			return EObjectAdaptable.adaptTo(eContainer, ViewAction.class);
		}
		return null;
	}

	@Override
	public String getIcon() {
		return EmfUtil.getNasdanikaAnnotationDetail(target.eClass(), "icon");
	}

	@Override
	public String getText() {
		return target.toString();
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
		URL descriptionResource = getClass().getResource(Util.camelToKebab(target.eClass().getName())+".md");
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
		Context targetContext = EObjectAdaptable.adaptTo(target, Context.class);
		URI baseUri = URI.createURI(targetContext.get(Context.BASE_URI_PROPERTY).toString());
		URI relativeBaseUri = baseUri.deresolve(thisUri, true, true, true);
		MutableContext ret = targetContext.fork();
		ret.put(Context.BASE_URI_PROPERTY, relativeBaseUri);
		
		ret.put("link", new PropertyComputer() {
			
			@SuppressWarnings("unchecked")
			@Override
			public <U> U compute(Context context, String key, String path, Class<U> type) {
				URI uri = URI.createURI(path);
				EObject eObj = target.eResource().getResourceSet().getEObject(uri, false);
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
			if (isFeatureInRole(sf, FeatureRole.PROPERTY) && (sf.isDerived() || target.eIsSet(sf))) {
				Object fv = target.eGet(sf);
				if (fv == null || (fv instanceof String && Util.isBlank((String) fv))) {
					continue;
				}
				Object featureValue = featureValue(sf, fv, viewGenerator, progressMonitor);
				if (featureValue != null) {
					Row fRow = pTable.row(); 
					fRow.header(Util.nameToLabel(sf.getName()));
					fRow.cell(featureValue);
				}
			}
		}		
		
		return pTable;
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
		EReference cf = target.eContainmentFeature();
		if (cf == null) {
			return null;
		}
		
		EObject ec = target.eContainer();
		
		// not exactly valid approach - should use isChildFeature of the container view action, but need to "peel" proxy for that.
		if (ec == null || ec.eClass().getEAllReferences().stream().filter(r -> isFeatureInRole(r, FeatureRole.ELEMENT_ACTIONS) || isFeatureInRole(r, FeatureRole.ELEMENT_ACTIONS_SORTED)).count() == 1) {
			return null;
		}		
		LabelImpl category = new LabelImpl();
		category.setId(cf.getName());
		category.setText(EmfUtil.getNasdanikaAnnotationDetail(cf, "label", Util.nameToLabel(cf.getName()))); 
		category.setIcon(EmfUtil.getNasdanikaAnnotationDetail(cf, "icon"));		
		category.setId(ViewAction.adaptToViewActionNonNull(ec).getId() + "-" + cf.getName());
		return category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((target == null) ? 0 : target.hashCode());
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
		return target == other.target;
	}
	
	@Override
	public String toString() {
		return super.toString() + " -> " + target;
	}
	
	protected Marker getMarker() {
		Marked marked = EObjectAdaptable.adaptTo(target, Marked.class);
		return marked == null ? null : marked.getMarker();
	}

	@Override
	public SectionStyle getSectionStyle() {
		return SectionStyle.DEFAULT;
	}
	
}
