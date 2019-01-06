package org.nasdanika.html.emf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.PropertySource;
import org.nasdanika.html.app.SingleValuePropertySource;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.ViewSingleValuePropertySourceViewPart;
import org.nasdanika.html.bootstrap.Color;

/**
 * Adapts {@link EObject} to {@link Action}.
 * @author Pavel Vlasov
 *
 */
public class EObjectViewActionAdapter extends AdapterImpl implements Action {
	
	private SingleValuePropertySource propertySource;
	
	protected void setPropertySource(SingleValuePropertySource propertySource) {
		this.propertySource = propertySource;
	}
	
	protected PropertySource getPropertySource() {
		return propertySource;
	}
	
	@Override
	public void setTarget(Notifier newTarget) {
		super.setTarget(newTarget);
		setPropertySource((SingleValuePropertySource) EcoreUtil.getRegisteredAdapter((EObject) target, SingleValuePropertySource.class));
	}
	
	@Override
	public void unsetTarget(Notifier oldTarget) {
		super.unsetTarget(oldTarget);
		setPropertySource(null);
	}
				
	@Override
	public boolean isAdapterForType(Object type) {
		return Action.class == type; // ViewAction marker interface???
	}

	@Override
	public Object execute(ViewGenerator viewGenerator, Map<String, Object> input) {
		if (propertySource instanceof SingleValuePropertySource) {
			return new ViewSingleValuePropertySourceViewPart((SingleValuePropertySource) propertySource).generate(viewGenerator);
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
	public List<? extends Action> getContextActions() {
		return getPropertySource() == null ? null : getPropertySource().getActions();		
	}

	@Override
	public List<? extends Action> getSections() {
		return Collections.emptyList();
	}
	
	private Action parent;
	
	public void setParent(Action parent) {
		this.parent = parent;
	}

	@Override
	public Action getParent() {
		return parent;
	}

	@Override
	public List<Action> getPath() {
		if (getParent() == null) {
			return Collections.emptyList();
		}
		
		List<Action> ret = new ArrayList<>(getParent().getPath());
		ret.add(getParent());
		return ret;
	}

	@Override
	public ActionActivator getActivator() {
		return null;
	}

	@Override
	public String getIcon() {
		return getPropertySource() == null ? null : getPropertySource().getIcon();
	}

	@Override
	public String getText() {
		return getPropertySource() == null ? null : getPropertySource().getText();
	}

	@Override
	public String getTooltip() {
		return getPropertySource() == null ? null : getPropertySource().getTooltip();
	}

	@Override
	public Color getColor() {
		return getPropertySource() == null ? null : getPropertySource().getColor();
	}

	@Override
	public boolean isOutline() {
		return getPropertySource() == null ? null : getPropertySource().isOutline();
	}

	@Override
	public String getDescription() {
		return getPropertySource() == null ? null : getPropertySource().getDescription();
	}

	@Override
	public Object getId() {
		return getPropertySource() == null ? null : getPropertySource().getId();
	}

	@Override
	public String getNotification() {
		return getPropertySource() == null ? null : getPropertySource().getNotification();
	}

}
