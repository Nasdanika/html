package org.nasdanika.html.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringEscapeUtils;
import org.nasdanika.html.Accordion;
import org.nasdanika.html.ApplicationPanel;
import org.nasdanika.html.Bootstrap;
import org.nasdanika.html.Breadcrumbs;
import org.nasdanika.html.Button;
import org.nasdanika.html.ButtonGroup;
import org.nasdanika.html.ButtonToolbar;
import org.nasdanika.html.Carousel;
import org.nasdanika.html.Dropdown;
import org.nasdanika.html.FontAwesome;
import org.nasdanika.html.FontAwesome.Directional;
import org.nasdanika.html.FontAwesome.Size;
import org.nasdanika.html.FontAwesome.Spinner;
import org.nasdanika.html.FontAwesome.Stack;
import org.nasdanika.html.Form;
import org.nasdanika.html.InputGroup;
import org.nasdanika.html.JsTreeContextMenuItem;
import org.nasdanika.html.JsTreeNode;
import org.nasdanika.html.KnockoutVirtualElement;
import org.nasdanika.html.LinkGroup;
import org.nasdanika.html.ListGroup;
import org.nasdanika.html.Modal;
import org.nasdanika.html.Navbar;
import org.nasdanika.html.Pills;
import org.nasdanika.html.Table;
import org.nasdanika.html.Tabs;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;
import org.nasdanika.html.Theme;
import org.nasdanika.html.UIElement;
import org.nasdanika.html.Well;

/**
 * HTML factory which relies on Bootstrap styles and scripts.
 * @author Pavel
 *
 */
public class DefaultHTMLFactory extends AbstractHTMLFactory {
	
	private RouterApplicationRenderer routerApplicationRenderer = new RouterApplicationRenderer();
	
	@Override
	public AutoCloseable bootstrapRouterApplication(
			Theme theme, 
			Object title,
			Object initialRoute, 
			Object head, 
			Object... body) {		
		if (bootstrapCssContainer==null) {
			return routerApplication(title, initialRoute, head, body);
		}
		List<String> effectiveStylesheets = new ArrayList<>();
		if (theme==null) {
			theme = Theme.None;
		}
		switch (theme) {
		case None:
			effectiveStylesheets.add(bootstrapCssContainer+"/bootstrap.min.css");
			break;
		case Default:
			effectiveStylesheets.add(bootstrapCssContainer+"/bootstrap.min.css");
			effectiveStylesheets.add(bootstrapCssContainer+"/bootstrap-theme.min.css");
			break;
		default:
			effectiveStylesheets.add(bootstrapCssContainer+"/bootstrap-"+theme.name().toLowerCase()+".min.css");
			break;
		}
		effectiveStylesheets.addAll(stylesheets);
		return routerApplication(effectiveStylesheets, title, initialRoute, head, body);
	}

	@Override
	public AutoCloseable routerApplication(
			final Object title, 
			final Object initialRoute,
			final Object head, 
			final Object... body) {
		return routerApplication(stylesheets, title, initialRoute, head, body);
	}
		
	private AutoCloseable routerApplication(
			final List<String> effectiveStylesheets,
			final Object title, 
			final Object initialRoute,
			final Object head, 
			final Object[] body) {
			
		return new AutoCloseable() {
			
			@Override
			public void close() throws Exception {
				if (title instanceof AutoCloseable) {
					((AutoCloseable) title).close();
				}
				if (initialRoute instanceof AutoCloseable) {
					((AutoCloseable) initialRoute).close();
				}
				if (head instanceof AutoCloseable) {
					((AutoCloseable) head).close();
				}
				for (Object b: body) {
					if (b instanceof AutoCloseable) {
						((AutoCloseable) b).close();
					}
				}
				
			}
			
			@Override
			public String toString() {
				return routerApplicationRenderer.generate(new RouterApplicationConfig() {
					
					@Override
					public String getTitle() {
						return title==null ? null : UIElementImpl.stringify(title, 0, DefaultHTMLFactory.this);
					}
					
					@Override
					public String getInitialRoute() {
						return initialRoute == null ? null : UIElementImpl.stringify(initialRoute, 0, DefaultHTMLFactory.this);
					}
					
					@Override
					public String getHead() {
						return head == null ? "" : UIElementImpl.stringify(head, 0, DefaultHTMLFactory.this);
					}
					
					@Override
					public String getBody() {
						StringBuilder bodyBuilder = new StringBuilder();
						for (Object b: body) {
							if (b!=null) {
								bodyBuilder.append(UIElementImpl.stringify(b, 0, DefaultHTMLFactory.this));
							}
						}
						return bodyBuilder.toString();
					}
					
					@Override
					public List<String> getScripts() {
						return scripts;
					}
					
					@Override
					public List<String> getStylesheets() {
						return effectiveStylesheets;
					}
					
				});
			}
		};
		
	}

