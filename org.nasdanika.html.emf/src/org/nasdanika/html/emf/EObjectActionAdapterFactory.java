package org.nasdanika.html.emf;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.PropertySource;

/**
 * Adapts {@link EObject} to {@link Action}. 
 * To do so it adapts the target to {@link PropertySource} first.
 * @author Pavel Vlasov
 *
 */
public class EObjectActionAdapterFactory extends ComposeableAdapterFactoryImpl {
	
	@Override
	public boolean isFactoryForType(Object type) {
		return Action.class == type;
	}
		
	@Override
	protected Adapter createAdapter(Notifier target) {
//		PropertySource propertySource = (PropertySource) EcoreUtil.getRegisteredAdapter((EObject) target, PropertySource.class);
//		if (propertySource == null) {
//			return null;
//		}
		
		return new EObjectActionAdapter(); // TODO - adapt to property source, pass to constructor
	}

}
