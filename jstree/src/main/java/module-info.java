import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.jstree.impl.DefaultJsTreeFactory;

module org.nasdanika.html.jstree {
		
	requires transitive org.nasdanika.html;
	
	exports org.nasdanika.html.jstree;
	exports org.nasdanika.html.jstree.impl;
	
	provides JsTreeFactory with DefaultJsTreeFactory;
	
}
