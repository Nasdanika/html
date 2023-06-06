package org.nasdanika.html.ecore.gen.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reflector;
import org.nasdanika.common.Util;
import org.nasdanika.emf.persistence.MarkerFactory;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Text;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.emf.EObjectNodeProcessor;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
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
	
	@Override
	protected Predicate<Object> getTargetPredicate(Object target) {
		EPackageNodeProcessorFactory pnfa = target.getClass().getAnnotation(EPackageNodeProcessorFactory.class);
		EClassifierNodeProcessorFactory cnfa = target.getClass().getAnnotation(EClassifierNodeProcessorFactory.class);		
		return obj -> {
			EObject eObj = ((EObjectNode) obj).getTarget();
			if (pnfa != null) {
				EPackage ePackage = null;
				if (eObj instanceof EPackage) {
					ePackage = (EPackage) eObj;
				} else if (eObj instanceof EClassifier) {
					ePackage = ((EClassifier) eObj).getEPackage();
				} else if (eObj instanceof EStructuralFeature) {
					ePackage = ((EStructuralFeature) eObj).getEContainingClass().getEPackage();
				} else if (eObj instanceof EOperation) {
					ePackage = ((EOperation) eObj).getEContainingClass().getEPackage();
				} else if (eObj instanceof EParameter) {
					ePackage = ((EParameter) eObj).getEOperation().getEContainingClass().getEPackage();
				}
								
				if (ePackage != null && !pnfa.nsURI().equals(ePackage.getNsURI())) {
					return false;
				}
			}
			
			if (cnfa != null) {
				EClassifier eClassifier = null;
				if (eObj instanceof EClassifier) {
					eClassifier = (EClassifier) eObj;
				} else if (eObj instanceof EStructuralFeature) {
					eClassifier = ((EStructuralFeature) eObj).getEContainingClass();
				} else if (eObj instanceof EOperation) {
					eClassifier = ((EOperation) eObj).getEContainingClass();
				} else if (eObj instanceof EParameter) {
					eClassifier = ((EParameter) eObj).getEOperation().getEContainingClass();
				} else if (eObj instanceof EEnumLiteral) {
					eClassifier = ((EEnumLiteral) eObj).getEEnum();
				}
				
				if (eClassifier != null) {
					if (!Util.isBlank(cnfa.nsURI()) && !cnfa.nsURI().equals(eClassifier.getEPackage().getNsURI())) {
						return false;
					}
					if (!Util.isBlank(cnfa.name()) && !cnfa.name().equals(eClassifier.getName())) {
						return false;
					}
				}				
			}
			
			return true;			
		};
	}
	
	protected Function<ProgressMonitor, Action> getPrototypeProvider(
			NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config,
			URI baseURI,
			String actionPrototypeSpec,
			String actionPrototypeRef,
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
				ret = EcoreNodeProcessorFactory.this.getPrototypeProvider(config).apply(pm);
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
			
			if (!Util.isBlank(documentation)) {
				Markdown markdown = ContentFactory.eINSTANCE.createMarkdown();
				Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();
				Text text = ContentFactory.eINSTANCE.createText();
				text.setContent(documentation);
				interpolator.setSource(text);
				markdown.setSource(interpolator);
				markdown.setStyle(true);
				
				// Creating a marker with EObject resource location for resource resolution in Markdown
				if (baseURI != null) {
					org.nasdanika.ncore.Marker marker = context.get(MarkerFactory.class, MarkerFactory.INSTANCE).createMarker(baseURI.toString(), progressMonitor);
					markdown.getMarkers().add(marker); 
				}
				ret.getContent().add(markdown);
			}
			
			return ret;
		};
	}
	
	protected BiConsumer<Label, ProgressMonitor> getLabelConfigurator(
			String label,
			String icon,
			String description,
			ProgressMonitor progressMonitor) {
		
		return (lbl, pm) -> {
			if (!Util.isBlank(label)) {
				lbl.setText(label);
			}
			if (!Util.isBlank(icon)) {
				lbl.setIcon(icon);
			}
			if (!Util.isBlank(description)) {
				lbl.setTooltip(description);
			}					
		};
	}
	
		
	@EObjectNodeProcessor(type = EPackage.class)
	public Object createEPackageNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		Optional<AnnotatedElementRecord> fo = annotatedElementRecords
			.stream()
			.filter(aer -> aer.test(config.getElement()))
			.filter(aer -> {
				EPackageNodeProcessorFactory ann = aer.getAnnotation(EPackageNodeProcessorFactory.class);
				return ann != null && (Util.isBlank(ann.nsURI()) || ann.nsURI().equals(((EPackage) ((EObjectNode) config.getElement()).getTarget()).getNsURI()));
			}).findFirst();
		
		if (fo.isPresent()) {
			AnnotatedElementRecord annotatedElementRecord = fo.get();
			EPackageNodeProcessorFactory ann = annotatedElementRecord.getAnnotation(EPackageNodeProcessorFactory.class);
			
			return annotatedElementRecord.invoke(
					config, 
					getPrototypeProvider(
							config, 
							annotatedElementRecord.getBaseURI(),
							ann.actionPrototype(), 
							ann.actionPrototypeRef(),
							ann.documentation(),
							progressMonitor),
					getLabelConfigurator(
							ann.label(),
							ann.icon(), 
							ann.description(),
							progressMonitor),
					progressMonitor);
		}
		
		return new EPackageNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EClass.class)
	public Object createEClassNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return createEClassifierNodeProcessor(config, () -> new EClassNodeProcessor(config, context, getPrototypeProvider(config)), progressMonitor);
	}

	protected Object createEClassifierNodeProcessor(
			NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config,
			Supplier<Object> fallback,
			ProgressMonitor progressMonitor) {
		Optional<AnnotatedElementRecord> fo = annotatedElementRecords
				.stream()
				.filter(aer -> aer.test(config.getElement()))
				.filter(aer -> {
					EClassifierNodeProcessorFactory ann = aer.getAnnotation(EClassifierNodeProcessorFactory.class);
					if (ann == null) {
						return false;
					}
					EClassifier target = (EClassifier) ((EObjectNode) config.getElement()).getTarget();
					String name = ann.name();
					if (!Util.isBlank(name) && !name.equals(target.getName())) {
						return false;
					}
					String nsURI = ann.nsURI();
					return Util.isBlank(nsURI) || nsURI.equals(target.getEPackage().getNsURI()) && ann.name().equals(target.getName());
				}).findFirst();
		
		if (fo.isEmpty()) {
			return fallback.get();
		}
		
		AnnotatedElementRecord annotatedElementRecord = fo.get();
		EClassifierNodeProcessorFactory ann = annotatedElementRecord.getAnnotation(EClassifierNodeProcessorFactory.class);
		return annotatedElementRecord.invoke(
				config, 
				getPrototypeProvider(
						config, 
						annotatedElementRecord.getBaseURI(),
						ann.actionPrototype(), 
						ann.actionPrototypeRef(),
						ann.documentation(),
						progressMonitor),
				getLabelConfigurator(
						ann.label(),
						ann.icon(), 
						ann.description(),
						progressMonitor),
				progressMonitor);
	}	
	
	@EObjectNodeProcessor(type = EDataType.class)
	public Object createEDataTypeNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return createEClassifierNodeProcessor(config, () -> new EDataTypeNodeProcessor<EDataType>(config, context, getPrototypeProvider(config)), progressMonitor);
	}
	
	@EObjectNodeProcessor(type = EEnum.class)
	public Object createEEnumNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return createEClassifierNodeProcessor(config, () -> new EEnumNodeProcessor(config, context, getPrototypeProvider(config)), progressMonitor);		
	}	

	// --- EClass members ---
	
	protected Object createEStructuralFeatureNodeProcessor(
			NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config,
			Supplier<Object> fallback,
			ProgressMonitor progressMonitor) {
		Optional<AnnotatedElementRecord> fo = annotatedElementRecords
				.stream()
				.filter(aer -> aer.test(config.getElement()))
				.filter(aer -> {
					EStructuralFeatureNodeProcessorFactory ann = aer.getAnnotation(EStructuralFeatureNodeProcessorFactory.class);
					if (ann == null) {
						return false;
					}
					EStructuralFeature target = (EStructuralFeature) ((EObjectNode) config.getElement()).getTarget();
					if (target.getFeatureID() != ann.featureID()) {
						return false;
					}
					String eClassName = ann.eClass();
					if (!Util.isBlank(eClassName) && !eClassName.equals(target.getEContainingClass().getName())) {
						return false;
					}
					
					String nsURI = ann.nsURI();
					return Util.isBlank(nsURI) || nsURI.equals(target.getEContainingClass().getEPackage().getNsURI());
				}).findFirst();
		
		if (fo.isEmpty()) {
			return fallback.get();
		}
		
		AnnotatedElementRecord annotatedElementRecord = fo.get();
		EStructuralFeatureNodeProcessorFactory ann = annotatedElementRecord.getAnnotation(EStructuralFeatureNodeProcessorFactory.class);
		return annotatedElementRecord.invoke(
				config, 
				getPrototypeProvider(
						config, 
						annotatedElementRecord.getBaseURI(),
						ann.actionPrototype(), 
						ann.actionPrototypeRef(),
						ann.documentation(),
						progressMonitor),
				getLabelConfigurator(
						ann.label(),
						ann.icon(), 
						ann.description(),
						progressMonitor),
				progressMonitor);
	}	
		
	@EObjectNodeProcessor(type = EAttribute.class)
	public Object createEAttributeNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return createEStructuralFeatureNodeProcessor(config, () -> new EAttributeNodeProcessor(config, context, getPrototypeProvider(config)), progressMonitor);		
	}	
	
	@EObjectNodeProcessor(type = EReference.class)
	public Object createEReferenceNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return createEStructuralFeatureNodeProcessor(config, () -> new EReferenceNodeProcessor(config, context, getPrototypeProvider(config)), progressMonitor);		
	}
	
	@EObjectNodeProcessor(type = EOperation.class)
	public Object createEOperationNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		Optional<AnnotatedElementRecord> fo = annotatedElementRecords
				.stream()
				.filter(aer -> aer.test(config.getElement()))
				.filter(aer -> {
					EOperationNodeProcessorFactory ann = aer.getAnnotation(EOperationNodeProcessorFactory.class);
					if (ann == null) {
						return false;
					}
					EOperation target = (EOperation) ((EObjectNode) config.getElement()).getTarget();
					if (target.getOperationID() != ann.operationID()) {
						return false;
					}
					String eClassName = ann.eClass();
					if (!Util.isBlank(eClassName) && !eClassName.equals(target.getEContainingClass().getName())) {
						return false;
					}
					
					String nsURI = ann.nsURI();
					return Util.isBlank(nsURI) || nsURI.equals(target.getEContainingClass().getEPackage().getNsURI());
				}).findFirst();
		
		if (fo.isPresent()) {
			AnnotatedElementRecord annotatedElementRecord = fo.get();
			EOperationNodeProcessorFactory ann = annotatedElementRecord.getAnnotation(EOperationNodeProcessorFactory.class);
			return annotatedElementRecord.invoke(
					config, 
					getPrototypeProvider(
							config, 
							annotatedElementRecord.getBaseURI(),
							ann.actionPrototype(), 
							ann.actionPrototypeRef(),
							ann.documentation(),
							progressMonitor),
					getLabelConfigurator(
							ann.label(),
							ann.icon(), 
							ann.description(),
							progressMonitor),
					progressMonitor);
		}				
		
		return new EOperationNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EParameter.class)
	public Object createEParameterNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		Optional<AnnotatedElementRecord> fo = annotatedElementRecords
				.stream()
				.filter(aer -> aer.test(config.getElement()))
				.filter(aer -> {
					EParameterNodeProcessorFactory ann = aer.getAnnotation(EParameterNodeProcessorFactory.class);
					if (ann == null) {
						return false;
					}					
					EParameter target = (EParameter) ((EObjectNode) config.getElement()).getTarget();
					if (!target.getName().equals(ann.name())) {
						return false;
					}
					if (target.getEOperation().getOperationID() != ann.operationID()) {
						return false;
					}
					String eClassName = ann.eClass();
					if (!Util.isBlank(eClassName) && !eClassName.equals(target.getEOperation().getEContainingClass().getName())) {
						return false;
					}
					
					String nsURI = ann.nsURI();
					return Util.isBlank(nsURI) || nsURI.equals(target.getEOperation().getEContainingClass().getEPackage().getNsURI());
				}).findFirst();
		
		if (fo.isPresent()) {
			AnnotatedElementRecord annotatedElementRecord = fo.get();
			EParameterNodeProcessorFactory ann = annotatedElementRecord.getAnnotation(EParameterNodeProcessorFactory.class);
			return annotatedElementRecord.invoke(
					config, 
					getPrototypeProvider(
							config, 
							annotatedElementRecord.getBaseURI(),
							ann.actionPrototype(), 
							ann.actionPrototypeRef(),
							ann.documentation(),
							progressMonitor),
					getLabelConfigurator(
							ann.label(),
							ann.icon(), 
							ann.description(),
							progressMonitor),
					progressMonitor);
		}
		
		return new EParameterNodeProcessor(config, context, getPrototypeProvider(config));
	}	

	// --- TODO ~~~
	
	@EObjectNodeProcessor(type = EEnumLiteral.class)
	public EEnumLiteralNodeProcessor createEEnumLiteralNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return new EEnumLiteralNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = ETypeParameter.class)
	public ETypeParameterNodeProcessor createETypeParameterNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return new ETypeParameterNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EGenericType.class)
	public EGenericTypeNodeProcessor createEGenericTypeNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return new EGenericTypeNodeProcessor(config, context, getPrototypeProvider(config));
	}	
	
	@EObjectNodeProcessor(type = EAnnotation.class)
	public EAnnotationNodeProcessor createEAnnotationNodeProcessor(NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, ProgressMonitor progressMonitor) {
		return new EAnnotationNodeProcessor(config, context, getPrototypeProvider(config));
	}	

}
