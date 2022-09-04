package org.nasdanika.html.model.app.drawio;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
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
	public Map<ProcessorInfo<ElementProcessor>, EReference> collectSemanticChildrenInfo(ProcessorInfo<ElementProcessor> semanticParentInfo) {
		Stream<Map.Entry<ProcessorInfo<ElementProcessor>, EReference>> superStream = super.collectSemanticChildrenInfo(semanticParentInfo).entrySet().stream();
		Stream<Map.Entry<ProcessorInfo<ElementProcessor>, EReference>> childStream = config.getChildProcessorsInfo()
				.values()
				.stream()
				.filter(ep -> {
					// Connections with sources "belong" to source nodes.
					Element e = ep.getConfig().getElement();
					return !(e instanceof Connection) || ((Connection) e).getSource() == null;
				})
				.map(ProcessorInfo::getProcessor)
				.map(ModelElementProcessor.class::cast)
				.flatMap(p -> p.setSemanticParentInfo(semanticParentInfo).entrySet().stream());
		
		Stream<Map.Entry<ProcessorInfo<ElementProcessor>, EReference>> stream = Stream.concat(superStream,	childStream);
		
		Comparator<ProcessorInfo<ElementProcessor>> semanticChildrenComparator = getSemanticChildrenComparator();
		if (semanticChildrenComparator != null) {
			stream = stream.sorted((a, b) -> semanticChildrenComparator.compare(a.getKey(), b.getKey()));
		}
		
		Map<ProcessorInfo<ElementProcessor>, EReference> ret = new LinkedHashMap<>();		
		stream.forEach(e -> ret.put(e.getKey(),  e.getValue()));
		return ret;
	}

}
