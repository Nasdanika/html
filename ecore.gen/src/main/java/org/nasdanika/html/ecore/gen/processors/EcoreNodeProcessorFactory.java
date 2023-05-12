package org.nasdanika.html.ecore.gen.processors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EDataType;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.Factory;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.Processor;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.graph.WidgetFactory;
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
	private java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider;

	public EcoreNodeProcessorFactory(Context context, java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider) {
		this.context = context;
		this.prototypeProvider = prototypeProvider;
	}
	
	@Processor("target.eClass().name == 'EPackage'")
	public EPackageNodeProcessor createEPackageNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EPackageNodeProcessor(config, context, prototypeProvider);
	}	
	
	@Processor("target.eClass().name == 'EClass'")
	public EClassNodeProcessor createEClassNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EClassNodeProcessor(config, context, prototypeProvider);
	}	
	
	@Processor("target.eClass().name == 'EDataType'")
	public EDataTypeNodeProcessor<EDataType> createEDataTypeNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EDataTypeNodeProcessor<EDataType>(config, context, prototypeProvider);
	}	
	
	@Processor("target.eClass().name == 'EEnum'")
	public EEnumNodeProcessor createEEnumNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EEnumNodeProcessor(config, context, prototypeProvider);
	}	
	
	@Processor("target.eClass().name == 'EAnnotation'")
	public EAnnotationNodeProcessor createEAnnotationNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EAnnotationNodeProcessor(config, context, prototypeProvider);
	}	
	
	@Processor("target.eClass().name == 'EEnumLiteral'")
	public EEnumLiteralNodeProcessor createEEnumLiteralNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EEnumLiteralNodeProcessor(config, context, prototypeProvider);
	}	
	
	@Processor("target.eClass().name == 'EOperation'")
	public EOperationNodeProcessor createEOperationNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EOperationNodeProcessor(config, context, prototypeProvider);
	}	
	
	@Processor("target.eClass().name == 'EParameter'")
	public EParameterNodeProcessor createEParameterNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EParameterNodeProcessor(config, context, prototypeProvider);
	}	
	
	@Processor("target.eClass().name == 'EAttribute'")
	public EAttributeNodeProcessor createEAttributeNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EAttributeNodeProcessor(config, context, prototypeProvider);
	}	
	
	@Processor("target.eClass().name == 'EReference'")
	public EReferenceNodeProcessor createEReferenceNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EReferenceNodeProcessor(config, context, prototypeProvider);
	}	
	
	@Processor("target.eClass().name == 'ETypeParameter'")
	public ETypeParameterNodeProcessor createETypeParameterNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new ETypeParameterNodeProcessor(config, context, prototypeProvider);
	}	
	
	@Processor("target.eClass().name == 'EGenericType'")
	public EGenericTypeNodeProcessor createEGenericTypeNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EGenericTypeNodeProcessor(config, context, prototypeProvider);
	}	

}
