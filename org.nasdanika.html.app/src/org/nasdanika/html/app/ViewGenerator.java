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
	void label(Action action, Consumer<Object> contentConsumer);
	
	/**
	 * Outputs icon, text, 
	 * @param action
	 * @param container
	 * @return
	 */
	Tag label(Action action, Tag container);	
	
	Tag label(Action action);
	
	Tag link(Action action);
	
	Tag badge(Action action, boolean isPill);
	
	Button<org.nasdanika.html.Button> button(Action action);
	
	ButtonGroup buttonGroup(Iterable<Action> actions);
	
	ButtonGroup buttonGroup(Action... action);
	
	/**
	 * A button with only the action icon if action has an icon.
	 * Action name is rendered as a prefix to the tooltip.
	 * @param action
	 * @return
	 */
	Button<org.nasdanika.html.Button> iconButton(Action action);	
	
	ButtonGroup iconButtonGroup(Iterable<Action> actions);
	
	ButtonGroup iconButtonGroup(Action... action);
	
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
