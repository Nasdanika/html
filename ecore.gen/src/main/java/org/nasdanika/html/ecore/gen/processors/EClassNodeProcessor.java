package org.nasdanika.html.ecore.gen.processors;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.emf.EOperationConnection;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.OutgoingEndpoint;
import org.nasdanika.html.ecore.gen.ReifiedTypeConnection;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.gen.DynamicTableBuilder;
import org.nasdanika.html.model.app.graph.Registry;
import org.nasdanika.html.model.app.graph.WidgetFactory;
import org.nasdanika.html.model.app.graph.emf.OutgoingReferenceBuilder;

public class EClassNodeProcessor extends EClassifierNodeProcessor<EClass> {
	
	public class ReifiedTypeSelector {
		
		private Object selector;
		
		ReifiedTypeSelector(Object selector) {
			this.selector = selector;
		}
		
		public Object getSelector() {
			return selector;
		}
		
		public WidgetFactory getReifiedTypeWidgetFactory(EGenericType eGenericType) {
			return reifiedTypesWidgetFactories.get(eGenericType);
		}
		
		public ReifiedTypeSelector createSelector(Object selector) {
			return new ReifiedTypeSelector(selector);
		}
		
	}

	public EClassNodeProcessor(
			NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config,
			Context context,
			java.util.function.Function<ProgressMonitor, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
	}
		
	@Override
	protected boolean isCallOutgoingReferenceLabelsSuppliers(EReference eReference) {
		if (eReference == EcorePackage.Literals.ECLASS__EATTRIBUTES) {
			return true;
		}
		if (eReference == EcorePackage.Literals.ECLASS__EREFERENCES) {
			return true;
		}
		if (eReference == EcorePackage.Literals.ECLASS__EOPERATIONS) {
			return true;
		}
		return super.isCallOutgoingReferenceLabelsSuppliers(eReference);
	}
	
	/**
	 * Effective generic type for structural features
	 */
	protected String typeLink(EReferenceConnection connection, WidgetFactory widgetFactory, ProgressMonitor progressMonitor) {
		EObject tt = connection.getTarget().getTarget();
		if (tt instanceof EStructuralFeature) {
			EStructuralFeature feature = (EStructuralFeature) tt;
			EGenericType featureType = getTarget().getFeatureType(feature);
			if (featureType != null) {
				String typeName = featureType.getERawType().getName(); 
				String typeNameComment = "<!-- " + typeName + "--> ";
				WidgetFactory genericTypeWidgetFactory = featureGenericTypesWidgetFactories.get(tt);
				if (genericTypeWidgetFactory != null) {
					String linkStr = genericTypeWidgetFactory.createLinkString(progressMonitor);
					if (linkStr != null) {
						return typeNameComment + linkStr;			
					}
				}
				return typeNameComment + typeName;
			}
		}
		return super.typeLink(connection, widgetFactory, progressMonitor);
	}
	
	private Map<EStructuralFeature, WidgetFactory> featureGenericTypesWidgetFactories = new HashMap<>();
	
	@OutgoingEndpoint("operation.name == 'getFeatureType'")
	public final void setFeatureTypeEndpoint(EOperationConnection connection, WidgetFactory genericTypeWidgetFactory) {
		featureGenericTypesWidgetFactories.put((EStructuralFeature) connection.getArguments().get(0), genericTypeWidgetFactory);
	}
	
	private Map<EGenericType,WidgetFactory> reifiedTypesWidgetFactories = new HashMap<>();
	
	@OutgoingEndpoint
	public final void setRefiedTypeEndpoint(ReifiedTypeConnection connection, WidgetFactory reifiedTypeWidgetFactory) {
		reifiedTypesWidgetFactories.put(connection.getGenericType(), reifiedTypeWidgetFactory);
	}	
	
	
	// === Attributes ===

	//	TODO getEIDAttribute()
	
	/**
	 * Returns attributes action, creates if necessary. Matches by location.
	 * @param parent
	 * @return
	 */
	protected Action getAttributesAction(Action parent) {
		Action pAction = (Action) parent;
		return pAction.getNavigation()
			.stream()
			.filter(e -> e instanceof Action && "attributes.html".equals(((Action) e).getLocation()))
			.findFirst()
			.map(Action.class::cast)
			.orElseGet(() -> {
				Action attributesAction = AppFactory.eINSTANCE.createAction();
				attributesAction.setText("Attributes");
				attributesAction.setIcon("https://cdn.jsdelivr.net/gh/Nasdanika/html@master/ecore.gen/web-resources/icons/EAttribute.gif");
				attributesAction.setLocation("attributes.html");
				pAction.getNavigation().add(attributesAction);
				return attributesAction;
			});
	}

