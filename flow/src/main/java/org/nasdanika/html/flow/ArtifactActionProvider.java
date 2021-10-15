package org.nasdanika.html.flow;

import java.util.List;

import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.FlowPackage;

public class ArtifactActionProvider extends PackageElementActionProvider<Artifact> {
	
	public ArtifactActionProvider(Artifact value, Context context) {
		super(value, context);
	}
	
	@Override
	protected List<ETypedElement> getProperties() {
		List<ETypedElement> properties = super.getProperties();
		properties.add(FlowPackage.Literals.ARTIFACT__INPUT_FOR);
		properties.add(FlowPackage.Literals.ARTIFACT__OUTPUT_FOR);
		properties.add(FlowPackage.Literals.ARTIFACT__PAYLOAD_FOR);
		properties.add(FlowPackage.Literals.ARTIFACT__REPOSITORIES);
		return properties;
	}
	
}
