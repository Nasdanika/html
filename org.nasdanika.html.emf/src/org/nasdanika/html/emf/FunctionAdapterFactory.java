package org.nasdanika.html.emf;

import java.util.function.Function;

import org.eclipse.emf.common.notify.Notifier;

/**
 * @param <T> Notifier type.
 * @param <A> Adapter type.
 */
public class FunctionAdapterFactory<T, A> extends DelegatingAdapterFactory<A> {
	
	private Function<T, A> function;

	public FunctionAdapterFactory(Class<A> type, ClassLoader proxyClassLoader, Function<T, A> function) {
		super(type, proxyClassLoader);
		this.function = function;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected A doCreateAdapter(Notifier target) {
		return function.apply((T) target);
	}

}