	@OutgoingReferenceBuilder(EcorePackage.ECLASS__EATTRIBUTES)
	public void buildEAttributesOutgoingReference(
			List<Entry<EReferenceConnection, WidgetFactory>> referenceOutgoingEndpoints, 
			Collection<Label> labels,
			Map<EReferenceConnection, Collection<Label>> outgoingLabels, 
			ProgressMonitor progressMonitor) {
		
		// Own attributes 
		for (Label tLabel: labels) {
			if (tLabel instanceof Action) {
				Action attributesAction = getAttributesAction((Action) tLabel);
				EList<Action> tAnonymous = attributesAction.getAnonymous();
				for (Entry<EReferenceConnection, Collection<Label>> re: outgoingLabels.entrySet()) {
					for (Label childLabel: re.getValue()) {
						if (childLabel instanceof Action && !((Action) childLabel).getContent().isEmpty()) {
							tAnonymous.add((Action) childLabel);
						}
					}
				}
			}
		}
	}		

	@OutgoingReferenceBuilder(EcorePackage.ECLASS__EALL_ATTRIBUTES)
	public void buildEAllAttributesOutgoingReference(
			List<Entry<EReferenceConnection, WidgetFactory>> referenceOutgoingEndpoints, 
			Collection<Label> labels,
			Map<EReferenceConnection, Collection<Label>> outgoingLabels, 
			ProgressMonitor progressMonitor) {

		// A page with a dynamic attributes table and links to attribute pages for attributes with documentation.
		for (Label label: labels) {
			if (label instanceof Action) {										
				DynamicTableBuilder<Entry<EReferenceConnection, WidgetFactory>> attributesTableBuilder = new DynamicTableBuilder<>("nsd-ecore-doc-table");
				attributesTableBuilder.setProperty("transitive-label", "Inherited");
				buildStructuralFeatureColumns(attributesTableBuilder, progressMonitor);
				
				org.nasdanika.html.model.html.Tag attributesTable = attributesTableBuilder.build(
						referenceOutgoingEndpoints.stream().sorted((a,b) -> {
							ENamedElement ane = (ENamedElement) a.getKey().getTarget().getTarget();
							ENamedElement bne = (ENamedElement) b.getKey().getTarget().getTarget();
							return ane.getName().compareTo(bne.getName());
						}).collect(Collectors.toList()),  
						"eclass-attributes", 
						"attributes-table", 
						progressMonitor);
				getAttributesAction((Action) label).getContent().add(attributesTable);
			}
		}
	}
	
	// === References ===
		
	//	getEAllContainments()
	
	// TODO - all incoming references
	
	/**
	 * Returns references action, creates if necessary. Matches by location.
	 * @param parent
	 * @return
	 */
	protected Action getReferencesAction(Action parent) {
		Action pAction = (Action) parent;
		return pAction.getNavigation()
			.stream()
			.filter(e -> e instanceof Action && "references.html".equals(((Action) e).getLocation()))
			.findFirst()
			.map(Action.class::cast)
			.orElseGet(() -> {
				Action referencesAction = AppFactory.eINSTANCE.createAction();
				referencesAction.setText("References");
				referencesAction.setIcon("https://cdn.jsdelivr.net/gh/Nasdanika/html@master/ecore.gen/web-resources/icons/EReference.gif");
				referencesAction.setLocation("references.html");
				pAction.getNavigation().add(referencesAction);
				return referencesAction;
			});
	}

	@OutgoingReferenceBuilder(EcorePackage.ECLASS__EREFERENCES)
	public void buildReferencesOutgoingReference(
			List<Entry<EReferenceConnection, WidgetFactory>> referenceOutgoingEndpoints, 
			Collection<Label> labels,
			Map<EReferenceConnection, Collection<Label>> outgoingLabels, 
			ProgressMonitor progressMonitor) {
			// Own references 
		for (Label tLabel: labels) {
			if (tLabel instanceof Action) {
				Action referencesAction = getReferencesAction((Action) tLabel);
				EList<Action> tAnonymous = referencesAction.getAnonymous();
				for (Entry<EReferenceConnection, Collection<Label>> re: outgoingLabels.entrySet()) {
					for (Label childLabel: re.getValue()) {
						if (childLabel instanceof Action && !((Action) childLabel).getContent().isEmpty()) {
							tAnonymous.add((Action) childLabel);
						}
					}
				}
			}
		}
	}		
	
