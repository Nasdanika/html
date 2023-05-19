package org.nasdanika.html.ecore.gen.processors;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;
import org.nasdanika.html.model.app.graph.emf.OutgoingReferenceBuilder;
import org.nasdanika.html.model.app.graph.Registry;

public class EPackageNodeProcessor extends ENamedElementNodeProcessor<EPackage> {

	public EPackageNodeProcessor(
			NodeProcessorConfig<Object, WidgetFactory, WidgetFactory, Registry<URI>> config, 
			Context context,
			java.util.function.BiFunction<URI, ProgressMonitor, Action> prototypeProvider) {
		super(config, context, prototypeProvider);
	}	
	
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		return super.createLabelsSupplier().then(this::sortLabels);
	}
	
	@OutgoingReferenceBuilder(EcorePackage.EPACKAGE__ECLASSIFIERS)
	public void buildOutgoingReference(
			List<Entry<EReferenceConnection, WidgetFactory>> referenceOutgoingEndpoints, 
			Collection<Label> labels,
			Map<EReferenceConnection, Collection<Label>> outgoingLabels, 
			ProgressMonitor progressMonitor) {
		
		List<Entry<EReferenceConnection, Collection<Label>>> sorted = outgoingLabels.entrySet().stream()
				.sorted((a,b) -> ((ENamedElement) a.getKey().getTarget().getTarget()).getName().compareTo(((ENamedElement) b.getKey().getTarget().getTarget()).getName()))
				.collect(Collectors.toList());		

			for (Label tLabel: labels) {
				for (Entry<EReferenceConnection, Collection<Label>> re: sorted) {
					tLabel.getChildren().addAll(re.getValue());
				}
			}
	}
	
	protected Collection<Label> sortLabels(Collection<Label> labels) {
		return labels
			.stream()
			.sorted((a,b) -> a.getText().compareTo(b.getText()))
			.collect(Collectors.toList());		
	}
	
	@Override
	protected boolean isCallOutgoingReferenceLabelsSuppliers(EReference eReference) {
		if (eReference == EcorePackage.Literals.EPACKAGE__ECLASSIFIERS) {
			return true;
		}
		return super.isCallOutgoingReferenceLabelsSuppliers(eReference);
	}
	
}


