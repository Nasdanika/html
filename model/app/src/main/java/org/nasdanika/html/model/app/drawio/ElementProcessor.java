package org.nasdanika.html.model.app.drawio;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

public class ElementProcessor {
	
	protected ResourceFactory resourceFactory;
	protected ProcessorConfig<ElementProcessor> config;
	protected ProcessorInfo<ElementProcessor> parentInfo;
	protected Map<Element, ProcessorInfo<ElementProcessor>> registry;

	protected ElementProcessor(ResourceFactory resourceFactory, ProcessorConfig<ElementProcessor> config) {
		this.resourceFactory = resourceFactory;
		this.config = config;
	}	

	/**
	 * This implementation collects semantic elements from children and passes them through.
	 * @return
	 */
	public List<EObject> getSemanticElements() {
		return config
			.getChildProcessorsInfo()
			.values()
			.stream()
			.map(ProcessorInfo::getProcessor)
			.map(ElementProcessor::getSemanticElements)
			.flatMap(Collection::stream)
			.collect(Collectors.toList());
	}
	
	public void setParentInfo(ProcessorInfo<ElementProcessor> parentInfo) {
		this.parentInfo = parentInfo;
	}
	
	public void setRegistry(Map<Element, ProcessorInfo<ElementProcessor>> registry) {
		this.registry = registry;
	}

}
