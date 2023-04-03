package org.nasdanika.html.model.app.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ExecutionParticipant;
import org.nasdanika.common.FilterExecutionParticipant;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.Link;
import org.nasdanika.html.model.app.util.LabelProvider.Resolver.Registry;

/**
 * Adapter interface for creating {@link Label}'s and Label subclasses.
 * @author Pavel
 *
 */
public interface LabelProvider extends ExecutionParticipant {
	
	public record Result(Label label, Map<Object,Label> registry) {
		
	};
		
	/**
	 * Callback interface
	 * @author Pavel
	 *
	 */
	interface Resolver {

		/**
		 * Registry of available labels accessible by key - EObject instance or its URI.
		 * @author Pavel
		 *
		 */
		interface Registry {
			
			/**
			 * @param key EObject (semantic element) instance of URI
			 * @return {@link Label} or {@link Link} to the {@link Action} or location identified by the key.
			 * Link might be relative.
			 */
			Label getLabel(Object key);
			
			/**
			 * @param key EObject (semantic element) instance or URI
			 * @return A URI of the semantic element action/location relative if possible
			 */
			URI getURI(Object key);
			
			// Target label
			
		}
	
		/**
		 * This method is called when all labels are created
		 * @param registry A registry of labels for resolving cross-references
		 * @param context
		 * @param progressMonitor
		 */
		void resolve(Registry registry, ProgressMonitor progressMonitor);
		
	}
	
	/**
	 * Creates a label
	 * @param registry A label can be registered to make it available in Resolver's registry.
	 * @param resolverCollector
	 * @return
	 */
	Label create(BiConsumer<Object,Label> registry, Consumer<Resolver> resolverCollector, ProgressMonitor progressMonitor);
	
	/**
	 * Creates a label. Delegates to <code>createBiConsumer<Object,Label> registry, Consumer<Resolver> resolverCollector, ProgressMonitor progressMonitor)</code>, 
	 * captures label/resolvers association. 
	 * @param registry A label can be registered to make it available in Resolver's registry.
	 * @param resolverCollector
	 * @return
	 */
	default Label create(BiConsumer<Object,Label> registry, BiConsumer<Label, Collection<Resolver>> resolverCollector, ProgressMonitor progressMonitor) {
		Collection<Resolver> resolvers = new ArrayList<>();
		Label label = create(registry, resolvers::add, progressMonitor);
		if (!resolvers.isEmpty()) {
			resolverCollector.accept(label, resolvers);
		}
		return label;
	}	
	
	/**
	 * Wraps this provider into a supplier.
	 * Creates a map of objects (keys) to labels,
	 * then calls create(). After that calls resolve() for registered resolvers.
	 * @param baseURI Base URI for links, if known - allows to build relative links to external locations.
	 * If null, a random base URI is created for relative cross-referencing. External entities are referenced using their absolute URI's.  
	 * @param registryFilters Optional registry filters which may provide resolution for external semantic elements
	 * @return {@link BiSupplier} returning the label from getFirst() and a registry of all labels from getSecond() 
	 */
	default org.nasdanika.common.Supplier<Result> asSupplier(URI baseURI, @SuppressWarnings("unchecked") java.util.function.Function<Resolver.Registry,Resolver.Registry>... registryFilters) {
		class LabelBuilderSupplier extends FilterExecutionParticipant<LabelProvider> implements org.nasdanika.common.Supplier<Result> {

			public LabelBuilderSupplier() {
				super(LabelProvider.this);
			}

			@Override
			public Result execute(ProgressMonitor progressMonitor) {
				Map<Object, Label> registry = new HashMap<>();
				
				record ResolverEntry(Label label, Collection<Resolver> resolvers) {
					
				};
				
				Collection<ResolverEntry> resolverEntries = new ArrayList<>();
				Label label = LabelProvider.this.create(registry::put, (lbl, resolvers) -> resolverEntries.add(new ResolverEntry(lbl, resolvers)), progressMonitor);
				for (ResolverEntry resolverEntry: resolverEntries) {
					Registry resolverRegistry = new Registry() {
						
						@Override
						public URI getURI(Object key) {
							// TODO Auto-generated method stub
							return null;
						}
						
						@Override
						public Label getLabel(Object key) {
							// TODO A label or a link referencing the target
							return null;
						}
					};
					
					for (Function<Registry, Registry> registryFilter: registryFilters) {
						resolverRegistry = registryFilter.apply(resolverRegistry);
					}
					
					for (Resolver resolver: resolverEntry.resolvers()) {
						resolver.resolve(resolverRegistry, progressMonitor);
					}
				}
				return new Result(label, registry);
			} 
			
		}
		return new LabelBuilderSupplier();
	}

}
