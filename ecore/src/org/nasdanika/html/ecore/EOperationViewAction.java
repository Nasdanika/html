package org.nasdanika.html.ecore;

import java.util.Iterator;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.ETypeParameter;
import org.nasdanika.html.emf.EObjectAdaptable;
import org.nasdanika.html.emf.ViewAction;

public class EOperationViewAction extends ETypedElementViewAction<EOperation> {

	public EOperationViewAction(EOperation value) {
		super(value);
	}
	
	// TODO - parameters.
	
	// TODO - id - signature.
	
	@Override
	public String getText() {
		StringBuilder label = new StringBuilder();

		if (!target.getETypeParameters().isEmpty()) {
			label.append("&lt;");
			for (Iterator<ETypeParameter> i = target.getETypeParameters().iterator(); i.hasNext();) {
				ETypeParameter typeParameter = i.next();
				label.append(getTypeParameterLabel(typeParameter));
				if (i.hasNext()) {
					label.append(", ");
				}
			}
			label.append("&gt; ");
		}

		if (target.getEGenericType() == null) {
			label.append("void ");
		}
		label.append(super.getText());

		if (!target.getEParameters().isEmpty()) {
			label.append("(");
			for (Iterator<EParameter> i = target.getEParameters().iterator(); i.hasNext();) {
				EParameter parameter = i.next();
				label.append(EObjectAdaptable.adaptTo(parameter, ViewAction.class).getText());
				if (i.hasNext()) {
					label.append(", ");
				}
			}
			label.append(")");
		}

		if (!target.getEExceptions().isEmpty()) {
			label.append(" throws ");
			for (Iterator<EClassifier> i = target.getEExceptions().iterator(); i.hasNext();) {
				EClassifier exception = i.next();
				label.append(EObjectAdaptable.adaptTo(exception, ViewAction.class).getText());
				if (i.hasNext()) {
					label.append(", ");
				}
			}
		}

		return label.toString();		
	}

}
