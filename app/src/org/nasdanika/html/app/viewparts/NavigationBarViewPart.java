package org.nasdanika.html.app.viewparts;

import java.util.List;
import java.util.Map.Entry;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Event;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ScriptActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Dropdown;
import org.nasdanika.html.bootstrap.Navbar;

/**
 * Builds a navigation bar from the principal action. 
 * Uses the action as a brand and its context children to build a navigation menu.
 * @author Pavel
 *
 */
public class NavigationBarViewPart implements ViewPart {

	protected List<Entry<Label, List<Action>>> categoryGroups;
	protected Action activeAction;
	protected ViewPart brandPart;

	/**
	 * Builds a navigation bar view part from a list of actions and an active action.
	 * The actions are grouped into categories to build the navbar. 
	 * This constructor creates a navBar without brand.
	 * @param actions
	 * @param activeAction
	 */
	public NavigationBarViewPart(List<Action> actions, Action activeAction) {
		this.categoryGroups = Util.groupByCategory(actions);
		this.activeAction = activeAction;
	}
	
	/**
	 * Builds a navigation bar with a brand for a principal action. 
	 * @param principalAction
	 * @param activeAction
	 */
	public NavigationBarViewPart(Action principalAction, Action activeAction) {
		this(principalAction.getContextChildren(), activeAction);
		brandPart = (viewGenerator, progressMonitor) -> principalAction.getActivator() == null ? viewGenerator.label(principalAction) : viewGenerator.link(principalAction);
	}	

	@SuppressWarnings("unchecked")
	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		Tag brand = brandPart == null ? null : (Tag) brandPart.generate(viewGenerator, progressMonitor);
		Navbar navBar = createNavbar(viewGenerator, brand);
		boolean hasContent = brand != null && !brand.isEmpty();
		
		for (Entry<Label, ?> categoryGroup: categoryGroups) {
			Label category = categoryGroup.getKey();
			if (category == null || (Util.isBlank(category.getText()) && Util.isBlank(category.getIcon()))) {
				for (Action ca: (List<Action>) categoryGroup.getValue()) {
					hasContent = true;
					// Children are ignored if activator is not null.
					Fragment fragment = viewGenerator.get(HTMLFactory.class).fragment();
					viewGenerator.label(ca, fragment::content);
					ActionActivator activator = ca.getActivator();
					if (activator instanceof NavigationActionActivator) {
						Tag item = navBar.item(((NavigationActionActivator) activator).getUrl(), Util.equalOrInPath(activeAction, ca), ca.isDisabled(), fragment);
						if (ca.getConfirmation() != null) {
							item.on(Event.click, "return confirm('"+ca.getConfirmation()+"');");
						}
					} else if (activator instanceof ScriptActionActivator) {
						String code = ((ScriptActionActivator) activator).getCode();
						if (ca.getConfirmation() != null) {
							code = "if (confirm('"+ca.getConfirmation()+"')) { "+code+" }";
						}
						navBar.item("#", Util.equalOrInPath(activeAction, ca), ca.isDisabled(), fragment).on(Event.click, code);				
					} else if (ca.getChildren().isEmpty()) {
						// As text
						navBar.navbarText(fragment);
					} else {
						Dropdown dropdown = navBar.dropdown(Util.equalOrInPath(activeAction, ca), fragment);				
						for (Entry<Label, List<Action>> cats: ca.getChildrenGroupedByCategory()) {
							if (cats.getKey() != null) {
								if (Util.isBlank(cats.getKey().getIcon()) && Util.isBlank(cats.getKey().getText())) {
									dropdown.divider();
								} else {
									dropdown.header(viewGenerator.labelFragment(cats.getKey()));
								}
							}
							for (Action cac: cats.getValue()) {	
								dropdown.item(viewGenerator.link(cac), Util.equalOrInPath(activeAction, cac), cac.isDisabled());
							}
						}
					}
				}
			} else {
				Dropdown dropdown = navBar.dropdown(false, viewGenerator.labelFragment(category));
				for (Action cac: (List<Action>) categoryGroup.getValue()) {	
					dropdown.item(viewGenerator.link(cac), Util.equalOrInPath(activeAction, cac), cac.isDisabled());
				}
			}
		}
		
		return hasContent ? navBar : null;
	}
	
	/**
	 * Creates navbar. Override to customize Navbar parameters such as background.
	 * @param brand 
	 * @param brand
	 * @return
	 */
	protected Navbar createNavbar(ViewGenerator viewGenerator, HTMLElement<?> brand) {
		return viewGenerator.get(BootstrapFactory.class).navbar(Breakpoint.LARGE, false, Color.LIGHT, brand);
	}
	

}
