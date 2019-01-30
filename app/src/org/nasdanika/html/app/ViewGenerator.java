package org.nasdanika.html.app;

import java.util.function.Consumer;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.NamedItemsContainer;
import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.ActionGroup;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.ButtonToolbar;
import org.nasdanika.html.bootstrap.ListGroup;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.jstree.JsTreeContextMenuItem;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;
import org.nasdanika.html.knockout.KnockoutFactory;

/**
 * Provides access to factories and generates UI elements from application interfaces.
 * @author Pavel Vlasov
 *
 */
public interface ViewGenerator {
	
	// --- Access to factories ---
	
	HTMLFactory getHTMLFactory();
	
	BootstrapFactory getBootstrapFactory();
	
	FontAwesomeFactory getFontAwesomeFactory();
	
	KnockoutFactory getKnockoutFactory();
	
	JsTreeFactory getJsTreeFactory();
	
	// --- View part unwrapping ---
	
	/**
	 * If obj is {@link ViewPart} then its generate() method is invoked.
	 * @param obj
	 * @return
	 */
	Object processViewPart(Object obj);
	
	// --- Contribution to head and body
	
	/**
	 * This consumer adds content to the page body. 
	 * It can be used, for example, to declare a modal dialog.
	 * @return
	 */
	Consumer<?> getBodyContentConsumer();

	/**
	 * This consumer adds content to the page head.
	 * It can be used, for example, to add a script or a stylesheet.
	 * @return
	 */
	Consumer<?> getHeadContentConsumer();
	
	/**
	 * Outputs icon, text, help tooltip icon to the content consumer.
	 * @param action
	 * @param bodyContentConsumer
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
	
	/**
	 * Outputs label to a fragment.
	 * @param label
	 * @return
	 */
	Fragment labelFragment(Label label);
	
	Tag link(Action action);
	
	// --- List & action groups ---
	
	/**
	 * Adds a label to a list group.
	 * @param listGroup
	 * @param label
	 */
	Tag add(ListGroup listGroup, Label label, boolean active, boolean disabled);

	/**
	 * Adds a label to a list group.
	 * @param listGroup
	 * @param label
	 */
	Tag add(ListGroup listGroup, Action action, boolean active);
	
	/**
	 * Adds an action to action group.
	 * @param actionGroup
	 * @param action
	 * @return
	 */
	Tag add(ActionGroup actionGroup, Action action, boolean active);
	
	/**
	 * Adds an action to an action group with execution result as content.
	 * @param actionGroup
	 * @param action
	 * @return
	 */
	Tag addContent(ActionGroup actionGroup, Action action, boolean active);
	
	/**
	 * Adds an action to navs with execution result as content.
	 * @param actionGroup
	 * @param action
	 * @return
	 */
	void add(Navs navs, Action action, boolean active);
		
	/**
	 * Adds an action to a named items container with label as name and execution result as content. 
	 * @param container
	 * @param action
	 */
	void add(NamedItemsContainer container, Action action);	
	
	Tag badge(Label action, boolean isPill);
	
	/**
	 * Builds a button or a dropdown button if action has children.
	 * @param action
	 * @return
	 */
	BootstrapElement<?,?> button(Action action);
	
	<T extends Action> ButtonToolbar buttonToolbar(Iterable<T> actions);
	
	JsTreeContextMenuItem jsTreeContextMenuItem(Action action);
	
	/**
	 * 
	 * @param action Action to build node from.
	 * @param ajax If true the children key is set to 'true' instead of array of children to load children using ajax.
	 * @return
	 */
	JsTreeNode jsTreeNode(Action action, boolean ajax);
	
	/**
	 * Label node
	 * @param label
	 * @return
	 */
	JsTreeNode jsTreeNode(Label label);
	
	
	/*
	 *  TODO:
	 *  - Navs, Navbar - for principal actions.
	 *  - Lists 
	 *  - List groups
	 *  - JsTree
	 */
		
	// TODO - charts from PropertySources.
	
	 
	

}