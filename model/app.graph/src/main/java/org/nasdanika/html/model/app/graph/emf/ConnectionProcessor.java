package org.nasdanika.html.model.app.graph.emf;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.graph.emf.EOperationConnection;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.emf.QualifiedConnection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class ConnectionProcessor {
	
	protected URI sourceURI;
	protected URI targetURI;
	protected ConnectionProcessorConfig<WidgetFactory, WidgetFactory> config;

	public ConnectionProcessor(ConnectionProcessorConfig<WidgetFactory, WidgetFactory> config) {		
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
					return base.resolve(targetURI); 					
				}
				return base;				
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public String selectString(Object selector, URI base, ProgressMonitor progressMonitor) {
				if (selector instanceof Selector) {
					return select((Selector<String>) selector, base, progressMonitor);
				}
				return sourceEndpoint.selectString(selector, resolveBase(base), progressMonitor); 				
			}
			
			@Override
			public Object select(Object selector, URI base, ProgressMonitor progressMonitor) {
				if (selector instanceof Selector) {
					return select((Selector<?>) selector, base, progressMonitor);
				}
				return sourceEndpoint.select(selector, resolveBase(base), progressMonitor);
			}
						
			@Override
			public <T> T select(Selector<T> selector, URI base, ProgressMonitor progressMonitor) {
				if (selector instanceof ConnectionSelector) {
					return selector.select(this, resolveBase(base), progressMonitor);
				}
				return sourceEndpoint.select(selector, resolveBase(base), progressMonitor);
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
			public Label createHelpDecorator(URI base, ProgressMonitor progressMonitor) {
				return sourceEndpoint.createHelpDecorator(resolveBase(base), progressMonitor);
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
				QualifiedConnection<?> conn = (QualifiedConnection<?>) config.getElement();
				String path = conn.getPath();
				if (conn instanceof EReferenceConnection) {
					EReference eRef = ((EReferenceConnection) conn).getReference();
					if (eRef.isContainment()) {
						String uriStr = "references/" + eRef.getName() + "/";
						if (path != null) {
							uriStr += path + "/";
						}
						URI refURI = URI.createURI(uriStr);
						targetEndpoint.resolve(refURI.resolve(base), progressMonitor);
					}
				} else if (conn instanceof EOperationConnection) {
					EOperation eOp = ((EOperationConnection) conn).getOperation();
					String uriStr = "operations/" + eOp.getName() + "/";
					if (path != null) {
						uriStr += path + "/";
					}
					URI refURI = URI.createURI(uriStr);
					targetEndpoint.resolve(refURI.resolve(base), progressMonitor);					
				}
			}
			
			private URI resolveBase(URI base) {
				if (base == null) {
					return sourceURI;
				}
				if (base.isRelative() && sourceURI != null && !sourceURI.isRelative()) {
					return base.resolve(sourceURI); 					
				}
				return base;				
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public String selectString(Object selector, URI base, ProgressMonitor progressMonitor) {
				if (selector instanceof Selector) {
					return select((Selector<String>) selector, base, progressMonitor);
				}
				return targetEndpoint.selectString(selector, resolveBase(base), progressMonitor);
			}
			
			@Override
			public Object select(Object selector, URI base, ProgressMonitor progressMonitor) {
				if (selector instanceof Selector) {
					return select((Selector<?>) selector, base, progressMonitor);
				}
				return targetEndpoint.select(selector, resolveBase(base), progressMonitor); 
			}
			
			@Override
			public <T> T select(Selector<T> selector, URI base, ProgressMonitor progressMonitor) {
				if (selector instanceof ConnectionSelector) {
					return selector.select(this, resolveBase(base), progressMonitor);
				}
				return targetEndpoint.select(selector, resolveBase(base), progressMonitor);
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
			public Label createHelpDecorator(URI base, ProgressMonitor progressMonitor) {
				return targetEndpoint.createHelpDecorator(resolveBase(base), progressMonitor);
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
