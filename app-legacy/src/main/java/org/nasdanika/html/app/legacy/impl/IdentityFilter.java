package org.nasdanika.html.app.impl;

import org.nasdanika.html.app.Identity;

public class IdentityFilter<T extends Identity> implements Identity {
	
	protected T target;

	public IdentityFilter(T target) {
		this.target = target;
	}
	
	@Override
	public Object getId() {
		return target.getId();
	}

}
