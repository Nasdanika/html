package org.nasdanika.html;

import java.util.List;
import java.util.function.Predicate;

import org.json.JSONObject;

/**
 * This interface can be used to build JsTree JSON data. ``toString()`` method of the implementation outputs JSON.
 * @author Pavel Vlasov
 *
 */
public interface JsTreeNode extends JsTree {
	
	/**
	 * Used to traverse the tree and collect values.
	 * @author Pavel Vlasov
	 *
	 * @param <V>
	 */
	interface Collector<R> {
		
		R visit(JsTreeNode node, List<R> childResults);
		
	}
	
	<R> R accept(Collector<R> collector);
	
	JsTreeNode id(Object id);
	
	Object getId();
	
	JsTreeNode text(Object text);
	
	List<JsTreeNode> children();

	/**
	 * Attribute for the generated LI node.
	 * @param name
	 * @param value
	 * @return
	 */
	JsTreeNode listItemAttribute(String name, Object value);
	
	/**
	 * Attribute for the generated A node.
	 * @param name
	 * @param value
	 * @return
	 */
	JsTreeNode anchorAttribute(String name, Object value);
	
	JSONObject toJSON();
	
	/**
	 * Outputs to JSON only nodes which match the predicate or contain children matching the predicate.
	 * @param filter predicate or null.
	 * @return JSON object or null if there is no match.
	 */
	JSONObject toJSON(Predicate<JsTreeNode> filter);	
	
	/**
	 * Sets the application defined node data. This data is not used for rendering.
	 * Applications may put arbitrary objects in this field. 
	 * @param data
	 * @return this UI element.
	 */
	JsTreeNode setData(Object data);
	
	/**
	 * Returns the application defined node data, or null if it has not been set. 
	 * @return
	 */
	Object getData();
	
	/**
	 * Sets the application defined property of the node with the specified name to the given value.
	 * Applications may associate arbitrary objects with nodes. This data is not used for rendering.
	 * @param key
	 * @param data
	 * @return this UI element.
	 */
	JsTreeNode setData(String key, Object data);
	
	/**
	 * Returns the application defined property of the node with the specified name, or null if it has not been set.
	 * @param Key
	 * @return
	 */
	Object getData(String key);
	
	
}
