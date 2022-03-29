package org.nasdanika.html.model.app.util;

import java.util.function.BiConsumer;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Action;

/**
 * Adapter interface. Builds an action for the target.
 * Implementations shall call the argument {@link BiConsumer} passing the target and resulting action as argument to use
 * later in resolve() phase.
 * @author Pavel
 *
 */
public interface ActionBuilder extends org.nasdanika.common.Function<BiSupplier<Action,BiConsumer<EObject,Action>>,Action> {

	/**
	 * @return AcitonProvider which passes null as action in BiSupplier.
	 */
	default ActionProvider asActionProvider() {
		return asActionProvider(null);
	}
	
	/**
	 * @return AcitonProvider which passes null as action in BiSupplier.
	 */
	default ActionProvider asActionProvider(Action action) {
		
		return new ActionProvider() {

			/**
			 * Passes null action.
			 */
			@Override
			public Action execute(BiConsumer<EObject, Action> registry, ProgressMonitor progressMonitor) throws Exception {
				return ActionBuilder.this.execute(BiSupplier.of(action, registry), progressMonitor);
			}

			@Override
			public Action splitAndExecute(BiConsumer<EObject, Action> registry, ProgressMonitor progressMonitor) throws Exception {
				return ActionBuilder.this.splitAndExecute(BiSupplier.of(action, registry), progressMonitor);
			}

			@Override
			public Action splitAndExecute(BiConsumer<EObject, Action> registry, double size, ProgressMonitor progressMonitor) throws Exception {
				return ActionBuilder.this.splitAndExecute(BiSupplier.of(action, registry), size, progressMonitor);
			}

//			@Override
//			public <V> Function<V, Action> before(java.util.function.Function<V, BiConsumer<EObject, Action>> before) {
//				return ActionBuilder.this.before(before);
//			}
//
//			@Override
//			public <V> Function<BiConsumer<EObject, Action>, V> then(java.util.function.Function<? super Action, V> then) {
//				return ActionBuilder.this.then(then);
//			}
//
//			@Override
//			public <V> Function<BiConsumer<EObject, Action>, V> then(Function<? super Action, V> then) {
//				return ActionBuilder.this.then(then);
//			}
//
//			@Override
//			public Consumer<BiConsumer<EObject, Action>> then(Consumer<? super Action> then) {
//				return ActionBuilder.this.then(then);
//			}
//
			@Override
			public Action apply(BiConsumer<EObject, Action> registry, ProgressMonitor progressMonitor) {
				return ActionBuilder.this.apply(BiSupplier.of(action, registry), progressMonitor);
			}
//
//			@Override
//			public java.util.function.Function<BiConsumer<EObject, Action>, Action> asFunction() {
//				return ActionBuilder.this.asFunction();
//			}

			@Override
			public void close() throws Exception {
				ActionBuilder.this.close();
			}

			@Override
			public void commit(ProgressMonitor progressMonitor) throws Exception {
				ActionBuilder.this.commit(progressMonitor);
			}

			@Override
			public void splitAndCommit(ProgressMonitor progressMonitor) throws Exception {
				ActionBuilder.this.splitAndCommit(progressMonitor);
			}

			@Override
			public void splitAndCommit(double size, ProgressMonitor progressMonitor) throws Exception {
				ActionBuilder.this.splitAndCommit(size, progressMonitor);
			}

			@Override
			public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
				return ActionBuilder.this.rollback(progressMonitor);
			}

			@Override
			public boolean splitAndRollback(ProgressMonitor progressMonitor) throws Exception {
				return ActionBuilder.this.splitAndRollback(progressMonitor);
			}

			@Override
			public boolean splitAndRollback(double size, ProgressMonitor progressMonitor) throws Exception {
				return ActionBuilder.this.splitAndRollback(size, progressMonitor);
			}

			@Override
			public ProgressMonitor split(ProgressMonitor parent, String taskName) {
				return ActionBuilder.this.split(parent, taskName);
			}

			@Override
			public ProgressMonitor split(double size, ProgressMonitor parent, String taskName) {
				return ActionBuilder.this.split(size, parent, taskName);
			}

			@Override
			public Diagnostic splitAndDiagnose(ProgressMonitor progressMonitor) {
				return ActionBuilder.this.splitAndDiagnose(progressMonitor);
			}

			@Override
			public Diagnostic splitAndDiagnose(double size, ProgressMonitor progressMonitor) {
				return ActionBuilder.this.splitAndDiagnose(size, progressMonitor);
			}

			@Override
			public double size() {
				return ActionBuilder.this.size();
			}

			@Override
			public String name() {
				return ActionBuilder.this.name();
			}

			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				return ActionBuilder.this.diagnose(progressMonitor);
			}
		};
		
	}

}
