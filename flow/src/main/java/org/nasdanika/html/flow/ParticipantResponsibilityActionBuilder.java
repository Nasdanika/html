package org.nasdanika.html.flow;

import java.util.List;

import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.ParticipantResponsibility;

public class ParticipantResponsibilityActionBuilder<T extends ParticipantResponsibility<?>> extends PackageElementActionBuilder<T> {
	
	public ParticipantResponsibilityActionBuilder(T value, Context context) {
		super(value, context);
	}
	
	@Override
	protected List<ETypedElement> getProperties() {
		List<ETypedElement> properties = super.getProperties();
		properties.add(FlowPackage.Literals.PARTICIPANT_RESPONSIBILITY__RESPONSIBLE);
		properties.add(FlowPackage.Literals.PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE);
		properties.add(FlowPackage.Literals.PARTICIPANT_RESPONSIBILITY__CONSULTED);
		properties.add(FlowPackage.Literals.PARTICIPANT_RESPONSIBILITY__INFORMED);
		return properties;
	}

}
