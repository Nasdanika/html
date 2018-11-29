package org.nasdanika.html.app.impl;

import java.util.function.Consumer;

import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.HTMLElement.Event;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.BindingActionActivator;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ScriptActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Button;
import org.nasdanika.html.bootstrap.ButtonGroup;
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
	
	protected HTMLFactory getHTMLFactory() {
		return HTMLFactory.INSTANCE;
	}
	
	protected BootstrapFactory getBootstrapFactory() {
		return BootstrapFactory.INSTANCE;
	}
	
	protected KnockoutFactory getKnockoutFactory() {
		return KnockoutFactory.INSTANCE;
	}
		
	protected FontAwesomeFactory getFontAwesomeFactory() {
		return FontAwesomeFactory.INSTANCE;
	}	
	
	protected JsTreeFactory getJsTreeFactory() {
		return JsTreeFactory.INSTANCE;
	}

	@Override
	public Tag link(Action action) {
		Tag ret = label(action, getHTMLFactory().tag(TagName.a));
		ActionActivator activator = getActionActivator(action);
		if (activator instanceof NavigationActionActivator) {
			ret.attribute("href", ((NavigationActionActivator) activator).getUrl());
		} else if (activator instanceof ScriptActionActivator) {
			ret.on(Event.click, ((ScriptActionActivator) activator).getCode());
		} else if (activator instanceof BindingActionActivator) {
			((BindingActionActivator) activator).bind(ret);
		}
		return ret;
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
	public Tag badge(Action action, boolean isPill) {
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
	public Button<org.nasdanika.html.Button> iconButton(Action action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ButtonGroup iconButtonGroup(Iterable<Action> actions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ButtonGroup iconButtonGroup(Action... action) {
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
	public void label(Action action, Consumer<Object> contentConsumer) {
		String icon = action.getIcon();
		if (icon != null) {
			Tag iconTag;
			if (icon.contains("/")) {
				// Image
				iconTag = getHTMLFactory().tag(TagName.img).attribute("src", icon).style().height("1em");
			} else {
				// Class
				iconTag = getHTMLFactory().span().addClass(icon);
			}
			if (action.getText() != null) {
				iconTag.style().margin().right("0.1em");
			}
			contentConsumer.accept(iconTag);
		}		
			
		if (action.getText() != null) {
			contentConsumer.accept(action.getText());
		}	
		
		// TODO - help content - tooltip, icon, modals, ...
	}
	
	@Override
	public Tag label(Action action, Tag container) {
		label(action, container::content);
		return container;
	}
	
	@Override
	public Tag label(Action action) {
		return label(action, getHTMLFactory().span());
	}

}
