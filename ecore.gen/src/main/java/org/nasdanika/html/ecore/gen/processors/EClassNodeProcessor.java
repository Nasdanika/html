package org.nasdanika.html.ecore.gen.processors;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.AppFactory;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.gen.DynamicTableBuilder;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;

public class EClassNodeProcessor extends EClassifierNodeProcessor<EClass> {

	public EClassNodeProcessor(
			NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config,
			Context context,
			java.util.function.Function<URI, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
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
					Action referencesAction = AppFactory.eINSTANCE.createAction();
					referencesAction.setText("References");
					referencesAction.setIcon("https://cdn.jsdelivr.net/gh/Nasdanika/html@master/ecore.gen/web-resources/icons/EReference.gif");
					referencesAction.setLocation("references.html");
					
					DynamicTableBuilder<Entry<EReferenceConnection, LabelFactory>> referencesTableBuilder = new DynamicTableBuilder<>();
					referencesTableBuilder
						.addStringColumnBuilder("name", true, true, "Name", endpoint -> endpoint.getValue().createLink().toString()); // TODO - link
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
							referenceOutgoingEndpoints, // TODO - sort by name 
							getTarget().getEPackage().getNsURI().hashCode() + "-" + getTarget().getName() + "-references", "references-table", 
							progressMonitor);
					referencesAction.getContent().add(referencesTable);
					((Action) label).getNavigation().add(referencesAction);
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
		
		if (eReference == EcorePackage.Literals.ECLASS__EATTRIBUTES || eReference == EcorePackage.Literals.ECLASS__EREFERENCES || eReference == EcorePackage.Literals.ECLASS__EOPERATIONS) {
			// Own attributes, references, and operations as anonymous if have contents. 
			for (Label tLabel: labels) {
				if (tLabel instanceof Action) {
					EList<Action> tAnonymous = ((Action) tLabel).getAnonymous();
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
