package org.nasdanika.html.ecore;

import java.util.Iterator;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.ETypeParameter;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.html.emf.ViewAction;

public class EClassifierViewAction<T extends EClassifier> extends ENamedElementViewAction<T> {
	
	public static final String iconsBase = "https://www.nasdanika.org/resources/images/ecore/";

	public EClassifierViewAction(T value) {
		super(value);
	}
	
	@Override
	public Object getId() {
		Object parentId = getParent().getId();
		if (parentId instanceof String && ((String) parentId).endsWith(EPackageViewAction.PACKAGE_SUMMARY_SUFFIX)) {
			String parentIdStr = (String) parentId;
			parentId = parentIdStr.substring(0, parentIdStr.length() - EPackageViewAction.PACKAGE_SUMMARY_SUFFIX.length()); 
		}
		return parentId+"/"+((ENamedElement) getValue()).getName();
	}
	
	@Override
	public String getText() {
		StringBuilder label = new StringBuilder(super.getText());

		if (!target.getETypeParameters().isEmpty()) {
			label.append("&lt;");
			Iterator<ETypeParameter> tpit = target.getETypeParameters().iterator();
			while (tpit.hasNext()) {
				ETypeParameter typeParameter = tpit.next();
				label.append(EObjectAdaptable.adaptTo(typeParameter, ViewAction.class).getText());
				if (tpit.hasNext()) {
					label.append(", ");
				}
			}
			label.append("&gt;");
		}
		return label.toString();
	}

}
