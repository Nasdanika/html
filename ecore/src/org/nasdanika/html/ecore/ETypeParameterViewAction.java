package org.nasdanika.html.ecore;

import java.util.Iterator;

import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.ETypeParameter;

public class ETypeParameterViewAction extends ENamedElementViewAction<ETypeParameter> {

	public ETypeParameterViewAction(ETypeParameter value) {
		super(value);
	}
	
	@Override
	public String getText() {
		StringBuilder label = new StringBuilder();

		label.append(super.getText());

		if (!target.getEBounds().isEmpty()) {
			label.append(" extends ");
			for (Iterator<EGenericType> j = target.getEBounds().iterator(); j.hasNext();) {
				EGenericType bound = j.next();
				label.append(computeLabel(bound));
				if (j.hasNext()) {
					label.append(" &amp; ");
				}
			}
		}
		return label.toString();
	}
	
	
}
