package org.nasdanika.html.forcegraph3d.impl;

import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.forcegraph3d.ForceGraph3D;
import org.nasdanika.html.forcegraph3d.ForceGraph3DFactory;

public class DefaultForceGraph3DFactory implements ForceGraph3DFactory {

	@Override
	public <P extends HTMLPage> P cdn(P page) {
		page.script("https://cdn.jsdelivr.net/npm/3d-force-graph");
		return page;
	}

	@Override
	public ForceGraph3D create() {
		return new ForceGraph3DImpl(this);
	}

}
