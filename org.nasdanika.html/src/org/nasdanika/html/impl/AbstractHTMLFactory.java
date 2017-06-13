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

import org.nasdanika.html.FactoryProducer;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.Function;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Input;
import org.nasdanika.html.Producer;
import org.nasdanika.html.Require;
import org.nasdanika.html.Select;
import org.nasdanika.html.Tag;
import org.nasdanika.html.Tag.TagName;
import org.nasdanika.html.TextArea;

/**
 * This factory contains method implementations which are not dependent
 * on the underlying web framework.
 * @author Pavel
 *
 */
public abstract class AbstractHTMLFactory implements HTMLFactory {
	
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
		return new TagImpl(this, tagName, content);
	}
	
	@Override
	public Tag tag(TagName tagName, Object... content) {
		return tag(tagName.name(), content);
	}

	@Override
	public Tag ul(final Iterable<?> items) {
		return new TagImpl(this, "ul") {
			
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
		return new TagImpl(this, "ol") {
			
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
		return new TagImpl(this, "a", content).attribute("href", href);
	}
		
	@Override
	public Tag routeLink(final Object targetElement, final Object path, Object... content) {
		return link(routeRef(targetElement, path), content);
	}

	@Override
	public AutoCloseable routeRef(final Object targetElement, final Object path) {
		AutoCloseable href = new AutoCloseable() {
			
			@Override
			public void close() throws Exception {
				if (targetElement instanceof AutoCloseable) {
					((AutoCloseable) targetElement).close();
				};
				if (path instanceof AutoCloseable) {
					((AutoCloseable) path).close();
				};
			}
			
			@Override
			public String toString() {
				return "#router/"+(targetElement==null ? "main" : targetElement)+(String.valueOf(path).startsWith("/") ? path : "/"+path);
			}
		};
		return href;
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
		if (content.length==0) {
			return tag(TagName.div, "");
		}
		return tag(TagName.div, content);
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


	@Override
	public Require require(final Object function, final Object... moduleArg) {
		return new Require() {
			
			List<Object> modules = new ArrayList<>(Arrays.asList(moduleArg));
	
			@Override
			public void close() throws Exception {
				for (Object obj: modules) {
					if (obj instanceof AutoCloseable) {
						((AutoCloseable) obj).close();
					}
				}
				if (function instanceof AutoCloseable) {
					((AutoCloseable) function).close();
				}
			}
	
			@Override
			public Require module(Object... module) {
				for (Object obj: module) {
					if (obj!=null) {
						modules.add(obj);
					}
				}
				
				return this;
			}
			
			@Override
			public String toString() {
				StringBuilder ret = new StringBuilder("require([");
				Iterator<Object> mit = modules.iterator();
				while (mit.hasNext()) {
					ret.append("'").append(mit.next()).append("'");
					if (mit.hasNext()) {
						ret.append(",");
					}
				}
				ret.append("], ");
				ret.append(function);
				ret.append(")");
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
		return interpolate(UIElementImpl.stringify(input, 0, this), tokenSource);
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
		return interpolate(UIElementImpl.stringify(input, 0, this), env);
	}	
	
	@Override
	public String interpolate(Object input, String token, Object value) {
		return interpolate(input, Collections.singletonMap(token, value));
	}
	
}
