package org.nasdanika.html.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.html.FontAwesome;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.JsTree;
import org.nasdanika.html.JsTreeNode;
import org.nasdanika.html.Tag;

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
	private FontAwesome<Tag> fontAwesome;
	private HTMLFactory factory;
	
	JsTreeNodeImpl(HTMLFactory factory) {
		this.factory = factory;
	}
	
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
	public FontAwesome<Tag> icon() {
		icon = null;
		fontAwesome = factory.fontAwesome();
		return fontAwesome;
	}
	
	@Override
	public JsTree icon(String icon) {
		fontAwesome = null;
		this.icon = icon;
		return this;
	}
	
	@Override
	public JsTree disabled(boolean disabled) {
		this.disabled = disabled;
		return this;
	}

	@Override
	public JsTree id(Object id) {
		this.id = id;
		return this;
	}

	@Override
	public JsTree text(Object text) {
		this.text = text;
		return this;
	}

	@Override
	public List<JsTreeNode> children() {
		return children;
	}

	@Override
	public JsTree listItemAttribute(String name, Object value) {
		if (value == null) {
			liAttributes.remove(name);
		} else {
			liAttributes.put(name, value);
		}
		return this;
	}

	@Override
	public JsTree anchorAttribute(String name, Object value) {
		if (value == null) {
			aAttributes.remove(name);					
		} else {
			aAttributes.put(name, value);
		}
		return this;
	}
	
	@Override
	public JSONObject toJSON() {
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
		} else if (fontAwesome != null) {
			data.put("icon", ((TagImpl) fontAwesome.getTarget()).classes());
		}
		if (id != null) {
			data.put("id", id.toString());
		}
		if (text != null) {
			data.put("text", text.toString());
		}
		
		JSONArray jch = new JSONArray();
		for (JsTreeNode child: children) {
			if (child != null) {
				jch.put(((JsTreeNodeImpl) child).toJSON());
			}
		}
		data.put("children", jch);

		data.put("li_attr", liAttributes);
		data.put("a_attr", aAttributes);
		
		return data;
	}
	
	public String toString() {
		return toJSON().toString();
	}

	@Override
	public int compareTo(JsTreeNode o) {
		return String.valueOf(text).compareTo(String.valueOf(((JsTreeNodeImpl) o).text));
	}	
}