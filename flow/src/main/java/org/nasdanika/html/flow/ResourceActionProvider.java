package org.nasdanika.html.flow;

import java.util.List;

import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Resource;

public class ResourceActionProvider extends PackageElementActionProvider<Resource> {
	
	public ResourceActionProvider(Resource value, Context context) {
		super(value, context);
	}
	
	@Override
	protected List<ETypedElement> getProperties() {
		List<ETypedElement> properties = super.getProperties();
		properties.add(FlowPackage.Literals.RESOURCE__ARTIFACTS);
		properties.add(FlowPackage.Literals.RESOURCE__SERVICES);
		properties.add(FlowPackage.Literals.RESOURCE__USED_BY);
		properties.add(FlowPackage.Literals.RESOURCE__USED_IN);
		return properties;
	}

}
