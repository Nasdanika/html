package org.nasdanika.html.jstree.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.html.jstree.JsTree;
import org.nasdanika.html.jstree.JsTreeNode;

class JsTreeNodeImpl implements JsTreeNode {
	
	private Object id;
	private Object text;
	private Map<String, Object> aAttributes = new HashMap<>();
	private Map<String, Object> liAttributes = new HashMap<>();
	private List<JsTreeNode> children = new ArrayList<>();			
	private boolean selected;
	private boolean opened;
	private boolean disabled;
	private String icon;
	private boolean hasChildren;
	
	@Override
	public JsTree selected(boolean selected) {
		this.selected = selected;
		return this;
	}
	
	@Override
	public JsTree opened(boolean opened) {
		this.opened = opened;
		return this;
	}
	
	@Override
	public JsTree icon(String icon) {
		this.icon = icon;
		return this;
	}
	
	@Override
	public JsTree disabled(boolean disabled) {
		this.disabled = disabled;
		return this;
	}

	@Override
	public JsTreeNode id(Object id) {
		this.id = id;
		return this;
	}
	
	@Override
	public Object getId() {
		return id;
	}

	@Override
	public JsTreeNode text(Object text) {
		this.text = text;
		return this;
	}

	@Override
	public List<JsTreeNode> children() {
		return children;
	}

	@Override
	public JsTreeNode listItemAttribute(String name, Object value) {
		if (value == null) {
			liAttributes.remove(name);
		} else {
			liAttributes.put(name, value);
		}
		return this;
	}

	@Override
	public JsTreeNode anchorAttribute(String name, Object value) {
		if (value == null) {
			aAttributes.remove(name);					
		} else {
			aAttributes.put(name, value);
		}
		return this;
	}

	@Override
	public JSONObject toJSON() {
		return toJSON(null);
	}	
	
	@Override
	public JSONObject toJSON(Predicate<JsTreeNode> filter) {
		JSONArray jch = new JSONArray();
		for (JsTreeNode child: children) {
			if (child != null) {
				JSONObject jsonChild = ((JsTreeNodeImpl) child).toJSON(filter);
				if (jsonChild != null) {
					jch.put(jsonChild);
				}
			}
		}
		
		if (jch.length() == 0 && filter != null && !filter.test(this) && hasChildren) {
			return null; // no children, this one is not accepted.
		}
		
		JSONObject data = new JSONObject();
		JSONObject state = new JSONObject();
		if (selected) {
			state.put("selected", true);
		}
		if (opened) {
			state.put("opened", true);
		}
		if (disabled) {
			state.put("disabled", true);
		}
		data.put("state", state);
		if (icon != null) {
			data.put("icon", icon);
		} 
		if (id != null) {
			data.put("id", id.toString());
		}
		if (text != null) {
			data.put("text", text.toString());
		}
		
		data.put("children", jch.length() == 0 && hasChildren ? true : jch);

		data.put("li_attr", liAttributes);
		data.put("a_attr", aAttributes);
		
		return data;
	}
	
	public String toString() {
		return toJSON().toString();
	}	
	
	private Object data;
	private Map<String, Object> properties = new HashMap<>();
	
	@Override
	public Object getData() {
		return data;
	}
	
	@Override
	public JsTreeNode setData(Object data) {
		this.data = data;
		return this;
	}
	
	@Override
	public Object getData(String key) {
		return properties.get(key);
	}
	
	@Override
	public JsTreeNode setData(String key, Object data) {
		properties.put(key, data);
		return this;
	}

	@Override
	public <R> R accept(Collector<R> collector) {
		List<R> childResults = new ArrayList<R>();
		for (JsTreeNode child: children()) {
			childResults.add(child.accept(collector));
		}
		return collector.visit(this, childResults);
	}

	@Override
	public JsTreeNode hasChildren() {
		hasChildren = true;
		return this;
	}

}