	@OutgoingReferenceBuilder(EcorePackage.ECLASS__EALL_REFERENCES)
	public void buildEAllReferencesOutgoingReference(
			List<Entry<EReferenceConnection, WidgetFactory>> referenceOutgoingEndpoints, 
			Collection<Label> labels,
			Map<EReferenceConnection, Collection<Label>> outgoingLabels, 
			ProgressMonitor progressMonitor) {
		
		// A page with a dynamic references table and links to reference pages for references with documentation. 
		for (Label label: labels) {
			if (label instanceof Action) {										
				DynamicTableBuilder<Entry<EReferenceConnection, WidgetFactory>> referencesTableBuilder = new DynamicTableBuilder<>("nsd-ecore-doc-table");
				referencesTableBuilder.setProperty("transitive-label", "Inherited");
				buildStructuralFeatureColumns(referencesTableBuilder, progressMonitor);
// TODO										
//					getEKeys()
//					getEOpposite()
//					getEReferenceType()
//					isContainer()
//					isContainment()
//					isResolveProxies()
				
				org.nasdanika.html.model.html.Tag referencesTable = referencesTableBuilder.build(
						referenceOutgoingEndpoints.stream().sorted((a,b) -> {
							ENamedElement ane = (ENamedElement) a.getKey().getTarget().getTarget();
							ENamedElement bne = (ENamedElement) b.getKey().getTarget().getTarget();
							return ane.getName().compareTo(bne.getName());
						}).collect(Collectors.toList()),  
						"eclass-references", 
						"references-table", 
						progressMonitor);
				getReferencesAction((Action) label).getContent().add(referencesTable);
			}
		}
	}
	
	// === Operations ===
	
	/**
	 * Returns operations action, creates if necessary. Matches by location.
	 * @param parent
	 * @return
	 */
	protected Action getOperationsAction(Action parent) {
		Action pAction = (Action) parent;
		return pAction.getNavigation()
			.stream()
			.filter(e -> e instanceof Action && "operations.html".equals(((Action) e).getLocation()))
			.findFirst()
			.map(Action.class::cast)
			.orElseGet(() -> {
				Action operationsAction = AppFactory.eINSTANCE.createAction();
				operationsAction.setText("Operations");
				operationsAction.setIcon("https://cdn.jsdelivr.net/gh/Nasdanika/html@master/ecore.gen/web-resources/icons/EOperation.gif");
				operationsAction.setLocation("operations.html");
				pAction.getNavigation().add(operationsAction);
				return operationsAction;
			});
	}
	

	@OutgoingReferenceBuilder(EcorePackage.ECLASS__EOPERATIONS)
	public void buildOperationsOutgoingReference(
			List<Entry<EReferenceConnection, WidgetFactory>> referenceOutgoingEndpoints, 
			Collection<Label> labels,
			Map<EReferenceConnection, Collection<Label>> outgoingLabels, 
			ProgressMonitor progressMonitor) {
			// Own references 
		for (Label tLabel: labels) {
			if (tLabel instanceof Action) {
				Action operationsAction = getOperationsAction((Action) tLabel);
				EList<Action> tAnonymous = operationsAction.getAnonymous();
				for (Entry<EReferenceConnection, Collection<Label>> re: outgoingLabels.entrySet()) {
					for (Label childLabel: re.getValue()) {
						if (childLabel instanceof Action && !((Action) childLabel).getContent().isEmpty()) {
							tAnonymous.add((Action) childLabel);
						}
					}
				}
			}
		}
	}		

