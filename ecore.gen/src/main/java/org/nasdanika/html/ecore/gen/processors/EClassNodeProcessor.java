package org.nasdanika.html.ecore.gen.processors;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.Tag;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.gen.AppAdapterFactory;
import org.nasdanika.html.model.app.gen.DynamicTableBuilder;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;

public class EClassNodeProcessor extends EClassifierNodeProcessor<EClass> {

	public EClassNodeProcessor(
			NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config,
			Context context,
			java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
	}
	
	protected String nameLink(EReferenceConnection connection, LabelFactory labelFactory, ProgressMonitor progressMonitor) {
		Label link = labelFactory.createLink(progressMonitor);
		if (link != null) {
			link.setIcon(null);
			Adapter adapter = AppAdapterFactory.INSTANCE.adapt(link, SupplierFactory.Provider.class);
			if (adapter instanceof SupplierFactory.Provider) {
				SupplierFactory<Tag> supplierFactory = ((SupplierFactory.Provider) adapter).getFactory(Tag.class);
				Supplier<Tag> supplier = supplierFactory.create(context);
				Tag tag = supplier.call(progressMonitor, null, Status.FAIL, Status.ERROR);
				return tag.toString();
			}
		}
		return ((ENamedElement) connection.getTarget().getTarget()).getName();
	}
	
	/**
	 * Returns references action, creates if necessary. Matches by location.
	 * @param parent
	 * @return
	 */
	private Action getReferencesAction(Action parent) {
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
	
	@Override
	protected void buildOutgoingReference(
			EReference eReference,
			List<Entry<EReferenceConnection, LabelFactory>> referenceOutgoingEndpoints, 
			Collection<Label> labels,
			Map<EReferenceConnection, Collection<Label>> outgoingLabels, 
			ProgressMonitor progressMonitor) {

		if (eReference == EcorePackage.Literals.ECLASS__EALL_ATTRIBUTES) {
			// A page with a dynamic attributes table and links to attribute pages for attributes with documentation.
			for (Label label: labels) {
				if (label instanceof Action) {					
					Action attributesAction = AppFactory.eINSTANCE.createAction();
					attributesAction.setText("Attributes");
					attributesAction.setIcon("https://cdn.jsdelivr.net/gh/Nasdanika/html@master/ecore.gen/web-resources/icons/EAttribute.gif");
					attributesAction.setLocation("attributes.html");					
					((Action) label).getNavigation().add(attributesAction);					
				}
			}
		}
		
		if (eReference == EcorePackage.Literals.ECLASS__EALL_REFERENCES) {
			// A page with a dynamic references table and links to reference pages for references with documentation. 
			for (Label label: labels) {
				if (label instanceof Action) {					
					
					DynamicTableBuilder<Entry<EReferenceConnection, LabelFactory>> referencesTableBuilder = new DynamicTableBuilder<>("nsd-ecore-doc-table");
					referencesTableBuilder
						.addStringColumnBuilder("name", true, false, "Name", endpoint -> nameLink(endpoint.getKey(), endpoint.getValue(), progressMonitor)); 
//						.addStringColumnBuilder("type", true, true, "Type", attr -> {
//							EGenericType genericType = attr.getEGenericType(); 
//							if (genericType == null) {
//								return null;
//							}
//							StringBuilder sb = new StringBuilder();
//							genericType(genericType, eObject, sb::append, progressMonitor);
//							return sb.toString();
//						})
//						.addStringColumnBuilder("cardinality", true, true, "Cardinality", EModelElementNodeProcessor::cardinality)
//						.addBooleanColumnBuilder("changeable", true, true, "Changeable", EStructuralFeature::isChangeable)
//						.addBooleanColumnBuilder("derived", true, true, "Derived", EStructuralFeature::isDerived);
//						.addStringColumnBuilder("declaring-class", true, true, "Declaring Class", attr -> link(attr.getEContainingClass(), eObject))
//						.addStringColumnBuilder("description", true, false, "Description", this::getEModelElementFirstDocSentence);
						// Other things not visible?
					
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
		
		if (eReference == EcorePackage.Literals.ECLASS__EALL_OPERATIONS) {
			// A page with a dynamic operations table and links to operations pages for operations with documentation. 
			for (Label label: labels) {
				if (label instanceof Action) {					
					Action operationsAction = AppFactory.eINSTANCE.createAction();
					operationsAction.setText("Operations");
					operationsAction.setIcon("https://cdn.jsdelivr.net/gh/Nasdanika/html@master/ecore.gen/web-resources/icons/EOperation.gif");
					operationsAction.setLocation("operations.html");
					
					((Action) label).getNavigation().add(operationsAction);
				}
			}			
		}
		
		if (eReference == EcorePackage.Literals.ECLASS__EREFERENCES) {			
			// Own attributes, references, and operations as anonymous if have contents. 
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
		
		// TODO - attributes and references
		
		super.buildOutgoingReference(eReference, referenceOutgoingEndpoints, labels, outgoingLabels, progressMonitor);
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
	
}
