package org.nasdanika.html.app;

import org.nasdanika.html.bootstrap.Color;

public interface MutableLabel extends Label, MutableIdentity {

	void setIcon(String icon);

	void setText(String text);

	void setTooltip(String tooltip);

	void setColor(Color color);

	void setOutline(boolean outline);

	void setDescription(String description);

	void setNotification(String notification);

}
