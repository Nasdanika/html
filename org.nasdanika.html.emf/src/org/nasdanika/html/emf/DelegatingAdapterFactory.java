package org.nasdanika.html.emf;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * Base class for adapter factories delegating to {@link Supplier} or {@link Function} to create an adapter and 
 * wrapping adapter into a dynamic proxy if the adapter does not implement {@link Adapter}.
 * @author Pavel Vlasov
 *
 */
public abstract class DelegatingAdapterFactory<T> extends ComposeableAdapterFactoryImpl {
	
	private Class<T> type;
	private ClassLoader proxyClassLoader;
	
	/**
	 * Uses {@link EObject}'s {@link EClass} as eClass argument
	 * @param type
	 * @param proxyClassLoader
	 */
	protected DelegatingAdapterFactory(Class<T> type, ClassLoader proxyClassLoader) {
		this(EcorePackage.Literals.EOBJECT, type, proxyClassLoader);
	}	

	/**
	 * 
	 * @param type
	 * @param proxyClassLoader Proxy class loader, can be null if created adapter implements {@link Adapter} and no proxy is required.
	 */
	protected DelegatingAdapterFactory(EClass eClass, Class<T> type, ClassLoader proxyClassLoader) {
		super(eClass);
		this.type = type;
		this.proxyClassLoader = proxyClassLoader;
	}
	
	@Override
	public boolean isFactoryForType(Object type) {
		return this.type == type;
	}
		
	private class DelegatingAdapter extends AdapterImpl {
		
		private T delegate;

		public DelegatingAdapter(T adapter, Notifier target) {			
			this.delegate = adapter;
			this.target = target;
		}
		
		@Override
		public void setTarget(Notifier newTarget) {			
			if (target != newTarget) {
				if (newTarget == null) {
					delegate = null;
				} else {
					delegate = doCreateAdapter(newTarget);
				}
			}
			super.setTarget(newTarget);
		}
		
		@Override
		public boolean isAdapterForType(Object type) {
			return DelegatingAdapterFactory.this.isFactoryForType(type);
		}		
				
	}
	
	/**
	 * Creates adapter. This adapter does not have to implement {@link Adapter}. 
	 * If it does not then a proxy will be created implementing both T and adapter.
	 * @param target
	 * @return
	 */
	protected abstract T doCreateAdapter(Notifier target);
		
	@Override
	protected Adapter createAdapter(Notifier target) {
		T adapter = doCreateAdapter(target);
		if (adapter instanceof Adapter) {
			return (Adapter) adapter;
		}
		
		DelegatingAdapter da = new DelegatingAdapter(adapter, target);
		
		return (Adapter) Proxy.newProxyInstance(proxyClassLoader, new Class[] { type,  Adapter.class}, new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Object target = method.getDeclaringClass() == Adapter.class ? da : da.delegate;
				return  method.invoke(target, args);
			}
			
		});
	}

}
