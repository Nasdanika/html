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
	 * Builds JsTree object for loading nodes using AJAX from the specified URL.
	 * @param url
	 * @return String representation because JSON does not support functions.
	 */
	String buildAjaxJsTree(String url);
	
	/**
	 * Generates script tag for binding jsTree to html element.
	 * @param selector
	 * @param jsTree
	 * @return
	 */
	Tag bind(String selector, Object jsTree);
	
	/**
	 * Generates script tag for binding jsTree to html element.
	 * @param selector
	 * @param jsTree
	 * @return
	 */
	Tag bind(HTMLElement<?> htmlElement, Object jsTree);
	
	HTMLFactory getHTMLFactory();
	
	
}
