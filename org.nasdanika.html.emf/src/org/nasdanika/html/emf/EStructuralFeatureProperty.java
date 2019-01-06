package org.nasdanika.html.emf;

import java.util.List;

import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.html.app.Choice;
import org.nasdanika.html.app.Diagnostic;
import org.nasdanika.html.app.Property;

public class EStructuralFeatureProperty implements Property {

	private EStructuralFeature feature;

	public EStructuralFeatureProperty(EStructuralFeature feature) {
		this.feature = feature;
	}

	@Override
	public Object getDisplayValue(Object obj) {
		// TODO - type-specific formatting.
		if (obj == null) {
			return "";
		}
		return StringEscapeUtils.escapeHtml4(obj.toString());
	}
	
	protected Object getValue(Object obj) {
		return ((EObject) obj).eGet(feature);
	}

	@Override
	public Object getEditValue(Object obj) {
		// TODO - type-specific formatting
		return getValue(obj);
	}

	@Override
	public List<Choice> getChoices(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEditable(Object obj) {
		if (obj instanceof EObject) {
			AuthorizationProvider ap = (AuthorizationProvider) EcoreUtil.getRegisteredAdapter((EObject) obj, AuthorizationProvider.class);
			if (ap != null && !ap.authorizeUpdate(feature.getName())) {
				return false;
			}
		}
		return feature.isChangeable(); 
	}

	@Override
	public Diagnostic update(Object obj, Object originalValue, Object newValue) {
		// check update access, return error diagnostic if no access.
		// TODO Auto-generated method stub
		return null;
	}

}
