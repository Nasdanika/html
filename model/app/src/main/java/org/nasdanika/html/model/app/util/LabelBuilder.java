package org.nasdanika.html.model.app.util;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.common.Composable;
import org.nasdanika.common.CompoundExecutionParticipant;
import org.nasdanika.common.ExecutionParticipant;
import org.nasdanika.common.FilterExecutionParticipant;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Label;
import org.nasdanika.html.model.app.util.LabelProvider.Resolver;

/**
 * Adapter interface for creating {@link Label}'s and Label subclasses.
 * @author Pavel
 *
 */
public interface LabelBuilder extends ExecutionParticipant, Composable<LabelBuilder> {
		
	Label build(Label label, Consumer<Resolver> resolverCollector, ProgressMonitor progressMonitor);
		
	default Label splitAndBuild(Label label, Consumer<Resolver> resolverCollector, ProgressMonitor progressMonitor) {
		try (ProgressMonitor subMonitor = split(progressMonitor, "Building" + name())) {
			return build(label, resolverCollector, subMonitor);
		}
	}	

	@Override
	default LabelBuilder compose(LabelBuilder other) {
		if (other == null) {
			return this;
		}
		
		List<LabelBuilder> labelBuilders = List.of(this, other);
		
		class CompoundLabelBuilder extends CompoundExecutionParticipant<LabelBuilder> implements LabelBuilder {

			protected CompoundLabelBuilder(String name, boolean reverseCommitClose) {
				super(name, reverseCommitClose);
			}

			@Override
			public Label build(Label label, Consumer<Resolver> resolverCollector, ProgressMonitor progressMonitor) {
				for (LabelBuilder element: getElements()) {
					if (element != null) {
						label = element.splitAndBuild(label, resolverCollector, progressMonitor);
					}
				}
				return label;
			}

			@Override
			protected Collection<LabelBuilder> getElements() {
				return labelBuilders;
			}
			
		}
		
		return new CompoundLabelBuilder("Composite label builder", false);
	}
	
	/**
	 * Wraps this builder into a provider
	 * @param identity Initial label, can be null
	 * @return {@link LabelProvider} delegating to this builder
	 */
	default LabelProvider asProvider(Label identity, Iterable<?> registryKeys) {
		class DelegatingLabelProvider extends FilterExecutionParticipant<LabelBuilder> implements LabelProvider {

			public DelegatingLabelProvider() {
				super(LabelBuilder.this);
			}

			@Override
			public Label create(BiConsumer<Object, Label> registry, Consumer<Resolver> resolverCollector, ProgressMonitor progressMonitor) {
				Label label = target.build(identity, resolverCollector, progressMonitor);
				if (registry != null && label != null && registryKeys != null) {
					for (Object registryKey: registryKeys) {
						registry.accept(registryKey, label);
					}
				}
				return label;
			}
			
		}
		return new DelegatingLabelProvider();
	}
	
}
