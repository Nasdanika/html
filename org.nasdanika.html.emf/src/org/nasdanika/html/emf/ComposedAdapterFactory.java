package org.nasdanika.html.emf;

import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;

/**
 * Composed factory delegates to its child factories. It uses inheritance ordering to delegate -
 * delegates to factories registered for more specific classes first. If there are several factories
 * for the same class then they are compared using factory's compare method if it implements comparable or by inheritance - more specific first.
 * @author Pavel Vlasov
 *
 */
public class ComposedAdapterFactory implements ComposeableAdapterFactory {

	@Override
	public boolean isFactoryForType(Object type) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object adapt(Object object, Object type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Adapter adapt(Notifier target, Object type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Adapter adaptNew(Notifier target, Object type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void adaptAllNew(Notifier notifier) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ComposeableAdapterFactory getRootAdapterFactory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Registers a factory for all classes in the {@link EPackage}.
	 * @param child
	 * @param ePackage
	 */
	public void registerAdapterFactory(AdapterFactory child, EPackage ePackage) {
		for (EClassifier ec: ePackage.getEClassifiers()) {
			if (ec instanceof EClass) {
				registerAdapterFactory(child, (EClass) ec);
			}
		}
	}
	
	// Example of finding min/max
//	Predicate<? super Object> predicate = null;
//	Comparator<? super Object> comparator = null;
//	Function adaptCall;
//	Optional<Object> min = Collections.emptyList().stream().filter(predicate).max(comparator).map(adaptCall);
	
	// TODO - comparing factories using a comparator:
	// delegate on one of being compared if it implements comparable
	// compare by inheritance and then by fully qualified class name.

	/**
	 * Registers a factory for specified {@link EClass}es in the package.
	 * If no EClasses are specified then the adapter factory is considered to be
	 * applicable to all classes.
	 * @param child
	 * @param eClasses
	 */
	public void registerAdapterFactory(AdapterFactory child, EClass... eClasses) {
		
	}
}
