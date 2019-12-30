package org.nasdanika.html.app.impl;

import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SimpleMutableContext;
import org.nasdanika.html.Event;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.NamedItemsContainer;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Adaptable;
import org.nasdanika.html.app.BindingActionActivator;
import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ScriptActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.ActionGroup;
import org.nasdanika.html.bootstrap.BootstrapElement;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Button;
import org.nasdanika.html.bootstrap.ButtonGroup;
import org.nasdanika.html.bootstrap.ButtonToolbar;
import org.nasdanika.html.bootstrap.Color;
import org.nasdanika.html.bootstrap.Direction;
import org.nasdanika.html.bootstrap.Dropdown;
import org.nasdanika.html.bootstrap.ListGroup;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.impl.DefaultBootstrapFactory;
import org.nasdanika.html.echarts.EChartsFactory;
import org.nasdanika.html.echarts.impl.DefaultEChartsFactory;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.fontawesome.impl.DefaultFontAwesomeFactory;
import org.nasdanika.html.jstree.JsTreeContextMenuItem;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.JsTreeNode;
import org.nasdanika.html.jstree.impl.DefaultJsTreeFactory;
import org.nasdanika.html.knockout.KnockoutFactory;
import org.nasdanika.html.knockout.impl.DefaultKnockoutFactory;

public class ViewGeneratorImpl extends SimpleMutableContext implements ViewGenerator {
	
	/**
	 * Content passed to this consumer is added to the head of the HTML page. E.g. stylesheets or scripts.
	 */
	protected Consumer<?> headContentConsumer;

	/**
	 * Content passed to this consumer is added to the body of the HTML page. E.g. modal dialogs definitions.
	 */
	protected Consumer<?> bodyContentConsumer;

	/**
	 * Create a new view generator implementation with a given context, head content consumer and body content consumer.
	 * @param context
	 * @param headContentConsumer
	 * @param bodyContentConsumer
	 */
	public ViewGeneratorImpl(Context context, Consumer<?> headContentConsumer, Consumer<?> bodyContentConsumer) {
		super(context);
		this.headContentConsumer = headContentConsumer;
		this.bodyContentConsumer = bodyContentConsumer;
	}
	
	/**
	 * Create a new view generator implementation with an empty context, head content consumer and body content consumer.
	 * @param context
	 * @param headContentConsumer
	 * @param bodyContentConsumer
	 */
	public ViewGeneratorImpl(Consumer<?> headContentConsumer, Consumer<?> bodyContentConsumer) {
		this(Context.EMPTY_CONTEXT, headContentConsumer, bodyContentConsumer);
	}
	
	@Override
	public Consumer<?> getBodyContentConsumer() {
		return bodyContentConsumer;
	}
	
	@Override
	public Consumer<?> getHeadContentConsumer() {
		return headContentConsumer;
	}
	
	/**
	 * Creating factories on demand.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<T> type) {
		T ret = super.get(type);
		if (ret != null) {
			return ret;
		}
		if (type == HTMLFactory.class) {
			ret = (T) HTMLFactory.INSTANCE;
		} else if (type == BootstrapFactory.class) {
			ret = (T) new DefaultBootstrapFactory(get(HTMLFactory.class));
		} else if (type == KnockoutFactory.class) {
			ret = (T) new DefaultKnockoutFactory(get(HTMLFactory.class));
		} else if (type == FontAwesomeFactory.class) {
			ret = (T) new DefaultFontAwesomeFactory(get(HTMLFactory.class));
		} else if (type == JsTreeFactory.class) {
			ret = (T) new DefaultJsTreeFactory(get(HTMLFactory.class));
		} else if (type == EChartsFactory.class) {
			ret = (T) new DefaultEChartsFactory(get(HTMLFactory.class));
		}

		if (ret != null) {
			register(type, ret);
		}
		
		// To be able to obtain ViewGenerator as a service when it is composed or chained.
		if (ret == null && type.isInstance(this)) {
			ret = (T) this;
		}

		return ret;
	}

	@Override
	public Tag link(Action action) {
		Tag ret = label(action, get(HTMLFactory.class).tag(TagName.a));
		bindLink(action, ret);
		return ret;
	}
	
	/**
	 * Decorates object if decorator implements {@link Decorator} or is {@link Adaptable} and adapts to Decorator.
	 * @param target
	 * @param decorator
	 * @return
	 */
	@Override
	public void decorate(Object target, Object decorator) {
		Decorator decoratorService = get(Decorator.class);
		if (decoratorService != null) {
			decoratorService.decorate(target, this);
		}
		if (decorator instanceof Decorator) {
			((Decorator) decorator).decorate(target, this);
		} else if (decorator instanceof Adaptable) {
			Decorator adapter = ((Adaptable) decorator).adaptTo(Decorator.class);
			if (adapter != null) {
				adapter.decorate(target, this);
			}
		}
		
	}
	