	@Override
	public Navbar navbar(final Object brand, Object brandRef) {
		return new NavbarImpl(this, brand, brandRef);
	}

	@Override
	public ApplicationPanel applicationPanel() {
		return new ApplicationPanelImpl(this);
	}
	

	@Override
	public Table table() {
		return new TableImpl(this);
	}
	
	private List<String> scripts = new ArrayList<>();
	private List<String> stylesheets = new ArrayList<>();
	private String bootstrapCssContainer;
	
	public List<String> getScripts() {
		return scripts;
	}
	
	public List<String> getStylesheets() {
		return stylesheets;
	}
	
	/**
	 * URL of the folder containing bootstrap.min.css, bootstrap-theme.min.css, and bootstrap-<code>&lt;theme name&gt;</code> files.
	 * @param bootstrapCssContainer
	 */
	public void setBootstrapCssContainer(String bootstrapCssContainer) {
		this.bootstrapCssContainer = bootstrapCssContainer;
	}

	@Override
	public Tag panel(Bootstrap.Style style, final Object header, final Object body, final Object footer) {
		Tag panel = div().addClass("panel").addClass("panel-"+style.name().toLowerCase());
		if (header!=null) {
			panel.content(div(tag("h3", header).addClass("panel-title")).addClass("panel-heading"));
		}
		
		if (body instanceof Table || body instanceof ListGroup || body instanceof LinkGroup) {
			panel.content(body);
		} else if (body!=null) {
			panel.content(div(body).addClass("panel-body"));
		}
		
		if (footer!=null) {
			panel.content(div(footer).addClass("panel-footer"));			
		}
		
		return panel;
	}

	@Override
	public Button button(Object... content) {
		return new ButtonImpl(this, false, content);
	}

	@Override
	public Tag label(Bootstrap.Style style, Object... content) {
		return span(content).addClass("label").addClass("label-"+style.name().toLowerCase());
	}
	
	private static final String ALERT_CLOSE_BUTTON = "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-hidden=\"true\">&times;</button>";
	
	@Override
	public Tag alert(Bootstrap.Style style, boolean dismissable, Object... content) {
		if (dismissable) {
			return div(ALERT_CLOSE_BUTTON)
					.content(content)
					.addClass("alert")
					.addClass("alert-"+style.name().toLowerCase())
					.addClass("alert-dismissable");			
		}
		return div(content).addClass("alert").addClass("alert-"+style.name().toLowerCase());
	}
	
	@Override
	public ListGroup listGroup() {
		return new ListGroupImpl(this);
	}
		
	@Override
	public LinkGroup linkGroup() {
		return new LinkGroupImpl(this);
	}
	
	@Override
	public InputGroup<?> inputGroup(Object control) {
		return new InputGroupImpl(this, control);
	}
	
	@Override
	public Accordion accordion() {
		return new AccordionImpl(this);
	}

	@Override
	public Form form() {
		return new FormImpl(this, false, false);
	}
	
	@Override
	public <T extends UIElement<?>> T popover(T element, Placement placement, String title, String text) {
		element
			.attribute("data-toggle", "popover")
			.attribute("data-container", "body")
			.attribute("data-placement", placement.name().toLowerCase());
		if (title!=null) {
			element.attribute("data-original-title", title);
		}
		if (text!=null) {
			element.attribute("data-content", text);
		}
		
		return element;
	}
	
