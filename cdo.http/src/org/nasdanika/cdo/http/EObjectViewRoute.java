package org.nasdanika.cdo.http;

import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.ApplicationFactory;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
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
		
		Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

		String jws = Jwts.builder().setSubject("Joe").signWith(key).compact();		
		System.out.println(jws);
		return application;
	}

}
