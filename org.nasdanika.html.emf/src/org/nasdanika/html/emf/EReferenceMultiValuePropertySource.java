package org.nasdanika.html.emf;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.html.app.PropertyDescriptor;

public class EReferenceMultiValuePropertySource extends EStructuralFeatureMultiValuePropertySource {

	protected EClassPropertySource propertySourceDelegate;

	public EReferenceMultiValuePropertySource(EObject eObject, EStructuralFeature feature) {
		super(eObject, feature);
		propertySourceDelegate = new EClassPropertySource(eObject.eClass(), (AuthorizationProvider) EcoreUtil.getRegisteredAdapter(eObject, AuthorizationProvider.class));
	}

	@Override
	public List<PropertyDescriptor> getPropertyDescriptors() {
		return propertySourceDelegate.getPropertyDescriptors();
	}

}
