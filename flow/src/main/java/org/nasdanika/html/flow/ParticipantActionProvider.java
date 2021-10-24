package org.nasdanika.html.flow;

import java.util.List;

import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Participant;

public class ParticipantActionProvider extends ServiceProviderActionProvider<Participant> {
	
	public ParticipantActionProvider(Participant value, Context context) {
		super(value, context);
	}
	
	@Override
	protected List<ETypedElement> getProperties() {
		List<ETypedElement> properties = super.getProperties();
		properties.add(FlowPackage.Literals.PARTICIPANT__ARTIFACTS);
		properties.add(FlowPackage.Literals.PARTICIPANT__PARTICIPATES);
		properties.add(FlowPackage.Literals.PARTICIPANT__RESOURCES);
		return properties;
	}

}
