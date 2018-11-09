package org.nasdanika.html.jstree;

import org.nasdanika.html.jstree.impl.DefaultJsTreeFactory;

public interface JsTreeFactory {
	
	JsTreeFactory INSTANCE = new DefaultJsTreeFactory();
	
	public JsTreeNode jsTreeNode();
	
	public JsTreeContextMenuItem jsTreeContextMenuItem();
	
}
