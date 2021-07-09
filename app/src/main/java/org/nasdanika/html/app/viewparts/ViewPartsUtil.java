package org.nasdanika.html.app.viewparts;

import org.nasdanika.html.Tag;
import org.nasdanika.html.TagName;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.NavigationActionActivator;

public class ViewPartsUtil {
	
	private ViewPartsUtil() {
		// Utility class
	}
		
	/**
	 * Creates a section action anchor. If action activator is {@link NavigationActionActivator} and its url has a fragment then anchor is that fragment.
	 * Otherwise it is an action ID, if ID is not null.
	 * @param section
	 * @return
	 */
	public static Tag sectionAnchor(Action section) {
		Object anchor = section.getId();
		ActionActivator activator = section.getActivator();
		if (activator instanceof NavigationActionActivator) {
			NavigationActionActivator nac = (NavigationActionActivator) activator;
			String url = nac.getUrl(null);
			int idx = url.indexOf("#");
			if (idx != -1 && idx != url.length() - 1) {
				anchor = url.substring(idx + 1);
			}
		}
		return anchor == null ? null : TagName.a.create().attribute("name", anchor);				
	}

}
