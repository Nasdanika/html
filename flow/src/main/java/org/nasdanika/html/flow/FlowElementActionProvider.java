package org.nasdanika.html.flow;

import org.nasdanika.common.Context;
import org.nasdanika.flow.FlowElement;

public class FlowElementActionProvider<T extends FlowElement<?>> extends PackageElementActionProvider<T> {
	
	public FlowElementActionProvider(T value, Context context) {
		super(value, context);
	}

}
