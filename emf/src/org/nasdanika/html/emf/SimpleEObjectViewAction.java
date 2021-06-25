package org.nasdanika.html.emf;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.common.Context;
import org.nasdanika.common.Converter;
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
import org.nasdanika.html.app.impl.LabelImpl;
import org.nasdanika.html.app.impl.PathNavigationActionActivator;
import org.nasdanika.html.app.impl.ViewGeneratorImpl;
import org.nasdanika.html.app.viewparts.ListOfActionsViewPart;
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
	 * Member role determines where a {@link EStructuralFeature} or {@link EOperation} is displayed in the UI.
	 * Member can be in zero or more roles. E.g. in ELEMENT_CHILD role to show elements in the 
	 * navigation panel and FEATURE_CHILD to show a summary as a section or CONTENT to show it in the content.
	 * @author Pavel
	 *
	 */
	public enum MemberRole {
		
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
		if (isInRole(Action.Role.SECTION)) {
			NavigationActionActivator ancestorNavigationActivator = getAncestorNavigationActivator();
			String fragment = path.toString().replace('/', '-');
			if (ancestorNavigationActivator == null) {
				return new PathNavigationActionActivator(this, contextUri, "#" + fragment, marked == null ? null : marked.getMarker());
			}

			return new NavigationActionActivator() {
				
				@Override
				public String getUrl(String base) {
					String ancestorUrl = ancestorNavigationActivator.getUrl(base);
					if (ancestorUrl == null) {
						return "#" + fragment;
					}
					return ancestorUrl + (ancestorUrl.contains("#") ? "-" : "#") + fragment ;
				}
			};
		}

		path.append("/index.html");
		return new PathNavigationActionActivator(this, contextUri, sectionPath(getParent()) + path.toString(), marked == null ? null : marked.getMarker());			
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
	
	private NavigationActionActivator getAncestorNavigationActivator() {
		for (Action ancestor = getParent(); ancestor != null; ancestor = ancestor.getParent()) {
			if (ancestor.getActivator() instanceof NavigationActionActivator) {
				return (NavigationActionActivator) ancestor.getActivator();
			}
		}		
		return null;
	}	
	
	protected String getDefaultPath() {
		T theSemanticElement = getSemanticElement();
		return String.valueOf(((List<?>) theSemanticElement.eContainer().eGet(theSemanticElement.eContainmentFeature())).indexOf(theSemanticElement));
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
		return null;
	}
	
	protected Object doGenerate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
		Fragment ret = bootstrapFactory.getHTMLFactory().fragment();
		for (Diagnostic diagnostic: getDiagnostic()) {
			ret.content(bootstrapFactory.alert(HtmlEmfUtil.getSeverityColor(diagnostic.getSeverity()), StringEscapeUtils.escapeHtml4(diagnostic.getMessage())));
		}
		ret.content(propertiesTable(viewGenerator, progressMonitor));
		ret.content(diagnosticSummary(viewGenerator, progressMonitor));
		Object head = generateHead(viewGenerator, progressMonitor);
		if (head != null) {
			ret.content(head);
		}
		
		String description = getTargetDescription();
		if (!Util.isBlank(description)) {
			ret.content(description);
		}
		
		// Contents
		for (ETypedElement member: getMembers()) {
			if (isMemberInRole(member, MemberRole.CONTENT)) {
				ret.content(memberContent(member, viewGenerator, progressMonitor));
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
		List<ETypedElement> ret = new ArrayList<>(getSemanticElement().eClass().getEAllStructuralFeatures());
		ret.addAll(getSemanticElement().eClass().getEAllOperations());
		return ret;
	}
	
	/**
	 * Generates content from a feature or operation.
	 * @param ref
	 * @param viewGenerator
	 * @param progressMonitor
	 * @return
	 */
	protected Object memberContent(ETypedElement member, ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.getBootstrapFactory();
		
		if (member instanceof EReference) {
			Label fl = memberLabel(member);
			Object listOfActions = listOfMemberElementsViewActionsSorted(member, referenceValue((EReference) member), viewGenerator.label(fl), false, true, 1);
			Fragment ret = viewGenerator.getHTMLFactory().fragment(viewGenerator.processViewPart(listOfActions, progressMonitor));
			for (Diagnostic diagnostic: getFeatureDiagnostic((EReference) member)) {
				ret.content(bootstrapFactory.alert(HtmlEmfUtil.getSeverityColor(diagnostic.getSeverity()), StringEscapeUtils.escapeHtml4(diagnostic.getMessage())));
			}			
			return ret;
		}
		
		Card ret = viewGenerator.getBootstrapFactory().card();
		ret.getHeader().toHTMLElement().content(viewGenerator.label(memberLabel(member)));
		if (member instanceof EAttribute) {
			ret.getBody().toHTMLElement().content(memberValue(member, getSemanticElement().eGet((EAttribute) member), viewGenerator, progressMonitor));
			for (Diagnostic diagnostic: getFeatureDiagnostic((EAttribute) member)) {
				ret.getFooter().toHTMLElement().content(bootstrapFactory.alert(HtmlEmfUtil.getSeverityColor(diagnostic.getSeverity()), StringEscapeUtils.escapeHtml4(diagnostic.getMessage())));
			}
		} else {			
			try {
				ret.getBody().toHTMLElement().content(memberValue(member, getSemanticElement().eInvoke((EOperation) member, bind((EOperation) member)), viewGenerator, progressMonitor));
			} catch (InvocationTargetException e) {
				throw new ConfigurationException("Error invoking " + member + ": " + e.getMessage(), e, getMarker());
			}			
		}
	
		return ret.toString();
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
		for (ETypedElement member: getMembers()) {
			if (isMemberInRole(member, MemberRole.ELEMENT_ACTIONS)) {				
				Collection<Action> childrenActions = childrenActions(member);
				if (childrenActions != null) {
					children.addAll(childrenActions);
				}
			} else if (isMemberInRole(member, MemberRole.ELEMENT_ACTIONS_SORTED)) {				
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
				
				EStructuralFeatureViewActionImpl<T, EStructuralFeature, SimpleEObjectViewAction<T>> featureSection = new EStructuralFeatureViewActionImpl<T, EStructuralFeature, SimpleEObjectViewAction<T>>(this, feature) {
					
					@Override
					public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
						if (feature instanceof EReference) {
							@SuppressWarnings("unchecked")
							Object listOfActions = listOfMemberElementsViewActionsSorted(member, featureValue instanceof Collection ? (Collection<EObject>) featureValue : Collections.singleton((EObject) featureValue), null, false, true, 1);
							return viewGenerator.processViewPart(listOfActions, progressMonitor);
						}
						
						return memberValue(feature, featureValue, viewGenerator, progressMonitor);
					}
					
				};
				
				featureSection.getRoles().add(Action.Role.SECTION); 
				featureSection.setSectionStyle(SectionStyle.DEFAULT);
				featureSection.setActivator(new PathNavigationActionActivator(featureSection, ((NavigationActionActivator) getActivator()).getUrl(null), "#feature-" + feature.getName(), getMarker()));
		
				return Collections.singleton(featureSection);
			}
			
			return Collections.emptyList();
		}
		
		EOperationViewActionImpl<T, SimpleEObjectViewAction<T>> operationSection = new EOperationViewActionImpl<T, SimpleEObjectViewAction<T>>(this, (EOperation) member) {
			
			@Override
			public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
				try {
					Object result = getSemanticElement().eInvoke((EOperation) member, bind((EOperation) member));
					return memberValue(member, result, viewGenerator, progressMonitor);
				} catch (InvocationTargetException e) {
					throw new ConfigurationException("Erroer invoking " + member + ": " + e.getMessage(), e, getMarker());
				}			
			}
			
		};
		
		operationSection.getRoles().add(Action.Role.SECTION); 
		operationSection.setSectionStyle(SectionStyle.DEFAULT);
		operationSection.setActivator(new PathNavigationActionActivator(operationSection, ((NavigationActionActivator) getActivator()).getUrl(null), "#operation-" + member.getName(), getMarker()));

		return Collections.singleton(operationSection);
		
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
					return role == MemberRole.ELEMENT_ACTIONS_SORTED;
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
		
		Object targetDocUri = targetContext.get("doc-uri");
		if (targetDocUri != null) {
			URI docUri = URI.createURI(targetDocUri.toString());			
			URI relativeDocUri = docUri.deresolve(thisUri, true, true, true);
			ret.put("doc-uri", relativeDocUri);
		}
		
		ret.put("embedded-image", (PropertyComputer) this::computeEmbeddedImage);
		ret.put("embedded-image-data", (PropertyComputer) this::computeEmbeddedImageData);
		ret.put("include", (PropertyComputer) this::computeInclude);
		ret.put("include-markdown", (PropertyComputer) this::computeIncludeMarkdown);
		
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	protected <U> U computeEmbeddedImage(Context context, String key, String path, Class<U> type) { 
		if (type == null || type == String.class) { 
			int idx = path.indexOf("/"); 
			if (idx == -1) { 
				return null;
			}
			try {
				StringBuilder imageTag = new StringBuilder("<img src=\"");
				imageTag.append(computeEmbeddedImageData(context, key, path, type));
				imageTag.append("\"/>"); 
				String imagePath = path.substring(idx + 1).trim(); 
				int spaceIdx = imagePath.indexOf(' ');
				if (spaceIdx == -1) {
					return (U) imageTag.toString();
				}
				BootstrapFactory bootstrapFactory = context.get(BootstrapFactory.class, BootstrapFactory.INSTANCE);
				Table imageTable = bootstrapFactory.table(); 
				imageTable.toHTMLElement().style().width( "auto"); 
				imageTable.row(imageTag);
				imageTable.row(imagePath.substring(spaceIdx + 1)).backgroundColor(Color.LIGHT); 
				return (U) imageTable.toString();
			} catch (Exception e) {
				throw new ConfigurationException("Error including '" + path +	": " + e, e, getMarker());
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected <U> U computeEmbeddedImageData(Context context, String key, String path, Class<U> type) { 
		if (type == null || type == String.class) { 
			int idx = path.indexOf("/"); 
			if (idx == -1) { 
				return null;
			}
			try {
				StringBuilder imageTag = new StringBuilder("data:image/" + path.substring(0, idx) + ";base64,");
				String imagePath = path.substring(idx + 1).trim(); 
				int spaceIdx = imagePath.indexOf(' ');
				URL imageURL = resolve(spaceIdx == -1 ? imagePath : imagePath.substring(0, spaceIdx));
				Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE); 
				byte[] imageBytes = converter.convert(imageURL.openStream(), byte[].class); 
				imageTag.append(Base64.getEncoder().encodeToString(imageBytes)); 
				return (U) imageTag.toString();
			} catch (Exception e) {
				throw new ConfigurationException("Error including '" + path +	": " + e, e, getMarker());
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected <U> U computeInclude(Context context, String key, String path, Class<U> type) { 
		if (type == null || type == String.class) { 
			try {
				URL includeURL = resolve(path);
				Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
				String includeContent = converter.convert(includeURL.openStream(), String.class); 
				return (U) context.interpolateToString(includeContent);
			} catch (Exception e) {
				throw new ConfigurationException("Error including + path +	" + e, e, getMarker());
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	protected <U> U computeIncludeMarkdown(Context context, String key, String path, Class<U> type) { 
		if (type == null || type == String.class) {
			try {
				URL includeURL = resolve(path);
				Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
				String markdown = converter.convert(includeURL.openStream(), String.class);
				String html = context.get(MarkdownHelper.class, MarkdownHelper.INSTANCE).markdownToHtml(markdown);
				return (U) context. interpolateToString(html) ;
			} catch (Exception e) {
				throw new ConfigurationException("Error including + path +	" + e, e, getMarker());
			}
		}
		return null;
	}

	
	
	/**
	 * Resolves URL relative to this resource URL.
	 */
	protected URL resolve(String url) throws MalformedURLException {
		Marker marker = getMarker(); 
		if (marker != null) {
			String location = marker.getLocation(); 
			if (!Util.isBlank(location)) {
				URL base = new URL(location); 
				return new URL(base, url);
			}
		}
		Resource resource = getSemanticElement().eResource();
		return new URL(resource == null ? null : new URL(resource.getURI().toString()), url);
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
		if (ec == null || ec.eClass().getEAllReferences().stream().filter(r -> isMemberInRole(r, MemberRole.ELEMENT_ACTIONS) || isMemberInRole(r, MemberRole.ELEMENT_ACTIONS_SORTED)).count() == 1) {
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
	
}
