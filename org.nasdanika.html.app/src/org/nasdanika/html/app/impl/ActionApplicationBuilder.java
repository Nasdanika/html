package org.nasdanika.html.app.impl;

import java.util.List;
import java.util.function.Consumer;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLElement.Event;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ScriptActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.DeviceSize;
import org.nasdanika.html.bootstrap.Dropdown;
import org.nasdanika.html.bootstrap.Navbar;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.knockout.KnockoutFactory;

/**
 * Builds an application from actions. Provides default implementations of protected "generateXXX" methods.
 * Override as needed.
 * @author Pavel Vlasov
 *
 */
public class ActionApplicationBuilder implements ApplicationBuilder {

	protected Action rootAction;
	protected Action principalAction;
	protected List<? extends Action> leftPanelActions;
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
	 * @param principal Principal context actions are generated as the nav bar. Its child actions are generated in the left menu.
	 * @param active Active action to be executed and execution result to be shown in the content container. If active action is shown in the top or left navigation is shall
	 * be selected. 
	 */
	public ActionApplicationBuilder(Action rootAction, Action principalAction, Action activeAction) {
		this(rootAction, principalAction, principalAction.getChildren(), activeAction);
	}	
	
	/**
	 * 
	 * @param root Root action label is output in the header, its context actions are output in the footer.
	 * @param principal Principal context actions are generated as the nav bar.
	 * @param leftPanelActions Actions to show in the left menu. 
	 * @param active Active action to be executed and execution result to be shown in the content container. If active action is shown in the top or left navigation is shall
	 * be selected. 
	 */
	public ActionApplicationBuilder(
			Action rootAction, 
			Action principalAction, 
			List<? extends Action> leftPanelActions, 
			Action activeAction) {
		this.rootAction = rootAction;
		this.principalAction = principalAction;
		this.leftPanelActions = leftPanelActions;
		this.activeAction = activeAction;
	}
	
	@Override
	public void build(Application application) {
		// Active action shall be executed first before any other building as it may change the state of application.
		// Generally, however, the action model would be built after execution of state changing actions and 
		// action.execute() would simply return the result.
		Object result = activeAction.execute(); 
		
		Fragment contentFragment = getHTMLFactory().fragment();
		application.getHTMLPage().body(contentFragment);
		ViewGenerator viewGenerator = createViewGenerator(contentFragment::content);
		application.header(generateHeader(viewGenerator, result));
		application.navigationBar(generateNavigationBar(viewGenerator, result));
		application.leftPanel(generateLeftPanel(viewGenerator, result));
		application.content(generateContent(viewGenerator, result));
		application.footer(generateFooter(viewGenerator, result));
	}
	
	protected Object generateHeader(ViewGenerator viewGenerator, Object result) {	
		return getBootstrapFactory().display(viewGenerator.link(rootAction), 4);
	}
	
	protected Object generateNavigationBar(ViewGenerator viewGenerator, Object result) {
		if (principalAction == null) {
			return null;
		}
		
		Tag brand = principalAction.getActivator() == null ? viewGenerator.label(principalAction) : viewGenerator.link(principalAction);
		Navbar navBar = createNavbar(brand);
		
		for (Action ca: principalAction.getContextActions()) {
			// Children are ignored if activator is not null.
			Fragment fragment = getHTMLFactory().fragment();
			viewGenerator.label(ca, fragment::content);
			ActionActivator activator = ca.getActivator();
			if (activator instanceof NavigationActionActivator) {
				navBar.item(((NavigationActionActivator) activator).getHref(), ca == activeAction, ca.isDisabled(), fragment);
			} else if (activator instanceof ScriptActionActivator) {
				navBar.item("#", ca == activeAction, ca.isDisabled(), fragment).on(Event.click, ((ScriptActionActivator) activator).getCode());				
			} else if (ca.getChildren().isEmpty()) {
				// As text
				navBar.navbarText(fragment);
			} else {
				Dropdown dropdown = navBar.dropdown(fragment);
				for (Action cac: ca.getChildren()) {
					Fragment cFragment = getHTMLFactory().fragment();
					viewGenerator.label(cac, cFragment::content);
					ActionActivator cActivator = cac.getActivator();
					if (cActivator instanceof NavigationActionActivator) {
						dropdown.item(((NavigationActionActivator) cActivator).getHref(), cac == activeAction, ca.isDisabled(), cFragment);
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
	protected Navbar createNavbar(HTMLElement<?> brand) {
		return getBootstrapFactory().navbar(DeviceSize.LARGE, false, Color.LIGHT, brand);
	}
	
	protected Object generateLeftPanel(ViewGenerator viewGenerator, Object result) {
		// TODO Auto-generated method stub
		return "Left panel";
	}

	protected Object generateContent(ViewGenerator viewGenerator, Object result) {
		// TODO Auto-generated method stub
		return "Content: "+result;
	}

	protected Object generateFooter(ViewGenerator viewGenerator, Object result) {
		// TODO Auto-generated method stub
		return "Footer";
	}

	protected HTMLFactory getHTMLFactory() {
		return HTMLFactory.INSTANCE;
	}
		
	protected BootstrapFactory getBootstrapFactory() {
		return BootstrapFactory.INSTANCE;
	}
		
	protected JsTreeFactory getJsTreeFactory() {
		return JsTreeFactory.INSTANCE;
	}
	
	protected KnockoutFactory getKnockoutFactory() {
		return KnockoutFactory.INSTANCE;
	}
	
	protected FontAwesomeFactory getFontAwesomeFactory() {
		return FontAwesomeFactory.INSTANCE;
	}
	
	protected ViewGenerator createViewGenerator(Consumer<?> contentConsumer) {
		return new ViewGeneratorImpl(contentConsumer);
	}
	
	// TODO - C3 once available.

}
