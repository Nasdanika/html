package org.nasdanika.html.app.impl;

///**
// * Implementation of action.
// */
//public class ActionImpl implements Action {
//	
//	protected List<Action> children;
//	private String icon;
//	private String label;
//	private boolean disabled;
//	private String tooltip;
//	private Color color;
//	private boolean outline;
//	private String confirmation;
//	private boolean floatRight;
//	protected List<Action> contextActions;
//	private String id;
//	private String description;
//	
//	public ActionImpl() {
//		
//	}
//	
//	/**
//	 * Creates action from data map which can be constructed from, say YAML or JSON file.
//	 * @param data
//	 */
//	@SuppressWarnings("unchecked")
//	public ActionImpl(Map<String, Object> data) {
//		if (data.containsKey("children")) {
//			for (Map<String, Object> dChild: (Iterable<Map<String, Object>>) data.get("children")) {
//				getChildren().add(createAction(dChild));
//			}
//		}
//		if (data.containsKey("context-actions")) {
//			for (Map<String, Object> dContextAction: (Iterable<Map<String, Object>>) data.get("context-actions")) {
//				getContextActions().add(createAction(dContextAction));
//			}
//		}
//		setIcon((String) data.get("icon"));
//		setLabel((String) data.get("label"));
//		setDisabled((Boolean) data.get("disabled"));
//		setTooltip((String) data.get("tooltip"));
//		if (data.containsKey("color")) {
//			setColor(Color.valueOf((String) data.get("color")));
//		}
//		setOutline((Boolean) data.get("outline"));
//		setConfirmation((String) data.get("confirmation"));
//		setFloatRight((Boolean) data.get("float-right"));
//		setId((String) data.get("id"));
//		setDescription((String) data.get("description"));
//		
//	}	
//	
//	protected Action createAction(Map<String,Object> data) {
//		if (data.containsKey("href")) {
//			return new NavigationActionImpl(data);
//		}
//		if (data.containsKey("code")) {
//			return new ScriptActionImpl(data);			
//		}
//		return new ActionImpl(data);
//	}
//
//	@Override
//	public String getIcon() {
//		return icon;
//	}
//
//	public void setIcon(String icon) {
//		this.icon = icon;
//	}
//
//	public void setLabel(String label) {
//		this.label = label;
//	}
//
//	public void setDisabled(boolean disabled) {
//		this.disabled = disabled;
//	}
//
//	public void setTooltip(String tooltip) {
//		this.tooltip = tooltip;
//	}
//
//	public void setColor(Color color) {
//		this.color = color;
//	}
//
//	public void setOutline(boolean outline) {
//		this.outline = outline;
//	}
//
//	public void setConfirmation(String confirmation) {
//		this.confirmation = confirmation;
//	}
//
//	public void setFloatRight(boolean floatRight) {
//		this.floatRight = floatRight;
//	}
//
//	public void setId(String id) {
//		this.id = id;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	@Override
//	public String getLabel() {
//		return label;
//	}
//
//	@Override
//	public boolean isDisabled() {
//		return disabled;
//	}
//
//	@Override
//	public String getTooltip() {
//		return tooltip;
//	}
//
//	@Override
//	public Color getColor() {
//		return color;
//	}
//
//	@Override
//	public boolean isOutline() {
//		return outline;
//	}
//
//	@Override
//	public String getConfirmation() {
//		return confirmation;
//	}
//
//	@Override
//	public boolean isFloatRight() {
//		return floatRight;
//	}
//
//	@Override
//	public List<Action> getChildren() {
//		if (children == null) {
//			children = loadChildren();
//		}
//		return children;
//	}
//
//	/**
//	 * Loads children on-access.
//	 * @return
//	 */
//	protected List<Action> loadChildren() {
//		return new ArrayList<>();
//	}
//
//	@Override
//	public List<Action> getContextActions() {
//		if (contextActions == null) {
//			contextActions = loadContextActions();
//		}
//		return contextActions;
//	}
//
//	/**
//	 * On-demand loads context actions.
//	 * @return
//	 */
//	protected List<Action> loadContextActions() {
//		return new ArrayList<>();
//	}
//
//	@Override
//	public String getId() {
//		return id;
//	}
//
//	@Override
//	public String getDescription() {
//		return description;
//	}
//
//}
