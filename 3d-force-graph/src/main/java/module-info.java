import org.nasdanika.html.forcegraph3d.ForceGraph3DFactory;
import org.nasdanika.html.forcegraph3d.impl.DefaultForceGraph3DFactory;

module org.nasdanika.html.forcegraph3d {
		
	requires transitive org.nasdanika.html;
	
	exports org.nasdanika.html.forcegraph3d;
	exports org.nasdanika.html.forcegraph3d.impl;
	
	provides ForceGraph3DFactory with DefaultForceGraph3DFactory;
	
}
