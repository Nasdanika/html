package org.nasdanika.html.app.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ScriptActionActivator;

/**
 * Bean action implementation.
 * @author Pavel Vlasov
 *
 */
public abstract class ActionImpl extends LabelImpl implements Action {
	
	private List<Action> children = new ArrayList<>();
	private String confirmation;
	private List<Action> contextActions = new ArrayList<>();
	private List<Action> sections = new ArrayList<>();
	private Action parent;
	private boolean disabled;
	private boolean floatRight;
	private ActionActivator activator;
	private Label category;
	
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
		@SuppressWarnings("unchecked")
		Iterable<Map<String, Object>> cxd = (Iterable<Map<String, Object>>) data.get("contextActions");
		if (cxd != null) {
			for (Map<String, Object> c: cxd) {
				contextActions.add(createAction(c));
			}
		}
		@SuppressWarnings("unchecked")
		Iterable<Map<String, Object>> sd = (Iterable<Map<String, Object>>) data.get("sections");
		if (sd != null) {
			for (Map<String, Object> c: sd) {
				sections.add(createAction(c));
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
	
	protected abstract Action createAction(Map<String, Object> cd);

	public void setParent(Action parent) {
		this.parent = parent;
	}
	
	@Override
	public List<? extends Action> getSections() {
		return sections;
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
	public List<? extends Action> getChildren() {
		return children;
	}

	@Override
	public List<? extends Action> getContextActions() {
		return contextActions;
	}

	@Override
	public Action getParent() {
		return parent;
	}

	@Override
	public List<Action> getPath() {		
		if (getParent() == null) {
			return Collections.emptyList();
		}
		ArrayList<Action> ret = new ArrayList<>(getParent().getPath());
		ret.add(getParent());
		return ret;
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
	
}
