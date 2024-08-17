package org.nasdanika.html.model.app.graph.drawio;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.ReducingListCompoundSupplier;
import org.nasdanika.common.Supplier;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Page;
import org.nasdanika.graph.processor.ChildProcessors;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.graph.WidgetFactory;

public class DocumentProcessor extends BaseProcessor<Document> {
	
	public DocumentProcessor(DrawioProcessorFactory factory) {
		super(factory);
	}

	@ChildProcessors
	public Map<Page, ProcessorInfo<PageProcessor>> pageProcessors;
	
	@Override
	public void resolve(URI base, ProgressMonitor progressMonitor) {
		List<Map.Entry<Page, ProcessorInfo<PageProcessor>>> topLevelPageProcessors = pageProcessors
			.entrySet()
			.stream()
			.filter(ppe -> ppe.getValue().getProcessor().referrers.isEmpty())
			.toList();
		
		if (topLevelPageProcessors.size() == 1) {
			topLevelPageProcessors.forEach(ppe -> ppe.getValue().getProcessor().resolve(base, progressMonitor));
		} else {
			topLevelPageProcessors.forEach(ppe -> ppe.getValue().getProcessor().resolve(URI.createURI(ppe.getKey().getId()).appendSegment("").resolve(base), progressMonitor));			
		}
	}
	
	@Override
	public Supplier<Collection<Label>> createLabelsSupplier() {
		List<Supplier<Collection<Label>>> topLevelPageProcessorSuppliers = pageProcessors
				.values()				
				.stream()
				.filter(pp -> pp.getProcessor().referrers.isEmpty())
				.map(ProcessorInfo::getProcessor)
				.map(WidgetFactory::createLabelsSupplier)
				.toList();
		
		if (topLevelPageProcessorSuppliers.isEmpty()) {
			PageProcessor firstPageProcessor = pageProcessors.get(element.getPages().iterator().next()).getProcessor();
			firstPageProcessor.isTopLevelPage = true; // Forcing
			return firstPageProcessor.createLabelsSupplier();			
		}
		
		if (topLevelPageProcessorSuppliers.size() == 1) {
			return topLevelPageProcessorSuppliers.iterator().next();
		}
		
		BinaryOperator<Collection<Label>> accumulator = (a, b) -> {
			if (a == null || a.isEmpty()) {
				return b == null ? Collections.emptyList() : b; 
			}
			
			if (b == null || b.isEmpty()) {
				return a;
			}
			
			return Stream.concat(a.stream(), b.stream()).toList();
		};
		
		Supplier<Collection<Label>> compoundSupplier = new ReducingListCompoundSupplier<Collection<Label>>(
				"Document labels supplier",
				accumulator,
				topLevelPageProcessorSuppliers);
		
		return compoundSupplier;
	}

}
