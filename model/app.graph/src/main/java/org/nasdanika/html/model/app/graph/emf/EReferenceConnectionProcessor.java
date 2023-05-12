package org.nasdanika.html.model.app.graph.emf;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class EReferenceConnectionProcessor {
	
	protected URI sourceURI;
	protected URI targetURI;
	protected ConnectionProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config;

	public EReferenceConnectionProcessor(ConnectionProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {		
		this.config = config;		
		config.getSourceEndpoint().thenAccept(se -> config.setTargetHandler(createTargetHandler(se)));
		config.getTargetEndpoint().thenAccept(te -> config.setSourceHandler(createSourceHandler(te)));
	}
	
	protected WidgetFactory createTargetHandler(WidgetFactory sourceEndpoint) {
		return new WidgetFactory() {
			
			@Override
			public void resolve(URI base, ProgressMonitor progressMonitor) {
				targetURI = base;
			}
			
			private URI resolveBase(URI base) {
				if (base == null) {
					return targetURI;
				}
				if (base.isRelative() && targetURI != null && !targetURI.isRelative()) {
					base.resolve(targetURI); 					
				}
				return base;				
			}
			
			@Override
			public String createWidgetString(Object selector, URI base, ProgressMonitor progressMonitor) {
				return sourceEndpoint.createWidgetString(selector, resolveBase(base), progressMonitor); 				
			}
			
			@Override
			public Object createWidget(Object selector, URI base, ProgressMonitor progressMonitor) {
				return sourceEndpoint.createWidget(selector, resolveBase(base), progressMonitor);
			}
			
			@Override
			public String createLinkString(URI base, ProgressMonitor progressMonitor) {
				return sourceEndpoint.createLinkString(resolveBase(base), progressMonitor);
			}
			
			@Override
			public Object createLink(URI base, ProgressMonitor progressMonitor) {
				return sourceEndpoint.createLink(resolveBase(base), progressMonitor);
			}
			
			@Override
			public Supplier<Collection<Label>> createLabelsSupplier() {
				return sourceEndpoint.createLabelsSupplier().then(labels -> {
					for (Label label : labels) {
						if (label instanceof Link) {
							((Link) label).rebase(targetURI, sourceURI);
						}
					}
					return labels;
				});
			}
			
			@Override
			public String createLabelString(ProgressMonitor progressMonitor) {
				return sourceEndpoint.createLabelString(progressMonitor);
			}
			
			@Override
			public Object createLabel(ProgressMonitor progressMonitor) {
				return sourceEndpoint.createLabel(progressMonitor);
			}
			
		};
		
	}

	protected WidgetFactory createSourceHandler(WidgetFactory targetEndpoint) {
		return new WidgetFactory() {
			
			@Override
			public void resolve(URI base, ProgressMonitor progressMonitor) {
				sourceURI = base;				
				EReferenceConnection eRefConn = (EReferenceConnection) config.getElement();
				String path = eRefConn.getPath();
				EReference eRef = eRefConn.getReference();
				if (eRef.isContainment()) {
					URI refURI = URI.createURI(path == null ? eRef.getName() + "/" : eRef.getName() + "/" + path + "/");
					targetEndpoint.resolve(refURI.resolve(base), progressMonitor);
				}
			}
			
			private URI resolveBase(URI base) {
				if (base == null) {
					return sourceURI;
				}
				if (base.isRelative() && sourceURI != null && !sourceURI.isRelative()) {
					base.resolve(sourceURI); 					
				}
				return base;				
			}
			
			@Override
			public String createWidgetString(Object selector, URI base, ProgressMonitor progressMonitor) {
				return targetEndpoint.createWidgetString(selector, resolveBase(base), progressMonitor);
			}
			
			@Override
			public Object createWidget(Object selector, URI base, ProgressMonitor progressMonitor) {
				return targetEndpoint.createWidget(selector, resolveBase(base), progressMonitor); 
			}
			
			@Override
			public String createLinkString(URI base, ProgressMonitor progressMonitor) {
				return targetEndpoint.createLinkString(resolveBase(base), progressMonitor);
			}
			
			@Override
			public Object createLink(URI base, ProgressMonitor progressMonitor) {
				return targetEndpoint.createLink(resolveBase(base), progressMonitor);
			}
			
			@Override
			public Supplier<Collection<Label>> createLabelsSupplier() {
				return targetEndpoint.createLabelsSupplier().then(labels -> {
					for (Label label : labels) {
						if (label instanceof Link) {
							((Link) label).rebase(targetURI, sourceURI);
						}
					}
					return labels;
				});
			}
			
			@Override
			public String createLabelString(ProgressMonitor progressMonitor) {
				return targetEndpoint.createLabelString(progressMonitor);
			}
			
			@Override
			public Object createLabel(ProgressMonitor progressMonitor) {
				return targetEndpoint.createLabel(progressMonitor);
			}
			
		};
	}

}
