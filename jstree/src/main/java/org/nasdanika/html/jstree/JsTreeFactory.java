package org.nasdanika.html.jstree;

import org.json.JSONObject;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.Tag;
import org.nasdanika.html.jstree.impl.DefaultJsTreeFactory;

public interface JsTreeFactory {
	
	JsTreeFactory INSTANCE = new DefaultJsTreeFactory(HTMLFactory.INSTANCE);
	
	JsTreeNode jsTreeNode();
	
	JsTreeContextMenuItem jsTreeContextMenuItem();
	
	/**
	 * Add JsTree stylesheet and script elements referencing CDN locations. Does not add jQuery. 
	 * @param page
	 * @return
	 */
	<P extends HTMLPage> P cdn(P page);
	
	/**
	 * Builds JsTree object from a list of root nodes.
	 * @param roots
	 * @return
	 */
	JSONObject buildJsTree(Iterable<JsTreeNode> roots);
	
	/**
	 * Builds JsTree object from a list of root nodes.
	 * @param roots
	 * @return
	 */
	JSONObject buildJsTree(JsTreeNode... roots);
	
	
	/**
	 * Builds JsTree object for loading nodes and context menus using AJAX from the specified URL's.
	 * Node id is passed to the server side in "id" request parameter.
	 * @param nodesUrl URL to load nodes.
	 * @param contextMenuUrl URL to load context menus. Can be null. 
	 * @return String representation because JSON does not support functions.
	 */
	String buildAjaxJsTree(String nodesUrl, String contextMenuUrl);
	
	/**
	 * Generates script tag for binding jsTree to html element.
	 * @param selector
	 * @param jsTree
	 * @param filter Filter script to manipulate jsTree object before passing it to jsTree. It can be used to add functions to jsTree, which are not supported by JSON. 
	 * The filter script can access jsTree via <code>tree</code> variable.
	 * @return
	 */
	Tag bind(String selector, Object jsTree, Object filter);
	
	/**
	 * Generates script tag for binding jsTree to html element.
	 * @param selector
	 * @param jsTree
	 * @param filter Filter script to manipulate jsTree object before passing it to jsTree. It can be used to add functions to jsTree, which are not supported by JSON. 
	 * The filter script can access jsTree via <code>tree</code> variable.
	 * @return
	 */
	Tag bind(HTMLElement<?> htmlElement, Object jsTree, Object filter);
	
	HTMLFactory getHTMLFactory();	
	
}
