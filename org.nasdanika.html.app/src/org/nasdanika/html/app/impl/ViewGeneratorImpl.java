package org.nasdanika.html.app.impl;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLElement.Event;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.NamedItemsContainer;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.BindingActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ScriptActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.ActionGroup;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Button;
import org.nasdanika.html.bootstrap.ButtonGroup;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.ListGroup;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.jstree.JsTreeContextMenuItem;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;
import org.nasdanika.html.knockout.KnockoutFactory;

public class ViewGeneratorImpl implements ViewGenerator {
	
	protected Consumer<?> contentConsumer;

	public ViewGeneratorImpl(Consumer<?> contentConsumer) {
		this.contentConsumer = contentConsumer;
	}
	
	@Override
	public HTMLFactory getHTMLFactory() {
		return HTMLFactory.INSTANCE;
	}
	
	@Override
	public BootstrapFactory getBootstrapFactory() {
		return BootstrapFactory.INSTANCE;
	}
	
	@Override
	public KnockoutFactory getKnockoutFactory() {
		return KnockoutFactory.INSTANCE;
	}
		
	@Override
	public FontAwesomeFactory getFontAwesomeFactory() {
		return FontAwesomeFactory.INSTANCE;
	}	
	
	@Override
	public JsTreeFactory getJsTreeFactory() {
		return JsTreeFactory.INSTANCE;
	}

	@Override
	public Tag link(Action action) {
		Tag ret = label(action, getHTMLFactory().tag(TagName.a));
		bindLink(action, ret);
		return ret;
	}
	
	protected void bindLink(Action action, HTMLElement<?> anchor) {
		// TODO - confirmation.
		ActionActivator activator = getActionActivator(action);
		if (activator instanceof NavigationActionActivator) {
			anchor.attribute("href", ((NavigationActionActivator) activator).getUrl());
		} else if (activator instanceof ScriptActionActivator) {
			anchor.on(Event.click, ((ScriptActionActivator) activator).getCode());
		} else if (activator instanceof BindingActionActivator) {
			((BindingActionActivator) activator).bind(anchor);
		}		
	}
	
	protected void bind(Action action, HTMLElement<?> element) {
		ActionActivator activator = getActionActivator(action);
		if (activator instanceof NavigationActionActivator) {
			element.on(Event.click, "location.href='"+((NavigationActionActivator) activator).getUrl()+"'");
		} else if (activator instanceof ScriptActionActivator) {
			element.on(Event.click, ((ScriptActionActivator) activator).getCode());
		} else if (activator instanceof BindingActionActivator) {
			((BindingActionActivator) activator).bind(element);
		}				
	}

	/**
	 * This implementation returns action.getActivator(). 
	 * Override to customize, e.g. resolve by action id.
	 * @param action
	 * @return
	 */
	protected ActionActivator getActionActivator(Action action) {
		return action.getActivator();
	}

	@Override
	public Tag badge(Label label, boolean isPill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Button<org.nasdanika.html.Button> button(Action action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ButtonGroup buttonGroup(Iterable<Action> actions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ButtonGroup buttonGroup(Action... action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsTreeContextMenuItem jsTreeContextMenuItem(Action action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JsTreeNode jsTreeNode(Action action, boolean ajax) {
		JsTreeNode ret = getJsTreeFactory().jsTreeNode();
		
		ret.icon(action.getIcon());
		ret.text(action.getText());
		ret.anchorAttribute("title", action.getTooltip());
		ret.id(action.getId());
		ret.disabled(action.isDisabled());
		if (ajax) {
			if (!action.getChildren().isEmpty()) {
				ret.hasChildren();
			}
		} else {
			for (Action child: action.getChildren()) {
				ret.children().add(jsTreeNode(child, ajax));
			}
		}
		
		return ret;
	}

	@Override
	public void label(Label label, Consumer<Object> contentConsumer) {
		String icon = label.getIcon();
		if (icon != null) {
			Tag iconTag;
			if (icon.contains("/")) {
				// Image
				iconTag = getHTMLFactory().tag(TagName.img).attribute("src", icon).style().height("1em");
			} else {
				// Class
				iconTag = getHTMLFactory().span().addClass(icon);
			}
			if (label.getText() != null) {
				iconTag.style().margin().right("0.3em");
			}
			contentConsumer.accept(iconTag);
		}		
			
		if (label.getText() != null) {
			contentConsumer.accept(label.getText());
		}	
		
		if (label.getNotification() != null) {
			Tag badge = getBootstrapFactory().badge(true, label.getColor() == Color.PRIMARY ? Color.SECONDARY : Color.PRIMARY, label.getNotification());
			badge.style().margin().left("0.3em");
			contentConsumer.accept(badge);
//			getBootstrapFactory().wrap(badge)._float().right();
		}
		
		// TODO - help content - tooltip, icon, modals, ...
				
	}
	
	@Override
	public Tag label(Label label, Tag container) {
		label(label, container::content);
		if (label instanceof Action) {
			container.addClass("nsd-action");
			container.attribute("data-nsd-action", ((Action) label).getId());
		}
		return container;
	}
	
	@Override
	public Tag label(Label label) {
		return label(label, getHTMLFactory().span());
	}
	
	@Override
	public Fragment labelFragment(Label label) {
		Fragment ret = getHTMLFactory().fragment();
		label(label, ret::content);
		return ret;
	}

	@Override
	public void add(NamedItemsContainer container, Action action) {
		add(container, action, Collections.emptyMap());	
	}

	@Override
	public void add(NamedItemsContainer container, Action action, Map<String,Object> input) {
		container.item(labelFragment(action), action.execute(this, input));		
	}

	@Override
	public Tag add(ListGroup listGroup, Label label, boolean active, boolean disabled) {
		return listGroup.item(active, disabled, label.getColor(), labelFragment(label));
	}

	@Override
	public Tag add(ListGroup listGroup, Action action, boolean active) {
		return add(listGroup, action, active, action.isDisabled());
	}

	@Override
	public Tag add(ActionGroup actionGroup, Action action, boolean active) {
		Tag link = actionGroup.action(active, action.isDisabled(), action.getColor(), null, labelFragment(action));
		bindLink(action, link);
		return link;
	}

	@Override
	public Tag addContent(ActionGroup actionGroup, Action action, boolean active) {
		return addContent(actionGroup, action, active, Collections.emptyMap());
	}

	@Override
	public void add(Navs navs, Action action, boolean active) {
		add(navs, action, active, Collections.emptyMap());
	}

	@Override
	public Tag addContent(ActionGroup actionGroup, Action action, boolean active, Map<String,Object> input) {
		String contentId = action.getId() == null ? null : "nsd-action-content-"+action.getId();
		return actionGroup.contentAction(labelFragment(action), active, action.isDisabled(), action.getColor(), contentId, action.execute(this, input));
	}

	@Override
	public void add(Navs navs, Action action, boolean active, Map<String,Object> input) {
		String contentId = action.getId() == null ? null : "nsd-action-content-"+action.getId();
		navs.item(labelFragment(action), active, action.isDisabled(), contentId, action.execute(this, input));
	}
	
}
