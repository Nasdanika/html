package org.nasdanika.html.ecore;

import java.net.URL;
import java.util.Map;

import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.app.Action;

public class EEnumLiteralViewActionStorable extends ENamedElementViewActionStorable<EEnumLiteral> {

	public EEnumLiteralViewActionStorable(EEnumLiteral value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	public Map<String, Map<String, Object>> store(URL base, ProgressMonitor progressMonitor) throws Exception {
		Map<String, Map<String, Object>> data = super.store(base, progressMonitor);
		put(data, "role", Action.Role.SECTION);
		put(data, "id", eObject.eClass().getName() + "-" + encodeEPackage(eObject.getEEnum().getEPackage()) + "-" + eObject.getEEnum().getName() + "-" + eObject.getName());
		put(data, "href", eObject.getEEnum().getName()+".html#"+eObject.getName());
		
		return data;
	}	
		
}
