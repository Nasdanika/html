package org.nasdanika.html.model.app.graph.drawio;

import java.util.ArrayList;
import java.util.Collection;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;

public class LinkTargetProcessor<T extends LinkTarget> extends BaseProcessor<T> {
	
	public LinkTargetProcessor(DrawioProcessorFactory factory) {
		super(factory);
	}
	
	public void addReferrer(ModelElement referrer) {
		referrers.add(referrer);
	}
	
	public Collection<ModelElement> getReferrers() {
		return referrers;
	}

	protected Collection<ModelElement> referrers = new ArrayList<>();	
	
	protected boolean isLogicalChildConnection(ModelElement modelElement) {
		if (modelElement instanceof Connection) {
			Node source = ((Connection) modelElement).getSource();
			if (source != null) {
				return source == element || referrers.contains(source);
			}
			return element == modelElement.getParent() || referrers.contains(modelElement.getParent());
		}
		return false;
	}	

}