	@Override
	public <T extends UIElement<?>> T tooltip(T element, Placement placement, String text) {
		element
			.attribute("data-toggle", "tooltip")
			.attribute("title", text)
			.attribute("data-placement", placement.name().toLowerCase());
		
		return element;
	}

	@Override
	public Tabs tabs() {
		return new TabsImpl(this);
	}

	@Override
	public Pills pills() {
		return new PillsImpl(this);
	}

	@Override
	public Tag glyphicon(Bootstrap.Glyphicon glyphicon) {		
		return span("").addClass("glyphicon").addClass("glyphicon-"+glyphicon.code());
	}
	
	@Override
	public Modal modal() {
		return new ModalImpl(this);
	}
	
	@Override
	public Tag title(final Object title) {
		Object escapedTitle = new Object() {
			public String toString() {
				return StringEscapeUtils.escapeEcmaScript(String.valueOf(title));				
			};
		};
		return tag("script", "document.title=\"", escapedTitle, "\";");
	}
	
	@Override
	public Tag inject(final Object selector, Object... content) {
		final String topId = nextId()+"_inject_container";
		final String contentId = nextId()+"_inject_content";
		Object script = new AutoCloseable() {
			
			@Override
			public String toString() {
				return "if ($(\""+selector+"\").length>0) { $(\""+selector+"\").html($(\"#"+contentId+"\").html()); $(\"#"+topId+"\").remove(); }";
			}
									
			@Override
			public void close() throws Exception {
				if (selector instanceof AutoCloseable) {
					((AutoCloseable) selector).close();
				}
				
			}
		};
		return div(div(content).id(contentId), tag("script", script)).id(topId);
	}
	
	@Override
	public Breadcrumbs breadcrumbs() {
		return new BreadcrumbsImpl(this);
	}

	@Override
	public ButtonGroup buttonGroup(Button... buttons) {
		return new ButtonGroupImpl(this, buttons);
	}

	@Override
	public ButtonToolbar buttonToolbar(ButtonGroup... buttonGroups) {
		return new ButtonToolbarImpl(this, buttonGroups);
	}

	@Override
	public Carousel carousel() {
		return new CarouselImpl(this);
	}

	@Override
	public Object collapsible(final Bootstrap.Style style, final Object title, final boolean collapsed, final Object... content) {
		return new AutoCloseable() {
			
			private void panelBody(int start, int end, StringBuilder sb) {
				if (start<end) {
					sb.append("<div class=\"panel-body\">");
					for (int i=start; i<end; ++i) {
						if (content[i]!=null) {
							sb.append(content[i]);
						}
					}
					sb.append("</div>");
				}
			}
			
			@Override
			public String toString() {
				String id = nextId()+"_collapsible";
				StringBuilder ret = new StringBuilder();
				ret.append("<div class=\"panel panel-"+style.name().toLowerCase()+"\">");
					ret.append("<div class=\"panel-heading\">");
						ret.append("<h4 class=\"panel-title\">");
							ret.append("<a data-toggle=\"collapse\" href=\"#"+id+"\">");
								ret.append(title);
							ret.append("</a>");
						ret.append("</h4>");
					ret.append("</div>");
					
					if (content.length==1 && (content[0] instanceof ListGroup || content[0] instanceof LinkGroup || content[0] instanceof Table)) {
						UIElement<?> sContent = (UIElement<?>) content[0];
						sContent.id(id);
						sContent.addClass("panel-collapse");
						sContent.addClass("collapse");
						if (!collapsed) {
							sContent.addClass("in");
						}
						ret.append(sContent);
					} else {				
						ret.append("<div id=\""+id+"\" class=\"panel-collapse collapse");
						if (!collapsed) {
							ret.append(" in");
						}
						ret.append("\">");
							int start = 0; 
							for (int i=0; i<content.length; ++i) {
								if (content[i] instanceof ListGroup
										|| content[i] instanceof LinkGroup
										|| content[i] instanceof Table) {
									panelBody(start, i, ret);
									ret.append(content[i]);
									start=i+1;
								}
							}
							panelBody(start, content.length, ret);
						ret.append("</div>");
					}
				ret.append("</div>");				
				return ret.toString();
			}

			@Override
			public void close() throws Exception {
				UIElementImpl.close(title);
				UIElementImpl.close(content);
			}
			
		};
	}

