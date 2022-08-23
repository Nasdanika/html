package org.nasdanika.html.model.app.drawio;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.DrawioResource;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Model;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Root;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.AppPackage;

public class ResourceFactory implements Factory {

	@Override
	public Resource createResource(URI uri) {
		return new DrawioResource<ElementProcessor>(uri, new ProcessorFactory(uri, this)) {

			@Override
			protected Stream<EObject> getSemanticElements(Map<Element,ProcessorInfo<ElementProcessor>> registry) {
				return ResourceFactory.this.getSemanticElements(registry);
			}
			
		};
	}
	
	protected Stream<EObject> getSemanticElements(Map<Element,ProcessorInfo<ElementProcessor>> registry) {
		return registry
				.values()
				.stream()
				.map(ProcessorInfo::getProcessor)
				.filter(Objects::nonNull)
				.map(ElementProcessor::getSemanticElements)
				.filter(Objects::nonNull)
				.flatMap(Collection::stream)
				.distinct();
	}

	protected ElementProcessor createProcessor(ProcessorConfig<ElementProcessor> config,
			Consumer<Consumer<ProcessorInfo<ElementProcessor>>> parentProcessorInfoCallbackConsumer,
			Consumer<Consumer<Map<Element, ProcessorInfo<ElementProcessor>>>> registryCallbackConsumer) {
		
		ElementProcessor processor;
		if (config.getElement() instanceof Document) {
			processor = createDocumentProcessor(config);
		} else if (config.getElement() instanceof Page) {
			processor = createPageProcessor(config);
		} else if (config.getElement() instanceof Model) {
			processor = createModelProcessor(config);
		} else if (config.getElement() instanceof Root) {
			processor = createRootProcessor(config);
		} else if (config.getElement() instanceof Node) {
			processor = createNodeProcessor(config);
		} else if (config.getElement() instanceof org.nasdanika.drawio.Connection) {
			processor = createConnectionProcessor(config);
		} else if (config.getElement() instanceof Layer) {
			processor = createLayerProcessor(config);
		} else {
			processor = null;
		}
		
		if (processor != null) {
			parentProcessorInfoCallbackConsumer.accept(processor::setParentInfo);
			registryCallbackConsumer.accept(processor::setRegistry);
		}

		return processor;
	}

	protected ElementProcessor createLayerProcessor(ProcessorConfig<ElementProcessor> config) {
		return new LayerProcessor(this, config);
	}

	protected ElementProcessor createConnectionProcessor(ProcessorConfig<ElementProcessor> config) {
		return new ConnectionProcessor(this, config);
	}

	protected ElementProcessor createDocumentProcessor(ProcessorConfig<ElementProcessor> config) {
		return new DocumentProcessor(this, config);
	}

	protected ElementProcessor createNodeProcessor(ProcessorConfig<ElementProcessor> config) {
		return new NodeProcessor(this, config);
	}

	protected ElementProcessor createRootProcessor(ProcessorConfig<ElementProcessor> config) {
		return new RootProcessor(this, config);
	}

	protected ElementProcessor createModelProcessor(ProcessorConfig<ElementProcessor> config) {
		return null;
	}

	protected ElementProcessor createPageProcessor(ProcessorConfig<ElementProcessor> config) {
		return new PageProcessor(this, config);
	}
	
	protected EReference resolveConnectionRole(String connectionRole) {
		switch (connectionRole) {
		case "none": 
			return null;
		case "child":
			return AppPackage.Literals.LABEL__CHILDREN;
		case "anonymous":
			return AppPackage.Literals.ACTION__ANONYMOUS;
		case "navigation":
			return AppPackage.Literals.ACTION__NAVIGATION;
		case "section":
			return AppPackage.Literals.ACTION__SECTIONS;
		default: 
			throw new IllegalArgumentException("Unsupported connection role: " + connectionRole);
		}
	}		

	protected String getConnectionRoleProperty() {
		return "role";
	}
	
	protected String getDefaultConnectionRoleProperty() {
		return "default-connection-role";
	}
	
	protected ConnectionBase getConnectionBase() {
		return ConnectionBase.SOURCE;
	}

}
