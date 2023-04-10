package org.nasdanika.html.model.app.graph;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.html.model.app.Label;

/**
 * This record is returned as a result of registry search and also is used as a factory handler/endpoint
 * @author Pavel
 *
 */
public interface LabelInfo {

	URI getURI();
	Label getLabel();
	String getText();
	String getIcon(); 
	String getTooltip();

}
