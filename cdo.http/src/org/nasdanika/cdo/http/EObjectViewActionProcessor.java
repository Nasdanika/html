package org.nasdanika.cdo.http;

import java.util.function.Function;

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
public class EObjectViewActionProcessor<T extends EObject> implements Processor {
	
	private T target;

	public EObjectViewActionProcessor(T target) {
		this.target = target;
	}

	@Override
	public Result process(HttpServletRequest request, HttpServletResponse response, Function<String,String> pathVariables) throws Exception {
		// TODO - check request method (GET) and access (read)
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

		return Result.NOT_FOUND;
	}

}
