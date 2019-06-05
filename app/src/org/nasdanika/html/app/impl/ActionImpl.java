package org.nasdanika.html.app.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ScriptActionActivator;
import org.nasdanika.html.app.ViewGenerator;

/**
 * Bean action implementation.
 * @author Pavel Vlasov
 *
 */
public class ActionImpl extends LabelImpl implements Action {
	
	private List<Action> children = new ArrayList<>();
	private String confirmation;
	private Action parent;
	private boolean disabled;
	private boolean floatRight;
	private ActionActivator activator;
	private Label category;
	private Set<String> roles = new HashSet<>();
	
	public ActionImpl() {
		
	}
	
	public ActionImpl(Map<String, Object> data) {
		super(data);
		@SuppressWarnings("unchecked")
		Iterable<Map<String, Object>> cd = (Iterable<Map<String, Object>>) data.get("children");
		if (cd != null) {
			for (Map<String, Object> c: cd) {
				children.add(createAction(c));
			}
		}
		confirmation = (String) data.get("confirmation");
		disabled = Boolean.TRUE.equals(data.get("disabled"));
		floatRight = Boolean.TRUE.equals(data.get("floatRight"));
		
		@SuppressWarnings("unchecked")
		Map<String, String> ad = (Map<String, String>) data.get("activator");
		if (ad != null) {
			for (Entry<String, String> e: ad.entrySet()) {
				if ("url".equals(e.getKey())) {
					activator = new NavigationActionActivator() {
						
						@Override
						public String getUrl() {
							return e.getValue();
						}
					};
					break;
				}
				if ("code".equals(e.getKey())) {
					activator = new ScriptActionActivator() {
						
						@Override
						public String getCode() {
							return e.getValue();
						}
					};
					break;
				}
			}
		}
		
		@SuppressWarnings("unchecked")
		Map<String, Object> catd = (Map<String,Object>) data.get("category");
		if (catd != null) {
			category = new LabelImpl(catd);
		}
		
		@SuppressWarnings("unchecked")
		Collection<String> roles = (Collection<String>) data.get("roles");
		if (roles != null) {
			this.roles.addAll(roles);
		}
		
	}
	
	public Set<String> getRoles() {
		return roles;
	}
	
	@Override
	public boolean isInRole(String role) {
		return getRoles().contains(role);
	}
	
	@Override
	public ActionActivator getActivator() {
		return activator;
	}
	
	public void setActivator(ActionActivator activator) {
		this.activator = activator;
	}
	
	@Override
	public Label getCategory() {
		return category;
	}
	
	public void setCategory(Label category) {
		this.category = category;
	}
	
	protected Action createAction(Map<String, Object> cd) {
		return new ActionImpl(cd);
	}

	public void setParent(Action parent) {
		this.parent = parent;
	}

	@Override
	public boolean isDisabled() {
		return disabled;
	}

	@Override
	public String getConfirmation() {
		return confirmation;
	}

	@Override
	public boolean isFloatRight() {
		return floatRight;
	}

	@Override
	public List<Action> getChildren() {
		return children;
	}

	@Override
	public Action getParent() {
		return parent;
	}

	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public void setFloatRight(boolean floatRight) {
		this.floatRight = floatRight;
	}
	
	@Override
	public Map<String, Object> toMap() {
		Map<String, Object> map = Action.super.toMap();
		map.put("roles", roles);
		return map;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator) {
		return null;
	}
	
}
