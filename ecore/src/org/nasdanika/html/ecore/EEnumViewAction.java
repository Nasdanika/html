package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EEnum;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Text.Alignment;

public class EEnumViewAction extends EClassifierViewAction<EEnum> {

	public EEnumViewAction(EEnum value) {
		super(value);
	}
	
	@Override
	public Object generate(ViewGenerator viewGenerator) {
		BootstrapFactory bootstrapFactory = viewGenerator.getBootstrapFactory();
		Container contentContainer = bootstrapFactory.fluidContainer();
		contentContainer.text().alignment(Alignment.LEFT);
		String description = getDescription();
		if (!Util.isBlank(description)) {
			contentContainer.row().col(description);
		}
		
		// TODO - enum literals table.
		
		return contentContainer;
	}

}
