package org.nasdanika.html.emf;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.html.app.NavigationActionActivator;

/**
 * Base abstract class for navigation view action activators. Subclass and implement getUrl() method. 
 * @author Pavel Vlasov
 *
 */
public abstract class NavigationViewActionActivatorAdapter extends AdapterImpl implements ViewActionActivator, NavigationActionActivator {

}
