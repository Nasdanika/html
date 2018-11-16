package org.nasdanika.html.jstree.impl;

import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.jstree.JsTreeContextMenuItem;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;

/**
 * HTML factory which relies on Bootstrap styles and scripts.
 * @author Pavel
 *
 */
public class DefaultJsTreeFactory implements JsTreeFactory {
	
	private HTMLFactory htmlFactory;

	public DefaultJsTreeFactory(HTMLFactory htmlFactory) {
		this.htmlFactory = htmlFactory;
	}
	
	@Override
	public HTMLFactory getHTMLFactory() {
		return htmlFactory;
	}
		
	@Override
	public JsTreeNode jsTreeNode() {
		return new JsTreeNodeImpl();
	}
	
	@Override
	public JsTreeContextMenuItem jsTreeContextMenuItem() {
		return new JsTreeContextMenuItemImpl();
	}

	@Override
	public <P extends HTMLPage> P cdn(P page) {
		page.stylesheet("https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css");
		page.script("https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js");
		return page;
	}

	@Override
	public JSONObject buildJsTree(Iterable<JsTreeNode> roots) {
		JSONObject ret = new JSONObject();
		JSONObject core = new JSONObject();
		ret.put("core", core);
		JSONArray jRoots = new JSONArray();
		for (JsTreeNode root: roots) {
			jRoots.put(root.toJSON());
		}
		core.put("data", jRoots);
		return ret;
	}
	
	@Override
	public JSONObject buildJsTree(JsTreeNode... roots) {
		return buildJsTree(Arrays.asList(roots));
	}

	@Override
	public String buildAjaxJsTree(String url) {
		return "{ 'core' : { 'data' : { 'url' : '"+url+"', 'data' : function (node) { return { 'id' : node.id }; } } } }";
	}

	@Override
	public Tag bind(String selector, Object jsTree) {
		return htmlFactory.nonEmptyTag(TagName.script, "$('"+selector+"').jstree("+jsTree+");");
	}

	@Override
	public Tag bind(HTMLElement<?> htmlElement, Object jsTree) {
		if (htmlElement.getId() == null) {
			htmlElement.id(htmlFactory.nextId());
		}
		return bind("#"+htmlElement.getId(), jsTree);
	}
	
}
