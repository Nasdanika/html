package org.nasdanika.html.app.impl;

import java.util.List;
import java.util.function.Consumer;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLElement.Event;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ScriptActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.ActionGroup;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.DeviceSize;
import org.nasdanika.html.bootstrap.Dropdown;
import org.nasdanika.html.bootstrap.Navbar;

/**
 * Builds an application from actions. Provides default implementations of protected "generateXXX" methods.
 * Override as needed.
 * @author Pavel Vlasov
 *
 */
public class ActionApplicationBuilder implements ApplicationBuilder {

	protected Action rootAction;
	protected Action principalAction;
	protected List<? extends Action> navigationPanelActions;
	protected Action activeAction;

	/**
	 * Creates an application builder with the active action's root action as the root and
	 * the next child as the principal. This constructor shall be used only for
	 * actions with path length greater or equal to 2.
	 * @param activeAction
	 */
	public ActionApplicationBuilder(Action activeAction) {
		this(activeAction.getPath().get(0), activeAction.getPath().get(1), activeAction);
	}
	
	/**
	 * @param root Root action label is output in the header, its context actions are output in the footer.
	 * @param principal Principal context actions are generated as the navigation bar. Its child actions are generated in the navigation panel.
	 * @param active Active action to be executed and execution result to be shown in the content container. If active action is shown in the navigation bar or panel is shall
	 * be selected/active. 
	 */
	public ActionApplicationBuilder(Action rootAction, Action principalAction, Action activeAction) {
		this(rootAction, principalAction, principalAction.getChildren(), activeAction);
	}	
	
	/**
	 * 
	 * @param root Root action label is output in the header, its context actions are output in the footer.
	 * @param principal Principal context actions are generated as the nav bar.
	 * @param navigationPanelActions Actions to show in the navigation panel. 
	 * @param active Active action to be executed and execution result to be shown in the content container. If active action is shown in the navigation bar or panel is shall
	 * be selected/active. 
	 */
	public ActionApplicationBuilder(
			Action rootAction, 
			Action principalAction, 
			List<? extends Action> navigationPanelActions, 
			Action activeAction) {
		this.rootAction = rootAction;
		this.principalAction = principalAction;
		this.navigationPanelActions = navigationPanelActions;
		this.activeAction = activeAction;
	}
	
	@Override
	public void build(Application application) {
		// A little trick to solve the chicken and egg problem
		Fragment[] cf = { null };
		ViewGenerator viewGenerator = createViewGenerator(content-> cf[0].accept(content));
		
		Fragment contentFragment = viewGenerator.getHTMLFactory().fragment();
		cf[0] = contentFragment;
		application.getHTMLPage().body(contentFragment);
		
		// Active action shall be executed first before any other building as it may change the state of the application.
		// Generally, however, the action model would be built after execution of state changing actions and 
		// action.execute() would simply return the result.
		Object result = activeAction.execute(viewGenerator); 
		
		application.header(generateHeader(viewGenerator, result));
		application.navigationBar(generateNavigationBar(viewGenerator, result));
		application.navigationPanel(generateNavigationPanel(viewGenerator, result));
		application.content(generateContent(viewGenerator, result));
		application.footer(generateFooter(viewGenerator, result));
	}
	
	protected Object generateHeader(ViewGenerator viewGenerator, Object result) {	
		return viewGenerator.getBootstrapFactory().display(viewGenerator.link(rootAction), 4);
	}
	
	protected Object generateNavigationBar(ViewGenerator viewGenerator, Object result) {
		if (principalAction == null) {
			return null;
		}
		
		Tag brand = principalAction.getActivator() == null ? viewGenerator.label(principalAction) : viewGenerator.link(principalAction);
		Navbar navBar = createNavbar(viewGenerator, brand);
		
		for (Action ca: principalAction.getContextActions()) {
			// Children are ignored if activator is not null.
			Fragment fragment = viewGenerator.getHTMLFactory().fragment();
			viewGenerator.label(ca, fragment::content);
			ActionActivator activator = ca.getActivator();
			if (activator instanceof NavigationActionActivator) {
				navBar.item(((NavigationActionActivator) activator).getUrl(), ca == activeAction, ca.isDisabled(), fragment);
			} else if (activator instanceof ScriptActionActivator) {
				navBar.item("#", ca == activeAction, ca.isDisabled(), fragment).on(Event.click, ((ScriptActionActivator) activator).getCode());				
			} else if (ca.getChildren().isEmpty()) {
				// As text
				navBar.navbarText(fragment);
			} else {
				Dropdown dropdown = navBar.dropdown(fragment);
				for (Action cac: ca.getChildren()) {
					Fragment cFragment = viewGenerator.getHTMLFactory().fragment();
					viewGenerator.label(cac, cFragment::content);
					ActionActivator cActivator = cac.getActivator();
					if (cActivator instanceof NavigationActionActivator) {
						dropdown.item(((NavigationActionActivator) cActivator).getUrl(), cac == activeAction, ca.isDisabled(), cFragment);
					} else if (cActivator instanceof ScriptActionActivator) {
						navBar.item("#", cac == activeAction, cac.isDisabled(), cFragment).on(Event.click, ((ScriptActionActivator) cActivator).getCode());				
					}
				}
			}
		}
		
		return navBar;
	}

	/**
	 * Creates navbar. Override to customize Navbar parameters such as background.
	 * @param brand
	 * @return
	 */
	protected Navbar createNavbar(ViewGenerator viewGenerator, HTMLElement<?> brand) {
		return viewGenerator.getBootstrapFactory().navbar(DeviceSize.LARGE, false, Color.LIGHT, brand);
	}
	
	protected Object generateNavigationPanel(ViewGenerator viewGenerator, Object result) {
		ActionGroup actionGroup = viewGenerator.getBootstrapFactory().actionGroup(true);
		for (Action child: principalAction.getChildren()) {
			viewGenerator.add(actionGroup, child, child == activeAction);
		}
		return actionGroup;
	}

	protected Object generateContent(ViewGenerator viewGenerator, Object result) {
		return result;
	}

	protected Object generateFooter(ViewGenerator viewGenerator, Object result) {
		// Single-level footer actions. 
		Fragment ret = viewGenerator.getHTMLFactory().fragment();
		for (Action ca: rootAction.getContextActions()) {
			if (!ret.isEmpty()) {
				ret.content("&nbsp;&bull;&nbsp;");
			}
			ret.content(viewGenerator.link(ca));
		}
		return ret;
	}
	
	protected ViewGenerator createViewGenerator(Consumer<?> contentConsumer) {
		return new ViewGeneratorImpl(contentConsumer);
	}
	
	// TODO - C3 once available.

}
