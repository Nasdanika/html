package org.nasdanika.html.emf;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.PropertyDescriptor;
import org.nasdanika.html.app.PropertySource;
import org.nasdanika.html.app.SingleValuePropertySource;
import org.nasdanika.html.bootstrap.Color;

public class EObjectSingleValuePropertySource extends EObjectSingleValueDataSource implements SingleValuePropertySource {
	
	/**
	 * Feature roles indicating that it shall be wrapped into a {@link PropertyDescriptor}.
	 */
	public static final String FEATURE_ROLE_PROPERTY_DESCRIPTOR = "propertyDescriptor";
	
	public EObjectSingleValuePropertySource(EObject value) {
		super(value);
		propertySourceDelegate = new EClassPropertySource(value.eClass(), (AuthorizationProvider) EcoreUtil.getRegisteredAdapter(value, AuthorizationProvider.class)) {
			
			@Override
			protected boolean isPropertyDescriptorFeature(EStructuralFeature feature) {
				return FEATURE_ROLE_PROPERTY_DESCRIPTOR.equals(EObjectSingleValuePropertySource.this.getFeatureRole(feature));
			}
			
		};
	}
	
	/**
	 * Feature may play different roles, e.g. a property descriptor or a navigation child action. 
	 * This implementation returns ``propertyDescriptor`` ({@link EObjectSingleValuePropertySource}.FEATURE_ROLE_PROPERTY_DESCRIPTOR) if
	 * feature is single value and is not a containment {@link EReference}.
	 * @param feature
	 * @param role
	 * @return
	 */
	protected String getFeatureRole(EStructuralFeature feature) {
		return !feature.isMany() && !(feature instanceof EReference && ((EReference) feature).isContainment()) ? FEATURE_ROLE_PROPERTY_DESCRIPTOR : null;
	}

	protected PropertySource propertySourceDelegate;

	@Override
	public List<PropertyDescriptor> getPropertyDescriptors() {
		return propertySourceDelegate == null ? Collections.emptyList() : propertySourceDelegate.getPropertyDescriptors();
	}

	@Override
	public List<Action> getActions() {
		return propertySourceDelegate == null ? Collections.emptyList() : propertySourceDelegate.getActions();
	}

	@Override
	public List<Action> getActions(Object obj) {
		return propertySourceDelegate == null ? Collections.emptyList() : propertySourceDelegate.getActions(obj);
	}

	@Override
	public String getIcon() {
		return propertySourceDelegate == null ? null : propertySourceDelegate.getIcon();
	}

	@Override
	public String getText() {

		// Using the first string property as label.
		for (PropertyDescriptor pd: propertySourceDelegate.getPropertyDescriptors()) {
			Object dv = pd.getDisplayValue(getValue());
			if (dv instanceof String && !((String) dv).trim().isEmpty()) {
				return (String) dv;
			}
		}

		// Using the first non-null property as label.
		for (PropertyDescriptor pd: propertySourceDelegate.getPropertyDescriptors()) {
			Object dv = pd.getDisplayValue(getValue());
			if (dv != null) {
				String vStr = dv.toString();
				if (!vStr.trim().isEmpty()) {
					return vStr;
				}
			}
		}
		return propertySourceDelegate == null ? null : propertySourceDelegate.getText();
	}

	@Override
	public String getTooltip() {
		return propertySourceDelegate == null ? null : propertySourceDelegate.getTooltip(); // Type, path???
	}

	@Override
	public Color getColor() {
		return propertySourceDelegate == null ? null : propertySourceDelegate.getColor();
	}

	@Override
	public boolean isOutline() {
		return propertySourceDelegate == null ? null : propertySourceDelegate.isOutline();
	}

	@Override
	public String getDescription() {
		return propertySourceDelegate == null ? null : propertySourceDelegate.getDescription();
	}

	@Override
	public Object getId() {
		if (getValue() instanceof CDOObject) {
			CDOObject cdoObj = (CDOObject) getValue();
			CDOID cdoId = cdoObj.cdoID();
			if (cdoId != null && !cdoId.isTemporary()) {
				return cdoId;
			}
		}
		return null;
	}

	@Override
	public String getNotification() {
		return propertySourceDelegate == null ? null : propertySourceDelegate.getNotification();
	}


}