package org.nasdanika.html.app;

import org.nasdanika.html.bootstrap.Color;

/**
 * Status for diagnostics.
 * @author Pavel Vlasov
 *
 */
public enum Status {
	
	SUCCESS(Color.SUCCESS, "fas fa-check-circle"),
	WARNING(Color.WARNING, "fas fa-exclamation-triangle"),
	ERROR(Color.DANGER, "fas fa-times-circle");
	
	public final Color color;
	public final String icon;
	
	private Status(Color color, String icon) {
		this.color = color;
		this.icon = icon;
	}

}
