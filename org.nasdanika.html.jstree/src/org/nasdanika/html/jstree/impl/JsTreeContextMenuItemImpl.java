package org.nasdanika.html.jstree.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.html.jstree.JsTreeContextMenuItem;

class JsTreeContextMenuItemImpl implements JsTreeContextMenuItem {
	
	private boolean separatorBefore;
	private boolean separatorAfter;
	private boolean disabled;
	private Object label;
	private Object title;
	private Object action;
	private Object icon;
	private Object shortcut;
	private Object shortcutLabel;
	private Map<String, JsTreeContextMenuItem> subMenu = new LinkedHashMap<>();
	private Object subMenuObj;

	@Override
	public JsTreeContextMenuItem separatorBefore(boolean separatorBefore) {
		this.separatorBefore = separatorBefore;
		return this;
	}

	@Override
	public JsTreeContextMenuItem separatorAfter(boolean separatorAfter) {
		this.separatorAfter = separatorAfter;
		return this;
	}

	@Override
	public JsTreeContextMenuItem disabled(boolean disabled) {
		this.disabled = disabled;
		return this;
	}

	@Override
	public JsTreeContextMenuItem label(Object label) {
		this.label = label;
		return this;
	}

	@Override
	public JsTreeContextMenuItem title(Object title) {
		this.title = title;
		return this;
	}

	@Override
	public JsTreeContextMenuItem action(Object action) {
		this.action = action;
		return this;
	}

	@Override
	public JsTreeContextMenuItem icon(Object icon) {
		this.icon = icon;
		return this;
	}

	@Override
	public JsTreeContextMenuItem shortcut(Object shortcut) {
		this.shortcut = shortcut;
		return this;
	}

	@Override
	public JsTreeContextMenuItem shortcutLabel(Object shortcutLabel) {
		this.shortcutLabel = shortcutLabel;
		return this;
	}

	@Override
	public JsTreeContextMenuItem createSubMenuItem(String key) {
		JsTreeContextMenuItem child = subMenu.get(key);
		if (child == null) {
			child = new JsTreeContextMenuItemImpl();
			subMenu.put(key, child);
		}
		return child;
	}
	
	@Override
	public JsTreeContextMenuItem addSubMenuItem(String key, JsTreeContextMenuItem item) {
		subMenu.put(key, item);
		return this;
	}
	
	@Override
	public JsTreeContextMenuItem subMenu(Object subMenu) {
		this.subMenuObj = subMenu;
		return this;
	}
	
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder("{").append(System.lineSeparator());
		int initialLength = ret.length();
		if (action != null) {
			ret.append("action: ").append(action);
		}
		if (disabled) {
			if (ret.length() > initialLength) {
				ret.append(",").append(System.lineSeparator());
			}
			ret.append("_disabled: true");
		}
		if (icon != null) {
			if (ret.length() > initialLength) {
				ret.append(",").append(System.lineSeparator());
			}
			ret.append("icon: '").append(icon).append("'");
		}
		if (label != null) {
			if (ret.length() > initialLength) {
				ret.append(",").append(System.lineSeparator());
			}
			ret.append("label: '").append(label).append("'");
		}
		if (separatorAfter) {
			if (ret.length() > initialLength) {
				ret.append(",").append(System.lineSeparator());
			}
			ret.append("separator_after: true");
		}
		if (separatorBefore) {
			if (ret.length() > initialLength) {
				ret.append(",").append(System.lineSeparator());
			}
			ret.append("separator_before: true");
		}
		if (shortcut != null) {
			if (ret.length() > initialLength) {
				ret.append(",").append(System.lineSeparator());
			}
			ret.append("shortcut: ").append(shortcut);
		}
		if (shortcutLabel != null) {
			if (ret.length() > initialLength) {
				ret.append(",").append(System.lineSeparator());
			}
			ret.append("shortcut_label: '").append(shortcutLabel).append("'");
		}
		if (title != null) {
			if (ret.length() > initialLength) {
				ret.append(",").append(System.lineSeparator());
			}
			ret.append("title: '").append(title).append("'");
		}
		if (subMenuObj != null) {
			if (ret.length() > initialLength) {
				ret.append(",").append(System.lineSeparator());
			}
			ret.append("submenu: ").append(subMenuObj);
		} else if (!subMenu.isEmpty()) {
			if (ret.length() > initialLength) {
				ret.append(",").append(System.lineSeparator());
			}
			ret.append("submenu: {").append(System.lineSeparator());
			boolean first = true;
			for (Entry<String, JsTreeContextMenuItem> sme: subMenu.entrySet()) {
				if (!first) {
					ret.append(",").append(System.lineSeparator());
				}
				ret.append(sme.getKey()).append(": ").append(sme.getValue());
				first = false;
			}
			ret.append("}");
		}
		ret.append(System.lineSeparator()).append("}");
		return ret.toString();
	}

}
