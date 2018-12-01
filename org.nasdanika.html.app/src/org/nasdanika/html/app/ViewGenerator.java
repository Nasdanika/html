package org.nasdanika.html.app;

import java.util.function.Consumer;

import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.Button;
import org.nasdanika.html.bootstrap.ButtonGroup;
import org.nasdanika.html.jstree.JsTreeContextMenuItem;
import org.nasdanika.html.jstree.JsTreeNode;

/**
 * Generates UI elements from application interfaces.
 * @author Pavel Vlasov
 *
 */
public interface ViewGenerator {
	
	/**
	 * Outputs icon, text, help tooltip icon to the content consumer.
	 * @param action
	 * @param contentConsumer
	 */
	void label(Label label, Consumer<Object> contentConsumer);
	
	/**
	 * Outputs icon, text, tooltip.
	 * Adds "nsd-action" class and "data-nds-action" attribute with action id as value, if id is not null.
	 * @param action
	 * @param container
	 * @return
	 */
	Tag label(Label label, Tag container);	
	
	Tag label(Label label);
	
	Tag link(Action action);
	
	
	// --- TODO ---
	
	Tag badge(Label action, boolean isPill);
	
	Button<org.nasdanika.html.Button> button(Action action);
	
	ButtonGroup buttonGroup(Iterable<Action> actions);
	
	ButtonGroup buttonGroup(Action... action);
	
	JsTreeContextMenuItem jsTreeContextMenuItem(Action action);
	
	/**
	 * 
	 * @param action Action to build node from.
	 * @param ajax If true the children key is set to 'true' instead of array of children to load children using ajax.
	 * @return
	 */
	JsTreeNode jsTreeNode(Action action, boolean ajax);
	
	/*
	 *  TODO:
	 *  - Navs, Navbar - for principal actions.
	 *  - Lists 
	 *  - List groups
	 *  - JsTree
	 */
	
	// TODO - jsTree
	
	// TO
	

}
