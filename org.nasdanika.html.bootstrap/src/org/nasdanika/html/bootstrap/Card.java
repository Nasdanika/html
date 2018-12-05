package org.nasdanika.html.bootstrap;

import org.nasdanika.html.Tag;

public interface Card extends BootstrapElement<Tag, Card> {

	/**
	 * @return Title tag for styling and adding content.
	 */
	TagBootstrapElement getTitle();
	
	/**
	 * @return Body tag for styling and adding content.
	 */
	TagBootstrapElement getBody();
	
	/**
	 * @return Footer tag for styling and adding content.
	 */
	TagBootstrapElement getFooter();

}
