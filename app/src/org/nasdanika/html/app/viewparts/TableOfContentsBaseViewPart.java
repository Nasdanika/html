package org.nasdanika.html.app.viewparts;

import org.nasdanika.html.app.ViewPart;

/**
 * Base class for tables of content.
 * @author Pavel
 *
 */
public abstract class TableOfContentsBaseViewPart implements ViewPart {

	protected String role;
	protected String header;
	protected int depth;

	public TableOfContentsBaseViewPart(
			String role,
			String header,
			int depth) {

		this.role = role;
		this.header = header;
		this.depth = depth;
	}

}
