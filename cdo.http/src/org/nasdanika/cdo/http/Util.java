package org.nasdanika.cdo.http;

public final class Util {
	
	public static final String JSON_CONTENT_TYPE = "application/json";
	public static final String PATH_VARIABLE_ROUTE_URL = "routeURL";
	public static final String PATH_VARIABLE_ROUTE_PATH = "routePath";
	public static final String PATH_VARIABLE_PATH_INFO = "pathInfo";	
	
	private Util() {
		// Singleton
	}

	public static boolean isBlank(String str) {
		return str == null || str.trim().isEmpty();
	}
	
	

}
