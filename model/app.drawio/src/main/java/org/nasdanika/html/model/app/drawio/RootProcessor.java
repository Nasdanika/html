package org.nasdanika.html.model.app.drawio;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.drawio.Root;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

public class RootProcessor extends ModelElementProcessor {
	
	public RootProcessor(ResourceFactory resourceFactory, URI uri, ProcessorConfig<ElementProcessor> config, URI baseURI) {
		super(resourceFactory, uri, config, baseURI);
	}
	
	@Override
	public Root getElement() {
		return (Root) super.getElement();
	}
	
	@Override
	public String getText() {
		return getElement().getModel().getPage().getName();
	}

	@Override
	protected Comparator<ProcessorInfo<ElementProcessor>> getSemanticChildrenComparator() {
		Comparator<ProcessorInfo<ElementProcessor>> semanticChildrenComparator = super.getSemanticChildrenComparator();
		if (semanticChildrenComparator != null) { 
			return semanticChildrenComparator;
		}
		Root root = getElement();
		return (a,b) -> {
			Element be = b.getConfig().getElement();
			Element ae = a.getConfig().getElement();
			if (Objects.equals(ae, be)) {
				return 0;
			}
			return root.getLayers().indexOf(be) - root.getLayers().indexOf(ae); // Reverse order 			
		};
	}
	
	@Override
	protected String getSemanticID() {
		return getElement().getModel().getPage().getId();
	}

	@Override
	public Map<ProcessorInfo<ElementProcessor>, EReference> collectSemanticChildrenInfo(ProcessorInfo<ElementProcessor> semanticParentInfo) {
		Map<ProcessorInfo<ElementProcessor>, EReference> ret = new LinkedHashMap<>();
		config.getChildProcessorsInfo()
			.values()
			.stream()
			.sorted(getSemanticChildrenComparator())
			.map(ProcessorInfo::getProcessor)
			.map(ModelElementProcessor.class::cast)
			.flatMap(p -> p.setSemanticParentInfo(semanticParentInfo).entrySet().stream())
			.forEach(e -> ret.put(e.getKey(), e.getValue()));
		
		return ret;
	}
	
}
