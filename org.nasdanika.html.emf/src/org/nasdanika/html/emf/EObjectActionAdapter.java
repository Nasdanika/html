package org.nasdanika.html.emf;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.Color;

/**
 * Adapts {@link EObject} to {@link Action}.
 * @author Pavel Vlasov
 *
 */
public class EObjectActionAdapter extends AdapterImpl implements Action {
				
	@Override
	public boolean isAdapterForType(Object type) {
		return Action.class == type;
	}

	@Override
	public Object execute(ViewGenerator viewGenerator, Map<String, Object> input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Label getCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDisabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getConfirmation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFloatRight() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<? extends Action> getChildren() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public List<? extends Action> getContextActions() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public List<? extends Action> getSections() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Action> getPath() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}

	@Override
	public ActionActivator getActivator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return String.valueOf(getTarget()); // for testing
	}

	@Override
	public String getTooltip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isOutline() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNotification() {
		// TODO Auto-generated method stub
		return null;
	}

}
