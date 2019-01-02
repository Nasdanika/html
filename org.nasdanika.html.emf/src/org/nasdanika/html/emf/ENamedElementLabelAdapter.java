package org.nasdanika.html.emf;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.ENamedElement;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.bootstrap.Color;

/**
 * Adapts {@link ENamedElement} to {@link Label}.
 * 
 * TODO - load things from annotations, multiple source - resource strings. Resource provider is passed by the factory.
 * @author Pavel Vlasov
 *
 */
public class ENamedElementLabelAdapter extends AdapterImpl implements Label {
				
	@Override
	public boolean isAdapterForType(Object type) {
		return Label.class == type;
	}

	@Override
	public String getIcon() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
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
