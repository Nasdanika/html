package org.nasdanika.html.emf;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;

public class EClassLabel extends ENamedElementLabel<EClass> {

	public EClassLabel(EClass eNamedElement) {
		super(eNamedElement);
	}
	
	@Override
	public Object getId() {
		List<String> modelPath = new ArrayList<>();
		modelPath.add(eNamedElement.getEPackage().getNsURI());
		modelPath.add(eNamedElement.getName());
		return modelPath;
	}

}
