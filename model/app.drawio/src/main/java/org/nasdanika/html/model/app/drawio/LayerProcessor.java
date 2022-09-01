package org.nasdanika.html.model.app.drawio;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.drawio.Layer;
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
	protected List<ProcessorInfo<ElementProcessor>> collectSemanticChildrenInfo(ProcessorInfo<ElementProcessor> semanticParentInfo) {
		Stream<ProcessorInfo<ElementProcessor>> stream = config.getChildProcessorsInfo()
				.values()
				.stream();
		
		Comparator<ProcessorInfo<ElementProcessor>> semanticChildrenComparator = getSemanticChildrenComparator();
		if (semanticChildrenComparator != null) {
			stream = stream.sorted(semanticChildrenComparator);
		}
		return stream
				.map(ProcessorInfo::getProcessor)
				.map(ModelElementProcessor.class::cast)
				.flatMap(p -> p.setSemanticParentInfo(semanticParentInfo).stream())
				.collect(Collectors.toList());
	}

}