	@OutgoingReferenceBuilder(EcorePackage.ECLASS__EALL_OPERATIONS)
	public void buildEAllOperationsOutgoingReference(
			List<Entry<EReferenceConnection, WidgetFactory>> referenceOutgoingEndpoints, 
			Collection<Label> labels,
			Map<EReferenceConnection, Collection<Label>> outgoingLabels, 
			ProgressMonitor progressMonitor) {
		// A page with a dynamic operations table and links to operations pages for operations with documentation. 
		for (Label label: labels) {
			if (label instanceof Action) {					
				DynamicTableBuilder<Entry<EReferenceConnection, WidgetFactory>> operationsTableBuilder = new DynamicTableBuilder<>("nsd-ecore-doc-table");
				operationsTableBuilder.setProperty("transitive-label", "Inherited");
				buildTypedElementColumns(operationsTableBuilder, progressMonitor);					
				operationsTableBuilder
					.addStringColumnBuilder("declaring-class", true, true, "Declaring Class", endpoint -> declaringClassLink(endpoint.getKey(), endpoint.getValue(), progressMonitor))
					.addStringColumnBuilder("parameters", true, false, "Parameters", endpoint -> endpoint.getValue().createWidgetString(new ReifiedTypeSelector(EcorePackage.Literals.EOPERATION__EPARAMETERS), progressMonitor))
					.addStringColumnBuilder("exceptions", true, false, "Exceptions", endpoint -> endpoint.getValue().createWidgetString(new ReifiedTypeSelector(EcorePackage.Literals.EOPERATION__EGENERIC_EXCEPTIONS), progressMonitor))
					.addStringColumnBuilder("type-parameters", true, false, "Type Parameters", endpoint -> endpoint.getValue().createWidgetString(new ReifiedTypeSelector(EcorePackage.Literals.EOPERATION__ETYPE_PARAMETERS), progressMonitor));		
				
				// TODO - overrides, not visible by default and not sortable
				
				org.nasdanika.html.model.html.Tag operationsTable = operationsTableBuilder.build(
						referenceOutgoingEndpoints.stream().sorted((a,b) -> {
							ENamedElement ane = (ENamedElement) a.getKey().getTarget().getTarget();
							ENamedElement bne = (ENamedElement) b.getKey().getTarget().getTarget();
							return ane.getName().compareTo(bne.getName());
						}).collect(Collectors.toList()),  
						"eclass-operations", 
						"operations-table", 
						progressMonitor);
				getOperationsAction((Action) label).getContent().add(operationsTable);
			}
		}			
	}
		
	// === Inheritance ===
	
	/**
	 * Returns inheritance action (subtypes and supertypes), creates if necessary. Matches by location.
	 * @param parent
	 * @return
	 */
	protected Action getInheritanceAction(Action parent) {
		Action pAction = (Action) parent;
		return pAction.getNavigation()
			.stream()
			.filter(e -> e instanceof Action && "inheritance.html".equals(((Action) e).getLocation()))
			.findFirst()
			.map(Action.class::cast)
			.orElseGet(() -> {
				Action inheritanceAction = AppFactory.eINSTANCE.createAction();
				inheritanceAction.setText("Inheritance");
				inheritanceAction.setIcon("https://cdn.jsdelivr.net/gh/Nasdanika/html@master/ecore.gen/web-resources/icons/EGenericSuperType.gif");
				inheritanceAction.setLocation("inheritance.html");
				pAction.getNavigation().add(inheritanceAction);
				return inheritanceAction;
			});
	}
	
	@OutgoingReferenceBuilder(EcorePackage.ECLASS__EALL_GENERIC_SUPER_TYPES)
	public void buildEAllGenericSuperTypesOutgoingReference(
			List<Entry<EReferenceConnection, WidgetFactory>> referenceOutgoingEndpoints, 
			Collection<Label> labels,
			Map<EReferenceConnection, Collection<Label>> outgoingLabels, 
			ProgressMonitor progressMonitor) {
		// A section with a dynamic super table and links to operations pages for operations with documentation. 
		for (Label label: labels) {
			if (label instanceof Action) {					
				DynamicTableBuilder<Entry<EReferenceConnection, WidgetFactory>> superTypesTableBuilder = new DynamicTableBuilder<>("nsd-ecore-doc-table");
				superTypesTableBuilder.setProperty("transitive-label", "All");
				superTypesTableBuilder
					.addStringColumnBuilder("name", true, false, "Name", endpoint -> targetNameLink(endpoint.getKey(), endpoint.getValue(), progressMonitor)) // TODO - bold for immediate supertypes  
					.addStringColumnBuilder("description", true, false, "Description", endpoint -> description(endpoint.getKey(), endpoint.getValue(), progressMonitor));
				
				// TODO - other things.
				
				org.nasdanika.html.model.html.Tag superTypesTable = superTypesTableBuilder.build(
						referenceOutgoingEndpoints,  
						"eclass-operations", 
						"operations-table", 
						progressMonitor);
				
				Action superTypesSection = AppFactory.eINSTANCE.createAction();
				superTypesSection.setText("Supertypes");
				superTypesSection.getContent().add(superTypesTable);
									
				getInheritanceAction((Action) label).getSections().add(superTypesSection);
			}
		}			
	}
	
}
