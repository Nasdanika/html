package org.nasdanika.cdo.http;

public final class Util {
	
	public static final String JSON_CONTENT_TYPE = "application/json";	
	
	private Util() {
		// Singleton
	}

	public static boolean isBlank(String str) {
		return str == null || str.trim().isEmpty();
	}
	
	

}
