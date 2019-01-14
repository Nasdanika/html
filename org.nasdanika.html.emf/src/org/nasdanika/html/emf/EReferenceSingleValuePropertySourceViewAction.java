package org.nasdanika.html.emf;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;

/**
 * Delegates to value ViewAction.
 * @author Pavel
 *
 */
public class EReferenceSingleValuePropertySourceViewAction extends EReferenceSingleValuePropertySource implements Action {

	private String featureRole;
	private Action parent;

	public EReferenceSingleValuePropertySourceViewAction(EObject eObject, EReference feature, String featureRole, Action parent) {
		super(eObject, feature);
		this.featureRole = featureRole;
		this.parent = parent;
	}
	
	@Override
	public Object execute(ViewGenerator viewGenerator, Map<String, Object> input) {
		EObject value = (EObject) getValue();
		if (value == null) {
			// TODO - some action to create an object for containment references.
			return null;
		}
		
		Action viewAction = (Action) EcoreUtil.getRegisteredAdapter(value, ViewAction.class);
		if (viewAction != null) {
			return viewAction.execute(viewGenerator, input);
		}
		return null;
	}

	@Override
	public Label getCategory() {
		return null;
	}

	@Override
	public boolean isDisabled() {
		return false;
	}

	@Override
	public String getConfirmation() {
		return null;
	}

	@Override
	public boolean isFloatRight() {
		return false;
	}

	@Override
	public List<? extends Action> getChildren() {
		return Collections.emptyList();
	}

	@Override
	public boolean isInRole(String role) {
		return featureRole != null && featureRole.equals(role);
	}

	@Override
	public Action getParent() {
		return parent;
	}

	@Override
	public ActionActivator getActivator() {
		return null;
	}

}
