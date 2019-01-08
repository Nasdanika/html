package org.nasdanika.html.emf;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.cdo.common.id.CDOID;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.PropertyDescriptor;
import org.nasdanika.html.app.PropertySource;
import org.nasdanika.html.app.SingleValuePropertySource;
import org.nasdanika.html.bootstrap.Color;

public class EObjectSingleValuePropertySourceAdapter extends EObjectSingleValueDataSourceAdapter implements SingleValuePropertySource {
	
	protected PropertySource propertySourceDelegate;
	
	@Override
	public void setTarget(Notifier newTarget) {
		super.setTarget(newTarget);
		if (newTarget instanceof EObject) {
			EObject eObj = (EObject) newTarget;
			setPropertySourceDelegate(new EClassPropertySource(eObj.eClass(), (AuthorizationProvider) EcoreUtil.getRegisteredAdapter(eObj, AuthorizationProvider.class)));
		} else {
			setPropertySourceDelegate(null);
		}
	}
	
	protected void setPropertySourceDelegate(PropertySource propertySourceDelegate) {
		this.propertySourceDelegate = propertySourceDelegate;
	}
	
	@Override
	public void unsetTarget(Notifier oldTarget) {
		super.unsetTarget(oldTarget);
		setPropertySourceDelegate(null);
	}

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
		if (getTarget() instanceof CDOObject) {
			CDOObject cdoObj = (CDOObject) getTarget();
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
