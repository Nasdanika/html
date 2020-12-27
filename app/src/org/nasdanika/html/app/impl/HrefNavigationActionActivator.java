package org.nasdanika.html.app.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;

import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.NavigationActionActivator;

public class HrefNavigationActionActivator implements NavigationActionActivator {
	
	private Action action;
	private String href;
	private LinkedList<String> path = new LinkedList<>(); 
	private Marker marker;

	public HrefNavigationActionActivator(Action action, String href, Marker marker) {
		this.action = action;
		this.href = href;
		this.marker = marker;
	}

	public LinkedList<String> getPath() {
		return path;
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
				if (path.isEmpty()) {
					return href;
				}
				URI baseURI = new URI(path.getFirst());
				for (String pe: path.subList(1, path.size())) {
					baseURI = baseURI.resolve(new URI(pe));
				}
				return baseURI.resolve(new URI(href)).toString();
			}
		
			URI baseURI = new URI(theBase);
			for (String pe: path) {
				baseURI = baseURI.resolve(new URI(pe));
			}
			return baseURI.resolve(new URI(href)).toString();
		} catch (URISyntaxException e) {
			throw new ConfigurationException(e.getMessage(), e, marker);
		}
	}

}
