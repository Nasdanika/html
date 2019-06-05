package org.nasdanika.html.ecore;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;

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

}
