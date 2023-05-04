package org.nasdanika.html.ecore.gen.processors;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.common.Context;
import org.nasdanika.graph.emf.EReferenceConnection;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
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
	protected BiConsumer<Collection<Entry<EReferenceConnection, Collection<Label>>>, Collection<Label>> getOutgoingReferenceInjector(EReference eReference) {
		if (eReference == EcorePackage.Literals.ECLASS__EALL_ATTRIBUTES) {
			// A page with a dynamic attributes table and links to attribute pages for attributes with documentation. 
			return (r, t) -> {
				List<Entry<EReferenceConnection, Collection<Label>>> sorted = r.stream()
					.sorted((a,b) -> ((ENamedElement) a.getKey().getTarget().getTarget()).getName().compareTo(((ENamedElement) b.getKey().getTarget().getTarget()).getName()))
					.collect(Collectors.toList());		

				for (Label tLabel: t) {
					for (Entry<EReferenceConnection, Collection<Label>> re: sorted) {
						tLabel.getChildren().addAll(re.getValue());
					}
				}
			};
		}
		if (eReference == EcorePackage.Literals.ECLASS__EATTRIBUTES) {
			// Own attributes as anonymous if have contents. 
			return (r, t) -> {
				for (Label tLabel: t) {
					if (tLabel instanceof Action) {
						EList<Action> tAnonymous = ((Action) tLabel).getAnonymous();
						for (Entry<EReferenceConnection, Collection<Label>> re: r) {
							for (Label attrLabel: re.getValue()) {
								if (attrLabel instanceof Action) {
									
								}
							}
						}
					}
				}
			};
		}		
		
		
		return super.getOutgoingReferenceInjector(eReference);
	}
	

}
