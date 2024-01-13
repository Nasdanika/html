package org.nasdanika.html.model.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.script.ScriptEngine;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;
import org.nasdanika.drawio.emf.DrawioResource;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.drawio.model.util.AbstractDrawioFactory;
import org.nasdanika.persistence.Marker;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.expression.spel.support.StandardTypeLocator;

/**
 * Loads Drawio model using {@link DrawioResource} and then transforms it to the graph model.
 */
public class AppDrawioResource extends ResourceImpl {
		
	protected Function<URI,EObject> uriResolver;
	
	public AppDrawioResource(Function<URI,EObject> uriResolver) {
		super();
		this.uriResolver = uriResolver;
	}

	public AppDrawioResource(URI uri, Function<URI,EObject> uriResolver) {
		super(uri);
		this.uriResolver = uriResolver;
	}

	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		Resource diagramResource = new DrawioResource(getURI()) {

			@Override
			protected Function<Marker, org.nasdanika.ncore.Marker> getMarkerFactory() {
				return AppDrawioResource.this.getMarkerFactory();
			}

			@Override
			protected ModelFactory getFactory() {
				return AppDrawioResource.this.getDrawioFactory();
			}
			
			@Override
			protected URIConverter getURIConverter() {
				return AppDrawioResource.this.getURIConverter();
			}
			
		};
		
		diagramResource.load(inputStream, options);
		
		AppDrawioFactory appDrawioFactory = new AppDrawioFactory() {

			@Override
			protected EObject getByRefId(String refId, int pass, Map<EObject, EObject> registry) {				
				return AppDrawioResource.this.getByRefId(refId, pass, registry);
			}
			
			@Override
			protected EvaluationContext createEvaluationContext(EObject context) {
				EvaluationContext ret = AppDrawioResource.this.createEvaluationContext(context);
				if (ret instanceof StandardEvaluationContext) {
					ClassLoader classLoader = getClassLoader(context);
					if (classLoader != null) {
						((StandardEvaluationContext) ret).setTypeLocator(new StandardTypeLocator(classLoader));
					}
				}
				
				return ret;
			}
			
			@Override
			protected ClassLoader getClassLoader(EObject context) {
				Supplier<ClassLoader> lpcs = () -> {
					EObject logicalParent = getLogicalParent(context);
					if (logicalParent == null) {
						return null;
					}
					
					return getClassLoader(logicalParent);
				};
				return AppDrawioResource.this.getClassLoader(
						context,
						getBaseURI(context),
						lpcs);
			}
			
			@Override
			protected void configureScriptEngine(
					ScriptEngine engine, 
					EObject diagramElement, 
					EObject semanticElement,
					Map<EObject, EObject> registry, 
					int pass, 
					ProgressMonitor progressMonitor) {
				super.configureScriptEngine(engine, diagramElement, semanticElement, registry, pass, progressMonitor);
				AppDrawioResource.this.configureScriptEngine(engine, diagramElement, semanticElement, registry, pass, progressMonitor);
			}
			
			@Override
			protected URI getAppBase() {
				return AppDrawioResource.this.getAppBase();
			}
			
			@Override
			protected void filterRepresentationElement(
					org.nasdanika.drawio.ModelElement representationElement, 
					EObject semanticElement, 
					Map<EObject, EObject> registry,
					ProgressMonitor progressMonitor) {
				super.filterRepresentationElement(representationElement, semanticElement, registry, progressMonitor);
				AppDrawioResource.this.filterRepresentationElement(representationElement, semanticElement, registry, progressMonitor);
			}
			
		};
		
		Transformer<EObject,EObject> graphFactory = new Transformer<>(appDrawioFactory);
		Collection<EObject> diagramModelContents = new ArrayList<>();
		diagramResource.getAllContents().forEachRemaining(e -> {
			if (e instanceof org.nasdanika.drawio.model.Document
					|| e instanceof org.nasdanika.drawio.model.Page
					|| e instanceof org.nasdanika.drawio.model.ModelElement
					|| e instanceof org.nasdanika.drawio.model.Tag) {
				diagramModelContents.add(e);
			}
		});
		
		Map<EObject, EObject> graphElements = graphFactory.transform(diagramModelContents, false, getProgressMonitor());		
		diagramResource.getContents().stream().map(graphElements::get).forEach(getContents()::add);
	}

	protected ProgressMonitor getProgressMonitor() {
		return new NullProgressMonitor();
	}

	protected Function<Marker, org.nasdanika.ncore.Marker> getMarkerFactory() {
		return null;
	}

	protected ModelFactory getDrawioFactory() {
		return org.nasdanika.drawio.model.ModelFactory.eINSTANCE;
	}	
	
	protected EObject getByRefId(String refId, int pass, Map<EObject, EObject> registry) {
		if (uriResolver == null) {
			return null;
		}
		
		URI refURI = URI.createURI(refId);
		if(!getURI().isRelative()) {
			refURI = refURI.resolve(getURI());
		}
		return uriResolver.apply(refURI);
	}
	
	protected EvaluationContext createEvaluationContext(EObject context) {		
		return new StandardEvaluationContext();
	}
	
	protected ClassLoader getClassLoader(EObject context, URI baseURI, Supplier<ClassLoader> logicalParentClassLoaderSupplier) {
		return logicalParentClassLoaderSupplier == null ? getClass().getClassLoader() : logicalParentClassLoaderSupplier.get();
	}	

	protected URI getAppBase() {
		return AbstractDrawioFactory.DEFAULT_APP_BASE;
	}
	
	/**
	 * Override to implement filtering of a representation element. 
	 * For example, if an element represents a processing unit, its background color or image can be modified depending on the load - red for overloaded, green for OK, grey for planned offline.  
	 * @param representationElement
	 * @param registry
	 * @param progressMonitor
	 */
	protected void filterRepresentationElement(
			org.nasdanika.drawio.ModelElement representationElement, 
			EObject semanticElement,
			Map<EObject, EObject> registry,
			ProgressMonitor progressMonitor) {
		
	}
	
	protected void configureScriptEngine(
			ScriptEngine engine, 
			EObject diagramElement, 
			EObject semanticElement,
			Map<EObject, EObject> registry, 
			int pass, 
			ProgressMonitor progressMonitor) {		
		
	}	
	
}
