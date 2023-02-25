package org.nasdanika.html.emf;

import java.util.List;
import java.util.function.BiConsumer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypedElement;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.model.app.Action;
import org.nasdanika.html.model.app.SectionStyle;
import org.nasdanika.html.model.bootstrap.Table;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Temporal;

public class TemporalActionBuilder extends NcoreActionBuilder<Temporal> {
	
	public TemporalActionBuilder(Temporal value, Context context) {
		super(value, context);		
	}
	
	@Override
	protected Action buildAction(
			Action action,
			BiConsumer<EObject,Action> registry, 
			java.util.function.Consumer<org.nasdanika.common.Consumer<org.nasdanika.html.emf.EObjectActionResolver.Context>> resolveConsumer, 
			ProgressMonitor progressMonitor) {
		Action ret = super.buildAction(action, registry, resolveConsumer, progressMonitor);		
		Temporal eObj = getTarget();
		
		String description = eObj.getDescription();
		addContent(ret, description);
		
		ret.setText(eObj.normalize().toString()); 
		ret.setSectionStyle(SectionStyle.HEADER);
		return ret;
	}

	@Override
	public String name() {
		return getTarget().normalize().toString();
	}
	
	@Override
	protected List<ETypedElement> getProperties(String type) {
		List<ETypedElement> properties = super.getProperties(type);
		if (HEAD_PROPERTY_TABLE_KEY.equals(type)) {
			properties.add(NcorePackage.Literals.TEMPORAL__BASE);
			properties.add(NcorePackage.Literals.TEMPORAL__OFFSET);
		}
		return properties;
	}
	
	@Override
	protected Table createPropertiesTable(
			Action action,
			String type,
			org.nasdanika.html.emf.EObjectActionResolver.Context context,
			ProgressMonitor progressMonitor) {
		Table propertiesTable = super.createPropertiesTable(action, type, context, progressMonitor);
		propertiesTable.getAttributes().put("style", createText("width:auto"));
		return propertiesTable;
	}
	
	// TODO - derivatives, lower bounds, upper bounds.
	
}
