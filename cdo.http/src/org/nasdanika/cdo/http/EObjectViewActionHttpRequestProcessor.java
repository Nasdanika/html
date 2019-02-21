package org.nasdanika.cdo.http;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.ApplicationFactory;
import org.nasdanika.html.emf.AccessController;
import org.nasdanika.html.emf.EObjectAdaptable;

/**
 * This processor handles ".html" path info by generating an EObject view application.
 * @author Pavel
 *
 * @param <T>
 */
public class EObjectViewActionHttpRequestProcessor<T extends EObject> implements HttpRequestProcessor {
	
	private T target;

	public EObjectViewActionHttpRequestProcessor(T target) {
		this.target = target;
	}

	@Override
	public Result process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (".html".equals(request.getPathInfo())) {
			AccessController accessController = EObjectAdaptable.adaptTo(target, AccessController.class);
			if (accessController == null || accessController.canRead(null)) {
				Application application = EObjectAdaptable.adaptTo(target, ApplicationFactory.class).createApplication();
				if (application != null) {
					ApplicationBuilder applicationBuilder = EObjectAdaptable.adaptTo(target, ApplicationBuilder.class);
					applicationBuilder.build(application);
					
					return new Result() {
	
						@Override
						public Object getValue() {
							return application;
						}
	
						@Override
						public String getContentType() {
							return "text/html";
						}
						
					};
				}
			}
		}

		response.sendError(HttpServletResponse.SC_NOT_FOUND);
		return null;
	}

}
