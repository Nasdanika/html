package org.nasdanika.html.flow;

import java.util.List;

import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.ServiceProvider;

public class ServiceProviderActionProvider<T extends ServiceProvider<?>> extends PackageElementActionProvider<T> {
	
	public ServiceProviderActionProvider(T value, Context context) {
		super(value, context);
	}
	
	@Override
	protected List<ETypedElement> getProperties() {
		List<ETypedElement> properties = super.getProperties();
		properties.add(FlowPackage.Literals.SERVICE_PROVIDER__SERVICES);
		return properties;
	}

}
