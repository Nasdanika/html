package org.nasdanika.html.app.impl;

import java.util.LinkedList;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.NavigationActionActivator;

public class PathNavigationActionActivator implements NavigationActionActivator {
	
	private Action action;
	private LinkedList<String> path = new LinkedList<>(); 
	private Marker marker;	
	private String contextUri;

	/**
	 * 
	 * @param action Owning action
	 * @param contextUri URI to resolve action URI's agains for actions which do not have ancestors with navigation activator.
	 * @param path Action URL relative to its first navigation ancestor or to the context URI.
	 * @param marker
	 */
	public PathNavigationActionActivator(Action action, String contextUri, String path, Marker marker) {
		this.action = action;
		this.contextUri = contextUri;
		if (path != null) {
			this.path.add(path);
		}
		this.marker = marker;
	}

	public LinkedList<String> getPath() {
		return path;
	}
	
	private Action getNavigationAncestor() {
		for (Action ancestor = action.getParent(); ancestor != null; ancestor = ancestor.getParent()) {
			if (ancestor.getActivator() instanceof NavigationActionActivator) {
				return ancestor;
			}
		}		
		return null;
	}

	@Override
	public String getUrl(String base) {
		try {
			// Resolving against the context URI
			Action navigationAncestor = getNavigationAncestor();
			String ctx = navigationAncestor == null ? contextUri : ((NavigationActionActivator) navigationAncestor.getActivator()).getUrl(null);
			URI ctxURI = Util.isBlank(ctx) ? null : URI.createURI(ctx);
			URI uri = path.stream().map(e -> URI.createURI(e)).reduce(ctxURI, (c, s) -> c == null || c.isRelative() || !c.isHierarchical() ? s : s.resolve(c)); 
			
			// Relativising against the base
			return (Util.isBlank(base) ? uri : uri.deresolve(URI.createURI(base), true, true, true)).toString();
		} catch (Exception e) {
			throw new ConfigurationException(e.getMessage(), e, marker);
		}
	}

}
