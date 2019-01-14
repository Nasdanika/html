package org.nasdanika.html.app.impl;

import java.util.Map;

import org.nasdanika.html.app.Identity;
import org.nasdanika.html.bootstrap.Color;

/**
 * Bean identity implementation
 * @author Pavel Vlasov
 *
 */
public class IdentityImpl implements Identity {
	
	private Object id;
	
	public IdentityImpl() {
		
	}
	
	public IdentityImpl(Map<String, Object> data) {
		id = data.get("id");
	}

	@Override
	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

}
