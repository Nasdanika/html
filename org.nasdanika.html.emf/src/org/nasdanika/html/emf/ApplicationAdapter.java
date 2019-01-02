package org.nasdanika.html.emf;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.app.Application;

/**
 * {@link Application} adapter.
 * @author Pavel Vlasov
 *
 */
public class ApplicationAdapter extends AdapterImpl implements Application {
	
	protected Application app;
	
	public ApplicationAdapter(Application app) {
		this.app = app;
	}
			
	@Override
	public boolean isAdapterForType(Object type) {
		return Application.class == type;
	}

	@Override
	public Object produce(int indent) {
		return app.produce(indent);
	}

	@Override
	public Application header(Object... content) {
		app.header(content);
		return this;
	}

	@Override
	public Application navigationBar(Object... content) {
		app.navigationBar(content);
		return this;
	}

	@Override
	public Application navigationPanel(Object... content) {
		app.navigationPanel(content);
		return this;
	}

	@Override
	public Application contentPanel(Object... content) {
		app.contentPanel(content);
		return this;
	}

	@Override
	public Application footer(Object... content) {
		app.footer(content);		
		return this;
	}

	@Override
	public HTMLPage getHTMLPage() {		
		return app.getHTMLPage();
	}
	
	@Override
	public String toString() {
		return app.toString();
	}

}
