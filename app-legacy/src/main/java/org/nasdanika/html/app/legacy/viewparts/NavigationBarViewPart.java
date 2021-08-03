package org.nasdanika.html.app.viewparts;

import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Event;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ScriptActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Color;
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
					Fragment fragment = viewGenerator.getHTMLFactory().fragment();
					viewGenerator.label(ca, (Consumer<Object>) fragment::content);
					ActionActivator activator = ca.getActivator();
					if (activator instanceof NavigationActionActivator) {
						Tag item = navBar.item(((NavigationActionActivator) activator).getUrl(viewGenerator.getString(Context.BASE_URI_PROPERTY)), Util.equalOrInPath(activeAction, ca), ca.isDisabled(), fragment);
						if (!Util.isBlank(ca.getConfirmation())) {
							item.on(Event.click, "return confirm('"+ca.getConfirmation()+"');");
						}
						viewGenerator.decorate(item, ca);
//						if (ca.isFloatRight()) {
//							viewGenerator.getBootstrapFactory().wrap(item)._float().right();
//						}
					} else if (activator instanceof ScriptActionActivator) {
						String code = ((ScriptActionActivator) activator).getCode();
						if (!Util.isBlank(ca.getConfirmation())) {
							code = "if (confirm('"+ca.getConfirmation()+"')) { "+code+" }";
						}
						Tag item = navBar.item("#", Util.equalOrInPath(activeAction, ca), ca.isDisabled(), fragment).on(Event.click, code);
						viewGenerator.decorate(item, ca);
//						if (ca.isFloatRight()) {
//							viewGenerator.getBootstrapFactory().wrap(item)._float().right();
//						}
					} else if (ca.getChildren().isEmpty()) {
						// As text
						Tag item = navBar.navbarText(fragment);
						viewGenerator.decorate(item, ca);
//						if (ca.isFloatRight()) {
//							viewGenerator.getBootstrapFactory().wrap(item)._float().right();
//						}						
					} else {
						Dropdown dropdown = navBar.dropdown(Util.equalOrInPath(activeAction, ca), fragment);
						viewGenerator.decorate(dropdown, ca);
						for (Entry<Label, List<Action>> cats: ca.getChildrenGroupedByCategory()) {
							if (cats.getKey() != null) {
								if (Util.isBlank(cats.getKey().getIcon()) && Util.isBlank(cats.getKey().getText())) {
									viewGenerator.decorate(dropdown.divider(), cats.getKey());
								} else {
									viewGenerator.decorate(dropdown.header(viewGenerator.labelFragment(cats.getKey())), cats.getKey());
								}
							}
							for (Action cac: cats.getValue()) {	
								dropdown.item(viewGenerator.link(cac), Util.equalOrInPath(activeAction, cac), cac.isDisabled());
							}
						}
//						if (ca.isFloatRight()) {
//							dropdown._float().right();
//						}
					}
				}
			} else {
				Dropdown dropdown = navBar.dropdown(false, viewGenerator.labelFragment(category));
				viewGenerator.decorate(dropdown, category);
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
		return viewGenerator.getBootstrapFactory().navbar(Breakpoint.LARGE, false, Color.LIGHT, brand);
	}
	

}
