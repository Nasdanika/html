package org.nasdanika.html.jstree.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.TokenSource;
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
	
	/**
	 * For the service loader
	 */
	public DefaultJsTreeFactory() {
		this(ServiceLoader.load(HTMLFactory.class).findFirst().get());
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
		page.stylesheet("https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.11/themes/default/style.min.css");
		page.script("https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.11/jstree.min.js");
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
	public String buildAjaxJsTree(String nodesUrl, String contextMenuUrl) {
		TokenSource tokens = getHTMLFactory().tokenSource("nodesUrl", nodesUrl).put("contextMenuUrl", contextMenuUrl);
		return getHTMLFactory().interpolate(DefaultJsTreeFactory.class.getResource(contextMenuUrl == null ? "ajaxTree.js" : "ajaxTreeWithContextMenu.js"), tokens);
	}

	@Override
	public Tag bind(String selector, Object jsTree, Object filter, Object searchInputSelector) {
		StringBuilder code = new StringBuilder("$(document).ready( function() {").append(System.lineSeparator());
	
		if (filter == null) {
			code.append("$('"+selector+"').jstree("+jsTree+");");
		} else {				
			code.append("$('"+selector+"').jstree(function(tree) { " + filter + " return tree; }(" + jsTree + "));");
		}
		
		if (searchInputSelector != null) {
			code.append(System.lineSeparator());
			Map<String,Object> tokens = new HashMap<>();
			tokens.put("searchInputSelector", searchInputSelector);
			tokens.put("timer", "window['nsd_jstTreeSearchTimer_" + getHTMLFactory().nextId() + "']");
			tokens.put("treeSelector", selector);
			code.append(getHTMLFactory().interpolate(DefaultJsTreeFactory.class.getResource("bindSearch.js"), tokens));			
		}
		
		code.append(System.lineSeparator()).append("});");
		return htmlFactory.nonEmptyTag(TagName.script, code.toString());
	}

	@Override
	public Tag bind(HTMLElement<?> htmlElement, Object jsTree, Object filter, Object searchInputSelector) {
		if (htmlElement.getId() == null) {
			htmlElement.id(htmlFactory.nextId());
		}
		return bind("#"+htmlElement.getId(), jsTree, filter, searchInputSelector);
	}
	
}
