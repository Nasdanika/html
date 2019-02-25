package org.nasdanika.cdo.http;

import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.ApplicationFactory;

/**
 * This route handles ".html" path info by generating an EObject view application.
 * @author Pavel
 *
 * @param <T>
 */
public class EObjectViewRoute {
	
	@Route(path=".html", produces="text/html", value=RequestMethod.GET)
	public Object getView(
			@EntityParameter ApplicationFactory applicationFactory, 
			@EntityParameter ApplicationBuilder applicationBuilder,
			@PathParameter("routeURL") String routeURL) {
		
		Application application = applicationFactory.createApplication();
		applicationBuilder.build(application);
		System.out.println(routeURL);
		return application;
	}

}