	protected void bindLink(Action action, HTMLElement<?> anchor) {
		// TODO - confirmation.
		ActionActivator activator = getActionActivator(action);
		if (activator instanceof NavigationActionActivator) {
			anchor.attribute("href", ((NavigationActionActivator) activator).getUrl());
			if (action.getConfirmation() != null) {
				anchor.on(Event.click, "return confirm('"+action.getConfirmation()+"');");
			}
		} else if (activator instanceof ScriptActionActivator) {
			String code = ((ScriptActionActivator) activator).getCode();
			if (action.getConfirmation() != null) {
				code = "if (confirm('"+action.getConfirmation()+"')) { "+code+" }";
			}
			anchor.on(Event.click, code);
		} else if (activator instanceof BindingActionActivator) {
			((BindingActionActivator) activator).bind(anchor, this);
		}		
	}
	
	protected void bind(Action action, HTMLElement<?> element) {
		ActionActivator activator = getActionActivator(action);
		if (activator instanceof BindingActionActivator) {
			((BindingActionActivator) activator).bind(element, this);
		} else {
			String code = null; 
			if (activator instanceof NavigationActionActivator) {
				code = "location.href='"+((NavigationActionActivator) activator).getUrl()+"'";
			} else if (activator instanceof ScriptActionActivator) {
				code = ((ScriptActionActivator) activator).getCode();
			}
			
			if (code != null) {
				if (action.getConfirmation() != null) {
					code = "if (confirm('"+action.getConfirmation()+"')) { "+code+" }";
				}
				element.on(Event.click, code);
			}
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
		// TODO AUTO-generated method stub
		return null;
	}

	@Override
	public BootstrapElement<?,?> button(Action action) {
		Button<Tag> button = get(BootstrapFactory.class).button(link(action), action.getColor() == null ? Color.PRIMARY : action.getColor(), action.getColor() == null ? true : action.isOutline());
		button.disabled(action.isDisabled());
//		if (action.isFloatRight()) {
//			button._float().right();
//		}
		if (action.getChildren().isEmpty()) {
			return button;
		}
		Dropdown dd = get(BootstrapFactory.class).dropdown(button, action.getActivator() != null, Direction.DOWN);
		for (Entry<Label, List<Action>> cats: Util.groupByCategory((List<Action>) action.getChildren())) {
			if (cats.getKey() != null) {
				if (Util.isBlank(cats.getKey().getIcon()) && Util.isBlank(cats.getKey().getText())) {
					decorate(dd.divider(), cats.getKey());
				} else {
					decorate(dd.header(labelFragment(cats.getKey())), cats.getKey());
				}
			}
			for (Action cac: cats.getValue()) {	
				dd.item(link(cac), false, cac.isDisabled());
			}
		}
		return dd;
	}
	
	@Override
	public <T extends Action> ButtonToolbar buttonToolbar(Iterable<T> actions) {
		ButtonToolbar buttonToolbar = get(BootstrapFactory.class).buttonToolbar();
		for (Entry<Label, List<T>> cats: Util.groupByCategory(actions)) {
			if (cats.getKey() == null) {
				for (Action cac: cats.getValue()) {
					BootstrapElement<?, ?> button = button(cac);
					button.margin().right(Breakpoint.DEFAULT, Size.S1);
					buttonToolbar.add(button);
				}					
			} else {
				ButtonGroup buttonGroup = get(BootstrapFactory.class).buttonGroup(false);
				for (Action cac: cats.getValue()) {
					buttonGroup.add(button(cac));
				}					
				buttonGroup.margin().right(Breakpoint.DEFAULT, Size.S1);
				buttonToolbar.add(buttonGroup);
			}
		}
		return buttonToolbar;
	}

	@Override
	public JsTreeContextMenuItem jsTreeContextMenuItem(Action action) {
		// TODO AUTO-generated method stub
		return null;
	}
	
	@Override
	public JsTreeNode jsTreeNode(Label label) {
		JsTreeNode ret = get(JsTreeFactory.class).jsTreeNode();
		
		ret.icon(label.getIcon());
		ret.text(label.getText());
		if (label.getTooltip() != null) {
			ret.anchorAttribute("title", label.getTooltip());
		}
		ret.id(label.getId());
		
		return ret;
	}

	@Override
	public JsTreeNode jsTreeNode(Action action, boolean ajax) {
		JsTreeNode ret = jsTreeNode(action);
		ret.disabled(action.isDisabled());
		List<Entry<Label, List<Action>>> categories = action.getNavigationChildrenGroupedByCategory();
		if (ajax) {
			if (!categories.isEmpty()) {
				ret.hasChildren();
			}
		} else {
			for (Entry<Label, List<Action>> group: categories) {			
				Label category = group.getKey();
				List<Action> categoryActions = (List<Action>) group.getValue();
				if (category == null || Util.isBlank(category.getText())) {
					for (Action ca: categoryActions) {
						ret.children().add(jsTreeNode(ca, ajax));
					}				
				} else {
					JsTreeNode categoryNode = jsTreeNode(category);
					ret.children().add(categoryNode);
					for (Action ca: categoryActions) {
						categoryNode.children().add(jsTreeNode(ca, ajax));
					}				
				}			
			}
		}
		
		ActionActivator activator = getActionActivator(action);
		if (activator instanceof NavigationActionActivator) {
			ret.anchorAttribute("onclick", "window.location='"+((NavigationActionActivator) activator).getUrl()+"';");
		} else if (activator instanceof ScriptActionActivator) {
			ret.anchorAttribute("onclick", ((ScriptActionActivator) activator).getCode());
		} else if (activator instanceof BindingActionActivator) {
			((BindingActionActivator) activator).bind(ret, this);
		}				
		
		return ret;
	}

	@Override
	public void label(Label label, Consumer<Object> contentConsumer) {
		String tooltip = label.getTooltip();
		if (!Util.isBlank(tooltip)) {
			Tag span = get(HTMLFactory.class).span();
			span.attribute("title", tooltip);
			contentConsumer.accept(span);
			contentConsumer = span;
		}
		
		String icon = label.getIcon();
		if (!Util.isBlank(icon)) {
			Tag iconTag;
			if (icon.contains("/")) {
				// Image
				iconTag = get(HTMLFactory.class).tag(TagName.img).attribute("src", icon).style().height("1em");
			} else {
				// Class
				iconTag = get(HTMLFactory.class).span().addClass(icon);
			}
			if (label.getText() != null) {
				iconTag.style().margin().right("0.3em");
			}
			contentConsumer.accept(iconTag);
		}		
			
		if (!Util.isBlank(label.getText())) {
			contentConsumer.accept(label.getText());
		}	
		
		if (!Util.isBlank(label.getNotification())) {
			Tag badge = get(BootstrapFactory.class).badge(true, label.getColor() == Color.PRIMARY ? Color.SECONDARY : Color.PRIMARY, label.getNotification());
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
		decorate(container, label);
		return container;
	}
	
	@Override
	public Tag label(Label label) {
		return label(label, get(HTMLFactory.class).span());
	}
	
	@Override
	public Fragment labelFragment(Label label) {
		Fragment ret = get(HTMLFactory.class).fragment();
		label(label, ret::content);
		return ret;
	}

	@Override
	public void add(NamedItemsContainer container, Action action) {
		container.item(labelFragment(action), processViewPart(action.generate(this, null), null));		
	}

	@Override
	public Tag add(ListGroup listGroup, Label label, boolean active, boolean disabled) {
		return listGroup.item(active, disabled, label.getColor(), labelFragment(label)); // TODO - decorate
	}

	@Override
	public Tag add(ListGroup listGroup, Action action, boolean active) {
		return add(listGroup, action, active, action.isDisabled());
	}

	@Override
	public Tag add(ActionGroup actionGroup, Action action, boolean active) {
		Tag link = actionGroup.action(active, action.isDisabled(), action.getColor(), null, labelFragment(action));
		bindLink(action, link);
		decorate(link, action);
		return link;
	}

	@Override
	public Tag addContent(ActionGroup actionGroup, Action action, boolean active) {
		String contentId = action.getId() == null ? null : "nsd-action-content-"+action.getId();
		Tag ret = actionGroup.contentAction(labelFragment(action), active, action.isDisabled(), action.getColor(), contentId, processViewPart(action.generate(this, null), null));
		decorate(ret, action);
		return ret;
	}

	@Override
	public void add(Navs navs, Action action, boolean active) {
		String contentId = action.getId() == null ? null : "nsd-action-content-"+action.getId();
		decorate(navs.item(labelFragment(action), active, action.isDisabled(), contentId, processViewPart(action.generate(this, null), null)), action);
	}
	
	@Override
	public Object processViewPart(Object obj, ProgressMonitor progressMonitor) {
		if (obj instanceof ViewPart) {
			return processViewPart(((ViewPart) obj).generate(this, progressMonitor), progressMonitor);
		}
		return obj;
	}
	
	@Override
	public ViewGenerator fork() {
		return new ViewGeneratorImpl(this, getHeadContentConsumer(), getBodyContentConsumer());
	}

	@SuppressWarnings("unchecked")
	@Override
	public Navs categorizedLinkNavs(List<Action> actions, Action activeAction, Color textColor) {	
		BootstrapFactory bootstrapFactory = get(BootstrapFactory.class);
		Navs navs = bootstrapFactory.navs();
		for (Entry<Label, ?> categoryGroup: Util.groupByCategory(actions)) {
			Label category = categoryGroup.getKey();
			if (category == null || (Util.isBlank(category.getText()) && Util.isBlank(category.getIcon()))) {
				for (Action ca: (List<Action>) categoryGroup.getValue()) {
					// Children are ignored if activator is not null.
					Fragment fragment = get(HTMLFactory.class).fragment();
					label(ca, fragment::content);
					ActionActivator activator = ca.getActivator();
					if (activator instanceof NavigationActionActivator) {
						Tag item = navs.item(fragment, ((NavigationActionActivator) activator).getUrl(), Util.equalOrInPath(activeAction, ca), ca.isDisabled());
						if (ca.getConfirmation() != null) {
							item.on(Event.click, "return confirm('"+ca.getConfirmation()+"');");
						}
						if (textColor != null) {
							bootstrapFactory.wrap(item).text().color(textColor);
						}
						decorate(item, ca);
					} else if (activator instanceof ScriptActionActivator) {
						String code = ((ScriptActionActivator) activator).getCode();
						if (ca.getConfirmation() != null) {
							code = "if (confirm('"+ca.getConfirmation()+"')) { "+code+" }";
						}
						Tag item = navs.item(fragment, "#", Util.equalOrInPath(activeAction, ca), ca.isDisabled()).on(Event.click, code);
						if (textColor != null) {
							bootstrapFactory.wrap(item).text().color(textColor);
						}
						decorate(item, ca);
					} else if (ca.getChildren().isEmpty()) {
						// As text
						Tag item = navs.item(fragment, "#", Util.equalOrInPath(activeAction, ca), ca.isDisabled());				
						if (textColor != null) {
							bootstrapFactory.wrap(item).text().color(textColor);
						}
						decorate(item, ca);
					} else {
						Dropdown dropdown = navs.dropdown(Util.equalOrInPath(activeAction, ca), fragment);				
						if (textColor != null) {
							dropdown.text().color(textColor);
						}
						decorate(dropdown.getToggle(), ca);						
						for (Entry<Label, List<Action>> cats: ca.getChildrenGroupedByCategory()) {
							if (cats.getKey() != null) {
								if (Util.isBlank(cats.getKey().getIcon()) && Util.isBlank(cats.getKey().getText())) {
									decorate(dropdown.divider(), cats.getKey());
								} else {
									decorate(dropdown.header(labelFragment(cats.getKey())), cats.getKey());
								}
							}
							for (Action cac: cats.getValue()) {	
								dropdown.item(link(cac), Util.equalOrInPath(activeAction, cac), cac.isDisabled());
							}
						}
					}
				}
			} else {
				Dropdown dropdown = navs.dropdown(false, labelFragment(category));
				if (textColor != null) {
					dropdown.text().color(textColor);
				}
				decorate(dropdown.getToggle(), category);
				for (Action cac: (List<Action>) categoryGroup.getValue()) {	
					dropdown.item(link(cac), Util.equalOrInPath(activeAction, cac), cac.isDisabled());
				}
			}
		}
		
		return navs;
	}
	
}
