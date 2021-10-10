package org.nasdanika.html.flow;

import org.nasdanika.common.Context;
import org.nasdanika.flow.Activity;

public class ActivityActionProvider<T extends Activity<?>> extends FlowElementActionProvider<T> {
	
	public ActivityActionProvider(T value, Context context) {
		super(value, context);
	}

}
