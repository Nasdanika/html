package org.nasdanika.html.ecore.gen.processors;

import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Context;
import org.nasdanika.graph.processor.Factory;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.Processor;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.emf.EObjectReflectiveProcessorFactory;

/**
 * Node processor factory to use with {@link EObjectReflectiveProcessorFactory}.
 * @author Pavel
 *
 */
@Factory("target.eClass().getEPackage().getNsURI() == 'http://www.eclipse.org/emf/2002/Ecore'")
public class EcoreNodeProcessorFactory {
	
	private Context context;
	private Function<URI, Action> prototypeProvider;

	public EcoreNodeProcessorFactory(Context context, Function<URI,Action> prototypeProvider) {
		this.context = context;
		this.prototypeProvider = prototypeProvider;
	}
	
	@Processor("target.eClass().name == 'EPackage'")
	public EPackageNodeProcessor createEPackageNodeProcessor(NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config) {
		return new EPackageNodeProcessor(config, context, prototypeProvider);
	}	
	
	@Processor("target.eClass().name == 'EClass'")
	public EClassNodeProcessor createEClassNodeProcessor(NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config) {
		return new EClassNodeProcessor(config, context, prototypeProvider);
	}	

}
