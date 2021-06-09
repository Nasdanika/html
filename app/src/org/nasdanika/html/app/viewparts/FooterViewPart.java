package org.nasdanika.html.app.viewparts;

import java.util.List;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.Fragment;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Decorator;
import org.nasdanika.html.app.DecoratorProvider;
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
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		// Single-level footer actions. 
		Fragment ret = viewGenerator.getHTMLFactory().fragment();
		DecoratorProvider decoratorProvider = viewGenerator.computingContext().get(DecoratorProvider.class);
		Decorator actionDecorator = decoratorProvider == null ? null : decoratorProvider.getDecorator("application/footer/action");
		
		for (Action ca: footerActions) {
			if (!ret.isEmpty()) {
				ret.content("&nbsp;&bull;&nbsp;");
			}
			Tag link = viewGenerator.link(ca);
			viewGenerator.decorate(link, actionDecorator);
			link.addClass("nsd-footer-action");
			ret.content(link);
		}
		return ret;
	}

}
