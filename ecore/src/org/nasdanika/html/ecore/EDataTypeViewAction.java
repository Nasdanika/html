package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EDataType;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.Util;

public class EDataTypeViewAction extends EClassifierViewAction<EDataType> {

	public EDataTypeViewAction(EDataType value) {
		super(value);
	}
	
	@Override
	public Object generate(ViewGenerator viewGenerator) {
		String description = getDescription();
		return Util.isBlank(description) ? "" : description;
	}
	
}
