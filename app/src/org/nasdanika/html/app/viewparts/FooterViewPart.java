package org.nasdanika.html.app.viewparts;

import java.util.List;

import org.nasdanika.html.Fragment;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;

/**
 * Outputs actions separated by a bullet. 
 * @author Pavel
 *
 */
public class FooterViewPart implements ViewPart {

	protected List<? extends Action> footerActions;

	public FooterViewPart(List<? extends Action> footerActions) {
		this.footerActions = footerActions;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator) {
		// Single-level footer actions. 
		Fragment ret = viewGenerator.get(HTMLFactory.class).fragment();
		for (Action ca: footerActions) {
			if (!ret.isEmpty()) {
				ret.content("&nbsp;&bull;&nbsp;");
			}
			ret.content(viewGenerator.link(ca));
		}
		return ret;
	}

}
