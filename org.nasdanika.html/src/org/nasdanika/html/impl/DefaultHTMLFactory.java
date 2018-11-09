package org.nasdanika.html.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.text.StringEscapeUtils;
import org.nasdanika.html.Button;
import org.nasdanika.html.FactoryProducer;
import org.nasdanika.html.Form;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.Function;
import org.nasdanika.html.Color;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Input;
import org.nasdanika.html.InputType;
import org.nasdanika.html.Producer;
import org.nasdanika.html.Select;
import org.nasdanika.html.Table;
import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.TextArea;
import org.nasdanika.html.TokenSource;

/**
 * HTML factory which relies on Bootstrap styles and scripts.
 * @author Pavel
 *
 */
public class DefaultHTMLFactory implements HTMLFactory {
	
	
	private static AtomicLong idCounter = new AtomicLong();
	
	private Producer.Adapter producerAdapter;
	
	public Producer.Adapter getProducerAdapter() {
		return producerAdapter;
	}
	
	public void setProducerAdapter(Producer.Adapter adapter) {
		this.producerAdapter = adapter;
	}
	
	private FactoryProducer.Adapter factoryProducerAdapter;
	
	public FactoryProducer.Adapter getFactoryProducerAdapter() {
		return factoryProducerAdapter;
	}
	
	public void setFactoryProducerAdapter(FactoryProducer.Adapter adapter) {
		this.factoryProducerAdapter = adapter;
	}
		
	@Override
	public String nextId() {
		return "nsd_"+Long.toString(idCounter.incrementAndGet(), Character.MAX_RADIX);
	}	

	@Override
	public Tag tag(String tagName, final Object... content) {
		return new TagImpl(this, tagName, false, content);
	}
	
	@Override
	public Tag tag(TagName tagName, Object... content) {
		return tag(tagName.name(), content);
	}
	
@Override
	public Tag nonEmptyTag(String tagName, Object... content) {
	return new TagImpl(this, tagName, true, content);
	}

	@Override
	public Tag nonEmptyTag(TagName tagName, Object... content) {
		return nonEmptyTag(tagName.name(), content);
	}

	@Override
	public Tag ul(final Iterable<?> items) {
		return new TagImpl(this, "ul", false) {
			
			@Override
			protected List<Object> getContent() {
				List<Object> ret = new ArrayList<>();
				for (Object item: items) {
					ret.add("<li>"+item+"</li>");
				}
				return ret;
			}
			
		};
	}

	@Override
	public Tag ol(final Iterable<?> items) {
		return new TagImpl(this, "ol", false) {
			
			@Override
			protected List<Object> getContent() {
				List<Object> ret = new ArrayList<>();
				for (Object item: items) {
					ret.add("<li>"+item+"</li>");
				}
				return ret;
			}
			
		};
	}
	
	@SuppressWarnings("resource")
	@Override
	public Tag link(Object href, final Object... content) {
		return new TagImpl(this, "a", false, content).attribute("href", href);
	}
	
	@Override
	public Select select() {
		return new SelectImpl(this);
	}
	
	@Override
	public TextArea textArea() {
		return new TextAreaImpl(this);
	}
	
	@Override
	public Fragment fragment(Object... content) {
		return new FragmentImpl(this, content);
	}


	@Override
	public Input input(InputType type) {
		return new InputImpl(this, type);		
	}


	@Override
	public Tag div(Object... content) {
		return tag(TagName.div, content);
	}
	
	@Override
	public Tag nonEmptyDiv(Object... content) {
		return nonEmptyTag(TagName.div, content);
	}

	@Override
	public Tag span(Object... content) {
		return tag(TagName.span, content);
	}


