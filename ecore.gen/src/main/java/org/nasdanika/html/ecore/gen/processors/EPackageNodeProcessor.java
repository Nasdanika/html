package org.nasdanika.html.ecore.gen.processors;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.Supplier;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.LabelFactory;
import org.nasdanika.html.model.app.graph.Registry;

public class EPackageNodeProcessor extends ENamedElementNodeProcessor<EPackage> {

	public EPackageNodeProcessor(NodeProcessorConfig<Object, LabelFactory, LabelFactory, Registry<URI>> config, Context context) {
		super(config, context);
	}	
	
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		// TODO - eclassifiers suppliers sorted, list supplier, map, ...
		
		outgoingEndpoints
			.entrySet()
			.stream()
			.filter(e -> e.getKey().getReference() == EcorePackage.Literals.EPACKAGE__ECLASSIFIERS)
//			.sorted(null) - TOOD by name
			.forEach(e -> System.out.println(e.getKey().getTarget().getTarget()));
// TODO - suppliers		
		
		return super.createLabelsSupplier();
	}
	
}
