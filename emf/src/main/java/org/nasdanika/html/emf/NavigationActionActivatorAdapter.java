package org.nasdanika.html.emf;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Context;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.NavigationActionActivator;

public abstract class NavigationActionActivatorAdapter extends AdapterImpl implements NavigationActionActivator {
	
	private LinkedList<String> path = new LinkedList<>(); 
	private LinkedList<String> fragment = new LinkedList<>(); 	
	private String contextUri;
	
	public NavigationActionActivatorAdapter(EObject target, String targetPath, boolean isFragment) {
		setTarget(target);
		Context targetContextAdapter = EObjectAdaptable.adaptTo(target, Context.class);
		contextUri = targetContextAdapter == null ? null : (String) targetContextAdapter.get(Context.BASE_URI_PROPERTY);
		
		if (!isFragment && !Util.isBlank(targetPath) && targetPath.indexOf(':') > 1) {
			// Absolute URI - treating as URL.
			path.add(targetPath);
		} else {		
			LinkedList<String> accumulator = isFragment ? fragment: path;
			EReference eContainmentReference = target.eContainmentFeature();
			if (eContainmentReference == null) {
				Resource semanticResource = target.eResource();
				if (semanticResource == null) {
					if (!Util.isBlank(targetPath)) {
						accumulator.add(targetPath);
					}
				} else {
					EList<EObject> resourceContents = semanticResource.getContents();
					if (resourceContents.size() > 1) {
						if (Util.isBlank(targetPath)) {
							accumulator.add(String.valueOf(resourceContents.indexOf(target)));
						} else {
							accumulator.add(targetPath);
						}
					} else if (path.toString().endsWith("/")) {
						if (!Util.isBlank(targetPath)) {
							accumulator.add(targetPath);
						}
					}
				}
			} else {
				accumulator.add(Util.camelToKebab(eContainmentReference.eClass().getName()));
				accumulator.add(Util.camelToKebab(eContainmentReference.getName()));
				if (eContainmentReference.isMany()) {
					if (Util.isBlank(targetPath)) {
						EObject eContainer = target.eContainer();
						if (eContainer != null) {
							accumulator.add(String.valueOf(((List<?>) eContainer.eGet(eContainmentReference)).indexOf(target)));							
						}
					} else {
						accumulator.add(targetPath);
					}
				}	
			}
	
			if (!isFragment) {
				path.add("index.html");
			}
		}
	}
	
	private NavigationActionActivator getAncestorNavigationActivator() {
		for (EObject ancestor = ((EObject) getTarget()).eContainer(); ancestor != null; ancestor = ancestor.eContainer()) {
			NavigationActionActivator ancestorActivator = EObjectAdaptable.adaptTo(ancestor, NavigationActionActivator.class);
			if (ancestorActivator != null) {
				return ancestorActivator;
			}
		}	
		Resource resource = ((EObject) getTarget()).eResource();
		if (resource != null) {
			Adapter resourceNavigationAdapter = EcoreUtil.getRegisteredAdapter(resource, NavigationActionActivator.class);
			if (resourceNavigationAdapter instanceof NavigationActionActivatorAdapter) {
				return (NavigationActionActivator) resourceNavigationAdapter;
			}
			ResourceSet resourceSet = resource.getResourceSet();
			if (resourceSet != null) {
				Adapter adapter = EcoreUtil.getExistingAdapter(resourceSet, NavigationActionActivator.class);
				if (adapter instanceof NavigationActionActivator) {
					return (NavigationActionActivator) adapter;
				}
			}
		}
		return null;
	}

	@Override
	public String getUrl(String base) {
		try {			
			// Resolving against the context URI
			NavigationActionActivator ancestorNavigationActivator = getAncestorNavigationActivator();
			String ctx = ancestorNavigationActivator == null ? contextUri : ancestorNavigationActivator.getUrl(null);
			URI ctxURI = Util.isBlank(ctx) ? null : URI.createURI(ctx);
			URI uri = path.stream().map(e -> URI.createURI(e)).reduce(ctxURI, (c, s) -> c == null || c.isRelative() || !c.isHierarchical() ? s : s.resolve(c)); 
			if (!fragment.isEmpty()) {
				String existingFragment = uri.fragment();
				String thisFragment = String.join("-", fragment);
				uri = uri.appendFragment(Util.isBlank(existingFragment) ? thisFragment : existingFragment + "-" + thisFragment);
			}
			
			// Relativising against the base
			return (Util.isBlank(base) ? uri : uri.deresolve(URI.createURI(base), true, true, true)).toString();
		} catch (Exception e) {
			throw new ConfigurationException(e.getMessage(), e, EObjectAdaptable.adaptTo((EObject) getTarget(), Marked.class));
		}
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == ActionActivator.class;
	}

}
