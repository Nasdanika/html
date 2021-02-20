package org.nasdanika.html.ecore;

import java.net.URL;
import java.util.Map;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;

public class ENamedElementViewActionStorable<T extends ENamedElement> extends EModelElementViewActionStorable<T> {
	
	public ENamedElementViewActionStorable(T value, Context context, java.util.function.Function<EPackage,String> ePackagePathComputer) {
		super(value, context, ePackagePathComputer);
	}
	
	@Override
	public Map<String, Object> store(URL base, ProgressMonitor progressMonitor) throws Exception {
		Map<String, Object> data = super.store(base, progressMonitor);
		data.put("text", eObject.getName());
		return data;
	}

}
