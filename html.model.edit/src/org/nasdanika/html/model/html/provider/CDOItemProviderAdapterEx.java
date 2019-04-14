package org.nasdanika.html.model.html.provider;

import org.eclipse.emf.cdo.edit.CDOItemProviderAdapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;

/**
 * Fixes delete problem for local CDO models.
 * @author Pavel Vlasov
 *
 */
public class CDOItemProviderAdapterEx extends CDOItemProviderAdapter {
	
	protected CDOItemProviderAdapterEx(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Object getParent(Object object) {
		Object parent = super.getParent(object);
		if (parent == null && object instanceof EObject) {
			EObject container = ((EObject) object).eContainer();
			parent = container == null ? ((EObject) object).eResource() : container;
		}
		return parent;
	}
		
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}	

}
