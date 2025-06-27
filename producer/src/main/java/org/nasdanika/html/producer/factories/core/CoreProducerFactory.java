package org.nasdanika.html.producer.factories.core;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertyComputer;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.Producer;
import org.nasdanika.ncore.Marker;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marked;

import reactor.core.publisher.Mono;

public class CoreProducerFactory {
			
	private Context context;

	public CoreProducerFactory(Context context)  {
		this.context = context;
	}

// --- Ncore ---
	
	@org.nasdanika.common.Transformer.Factory
	public final Producer<String> createStringProducer(
			org.nasdanika.ncore.String str,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<Producer<String>,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, Producer<String>>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
		
		return new Producer<String>() {
			
			@Override
			public String produce(int indent) {
				return str.getValue();
			}

			@Override
			public Mono<String> produceAsync(int indent) {
				return Mono.just(produce(indent));
			}					
			
		};
	}
	
// --- Exec ---	
	
// --- Content ---	
		
	@org.nasdanika.common.Transformer.Factory
	public final Producer<String> createTextProducer(
			org.nasdanika.exec.content.Text text,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<Producer<String>,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, Producer<String>>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
		
		return new Producer<String>() {
			
			@Override
			public String produce(int indent) {
				String txt = text.getContent();
				if (Util.isBlank(txt) || !text.isInterpolate() || context == null) {
					return txt;
				}				
				return context.interpolateToString(txt);
			}

			@Override
			public Mono<String> produceAsync(int indent) {
				return Mono.just(produce(indent));
			}					
			
		};
	}
	
	@org.nasdanika.common.Transformer.Factory
	public final Producer<String> createResourceProducer(
			org.nasdanika.exec.content.Resource resource,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<Producer<String>,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, Producer<String>>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
				
		return new Producer<String>() {
			
			@Override
			public String produce(int indent) {
				ClassLoader[] classLoaders = { getClass().getClassLoader(), Thread.currentThread().getContextClassLoader(), context == null ? null : context.get(ClassLoader.class) };		
				URI uri = URI.createURI(resource.isInterpolate() ? context.interpolateToString(resource.getLocation()) : resource.getLocation());
				URL url = null;;					
				try {
					org.eclipse.emf.ecore.resource.Resource eResource = resource.eResource();
					if (eResource != null) {
						URI base = eResource.getURI();
						if (uri.isRelative() && base != null && base.isHierarchical() && !base.isRelative()) {
							uri = uri.resolve(base);
						}
					}
					
					String iUrl = uri.toString();					
					
					if (iUrl.startsWith(Util.CLASSPATH_URL_PREFIX)) {
						String resourcePath = iUrl.substring(Util.CLASSPATH_URL_PREFIX.length());
						for (ClassLoader classLoader: classLoaders) {
							if (classLoader != null) {
								url = classLoader.getResource(resourcePath);
								if (url != null) {
									break;
								}
							}
						}						
						
						if (url == null) {
							throw new ConfigurationException("Classpath resources not found: " + iUrl, resource);						
						}
					} else {
						url = new URL(iUrl);
					}
					try (InputStream in = url.openStream()) {
						String txt = DefaultConverter.INSTANCE.toString(in);
						if (Util.isBlank(txt) || !resource.isInterpolate() || context == null) {
							return txt;
						}				
						return context.interpolateToString(txt);
					}
				} catch (IOException e) {
					String errorMesage = resource.getErrorMessage();
					if (Util.isBlank(errorMesage)) {
						throw new ConfigurationException(e, resource);
					}
					String interpolatedErrorMessage = Util.interpolate(errorMesage, Map.of("url", url.toString(), "exception", e.toString())::get);
					return "<div class=\"nsd-error\">" + interpolatedErrorMessage + "</div>";
				}
			}

			@Override
			public Mono<String> produceAsync(int indent) {
				return Mono.just(produce(indent));
			}					
			
		};
	}
	
