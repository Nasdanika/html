package org.nasdanika.html.model.app.graph.emf;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Util;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;

public class EReferenceConnectionProcessor {
	
	protected URI sourceURI;
	
	protected URI targetURI;
	
	protected ConnectionProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config;

	public EReferenceConnectionProcessor(ConnectionProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config) {		
		this.config = config;		
		config.getSourceEndpoint().thenAccept(se -> config.setTargetHandler(createTargetHandler(se)));
		config.getTargetEndpoint().thenAccept(te -> config.setSourceHandler(createSourceHandler(te)));
	}
	
	protected LabelFactory createTargetHandler(LabelFactory sourceEndpoint) {
		return new LabelFactory() {

			@Override
			public Label createLabel(ProgressMonitor progressMonitor) {
				return sourceEndpoint.createLabel(progressMonitor);
			}

			@Override
			public Label createLink(String path, ProgressMonitor progressMonitor) {
				Label link = sourceEndpoint.createLink(progressMonitor);
				// TODO if link - deresolve URI
				return link;
			}

			@Override
			public void resolve(URI base, ProgressMonitor progressMonitor) {
				targetURI = base;
			}
			
			@Override
			public Label createLink(Object selector, String path, ProgressMonitor progressMonitor) {
				Label link = sourceEndpoint.createLink(selector, path, progressMonitor);
				// TODO if link - deresolve
				return link;
			}

			@Override
			public Supplier<Collection<Label>> createLabelsSupplier() {								
				return sourceEndpoint.createLabelsSupplier().then(labels -> {
					for (Label label: labels) {
						if (label instanceof Link) {
							// TODO - resolve against source and deresolve against target
							Link link = (Link) label;
							String location = link.getLocation();
							if (!Util.isBlank(location)) {
								URI uri = URI.createURI(location);
								if (!uri.isRelative() && targetURI != null && !targetURI.isRelative()) {
									link.setLocation(uri.deresolve(targetURI, true, true, true).toString());
								}
							}
						}
					}
					return labels;
				});
			}
			
		};
	}

	/**
	 * Rebases from target to source
	 * @param label
	 * @return
	 */
	protected Label rebaseLinkFromTargetToSource(Label label) {
		if (label instanceof Link) {
			String location = ((Link) label).getLocation();
			if (!Util.isBlank(location)) {						
				URI locationURI = URI.createURI(location);
				if (targetURI != null && !targetURI.isRelative()) {
					locationURI = locationURI.resolve(targetURI);
				}						
				if (sourceURI != null && !sourceURI.isRelative()) {
					locationURI = locationURI.deresolve(sourceURI, true, true, true);
				}
				((Link) label).setLocation(locationURI.toString());
			}
		}
		return label;
	}
		
	protected LabelFactory createSourceHandler(LabelFactory targetEndpoint) {
		return new LabelFactory() {

			@Override
			public Label createLabel(ProgressMonitor progressMonitor) {
				return targetEndpoint.createLabel(progressMonitor);
			}

			@Override
			public Label createLink(String path, ProgressMonitor progressMonitor) {
				return rebaseLinkFromTargetToSource(targetEndpoint.createLink(progressMonitor));
			}
			
			@Override
			public Label createLink(Object selector, String path, ProgressMonitor progressMonitor) {
				return rebaseLinkFromTargetToSource(targetEndpoint.createLink(selector, path, progressMonitor));
			}

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

			@Override
			public Supplier<Collection<Label>> createLabelsSupplier() {
				return targetEndpoint.createLabelsSupplier().then(labels -> {
					for (Label label: labels) {
						rebaseLinkFromTargetToSource(label);
					}
					return labels;
				});
				
			}
			
		};
	}

}
