package org.nasdanika.html.model.app.drawio;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Layer;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

public class LayerProcessor extends ModelElementProcessor {

	public LayerProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config, URI baseURI) {
		super(resourceFactory, uri, config, baseURI);
	}
	
	@Override
	public Layer getElement() {
		return (Layer) super.getElement();
	}

	@Override
	public List<ProcessorInfo<ElementProcessor>> collectSemanticChildrenInfo(ProcessorInfo<ElementProcessor> semanticParentInfo) {
		Stream<ProcessorInfo<ElementProcessor>> superStream = super.collectSemanticChildrenInfo(semanticParentInfo).stream();
		Stream<ProcessorInfo<ElementProcessor>> childStream = config.getChildProcessorsInfo()
				.values()
				.stream()
				.filter(ep -> {
					// Connections with sources "belong" to source nodes.
					Element e = ep.getConfig().getElement();
					return !(e instanceof Connection) || ((Connection) e).getSource() == null;
				})
				.map(ProcessorInfo::getProcessor)
				.map(ModelElementProcessor.class::cast)
				.flatMap(p -> p.setSemanticParentInfo(semanticParentInfo).stream());
		
		Stream<ProcessorInfo<ElementProcessor>> stream = Stream.concat(superStream,	childStream);
		
		Comparator<ProcessorInfo<ElementProcessor>> semanticChildrenComparator = getSemanticChildrenComparator();
		if (semanticChildrenComparator != null) {
			stream = stream.sorted(semanticChildrenComparator);
		}
		
		return stream.collect(Collectors.toList());
	}

}
