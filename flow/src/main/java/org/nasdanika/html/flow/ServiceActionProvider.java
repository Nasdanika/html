package org.nasdanika.html.flow;

import java.util.List;

import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Service;

public class ServiceActionProvider extends FlowElementActionProvider<Service> {
	
	public ServiceActionProvider(Service value, Context context) {
		super(value, context);
	}
	
	@Override
	protected List<ETypedElement> getProperties() {
		List<ETypedElement> properties = super.getProperties();
		properties.add(FlowPackage.Literals.SERVICE__TARGET);
		return properties;
	}

}
