package org.nasdanika.html.ecore.gen.processors;

import org.nasdanika.common.Context;
import org.nasdanika.graph.processor.Factory;
import org.nasdanika.graph.processor.Processor;
import org.nasdanika.html.model.app.graph.emf.EObjectReflectiveProcessorFactory;

/**
 * Node processor factory to use with {@link EObjectReflectiveProcessorFactory}.
 * @author Pavel
 *
 */
@Factory("target.eClass().getEPackage().getNsURI() == 'http://www.eclipse.org/emf/2002/Ecore'")
public class EcoreNodeProcessorFactory {
	
	private Context context;

	public EcoreNodeProcessorFactory(Context context) {
		this.context = context;
	}
	
	@Processor("target.eClass().name == 'EPackage'")
	public EPackageNodeProcessor createEPackageNodeProcessor() {
		return new EPackageNodeProcessor(context);
	}	

}
