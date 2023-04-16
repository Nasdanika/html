package org.nasdanika.html.ecore.gen.processors;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Processor;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.URINodeProcessor;

public class EPackageNodeProcessor implements URINodeProcessor {
	
	public static URINodeProcessor.Factory createFactory(EPackage target, EcoreNodeProcessorFactoryAdapterFactory factory) {
		return (config, context, progressMonitor) -> new EPackageNodeProcessor(target, factory, config, context, progressMonitor); 
	}
	
	public EPackageNodeProcessor(
			EPackage target,
			EcoreNodeProcessorFactoryAdapterFactory factory,			
			NodeProcessorConfig<Processor, LabelFactory, LabelFactory, Registry<URI>> config,
			Context context, 
			ProgressMonitor progressMonitor) {
		this(config, context, progressMonitor);
		System.out.println(target);
	}
	
	public EPackageNodeProcessor(
			NodeProcessorConfig<Processor, LabelFactory, LabelFactory, Registry<URI>> config,
			Context context, 
			ProgressMonitor progressMonitor) {
		// TODO Auto-generated constructor stub
		System.out.println(config.getElement());
	}
	

	@Override
	public void resolveURI(URI base) {
		// TODO Auto-generated method stub
		System.out.println(base);
	}

	@Override
	public Collection<Label> execute(ProgressMonitor progressMonitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return null;
	}

}
