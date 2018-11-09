package org.nasdanika.html.jstree.impl;

import org.nasdanika.html.jstree.JsTreeContextMenuItem;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;

/**
 * HTML factory which relies on Bootstrap styles and scripts.
 * @author Pavel
 *
 */
public class DefaultJsTreeFactory implements JsTreeFactory {
		
	@Override
	public JsTreeNode jsTreeNode() {
		return new JsTreeNodeImpl();
	}
	
	@Override
	public JsTreeContextMenuItem jsTreeContextMenuItem() {
		return new JsTreeContextMenuItemImpl();
	}
	
}
