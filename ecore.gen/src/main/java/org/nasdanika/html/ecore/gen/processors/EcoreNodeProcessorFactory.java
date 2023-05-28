package org.nasdanika.html.ecore.gen.processors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.emf.EObjectNodeProcessor;
import org.nasdanika.graph.processor.emf.EObjectNodeProcessorReflectiveFactory;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.WidgetFactory;
import org.nasdanika.html.model.app.graph.emf.EObjectReflectiveProcessorFactory;

/**
 * Node processor factory to use with {@link EObjectReflectiveProcessorFactory}.
 * @author Pavel
 *
 */
public class EcoreNodeProcessorFactory extends EObjectNodeProcessorReflectiveFactory<Object, WidgetFactory, WidgetFactory, Registry<URI>> {
	
	private Context context;
	private java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider;

	/**
	 * 
	 * @param context
	 * @param reflectiveFactories Objects with annotated methods for creating processors. 
	 */
	public EcoreNodeProcessorFactory(Context context, java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider) {
		this.context = context;
		this.prototypeProvider = prototypeProvider;
	}
	
	@EObjectNodeProcessor(type = EPackage.class)
	public EPackageNodeProcessor createEPackageNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EPackageNodeProcessor(config, context, prototypeProvider);
	}	
	
	@EObjectNodeProcessor(type = EClass.class)
	public EClassNodeProcessor createEClassNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EClassNodeProcessor(config, context, prototypeProvider);
	}	
	
	@EObjectNodeProcessor(type = EDataType.class)
	public EDataTypeNodeProcessor<EDataType> createEDataTypeNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EDataTypeNodeProcessor<EDataType>(config, context, prototypeProvider);
	}	
	
	@EObjectNodeProcessor(type = EEnum.class)
	public EEnumNodeProcessor createEEnumNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EEnumNodeProcessor(config, context, prototypeProvider);
	}	
	
	@EObjectNodeProcessor(type = EAnnotation.class)
	public EAnnotationNodeProcessor createEAnnotationNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EAnnotationNodeProcessor(config, context, prototypeProvider);
	}	
	
	@EObjectNodeProcessor(type = EEnumLiteral.class)
	public EEnumLiteralNodeProcessor createEEnumLiteralNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EEnumLiteralNodeProcessor(config, context, prototypeProvider);
	}	
	
	@EObjectNodeProcessor(type = EOperation.class)
	public EOperationNodeProcessor createEOperationNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EOperationNodeProcessor(config, context, prototypeProvider);
	}	
	
	@EObjectNodeProcessor(type = EParameter.class)
	public EParameterNodeProcessor createEParameterNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EParameterNodeProcessor(config, context, prototypeProvider);
	}	
	
	@EObjectNodeProcessor(type = EAttribute.class)
	public EAttributeNodeProcessor createEAttributeNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EAttributeNodeProcessor(config, context, prototypeProvider);
	}	
	
	@EObjectNodeProcessor(type = EReference.class)
	public EReferenceNodeProcessor createEReferenceNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EReferenceNodeProcessor(config, context, prototypeProvider);
	}	
	
	@EObjectNodeProcessor(type = ETypeParameter.class)
	public ETypeParameterNodeProcessor createETypeParameterNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new ETypeParameterNodeProcessor(config, context, prototypeProvider);
	}	
	
	@EObjectNodeProcessor(type = EGenericType.class)
	public EGenericTypeNodeProcessor createEGenericTypeNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EGenericTypeNodeProcessor(config, context, prototypeProvider);
	}	

}
