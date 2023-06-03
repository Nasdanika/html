package org.nasdanika.html.ecore.gen.processors;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reflector;
import org.nasdanika.common.Util;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.emf.EObjectNodeProcessor;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.WidgetFactory;
import org.nasdanika.html.model.app.graph.emf.EObjectReflectiveProcessorFactory;
import org.nasdanika.html.model.app.util.AppObjectLoaderSupplier;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.util.NcoreUtil;
import org.nasdanika.persistence.ObjectLoaderResource;

/**
 * Node processor factory to use with {@link EObjectReflectiveProcessorFactory}.
 * @author Pavel
 *
 */
public class EcoreNodeProcessorFactory extends Reflector {
			
	private Context context;
	private Consumer<Diagnostic> diagnosticConsumer;
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
			return AppFactory.eINSTANCE.createAction();
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
			Consumer<Diagnostic> diagnosticConsumer,
			Object... targets)  {
		this.context = context;
		this.prototypeProvider = prototypeProvider;
		this.diagnosticConsumer = diagnosticConsumer;
		for (Object target: targets) {
			getAnnotatedElementRecords(target).forEach(annotatedElementRecords::add);
		}
	}
	
	protected Action loadActionPrototype(
			String spec,
			String specRef, 
			URI base, 
			ProgressMonitor progressMonitor) {

		if (!Util.isBlank(spec) && !Util.isBlank(specRef)) {
			throw new IllegalArgumentException("actionPrototype and actionPrototypeRef are mutually exclusive");
		}
		
		URI specURI;		
		if (Util.isBlank(spec)) {
			if (Util.isBlank(specRef)) {
				return null;
			}
			
			specURI = URI.createURI(specRef);
			if (base != null) {
				specURI = specURI.resolve(base);
			}
		} else {
			specURI = ObjectLoaderResource.encode(spec, "YAML", base, null);
		}
		return (Action) AppObjectLoaderSupplier.loadObject(specURI, diagnosticConsumer, context, progressMonitor);
	}
	
	protected java.util.function.Function<ProgressMonitor, Action> getPrototypeProvider(
			NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config,
			URI baseURI,
			String actionPrototypeSpec,
			String actionPrototypeRef,
			String description,
			String icon,
			String documentation,
			ProgressMonitor progressMonitor) {
		
		Action actionPrototype = loadActionPrototype(
				actionPrototypeSpec,
				actionPrototypeRef,
				baseURI, 
				progressMonitor); 
		
		return pm -> {
			Action ret;
			if (actionPrototype == null) {
				ret = getPrototypeProvider(config).apply(pm);
			} else {
				ret = EcoreUtil.copy(actionPrototype);
				ret.setUuid(UUID.randomUUID().toString());
				TreeIterator<EObject> cit = ret.eAllContents();
				while (cit.hasNext()) {
					EObject next = cit.next();
					if (next instanceof ModelElement) {
						((ModelElement) next).setUuid(UUID.randomUUID().toString());
					}
				}
			}
			
			// TODO - description, icon, documentation, text (label), decorator?
			
			return ret;						
		};
	}
		
	@EObjectNodeProcessor(type = EPackage.class)
	public Object createEPackageNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		Optional<AnnotatedElementRecord> fo = annotatedElementRecords
			.stream()
			.filter(aer -> {
				EPackageNodeProcessorFactory ann = aer.getAnnotation(EPackageNodeProcessorFactory.class);
				return ann != null && ann.value().equals(((EPackage) ((EObjectNode) config.getElement()).getTarget()).getNsURI());
			}).findFirst();
		
		if (fo.isPresent()) {
			AnnotatedElementRecord annotatedElementRecord = fo.get();
			EPackageNodeProcessorFactory ann = annotatedElementRecord.getAnnotation(EPackageNodeProcessorFactory.class);
			AnnotatedElement annotatedElement = annotatedElementRecord.getAnnotatedElement();
			Class<?> declaringClass = annotatedElement instanceof Method ? ((Method) annotatedElement).getDeclaringClass() : ((Field) annotatedElement).getDeclaringClass();
			URI baseURI = URI.createURI(Util.CLASSPATH_URL_PREFIX + declaringClass.getName().replace('.', '/'));
			return annotatedElementRecord.invoke(
					config, 
					getPrototypeProvider(
							config, 
							baseURI,
							ann.actionPrototype(), 
							ann.actionPrototypeRef(),
							ann.description(), 
							ann.icon(), 
							ann.documentation(),
							progressMonitor), 
					progressMonitor);
		}
		
		return new EPackageNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EClass.class)
	public EClassNodeProcessor createEClassNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return new EClassNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EDataType.class)
	public EDataTypeNodeProcessor<EDataType> createEDataTypeNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return new EDataTypeNodeProcessor<EDataType>(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EEnum.class)
	public EEnumNodeProcessor createEEnumNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return new EEnumNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EAnnotation.class)
	public EAnnotationNodeProcessor createEAnnotationNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return new EAnnotationNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EEnumLiteral.class)
	public EEnumLiteralNodeProcessor createEEnumLiteralNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return new EEnumLiteralNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EOperation.class)
	public EOperationNodeProcessor createEOperationNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return new EOperationNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EParameter.class)
	public EParameterNodeProcessor createEParameterNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return new EParameterNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EAttribute.class)
	public EAttributeNodeProcessor createEAttributeNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return new EAttributeNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EReference.class)
	public EReferenceNodeProcessor createEReferenceNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return new EReferenceNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = ETypeParameter.class)
	public ETypeParameterNodeProcessor createETypeParameterNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return new ETypeParameterNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EGenericType.class)
	public EGenericTypeNodeProcessor createEGenericTypeNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return new EGenericTypeNodeProcessor(config, context, getPrototypeProvider(config));
	}	

}
