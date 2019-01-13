package org.nasdanika.html.emf;

import java.util.function.Function;

import org.eclipse.emf.common.notify.Notifier;

/**
 * @param <T> Adapter type.
 * @param <N> Notifier type.
 */
public class FunctionAdapterFactory<T, N> extends DelegatingAdapterFactory<T> {
	
	private Function<N, T> function;

	public FunctionAdapterFactory(Class<T> type, ClassLoader proxyClassLoader, Function<N, T> function) {
		super(type, proxyClassLoader);
		this.function = function;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T doCreateAdapter(Notifier target) {
		return function.apply((N) target);
	}

}
