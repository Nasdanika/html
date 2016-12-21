package org.nasdanika.html.impl;

import java.util.List;

import org.nasdanika.html.ApplicationPanel.ContentPanel;


interface ApplicationPanelConfig {
	
	String getAttributes();

	int getWidth();

	String getHeader();

	Object getHeaderLink();

	int getMinHeight();

	String getNavigation();

	List<ContentPanel> getContentPanels();

	String getFooter();

	String getStyle();

}
