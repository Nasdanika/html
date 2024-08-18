package org.nasdanika.html.model.app.graph.drawio;

import java.util.ArrayList;
import java.util.Collection;

import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.ModelElement;

public class LinkTargetProcessor<T extends LinkTarget> extends BaseProcessor<T> {
	
	public LinkTargetProcessor(DrawioProcessorFactory factory) {
		super(factory);
	}

	public Collection<ModelElement> referrers = new ArrayList<>();	


}
