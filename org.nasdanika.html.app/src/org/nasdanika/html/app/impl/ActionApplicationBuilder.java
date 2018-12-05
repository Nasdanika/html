package org.nasdanika.html.app.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.Consumer;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLElement.Event;
import org.nasdanika.html.NamedItemsContainer;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ScriptActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.ActionGroup;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breadcrumbs;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.DeviceSize;
import org.nasdanika.html.bootstrap.Dropdown;
import org.nasdanika.html.bootstrap.Navbar;
import org.nasdanika.html.bootstrap.Navs;

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
	protected Map<String,Object> input;

	/**
	 * Creates an application builder with the active action's root action as the root and
	 * the next child as the principal. This constructor shall be used only for
	 * actions with path length greater or equal to 2.
	 * @param activeAction
	 */
	public ActionApplicationBuilder(Action activeAction, Map<String,Object> input) {
		this(activeAction.getPath().get(0), activeAction.getPath().get(1), activeAction, input);
	}
	
	/**
	 * @param root Root action label is output in the header, its context actions are output in the footer.
	 * @param principal Principal context actions are generated as the navigation bar. Its child actions are generated in the navigation panel.
	 * @param active Active action to be executed and execution result to be shown in the content container. If active action is shown in the navigation bar or panel is shall
	 * be selected/active. 
	 */
	public ActionApplicationBuilder(Action rootAction, Action principalAction, Action activeAction, Map<String,Object> input) {
		this(rootAction, principalAction, principalAction.getChildren(), activeAction, input);
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
			Action activeAction,
			Map<String,Object> input) {
		this.rootAction = rootAction;
		this.principalAction = principalAction;
		this.navigationPanelActions = navigationPanelActions;
		this.activeAction = activeAction;
		this.input = input;
	}
	
	@Override
	public void build(Application application) {
		// A little trick to solve the chicken and egg problem
		Fragment[] cf = { null };
		ViewGenerator viewGenerator = createViewGenerator(content-> cf[0].accept(content));
		
		Fragment contentFragment = viewGenerator.getHTMLFactory().fragment();
		cf[0] = contentFragment;
		application.getHTMLPage().body(contentFragment);
		
		// The action model would be built after execution of state changing actions and 
		// action.execute() would simply return the result.
		
		application.header(generateHeader(viewGenerator));
		application.navigationBar(generateNavigationBar(viewGenerator));
		application.navigationPanel(generateNavigationPanel(viewGenerator));
		application.contentPanel(generateContentPanel(viewGenerator));
		application.footer(generateFooter(viewGenerator));
	}
	
	protected Object generateHeader(ViewGenerator viewGenerator) {	
		return viewGenerator.getBootstrapFactory().display(viewGenerator.link(rootAction), 4);
	}
	
	protected Object generateNavigationBar(ViewGenerator viewGenerator) {
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
				navBar.item(((NavigationActionActivator) activator).getUrl(), equalOrInPath(ca), ca.isDisabled(), fragment);
			} else if (activator instanceof ScriptActionActivator) {
				navBar.item("#", equalOrInPath(ca), ca.isDisabled(), fragment).on(Event.click, ((ScriptActionActivator) activator).getCode());				
			} else if (ca.getChildren().isEmpty()) {
				// As text
				navBar.navbarText(fragment);
			} else {
				Dropdown dropdown = navBar.dropdown(equalOrInPath(ca), fragment);
				for (Action cac: ca.getChildren()) {
					dropdown.item(viewGenerator.link(cac), equalOrInPath(cac), ca.isDisabled());
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
	
	protected Object generateNavigationPanel(ViewGenerator viewGenerator) {
		ActionGroup actionGroup = viewGenerator.getBootstrapFactory().actionGroup(true);
		for (Action child: principalAction.getChildren()) {
			viewGenerator.add(actionGroup, child, equalOrInPath(child));
		}
		return actionGroup;
	}

	protected Object generateContentPanel(ViewGenerator viewGenerator) {
		Fragment ret = viewGenerator.getHTMLFactory().fragment();
		Action lastNonSection = lastNonSection();
		// Breadcrumbs
		if (lastNonSection.getPath().size() > 2) {
			Breadcrumbs breadcrumbs = viewGenerator.getBootstrapFactory().breadcrums();
			breadcrumbs.margin().top(1);
			ret.content(breadcrumbs);
			ListIterator<Action> tit = lastNonSection.getPath().listIterator(2);
			while (tit.hasNext()) {
				breadcrumbs.item(false, viewGenerator.link(tit.next()));
			}		
			breadcrumbs.item(true, viewGenerator.label(lastNonSection));
		}
		ret.content(viewGenerator.label(lastNonSection, viewGenerator.getHTMLFactory().tag(TagName.h2)));
		ret.content(lastNonSection.execute(viewGenerator, input));

		// Context actions
		Tag buttonContainer = viewGenerator.getHTMLFactory().nonEmptyDiv();
		ret.content(buttonContainer);
		for (Action ca: lastNonSection.getContextActions()) {
			BootstrapElement<?, ?> button = viewGenerator.button(ca);
			button.margin().right(1).top(1).bottom(1);
			buttonContainer.content(button);
		}
				
		// Sections
		List<? extends Action> sections = lastNonSection.getSections();
		if (!sections.isEmpty()) {
			sections(sections, viewGenerator, 0, ret);
		}
		return ret;
	}
	
	/**
	 * Generates (nested) sections.
	 * @param sections
	 * @param level
	 * @return
	 */
	protected void sections(List<? extends Action> sections, ViewGenerator viewGenerator, int level, Consumer<Object> contentConsumer) {		
		NamedItemsContainer sectionsContainer = createSectionsContainer(viewGenerator, contentConsumer, level);
		Action activeSection = null;
		for (Action section: sections) {
			if (equalOrInPath(section)) {
				activeSection = section;
			}
		}
		
		for (Action section: sections) {
			if (activeSection == null) {
				activeSection = section; // First if null.
			}
			Fragment sectionContent = viewGenerator.getHTMLFactory().fragment(section.execute(viewGenerator, input));

			// Context actions
			Tag buttonContainer = viewGenerator.getHTMLFactory().nonEmptyDiv();
			sectionContent.content(buttonContainer);
			for (Action ca: section.getContextActions()) {
				BootstrapElement<?, ?> button = viewGenerator.button(ca);
				button.margin().right(1).top(1).bottom(1);
				buttonContainer.content(button);
			}
					
			// Sections
			List<? extends Action> subSections = section.getChildren();
			if (!subSections.isEmpty()) {
				sections(subSections, viewGenerator, level + 1, sectionContent);
			}
			
			String contentId = section.getId() == null ? null : "nsd-action-content-"+section.getId();
			Fragment labelFragment = viewGenerator.labelFragment(section);
			if (sectionsContainer instanceof ActionGroup) {
				((ActionGroup) sectionsContainer).contentAction(labelFragment, section == activeSection, section.isDisabled(), section.getColor(), contentId, sectionContent);				
			} else if (sectionsContainer instanceof Navs) {
				((Navs) sectionsContainer).item(labelFragment, section == activeSection, section.isDisabled(), contentId, sectionContent);
			} else {
				sectionsContainer.item(labelFragment, sectionContent);
			}
		}
	}
	
	/**
	 * Creates section container for the specified level.
	 * This implementation creates tabs for level 0, content action group for level 1, and header containers for levels 2 and above starting 
	 * with header 3 going up to 6. 
	 * @param viewGenerator
	 * @param contentConsumer
	 * @param level
	 * @return Section container which is already added to the content consumer.
	 */
	protected NamedItemsContainer createSectionsContainer(ViewGenerator viewGenerator, Consumer<Object> contentConsumer, int level) {
		switch (level) {
		case 0:
			Navs tabs = viewGenerator.getBootstrapFactory().tabs();
			contentConsumer.accept(tabs);
			return tabs;
		case 1:
			ActionGroup actionGroup = BootstrapFactory.INSTANCE.actionGroup(false);
			contentConsumer.accept(actionGroup.asContainer().margin().top(1).toBootstrapElement());
			return actionGroup;
		case 2:
			NamedItemsContainer ret = viewGenerator.getHTMLFactory().tagNamedItemsContainer(TagName.h3);
			contentConsumer.accept(ret);
			return ret;
		case 3:
			ret = viewGenerator.getHTMLFactory().tagNamedItemsContainer(TagName.h4);
			contentConsumer.accept(ret);
			return ret;
		case 4:
			ret = viewGenerator.getHTMLFactory().tagNamedItemsContainer(TagName.h5);
			contentConsumer.accept(ret);
			return ret;
		default:
			ret = viewGenerator.getHTMLFactory().tagNamedItemsContainer(TagName.h6);
			contentConsumer.accept(ret);
			return ret;
		}
	}
	
	/**
	 * Returns the last action from the action path or the action itself which is not a section action.
	 * @return
	 */
	protected Action lastNonSection() {
		List<Action> fullPath = new ArrayList<>(activeAction.getPath());
		fullPath.add(activeAction);
		for (int i = 0; i < fullPath.size() - 1; ++i) {
			Action currentAction = fullPath.get(i);
			if (contains(currentAction.getSections(), fullPath.get(i + 1))) {
				return currentAction;
			}
		}
		return activeAction;
	}

	protected Object generateFooter(ViewGenerator viewGenerator) {
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
	
	/**
	 * Compares actions by id and then using equals.
	 * @param a1
	 * @param a2
	 * @return
	 */
	protected boolean equal(Action a1, Action a2) {
		if (a1 == a2) {
			return true;
		}
		if (a1 == null || a2 == null) {
			return false;
		}
		if (a1.getId() != null && a1.getId().equals(a2.getId())) {
			return true;
		}
		return a1.equals(a2);
	}
	
	protected boolean contains(Collection<? extends Action> collection, Action action) {
		for (Action e: collection) {
			if (equal(e, action)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns true if the parameter action is equal to the active action or is in its path.
	 * @param a
	 * @return
	 */
	protected boolean equalOrInPath(Action a) {
		return equal(activeAction, a) || contains(activeAction.getPath(), a);
	}
	
	// TODO - C3 once available.

}
