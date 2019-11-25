package org.nasdanika.html.app;

import org.nasdanika.html.bootstrap.Color;

/**
 * Something that can be displayed in the UI in a variety of ways - buttons, links, badges, alerts, ...
 * @author Pavel Vlasov
 */
public interface Label extends Identity {
	
	
	/**
	 * Label icon. URL if contains slash, css class otherwise, e.g. 'far fa-user'.
	 * @return
	 */
	String getIcon();
	
	/**
	 * Icon text.
	 * @return
	 */
	String getText();
		
	/**
	 * @return Short help text, typically rendered as 'title' attribute.
	 */
	String getTooltip();
	
	/**
	 * @return Bootstrap color background or outline. Applicable only to bootstrap-based UI elements.
	 */
	Color getColor();
	
	/**
	 * Indicates that label shall be rendered as outline, not "solid" - see https://getbootstrap.com/docs/4.1/components/buttons/#outline-buttons 
	 * @return
	 */
	boolean isOutline();
		
	/**
	 * A detailed description which can be shown to the user in, say, a dialog box.
	 * @return
	 */	
	String getDescription();
	
	/**
	 * A notification, e.g. a number of e-mails in the inbox. Notifications are typically shown as badges.
	 * @return
	 */
	String getNotification();

}
