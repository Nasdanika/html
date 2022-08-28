package org.nasdanika.html.model.app.drawio;

import java.util.Comparator;
import java.util.Objects;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.drawio.Root;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

public class RootProcessor extends ModelElementProcessor {
	
	public RootProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config) {
		super(resourceFactory, uri, config);
	}
	
	@Override
	protected String getText() {
		return ((Root) config.getElement()).getModel().getPage().getName();
	}

	@Override
	protected Comparator<ProcessorInfo<ElementProcessor>> getSemanticChildrenComparator() {
		Comparator<ProcessorInfo<ElementProcessor>> semanticChildrenComparator = super.getSemanticChildrenComparator();
		if (semanticChildrenComparator != null) { 
			return semanticChildrenComparator;
		}
		Root root = (Root) config.getElement();
		return (a,b) -> {
			Element be = b.getConfig().getElement();
			Element ae = a.getConfig().getElement();
			if (Objects.equals(ae, be)) {
				return 0;
			}
			return root.getLayers().indexOf(be) - root.getLayers().indexOf(ae); // Reverse order 
			
		};
	}
	
}
