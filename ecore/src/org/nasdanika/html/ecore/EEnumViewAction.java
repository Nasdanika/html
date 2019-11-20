package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EEnum;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.Text.Alignment;

public class EEnumViewAction extends EClassifierViewAction<EEnum> {

	public EEnumViewAction(EEnum value) {
		super(value);
	}
	
	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class);
		Container contentContainer = bootstrapFactory.fluidContainer();
		contentContainer.text().alignment(Alignment.LEFT);
		String description = getDescription();
		if (!Util.isBlank(description)) {
			contentContainer.row().col(description).width(Breakpoint.DEFAULT, Size.NONE);
		}
		
		// TODO - enum literals table.
		
		return contentContainer;
	}

}
