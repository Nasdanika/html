package org.nasdanika.html.ecore.gen.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.nasdanika.common.Reflector;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.emf.EObjectNodeProcessor;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.WidgetFactory;
import org.nasdanika.html.model.app.graph.emf.EObjectReflectiveProcessorFactory;
import org.nasdanika.ncore.util.NcoreUtil;

/**
 * Node processor factory to use with {@link EObjectReflectiveProcessorFactory}.
 * @author Pavel
 *
 */
public class EcoreNodeProcessorFactory extends Reflector {
	
	private Context context;
	private java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider;
	
	protected java.util.function.Function<ProgressMonitor, Action> getPrototypeProvider(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return progressMonitor -> {
			if (prototypeProvider != null) {
				for (URI identifier: NcoreUtil.getIdentifiers(((EObjectNode) config.getElement()).getTarget())) {
					Action prototype = prototypeProvider.apply(identifier, progressMonitor);
					if (prototype != null) {
						return prototype;
					}				
				}			
			}
			return null;
		};		
	}
	
	protected List<AnnotatedElementRecord> annotatedElementRecords = new ArrayList<>();

	/**
	 * 
	 * @param context
	 * @param reflectiveFactories Objects with annotated methods for creating processors. 
	 */
	public EcoreNodeProcessorFactory(
			Context context, 
			java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider,
			Object... targets)  {
		this.context = context;
		this.prototypeProvider = prototypeProvider;
		for (Object target: targets) {
			getAnnotatedElementRecords(target).forEach(annotatedElementRecords::add);
		}
	}
	
	@EObjectNodeProcessor(type = EPackage.class)
	public Object createEPackageNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		Optional<AnnotatedElementRecord> fo = annotatedElementRecords
			.stream()
			.filter(aer -> {
				EPackageNodeProcessorFactory ann = aer.getAnnotation(EPackageNodeProcessorFactory.class);
				return ann != null && ann.value().equals(((EPackage) ((EObjectNode) config.getElement()).getTarget()).getNsURI());
			}).findFirst();
		
		if (fo.isPresent()) {
			// TODO - action provider from annotation
			
			return fo.get().invoke(config, getPrototypeProvider(config));
		}
		
		return new EPackageNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EClass.class)
	public EClassNodeProcessor createEClassNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EClassNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EDataType.class)
	public EDataTypeNodeProcessor<EDataType> createEDataTypeNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EDataTypeNodeProcessor<EDataType>(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EEnum.class)
	public EEnumNodeProcessor createEEnumNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EEnumNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EAnnotation.class)
	public EAnnotationNodeProcessor createEAnnotationNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EAnnotationNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EEnumLiteral.class)
	public EEnumLiteralNodeProcessor createEEnumLiteralNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EEnumLiteralNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EOperation.class)
	public EOperationNodeProcessor createEOperationNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EOperationNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EParameter.class)
	public EParameterNodeProcessor createEParameterNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EParameterNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EAttribute.class)
	public EAttributeNodeProcessor createEAttributeNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EAttributeNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EReference.class)
	public EReferenceNodeProcessor createEReferenceNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EReferenceNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = ETypeParameter.class)
	public ETypeParameterNodeProcessor createETypeParameterNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new ETypeParameterNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EGenericType.class)
	public EGenericTypeNodeProcessor createEGenericTypeNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config) {
		return new EGenericTypeNodeProcessor(config, context, getPrototypeProvider(config));
	}	

}
