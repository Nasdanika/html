package org.nasdanika.html.app;

import java.util.Map;

public class NavigationActionImpl extends ActionImpl implements NavigationAction {
		
	private String href;
	
	public NavigationActionImpl() {
		super();
	}

	public NavigationActionImpl(Map<String, Object> data) {
		super(data);
		setHref((String) data.get("href"));
	}

	@Override
	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}
	
}
