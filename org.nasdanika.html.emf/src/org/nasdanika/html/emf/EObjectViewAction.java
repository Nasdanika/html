package org.nasdanika.html.emf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Identity;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.ActionFilter;
import org.nasdanika.html.app.viewparts.ViewSingleValuePropertySourceViewPart;

/**
 * Adapts {@link EObject} to {@link Action}.
 * @author Pavel Vlasov
 *
 */
public class EObjectViewAction extends EObjectSingleValuePropertySource implements ViewAction {
	
	protected Identity identity;
	protected AuthorizationProvider authorizationProvider;

	public EObjectViewAction(EObject value) {
		super(value);
		authorizationProvider = (AuthorizationProvider) EcoreUtil.getRegisteredAdapter(value, AuthorizationProvider.class);
		identity = (Identity) EcoreUtil.getRegisteredAdapter(value, Identity.class);
	}
	
	@Override
	public Object getId() {
		return identity == null ? super.getId() : identity.getId();
	}
	
	@Override
	public Object execute(ViewGenerator viewGenerator, Map<String, Object> input) {
		return new ViewSingleValuePropertySourceViewPart(this).generate(viewGenerator);
	}
	
	@Override
	public Label getCategory() {
		EReference containmentFeature = value.eContainmentFeature();
		return containmentFeature == null ? null : new EStructuralFeatureLabel<EReference>(containmentFeature);
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
	public boolean isFloatRight() {
		return false;
	}

	@Override
	public List<? extends Action> getChildren() {
		List<EStructuralFeature> childFeatures = getChildFeatures();
		if (childFeatures.isEmpty()) {
			return Collections.emptyList();
		}
				
		List<Action> ret = new ArrayList<>();
		for (EStructuralFeature childFeature: childFeatures) {
			String childFeatureRole = getFeatureRole(childFeature);
			if (Action.Role.NAVIGATION.equals(childFeatureRole)) {
				if (childFeature instanceof EReference) {
					if (childFeature.isMany()) {
						EReferenceMultiValuePropertySource ps = new EReferenceMultiValuePropertySource(value, (EReference) childFeature);				
						for (Object child: ps.getValues()) {
							if (child instanceof EObject) {
								Action ca = (Action) EcoreUtil.getRegisteredAdapter((EObject) child, ViewAction.class);
								if (ca != null) {
									ret.add(filterChildAction(ca, childFeatureRole, childFeatures.size() == 1 ? null : ps));
								}
							}
						}
					} else {
						EReferenceSingleValuePropertySource ps = new EReferenceSingleValuePropertySource(value, (EReference) childFeature);
						Object child = ps.getValue();
						if (child instanceof EObject) {
							Action ca = (Action) EcoreUtil.getRegisteredAdapter((EObject) child, ViewAction.class);
							if (ca != null) {
								ret.add(filterChildAction(ca, childFeatureRole, childFeatures.size() == 1 ? null : ps));
							}
						}
					}
				}
			} else if (Action.Role.SECTION.equals(childFeatureRole)) {
				if (childFeature instanceof EReference) {
					if (childFeature.isMany()) {
						ret.add(new EReferenceMultiValuePropertySourceViewAction(value, (EReference) childFeature, childFeatureRole, this));
					} else {
						ret.add(new EReferenceSingleValuePropertySourceViewAction(value, (EReference) childFeature, childFeatureRole, this));
					}
				} else {
					// TODO - single attribute, many attribute
				}
				
			}
		}
		
		return ret;
	}
	
	/**
	 * Filters isInRole() by returning feature role. 
	 * @param childActoin
	 * @param eReference
	 * @return
	 */
	protected Action filterChildAction(Action childAction, String featureRole, Label category) {
		return new ActionFilter<Action>(childAction) {
			
			@Override
			public boolean isInRole(String role) {
				return featureRole != null && featureRole.equals(role);
			}
			
			@Override
			public Label getCategory() {
				return category;
			}
			
			@Override
			public Action getParent() {
				return EObjectViewAction.this;
			}
			
		};
	}
	
	/**
	 * This implementation returns multi-value containment features.
	 * Override to customize, e.g. sort.
	 * @return features to wrap into child actions.
	 */
	protected List<EStructuralFeature> getChildFeatures() {
		return value.eClass().getEAllStructuralFeatures()
				.stream()
				.filter(f -> authorizationProvider == null || authorizationProvider.authorizeRead(f.getName()))
				.filter(this::isChildFeature)
				.sorted((fa, fb) -> fa.getName().compareTo(fb.getName()))
				.collect(Collectors.toList());		
	}	
	
	/**
	 * This method returns if feature is in NAVIGATION or SECTION role. Override to customize.
	 * @param feature
	 * @return
	 */
	protected boolean isChildFeature(EStructuralFeature feature) {
		String featureRole = getFeatureRole(feature);
		return Action.Role.NAVIGATION.equals(featureRole) || Action.Role.SECTION.equals(featureRole);
	}

	@Override
	public Action getParent() {
		EObject eContainer = value.eContainer();		
		return eContainer == null ? null : (Action) EcoreUtil.getRegisteredAdapter((EObject) eContainer, ViewAction.class);
	}

	@Override
	public ActionActivator getActivator() {
		return (ActionActivator) EcoreUtil.getRegisteredAdapter(value, ViewActionActivator.class);
	}
	
	@Override
	protected String getFeatureRole(EStructuralFeature feature) {
		// Handling property descriptors.
		String featureRole = super.getFeatureRole(feature);
		if (featureRole != null) {
			return featureRole;
		}
		
		if (feature instanceof EReference) {
			boolean containment = ((EReference) feature).isContainment();
			if (feature.isMany()) {
				// Many containment are navigations, many non-containment are sections			
				return  containment ? Action.Role.NAVIGATION : Action.Role.SECTION;
			}			
			
			// Single containment are sections, single non-containment are property descriptors (handled in super.getFeatureRole())
			return containment ? Action.Role.SECTION : FEATURE_ROLE_PROPERTY_DESCRIPTOR;			
		} 
		
		if (feature.isMany()) {
			// Many attributes are sections.
			return Action.Role.SECTION;
		}
		
		return null;
	}

	@Override
	public boolean isInRole(String role) {
		EReference containmentFeature = value.eContainmentFeature();	
		return role != null && containmentFeature != null && role.equals(getFeatureRole(containmentFeature));
	}

}
