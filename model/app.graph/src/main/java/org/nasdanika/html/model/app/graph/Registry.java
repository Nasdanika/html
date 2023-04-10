package org.nasdanika.html.model.app.graph;

import java.util.Collection;
import java.util.Map;
import java.util.function.Predicate;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorInfo;

/**
 * 
 * @author Pavel
 *
 * @param <I> Element identifier type
 */
public interface Registry<I> {
	
	Map<Element, ProcessorInfo<Processor,Registry<I>>> getProcessorInfoMap();
	
	/**
	 * Finds labels by predicate
	 * @param predicate
	 * @return 
	 */
	Collection<LabelInfo> select(Predicate<Element> predicate, Processor base);
	
	/**
	 * Finds a label by identifier
	 * @param identifier
	 * @return label record or null
	 */
	LabelInfo find(I identifier, Processor base);
	
}