	@Override
	public FontAwesome<Tag> fontAwesome() {
		return span().fontAwesome();
	}
	
	@Override
	public Stack fontAwesomeStack() {
		return new Stack() {
			
			private Tag span = span().addClass("fa-stack");

			@Override
			public Stack icon(UIElement<?> icon, IconSize size, boolean inverse) {
				if (size==null) {
					icon.addClass("fa-stack-1x");
				} else {
					switch (size) {
					case x1:
						icon.addClass("fa-stack-1x");
						break;
					case x2:
						icon.addClass("fa-stack-2x");
						break;
					case x3:
						icon.addClass("fa-stack-3x");
						break;
					case x4:
						icon.addClass("fa-stack-4x");
						break;
					case x5:
						icon.addClass("fa-stack-5x");
						break;
					default:
						throw new IllegalArgumentException("Unexpected icon size: "+size);					
					}
				}
				if (inverse) {
					icon.addClass("fa-inverse");
				}
				span.content(icon);
				return this;
			}

			@Override
			public Stack size(Size size) {
				FontAwesomeImpl.size(size, span);
				return this;
			}

			@Override
			public void close() throws Exception {
				span.close();				
			}

			@Override
			public Stack icon(FontAwesome<?> icon, IconSize size, boolean inverse) {
				return icon(icon.getTarget(), size, inverse);
			}
			
			@Override
			public String toString() {
				return span.toString();
			}
			
		};
	}

	@Override
	public Tag overlay(Object... content) {		
		Tag contentDiv = div(content)
				.style("position", "relative")
				.style("height", "100%")
				.style("width", "100%")
				.style("display", "flex")
				.style("justify-content", "center")
				.style("align-items", "center");
		
		return div(contentDiv)
				.style("position", "absolute")
				.style("width", "100%")
				.style("height", "100%")
				.style().background().color().bootstrapColor(Bootstrap.Color.GRAY_LIGHTER)
				.style("opacity", 0.7)
				.style("z-index", 10)
				.style("display", "block");
	}

	@Override
	public Tag spinnerOverlay(Spinner spinner) {
		return overlay(fontAwesome().spinner(spinner).size(Size.x5).spin().getTarget());
	}

	@Override
	public String showOverlay(String overlaySelector, String overlaidSelector, int widthAdjustment, int heightAdjustment) {
		return "(function(overlay, overlaid) { overlay.width(overlaid.width()+"+widthAdjustment+"); overlay.height(overlaid.height()+"+heightAdjustment+"); overlay.show(); })(jQuery('"+overlaySelector+"'),jQuery('"+overlaidSelector+"'));";		
	}

	@Override
	public Well well(Object... content) {		
		return new WellImpl(this, content);
	}
	
	@Override
	public Tag badge(Object... content) {
		return span(content).addClass("badge");
	}
	
	@Override
	public Tag stackModal() {
		return tag(TagName.script, getClass().getResource("StackedModal.js"));
	}

	@Override
	public Dropdown<?> dropdown(UIElement<?> toggle) {
		return new DropdownImpl(this, "span", toggle);
	}
	
	@Override
	public Dropdown<?> caretDropdown() {
		return dropdown(fontAwesome().directional(Directional.caret_down).getTarget().style("cursor", "pointer"));
	}

	@Override
	public KnockoutVirtualElement knockoutVirtualElement(Object... content) {
		return new KnockoutVirtualElementImpl(this, content);
	}
	
	@Override
	public JsTreeNode jsTreeNode() {
		return new JsTreeNodeImpl(this);
	}
	
	@Override
	public JsTreeContextMenuItem jsTreeContextMenuItem() {
		return new JsTreeContextMenuItemImpl();
	}
	
}
