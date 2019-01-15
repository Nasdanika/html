package org.nasdanika.html.app.viewparts;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;

public class FooterViewPart implements ViewPart {

	protected Action rootAction;

	public FooterViewPart(Action rootAction) {
		this.rootAction = rootAction;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator) {
		// Single-level footer actions. 
		Fragment ret = viewGenerator.getHTMLFactory().fragment();
		for (Action ca: rootAction.getContextChildren()) {
			if (!ret.isEmpty()) {
				ret.content("&nbsp;&bull;&nbsp;");
			}
			ret.content(viewGenerator.link(ca));
		}
		return ret;
	}

}
