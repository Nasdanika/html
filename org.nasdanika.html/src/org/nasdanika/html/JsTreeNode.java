package org.nasdanika.html;

import java.util.List;

import org.json.JSONObject;

/**
 * This interface can be used to build JsTree JSON data. ``toString()`` method of the implementation outputs JSON.
 * @author Pavel Vlasov
 *
 */
public interface JsTreeNode extends JsTree {
	
	JsTree id(Object id);
	
	JsTree text(Object text);
	
	List<JsTreeNode> children();

	/**
	 * Attribute for the generated LI node.
	 * @param name
	 * @param value
	 * @return
	 */
	JsTree listItemAttribute(String name, Object value);
	
	/**
	 * Attribute for the generated A node.
	 * @param name
	 * @param value
	 * @return
	 */
	JsTree anchorAttribute(String name, Object value);
	
	JSONObject toJSON();
	
}
