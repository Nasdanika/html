package org.nasdanika.html.producer.factories.core;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Producer;

public class CoreProducerFactory {
			
	private Context context;

	public CoreProducerFactory(Context context)  {
		this.context = context;
	}

// --- Ncore ---
	
	@org.nasdanika.common.Transformer.Factory
	public final Producer<Object> createStringProducer(
			org.nasdanika.ncore.String str,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
		
		return new StringNodeProcessor(str);
	}
	
	
}
