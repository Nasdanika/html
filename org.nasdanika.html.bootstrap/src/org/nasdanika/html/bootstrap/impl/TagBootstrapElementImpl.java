package org.nasdanika.html.bootstrap.impl;

import org.nasdanika.html.Tag;
import org.nasdanika.html.bootstrap.BootstrapFactory;
import org.nasdanika.html.bootstrap.TagBootstrapElement;

public class TagBootstrapElementImpl extends WrappingBootstrapElementImpl<Tag, TagBootstrapElement> implements TagBootstrapElement {

	public TagBootstrapElementImpl(BootstrapFactory factory, Tag tag) {
		super(factory, tag);
	}

}
