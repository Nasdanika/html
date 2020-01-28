package org.nasdanika.html.ecore;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.Util;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Breakpoint;
import org.nasdanika.html.bootstrap.Container;
import org.nasdanika.html.bootstrap.Size;
import org.nasdanika.html.bootstrap.Text.Alignment;
import org.nasdanika.html.emf.ViewAction;

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
		
	@Override
	public List<Action> getChildren() {
		List<Action> ret = new ArrayList<>();
		
		for (EEnumLiteral literal: target.getELiterals()) {			
			Action literalAction = adaptTo(literal, ViewAction.class);
			if (literalAction != null) {
				ret.add(filterChildAction(literalAction, Action.Role.NAVIGATION, null));
			}
		}
		return ret;
	}	

}
