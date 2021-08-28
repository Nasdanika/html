package org.nasdanika.html.model.app.gen;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Event;
import org.nasdanika.html.HTMLElement;
import org.nasdanika.html.app.legacy.impl.Action;
import org.nasdanika.html.app.legacy.impl.ActionActivator;
import org.nasdanika.html.app.legacy.impl.BindingActionActivator;
import org.nasdanika.html.app.legacy.impl.NavigationActionActivator;
import org.nasdanika.html.app.legacy.impl.ScriptActionActivator;
import org.nasdanika.html.app.legacy.impl.Util;
import org.nasdanika.html.model.app.Link;

public class LinkSupplierFactoryAdapter<M extends Link> extends LabelSupplierFactoryAdapter<M> {
	
	public LinkSupplierFactoryAdapter(M label) {
		super(label);
	}
	
	/**
	 * Override in Link to create a link.
	 * @param context
	 * @param progressMonitor
	 * @return
	 */
	protected Object text(Context context, ProgressMonitor progressMonitor) {
		return context.interpolateToString(getTarget().getText());
	}
	
	
	protected void bindLink(Action action, HTMLElement<?> anchor) {
		if (!action.isDisabled()) {
			ActionActivator activator = getActionActivator(action);
			if (activator instanceof NavigationActionActivator) {
				anchor.attribute("href", ((NavigationActionActivator) activator).getUrl(getString(BASE_URI_PROPERTY)));
				if (!Util.isBlank(action.getConfirmation())) {
					anchor.on(Event.click, "return confirm('"+action.getConfirmation()+"');");
				}
			} else if (activator instanceof ScriptActionActivator) {
				String code = ((ScriptActionActivator) activator).getCode();
				if (!Util.isBlank(action.getConfirmation())) {
					code = "if (confirm('"+action.getConfirmation()+"')) { "+code+" }";
				}
				anchor.on(Event.click, code);
				anchor.style("cursor", "pointer");
			} else if (activator instanceof BindingActionActivator) {
				((BindingActionActivator) activator).bind(anchor, this);
			}
		}
	}	
	
}
