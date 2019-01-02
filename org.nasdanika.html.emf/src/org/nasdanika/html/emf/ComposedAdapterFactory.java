package org.nasdanika.html.emf;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

/**
 * Composed factory delegates to its child factories. It uses inheritance ordering to delegate -
 * delegates to factories registered for more specific classes first. If there are several factories
 * for the same class then they are compared using factory's compare method if it implements comparable or by inheritance - more specific first.
 * @author Pavel Vlasov
 *
 */
public class ComposedAdapterFactory implements ComposeableAdapterFactory {

	private ComposedAdapterFactory parentAdapterFactory;

	@Override
	public boolean isFactoryForType(Object type) {
		for (AdapterFactoryEntry c: children) {
			if (c.adapterFactory.isFactoryForType(type)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object adapt(Object object, Object type) {
		for (AdapterFactory af: match(object, type)) {
			Object ret = af.adapt(object, type);
			if (ret != null) {
				return ret;
			}
		}
		return null;
	}

	@Override
	public Adapter adapt(Notifier target, Object type) {
		for (AdapterFactory af: match(target, type)) {
			Adapter ret = af.adapt(target, type);
			if (ret != null) {
				return ret;
			}
		}
		return null;
	}

	@Override
	public Adapter adaptNew(Notifier target, Object type) {
		for (AdapterFactory af: match(target, type)) {
			Adapter ret = af.adaptNew(target, type);
			if (ret != null) {
				return ret;
			}
		}
		return null;
	}

	@Override
	public void adaptAllNew(Notifier notifier) {
		for (AdapterFactory af: match(notifier, null)) {
			af.adaptAllNew(notifier);
		}
	}

	@Override
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	@Override
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;		
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
	
	protected List<AdapterFactory> match(Object obj, Object type) {
		return children
			.stream()
			.filter(ae -> ae.match(obj) && (type == null || ae.adapterFactory.isFactoryForType(type)))
			.sorted((a,b) -> cmpDistance((EObject) obj, a.eClass, b.eClass))
			.map(ae -> ae.adapterFactory)
			.collect(Collectors.toList());
	}
	
	protected int cmpDistance(EObject obj, EClass a, EClass b) {
		if (a == b) {
			return 0;
		}
		if (a == null) {
			return 1;
		}			
		if (b == null) {
			return -1;
		}
		if (a.equals(b)) {
			return 0;
		}
		if (a.isSuperTypeOf(b)) {
			return 1;
		}
		if (b.isSuperTypeOf(a)) {
			return -1;
		}
		if (obj == null) {
			return 0; // Does not matter.
		}
		EClass eClass = obj.eClass();
		if (eClass.equals(a)) {
			return -1;
		}
		if (eClass.equals(b)) {
			return 1;
		}
		return distance(eClass, a) - distance(eClass, b);
	}
	
	protected int distance(EClass sub, EClass sup) {
		if (sub.equals(sup)) {
			return 0;
		}
		int ret = Integer.MAX_VALUE;
		for (EClass isup: sub.getESuperTypes()) {
			if (isup.equals(sup)) {
				return 1;
			}
			if (sup.isSuperTypeOf(isup)) {
				ret = Math.min(ret, distance(isup, sup)); 
			}
		}
		return ret;
	}
		
	private class AdapterFactoryEntry {
		
		EClass eClass;
		AdapterFactory adapterFactory;
		
		AdapterFactoryEntry(EClass eClass, AdapterFactory adapterFactory) {
			super();
			this.eClass = eClass;
			this.adapterFactory = adapterFactory;
		}
		
		boolean match(Object obj) {
			return obj == null || (obj instanceof EObject && (eClass == null || eClass.isInstance(obj)));
		}				
		
	}
	
	private List<AdapterFactoryEntry> children = new ArrayList<>();

	/**
	 * Registers a factory for specified {@link EClass}es in the package.
	 * If no EClasses are specified then the adapter factory is considered to be
	 * applicable to all classes.
	 * @param child
	 * @param eClasses
	 */
	public void registerAdapterFactory(AdapterFactory child, EClass... eClasses) {
		if (eClasses.length == 0) {
			children.add(new AdapterFactoryEntry(null, child));
		} else for (EClass eClass: eClasses) {
			children.add(new AdapterFactoryEntry(eClass, child));			
		}
	}
}
