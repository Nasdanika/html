package org.nasdanika.html.emf;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.html.app.PropertyDescriptor;

public class EReferenceSingleValuePropertySource<T extends EObject> extends EStructuralFeatureSingleValuePropertySource<T,EReference> {

	protected EClassPropertySource propertySourceDelegate;

	public EReferenceSingleValuePropertySource(T eObject, EReference feature) {
		super(eObject, feature);
		propertySourceDelegate = new EClassPropertySource(feature.getEReferenceType(), (AuthorizationProvider) EcoreUtil.getRegisteredAdapter(eObject, AuthorizationProvider.class));
	}

	@Override
	public List<PropertyDescriptor> getPropertyDescriptors() {
		return propertySourceDelegate.getPropertyDescriptors();
	}

}
