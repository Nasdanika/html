package org.nasdanika.html.model.app.graph.emf;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
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
			public Label createLabel() {
				return sourceEndpoint.createLabel();
			}

			@Override
			public Label createLink(String path) {
				Label link = sourceEndpoint.createLink();
				// TODO if link - deresolve URI
				return link;
			}

			@Override
			public void resolve(URI base) {
				targetURI = base;
			}

			@Override
			public Supplier<Collection<Label>> createLabelsSupplier() {								
				return sourceEndpoint.createLabelsSupplier().then(labels -> {
					for (Label label: labels) {
						if (label instanceof Link) {
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
		
	protected LabelFactory createSourceHandler(LabelFactory targetEndpoint) {
		return new LabelFactory() {

			@Override
			public Label createLabel() {
				return targetEndpoint.createLabel();
			}

			@Override
			public Label createLink(String path) {
				Label link = targetEndpoint.createLink();
				// TODO if link - deresolve URI
				return link;
			}

			@Override
			public void resolve(URI base) {				
				sourceURI = base;				
				EReferenceConnection eRefConn = (EReferenceConnection) config.getElement();
				String path = eRefConn.getPath();
				EReference eRef = eRefConn.getReference();
				if (eRef.isContainment()) {
					URI refURI = URI.createURI(path == null ? eRef.getName() + "/" : eRef.getName() + "/" + path + "/");
					targetEndpoint.resolve(refURI.resolve(base));
				}
			}

			@Override
			public Supplier<Collection<Label>> createLabelsSupplier() {
				return targetEndpoint.createLabelsSupplier().then(labels -> {
					for (Label label: labels) {
						if (label instanceof Link) {
							Link link = (Link) label;
							String location = link.getLocation();
							if (!Util.isBlank(location)) {
								URI uri = URI.createURI(location);
								if (!uri.isRelative() && sourceURI != null && !sourceURI.isRelative()) {
									link.setLocation(uri.deresolve(sourceURI, true, true, true).toString());
								}
							}
						}
					}
					return labels;
				});
				
			}
			
		};
	}

}
