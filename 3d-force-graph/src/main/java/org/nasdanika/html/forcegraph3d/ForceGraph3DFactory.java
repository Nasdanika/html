package org.nasdanika.html.forcegraph3d;

import org.nasdanika.html.HTMLPage;
import org.nasdanika.html.forcegraph3d.impl.DefaultForceGraph3DFactory;

public interface ForceGraph3DFactory {
	
	ForceGraph3DFactory INSTANCE = new DefaultForceGraph3DFactory();
	
	/**
	 * Adds CDN script declaration to the page head. 
	 * @param page
	 * @return
	 */
	<P extends HTMLPage> P cdn(P page);
	
	ForceGraph3D create();

}
