package org.nasdanika.html.app.impl;

public class ActionApplication {
			
	/*
	 * TODO - builds Application from 3 actions - root, principal, and selected
	 * root action label is shown in the header and context actions in the footer
	 * principal action context actions are shown in the nav bar along with the link as branding
	 * principal action children are shown in the left menu.
	 * 
	 * Selected action is selected in the left menu and executed to produce content.
	 * 
	 * Default action execution shall execute the action, render its context actions below and then
	 * child actions in "section" role in sections below the context actions. Sections shall be rendered as
	 * tabs by default. child action section shall contain result of action execution and its context actions below.
	 * 
	 * breadcrumbs and action label shall be rendered above action content if action is not equal to the principal action.
	 * 
	 * If selected action is null then the principal action is executed.
	 * 
	 * One of constructors/invocations shall take one action and treat it as selected if its path length is > 1. In this case
	 * the path root becomes root and the next element becomes the principal action.
	 * 
	 * If path length is 1 then principal and selected actions are the same and content shows result of execution of the principal
	 * action without breadcrumbs, title and context actions as they are rendered in the navbar. Sections shall not be rendered by default 
	 * for principal actions. In other words, only execution result shall be rendered for principal actions.
	 * 
	 * If principal action is themed and returns non-null theme then that theme shall be used for the application.
	 * Otherwise the theme from the root action shall be used, is that action is themed and returns non-null theme.
	 */

}
