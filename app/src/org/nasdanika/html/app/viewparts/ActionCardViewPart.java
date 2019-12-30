package org.nasdanika.html.app.viewparts;

import java.util.List;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.Card;
import org.nasdanika.html.bootstrap.Navs;
import org.nasdanika.html.bootstrap.TagBootstrapElement;

/**
 * Generates a card from action and its section children.
 * @author Pavel
 *
 */
public class ActionCardViewPart implements ViewPart {

	private Action action;
	private Action activeAction;
	private int sectionLevel;
	private int headerLevel;

	/**
	 * 
	 * @param action Action to generate card from 
	 * @param sectionLevel section level is used if section style is AUTO.
	 * @param headerLevel Header level. 
	 */
	public ActionCardViewPart(Action action, Action activeAction, int sectionLevel, int headerLevel) {
		this.action = action;
		this.activeAction = activeAction;
		this.sectionLevel = sectionLevel;
		this.headerLevel = headerLevel;
	}

	@Override
	public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
		BootstrapFactory bootstrapFactory = viewGenerator.get(BootstrapFactory.class);
		Card card = bootstrapFactory.card();

		HTMLFactory htmlFactory = bootstrapFactory.getHTMLFactory();
		Tag hTag = htmlFactory.tag("H"+Math.min(6, headerLevel));
		viewGenerator.label(action, hTag);
		hTag.addClass("card-header");
		if (action.getColor() != null) {
			card.border(action.getColor());
			TagBootstrapElement wrapped = bootstrapFactory.wrap(hTag);
			wrapped.background(action.getColor());
			wrapped.border(action.getColor());
		}
		card.toHTMLElement().content(hTag);
		
		TagBootstrapElement body = card.getBody();
		Tag bodyHtmlElement = body.toHTMLElement();
		bodyHtmlElement.content(action.generate(viewGenerator, progressMonitor));
		
		ViewPart subSectionsViewPart = action.createSectionsViewPart(activeAction, sectionLevel+1, headerLevel+1);
		if (subSectionsViewPart != null) {
			bodyHtmlElement.content(subSectionsViewPart.generate(viewGenerator, progressMonitor)); // TODO - split monitor.
		}

		List<Action> contextChildren = action.getContextChildren();
		if (!contextChildren.isEmpty()) {
			Navs contextNavs = viewGenerator.categorizedLinkNavs(contextChildren, activeAction, null);
//			contextNavs.background(Color.LIGHT);
			TagBootstrapElement footer = card.getFooter();
			footer.toHTMLElement().content(contextNavs);
		}
		return card;
	}

}