	@org.nasdanika.common.Transformer.Factory
	public final Producer<String> createInterpolatorProducer(
			org.nasdanika.exec.content.Interpolator interpolator,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<Producer<String>,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
		
		return new Producer<String>() {
			
			@SuppressWarnings("unchecked")
			private <T> T computeEmbeddedImage(Context context, String key, String path, Class<T> type) {
				if (type == null || type.isAssignableFrom(String.class)) {
					int idx = path.indexOf("/");
					if (idx == -1) {
						return null;
					}

					try {
						StringBuilder ret = new StringBuilder("<img src=\"data:image/" + path.substring(0, idx) + ";base64, ");
						Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
						byte[] imageBytes = converter.convert(converter.convert(resolve(path.substring(idx + 1)), InputStream.class), byte[].class);
						ret
							.append(Base64.getEncoder().encodeToString(imageBytes))
							.append("\"/>");
						
						return (T) ret.toString();
					} catch (Exception e) {
						throw new ConfigurationException("Error encoding image '" + path + "' : " + e, e, EObjectAdaptable.adaptTo(interpolator, Marked.class));
					}
				}
				return null;
			}
			
			@SuppressWarnings("unchecked")
			private <T> T computeInclude(Context context, String key, String path, Class<T> type) {
				if (type == null || type.isAssignableFrom(String.class)) {
					try {
						Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
						String includeContent = converter.convert(converter.convert(resolve(path), InputStream.class), String.class);
						return (T) context.interpolateToString(includeContent);
					} catch (Exception e) {
						throw new ConfigurationException("Error including '" + path + "': " + e, e, EObjectAdaptable.adaptTo(interpolator, Marked.class));
					}
				}
				return null;
			}
			
			protected URI resolve(String path) throws MalformedURLException {
				URI uri = URI.createURI(path);
				
				URI markerBase = null;
				List<? extends Marker> markers = interpolator.getMarkers();
				if (markers != null) {
					for (Marker marker: markers) {
						if (!Util.isBlank(marker.getLocation())) {
							markerBase = URI.createURI(marker.getLocation());
							break;
						}
					}
				}

				URI resourceBase = null;
				org.eclipse.emf.ecore.resource.Resource resource = interpolator.eResource();
				if (resource != null) {
					resourceBase = resource.getURI();
				}
				
				if (markerBase != null && !markerBase.isRelative() && markerBase.isHierarchical() && path.startsWith("./")) {
					uri = uri.resolve(markerBase);
				} else if (resourceBase != null) {
					uri = uri.resolve(resourceBase);
				}
				
				return uri;					
			}
			
			@SuppressWarnings("unchecked")
			private <T> T computeIncludeMarkdown(Context context, String key, String path, Class<T> type) {
				if (type == null || type.isAssignableFrom(String.class)) {
					try {
						Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
						String markdown = converter.convert(converter.convert(resolve(path), InputStream.class), String.class);
						String html = context.computingContext().get(MarkdownHelper.class, MarkdownHelper.INSTANCE).markdownToHtml(markdown);
						return (T) context.interpolateToString(html);
					} catch (Exception e) {
						throw new ConfigurationException("Error including markdown '" + path + "': " + e, e, EObjectAdaptable.adaptTo(interpolator, Marked.class));
					}
				}
				return null;
			}	
			
			@SuppressWarnings("unchecked")
			private <T> T computeIncludeStyledMarkdown(Context context, String key, String path, Class<T> type) {
				Object html = computeIncludeMarkdown(context, key, path, type);
				return (T) (html == null ? null : "<div class=\"markdown-body\">" + html + "</div>");
			}				
			
			protected String filter(String input) {
				if (interpolator.isProcessIncludes()) {
					MutableContext mc = context.fork();
					mc.put("embedded-image", (PropertyComputer) this::computeEmbeddedImage);
					mc.put("include", (PropertyComputer) this::computeInclude);
					mc.put("include-markdown", (PropertyComputer) this::computeIncludeMarkdown);
					mc.put("include-styled-markdown", (PropertyComputer) this::computeIncludeStyledMarkdown);
					
					return mc.interpolateToString(input);
				}
				return context.interpolateToString(input);				
			}
						
			private Producer<String> sourceProducer;
			
			{
				elementProvider.accept(interpolator.getSource(), (sp, pm) -> sourceProducer = sp);
			}
			
			@Override
			public String produce(int indent) {
				return filter(sourceProducer.produce(indent));
			}

			@Override
			public Mono<String> produceAsync(int indent) {
				return sourceProducer.produceAsync(indent).map(this::filter);
			}					
			
		};
	}
	
	@org.nasdanika.common.Transformer.Factory
	public final Producer<String> createMarkdownProducer(
			org.nasdanika.exec.content.Markdown markdown,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<Producer<String>,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
				
		return new Producer<String>() {
			
			protected String filter(String input) {
				MarkdownHelper markdownHelper = new MarkdownHelper() {
					
					@Override
					protected URI getResourceBase() {
						org.eclipse.emf.ecore.resource.Resource targetResource = markdown.eResource();
						URI resURI = targetResource == null ? null : targetResource.getURI();
						List<? extends Marker> markers = markdown.getMarkers();
						if (markers != null) {
							for(Marker marker: markers) {
								if (marker == null || Util.isBlank(marker.getLocation())) {
									return resURI;
								}
						
								try {
									URI markerLocation = URI.createURI(marker.getLocation());
									return resURI != null && resURI.isHierarchical() ? markerLocation.resolve(resURI) : markerLocation;
								} catch (Exception e) {
									throw new ConfigurationException("Invalid location: " + marker.getLocation(), e, marker);
								}
							}
						}
						return resURI;
					}
					
					@Override
					protected DiagramGenerator getDiagramGenerator() {
						return context == null ? super.getDiagramGenerator() : context.computingContext().get(DiagramGenerator.class, super.getDiagramGenerator()); 
					}
					
				};
				String html = markdownHelper.markdownToHtml(input);
				return markdown.isStyle() ? "<div class=\"markdown-body\">" + html + "</div>" : html;
			}
						
			private Producer<String> sourceProducer;
			
			{
				elementProvider.accept(markdown.getSource(), (sp, pm) -> sourceProducer = sp);
			}
			
			@Override
			public String produce(int indent) {
				return filter(sourceProducer.produce(indent));
			}

			@Override
			public Mono<String> produceAsync(int indent) {
				return sourceProducer.produceAsync(indent).map(this::filter);
			}					
			
		};
	}
	
}
