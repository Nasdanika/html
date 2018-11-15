package org.nasdanika.html.bootstrap;

public enum DeviceSize {
	
	EXTRA_SMALL("xs"),
	SMALL("sm"),
	MEDIUM("md"),
	LARGE("lg"),
	EXTRA_LARGE("xl");
	
	DeviceSize(String code) {
		this.code = code;
	}
	
	public final String code;

}
