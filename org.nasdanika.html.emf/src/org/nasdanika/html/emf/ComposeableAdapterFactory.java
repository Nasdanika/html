package org.nasdanika.html.emf;

import org.eclipse.emf.common.notify.AdapterFactory;

public interface ComposeableAdapterFactory extends AdapterFactory {
	
	  ComposeableAdapterFactory getRootAdapterFactory();

	  void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory);	

}
