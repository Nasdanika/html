package org.nasdanika.html.app.impl;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Tag;
import org.nasdanika.html.HTMLElement.Event;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ScriptActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.DeviceSize;
import org.nasdanika.html.bootstrap.Dropdown;
import org.nasdanika.html.bootstrap.Navbar;

public class NavigationBarViewPart implements ViewPart {

	protected Action principalAction;
	protected Action activeAction;

	public NavigationBarViewPart(Action principalAction, Action activeAction) {
		this.principalAction = principalAction;
		this.activeAction = activeAction;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator) {
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
				navBar.item(((NavigationActionActivator) activator).getUrl(), Util.equalOrInPath(activeAction, ca), ca.isDisabled(), fragment);
			} else if (activator instanceof ScriptActionActivator) {
				navBar.item("#", Util.equalOrInPath(activeAction, ca), ca.isDisabled(), fragment).on(Event.click, ((ScriptActionActivator) activator).getCode());				
			} else if (ca.getChildren().isEmpty()) {
				// As text
				navBar.navbarText(fragment);
			} else {
				Dropdown dropdown = navBar.dropdown(Util.equalOrInPath(activeAction, ca), fragment);
				for (Action cac: ca.getChildren()) {
					dropdown.item(viewGenerator.link(cac), Util.equalOrInPath(activeAction, cac), ca.isDisabled());
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
	

}
