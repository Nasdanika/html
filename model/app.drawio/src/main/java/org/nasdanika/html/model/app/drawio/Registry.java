package org.nasdanika.html.model.app.drawio;

import java.util.Map;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorInfo;

/**
 * Registry record wrapping the map to simplify generic type parameters
 * @author Pavel
 *
 * @param <P>
 */
public record Registry(Map<Element, ProcessorInfo<ElementProcessor,Registry>> infoMap) {

}
