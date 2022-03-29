package org.nasdanika.html.flow;

import java.util.List;

import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.flow.Activity;
import org.nasdanika.flow.FlowPackage;

public class ActivityActionBuilder<T extends Activity<?>> extends FlowElementActionBuilder<T> {
	
	public ActivityActionBuilder(T value, Context context) {
		super(value, context);
	}
	
	@Override
	protected List<ETypedElement> getProperties() {
		List<ETypedElement> properties = super.getProperties();
		properties.add(FlowPackage.Literals.ACTIVITY__SERVICES);
		return properties;
	}

}
