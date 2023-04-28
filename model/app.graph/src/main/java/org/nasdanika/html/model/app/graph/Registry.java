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
	
	Map<Element, ProcessorInfo<Object,Registry<I>>> getProcessorInfoMap();
	
	/**
	 * Finds {@link LabelFactory}ies by predicate
	 * @param predicate
	 * @return 
	 */
	Collection<LabelFactory> select(Predicate<Element> predicate, NodeProcessor<I> base);
	
	/**
	 * Finds a {@link LabelFactory} by identifier
	 * @param identifier
	 * @return label record or null
	 */
	LabelFactory find(I identifier, NodeProcessor<I> base);
	
}