	@Override
	public Function function(final Object... paramArgs) {
		return new Function() {
			
			List<Object> params = new ArrayList<>(Arrays.asList(paramArgs));
			List<Object> binds = new ArrayList<>();			
			List<Object> code = new ArrayList<>();
	
			@Override
			public void close() throws Exception {
				for (Object obj: code) {
					if (obj instanceof AutoCloseable) {
						((AutoCloseable) obj).close();
					}
				}
				for (Object obj: binds) {
					if (obj instanceof AutoCloseable) {
						((AutoCloseable) obj).close();
					}
				}				
				for (Object obj: params) {
					if (obj instanceof AutoCloseable) {
						((AutoCloseable) obj).close();
					}
				}				
			}
	
			@Override
			public Function code(Object... code) {
				for (Object obj: code) {
					if (obj != null) {
						this.code.add(obj);
					}
				}
				return this;
			}
	
			@Override
			public Function bind(Object... bind) {
				for (Object obj: bind) {
					if (obj != null) {
						this.binds.add(obj);
					}
				}
				return this;
			}
			
			@Override
			public Function parameter(Object... param) {
				for (Object obj: param) {
					if (obj != null) {
						this.params.add(obj);
					}
				}
				return this;
			}
			
			@Override
			public String toString() {
				StringBuilder ret = new StringBuilder("function(");
				Iterator<Object> pit = params.iterator();
				while (pit.hasNext()) {
					ret.append(pit.next());
					if (pit.hasNext()) {
						ret.append(",");
					}
				}
				ret.append(") {");
				for (Object c: code) {
					ret.append(c);
				}
				ret.append("}");
				if (!binds.isEmpty()) {
					ret.append(".bind(");
					Iterator<Object> bit = binds.iterator();
					while (bit.hasNext()) {
						ret.append(bit.next());
						if (bit.hasNext()) {
							ret.append(",");
						}
					}
					ret.append(")");
				}
				return ret.toString();
			}
	
		};
	}
		
	private static final Pattern EXPANDER_PATTERN = Pattern.compile("\\{\\{(.+?)\\}\\}");	
	
	/**
	 * Expands tokens in the form of <code>{{token name}}</code> to their values.
	 * If a token is not found expansion is not processed.
	 * @param input
	 * @param env
	 * @return
	 */
	public static String interpolate(String input, TokenSource tokenSource) {
		if (tokenSource==null) {
			return input;
		}
		Matcher matcher = EXPANDER_PATTERN.matcher(input);
		StringBuilder output = new StringBuilder();
		int i = 0;
		while (matcher.find()) {
		    String token = matcher.group();
			Object replacement = tokenSource.get(token.substring(2, token.length()-2));
		    if (replacement != null) {
			    output.append(input.substring(i, matcher.start())).append(replacement);			    
			    i = matcher.end();
		    }
		}
		output.append(input.substring(i, input.length()));
		return output.toString();
	}
	
	public static String interpolate(String input, final Map<String, Object> env) {
		return interpolate(input, new TokenSource() {
	
			@Override
			public Object get(String token) {
				return env==null ? null : env.get(token);
			}
			
		});
	}
	
	/**
	 * Expands tokens found in URL, InputStream, Reader or String. 
	 * @param input URL, InputStream, Reader or String.
	 * @param tokenSource Source of tokens.
	 * @return String with expanded tokens.
	 * @throws IOException
	 */
	@Override
	public String interpolate(Object input, TokenSource tokenSource) {
		return interpolate(HTMLElementImpl.stringify(input, 0, this), tokenSource);
	}
	
	/**
	 * Expands tokens found in URL, InputStream, Reader or String. 
	 * @param input URL, InputStream, Reader or String.
	 * @param env token to value map.
	 * @return String with expanded tokens.
	 * @throws IOException
	 */
	@Override
	public String interpolate(Object input, Map<String, Object> env) {
		return interpolate(HTMLElementImpl.stringify(input, 0, this), env);
	}	
	
	@Override
	public String interpolate(Object input, String token, Object value) {
		return interpolate(input, Collections.singletonMap(token, value));
	}
			
	@Override
	public Table table() {
		return new TableImpl(this);
	}
		
	@Override
	public Button button(Object... content) {
		return new ButtonImpl(this, content);
	}
	
	@Override
	public Form form() {
		return new FormImpl(this);
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
				.style().background().color().color(Color.Silver)
				.style("opacity", 0.7)
				.style("z-index", 10)
				.style("display", "block");
	}

	@Override
	public String showOverlay(String overlaySelector, String overlaidSelector, int widthAdjustment, int heightAdjustment) {
		return "(function(overlay, overlaid) { overlay.width(overlaid.width()+"+widthAdjustment+"); overlay.height(overlaid.height()+"+heightAdjustment+"); overlay.show(); })(jQuery('"+overlaySelector+"'),jQuery('"+overlaidSelector+"'));";		
	}
	
}
