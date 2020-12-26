package org.nasdanika.html.app.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.NavigationActionActivator;

public class HrefNavigationActionActivator implements NavigationActionActivator {
	
	private Action action;
	private String href;
	private String path; // TODO - multiple paths - from category and references
	private Marker marker;

	public HrefNavigationActionActivator(Action action, String href, Marker marker) {
		this.action = action;
		this.href = href;
		this.marker = marker;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String getUrl(String base) {
		String theBase = base;
		for (Action ancestor = action.getParent(); ancestor != null; ancestor = ancestor.getParent()) {
			if (ancestor.getActivator() instanceof NavigationActionActivator) {
				theBase = ((NavigationActionActivator) ancestor.getActivator()).getUrl(base);
				break;
			}
		}
		
		try {
			if (Util.isBlank(theBase)) {
				if (Util.isBlank(path)) {
					return href;
				}
				return new URI(path).resolve(new URI(href)).toString();
			}
		
			URI baseURI = new URI(theBase);
			if (!Util.isBlank(path)) {
				baseURI = baseURI.resolve(new URI(path));
			}
			return baseURI.resolve(new URI(href)).toString();
		} catch (URISyntaxException e) {
			throw new ConfigurationException(e.getMessage(), e, marker);
		}
	}

}
