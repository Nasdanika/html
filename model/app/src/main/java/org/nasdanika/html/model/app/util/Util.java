package org.nasdanika.html.model.app.util;

import static org.nasdanika.common.Util.isBlank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.ActionReference;
import org.nasdanika.html.model.app.AppPackage;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;

public final class Util {
	
	private Util() {
		// Utility class
	}
	
	/**
	 * Resolves URI's by traversing containment references from the root Action taking {@link ActionReference} into account.
	 * @param context
	 * @return A function resolving {@link URI} for the argument {@link Action}. Caches results.
	 */
	public static BiFunction<Label,URI,URI> uriResolver(Label root) {		
		return uriResolver(root, null);
	}
	
	/**
	 * Resolves URI's by traversing containment references from the root Action taking {@link ActionReference} into account.
	 * @param context
	 * @return A function resolving {@link URI} for the argument {@link Action}. Caches results.
	 */
	public static BiFunction<Label,URI,URI> uriResolver(Label root, Context context) {		
		Map<String, URI> cache = new HashMap<>();
		if (context == null) {
			context = Context.EMPTY_CONTEXT;
		}
		URI baseURI = context.get(Context.BASE_URI_PROPERTY, URI.class);
		if (baseURI == null) {
			context = context.fork();
			baseURI = URI.createURI("temp://" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/");
			((MutableContext) context).put(Context.BASE_URI_PROPERTY, baseURI);
		}
		
		traverse(root, baseURI, context, cache);
		
		return new BiFunction<Label, URI, URI>() {
			
			@Override
			public URI apply(Label label, URI base) {
				URI uri = cache.get(label.getUuid());		
				return base == null || uri == null ? uri : uri.deresolve(base, true, true, true);
			}
		
		};
	}
	
	private static void traverse(Label label, URI base, Context context, Map<String, URI> cache) {
		URI linkURI = compute(label, base, context);
		cache.put(label.getUuid(), linkURI);
		
		for (EObject child: resolveActionReferences(label.getChildren())) {
			if (child instanceof Label) {
				traverse((Label) child, linkURI == null ? base : linkURI, context, cache);
			}
		}
		
		if (label instanceof Action) {
			Action action = (Action) label;
			for (EObject item: resolveActionReferences(action.getNavigation())) {
				if (item instanceof Label) {
					traverse((Label) item, linkURI == null ? base : linkURI, context, cache);
				}
			}
			for (Action section: action.getSections()) {
				traverse(section, linkURI == null ? base : linkURI, context, cache);
			}
			for (Action anonymous: action.getAnonymous()) {
				traverse(anonymous, linkURI == null ? base : linkURI, context, cache);
			}
		}
		
		// TODO - nav panels?
	}

	private static URI compute(Label label, URI base, Context context) {
		if (label instanceof Link) {
			Link link = (Link) label;
			String uriString;
			if (link.eContainmentFeature() == AppPackage.Literals.ACTION__SECTIONS) {
				String aName = context.interpolateToString(link.getLocation());
				if (isBlank(aName)) {
					return null;
				}
				uriString = "#" + aName;
			} else {				
				uriString = context.interpolateToString(link.getLocation());
				if (isBlank(uriString)) {
					return null;
				}
			}
			URI uri = URI.createURI(uriString);
			if (uri.isRelative() && base != null && base.isHierarchical() && !base.isRelative()) {
				return uri.resolve(base);
			}
			return uri;
		} 
		
		return null;
	}
	
	public static EObject resolveActionReference(EObject obj) {
		return obj instanceof ActionReference ? ((ActionReference) obj).getTarget() : obj;
	}
	
	public static List<EObject> resolveActionReferences(EList<EObject> objs) {
		return objs.stream().map((Function<EObject, EObject>) Util::resolveActionReference).collect(Collectors.toList());
	}
	

}
