package org.nasdanika.html.emf.legacy;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.PropertyComputer;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.ViewGeneratorImpl;

public class ViewGeneratorAdapter extends ViewGeneratorImpl {

	public ViewGeneratorAdapter(EObject target) {
		super(headContentConsumer, bodyContentConsumer)
	}
	
	/**
	 * Computes and sets base URI and doc URI relative to the site root - base uri obtained from the target context.
	 * TODO - extract into a {@link ViewGenerator} adapter relying on {@link ActionActivator} adapter. 
	 * @return Action context built from the resource set context plus link resolver and "base-uri" property containing relative URI's of the site root. 
	 */
	@Override
	public Context getContext() {
		URI thisUri = URI.createURI(((NavigationActionActivator) getActivator()).getUrl(null));
		Context targetContext = EObjectAdaptable.adaptTo(getSemanticElement(), Context.class);
		URI baseUri = URI.createURI(targetContext.get(Context.BASE_URI_PROPERTY).toString());
		URI relativeBaseUri = baseUri.deresolve(thisUri, true, true, true);
		MutableContext ret = targetContext.fork();
		ret.put(Context.BASE_URI_PROPERTY, relativeBaseUri);
		
		ret.put("link", new PropertyComputer() {
			
			@SuppressWarnings("unchecked")
			@Override
			public <U> U compute(Context context, String key, String path, Class<U> type) {
				URI uri = URI.createURI(path);
				EObject eObj = getSemanticElement().eResource().getResourceSet().getEObject(uri, false);
				if (eObj == null) {
					return null;
				}

				Action action = ViewAction.adaptToViewActionNonNull(eObj);
				return (U) ((NavigationActionActivator) action.getActivator()).getUrl(thisUri.toString());
			}
		});
		
		Object targetDocUri = targetContext.get(DOC_URI);
		if (targetDocUri != null) {
			URI docUri = URI.createURI(targetDocUri.toString());			
			URI relativeDocUri = docUri.deresolve(thisUri, true, true, true);
			ret.put(DOC_URI, relativeDocUri);
		}
		
		return ret;
	}
	

}
